package com.example.uasmobileprogrammingag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.uasmobileprogrammingag.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    DatabaseHelper dbHelper;
    Button btlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
        btlogin = findViewById(R.id.btn_login);
        dbHelper = new DatabaseHelper(this);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameCheck = username.getText().toString();
                String passCheck = password.getText().toString();
                Cursor cursor = dbHelper.getData();
                if(cursor.getCount() == 0){
                    Toast.makeText(LoginActivity.this,"No entries Exists",Toast.LENGTH_LONG).show();
                }
                if (loginCheck(cursor,usernameCheck,passCheck)) {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    /*intent.putExtra("email",usernameCheck);*/
                    username.setText("");
                    password.setText("");
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Wrong Credential");
                    builder.setMessage("Wrong Credential");
                    builder.show();
                }
                dbHelper.close();
            }
        });
    }
    public static boolean loginCheck(Cursor cursor,String usernameCheck,String passCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(usernameCheck)) {
                if (cursor.getString(1).equals(passCheck)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}