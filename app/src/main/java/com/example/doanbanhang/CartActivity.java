package com.example.doanbanhang;

import android.content.Context;
import android.content.DialogInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.session.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class CartActivity extends AppCompatActivity implements SanPhamAdapter.Listener{
    ImageView imgback;
    TextView thanhtien;
    RecyclerView recyclerView;
    SanPhamAdapter adapter;
    Button btnCheckOut;
    DBHelper dbHelper;
    SharedPreferences sharedPref;
    Gson gson;
    ArrayList<Sanpham> Arraysp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper=new DBHelper(CartActivity.this);
        setContentView(R.layout.activity_cart);
        imgback=findViewById(R.id.imageView10);
        thanhtien=findViewById(R.id.thanhtien);
        btnCheckOut=findViewById(R.id.checkOutButton);
       SessionManager sessionManager=new SessionManager(CartActivity.this);
       String maKhachHang=sessionManager.getsusername();
        sharedPref = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        gson = new Gson();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        sharedPref = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        gson = new Gson();
        String json = sharedPref.getString("sanpham_list", "");
        Type type = new TypeToken<ArrayList<Sanpham>>() {}.getType();
        Arraysp = gson.fromJson(json, type);

        if (Arraysp == null || Arraysp.isEmpty()) {
            Arraysp = getIntent().getParcelableArrayListExtra("sanpham_list");
        } else {
            ArrayList<Sanpham> extrasList = getIntent().getParcelableArrayListExtra("sanpham_list");
            if (extrasList != null) {
                for (Sanpham sanpham : extrasList) {
                    if (!Arraysp.contains(sanpham)) {
                        Arraysp.add(sanpham);
                    }
                }
            }
        }
        recyclerView = findViewById(R.id.cart_rec);
        adapter = new SanPhamAdapter(R.id.cart_rec, CartActivity.this, Arraysp, CartActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tongTien = 0;
                for (Sanpham sp : Arraysp) {
                    tongTien += sp.getGia();
                }
                Random rand = new Random();
                int ID = rand.nextInt(999) + 1;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String ngaymua = formatter.format(new Date());
                DonHang donHang = new DonHang(ID, maKhachHang, ngaymua, tongTien);
                ArrayList<ChiTietDonHang> chiTietDonHangs = new ArrayList<>();
                for (Sanpham sanpham : Arraysp) {
                    ChiTietDonHang chiTietDonHang = new ChiTietDonHang(ID, sanpham.getID(), 1, sanpham.getGia());
                    chiTietDonHangs.add(chiTietDonHang);
                }
                dbHelper.insertDonhang(donHang,ID,CartActivity.this);
                for (ChiTietDonHang chiTietDonHang : chiTietDonHangs) {
                    dbHelper.insertChitietDonhang(chiTietDonHang,CartActivity.this);
                }
            }
        });

    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {
    }
    @Override
    public void onItemLongClickListener4(int recyclerViewId, final Sanpham sanpham, View view) {
        new AlertDialog.Builder(CartActivity.this)
                .setTitle("Xóa sản phẩm")
                .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Arraysp.remove(sanpham);
                        adapter.notifyDataSetChanged();
                        int total = 0;
                        for (Sanpham sp : Arraysp) {
                            total += sp.getGia();
                        }
                        thanhtien.setText(String.valueOf(total));
                        String json = gson.toJson(Arraysp);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("sanpham_list", json);
                        editor.apply();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Sanpham sanpham = (Sanpham) obj;
//        return tenSanPham.equals(sanpham.tenSanPham);
//    }

}
