package QLTV;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ChiTietPhieuNhapTaiLieu{
    SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);
    private int slnhap, gia;
    private String maPN, ngayNhap, maTL, tenTL, tacGia, NXB, theLoai;

    //CÁC HÀM KHỞI TẠO
    public ChiTietPhieuNhapTaiLieu() {
        super();
    }

    public ChiTietPhieuNhapTaiLieu(String maPN, int slnhap, String ngayNhap, String maTL, String tenTL, String tacgia, String NXB, String theLoai, int gia) {
        this.maPN = maPN;
        this.slnhap = slnhap;
        this.ngayNhap = ngayNhap;
        this.tenTL = tenTL;
        this.maTL = maTL;
        this.tacGia = tacgia;
        this.NXB = NXB;
        this.theLoai = theLoai;
        this.gia = gia;
    }

    // CÁC PHƯƠNG THỨC GETTER AND SETTER
    public int getSlnhap() {
        return slnhap;
    }
    public int getGia() {
        return gia;
    }
    public String getMaPN() {
        return maPN;
    }
    public String getNgayNhap() {
        return ngayNhap;
    }
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

    public void setSlnhap(int slnhap) {
        this.slnhap = slnhap;
    }
    public void setGia(int gia) {
        this.gia = gia;
    }
    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }
    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
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

    //KIỂM TRA NGÀY NHẬP
    public boolean checkNgayTheoFormat(String date) {
        day.setLenient(false);
        try {
            day.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //NHẬP MÃ PHIẾU NHẬP
    public void nhapMPN(){
        // NHẬP MÃ PHIẾU NHẬP
        do {
            System.out.print("Nhập vào mã độc giả(Gồm 5 ký tự): ");
            maPN = sc.nextLine();
            if (maPN.length() != 5)
                System.out.println("Bạn đã nhập thừa hoặc thiếu kí tự");
        } while (maPN.length() != 5);
    }

    //PHƯƠNG THỨC NHẬP PHIẾU NHẬP
    public void nhapPN(){

        //NHẬP MÃ TÀI LIỆU
        do {
            System.out.print("Nhập mã tài liệu(Gồm 5 ký tự): ");
            maTL = sc.nextLine();
            if(maTL.length() == 5)
                break;
            System.out.println("Độ dài của mã tài liệu không hợp lệ!Vui lòng nhập lại!");
        }while(true);

        //NHẬP TÊN TÀI LIỆU
        System.out.print("Nhập tên tài liệu: ");
        tenTL = sc.nextLine();

        //NHẬP GIÁ TÀI LIỆU
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

        //NHÂP NGÀY NHẬP
        System.out.println("\nNgày nhập tài liệu phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
        do {
            System.out.print("Ngày nhâp tài liệu: ");
            ngayNhap = sc.nextLine();
            if (!checkNgayTheoFormat(ngayNhap)) {
                System.out.print("\nNgày nhập không hợp lệ. Xin mời nhập lại!!!");
                System.out.println();
            }
        } while (!checkNgayTheoFormat(ngayNhap));

        //NHẬP SỐ LƯỢNG NHẬP
        do{
            System.out.println("Nhập số lượng tài liệu nhập về:");
            try {
                slnhap = Integer.parseInt(sc.nextLine());
                if(slnhap < 0){
                    System.out.println("Số lượng nhập về không thể bé hơn 0!");
                }
            }catch (NumberFormatException nfe){

            }
        }while(slnhap<0);
    }

    //PHƯỚNG THỨC LẤY THÔNG TIN TÀI LIỆU CHO PHIẾU NHẬP
    public void getInfoDocument(TaiLieu TL){
        this.maTL = TL.getMaTL();
        this.tenTL = TL.getTenTL();
        this.tacGia = TL.getTacGia();
        this.NXB = TL.getNXB();
        this.theLoai = TL.getTheLoai();
        this.gia = TL.getGia();

        System.out.println("Tài liệu \"" + tenTL + "\" này đã có trong thư viện!");


    }

    //XUẤT THÔNG TIN PHIẾU NHẬP
    public void xuat(){
        System.out.printf("| %-8s | %-8s | %-30s | %-15s | %-20s | %-20s | %-15s | %8d | %10d |\n", maPN,maTL,tenTL,ngayNhap,tacGia,NXB,theLoai,slnhap,gia);
    }

    //ĐỌC FILE
    public void docFilePhieuNhap(Scanner readFile) throws IOException{
        maPN = readFile.nextLine();
        maTL = readFile.nextLine();
        tenTL = readFile.nextLine();
        ngayNhap = readFile.nextLine();
        tacGia = readFile.nextLine();
        NXB = readFile.nextLine();
        theLoai = readFile.nextLine();
        slnhap = readFile.nextInt();
        readFile.nextLine();
        gia = readFile.nextInt();
        readFile.nextLine();
        readFile.nextLine();
    }

    //GHI FILE
    public void ghiFilePhieuNhap(PrintWriter writeFile) throws IOException{
        writeFile.println(maPN);
        writeFile.println(maTL);
        writeFile.println(tenTL);
        writeFile.println(ngayNhap);
        writeFile.println(tacGia);
        writeFile.println(NXB);
        writeFile.println(theLoai);
        writeFile.println(slnhap);
        writeFile.println(gia);
        writeFile.println("");
    }
}

    
