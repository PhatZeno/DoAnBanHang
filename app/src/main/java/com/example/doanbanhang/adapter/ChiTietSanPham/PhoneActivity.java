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


public class PhoneActivity extends AppCompatActivity implements SanPhamAdapter.Listener {

    DBHelper dbHelper;
    RecyclerView RPhone;
    ArrayList<Sanpham> sanphams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        RPhone=findViewById(R.id.rAllPhone);
        dbHelper=new DBHelper(PhoneActivity.this);

        sanphams=dbHelper.getallPhone();
        sanphams.clear();
        sanphams=dbHelper.getallPhone();
        RPhone.setAdapter(new SanPhamAdapter(R.id.rAllPhone, this, sanphams, PhoneActivity.this));
        RPhone.setLayoutManager(new LinearLayoutManager(PhoneActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}