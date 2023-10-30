package com.example.doanbanhang.Activity.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.doanbanhang.Activity.MainActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class PCActivity extends AppCompatActivity implements SanPhamAdapter.Listener {

    DBHelper dbHelper;
    RecyclerView RPC;
    ArrayList<Sanpham> sanphams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcactivity);
        RPC=findViewById(R.id.rAllPC);
        dbHelper=new DBHelper(PCActivity.this);

        sanphams=dbHelper.getallPC();
        sanphams.clear();
        sanphams=dbHelper.getallPC();
        RPC.setAdapter(new SanPhamAdapter(R.id.rAllPC, this, sanphams, PCActivity.this));
        RPC.setLayoutManager(new LinearLayoutManager(PCActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}