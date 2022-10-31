
package QLTV;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachTaiLieu implements ThaoTac{

    Scanner sc = new Scanner(System.in);
    private TaiLieu[] TL;
    int n;//Biến lưu số lượng tài liệu thật trong danh sách

    public DanhSachTaiLieu() {

    }

    //PHƯƠNG THỨC NHẬP DANH SÁCH TÀI LIỆU
    public void nhapDanhSach() {
        int sl;
        do {
            System.out.println("Nhập số tài liệu: ");
            sl = Integer.parseInt(sc.nextLine());
            if(n>0)
                break;
            System.out.println("Số tài liệu không thể bé hơn hoặc bằng 0!");
        }while(true);

        int temp = n + sl;
        sl = temp - sl;
        //Tạo danh sách đọc giả nếu rỗng
        if(TL == null){
            TL = new TaiLieu[n];
        }else {
            //Ngược lại mở rộng vùng nhớ cho danh sách
            TL = Arrays.copyOf(TL, n);
        }
        for (int i = sl; i < temp; i++) {
            TL[i] = new TaiLieu();
            n++;
            TL[i].nhap();
            if(i>0)
                nhapLaiMaTL(TL[i].getMaTL(),i);
        }
    }

    //PHƯƠNG THỨC XUẤT HAY THỐNG KÊ DANH SÁCH TÀI LIỆU
    public void xuatDanhSach() {
        System.out.println("*================================================================================================================================================*");
        System.out.println("|                                                               DANH SÁCH TÀI LIỆU                                                               |");
        System.out.println("|================================================================================================================================================|");
        System.out.printf("| %-8s | %-30s | %-25s | %-25s | %-15s | %8s | %5s | %5s |\n","Mã TL","Tên tài liệu","Tác giả","Nhà xuất bản","Thể loại","Giá","Có","Còn");
        System.out.println("|================================================================================================================================================|");
        for (int i = 0; i < n; i++) {
            TL[i].xuat();
        }
        System.out.println("*================================================================================================================================================*\n\n");
    }

//KIỂM TRA XEM MÃ TÀI LIỆU CÓ TRÙNG KHÔNG
    //KIỂM TRA MÃ VỪA NHẬP VÀO DANH SÁCH
    public boolean checkMaTL(String maTL,int k) {
        for (int i = 0; i < n; i++) {
            if (maTL.equals(TL[i].getMaTL()) && i!=k) {
                return true;
            }
        }
        return false;
    }

    //KIỂM TRA SỰ TỒN TẠI CỦA MỘT MÃ BẤT KÌ
    public TaiLieu checkMaTL(String maTL) {
        for (int i = 0; i < n; i++) {
            if (maTL.equals(TL[i].getMaTL())) {
                return TL[i];
            }
        }
        return null;
    }

    //PHƯƠNG THỨC NHẬP LẠI MÃ TÀI LIỆU TRÙNG
    public void nhapLaiMaTL(String maTL, int index){
        if(checkMaTL(maTL,index))
        {
            System.out.println("Mã tài liệu bị trùng hoặc không hợp lệ! Vui lòng nhập lại!");
            do {
                System.out.print("Nhập mã tài liệu(Gồm 5 ký tự): ");
                maTL = sc.nextLine();
                if(maTL.length() == 5 && !checkMaTL(maTL, index)) {
                    TL[index].setMaTL(maTL);
                    System.out.println("Cập nhật mã tài liệu thành công!");
                    break;
                }
                System.out.println("Độ dài của mã tài liệu không hợp lệ! Vui lòng nhập lại!");
            }while(true);
        }
    }

//CÁC PHƯƠNG THỨC THAO TÁC TRÊN DANH SÁCH
    //THAY ĐỔI SỐ LƯỢNG TÀI LIỆU CÒN (HỖ TRỢ MƯỢN TRẢ TÀI LIỆU)
    public void thayDoiSLCon(TaiLieu tl, int sl, String status) {
        if(status.equalsIgnoreCase("mượn"))
            tl.setSlCon(tl.getSlcon() - sl);
        else {
            if(status.equalsIgnoreCase("trả"))
                tl.setSlCon(tl.getSlcon() + sl);
            else System.out.println("Trạng thái: \"" +status+"\"không được chấp nhận!");
        }
    }

    //NHẬP THÊM TÀI LIỆU MỚI VÀO DANH SÁCH
    @Override
    public void Insert(String maTL) {
        TaiLieu tl = checkMaTL(maTL);
        if(tl == null){
            if(TL == null){
                TL = new TaiLieu[1];
                n++;
            }else {
                TL = Arrays.copyOf(TL, ++n);
            }
            TL[n-1] = new TaiLieu();
            TL[n-1].nhap2();//Nhập thông tin tài liệu
            //Nhập số lượng nhập về
            int slNhap;
            do {
                System.out.println("Nhâp số lượng tài liệu mới nhập về: ");
                slNhap = sc.nextInt();
                if(slNhap < 0){
                    System.out.println("Số lượng tài liệu không thể bé hơn 0!");
                }
            }while (slNhap < 0);

            TL[n-1].setSlCo(slNhap);
            TL[n-1].setSlCon(slNhap);
        }
        else{
            System.out.println("Tài liệu có mã \"" + maTL +"\" có tên là \"" + tl.getTenTL() + "\" trong thư viện!!");
            int slNhap;
            do {
                System.out.println("Nhâp số lượng tài liệu mới nhập về: ");
                slNhap = sc.nextInt();
                if(slNhap < 0){
                    System.out.println("Số lượng tài liệu không thể bé hơn 0!");
                }
            }while (slNhap < 0);
            tl.setSlCo(tl.getSlco() + slNhap);
            tl.setSlCon(tl.getSlcon() + slNhap);
        }
        System.out.println("Cập nhật thành công!!!");
        System.out.println("\nNhấn Enter để tiếp tục!");
        sc.nextLine();
    }

    //Thêm một đối tượng tài liệu mới vào DSTL
    public void addTaiLieu(TaiLieu doc){
        if(TL == null){
            TL = new TaiLieu[1];
            n++;
        }else {
            TL = Arrays.copyOf(TL, ++n);
        }
        TL[n-1] = doc;
        System.out.println("Đã thêm tài liệu thành công!");
    }

    //XÓA TÀI LIỆU BẰNG MÃ TÀI LIỆU
    @Override
    public void Delete(String MaTL) {
        if(n <= 0){
            System.out.println("Hiện chưa có tài liệu nào!");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (MaTL.equals(TL[i].getMaTL())) {
                String temp = TL[i].getTenTL();
                for (int j = i; j < n - 1; j++) {
                    TL[j] = TL[j+1];
                }
                //Nếu tài liệu chỉ có 1 thì ta sẽ xóa danh sách
                if(n == 1){
                    TL = null;
                    n--;
                }else {
                    TL = Arrays.copyOf(TL, --n);
                }
                System.out.println("Đã xóa tài liệu \"" + temp +"\" khỏi danh sách!");
                return;
            }
        }
        System.out.println("Không tìm thấy tài liệu muốn xóa!");
    }

    //PHƯƠNG THỨC CHỈNH SỬA THÔNG TIN TÀI LIỆU
    @Override
    public void Adjust(String maTL){
        if (n <= 0) {
            System.out.println("Hiện tại bạn chưa có tài liệu!");
            System.out.println("\nNhấn Enter để tiếp tục!");
            sc.nextLine();
        } else {
            String chose;
            String tenmoi;
            int somoi = 0;
            boolean check = true;
            boolean flag;
            for (int i = 0; i < n; i++) {
                if (maTL.equals(TL[i].getMaTL())) {
                    System.out.println("Đã tìm thấy tài liệu của bạn: ");
                    System.out.printf("| %-8s | %-30s | %-25s | %-25s | %-15s | %8s | %5s | %5s |\n","Mã TL","Tên tài liệu","Tác giả","Nhà xuất bản","Thể loại","Giá","Có","Còn");
                    System.out.println("*================================================================================================================================================*");
                    TL[i].xuat();
                    System.out.println("*================================================================================================================================================*");
                    System.out.println("Nhấn Enter để tiếp tục!");
                    sc.nextLine();
                    while(check) {
                        System.out.println("*=======================================================*");
                        System.out.println("|               BẢNG CÁC THAO TÁC CHỈNH SỬA             |");
                        System.out.println("*=======================================================*");
                        System.out.println("| 1. Nhập 1 để chỉnh sửa mã tài liệu.                   |");
                        System.out.println("| 2. Nhập 2 để chỉnh sửa tên tài liệu.                  |");
                        System.out.println("| 3. Nhập 3 để chỉnh sửa tác giả.                       |");
                        System.out.println("| 4. Nhập 4 để chỉnh sửa nhà xuất bản.                  |");
                        System.out.println("| 5. Nhập 5 để chỉnh sửa thể loại.                      |");
                        System.out.println("| 6. Nhập 6 để chỉnh sửa giá.                           |");
                        System.out.println("| 7. Nhập 7 để chỉnh sửa số lượng.                      |");
                        System.out.println("| 8. Nhập 8 để chỉnh sửa số lượng còn.                  |");
                        System.out.println("| 9. Nhập 9 để trở về                                   |");
                        System.out.println("*=======================================================*");
                        System.out.print("Nhập lựa chọn của bạn: ");
                        chose = sc.nextLine();

                        switch(chose)
                        {
                            case "1":
                                do {
                                    System.out.print("Nhập mã tài liệu(Gồm 5 ký tự): ");
                                    tenmoi = sc.nextLine();
                                    if(tenmoi.length() == 5)
                                        break;
                                    System.out.println("Độ dài của mã tài liệu không hợp lệ!Vui lòng nhập lại!");
                                }while(true);
                                nhapLaiMaTL(tenmoi,i);
                                break;

                            case "2":
                                System.out.println("Nhập tên tài liệu mới: ");
                                tenmoi = sc.nextLine();
                                TL[i].setTenTL(tenmoi);
                                break;

                            case "3":
                                System.out.println("Nhập tên tác giả mới: ");
                                tenmoi = sc.nextLine();
                                TL[i].setTacGia(tenmoi);
                                break;

                            case "4":
                                System.out.println("Nhập tên nhà xuất bản mới: ");
                                tenmoi = sc.nextLine();
                                TL[i].setNXB(tenmoi);
                                break;

                            case "5":
                                System.out.println("Nhập tên thể loại mới: ");
                                tenmoi = sc.nextLine();
                                TL[i].setTheLoai(tenmoi);
                                break;

                            case "6":
                                do {
                                    try {
                                        flag = false;
                                        System.out.print("Nhập giá mới của tài liệu(>0): ");
                                        somoi = Integer.parseInt(sc.nextLine());
                                        if(somoi < 0) {
                                            flag = true;
                                            System.out.println("Giá tài liệu không được bé hơn 0!");
                                        }
                                    }catch(NumberFormatException e) {
                                        flag = true;
                                        System.out.println("Vui lòng nhập một số!");
                                    }
                                } while(flag);
                                TL[i].setGia(somoi);
                                break;
                            case "7":
                                do {
                                    try {
                                        flag = false;
                                        System.out.print("Nhập số lượng mới của tài liệu(>0): ");
                                        somoi = Integer.parseInt(sc.nextLine());
                                        if(somoi < 0) {
                                            flag = true;
                                            System.out.println("Số lượng tài liệu không được bé hơn 0!");
                                        }
                                    }catch(NumberFormatException e) {
                                        flag = true;
                                        System.out.println("Vui lòng nhập một số!");
                                    }
                                } while(flag);
                                TL[i].setSlCo(somoi);
                                break;
                            case "8":
                                do {
                                    try {
                                        flag = false;
                                        System.out.print("Nhập số lượng còn lại mới của tài liệu(>0): ");
                                        somoi = Integer.parseInt(sc.nextLine());
                                        if(somoi < 0) {
                                            flag = true;
                                            System.out.println("Số lượng còn lại của tài liệu không được bé hơn 0!");
                                        }
                                    }catch(NumberFormatException e) {
                                        flag = true;
                                        System.out.println("Vui lòng nhập một số!");
                                    }
                                } while(flag);
                                TL[i].setSlCon(somoi);
                                break;
                            case "9": check = false; break;
                            default: System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại!");
                        }
                        if(!check) {
                            System.out.println("Cập nhật hoàn tất! Tài liệu sau khi chỉnh sửa:\n");
                            System.out.printf("| %-8s | %-30s | %-25s | %-25s | %-15s | %8s | %5s | %5s |\n", "Mã TL", "Tên tài liệu", "Tác giả", "Nhà xuất bản", "Thể loại", "Giá", "Có", "Còn");
                            System.out.println("*================================================================================================================================================*");
                            TL[i].xuat();
                            System.out.println("*================================================================================================================================================*");
                            System.out.println("Nhấn Enter để tiếp tục!");
                            sc.nextLine();
                        }
                    }
                    break;
                }
            }
            if(check) {
                System.out.println("Không tìm thấy tài liệu!");
            }
        }
    }

    //CÁC PHƯƠNG THỨC TÌM KIẾM
    //TÌM KIẾM THEO MÃ TÀI LIỆU
    public void searchByMTL(String maTL) {
        System.out.println("*================================================================================================================================================*");
        System.out.println("|                                                       =========KẾT QUẢ TÌM KIẾM=========                                                       |");
        System.out.println("*================================================================================================================================================*");
        System.out.printf("| %-8s | %-30s | %-25s | %-25s | %-15s | %8s | %5s | %5s |\n","Mã TL","Tên tài liệu","Tác giả","Nhà xuất bản","Thể loại","Giá","Có","Còn");
        for (int i = 0; i < n; i++) {
            if (maTL.equals(TL[i].getMaTL())) {
                TL[i].xuat();
                System.out.println("*================================================================================================================================================*\n\n");
                return;
            }
        }
        System.out.println("|Không tìm thấy tài liệu!                                                                                                                        |");
        System.out.println("*================================================================================================================================================*\n\n");
    }

    //TÌM KIẾM THEO TÊN TÀI LIỆU
    public void searchByName(String tenTL) {
        boolean flag = true;
        System.out.println("*================================================================================================================================================*");
        System.out.println("|                                                       =========KẾT QUẢ TÌM KIẾM=========                                                       |");
        System.out.println("*================================================================================================================================================*");
        System.out.printf("| %-8s | %-30s | %-25s | %-25s | %-15s | %8s | %5s | %5s |\n","Mã TL","Tên tài liệu","Tác giả","Nhà xuất bản","Thể loại","Giá","Có","Còn");
        for (int i = 0; i < n; i++) {
            if (TL[i].getTenTL().toLowerCase().contains(tenTL.toLowerCase())) {
                flag = false;
                TL[i].xuat();
            }
        }
        if (flag) {
            System.out.println("|Không tìm thấy tài liệu!                                                                                                                        |");
        }
        System.out.println("*================================================================================================================================================*\n\n");
    }

    //PHƯƠNG THỨC LỰA CHỌN TÌM KIẾM
    @Override
    public void Search(){
        boolean check = true;
        String chose;
        while(check)
        {
            System.out.println("*=======================================================*");
            System.out.println("|           BẢNG CÁC THAO TÁC TÌM KIẾM TÀI LIỆU         |");
            System.out.println("*=======================================================*");
            System.out.println("|  1. Ấn phím 1 để tìm kiếm bằng mã tài liệu.           |");
            System.out.println("|  2. Ấn phím 2 để tìm kiếm bằng tên tài liệu.          |");
            System.out.println("|  3. Ấn phím 3 để thoát khỏi phần tìm kiếm.            |");
            System.out.println("*=======================================================*\n\n");
            System.out.print("Nhập lựa chọn của bạn: ");
            chose = sc.nextLine();
            switch (chose) {
                case "1":
                    System.out.print("Nhập mã tài liệu(Gồm 5 ký tự): ");
                    searchByMTL(sc.nextLine());
                    break;
                case "2":
                    System.out.print("Nhập tên tài liệu: ");
                    searchByName(sc.nextLine());
                    break;
                case "3": check = false; break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
            if(check) {
                System.out.print("\nNhấn Enter để tiếp tục!");
                sc.nextLine();
            }
        }
    }
//HOÀN TẤT CÁC THAO TÁC TRÊN DACH SÁCH TÀI LIỆU

//THAO TÁC ĐỌC GHI FILE
    //ĐỌC FILE
    @Override
    public void docFileDanhSach(String filename) throws FileNotFoundException{
        Scanner readFile = null;
        try {
            readFile = new Scanner(new File(filename));
            while (readFile.hasNext()) {
                //Tạo danh sách nếu rỗng
                if(TL == null){
                    TL = new TaiLieu[1];
                    n++;
                }else {
                    //Ngược lại mở rộng vùng nhớ cho danh sách
                    TL = Arrays.copyOf(TL, ++n);
                }
                TL[n-1] = new TaiLieu();
                TL[n-1].docFile(readFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            readFile.close();
        }
    }

    @Override
    //GHI FILE
    public void ghiFileDanhSach(String filename) throws FileNotFoundException {
        PrintWriter writeFile = new PrintWriter(new File(filename));
        try {
            for (int i = 0; i < n; i++) {
                TL[i].ghiFile(writeFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeFile.close();
        }
    }
}
