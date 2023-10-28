package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.DanhMucAdapter;
import com.example.doanbanhang.adapter.DonHangAdapter;
import com.example.doanbanhang.adapter.UserAdapter;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ListDonHang extends AppCompatActivity implements DonHangAdapter.Listener{
    DBHelper dbHelper;
    ArrayList<DonHang> donHangs;
    ImageView imgadd;
    RecyclerView recyclerViewDonHang;
    DonHangAdapter donHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_don_hang);
        dbHelper=new DBHelper(ListDonHang.this);
        recyclerViewDonHang=findViewById(R.id.RecyclerviewDonhang);
        donHangs=dbHelper.getalldonhang();
        donHangAdapter = new DonHangAdapter(R.id.RecyclerviewDonhang, ListDonHang.this, donHangs, ListDonHang.this); // Khởi tạo adapter
        recyclerViewDonHang.setAdapter(donHangAdapter);
        recyclerViewDonHang.setLayoutManager(new LinearLayoutManager(ListDonHang.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, DonHang DonHang, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, DonHang DonHang, View view) {

    }
}