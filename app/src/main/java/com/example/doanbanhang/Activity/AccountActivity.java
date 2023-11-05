package com.example.doanbanhang.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanbanhang.R;
import com.example.doanbanhang.adapter.ChiTietSanPham.HeadphoneActivity;
import com.example.doanbanhang.data.User;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.session.SessionManager;

public class AccountActivity extends AppCompatActivity {

    TextView textView_ac_name;

    SessionManager sessionManager;
    TextView ID,UserName,Password;
    Button logout;
    ImageView back;

    TextView lichsumuahang;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        DBHelper dbHelper=new DBHelper(AccountActivity.this);
        sessionManager=new SessionManager(AccountActivity.this);
        String name=sessionManager.getstring();
        textView_ac_name=findViewById(R.id.tv_account_name);
        ID=findViewById(R.id.Account_User_ID);
        sessionManager=new SessionManager(AccountActivity.this);
        int names=sessionManager.getUserID();
        String currentPassword = dbHelper.searchpassword(names);
        ID.setText("ID: "+String.valueOf(sessionManager.getUserID()));
        Password=findViewById(R.id.Account_User_Password);
        Password.setText("Password: "+ currentPassword);
        textView_ac_name.setText(name);
        UserName=findViewById(R.id.Account_User_UserName);
        UserName.setText("Email: "+dbHelper.searchusername(sessionManager.getUserID()));
        back=findViewById(R.id.img_back_ac);
        lichsumuahang=findViewById(R.id.Account_User_History);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logout=findViewById(R.id.btn_logOut);
        Log.d("TAG", "onCreate: "+1);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(AccountActivity.this, LoginActivity.class  );
                startActivity(intent);
                SessionManager sessionManager=new SessionManager(AccountActivity.this);
                sessionManager.logoutUser();
            }
        });

        Button changePasswordButton = findViewById(R.id.btn_changePassword);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_old_password, null);
                builder.setView(view);

                final EditText oldPassword = view.findViewById(R.id.edt_oldPassword);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldPasswordText = oldPassword.getText().toString();

                        // Check if the old password is correct
                        if (checkOldPassword(oldPasswordText)) {
                            // If the old password is correct, show the change password dialog
                            showChangePasswordDialog();
                        } else {
                            Toast.makeText(AccountActivity.this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


    }
    private boolean checkOldPassword(String oldPassword) {
        DBHelper dbHelper = new DBHelper(AccountActivity.this);
        sessionManager=new SessionManager(AccountActivity.this);
        int name=sessionManager.getUserID();
        String currentPassword = dbHelper.searchpassword(name);
        return oldPassword.equals(currentPassword);
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(view);

        final EditText newPassword = view.findViewById(R.id.edt_newPassword);
        final EditText rePassword = view.findViewById(R.id.edt_rePassword);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newPasswordText = newPassword.getText().toString();
                String rePasswordText = rePassword.getText().toString();
                SessionManager sessionManager = new SessionManager(AccountActivity.this);
                int userID = sessionManager.getUserID();
                if (!newPasswordText.equals(rePasswordText)) {
                    Toast.makeText(AccountActivity.this, "Mật khẩu mới và mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                } else {

                    User user=new User(userID,newPasswordText);
                    DBHelper dbHelper=new DBHelper(AccountActivity.this);
                    dbHelper.updatepassword(user);
                    Toast.makeText(AccountActivity.this, "Mật khẩu đã cập nhật thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();


        lichsumuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AccountActivity.this, HeadphoneActivity.class);
                startActivity(intent);
            }
        });
    }

}