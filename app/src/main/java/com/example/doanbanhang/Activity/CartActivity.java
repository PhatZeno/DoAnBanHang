package com.example.doanbanhang.Activity;

import android.content.Context;
import android.content.DialogInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.data.GioHang;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.session.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class CartActivity extends AppCompatActivity implements SanPhamAdapter.Listener{
    RecyclerView recyclerView;
    SanPhamAdapter adapter;
    Button btnCheckOut;
    DBHelper dbHelper;
    ImageView imgback;
    EditText diachi,ghichu;
    ArrayList<Sanpham> sanPhamList;
    TextView thanhtien;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        thanhtien=findViewById(R.id.thanhtien);
        diachi=findViewById(R.id.edDiachi);
        ghichu=findViewById(R.id.edGhichu);
        imgback=findViewById(R.id.imgback_cart);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dbHelper = new DBHelper(this);
        SessionManager sessionManager = new SessionManager(CartActivity.this);
         user_id = dbHelper.searchusername(sessionManager.getUserID());
        ArrayList<GioHang> gioHangList = dbHelper.getAllGioHang(user_id);
        sanPhamList = new ArrayList<>();
        for (GioHang gioHang : gioHangList) {
            Sanpham sanPham = dbHelper.getSanPham(gioHang.getIDsanpham());
            sanPhamList.add(sanPham);
        }

        recyclerView = findViewById(R.id.cart_rec);
        adapter = new SanPhamAdapter(R.id.cart_rec, CartActivity.this, sanPhamList, CartActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
        btnCheckOut=findViewById(R.id.checkOutButton);
        int tongTien = 0;
        for (Sanpham sp : sanPhamList) {
            tongTien += sp.getGia();
        }
        thanhtien.setText(String.valueOf(tongTien) + "VND");
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tongTien = 0;
                for (Sanpham sp : sanPhamList) {
                    tongTien += sp.getGia();
                }
                Random rand = new Random();
                int ID = rand.nextInt(999) + 1;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String ngaymua = formatter.format(new Date());
                String diachis=diachi.getText().toString();
                String ghichus=ghichu.getText().toString();
                DonHang donHang = new DonHang(ID, user_id, ngaymua, tongTien,diachis,ghichus);
                ArrayList<ChiTietDonHang> chiTietDonHangs = new ArrayList<>();
                for (Sanpham sanpham : sanPhamList) {
                    ChiTietDonHang chiTietDonHang = new ChiTietDonHang(ID, sanpham.getID(), 1, sanpham.getGia());
                    chiTietDonHangs.add(chiTietDonHang);
                }
                dbHelper.insertDonhang(donHang,ID,CartActivity.this);
                for (ChiTietDonHang chiTietDonHang : chiTietDonHangs) {
                    dbHelper.insertChitietDonhang(chiTietDonHang,CartActivity.this);
                }
                dbHelper.deleteGioHang(user_id);
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
                        dbHelper.deleteFromGioHang(user_id, sanpham.getID());
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbHelper = new DBHelper(this);
        SessionManager sessionManager = new SessionManager(CartActivity.this);
        String user_id = dbHelper.searchusername(sessionManager.getUserID());
        ArrayList<GioHang> gioHangList = dbHelper.getAllGioHang(user_id);
        ArrayList<Sanpham> sanPhamList = new ArrayList<>();
        for (GioHang gioHang : gioHangList) {
            Sanpham sanPham = dbHelper.getSanPham(gioHang.getIDsanpham());
            sanPhamList.add(sanPham);
        }
        // Now you can use sanPhamList to display the list of products in the cart
    }

}
