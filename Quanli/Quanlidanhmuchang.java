package com.example.doanbanhang.Quanli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doanbanhang.List.ListDanhMucActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.db.DBHelper;

import java.util.Random;

public class Quanlidanhmuchang extends AppCompatActivity {

    Button button;
    EditText namedanhmuc,tenID;
    String Snamedanhmuc,SnameID;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlidanhmuchang);
        button=findViewById(R.id.btnAdd);
        namedanhmuc=findViewById(R.id.edTenhang);
        tenID=findViewById(R.id.edID);
        dbHelper=new DBHelper(Quanlidanhmuchang.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snamedanhmuc = namedanhmuc.getText().toString();
                SnameID=tenID.getText().toString().trim();

                if (TextUtils.isEmpty(Snamedanhmuc))
                    Toast.makeText(Quanlidanhmuchang.this, "Cần điền đủ các thông tin ở trên", Toast.LENGTH_SHORT).show();
                else {

                    DanhMuc danhMuc = new DanhMuc(SnameID, Snamedanhmuc);
                    dbHelper.insertDanhmuc(danhMuc, SnameID, Quanlidanhmuchang.this);
                    Intent intent = new Intent(Quanlidanhmuchang.this, ListDanhMucActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}