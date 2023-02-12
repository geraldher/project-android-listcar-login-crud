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

public class KaryawanActivity extends AppCompatActivity {
    String[] daftar;
    public static KaryawanActivity lh;
    DatabaseHelper dbcenter;
    protected Cursor cursor;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan);
        lh = this;
        dbcenter = new DatabaseHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("Select * from Penyewa", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }
        listView = (ListView) findViewById(R.id.listviewkaryawan);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Data", "Tambah", "Hapus", "Kembali ke menu awal"};
                AlertDialog.Builder builder = new AlertDialog.Builder(KaryawanActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), DetailKaryawan.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(KaryawanActivity.this, daftarkaryawanActivity.class);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from penyewa where nama = '" + selection + "'");
                                RefreshList();
                                break;
                            case 3:
                                Intent is = new Intent(KaryawanActivity.this, MainActivity.class);
                                startActivity(is);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
    public void karyawan2(View view){
        Intent intent6 = new Intent(KaryawanActivity.this, daftarkaryawanActivity.class);
        startActivity(intent6);
    }
}

