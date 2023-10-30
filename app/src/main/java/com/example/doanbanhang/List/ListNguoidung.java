package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doanbanhang.Activity.AdminActivity;
import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.UserAdapter;
import com.example.doanbanhang.data.User;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ListNguoidung extends AppCompatActivity implements UserAdapter.Listener{

    UserAdapter userAdapter;
    DBHelper dbHelper;
    ImageView back;
    RecyclerView ruser;
    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoidung);
        ruser=findViewById(R.id.RecyclerviewUser);
        dbHelper=new DBHelper(ListNguoidung.this);
        users=dbHelper.getalluser();
        back=findViewById(R.id.back_sp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListNguoidung.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        userAdapter = new UserAdapter(R.id.RecyclerviewUser, ListNguoidung.this, users, ListNguoidung.this); // Khởi tạo adapter
        ruser.setAdapter(userAdapter);
        ruser.setLayoutManager(new LinearLayoutManager(ListNguoidung.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onItemClickListener4(int recyclerViewId, User User, int size) {

    }

    @Override
    public void onItemLongClickListener4(int recyclerViewId, User User, View view) {

    }
}