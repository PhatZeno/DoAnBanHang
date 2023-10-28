package com.example.doanbanhang.db;

import android.app.Application;

public class MusicDB extends Application { // Singleton Pattern
    DBHelper dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper=new DBHelper(this);
        dbHelper.CopyDatabase();
    }
}
