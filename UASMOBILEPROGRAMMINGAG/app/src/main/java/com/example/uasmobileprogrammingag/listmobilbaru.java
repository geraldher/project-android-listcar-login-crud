package com.example.uasmobileprogrammingag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class listmobilbaru extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> mobil,harga;
    DatabaseHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmobilbaru);
        DB = new DatabaseHelper(this);
        mobil = new ArrayList<>();
        harga = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,mobil,harga);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from mobil", null);
        if (cursor.getCount()==0){
            Toast.makeText(listmobilbaru.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while (cursor.moveToNext()){
                mobil.add(cursor.getString(0));
                harga.add(cursor.getString(1));
            }
        }

    }
}