package com.example.doanbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.doanbanhang.adapter.ChiTietSanPham.AllProcActivity;
import com.example.doanbanhang.adapter.ChiTietSanPham.HeadphoneActivity;
import com.example.doanbanhang.adapter.ChiTietSanPham.LaptopActivity;
import com.example.doanbanhang.adapter.ChiTietSanPham.PCActivity;
import com.example.doanbanhang.adapter.ChiTietSanPham.PhoneActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.SearchProcActivity;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.session.SessionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SanPhamAdapter.Listener{
    ImageView imageView10,imageView,PC,Phone,Headphone,Laptop,all;
    RecyclerView recyclerView;
    TextView greeting;
    ImageView imageViews;
    ArrayList<Sanpham> sanphams;
    SanPhamAdapter sanPhamAdapter;
    DBHelper dbHelper;
    EditText search;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView10=findViewById(R.id.imggiohang);
        PC=findViewById(R.id.img_pc);
        Phone = findViewById(R.id.img_phone);
        Headphone=findViewById(R.id.img_headphone);
        Laptop=findViewById(R.id.img_laptop);
        Laptop=findViewById(R.id.img_laptop);
        all=findViewById(R.id.img_allsp);
        // Trong Activity A
        EditText editTextA = findViewById(R.id.editTextTextPersonName);
        editTextA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchProcActivity.class);
                startActivity(intent);
            }
        });
        imageViews=findViewById(R.id.personal);
        imageViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AllProcActivity.class);
                startActivity(intent);
            }
        });
        Laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, LaptopActivity.class);
                startActivity(intent);
            }
        });
        Headphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, HeadphoneActivity.class);
                startActivity(intent);
            }
        });
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, PhoneActivity.class);
                startActivity(intent);
            }
        });
        PC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, PCActivity.class);
                startActivity(intent);
            }
        });
        dbHelper=new DBHelper(MainActivity.this);
        recyclerView=findViewById(R.id.view1);
        sessionManager=new SessionManager(MainActivity.this);
        greeting=findViewById(R.id.textchao);
        String name=sessionManager.getstring();
        greeting.setText("Xin Chào"+" "+name);
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        sanphams=dbHelper.getallSp();
        sanphams.clear();
        sanphams=dbHelper.getallSp();
        recyclerView.setAdapter(new SanPhamAdapter(R.id.view1, this, sanphams, MainActivity.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        String isAdmin=sessionManager.getRole();
        imageView= findViewById(R.id.person);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                // Kiểm tra vai trò của người dùng
                if (isAdmin.trim().equals("admin")) {
                    popup.getMenuInflater().inflate(R.menu.admin_logout, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.btnAdmin:
                                    Intent intent =new Intent(MainActivity.this, AdminActivity.class  );
                                    startActivity(intent);
                                    return true;
                                case R.id.btnAdLogout:
                                    Intent intent2 =new Intent(MainActivity.this, LoginActivity.class  );
                                    startActivity(intent2);
                                    SessionManager sessionManager=new SessionManager(MainActivity.this);
                                    sessionManager.logoutUser();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.show();
                } else {
                    popup.getMenuInflater().inflate(R.menu.log_out, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.btnLogout:
                                    Intent intent3 =new Intent(MainActivity.this, LoginActivity.class  );
                                    startActivity(intent3);
                                    SessionManager sessionManager=new SessionManager(MainActivity.this);
                                    sessionManager.logoutUser();
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popup.show();
                }
            }
        });
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {
        ArrayList<Sanpham> Arraysp = new ArrayList<>();

        for (Sanpham sanpham1 : sanphams) {
            Arraysp.add(sanpham1);
        }
        Intent intent = new Intent(this, CartActivity.class);
        intent.putParcelableArrayListExtra("sanpham_list", Arraysp);
        startActivity(intent);
    }


    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}