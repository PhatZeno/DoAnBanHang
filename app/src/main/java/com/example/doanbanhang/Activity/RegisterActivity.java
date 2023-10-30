package com.example.doanbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanbanhang.R;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.data.User;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    TextView alreadyHaveaccount;
    EditText Email,Password,Confirmpass,Name;
    String email,password,repass,name;
    Button Register;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        dbHelper=new DBHelper(RegisterActivity.this);
        Email=findViewById(R.id.inputReEmail);
        Name=findViewById(R.id.nameuser);
        Password=findViewById(R.id.inputRePassword);
        Confirmpass=findViewById(R.id.inputReConfirmPassword);
        alreadyHaveaccount=findViewById(R.id.alreadyHaveaccount);
        alreadyHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        Register=findViewById(R.id.btnRegister);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=Name.getText().toString();
                email=Email.getText().toString();
                password=Password.getText().toString();
                repass=Confirmpass.getText().toString();
                Random rand = new Random();
                int ID = rand.nextInt(999) + 1;
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repass))
                    Toast.makeText(RegisterActivity.this, "Cần điền đủ các thông tin ở trên", Toast.LENGTH_SHORT).show();
                else {
                    if(password.equals(repass)){
                        if(dbHelper.checkusername(email)){
                            User user1=new User(ID,email,password,name,"user");
                            dbHelper.insertuser(user1,email,RegisterActivity.this);
                            Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu xác nhận không trùng khớp", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

}