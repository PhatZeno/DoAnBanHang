package com.example.doanbanhang.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.doanbanhang.Activity.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LoginPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAMEUSER ="nameuser";
    public static final String KEY_ROLE="role";

    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public String getstring(){
        String value = pref.getString("nameuser", null);
        return value;
    }
    public String getsusername(){
        String value = pref.getString("username", null);
        return value;
    }
    public void createLoginSession(String username, String password,String nameuser,String role){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_NAMEUSER,nameuser);
        editor.putString(KEY_ROLE,role);
        editor.commit();
    }
    public void setUserID(int userID) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("userID", userID);
        editor.apply();
    }
    public int getUserID() {
        return pref.getInt("userID", -1); // -1 là giá trị mặc định nếu không tìm thấy "userID"
    }
    public  String getRole(){
        String role=pref.getString("role",null);
        return role;
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_NAMEUSER, pref.getString(KEY_NAMEUSER, null));
        return user;
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser(){
        editor.clear();
        context.deleteFile("LoginPref");
        editor.commit();
        editor.apply();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
