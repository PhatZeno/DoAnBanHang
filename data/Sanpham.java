package com.example.doanbanhang.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Sanpham implements Parcelable, Serializable {
    int ID;
    String Tensp;
    String Dessp;
    int gia;
    String danhmuc;
    String nhacungcap;

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String ngayTao;

    String image;

    public Sanpham(int ID, String tensp, String dessp, int gia, String danhmuc, String nhacungcap,String ngayTao,String image) {
        this.ID = ID;
        Tensp = tensp;
        Dessp = dessp;
        this.gia = gia;
        this.danhmuc = danhmuc;
        this.nhacungcap = nhacungcap;
        this.ngayTao = ngayTao;
        this.image = image;
    }

    public String getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public String getDessp() {
        return Dessp;
    }

    public void setDessp(String dessp) {
        Dessp = dessp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public Sanpham(int ID, String tensp, String dessp, int gia, String danhmuc ,String ngayTao,String image) {
        this.ID = ID;
        Tensp = tensp;
        Dessp = dessp;
        this.gia = gia;
        this.danhmuc = danhmuc;
        this.ngayTao = ngayTao;
        this.image = image;
    }

    protected Sanpham(Parcel in) {
        ID = in.readInt();
        Tensp = in.readString();
        Dessp = in.readString();
        gia = in.readInt();
        danhmuc = in.readString();
        ngayTao = in.readString();
        image = in.readString();
    }

    public static final Creator<Sanpham> CREATOR = new Creator<Sanpham>() {
        @Override
        public Sanpham createFromParcel(Parcel in) {
            return new Sanpham(in);
        }

        @Override
        public Sanpham[] newArray(int size) {
            return new Sanpham[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(Tensp);
        parcel.writeString(Dessp);
        parcel.writeInt(gia);
        parcel.writeString(danhmuc);
        parcel.writeString(ngayTao);
        parcel.writeString(image);
    }

    // Các phương thức getter và setter của bạn ở đây
}
