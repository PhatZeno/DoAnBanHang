package com.example.doanbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanbanhang.R;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.session.SessionManager;

public class LoginActivity extends AppCompatActivity {
    EditText inputemail, inputmatkhau;
    Button btndn;
    TextView createnewaccount;
    DBHelper dbHelper;
    SessionManager session;
    ImageView google,facebook,phone,twitter,github;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        createnewaccount=findViewById(R.id.createNewAccount);
        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        inputemail = findViewById(R.id.inputEmail);
        inputmatkhau = findViewById(R.id.inputPassword);
        btndn = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(LoginActivity.this);
        session = new SessionManager(getApplicationContext());
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        SharedPreferences sharedPref = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        boolean IsLoggedIn = sharedPref.getBoolean("IsLoggedIn", false);
        if (IsLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            String username = inputemail.getText().toString().trim();
            startActivity(intent);
        } else {
            btndn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Lay du lieu
                    String username = inputemail.getText().toString().trim();
                    String password = inputmatkhau.getText().toString().trim();
                    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkuserpass = dbHelper.checkusernamepassword(username, password);
                        if(checkuserpass==true) {
                            String name=dbHelper.searchname(username);
                            String role=dbHelper.searchrole(username);
                            Log.d("TAG", "onClick: "+role);
                            session.createLoginSession(username, password,name,role);
                            int a=dbHelper.searchID(username);
                            session.setUserID(a);
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            if(role.trim().equals("admin")){
                                Intent intent1 = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intent1);
                                finish();
                            }
                            else {
                                Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent2);
                                finish();
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });
        }
    }
}