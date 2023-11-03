package com.example.doanbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doanbanhang.List.ListSanPham;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.data.NhaCungCap;
import com.example.doanbanhang.db.DBHelper;
import com.example.doanbanhang.data.Sanpham;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
public class AddSpActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 11111;
    // Khai báo các biến
    EditText namesp, dessp, gia;
    Spinner danhmuc,nhacungcap;
    String Sdanhmuc, Sdessp, Snamesp, Sgia,Snhacungcap;

    String base64Image;

    ImageView imageSp;
    int Igia;
    DBHelper dbHelper;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sp);

        // Khởi tạo các view
        namesp = findViewById(R.id.tensp);
        dessp = findViewById(R.id.dessp);
        gia = findViewById(R.id.gia);
        danhmuc = findViewById(R.id.spinner); // Đổi từ EditText sang Spinner
        nhacungcap=findViewById(R.id.spinnernhacungcap);
        button = findViewById(R.id.buttonsave);
        imageSp = findViewById(R.id.imageSp);

        // Khởi tạo DBHelper
        dbHelper = new DBHelper(this);

// Lấy dữ liệu từ cơ sở dữ liệu
        ArrayList<DanhMuc> danhMucList = dbHelper.getalldanhmuc();
        ArrayList<NhaCungCap> nhaCungCapsList=dbHelper.getallnhacungcap();

// Tạo một ArrayList mới chỉ chứa tên của các DanhMuc
        ArrayList<String> danhMucNames = new ArrayList<>();
        for (DanhMuc dm : danhMucList) {
            danhMucNames.add(dm.getTenDanhmuc()); // Giả sử bạn có phương thức getName() trong lớp DanhMuc
        }
        ArrayList<String> nhCungCapNames = new ArrayList<>();
        for (NhaCungCap nhaCungCap : nhaCungCapsList) {
            nhCungCapNames.add(nhaCungCap.getTen()); // Giả sử bạn có phương thức getName() trong lớp DanhMuc
        }

// Tạo ArrayAdapter sử dụng mảng chuỗi và layout spinner mặc định
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, danhMucNames);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nhCungCapNames);

// Chỉ định layout được sử dụng khi hiển thị danh sách các lựa chọn
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Áp dụng adapter cho spinner
        danhmuc.setAdapter(adapter);
        nhacungcap.setAdapter(adapter2);

// Lấy giá trị từ Spinner
        danhmuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sdanhmuc = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        imageSp.setOnClickListener(view ->{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        });


        nhacungcap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Snhacungcap = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snamesp = namesp.getText().toString();
                Sdessp = dessp.getText().toString();
                Sgia = gia.getText().toString();

                if (TextUtils.isEmpty(Snamesp) || TextUtils.isEmpty(Sdessp) || TextUtils.isEmpty(Sdanhmuc) || TextUtils.isEmpty(Sgia)|| TextUtils.isEmpty(Snhacungcap))
                    Toast.makeText(AddSpActivity.this, "Cần điền đủ các thông tin ở trên", Toast.LENGTH_SHORT).show();
                else if (!isNumeric(Sgia))
                    Toast.makeText(AddSpActivity.this, "Giá trị đầu vào phải là số", Toast.LENGTH_SHORT).show();
                else {
                    Igia = Integer.parseInt(Sgia);
                    Random rand = new Random();
                    int ID = rand.nextInt(999) + 1;
                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    Sanpham sanpham = new Sanpham(ID, Snamesp, Sdessp, Igia, Sdanhmuc,Snhacungcap,date,base64Image);
                    dbHelper.insertSanpham(sanpham, ID, AddSpActivity.this);
                    Intent intent = new Intent(AddSpActivity.this, ListSanPham.class);
                    finish();
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                InputStream inputStream = AddSpActivity.this.getContentResolver().openInputStream(data.getData());

                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos); // Could be Bitmap.CompressFormat.PNG or Bitmap.CompressFormat.WEBP
                byte[] bai = baos.toByteArray();

                base64Image = Base64.encodeToString(bai, Base64.DEFAULT);

                imageSp.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}