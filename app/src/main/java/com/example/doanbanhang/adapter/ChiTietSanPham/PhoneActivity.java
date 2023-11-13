package com.example.doanbanhang.adapter.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.Activity.DetailActivity2;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.io.Serializable;
import java.util.ArrayList;


public class PhoneActivity extends AppCompatActivity implements SanPhamAdapter.Listener {

    DBHelper dbHelper;
    RecyclerView RPhone;
    ArrayList<Sanpham> sanphams;
    ImageView imageView;
    int co;
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
        imageView=findViewById(R.id.sortphone);
        co=0;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(co==0){
                    sanphams.clear();
                    sanphams=dbHelper.getallPhonesort();
                    RPhone.setAdapter(new SanPhamAdapter(R.id.rAllPC, PhoneActivity.this, sanphams, PhoneActivity.this));
                    RPhone.setLayoutManager(new LinearLayoutManager(PhoneActivity.this, LinearLayoutManager.VERTICAL, false));
                    co=1;
                }
                else {
                    sanphams.clear();
                    sanphams=dbHelper.getallPhonesort2();
                    RPhone.setAdapter(new SanPhamAdapter(R.id.rAllPC, PhoneActivity.this, sanphams, PhoneActivity.this));
                    RPhone.setLayoutManager(new LinearLayoutManager(PhoneActivity.this, LinearLayoutManager.VERTICAL, false));
                    co=0;
                }
            }
        });
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {
        Intent intent = new Intent(this, DetailActivity2.class);
        intent.putExtra("Sanpham", (Serializable) sanpham);
        startActivity(intent);
    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}