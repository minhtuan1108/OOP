package QLTV;

import java.io.FileNotFoundException;

public class ChucNangThuThu extends NguoiQuanLy {

    ChucNangThuThu() {
        try {
            this.docFile("ThuThu.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //XÁC THỰC THÔNG TIN CỦA THỦ THƯ
    public boolean kiemTraDangNhap(String id,String pass) {
        return id.equals(this.MaNQL) && pass.equals(this.password);
    }

    public void chucNang()
    {
        //Tạo mới các danh sách để bỏ các dữ liệu đã có sẵn khi đăng nhập nhiều lần (không thoát chương trình)
        DSDG = new DanhSachDocGia();
        DSTL = new DanhSachTaiLieu();
        try {
            DSTL.docFileDanhSach("DanhSachTaiLieu.txt");
            DSDG.docFileDanhSach("DanhSachDocGia.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DSTMT = new DanhSachTheMuonTra(DSTL,DSDG);
        try {
            DSTMT.docFileDSTheMuon("DanhSachTheMuon.txt");
            DSTMT.docFileDSTheTra("DanhSachTheTra.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //DSTMT = new DanhSachTheMuonTra(DSTL,DSDG);
        String chose;
        String maDG = "" ;
        boolean check = true;
        while (check) {
            System.out.println("*=====================================================*");
            System.out.println("|                  CHỨC NĂNG THỦ THƯ                  |");
            System.out.println("*=====================================================*");
            System.out.println("|  1.Ấn phím 1 để lập thẻ cho 1 đọc giả.              |");
            System.out.println("|  2.Ấn phím 2 để lập thẻ cho nhiều đọc giả.          |");
            System.out.println("|  3.Ấn phím 3 để xoá đọc giả.                        |");
            System.out.println("|  4.Ấn phím 4 để sửa thông tin đọc giả.              |");
            System.out.println("|  5.Ấn phím 5 để tìm kiếm đọc giả.                   |");
            System.out.println("|  6.Ấn phím 6 để xuất danh sách đọc giả.             |");
            System.out.println("|  7.Ấn phím 7 để cho mượn tài liệu.                  |");
            System.out.println("|  8.Ấn phím 8 để thống kê danh sách mượn tài liệu    |");
            System.out.println("|  9.Ấn phím 9 để nhận trả tài liệu.                  |");
            System.out.println("|  10.Ấn phím 10 để thống kê danh sách trả tài liệu   |");
            System.out.println("|  11.Trở về.                                         |");
            System.out.println("*=====================================================*");
            System.out.print("Lựa chọn của bạn là: ");
            chose = sc.nextLine();
            switch (chose) {
                case "1":
                    do {
                        System.out.print("Nhập vào mã độc giả(Gồm 5 ký tự): ");
                        maDG = sc.nextLine();
                        if (maDG.length() == 5 && !DSDG.checkMaDG(maDG,-1))
                            break;
                        System.out.println("Bạn đã nhập thừa hoặc thiếu kí tự hoặc mã đã tồn tại!");
                    } while (true);
                    DSDG.Insert(maDG);
                    break;
                case "2":
                    DSDG.nhapDanhSachDocGia();
                    break;
                case "3":
                    System.out.print("Nhập vào mã độc giả cần xoá: ");
                    maDG = sc.nextLine();
                    DSDG.Delete(maDG);
                    break;
                case "4":
                    System.out.print("Nhập vào mã độc giả cần sửa: ");
                    maDG = sc.nextLine();
                    DSDG.Adjust(maDG);
                    break;
                case "5":
                    DSDG.Search();
                    break;
                case "6":
                    DSDG.xuatDanhSachDocGia();
                    break;
                case "7":
                    DSTMT.choMuonTaiLieu();
                    break;
                case "8":
                    DSTMT.thongKeTaiLieuMuon();
                    break;
                case "9":
                    DSTMT.nhanTraTaiLieu();
                    break;
                case "10":
                    DSTMT.thongKeDSTraTaiLieu();
                    break;
                case "11":
                    check = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại!");
            }
            if(check) {
                System.out.print("Ấn Enter để tiếp tục!");
                sc.nextLine();
            }
        }

        try {
            DSTL.ghiFileDanhSach("DanhSachTaiLieu.txt");
            DSDG.ghiFileDanhSach("DanhSachDocGia.txt");
            DSTMT.ghiFileDSTheMuon("DanhSachTheMuon.txt");
            DSTMT.ghiFileDSTheTra("DanhSachTheTra.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Đã cập nhật dữ liệu vào hệ thống!");
        System.out.println("Nhấn Enter để về trang chủ!");
        sc.nextLine();
    }
}
