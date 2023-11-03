package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private ImageView imageBACK,imageSP;
    private TextView tvName,tvDDSP,tvGia,tvDanhMuc,tvNhaCungCap,tvNgayTao;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        dbHelper=new DBHelper(ChiTietSanPhamActivity.this);

        imageBACK = findViewById(R.id.back);
        imageSP = findViewById(R.id.imageSp);

        tvName = findViewById(R.id.tvName);
        tvDDSP = findViewById(R.id.tvDDSP);
        tvGia = findViewById(R.id.tvGia);
        tvDanhMuc = findViewById(R.id.tvDanhmuc);
        tvNhaCungCap = findViewById(R.id.tvNhaCC);
        tvNgayTao = findViewById(R.id.tvNgayTao);

        Sanpham sanpham = dbHelper.getSP(getIntent().getIntExtra("id",-1));

        byte[] data = Base64.decode(sanpham.getImage(), Base64.DEFAULT);
        Bitmap bm;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        bm = BitmapFactory.decodeByteArray(data, 0, data.length, opt);

        imageSP.setImageBitmap(bm);
        tvName.setText("Tên sản phẩm : "+sanpham.getTensp());
        tvDDSP.setText("Tên DesSP : "+sanpham.getDessp());
        tvGia.setText("Giá bán : "+sanpham.getGia());
        tvNgayTao.setText("Ngày đăng : "+sanpham.getNgayTao());
        tvNhaCungCap.setText("Nhà cung cấp : "+sanpham.getNhacungcap());
        tvDanhMuc.setText("Danh mục : "+sanpham.getDanhmuc());

        imageBACK.setOnClickListener(view -> {
            finish();
        });
    }
}