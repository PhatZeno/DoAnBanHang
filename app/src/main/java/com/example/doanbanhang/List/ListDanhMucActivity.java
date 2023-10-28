package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.Quanli.Quanlidanhmuchang;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.DanhMucAdapter;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ListDanhMucActivity extends AppCompatActivity implements DanhMucAdapter.Listener {

    DBHelper dbHelper;
    ArrayList<DanhMuc> danhMucs;
    ImageView imgadd;
    RecyclerView recyclerViewDanhmuc;
    DanhMucAdapter danhMucAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_danh_muc);
        dbHelper=new DBHelper(ListDanhMucActivity.this);
        recyclerViewDanhmuc=findViewById(R.id.RecyclerviewDanhmuc);
        danhMucs=dbHelper.getalldanhmuc();
        danhMucAdapter = new DanhMucAdapter(R.id.RecyclerviewDanhmuc, ListDanhMucActivity.this, danhMucs, ListDanhMucActivity.this); // Khởi tạo adapter
        recyclerViewDanhmuc.setAdapter(danhMucAdapter);
        recyclerViewDanhmuc.setLayoutManager(new LinearLayoutManager(ListDanhMucActivity.this, LinearLayoutManager.VERTICAL, false));
        imgadd=findViewById(R.id.adddanhmuc);
        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListDanhMucActivity.this, Quanlidanhmuchang.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, DanhMuc DanhMuc, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, DanhMuc DanhMuc, View view) {

    }
}