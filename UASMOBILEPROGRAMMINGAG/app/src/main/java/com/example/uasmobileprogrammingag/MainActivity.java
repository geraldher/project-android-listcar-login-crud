package com.example.uasmobileprogrammingag;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void karyawan5(View view){
        Intent intent = new Intent(MainActivity.this, KaryawanActivity.class);
        startActivity(intent);
    }
    public void infomobil(View view2){
        Intent intent2 = new Intent(MainActivity.this, listmobilbaru.class);
        startActivity(intent2);
    }
}