package QLTV;

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class DanhSachDocGia implements ThaoTac {
    private DocGia[] DG;
    int n;//Biến số lượng đọc giả thực thế có trong danh sách
    Scanner sc = new Scanner(System.in);

    // CONSTRUCTOR
    public DanhSachDocGia() {
        DG = new DocGia[n];
    }

    // HÀM NHẬP DANH SÁCH
    public void nhapDanhSachDocGia() {
        //Nhập số lượng đọc giả muốn thêm
        int sl;
        do {
            try {
                System.out.println("Nhập số lượng đọc giả: ");
                sl = Integer.parseInt(sc.nextLine());
                if (sl > 0)
                    break;
                System.out.println("Số đọc giả không thể bé hơn hoặc bằng 0!");
            } catch (NumberFormatException nfe){
                System.out.println("Bạn vừa nhập không phải số! Vui lòng nhập 1 số!");
            }
        }while(true);

        //biến lưu số lượng đọc giả của mảng để duyệt vòng for
        int temp = n+sl;
        sl=temp-sl;
        //Tạo danh sách đọc giả nếu rỗng
        if(DG == null){
            DG = new DocGia[n];
        }else {
            //Ngược lại thì mở rộng vùng nhớ cho danh sách
            DG = Arrays.copyOf(DG, temp);
        }

        for (int i = sl; i < temp; i++) {
            System.out.print("\n\t\t\t\t\tĐỘC GIẢ THỨ " + (i + 1) + "\n");
            DG[i] = new DocGia();
            //Tạo đọc giả mới thì mới tăng n
            n++;
            DG[i].nhapMDG();
            DG[i].nhap();
            if (i > 0) {
                nhapLaiMaDG(DG[i].getMaDG(),i);
            }
        }
    }

    // HÀM XUẤT DANH SÁCH
    public void xuatDanhSachDocGia() {

        System.out.println("*====================================================================================================================================================================*");
        System.out.println("|                                                                          DANH SÁCH ĐỌC GIẢ                                                                         |");
        System.out.println("*====================================================================================================================================================================*");
        System.out.format("| %-8s | %-30s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n","MÃ ĐG","TÊN","GIỚI TÍNH","SỐ ĐT", "NGÀY SINH", "NGÀY LÀM THẺ", "NGÀY HẾT HẠN", "CHỨC VỤ", "ĐỊA CHỈ");
        System.out.println("*====================================================================================================================================================================*");
        try {
            for (int i = 0; i < n; i++) {
                DG[i].xuat();
            }
        } catch (NullPointerException npe) {

        }
        System.out.println("*====================================================================================================================================================================*");
    }

// ==============CÁC THAO TÁC TRÊN DANH SÁCH=================
    // THAY ĐỔI MÃ ĐỌC GIẢ NẾU BỊ TRÙNG
public void nhapLaiMaDG(String maDG, int index){
    if(checkMaDG(maDG,index))
    {
        System.out.println("Mã đọc giả bị trùng hoặc không hợp lệ! Vui lòng nhập lại!");
        do {
            System.out.print("Nhập mã đọc giả(Gồm 5 ký tự): ");
            maDG = sc.nextLine();
            if(maDG.length() == 5 && !checkMaDG(maDG,index)) {
                DG[index].setMaDG(maDG);
                System.out.println("Cập nhật mã đọc giả thành công!");
                break;
            }
            System.out.println("Độ dài của mã đọc giả không hợp lệ! Vui lòng nhập lại!");
        }while(true);
    }
}

    //KIỂM TRA MÃ ĐỌC GIẢ BẤT KỲ
    public DocGia checkMaDG(String maDG) {
        for (int i = 0; i < n; i++) {
            if (maDG.equals(DG[i].getMaDG())) {
                return DG[i];
            }
        }
        return null;
    }

    // KIỂM TRA MÃ ĐỌC GIẢ
    public boolean checkMaDG(String MaDG, int k) {
        for (int i = 0; i < n ; i++) {
            if (DG[i].getMaDG().equalsIgnoreCase(MaDG) && i != k) {
                return true;
            }
        }
        return false;
    }

    //HÀM TÌM KIẾM THEO MÃ ĐỌC GIẢ
    public void searchByMDG(String maDG) {
        System.out.println("*====================================================================================================================================================================*");
        System.out.println("|                                                                 =========KẾT QUẢ TÌM KIẾM=========                                                                 |");
        System.out.println("*====================================================================================================================================================================*");
        System.out.format("| %-8s | %-30s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n","MÃ ĐG","TÊN","GIỚI TÍNH","SỐ ĐT", "NGÀY SINH", "NGÀY LÀM THẺ", "NGÀY HẾT HẠN", "CHỨC VỤ", "ĐỊA CHỈ");
        for (int i = 0; i < n; i++) {
            if (maDG.equals(DG[i].getMaDG())) {
                DG[i].xuat();
                System.out.println("*====================================================================================================================================================================*");
                System.out.print("Nhấn Enter để tiếp tục!");
                sc.nextLine();
                return;
            }
        }
        System.out.println("| Không tìm thấy đọc giả!                                                                                                                                            |");
        System.out.println("*====================================================================================================================================================================*");
        System.out.print("Nhấn Enter để tiếp tục!");
        sc.nextLine();
    }

    // HÀM TÌM KIẾM THEO TÊN
    public void searchByName(String tenDG) {
        boolean flag = true;

        System.out.println("*====================================================================================================================================================================*");
        System.out.println("|                                                                 =========KẾT QUẢ TÌM KIẾM=========                                                                 |");
        System.out.println("*====================================================================================================================================================================*");
        System.out.format("| %-8s | %-30s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n","MÃ ĐG","TÊN","GIỚI TÍNH","SỐ ĐT", "NGÀY SINH", "NGÀY LÀM THẺ", "NGÀY HẾT HẠN", "CHỨC VỤ", "ĐỊA CHỈ");
        for (int i = 0; i < n; i++) {
            if (DG[i].getTen().toLowerCase().contains(tenDG.toLowerCase())) {
                flag = false;
                DG[i].xuat();
            }
        }
        if (flag) {
            System.out.println("| Không tìm thấy đọc giả!                                                                                                                                            |");
        }
        System.out.println("*====================================================================================================================================================================*");

        System.out.print("Nhấn Enter để tiếp tục!");
        sc.nextLine();
    }

    // LẬP THẺ ĐỌC GIẢ MỚI VÀO DANH SÁCH
    @Override
    public void Insert(String maDG) {
        DocGia dg = checkMaDG(maDG);
        if(dg == null){
            //Tạo danh sách đọc giả nếu rỗng
            if(DG == null){
                DG = new DocGia[1];
                n++;
            }else {
                DG = Arrays.copyOf(DG, ++n);
            }
            DG[n-1] = new DocGia();

            System.out.println("\n\nBắt đầu nhập thông tin đọc giả mới!");
            DG[n-1].setMaDG(maDG);
            DG[n-1].nhap();
            System.out.println("Cập nhật thành công!!!");
        }
        else{
            System.out.println("Đọc giả có mã \"" + maDG +"\" trùng với mã của đọc giả \"" + dg.getTen() + "\" trong thư viện!!");
        }
    }

    //XOÁ MỘT ĐỌC GIẢ TRONG DANH SÁCH
    @Override
    public void Delete(String maDG) {
        if(n <= 0){
            System.out.println("Hiện danh sách chưa có đọc giả nào!");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (maDG.equals(DG[i].getMaDG())) {
                //Giữ tên đọc giả lại
                String temp = DG[i].getTen();
                for (int j = i; j < n - 1; j++) {
                    DG[j] = DG[j+1];
                }
                if(n == 1){
                    DG = null;
                    n--;
                }else {
                    DG = Arrays.copyOf(DG, --n);
                }
                System.out.println("Đã xóa đọc giả \"" + temp +"\" khỏi danh sách!");
                return;
            }
        }
        System.out.println("Không tìm thấy đọc giả muốn xóa!");
    }

    // SỬA CHI TIẾT ĐỘC GIẢ
    @Override
    public void Adjust(String maDG) {
        if (n <= 0) {
            System.out.println("Hiện bạn chưa có đọc giả!");
        } else {
            boolean check = true;
            for (int i = 0; i < n; i++) {
                if (DG[i].getMaDG().equals(maDG)) {
                    System.out.println("Đã tìm thấy đọc giả bạn muốn!");
                    System.out.println("*====================================================================================================================================================================*");
                    System.out.format("| %-8s | %-30s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", "MÃ ĐG", "TÊN", "GIỚI TÍNH","Số ĐT", "NGÀY SINH", "NGÀY LÀM THẺ", "NGÀY HẾT HẠN", "CHỨC VỤ", "QUÊ QUÁN");
                    System.out.println("*====================================================================================================================================================================*");
                    DG[i].xuat();
                    System.out.println("*====================================================================================================================================================================*");
                    String thuocTinhMoi;
                    while (check) {
                        System.out.println("*========================================================*");
                        System.out.println("|            BẢNG THAO TÁC CHỈNH SỬA ĐỌC GIẢ             |");
                        System.out.println("*========================================================*");
                        System.out.println("|  1. Ấn 1 để sửa mã đọc giả.                            |");
                        System.out.println("|  2. Ấn 2 để sửa họ và tên đọc giả.                     |");
                        System.out.println("|  3. Ấn 3 để sửa giới tính đọc giả.                     |");
                        System.out.println("|  4. Ấn 4 để sửa số điện thoại đọc giả.                 |");
                        System.out.println("|  5. Ấn 5 để sửa ngày sinh đọc giả.                     |");
                        System.out.println("|  6. Ấn 6 để sửa ngày làm thẻ đọc giả.                  |");
                        System.out.println("|  7. Ấn 7 để sửa hết hạn thẻ đọc giả.                   |");
                        System.out.println("|  8. Ấn 8 để sửa chức vụ đọc giả.                       |");
                        System.out.println("|  9. Ấn 9 để sửa địa chỉ đọc giả.                       |");
                        System.out.println("|  10.Ấn 10 để sửa tất cả thông tin của đọc giả.         |");
                        System.out.println("|  11.Ấn 11 để Trở về.                                   |");
                        System.out.println("*========================================================*");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        String chose = sc.nextLine();
                        switch (chose) {

                            case "1":
                                do {
                                    System.out.print("Nhập vào mã đọc giả(Gồm 5 ký tự): ");
                                    thuocTinhMoi = sc.nextLine();
                                    if (thuocTinhMoi.length() != 5)
                                        System.out.println("Bạn đã nhập thừa hoặc thiếu kí tự");
                                } while (thuocTinhMoi.length() != 5);
                                nhapLaiMaDG(thuocTinhMoi,i);
                                break;

                            case "2":
                                System.out.println("Nhập tên mới của đọc giả: ");
                                DG[i].setTen(sc.nextLine());
                                break;

                            case "3":
                                do {
                                    System.out.print("Khi nhập giới tính, lưu ý chỉ cho phép nhập nam hay nữ!!!");
                                    System.out.print("Giới tính: ");
                                    thuocTinhMoi = sc.nextLine();
                                    if (!thuocTinhMoi.equalsIgnoreCase("Nam") && !thuocTinhMoi.equalsIgnoreCase("Nữ"))
                                        System.out.println("Bạn đã nhập sai giới tính!!!");
                                } while (!thuocTinhMoi.equalsIgnoreCase("Nam") && !thuocTinhMoi.equalsIgnoreCase("Nữ"));
                                DG[i].setGioitinh(thuocTinhMoi);
                                break;

                            case "4":
                                //NHẬP SỐ ĐIỆN THOẠI
                                boolean checkSDT;
                                do {
                                    checkSDT = false;
                                    System.out.print("Nhập số điện thoại của đọc giả (Chú ý sdt chỉ từ 10-11 số): ");
                                    try{
                                        thuocTinhMoi = sc.nextLine();
                                        long temp = Long.parseLong(thuocTinhMoi);
                                        if(thuocTinhMoi.length() < 10 || thuocTinhMoi.length() > 11){
                                            checkSDT = true;
                                            System.out.println("Số điện thoại có chiều dài không hợp lệ!");
                                        }
                                    } catch(NumberFormatException nfe){
                                        System.out.println("Số điện thoại không hợp lệ!");
                                        checkSDT = true;
                                    }
                                }while(checkSDT);
                                break;

                            case "5":
                                System.out.println("\nNgày tháng năm sinh của độc giả phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
                                do {
                                    System.out.print("Ngày tháng năm sinh: ");
                                    thuocTinhMoi = sc.nextLine();
                                    if (!DG[i].checkNgayTheoFormat(thuocTinhMoi)) {
                                        System.out.println("\nNgày tháng năm sinh không hợp lệ. Xin mời nhập lại!!!");
                                    }
                                } while (!DG[i].checkNgayTheoFormat(thuocTinhMoi));
                                DG[i].setNgaysinh(thuocTinhMoi);
                                break;

                            case "6":
                                System.out.println("\nNgày làm thẻ của độc giả phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
                                do {
                                    System.out.print("Ngày làm thẻ: ");
                                    thuocTinhMoi = sc.nextLine();
                                    if (!DG[i].checkNgayTheoFormat(thuocTinhMoi)) {
                                        System.out.println("\nNgày làm thẻ không hợp lệ. Xin mời nhập lại!!!");
                                    }
                                } while (!DG[i].checkNgayTheoFormat(thuocTinhMoi));
                                DG[i].setNgayLamThe(thuocTinhMoi);
                                break;

                            case "7":
                                do {
                                    System.out.println("\nNgày hết hạn của thẻ độc giả phải hợp lệ theo cú pháp dd/MM/yyyy:");
                                    thuocTinhMoi = sc.nextLine();
                                    if (!DG[i].checkNgayTheoFormat(thuocTinhMoi)) {
                                        System.out.println("\nNgày làm thẻ không hợp lệ. Xin mời nhập lại!!!");
                                    }
                                } while (!DG[i].checkNgayTheoFormat(thuocTinhMoi));
                                DG[i].setNgayHetHan(thuocTinhMoi);
                                break;

                            case "8":
                                System.out.println("Nhập chức vụ mới: ");
                                DG[i].setChucVu(sc.nextLine());
                                break;

                            case "9":
                                System.out.println("Nhập địa chỉ mới: ");
                                DG[i].setDiachi(sc.nextLine());
                                break;
                            case "10":
                                DG[i].nhap();
                                break;
                            case "11":
                                check = false; break;
                            default:
                                System.out.print("Lựa chọn không hợp lệ! Xin mời nhập lại!!!");
                        }
                        if(!check) {
                            System.out.println("Cập nhật hoàn tất! Thông tin đọc giả sau khi chỉnh sửa:\n");
                            System.out.println("*====================================================================================================================================================================*");
                            System.out.format("| %-8s | %-30s | %-10s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", "MÃ ĐG", "TÊN", "GIỚI TÍNH", "SỐ ĐT", "NGÀY SINH", "NGÀY LÀM THẺ", "NGÀY HẾT HẠN", "CHỨC VỤ", "ĐỊA CHỈ");
                            System.out.println("*====================================================================================================================================================================*");
                            DG[i].xuat();
                            System.out.println("*====================================================================================================================================================================*");
                        } else {
                            System.out.println("Đã cập nhật!");
                        }
                    }
                    break;
                }
            }
            if(check) {
                System.out.println("Không tìm thấy đọc giả!");
            }
        }
    }

    // LỰA CHỌN CÁCH TÌM KIẾM
    public void Search() {
        String select;
        while (true) {
            System.out.println("*=====================================================*");
            System.out.println("|               BẢNG THAO TÁC TÌM KIẾM                |");
            System.out.println("*=====================================================*");
            System.out.println("|  1.Ấn phím 1 để tìm kiếm độc giả theo mã.           |");
            System.out.println("|  2.Ấn phím 2 để tìm kiếm độc giả theo tên.          |");
            System.out.println("|  3.Ấn phím 3 để thoát.                              |");
            System.out.println("*=====================================================*");
            System.out.print("Lựa chọn của bạn: ");
            select = sc.nextLine();
            switch (select) {
                case "1":
                    System.out.print("Nhập vào mã độc giả cần tìm: ");
                    String MaDG = sc.nextLine();
                    searchByMDG(MaDG);
                    break;
                case "2":
                    System.out.print("Nhập vào tên mà bạn muốn tìm: ");
                    String ten = sc.nextLine();
                    searchByName(ten);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Bạn đã nhập sai lựa chọn của mình. Xin mời vào lại chức năng!!!");
            }
        }
    }

    // GHI FILE
    @Override
    public void ghiFileDanhSach(String filename) throws FileNotFoundException{
        PrintWriter writeFile = new PrintWriter(new File(filename));
        try {
            for (int i = 0; i < n; i++) {
                DG[i].ghiFile(writeFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeFile.close();
        }
    }

    // ĐỌC FILE
    @Override
    public void docFileDanhSach(String filename) throws FileNotFoundException{
        Scanner readFile = null;
        try {
            readFile = new Scanner(new File(filename));
            while (readFile.hasNext()) {
                //Tạo danh sách đọc giả nếu rỗng
                if(DG == null){
                    DG = new DocGia[1];
                    n++;
                }else {
                    //Ngược lại thì mở rộng vùng nhớ cho danh sách
                    DG = Arrays.copyOf(DG, ++n);
                }
                DG[n-1] = new DocGia();
                DG[n-1].docFile(readFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            readFile.close();
        }
    }
}
