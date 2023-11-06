package com.example.doanbanhang.data;

public class DonHang {


        private int maDonHang;
        private String maKhachHang;
        private String ngayMua;
        private double tongTien;


    public DonHang(int maDonHang, String maKhachHang, String ngayMua, double tongTien) {
        this.maDonHang = maDonHang;
        this.maKhachHang = maKhachHang;
        this.ngayMua = ngayMua;
        this.tongTien = tongTien;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
