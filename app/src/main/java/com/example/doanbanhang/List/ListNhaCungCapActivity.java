package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.Quanli.Quanlinhacungcap;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.NhaCungCapAdapter;
import com.example.doanbanhang.data.NhaCungCap;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ListNhaCungCapActivity extends AppCompatActivity implements NhaCungCapAdapter.Listener {
    NhaCungCapAdapter nhaCungCapAdapter;
    DBHelper dbHelper;
    ImageView imgadd;
    ArrayList<NhaCungCap> nhaCungCaps;
    RecyclerView recyclerViewNhacungcap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nha_cung_cap);
        dbHelper=new DBHelper(ListNhaCungCapActivity.this);
        recyclerViewNhacungcap=findViewById(R.id.RecyclerviewNhacungcap);
        nhaCungCaps=dbHelper.getallnhacungcap();
        nhaCungCapAdapter = new NhaCungCapAdapter(R.id.RecyclerviewProduct, ListNhaCungCapActivity.this, nhaCungCaps, ListNhaCungCapActivity.this); // Khởi tạo adapter
        recyclerViewNhacungcap.setAdapter(nhaCungCapAdapter);
        recyclerViewNhacungcap.setLayoutManager(new LinearLayoutManager(ListNhaCungCapActivity.this, LinearLayoutManager.VERTICAL, false));
        imgadd=findViewById(R.id.addnhacungcap);
        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListNhaCungCapActivity.this, Quanlinhacungcap.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, NhaCungCap NhaCungCap, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, NhaCungCap NhaCungCap, View view) {

    }
}