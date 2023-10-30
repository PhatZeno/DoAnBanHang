package com.example.doanbanhang.Activity.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;


public class LaptopActivity extends AppCompatActivity implements SanPhamAdapter.Listener {

    DBHelper dbHelper;
    RecyclerView RLaptop;
    ArrayList<Sanpham> sanphams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        RLaptop=findViewById(R.id.rAllLaptop);
        dbHelper=new DBHelper(LaptopActivity.this);

        sanphams=dbHelper.getallLaptop();
        sanphams.clear();
        sanphams=dbHelper.getallLaptop();
        RLaptop.setAdapter(new SanPhamAdapter(R.id.rAllLaptop, this, sanphams, LaptopActivity.this));
        RLaptop.setLayoutManager(new LinearLayoutManager(LaptopActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}