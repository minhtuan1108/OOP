
package QLTV;

import java.io.*;
import java.util.Scanner;

public class TaiLieu {

    Scanner sc = new Scanner(System.in);
    private String maTL, tenTL, tacGia, NXB, theLoai;
    private int slCo, slCon, gia;

    //CÁC HÀM KHỞI TẠO
    public TaiLieu() {
    }

    public TaiLieu(String MaTL, String TenTL, String TacGia, String NXB, String TheLoai, int slco, int gia) {
        this.maTL = MaTL;
        this.tenTL = TenTL;
        this.tacGia = TacGia;
        this.NXB = NXB;
        this.theLoai = TheLoai;
        this.slCo = slco;
        this.slCon = slco;
        this.gia = gia;
    }

    //CÁC PHƯƠNG THỨC GETTER

    public String getMaTL() {
        return maTL;
    }
    public String getTenTL() {
        return tenTL;
    }
    public String getTacGia() {
        return tacGia;
    }
    public String getNXB() {
        return NXB;
    }
    public String getTheLoai() {
        return theLoai;
    }
    public int getSlco() {
        return slCo;
    }
    public int getSlcon() {
        return slCon;
    }
    public int getGia() {
        return gia;
    }

    //CÁC PHƯƠNG THỨC SETTER

    public void setMaTL(String maTL) {
        this.maTL = maTL;
    }
    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
    public void setNXB(String NXB) {
        this.NXB = NXB;
    }
    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
    public void setSlCo(int slCo) {
        this.slCo = slCo;
    }
    public void setSlCon(int slCon) {
        this.slCon = slCon;
    }
    public void setGia(int gia) {
        this.gia = gia;
    }
//KẾT THÚC CÁC PHƯƠNG THỨC GETTER SETTER

//CÁC PHƯƠNG THỨC NHẬP XUẤT
    //NHẬP MÃ TÀI LIỆU
    public void nhap1()
    {
        do {
            System.out.print("Nhập mã tài liệu(Gồm 5 ký tự): ");
            maTL = sc.nextLine();
            if(maTL.length() == 5)
                break;
            System.out.println("Độ dài của mã tài liệu không hợp lệ!Vui lòng nhập lại!");
        }while(true);
    }

    //NHẬP TÀI LIỆU KHÔNG NHẬP SỐ LƯỢNG
    public void nhap2() {
        System.out.print("Nhập tên tài liệu: ");
        tenTL = sc.nextLine();

        boolean flag;
        do {
            try {
                flag = false;
                System.out.print("Nhập giá tài liệu(>0): ");
                gia = Integer.parseInt(sc.nextLine());
                if (gia < 0) {
                    flag = true;
                    System.out.println("Giá tài liệu không được bé hơn 0!");
                }
            } catch (NumberFormatException e) {
                flag = true;
                System.out.println("Vui lòng nhập một số!");
            }
        } while (flag);

        System.out.print("Nhập tên tác giả: ");
        tacGia = sc.nextLine();
        System.out.print("Nhập tên nhà xuất bản: ");
        NXB = sc.nextLine();
        System.out.print("Nhập loại tài liệu: ");
        theLoai = sc.nextLine();
    }

    //PHƯƠNG THỨC NHẬP SL TÀI LIỆU CÓ TRONG THƯ VIỆN
    public void nhap3(){
        boolean flag;
        do {
            try {
                flag = false;
                System.out.print("Nhập số lượng tài liệu: ");
                slCo = Integer.parseInt(sc.nextLine());
                if(slCo < 0) {
                    flag = true;
                    System.out.print("Số lượng tài liệu không được bé hơn 0!");
                }
            }catch(NumberFormatException e) {
                flag = true;
                System.out.println("Vui lòng nhập một số!");
            }
        } while(flag);
        slCon+= slCo;
    }

    public void nhap(){
        nhap1();
        nhap2();
        nhap3();
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-30s | %-25s | %-25s | %-15s | %8d | %5d | %5d |",maTL,tenTL,tacGia,NXB,theLoai,gia,slCo,slCon);
    }

    public void xuat() {
        System.out.println(toString());
    }

    //GHI FILE
    void ghiFile(PrintWriter writeFile) throws IOException{
        writeFile.println(maTL);
        writeFile.println(tenTL);
        writeFile.println(tacGia);
        writeFile.println(NXB);
        writeFile.println(theLoai);
        writeFile.println(gia);
        writeFile.println(slCo);
        writeFile.println(slCon);
        writeFile.println("");
    }

    //ĐỌC FILE
    void docFile(Scanner readFile) throws IOException{
        maTL = readFile.nextLine();
        tenTL = readFile.nextLine();
        tacGia = readFile.nextLine();
        NXB = readFile.nextLine();
        theLoai = readFile.nextLine();
        gia = readFile.nextInt();
        readFile.nextLine();
        slCo = readFile.nextInt();
        readFile.nextLine();
        slCon = readFile.nextInt();
        readFile.nextLine();
        readFile.nextLine();
    }


}
