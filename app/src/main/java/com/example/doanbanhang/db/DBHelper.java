package com.example.doanbanhang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.Toast;


import com.example.doanbanhang.data.ChiTietDonHang;
import com.example.doanbanhang.data.DanhMuc;
import com.example.doanbanhang.data.DonHang;
import com.example.doanbanhang.data.GioHang;
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
    public int searchID(String newQuery){
        int id = -1;
        db = openDB();
        String sql = "SELECT ID FROM User WHERE USERNAME like '%" + newQuery +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
        }
        db.close();
        return id;
    }

    //thêm người dùng vào database
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

    //Kiểm tra tài khoản và mật khẩu có trong database
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
        Sanpham sanpham=new Sanpham(id,name,des,gia,danhmuc);
        tam.add(sanpham);
    }


    cursor.close();
    return tam;
}

    public ArrayList<Sanpham> getallSpsort(){
        ArrayList<Sanpham> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM Sanpham  ORDER BY TENSP ASC";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            int gia=cursor.getInt(3);
            String danhmuc=cursor.getString(4);
            Sanpham sanpham=new Sanpham(id,name,des,gia,danhmuc);
            tam.add(sanpham);
        }


        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallSpsort2(){
        ArrayList<Sanpham> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM Sanpham  ORDER BY TENSP DESC";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            int gia=cursor.getInt(3);
            String danhmuc=cursor.getString(4);
            Sanpham sanpham=new Sanpham(id,name,des,gia,danhmuc);
            tam.add(sanpham);
        }


        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> searchSanpham(String newQuery){
        ArrayList<Sanpham> tmp = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE TENSP like '%" + newQuery +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name= cursor.getString(1);
            String des=cursor.getString(2);
            int gia=cursor.getInt(3);
            String danhmuc=cursor.getString(4);
            Sanpham sanpham=new Sanpham(id,name,des,gia,danhmuc);
            tmp.add(sanpham);
        }

        db.close();

        return tmp;
    }
    public String searchpassword(int newQuery){
        String name = null;
        db = openDB();
        String sql = "SELECT PASSWORD FROM User WHERE ID like '%" + newQuery +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            name= cursor.getString(0);
        }
        db.close();
        return name;
    }
    public long updatepassword(User user){
        db = openDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD",user.getPassword());
        long tmp = db.update("User",contentValues, "ID="+ user.getID(), null);
        db.close();
        return tmp;
    }
    public String searchusername(int newQuery){
        String name = null;
        db = openDB();
        String sql = "SELECT USERNAME FROM User WHERE ID like '%" + newQuery +"%'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            name= cursor.getString(0);
        }
        db.close();
        return name;
    }
    public String getProductName(int ID){
        db=openDB();
        String sql="SELECT TENSP FROM Sanpham WHERE ID=?";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(ID)});
        if (cursor.moveToFirst()){
            String name= cursor.getString(0);
            cursor.close();
            return name;
        }
        return null;
    }
    public ArrayList<Sanpham> getallPCsort() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%PC%' ORDER BY TENSP ASC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallPCsort2() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%PC%' ORDER BY TENSP DESC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallPC() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%PC%'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallPhone() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%Phone%'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallPhonesort() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%Phone%'  ORDER BY TENSP ASC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallPhonesort2() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%Phone%' ORDER BY TENSP DESC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallHP() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%HP%'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallHPsort() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%HP%'  ORDER BY TENSP ASC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallHPsort2() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%HP%'  ORDER BY TENSP DESC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallLaptop() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%Laptop%'";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }
    public ArrayList<Sanpham> getallLaptopsort() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%Laptop%'  ORDER BY TENSP ASC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
    }

    public ArrayList<Sanpham> getallLaptopsort2() {
        ArrayList<Sanpham> tam = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM Sanpham WHERE Danhmuc like'%Laptop%'  ORDER BY TENSP DESC";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            int gia = cursor.getInt(3);
            String danhmuc = cursor.getString(4);
            Sanpham sanpham = new Sanpham(id, name, des, gia, danhmuc);
            tam.add(sanpham);
        }
        cursor.close();
        return tam;
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
    public ArrayList<DonHang> getdonhang(String username){
        ArrayList<DonHang> tam=new ArrayList<>();
        db=openDB();
        String sql="SELECT * FROM DonHang WHERE MaKhachHang=? ";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(username)});
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
    public ArrayList<GioHang> getAllGioHang(String userId){
        ArrayList<GioHang> gioHangList = new ArrayList<>();
        db = openDB();
        String sql = "SELECT * FROM GioHang WHERE USER_ID=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userId});
        while (cursor.moveToNext()){
            String userIds = cursor.getString(0);
            int sanPhamId = cursor.getInt(1);
            int soLuong = cursor.getInt(2);
            GioHang gioHang = new GioHang(userIds,sanPhamId,soLuong);
            gioHangList.add(gioHang);
        }
        cursor.close();
        return gioHangList;
    }



    public Sanpham getSanPham(int MaSanPham){
        db = openDB();
        String sql = "SELECT TENSP,DESSP,GIASP,DANHMUC,Nhacungcap FROM Sanpham WHERE ID=?";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(MaSanPham)});
        if (cursor.moveToFirst()){
            String tensp = cursor.getString(0);
            String dessp = cursor.getString(1);
            int giasp = cursor.getInt(2);
            String danhmuc = cursor.getString(3);
            String nhacungcap = cursor.getString(4);
            Sanpham sanPham = new Sanpham(MaSanPham, tensp, dessp, giasp, danhmuc, nhacungcap);
            cursor.close();
            return sanPham;
        } else {
            cursor.close();
            return null;
        }
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
    public long deleteFromGioHang(String username,int idsp){
        db = openDB();
        long tmp = db.delete("Giohang", "USER_ID = ? AND SANPHAM_ID = ?", new String[]{username, String.valueOf(idsp)});
        db.close();
        return tmp;
    }
    public long deleteGioHang(String username){
        db = openDB();
        long tmp = db.delete("Giohang", "USER_ID = ?", new String[]{username});
        db.close();
        return tmp;
    }
    public long insertGioHang(GioHang gioHang, String Username, int MaSanPham, Context context){
        db = openDB();
        String query = "SELECT * FROM GioHang WHERE USER_ID=? AND SANPHAM_ID=?";
        Cursor cursor = db.rawQuery(query, new String[]{Username, Integer.toString(MaSanPham)});
        if(cursor.getCount() <= 0){
            // Insert new value into database
            ContentValues contentValues = new ContentValues();
            contentValues.put("USER_ID", gioHang.getUsername());
            contentValues.put("SANPHAM_ID", gioHang.getIDsanpham());
            contentValues.put("SOLUONG", gioHang.getSoluong());
            long tmp = db.insert("Giohang","",contentValues);
            cursor.close();
            db.close();
            return tmp;
        } else {
            Toast.makeText(context, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
            cursor.close();
            db.close();
            return -1;
        }
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
            contentValues.put("DiaChi",donHang.getDiachi());
            contentValues.put("GhiChu",donHang.getGhichu());
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



