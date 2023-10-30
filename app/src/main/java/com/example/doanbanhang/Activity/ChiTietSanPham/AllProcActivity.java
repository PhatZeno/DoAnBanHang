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


public class AllProcActivity extends AppCompatActivity implements SanPhamAdapter.Listener {

    DBHelper dbHelper;
    SanPhamAdapter sanPhamAdapter;
    RecyclerView RAllProc;
    ArrayList<Sanpham> sanphams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_proc);
        RAllProc=findViewById(R.id.rAllAllProc);
        dbHelper=new DBHelper(AllProcActivity.this);

        sanphams=dbHelper.getallSp();
        sanphams.clear();
        sanphams=dbHelper.getallSp();
        RAllProc.setAdapter(new SanPhamAdapter(R.id.rAllAllProc, this, sanphams, AllProcActivity.this));
        RAllProc.setLayoutManager(new LinearLayoutManager(AllProcActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}