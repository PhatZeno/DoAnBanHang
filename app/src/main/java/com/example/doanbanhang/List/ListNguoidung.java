package com.example.doanbanhang.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.UserAdapter;
import com.example.doanbanhang.data.User;
import com.example.doanbanhang.db.DBHelper;

import java.util.ArrayList;

public class ListNguoidung extends AppCompatActivity implements UserAdapter.Listener{

    UserAdapter userAdapter;
    DBHelper dbHelper;
    RecyclerView ruser;
    ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoidung);
        ruser=findViewById(R.id.RecyclerviewUser);
        dbHelper=new DBHelper(ListNguoidung.this);
        users=dbHelper.getalluser();
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