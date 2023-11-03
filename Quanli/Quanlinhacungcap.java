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
import com.example.doanbanhang.List.ListNhaCungCapActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.data.NhaCungCap;
import com.example.doanbanhang.db.DBHelper;

import java.util.Random;

public class Quanlinhacungcap extends AppCompatActivity {


    Button button;
    EditText namenhacungcap,desnhacungcap,diachinhacungcap,IDnhacungcap;
    String Snamenhacungcap,Sdesnhacungcap,Sdiachinhacungcap,SIDnhacungcap;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlinhacungcap);
        button=findViewById(R.id.btnAdd);
        namenhacungcap=findViewById(R.id.edTennhacungcap);
        desnhacungcap=findViewById(R.id.edDesnhacungcap);
        diachinhacungcap=findViewById(R.id.edDiachinhacungcap);
        IDnhacungcap=findViewById(R.id.edIDNhacungcap);
        dbHelper=new DBHelper(Quanlinhacungcap.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snamenhacungcap = namenhacungcap.getText().toString();
                Sdesnhacungcap = desnhacungcap.getText().toString();
                Sdiachinhacungcap = diachinhacungcap.getText().toString();
                SIDnhacungcap=IDnhacungcap.getText().toString().trim();
                if (TextUtils.isEmpty(Snamenhacungcap)||TextUtils.isEmpty(Sdesnhacungcap)||TextUtils.isEmpty(Sdiachinhacungcap))
                    Toast.makeText(Quanlinhacungcap.this, "Cần điền đủ các thông tin ở trên", Toast.LENGTH_SHORT).show();
                else {

                    NhaCungCap nhaCungCap = new NhaCungCap(SIDnhacungcap, Snamenhacungcap,Sdesnhacungcap,Sdiachinhacungcap);
                    dbHelper.insertNhacungcap(nhaCungCap, SIDnhacungcap, Quanlinhacungcap.this);
                    Intent intent = new Intent(Quanlinhacungcap.this, ListNhaCungCapActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}