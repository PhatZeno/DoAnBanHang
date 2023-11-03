package com.example.doanbanhang.data;

public class DanhMuc {

    private String id;
    private String tenDanhmuc;

    public DanhMuc(String id, String tenDanhmuc) {
        this.id = id;
        this.tenDanhmuc = tenDanhmuc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDanhmuc() {
        return tenDanhmuc;
    }

    public void setTenDanhmuc(String tenDanhmuc) {
        this.tenDanhmuc = tenDanhmuc;
    }
}
