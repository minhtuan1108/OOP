package QLTV;

import java.io.FileNotFoundException;

public class ChucNangThuKho extends NguoiQuanLy{

    DanhSachPhieuNhapTaiLieu DSPN;

    ChucNangThuKho() {
        try {
            this.docFile("ThuKho.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean kiemTraDangNhap(String id, String pass)
    {
        return id.equals(this.MaNQL) && pass.equals(this.password);
    }

    public void chucNang()
    {
        //Tạo mới các danh sách để bỏ các dữ liệu đã có sẵn khi đăng nhập nhiều lần (không thoát chương trình)
        DSTL = new DanhSachTaiLieu();
        DSTMT = new DanhSachTheMuonTra(DSTL,DSDG);
        DSPN = new DanhSachPhieuNhapTaiLieu(DSTL);

        //Đọc file "DanhSachPhieuNhap", "DanhSachTaiLieu", "DanhSachTheMuon", "DanhSach
        try {
            DSTL.docFileDanhSach("DanhSachTaiLieu.txt");
            DSPN.docFileDanhSach("DanhSachPhieuNhap.txt");
            DSTMT.docFileDSTheMuon("DanhSachTheMuon.txt");
            DSTMT.docFileDSTheTra("DanhSachTheTra.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String chose;
        boolean check = true;
        String mamoi;
        while(check) {
            System.out.println("*=======================================================*");
            System.out.println("|                   CHỨC NĂNG THỦ KHO                   |");
            System.out.println("*=======================================================*");
            System.out.println("|  1. Ấn phím 1 để nhập tài liệu mới về.                |");
            System.out.println("|  2. Ấn phím 2 để xóa tài liệu.                        |");
            System.out.println("|  3. Ấn phím 3 để chỉnh sửa tài liệu.                  |");
            System.out.println("|  4. Ấn phím 4 để tìm kiếm tài liệu.                   |");
            System.out.println("|  5. Ấn phím 5 để xuất danh sách tài liệu.             |");
            System.out.println("|  6. Ấn phím 6 để thống kê tài liệu quá hạn.           |");
            System.out.println("|  7. Ấn phím 7 để thống kê tài liệu hỏng mất.          |");
            System.out.println("|  8. Ấn phím 8 để thống kê danh sách phiếu nhập.       |");
            System.out.println("|  9. Ấn phím 9 Trở về.                                 |");
            System.out.println("*=======================================================*");
            System.out.print("Nhập lựa chọn của bạn : ");
            chose = sc.nextLine();
            switch(chose){
                case "1":
                    do {
                        System.out.print("Nhập mã của phiếu nhập(Gồm 5 ký tự): ");
                        mamoi = sc.nextLine();
                        if(mamoi.length() == 5 && !DSPN.checkMaPNTL(mamoi,-1))
                            break;
                        System.out.println("Độ dài của mã phiếu nhập không hợp lệ hoặc bị trùng! Vui lòng nhập lại!");
                    }while(true);
                    DSPN.Insert(mamoi);
                    break;

                case "2":
                    do {
                        System.out.print("Nhập mã tài liệu cần xóa(Gồm 5 ký tự): ");
                        mamoi = sc.nextLine();
                        if(mamoi.length() == 5 )
                            break;
                        System.out.println("Độ dài của mã tài liệu không hợp lệ!Vui lòng nhập lại!");
                    }while(true);
                    DSTL.Delete(mamoi);
                    break;

                case "3":
                    do {
                        System.out.print("Nhập mã tài liệu muốn chỉnh sửa(Gồm 5 ký tự): ");
                        mamoi = sc.nextLine();
                        if(mamoi.length() == 5)
                            break;
                        System.out.println("Độ dài của mã tài liệu không hợp lệ! Vui lòng nhập lại!");
                    }while(true);
                    DSTL.Adjust(mamoi);
                    break;

                case "4":
                    DSTL.Search();
                    break;

                case "5":
                    DSTL.xuatDanhSach();
                    break;

                case "6":
                    DSTMT.thongKeQuaHan();
                    break;

                case "7":
                    DSTMT.thongKeTaiLieuHongMat();
                    break;

                case "8":
                    DSPN.xuatDanhSach();
                    break;

                case "9":
                    check = false;
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại");
            }
            if(check) {
                System.out.println("Nhấn Enter để tiếp tục!");
                sc.nextLine();
            }
        }

        //Ghi file sau khi hoàn tất công việc
        try {
            DSTL.ghiFileDanhSach("DanhSachTaiLieu.txt");
            DSPN.ghiFileDanhSach("DanhSachPhieuNhap.txt");
            DSTMT.ghiFileDSTheMuon("DanhSachTheMuon.txt");
            DSTMT.ghiFileDSTheTra("DanhSachTheTra.txt");
            System.out.println("Đã cập nhật dữ liệu vào hệ thống!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
