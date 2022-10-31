package QLTV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class NguoiQuanLy extends Nguoi {
    SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
    protected Scanner sc = new Scanner(System.in);
    DanhSachTaiLieu DSTL;
    DanhSachDocGia DSDG ;
    DanhSachTheMuonTra DSTMT;
    protected String MaNQL, password;

    NguoiQuanLy() {
        super();
    }

    NguoiQuanLy(String maNQL, String password, String ten, String gioiTinh, String ngaySinh, String SDT) {
        super(ten,gioiTinh,ngaySinh,SDT);
        this.password = password;
        this.MaNQL = maNQL;
    }

    //CÁC HÀM SETTER
    public void setMaNQL(String MaNQL) {
        this.MaNQL = MaNQL;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //CÁC HÀM GETTER
    public String getMaNQL() {
        return MaNQL;
    }
    public String getPassword() {
        return password;
    }

    public boolean checkNgayTheoFormat(String date) {
        day.setLenient(false);
        try {
            day.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void nhap(){
        //NHẬP MÃ NGƯỜI QUẢN LÝ
        do {
            System.out.print("Nhập vào mã độc giả(Gồm 6 ký tự): ");
            MaNQL = sc.nextLine();
            if (MaNQL.length() != 6)
                System.out.println("Bạn đã nhập thừa hoặc thiếu kí tự");
        } while (MaNQL.length() != 6);

        // NHẬP TÊN
        System.out.print("Tên của người quản lý: ");
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
        System.out.println("\nNgày tháng năm sinh của người quản lý phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
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
            System.out.println("Nhập số điện thoại của người quản lý (Chú ý sdt chỉ từ 10-11 số): ");
            try{
                SDT = sc.nextLine();
                long temp = Integer.parseInt(SDT);
                if(SDT.length() < 10 || SDT.length() > 11){
                    check = true;
                    System.out.println("Số điện thoại có chiều dài không hợp lệ!");
                }
            } catch(NumberFormatException nfe){
                System.out.println("Số điện thoại không hợp lệ!");
                check = true;
            }
        }while(check);

        //NHẬP MẬT KHẨU
        do {
            System.out.println("Nhập mật khẩu người quản lý(> 4 ký tự): ");
            password = sc.nextLine();
            if(password.length() <= 4){
                System.out.println("Độ dài của mật khảu quá ngắn!");
            }
        }while(password.length() <= 4);
    }

    //XUẤT THÔNG TIN NGƯỜI QUẢN LÝ
    public void xuat(){
        System.out.printf("| %-10s | %-30s | %-5s | %-15s | %-15s |", MaNQL, ten, gioitinh, ngaysinh, SDT);
    }

    public void docFile(String filename) throws FileNotFoundException {
        Scanner readFile = new Scanner(new File(filename));

        if(readFile.hasNext()){
            MaNQL = readFile.nextLine();
            ten = readFile.nextLine();
            gioitinh = readFile.nextLine();
            ngaysinh = readFile.nextLine();
            SDT = readFile.nextLine();
            password = readFile.nextLine();
        }
        readFile.close();
    }

    public void ghiFile(String filename) throws FileNotFoundException{
        PrintWriter writeFile = new PrintWriter(new File(filename));
        writeFile.println(MaNQL);
        writeFile.println(ten);
        writeFile.println(gioitinh);
        writeFile.println(ngaysinh);
        writeFile.println(SDT);
        writeFile.println(password);
        writeFile.close();
    }
}
