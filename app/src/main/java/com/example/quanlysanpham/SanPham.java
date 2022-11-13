package com.example.quanlysanpham;

public class SanPham {
    private String danhmuc;
    private String masp;
    private String tensp;
    private int soluong;

    public SanPham(String danhmuc, String masp, String tensp, int soluong) {
        this.danhmuc = danhmuc;
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
    }
    public SanPham() {

    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
