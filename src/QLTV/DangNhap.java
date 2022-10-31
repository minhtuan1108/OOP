package QLTV;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class DangNhap {
    private ChucNangThuThu TT ;
    private ChucNangThuKho TK ;
    Scanner sc = new Scanner(System.in);

    DangNhap(){
        TT = new ChucNangThuThu();
        TK = new ChucNangThuKho();
    }

    public void thucHienDangNhap()
    {
        String ID, password;
        //Biết lựa chọn trong khi đăng nhập
        String chose;
        //Biến đếm số lần đăng nhập sai
        int count = 0;
        do {
            //Nhập sai 3 lần sẽ thoát đăng nhập
            if(count == 3){
                System.out.println("Bạn đã nhập sai 3 lần!");
                return;
            }

            System.out.print("Nhập ID của bạn: ");
            ID = sc.nextLine();
            System.out.print("Nhập password của bạn: ");
            password = sc.nextLine();
            count++;

            if(TT.kiemTraDangNhap(ID,password)) {
                System.out.println("Xin chào " + TT.getTen() + "! Chúc bạn một ngày làm việc thật vui vẻ ^^!");
                TT.chucNang();
                break;
            }

            if(TK.kiemTraDangNhap(ID,password)) {
                System.out.println("Xin chào " + TK.getTen() + "! Chúc bạn một ngày làm việc thật vui vẻ ^^!");
                TK.chucNang();
                break;
            }

            System.out.println("ID hoặc password không hợp lệ! Vui lòng nhập lại!");
            System.out.println("Ấn 1 để thoát đăng nhập!");
            System.out.println("Ấn phím bất kỳ để tiếp tục đăng nhập!");
            chose = sc.nextLine();
            if(chose.equals("1")) return;
        }while(true);

    }
}
