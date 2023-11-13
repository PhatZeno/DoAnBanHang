package com.example.doanbanhang.adapter.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.R;

public class ChitietbannerActivity extends AppCompatActivity {

    ImageView image1,image2,img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietbanner);
        image1=findViewById(R.id.anh1);
        image2=findViewById(R.id.anh2);
        img3=findViewById(R.id.anh3);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChitietbannerActivity.this,PhoneActivity.class);
                startActivity(intent);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChitietbannerActivity.this,PhoneActivity.class);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChitietbannerActivity.this,PhoneActivity.class);
                startActivity(intent);
            }
        });
    }
}