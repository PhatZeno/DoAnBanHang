package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.AddSpActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ListSanPham extends AppCompatActivity implements SanPhamAdapter.Listener{

    SanPhamAdapter sanPhamAdapter;
    DBHelper dbHelper;
    RecyclerView rsp;
    ArrayList<Sanpham> sanphams;
    ImageView addsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);
        rsp=findViewById(R.id.RecyclerviewProduct);
        dbHelper=new DBHelper(ListSanPham.this);
        sanphams=dbHelper.getallSp();
        sanPhamAdapter = new SanPhamAdapter(R.id.RecyclerviewProduct, ListSanPham.this, sanphams, ListSanPham.this); // Khởi tạo adapter
        rsp.setAdapter(sanPhamAdapter);
        rsp.setLayoutManager(new LinearLayoutManager(ListSanPham.this, LinearLayoutManager.VERTICAL, false));
        addsp=findViewById(R.id.imgaddsp);
        addsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListSanPham.this, AddSpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}