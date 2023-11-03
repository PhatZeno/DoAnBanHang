package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanbanhang.AddSpActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.SanPhamAdapter;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.db.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListSanPham extends AppCompatActivity implements SanPhamAdapter.Listener{
    private boolean flag = false;
    private boolean flagdate = false;

    private ImageView imgSearch;

    private TextView tvDatHang,tvGiaoHang;


    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    SanPhamAdapter sanPhamAdapter;
    DBHelper dbHelper;
    RecyclerView rsp;
    ArrayList<Sanpham> sanphams;
    ImageView addsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);
        rsp=findViewById(R.id.RecyclerviewProduct);
        dbHelper=new DBHelper(ListSanPham.this);
        sanphams=dbHelper.getallSp();
        sanPhamAdapter = new SanPhamAdapter(R.id.RecyclerviewProduct, ListSanPham.this, sanphams, ListSanPham.this); // Khởi tạo adapter
        rsp.setAdapter(sanPhamAdapter);
        rsp.setLayoutManager(new LinearLayoutManager(ListSanPham.this, LinearLayoutManager.VERTICAL, false));
        addsp=findViewById(R.id.imgaddsp);
        addsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListSanPham.this, AddSpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvDatHang = findViewById(R.id.dateDatHang);
        tvDatHang.setOnClickListener(view -> {
            flagdate = false;
            setDate(view);
        });
        tvGiaoHang = findViewById(R.id.dateGiaHang);

        tvGiaoHang.setOnClickListener(view -> {
            flagdate = true;
            setDate(view);
        });

        imgSearch = findViewById(R.id.search);
        imgSearch.setOnClickListener(view -> {

            String dateDat = tvDatHang.getText().toString();
            String dateGiao = tvGiaoHang.getText().toString();
            long dateDa =converDatetoMilisecond(dateDat);
            long dateGiaos = converDatetoMilisecond(dateGiao);

            if(dateGiaos < dateDa) {
                Toast.makeText(getApplicationContext(),"Ngày kết thúc không hợp lệ",Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<Sanpham> tmp = new ArrayList<>();
            for (Sanpham sanpham : sanphams) {
                long ngaytao = converDatetoMilisecond(sanpham.getNgayTao());
                if (ngaytao >= dateDa && ngaytao <= dateGiaos) {
                   tmp.add(sanpham);
                }
            }

            sanPhamAdapter = new SanPhamAdapter(R.id.RecyclerviewProduct, ListSanPham.this, tmp, ListSanPham.this); // Khởi tạo adapter
            rsp.setAdapter(sanPhamAdapter);
        });
    }

    public void setDate(View view) {
        showDialog(1000);
    }

    public static long converDatetoMilisecond(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long millis = date.getTime();
        return millis;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 1000) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {

                    Date date = new Date();
                    date.setYear(arg1-1900);
                    date.setMonth(arg2);
                    date.setDate(arg3);

                    SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
                    String stringDate= DateFor.format(date);

                    if(flagdate) {
                        tvGiaoHang.setText(stringDate);
                    } else {
                        tvDatHang.setText(stringDate);
                    }
                }
            };
    @Override
    protected void onResume() {
        super.onResume();
        sanphams=dbHelper.getallSp();
        sanPhamAdapter = new SanPhamAdapter(R.id.RecyclerviewProduct, ListSanPham.this, sanphams, ListSanPham.this); // Khở
        rsp.setAdapter(sanPhamAdapter);

    }

    @Override
    public void onItemClickListener4(int recyclerViewId, Sanpham sanpham, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, Sanpham sanpham, View view) {

    }
}