package QLTV;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChiTietTheMuonTra {
    private String maThe, maDG, tenDG, maTL, tenTL, ngayMuon, hanTra, ngayTra;
    private int slMuon, gia, slTra, phatQuaHan, phatHongMat;

    ChiTietTheMuonTra(){

    }

    ChiTietTheMuonTra(String maThe, String maDG, String tenDG, String maTL, String tenTL, String ngayMuon, String hanTra, int gia, int sl){
        this.maThe = maThe;
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.gia = gia;
        this.ngayMuon = ngayMuon;
        this.slMuon = sl;
        this.hanTra = hanTra;
    }

    //CÁC PHƯƠNG THỨC GETTER
    public String getMaThe() {
        return maThe;
    }
    public String getMaDG() {
        return maDG;
    }
    public String getTenDG() {
        return tenDG;
    }
    public String getMaTL() {
        return maTL;
    }
    public String getTenTL() {
        return tenTL;
    }
    public String getNgayMuon() {
        return ngayMuon;
    }
    public String getHanTra() {
        return hanTra;
    }
    public int getSlMuon() {
        return slMuon;
    }
    public int getGia() {
        return gia;
    }
    public String getNgayTra() {
        return ngayTra;
    }
    public int getSlTra() {
        return slTra;
    }
    public int getPhatQuaHan() {
        return phatQuaHan;
    }
    public int getPhatHongMat() {
        return phatHongMat;
    }

    //CÁC PHƯƠNG THỨC SETTER
    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }
    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }
    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }
    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }
    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
    public void setHanTra(String hanTra) {
        this.hanTra = hanTra;
    }
    public void setSlMuon(int slMuon) {
        this.slMuon = slMuon;
    }
    public void setGia(int gia) {
        this.gia = gia;
    }
    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
    public void setSlTra(int slTra) {
        this.slTra = slTra;
    }
    public void setPhatQuaHan(int phatQuaHan) {
        this.phatQuaHan = phatQuaHan;
    }
    public void setPhatHongMat(int phatHongMat) {
        this.phatHongMat = phatHongMat;
    }

    //XUẤT THẺ MƯỢN TÀI LIỆU
    public void xuatTheMuon(){
        System.out.printf("| %-8s | %-8s | %-30s | %-8s | %-30s | %-8d | %-15s | %-8d | %-15s |\n",maThe,maDG, tenDG, maTL, tenTL, gia, ngayMuon, slMuon, hanTra);
    }

    //XUẤT THẺ TRẢ TÀI LIỆU
    public void xuatTheTra(){
        System.out.printf("| %-8s | %-8s | %-30s | %-8s | %-30s | %-8d | %-15s | %-8d | %-15s | %-15s | %-8d | %-8d | %-8d |\n",maThe,maDG, tenDG, maTL, tenTL, gia, ngayMuon, slMuon, hanTra, ngayTra, slTra,phatQuaHan,phatHongMat);
    }

    //XUẤT TÀI LIỆU ĐÃ QUÁ HẠN
    public void xuatTLQH(){
        System.out.printf("| %-30s | %-30s | %-8d | %-15s | %-15s |\n",tenDG,tenTL,slMuon,ngayMuon,hanTra);
    }

    //XUẤT TÀI LIỆU HỎNG MẤT
    public void xuatTLHM(){
        System.out.printf("| %-30s | %-30s | %-8d | %-15s |\n",tenDG,tenTL,slMuon - slTra,ngayTra);
    }

    //ĐỌC FILE THẺ MƯỢN TÀI LIỆU
    public void docFileTM(Scanner readFile) throws IOException{
        maThe = readFile.nextLine();
        maDG = readFile.nextLine();
        tenDG = readFile.nextLine();
        maTL = readFile.nextLine();
        tenTL = readFile.nextLine();
        gia = readFile.nextInt();
        readFile.nextLine();
        ngayMuon = readFile.nextLine();
        slMuon = readFile.nextInt();
        readFile.nextLine();
        hanTra = readFile.nextLine();
    }

    //ĐỌC FILE THẺ TRẢ TÀI LIỆU
    public void docFileTT(Scanner readFile) throws IOException{
        docFileTM(readFile);
        ngayTra = readFile.nextLine();
        slTra = readFile.nextInt();
        readFile.nextLine();
        phatQuaHan = readFile.nextInt();
        readFile.nextLine();
        phatHongMat = readFile.nextInt();
        readFile.nextLine();
    }

    //GHI FILE THẺ MƯỢN TÀI LIỆU
    public void ghiFileTM(PrintWriter writeFile) throws IOException{
        writeFile.println(maThe);
        writeFile.println(maDG);
        writeFile.println(tenDG);
        writeFile.println(maTL);
        writeFile.println(tenTL);
        writeFile.println(gia);
        writeFile.println(ngayMuon);
        writeFile.println(slMuon);
        writeFile.println(hanTra);
    }

    //GHI FILE THẺ TRẢ TÀI LIỆU
    public void ghiFileTT(PrintWriter writeFile) throws IOException{
        ghiFileTM(writeFile);
        writeFile.println(ngayTra);
        writeFile.println(slTra);
        writeFile.println(phatQuaHan);
        writeFile.println(phatHongMat);
    }

}
