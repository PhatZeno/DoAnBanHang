package com.example.doanbanhang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanbanhang.ChiTietDonHangActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangVH> {
    ArrayList<DonHang> DonHangs;
    DonHangAdapter.Listener listener;
    Context context;
    int recyclerViewId;
    DBHelper dbHelper;
    public DonHangAdapter(int recyclerViewId, DonHangAdapter.Listener listener, ArrayList<DonHang> DonHangs, Context context) {
        this.recyclerViewId = recyclerViewId;
        this.listener = listener;
        this.DonHangs = DonHangs;
        this.context = context;
        this.dbHelper=new DBHelper(context);
    }
    @NonNull
    @Override
    public DonHangAdapter.DonHangVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donhang_item, parent,false);
        return new DonHangAdapter.DonHangVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.DonHangVH holder, @SuppressLint("RecyclerView") int position) {
        DonHang DonHang = DonHangs.get(position);
        holder.MaDonHang.setText(String.valueOf(DonHang.getMaDonHang()));
        holder.NgayMua.setText(DonHang.getNgayMua());
        holder.MaKhachHang.setText(DonHang.getMaKhachHang());
        holder.setIsRecyclable(false);

        holder.TongTien.setText(String.valueOf(DonHang.getTongTien()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener4(recyclerViewId, DonHang, DonHangs.size());
                SharedPreferences sharedPref = context.getSharedPreferences("myKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", String.valueOf(position));
                editor.apply();
                Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                ArrayList<ChiTietDonHang> chiTietDonHangs=dbHelper.getChiTietDonHang(DonHang.getMaDonHang());
                intent.putExtra("chiTietDonHang", chiTietDonHangs);
                Log.d("TAG2", "onClick: "+chiTietDonHangs);
                intent.putExtra("tenkhachhang",DonHang.getMaKhachHang());
                intent.putExtra("madonhang",String.valueOf(DonHang.getMaDonHang()));
                context.startActivity(intent);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("count", DonHangs.size());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener4(recyclerViewId, DonHang, v);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {

        return DonHangs.size();

    }



    class DonHangVH extends RecyclerView.ViewHolder{


        TextView MaDonHang, MaKhachHang, NgayMua, TongTien;

        public DonHangVH
                (@NonNull View itemView) {
            super(itemView);
            MaDonHang = itemView.findViewById(R.id.RMaDonhang);
            MaKhachHang =itemView.findViewById(R.id.RMaKhachHang);
            NgayMua =itemView.findViewById(R.id.RNgaymua);
            TongTien =itemView.findViewById(R.id.RTongtien);
        }
    }

    public interface Listener {
        void onItemClickListener4(int recyclerViewId, DonHang DonHang, int size);
        void onItemLongClickListener4(int recyclerViewId, DonHang DonHang, View view);
    }
}
