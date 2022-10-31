package QLTV;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

public class DocGia extends Nguoi {
    SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
    private String MaDG, Diachi, Ngaylamthe, Ngayhethan, Chucvu;

    // CONSTRUCTOR
    public DocGia() {
        super();
    }

    public DocGia(String ten, String gioitinh, String ngaysinh, String SDT, String MaDG, String Diachi, String NgaylamThe, String Ngayhethan, String Chucvu) {
        super(ten, gioitinh, ngaysinh, SDT);
        this.MaDG = MaDG;
        this.Diachi = Diachi;
        this.Ngaylamthe = NgaylamThe;
        this.Ngayhethan = Ngayhethan;
        this.Chucvu = Chucvu;
    }

    // GETTER & SETTER
    public String getMaDG() {
        return MaDG;
    }
    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }
    public String getDiachi() {
        return Diachi;
    }
    public void setDiachi(String DonVi) {
        this.Diachi = DonVi;
    }
    public String getChucVu() {
        return Chucvu;
    }
    public void setChucVu(String Chucvu) {
        this.Chucvu = Chucvu;
    }
    public String getNgayLamThe() {
        return Ngaylamthe;
    }
    public void setNgayLamThe(String Ngaylamthe) {
        this.Ngaylamthe = Ngaylamthe;
    }
    public String getNgayHetHan() {
        return Ngayhethan;
    }
    public void setNgayHetHan(String Ngayhethan) {
        this.Ngayhethan = Ngayhethan;
    }

    //KIỂM TRA NGÀY THÁNH NĂM
    public boolean checkNgayTheoFormat(String date) {
        day.setLenient(false);
        try {
            day.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void nhapMDG(){
        // NHẬP MÃ ĐỌC GIẢ
        do {
            System.out.print("Nhập vào mã độc giả(Gồm 5 ký tự): ");
            MaDG = sc.nextLine();
            if (MaDG.length() != 5)
                System.out.println("Bạn đã nhập thừa hoặc thiếu kí tự");
        } while (MaDG.length() != 5);
    }

    @Override
    public void nhap() {

        // NHẬP TÊN
        System.out.print("Tên của độc giả: ");
        ten = sc.nextLine();

        // NHẬP GIỚI TÍNH
        do {
            System.out.print("Khi nhập giới tính, lưu ý chỉ cho phép nhập nam hay nữ!!!");
            System.out.print("Giới tính: ");
            gioitinh = sc.nextLine();
            if (!gioitinh.equalsIgnoreCase("Nam") && !gioitinh.equalsIgnoreCase("Nữ"))
                System.out.println("Bạn đã nhập sai giới tính!!!");
        } while (!gioitinh.equalsIgnoreCase("Nam") && !gioitinh.equalsIgnoreCase("Nữ"));

        // NHẬP NGÀY THÁNG NĂM SINH
        System.out.println("\nNgày tháng năm sinh của độc giả phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
        do {
            System.out.print("Ngày tháng năm sinh: ");
            ngaysinh = sc.nextLine();
            if (!checkNgayTheoFormat(ngaysinh)) {
                System.out.print("\nNgày tháng năm sinh không hợp lệ. Xin mời nhập lại!!!");
                System.out.println();
            }
        } while (!checkNgayTheoFormat(ngaysinh));

        //NHẬP SỐ ĐIỆN THOẠI
        boolean check;
        do {
            check = false;
            System.out.print("Nhập số điện thoại của đọc giả (Chú ý sdt chỉ từ 10-11 số): ");
            try{
                SDT = sc.nextLine();
                long temp = Long.parseLong(SDT);
                if(SDT.length() < 10 || SDT.length() > 11){
                    check = true;
                    System.out.println("Số điện thoại có chiều dài không hợp lệ!");
                }
            } catch(NumberFormatException nfe){
                System.out.println("Số điện thoại không hợp lệ!");
                check = true;
            }
        }while(check);

        // NHẬP NGÀY LÀM THẺ
        System.out.println("\nNgày làm thẻ của độc giả phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi!");
        do {
            System.out.print("Ngày làm thẻ: ");
            Ngaylamthe = sc.nextLine();
            if (!checkNgayTheoFormat(Ngaylamthe)) {
                System.out.print("\nNgày làm thẻ không hợp lệ. Xin mời nhập lại!!!");
                System.out.println();
            }
        } while (!checkNgayTheoFormat(Ngaylamthe));

        // NHẬP NGÀY HẾT HẠN THẺ
        do {
            System.out.println("\nNgày hết hạn của thẻ độc giả phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi!");
            System.out.print("Ngày hết hạn: ");
            Ngayhethan = sc.nextLine();
            if (!checkNgayTheoFormat(Ngayhethan)) {
                System.out.print("\nNgày hết hạn của thẻ không hợp lệ. Xin mời nhập lại!!!");
                System.out.println();
            }
        } while (!checkNgayTheoFormat(Ngayhethan));

        // NHẬP CHỨC VỤ
        System.out.print("Chức vụ: ");
        Chucvu = sc.nextLine();

        // NHẬP ĐỊA CHỈ
        System.out.print("Địa chỉ: ");
        Diachi = sc.nextLine();

    }

    @Override
    public void xuat() {
        System.out.format("| %-8s | %-30s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", MaDG, ten, gioitinh, SDT, ngaysinh, Ngaylamthe, Ngayhethan, Chucvu, Diachi);
    }

    public void ghiFile(PrintWriter writeFile) throws IOException {
        writeFile.println(MaDG);
        writeFile.println(ten);
        writeFile.println(gioitinh);
        writeFile.println(SDT);
        writeFile.println(ngaysinh);
        writeFile.println(Ngaylamthe);
        writeFile.println(Ngayhethan);
        writeFile.println(Chucvu);
        writeFile.println(Diachi);
        writeFile.println("");
    }

    public void docFile(Scanner readFile){
        MaDG = readFile.nextLine();
        ten = readFile.nextLine();
        gioitinh = readFile.nextLine();
        SDT = readFile.nextLine();
        ngaysinh = readFile.nextLine();
        Ngaylamthe = readFile.nextLine();
        Ngayhethan = readFile.nextLine();
        Chucvu = readFile.nextLine();
        Diachi = readFile.nextLine();
        readFile.nextLine();
    }
}