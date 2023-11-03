package com.example.doanbanhang.data;

public class DoanhThu {
    private String ngay;
    private double tongDoanhThu;

    public DoanhThu(String ngay, double tongDoanhThu) {
        this.ngay = ngay;
        this.tongDoanhThu = tongDoanhThu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }
}
