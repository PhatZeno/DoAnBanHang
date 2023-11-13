package com.example.doanbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanbanhang.R;
import com.example.doanbanhang.data.GioHang;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.session.SessionManager;

public class DetailActivity2 extends AppCompatActivity {

    TextView Name,Price,Des;
    ImageView imageView;
    Button buybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        Name=findViewById(R.id.titleTxt);
        Price=findViewById(R.id.PriceTxt);
        Des=findViewById(R.id.descriptionTxt);
        Intent intent=getIntent();
        Sanpham sanpham= (Sanpham) intent.getSerializableExtra("Sanpham");
        Name.setText(sanpham.getTensp());
        Price.setText(String.valueOf(sanpham.getGia())+" VND");
        Des.setText(sanpham.getDessp());
        imageView=findViewById(R.id.detail_img_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(DetailActivity2.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        buybutton=findViewById(R.id.addToCartBtn);
        buybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager=new SessionManager(DetailActivity2.this);
                DBHelper dbHelper=new DBHelper(DetailActivity2.this);
                String user_id=dbHelper.searchusername(sessionManager.getUserID());
                GioHang giohang = new GioHang(user_id,sanpham.getID(),1);
                dbHelper.insertGioHang(giohang, user_id, sanpham.getID(), DetailActivity2.this);
                Intent intent1=new Intent(DetailActivity2.this,CartActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}