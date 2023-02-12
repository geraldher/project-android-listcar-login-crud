package com.example.uasmobileprogrammingag;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListMobil extends AppCompatActivity {
    String[] daftar;
    String[] daftar2;
    public static ListMobil lh;
    DatabaseHelper dbcenter;
    protected Cursor cursor;
    ListView listView;
    Button btkembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mobil);
        lh = this;
        dbcenter = new DatabaseHelper(this);
        RefreshList();
        btkembali = (Button) findViewById(R.id.btkembali);
        btkembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }

    private void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("Select * from mobil", null);
        daftar = new String[cursor.getCount()];
        daftar2 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
            /*daftar2[cc] = cursor.getString(1).toString();*/
        }
        listView = (ListView) findViewById(R.id.listmobil);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
    public void kembali10(View view){
        Intent intent = new Intent(ListMobil.this, MainActivity.class);
        startActivity(intent);
    }
}