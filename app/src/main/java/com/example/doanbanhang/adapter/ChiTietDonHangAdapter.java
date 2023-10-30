package com.example.doanbanhang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanbanhang.R;
import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ChiTietDonHangVH> {
    ArrayList<ChiTietDonHang> ChiTietDonHangs;
    ChiTietDonHangAdapter.Listener listener;
    Context context;
    int recyclerViewId;
    public ChiTietDonHangAdapter(int recyclerViewId, ChiTietDonHangAdapter.Listener listener, ArrayList<ChiTietDonHang> ChiTietDonHangs, Context context) {
        this.recyclerViewId = recyclerViewId;
        this.listener = listener;
        this.ChiTietDonHangs = ChiTietDonHangs;
        this.context = context;
    }
    @NonNull
    @Override
    public ChiTietDonHangAdapter.ChiTietDonHangVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chitietdonhang_item, parent,false);
        return new ChiTietDonHangAdapter.ChiTietDonHangVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangAdapter.ChiTietDonHangVH holder, @SuppressLint("RecyclerView") int position) {
        ChiTietDonHang ChiTietDonHang = ChiTietDonHangs.get(position);
        holder.setIsRecyclable(false);
        DBHelper dbHelper=new DBHelper(context);
        String tensp=dbHelper.getProductName(ChiTietDonHang.getMaSanPham());
        holder.TenSp.setText(tensp);
        holder.Soluong.setText(String.valueOf(ChiTietDonHang.getSoLuong()));
        holder.gia.setText(String.valueOf(ChiTietDonHang.getGiaBan()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClickListener4(recyclerViewId, ChiTietDonHang, ChiTietDonHangs.size());
                SharedPreferences sharedPref = context.getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", String.valueOf(position));
                editor.apply();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("count", ChiTietDonHangs.size());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener4(recyclerViewId, ChiTietDonHang, v);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {

        return ChiTietDonHangs.size();

    }



    class ChiTietDonHangVH extends RecyclerView.ViewHolder{


        TextView TenSp,Soluong,Madonhang,gia;

        public ChiTietDonHangVH
                (@NonNull View itemView) {
            super(itemView);
            TenSp =itemView.findViewById(R.id.Rtensanpham);
            Soluong=itemView.findViewById(R.id.Rsoluong);
            gia=itemView.findViewById(R.id.Rgia);
        }
    }

    public interface Listener {
        void onItemClickListener4(int recyclerViewId, ChiTietDonHang ChiTietDonHang, int size);
        void onItemLongClickListener4(int recyclerViewId, ChiTietDonHang ChiTietDonHang, View view);
    }
}
