package QLTV;

import java.util.Scanner;

public class TrangChu {
    Scanner sc = new Scanner(System.in);
    DangNhap signUp;
    TimKiem search;

    TrangChu(){
        signUp = new DangNhap();
        search = new TimKiem();
    }

    public void chucNangTrangChu() {
        boolean check = true;
        String chose;
        while(check) {
            System.out.println("*===============================================*");
            System.out.println("|                   TRANG CHỦ                   |");
            System.out.println("*===============================================*");
            System.out.println("|  1. Ấn 1 để tìm kiếm tài liệu.                |");
            System.out.println("|  2. Ấn 2 để đăng nhập(Thủ thư, thủ kho).      |");
            System.out.println("|  3. Thoát khỏi chương trình.                  |");
            System.out.println("*===============================================*");
            System.out.print("Nhập lựa chọn của bạn: ");
            chose = sc.nextLine();
            switch (chose){
                case "1":
                    search.chucNang();
                    break;

                case "2":
                    signUp.thucHienDangNhap();
                    break;

                case "3":
                    check = false;
                    break;
                default:System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại");
            }
        }
    }
}
