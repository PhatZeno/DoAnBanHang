package com.example.doanbanhang.data;

public class GioHang {

    private String Username;
    private  int IDsanpham;
    private int soluong;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getIDsanpham() {
        return IDsanpham;
    }

    public void setIDsanpham(int IDsanpham) {
        this.IDsanpham = IDsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public GioHang( String username, int IDsanpham, int soluong) {
        Username = username;
        this.IDsanpham = IDsanpham;
        this.soluong = soluong;
    }

    public GioHang( int soluong) {
        this.soluong = soluong;
    }
}
