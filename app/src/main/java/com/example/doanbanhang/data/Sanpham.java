package com.example.doanbanhang.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Sanpham implements Parcelable {
    int ID;
    String Tensp;
    String Dessp;
    int gia;
    String danhmuc;
    String nhacungcap;

    public Sanpham(int ID, String tensp, String dessp, int gia, String danhmuc, String nhacungcap) {
        this.ID = ID;
        Tensp = tensp;
        Dessp = dessp;
        this.gia = gia;
        this.danhmuc = danhmuc;
        this.nhacungcap = nhacungcap;
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

    public Sanpham(int ID, String tensp, String dessp, int gia, String danhmuc) {
        this.ID = ID;
        Tensp = tensp;
        Dessp = dessp;
        this.gia = gia;
        this.danhmuc = danhmuc;
    }

    protected Sanpham(Parcel in) {
        ID = in.readInt();
        Tensp = in.readString();
        Dessp = in.readString();
        gia = in.readInt();
        danhmuc = in.readString();
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
    }

    // Các phương thức getter và setter của bạn ở đây
}
