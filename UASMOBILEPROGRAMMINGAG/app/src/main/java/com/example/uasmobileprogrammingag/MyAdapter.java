package com.example.uasmobileprogrammingag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList mobil_id, harga_id;

    public MyAdapter(Context context, ArrayList mobil_id, ArrayList harga_id) {
        this.context = context;
        this.mobil_id = mobil_id;
        this.harga_id = harga_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mobil_id.setText(String.valueOf(mobil_id.get(position)));
        holder.harga_id.setText(String.valueOf(harga_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return mobil_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mobil_id, harga_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mobil_id = itemView.findViewById(R.id.txtmobil);
            harga_id = itemView.findViewById(R.id.txtharga);
        }
    }
}
