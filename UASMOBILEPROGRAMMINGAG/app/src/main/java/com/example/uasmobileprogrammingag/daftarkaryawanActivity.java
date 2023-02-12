package com.example.uasmobileprogrammingag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class daftarkaryawanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText nama, alamat, no_hp, lama;
    Button selesai;
    String sNama;
    String sAlamat;
    String sNo;
    String sMerk;
    String sLama;
    int iLama, iPromo, iHarga;
    double dTotal;
    String[] merkmobil = {"Avanza","Xenia"};
    Spinner spinner;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarkaryawan);

        dbHelper = new DatabaseHelper(this);

        spinner = findViewById(R.id.spinner);
        selesai = findViewById(R.id.selesaiHitung);
        nama = findViewById(R.id.eTNama);
        alamat = findViewById(R.id.eTAlamat);
        no_hp = findViewById(R.id.eTHP);
        lama = findViewById(R.id.eTLamaSewa);

        List<String> lables = dbHelper.getAllCategories();

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lables);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

/*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

    /*private void loadSpinnerData() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> categories = db.getAllCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }*/


    }
    public void selesaidaftar(View v5){

        sNama = nama.getText().toString();
        sAlamat = alamat.getText().toString();
        sNo = no_hp.getText().toString();
        sLama = lama.getText().toString();
        if (sNama.isEmpty() || sAlamat.isEmpty() || sNo.isEmpty() || sLama.isEmpty()) {
            Toast.makeText(daftarkaryawanActivity.this, "(*) tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sMerk.equals("Avanza")) {
            iHarga = 400000;
        } else if (sMerk.equals("Xenia")) {
            iHarga = 400000;
        } else if (sMerk.equals("Ertiga")) {
            iHarga = 400000;
        } else if (sMerk.equals("APV")) {
            iHarga = 450000;
        } else if (sMerk.equals("Innova")) {
            iHarga = 500000;
        } else if (sMerk.equals("Xpander")) {
            iHarga = 550000;
        } else if (sMerk.equals("Pregio")) {
            iHarga = 550000;
        } else if (sMerk.equals("Elf")) {
            iHarga = 700000;
        } else if (sMerk.equals("Alphard")) {
            iHarga = 1500000;
        }

        iLama = Integer.parseInt(sLama);
        dTotal = (iHarga * iLama)/* - (iHarga * iLama)*/;

        SQLiteDatabase dbH = dbHelper.getWritableDatabase();
        dbH.execSQL("INSERT INTO penyewa (nama, alamat, no_hp, merek, lama, total) VALUES ('" +
                sNama + "','" +
                sAlamat + "','" +
                sNo + "','" +
                sMerk + "','" +
                sLama + "','" +
                dTotal + "');");
             KaryawanActivity.lh.RefreshList();
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}