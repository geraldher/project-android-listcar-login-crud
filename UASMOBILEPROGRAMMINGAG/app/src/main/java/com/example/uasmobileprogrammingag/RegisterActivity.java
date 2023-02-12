package com.example.uasmobileprogrammingag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbhelper;
    EditText username2,password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbhelper = new DatabaseHelper(this);
        username2 = (EditText) findViewById(R.id.input_username2);
        password2 = (EditText) findViewById(R.id.input_password2);


    }

    public void daftar1(View n) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.execSQL("insert into Admin(username,password) values('" +
                username2.getText().toString() + "','" +
                password2.getText().toString() + "')");
        Toast.makeText(getApplicationContext(), "Berhasil",Toast.LENGTH_LONG).show();
        Intent s = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(s);
    }
    public void kelogin(View b) {
        Intent n = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(n);
    }
}