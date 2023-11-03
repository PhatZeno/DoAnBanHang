package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanbanhang.List.ListDanhMucActivity;
import com.example.doanbanhang.adapter.ChiTietDonHangAdapter;
import com.example.doanbanhang.adapter.DonHangAdapter;
import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

//public class ChiTietDonHangActivity extends AppCompatActivity implements ChiTietDonHangAdapter.Listener{
//    DBHelper dbHelper;
//    ImageView imgadd;
//    RecyclerView recyclerViewChitietdonhang;
//    ChiTietDonHangAdapter chiTietDonHangAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chi_tiet_don_hang);
//        dbHelper=new DBHelper(ChiTietDonHangActivity.this);
//        recyclerViewChitietdonhang=findViewById(R.id.RChitietdonhang);
//    }
//    Intent intent = getIntent();
//    ArrayList<ChiTietDonHang> arrChiTietDonHangs = (ArrayList<ChiTietDonHang>) intent.getSerializableExtra("chiTietDonHang");
//    chiTietDonHangAdapter = new ChiTietDonHangAdapter(R.id.RChitietdonhang, ChiTietDonHangActivity.this, arrChiTietDonHangs, ChiTietDonHangActivity.this); // Khởi tạo adapter
//    chiTietDonHangAdapter.setAdapter(chiTietDonHangAdapter);
//    chiTietDonHangAdapter.setLayoutManager(new LinearLayoutManager(ChiTietDonHangActivity.this, LinearLayoutManager.VERTICAL, false));
//
//    @Override
//    public void onItemClickListener4(int recyclerViewId, ChiTietDonHang ChiTietDonHang, int size) {
//
//    }
//
//    @Override
//    public void onItemLongClickListener4(int recyclerViewId, ChiTietDonHang ChiTietDonHang, View view) {
//
//    }
//}
public class ChiTietDonHangActivity extends AppCompatActivity implements ChiTietDonHangAdapter.Listener{
    DBHelper dbHelper;
    RecyclerView recyclerViewChitietdonhang;
    ChiTietDonHangAdapter chiTietDonHangAdapter;
    TextView MaDonHang,MaKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        MaDonHang=findViewById(R.id.CTMaDonHang);
        MaKhachHang=findViewById(R.id.CTMaKhachHang);
        dbHelper = new DBHelper(ChiTietDonHangActivity.this);
        recyclerViewChitietdonhang = findViewById(R.id.RChitietdonhang);

        Intent intent = getIntent();
        ArrayList<ChiTietDonHang> arrChiTietDonHangs = (ArrayList<ChiTietDonHang>) intent.getSerializableExtra("chiTietDonHang");
        String tenKhachHang = getIntent().getStringExtra("tenkhachhang");
        MaKhachHang.setText(tenKhachHang);
        String madonhang = getIntent().getStringExtra("madonhang");
        MaDonHang.setText(madonhang);
        chiTietDonHangAdapter = new ChiTietDonHangAdapter(R.id.RChitietdonhang, ChiTietDonHangActivity.this, arrChiTietDonHangs, ChiTietDonHangActivity.this); // Khởi tạo adapter
        recyclerViewChitietdonhang.setAdapter(chiTietDonHangAdapter);
        recyclerViewChitietdonhang.setLayoutManager(new LinearLayoutManager(ChiTietDonHangActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, ChiTietDonHang ChiTietDonHang, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, ChiTietDonHang ChiTietDonHang, View view) {

    }
}
