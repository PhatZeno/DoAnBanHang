package com.example.doanbanhang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Bitmap;
import android.widget.Toast;


import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.data.NhaCungCap;
import com.example.doanbanhang.data.Sanpham;
import com.example.doanbanhang.data.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper {
    String DB_NAME="MusicDB.db";
    Context context;
    SQLiteDatabase db;

    public DBHelper(Context context) {
        this.context = context;
    }
    SQLiteDatabase openDB(){
        return context.openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE,null);
    }
    public void CopyDatabase(){
        File dbFile=context.getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            try {
                InputStream inputStream=context.getAssets().open(DB_NAME);
                OutputStream op=new FileOutputStream(dbFile);
                byte[] bytes=new byte[1024];
                while (inputStream.read(bytes)>0){
                    op.write(bytes);
                }
                op.flush();
                op.close();
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public long insertuser(User user, String email, Context context){
        db=openDB();
        String querry="SELECT * FROM User WHERE USERNAME=?";
        Cursor cursor=db.rawQuery(querry,new String[]{email});
        if(cursor.getCount() <= 0){
            ContentValues contentValues=new ContentValues();
            contentValues.put("USERNAME",user.getUsername());
            contentValues.put("PASSWORD",user.getPassword());
            contentValues.put("NAMEUSER",user.getName());
            contentValues.put("ROLE",user.getRole());
            long tmp=db.insert("User","",contentValues);
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
    }

    public Boolean checkusernamepassword(String username, String password){
        db=openDB();
        String querry="SELECT * FROM User WHERE USERNAME=? and PASSWORD=?";
        Cursor cursor=db.rawQuery(querry,new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusername(String username) {
        db = openDB();
        String querry = "SELECT * FROM User WHERE USERNAME=?";
        Cursor cursor = db.rawQuery(querry, new String[]{username});
        if (cursor.getCount() <= 0)
            return true;
        else
            return false;
    }
    public String searchrole(String newQuery) {
        SQLiteDatabase db = openDB();
        String role = null;
        String sql = "SELECT ROLE FROM User WHERE USERNAME = '" + newQuery + "'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            role= cursor.getString(0);
        }
        db.close();
        return role;
    }
    public String searchname(String newQuery){
        String name = null;
        db = openDB();
        String sql = "SELECT NAMEUSER FROM User WHERE USERNAME like '%" + newQuery +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            name= cursor.getString(0);
        }
        db.close();
        return name;
    }
    public ArrayList<Sanpham> getallSp(){
        ArrayList<Sanpham> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM Sanpham";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            int gia=cursor.getInt(3);
            String danhmuc=cursor.getString(4);
            String ncc=cursor.getString(5);
            String ngaytao=cursor.getString(6);
            String bitmap = cursor.getString(7);
            Sanpham sanpham=new Sanpham(id,name,des,gia,danhmuc,ncc,ngaytao,bitmap);
            tam.add(sanpham);
        }


        cursor.close();
        return tam;
    }

    public ArrayList<Sanpham> getallSp(String search){
        ArrayList<Sanpham> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM Sanpham WHERE TENSP like '%" + search +"%'";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            int gia=cursor.getInt(3);
            String danhmuc=cursor.getString(4);
            String ncc=cursor.getString(5);
            String ngaytao=cursor.getString(6);
            String bitmap = cursor.getString(7);
            Sanpham sanpham=new Sanpham(id,name,des,gia,danhmuc,ncc,ngaytao,bitmap);
            tam.add(sanpham);
        }


        cursor.close();
        return tam;
    }

    public Sanpham getSP(int ids){
        db=openDB();
        String sql="SELECT * FROM Sanpham where ID ="+ ids;
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            int gia=cursor.getInt(3);
            String danhmuc=cursor.getString(4);
            String ncc=cursor.getString(5);
            String ngaytao=cursor.getString(6);
            String bitmap = cursor.getString(7);
            cursor.close();
            return new Sanpham(id,name,des,gia,danhmuc,ncc,ngaytao,bitmap);
        }


        return null;
    }

    public long insertSanpham(Sanpham sanpham, int ID, Context context){
        db = openDB();
        String query = "SELECT * FROM Sanpham WHERE ID=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(ID)});//
        if(cursor.getCount() <= 0){
            // Insert new value into database
            ContentValues contentValues = new ContentValues();
            contentValues.put("TENSP", sanpham.getTensp());
            contentValues.put("DESSP", sanpham.getDessp());
            contentValues.put("GIASP", sanpham.getGia());
            contentValues.put("DANHMUC", sanpham.getDanhmuc());
            contentValues.put("NGAYTAO", sanpham.getNgayTao());
            contentValues.put("IMAGE", sanpham.getImage());
            long tmp = db.insert("Sanpham","",contentValues);
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Sản phẩm đã được thêm từ trước", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
    }
    public long insertDanhmuc(DanhMuc danhMuc, String ID, Context context){
        db = openDB();
        String query = "SELECT * FROM Danhmuc WHERE ID=?";
        Cursor cursor = db.rawQuery(query, new String[]{ID});//
        if(cursor.getCount() <= 0){
            // Insert new value into database
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", ID);
            contentValues.put("TenDanhmuc", danhMuc.getTenDanhmuc());
            long tmp = db.insert("Danhmuc","",contentValues);
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Danh mục đã được thêm từ trước", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
    }

    public long insertNhacungcap(NhaCungCap nhaCungCap, String ID, Context context){
        db = openDB();
        String query = "SELECT * FROM Nhacungcap WHERE ID=?";
        Cursor cursor = db.rawQuery(query, new String[]{ID});//
        if(cursor.getCount() <= 0){
            // Insert new value into database
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID", ID);
            contentValues.put("Ten", nhaCungCap.getTen());
            contentValues.put("Des", nhaCungCap.getDes());
            contentValues.put("Diachi", nhaCungCap.getDiachi());
            long tmp = db.insert("Nhacungcap","",contentValues);
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Nhà cung cấp đã được thêm từ trước", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
    }

    public ArrayList<NhaCungCap> getallnhacungcap(){
        ArrayList<NhaCungCap> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM Nhacungcap";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            String diachi=cursor.getString(3);
            NhaCungCap nhaCungCap=new NhaCungCap(id,name,des,diachi);
            tam.add(nhaCungCap);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<DonHang> getalldonhang(){
        ArrayList<DonHang> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM DonHang";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String makhachhang= cursor.getString(1);
            String ngaymua=cursor.getString(2);
            double tongtien=cursor.getDouble(3);
            DonHang donHang=new DonHang(id,makhachhang,ngaymua,tongtien);
            tam.add(donHang);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<DanhMuc> getalldanhmuc(){
        ArrayList<DanhMuc> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM Danhmuc ";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String name= cursor.getString(1);
            DanhMuc danhMuc=new DanhMuc(id,name);
            tam.add(danhMuc);
        }
        cursor.close();
        return tam;
    }

    public ArrayList<ChiTietDonHang> getChiTietDonHang(int maDonHang) {
        ArrayList<ChiTietDonHang> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM ChiTietDonHang WHERE MaDonHang = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maDonHang)});
        while (cursor.moveToNext()) {
            int madonhang = cursor.getInt(0);
            int masanpham = cursor.getInt(1);
            int soluong = cursor.getInt(2);
            double giatien = cursor.getDouble(3);
            ChiTietDonHang chiTietDonHang = new ChiTietDonHang(madonhang, masanpham, soluong, giatien);
            tam.add(chiTietDonHang);
        }
        cursor.close();
        return tam;
    }


    public ArrayList<User> getalluser(){
        ArrayList<User> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM User";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String Username=cursor.getString(1);
            String Password=cursor.getString(2);
            String name= cursor.getString(3);
            String role=cursor.getString(4);
            User user=new User(id,Username,Password,name,role);
            tam.add(user);
        }
        cursor.close();
        return tam;
    }

    public long insertDonhang(DonHang donHang, int MaDonHang, Context context){
        db = openDB();
        String query = "SELECT * FROM DonHang WHERE MaDonHang=?";
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(MaDonHang)});//
        if(cursor.getCount() <= 0){
            // Insert new value into database
            ContentValues contentValues = new ContentValues();
            contentValues.put("MaDonHang", donHang.getMaDonHang());
            contentValues.put("MaKhachHang", donHang.getMaKhachHang());
            contentValues.put("NgayMua", donHang.getNgayMua());
            contentValues.put("TongTien", donHang.getTongTien());
            long tmp = db.insert("DonHang","",contentValues);
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Đã có lỗi", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
    }

    public long insertChitietDonhang(ChiTietDonHang chiTietDonHang, Context context){
        db = openDB();
        String query = "SELECT * FROM ChiTietDonHang WHERE MaDonHang=? AND MaSanPham=?";
        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(chiTietDonHang.getMaDonHang()), Integer.toString(chiTietDonHang.getMaSanPham())});
        if(cursor.getCount() <= 0){
            // Insert new value into database
            ContentValues contentValues = new ContentValues();
            contentValues.put("MaDonHang", chiTietDonHang.getMaDonHang());
            contentValues.put("MaSanPham", chiTietDonHang.getMaSanPham());
            contentValues.put("SoLuong", chiTietDonHang.getSoLuong());
            contentValues.put("GiaBan", chiTietDonHang.getGiaBan());
            long tmp = db.insert("ChiTietDonHang","",contentValues);
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Đã có lỗi", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
    }
}



