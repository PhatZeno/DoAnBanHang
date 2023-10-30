package com.example.doanbanhang.adapter.ChiTietSanPham;

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


public class HeadphoneActivity extends AppCompatActivity implements SanPhamAdapter.Listener {

    DBHelper dbHelper;
    SanPhamAdapter sanPhamAdapter;
    RecyclerView RHeadphone;
    ArrayList<Sanpham> sanphams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headphone);
        RHeadphone=findViewById(R.id.rAllheadphone);
        dbHelper=new DBHelper(HeadphoneActivity.this);

        sanphams=dbHelper.getallHP();
        sanphams.clear();
        sanphams=dbHelper.getallHP();
        RHeadphone.setAdapter(new SanPhamAdapter(R.id.rAllheadphone, this, sanphams, HeadphoneActivity.this));
        RHeadphone.setLayoutManager(new LinearLayoutManager(HeadphoneActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}