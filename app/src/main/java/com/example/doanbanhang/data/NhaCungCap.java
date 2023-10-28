package com.example.doanbanhang.data;

public class NhaCungCap {

    private String id;
    private String ten;
    private String des;
    private String diachi;

    public NhaCungCap(String id, String ten, String des, String diachi) {
        this.id = id;
        this.ten = ten;
        this.des = des;
        this.diachi = diachi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
