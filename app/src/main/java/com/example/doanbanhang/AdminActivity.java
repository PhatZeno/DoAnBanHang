package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanbanhang.List.ListDanhMucActivity;
import com.example.doanbanhang.List.ListDonHang;
import com.example.doanbanhang.List.ListNguoidung;
import com.example.doanbanhang.List.ListNhaCungCapActivity;
import com.example.doanbanhang.List.ListSanPham;
import com.example.doanbanhang.Quanli.QuanlidoanhthuActivity;
import com.example.doanbanhang.Quanli.Quanlidonhang;
import com.example.doanbanhang.Quanli.Quanlinhacungcap;

public class AdminActivity extends AppCompatActivity {
    TextView textsp,nguoidung;
    ImageView cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        cart=findViewById(R.id.img_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView ivdonhang=findViewById(R.id.ivquanlidonhang);
        ivdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ListDonHang.class);
                startActivity(intent);
            }
        });


        ImageView ivnguoidung=findViewById(R.id.ivquanlinguoidung);
        ivnguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ListNguoidung.class);
                startActivity(intent);
            }
        });

        ImageView ivdoanhthu=findViewById(R.id.ivquanlidoanhthu);
        ivdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, QuanlidoanhthuActivity.class);
                startActivity(intent);
            }
        });

        ImageView ivdoanhmuchang=findViewById(R.id.ivquanlidanhmuchang);
        ivdoanhmuchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ListDanhMucActivity.class);
                startActivity(intent);
            }
        });

        ImageView ivsanpham=findViewById(R.id.ivquanlisanpham);
        ivsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ListSanPham.class);
                startActivity(intent);
            }
        });

        ImageView ivnhacungcap=findViewById(R.id.ivquanlinhacungcap);
        ivnhacungcap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ListNhaCungCapActivity.class);
                startActivity(intent);
            }
        });

    }
}