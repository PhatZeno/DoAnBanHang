package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class DanhSachSearchActivity extends AppCompatActivity implements SanPhamAdapter.Listener{
    DBHelper dbHelper;
    private ImageView imageBACK;
    RecyclerView recyclerView;
    ArrayList<Sanpham> sanphams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_search);
        dbHelper=new DBHelper(DanhSachSearchActivity.this);

        String search = getIntent().getStringExtra("search");

        recyclerView=findViewById(R.id.view1);
        imageBACK = findViewById(R.id.back);

        sanphams=dbHelper.getallSp(search);
        recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachSearchActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SanPhamAdapter(R.id.view1, this, sanphams, DanhSachSearchActivity.this));
        //recommendAdapter.notifyDataSetChanged();


        imageBACK.setOnClickListener(view -> {
            finish();
        });


    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {
        Intent intent = new Intent(this, ChiTietSanPhamActivity.class);
        intent.putExtra("id",sanpham.getID());
        startActivity(intent);
    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}