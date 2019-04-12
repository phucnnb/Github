package com.example.demofirebase.Model;

public class SinhVien {
    String hoTen;
    int msSinhVien;

    public SinhVien(String hoTen, int msSinhVien) {
        this.hoTen = hoTen;
        this.msSinhVien = msSinhVien;
    }

    public SinhVien() {
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getMsSinhVien() {
        return msSinhVien;
    }

    public void setMsSinhVien(int msSinhVien) {
        this.msSinhVien = msSinhVien;
    }
}
