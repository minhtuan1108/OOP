package QLTV;

import java.util.Scanner;

public abstract class Nguoi {
    protected String ten, gioitinh, ngaysinh, SDT;
    Scanner sc = new Scanner(System.in);

    // CONSTRUTOR
    public Nguoi() {

    }

    public Nguoi(String ten, String gioitinh, String ngaysinh, String SDT) {
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.SDT = SDT;
    }

    // GETTER
    public String getTen() {
        return ten;
    }
    public String getNgaysinh() {
        return ngaysinh;
    }
    public String getGioitinh() {
        return gioitinh;
    }
    public String getSDT() {
        return SDT;
    }

    // SETTER
    public void setTen(String ten) {
        this.ten = ten;
    }
    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    //PHƯƠNG THỨC TRỪU TƯỢNG
    public abstract void nhap();
    public abstract void xuat();
}
