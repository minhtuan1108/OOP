package QLTV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Scanner;

public class DanhSachPhieuNhapTaiLieu {
	
	Scanner sc = new Scanner(System.in);
	private ChiTietPhieuNhapTaiLieu[] PNTL;
    private DanhSachTaiLieu DSTL;
	private int n;
	
	public DanhSachPhieuNhapTaiLieu(){
		
	}

    public DanhSachPhieuNhapTaiLieu(DanhSachTaiLieu DS){
        this.DSTL = DS;
    }

	//PHƯƠNG THỨC NHẬP DANH SÁCH PHIẾU NHẬP TÀI LIỆU
	public void nhapDanhSach() {
        int sl;
        do {
            System.out.println("Nhập số phiếu nhập: ");
            sl = Integer.parseInt(sc.nextLine());
            if(n>0)
                break;
            System.out.println("Số phiếu nhập không thể bé hơn hoặc bằng 0!");
        }while(true);
        n+=sl;
        sl=n-sl;
        if(PNTL == null){
            PNTL = new ChiTietPhieuNhapTaiLieu[n];
        }else {
            PNTL = Arrays.copyOf(PNTL, n);
        }
        for (int i = sl; i < n; i++) {
            PNTL[i] = new ChiTietPhieuNhapTaiLieu();
            PNTL[i].nhapMPN();
            PNTL[i].nhapPN();
            if(i>0)
            	nhapLaiMaphieuTL(PNTL[i].getMaPN(),i);
        }
	}
	//PHƯƠNG THỨC XUẤT HAY THỐNG KÊ DANH SÁCH TÀI LIỆU
    public void xuatDanhSach() {
        System.out.println("*================================================================================================================================================================*");
        System.out.println("|                                                                       DANH SÁCH PHIẾU NHẬP TÀI LIỆU                                                            |");
        System.out.println("*================================================================================================================================================================*");
        System.out.printf("| %-8s | %-8s | %-30s | %-15s | %-20s | %-20s | %-15s | %8s | %10s |\n","Mã PN","Mã TL","Tên tài liệu","Ngày nhập","Tác giả","Nhà xuất bản","Thể loại","SL Nhập","Giá");
        System.out.println("|================================================================================================================================================================|");
        for (int i = 0; i < n; i++) {
            PNTL[i].xuat();
        }
        System.out.println("*================================================================================================================================================================*\n\n");
    }

  //KIỂM TRA XEM MÃ PHIẾU NHẬP TÀI LIỆU CÓ TRÙNG KHÔNG
    //KIỂM TRA MÃ PHIẾU VỪA NHẬP VÀO DANH SÁCH
    public boolean checkMaPNTL(String maphieuTL,int k) {
        for (int i = 0; i < n; i++) {
            if (maphieuTL.equals(PNTL[i].getMaPN()) && i!=k) {
                return true;
            }
        }
        return false;
    }

    //KIỂM TRA SỰ TỒN TẠI CỦA MỘT MÃ PHIẾU NHẬP TÀI LIỆU BẤT KÌ
    public ChiTietPhieuNhapTaiLieu checkMaPNTL(String maphieuTL) {
        for (int i = 0; i < n; i++) {
            if (maphieuTL.equals(PNTL[i].getMaPN())) {
                return PNTL[i];
            }
        }
        return null;
    }

    //PHƯƠNG THỨC NHẬP LẠI MÃ PHIẾU TÀI LIỆU TRÙNG
    public void nhapLaiMaphieuTL(String maphieuNTL, int index){
        if(checkMaPNTL(maphieuNTL,index))
        {
            System.out.println("Mã phiếu nhập tài liệu bị trùng hoặc không hợp lệ! Vui lòng nhập lại!");
            do {
                System.out.print("Nhập mã phiếu nhập mới(Gồm 5 ký tự): ");
                maphieuNTL = sc.nextLine();
                if(maphieuNTL.length() == 5 && !checkMaPNTL(maphieuNTL,index)) {
                    PNTL[index].setMaPN(maphieuNTL);
                    System.out.println("Cập nhật mã phiếu nhập tài liệu thành công!");
                    break;
                }
                System.out.println("Độ dài của mã phiếu nhập tài liệu không hợp lệ!Vui lòng nhập lại!");
            }while(true);
        }
    }

    //NHẬP TÀI LIỆU
    public void Insert(String maphieuTL) {

        //Nếu mà phiếu nhập đang rỗng thì tạo phiếu nhập đầu tiên cho DSPN
        if(PNTL == null){
            PNTL = new ChiTietPhieuNhapTaiLieu[1];
            n++;
        }else {
            //Tạo thêm 1 phiếu nhập tài liệu
            PNTL = Arrays.copyOf(PNTL, ++n);
        }

        PNTL[n - 1] = new ChiTietPhieuNhapTaiLieu();
        //Nhập mã tài liệu để kiểm tra xem tài liệu có trong thư viện chưa
        String maTL,tenTL, tacGia, NXB, theLoai, ngayNhap;
        int gia = 0, slnhap = 0;
        do {
            System.out.print("Nhập mã tài liệu(Gồm 5 ký tự): ");
            maTL = sc.nextLine();
            if(maTL.length() == 5 )
                break;
            System.out.println("Độ dài của mã tài liệu không hợp lệ !Vui lòng nhập lại!");
        }while(true);

        //Dù tài liệu đã tồn tại hay chưa cũng phải nhập số lượng nhập và ngày nhập
        //NHÂP NGÀY NHẬP
        System.out.println("\nNgày nhập tài liệu phải hợp lệ theo cú pháp dd/MM/yyyy. Nếu không sẽ báo lỗi");
        do {
            System.out.print("Ngày nhâp tài liệu: ");
            ngayNhap = sc.nextLine();
            if (!PNTL[n-1].checkNgayTheoFormat(ngayNhap)) {
                System.out.print("\nNgày nhập không hợp lệ. Xin mời nhập lại!!!");
                System.out.println();
            }
        } while (!PNTL[n-1].checkNgayTheoFormat(ngayNhap));

        //NHẬP SỐ LƯỢNG NHẬP
        do{
            System.out.println("Nhập số lượng tài liệu nhập về:");
            try {
                slnhap = Integer.parseInt(sc.nextLine());
                if(slnhap < 0){
                    System.out.println("Số lượng nhập về không thể bé hơn 0!");
                }
            }catch (NumberFormatException nfe){
                System.out.println("Vui lòng nhập số lượng (một số)!");
            }
        }while(slnhap<0);

        //Nếu tài liệu đã tồn tại trong thư viện thì thay đổi số lượng của tài liệu đó
        TaiLieu temp = DSTL.checkMaTL(maTL);
        if(temp != null){

            //Set lại các giá trị cơ bản của 1 phiếu nhập
            PNTL[n-1].setMaPN(maphieuTL);
            PNTL[n-1].setSlnhap(slnhap);
            PNTL[n-1].setNgayNhap(ngayNhap);

            //Lấy thông tin của tài liệu đã có sẵn cho phiếu nhập
            PNTL[n-1].getInfoDocument(temp);

            //set số lượng của tài liệu trong danh sách tài liệu
            temp.setSlCo(temp.getSlco() + slnhap);
            temp.setSlCon(temp.getSlcon() + slnhap);

        }else {
            //Nếu không có thì cho người dùng nhập thông tin tài liệu mới
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

            //Tạo một Tài liệu mới cho DSTL
            TaiLieu doc = new TaiLieu(maTL,tenTL,tacGia,NXB,theLoai,slnhap,gia);
            DSTL.addTaiLieu(doc);

            //set thông tin về tài liệu cho phiếu nhập
            PNTL[n-1] = new ChiTietPhieuNhapTaiLieu(maphieuTL,slnhap,ngayNhap,maTL,tenTL,tacGia,NXB,theLoai,gia);
        }
    }

	public void docFileDanhSach(String filename) throws FileNotFoundException {
        Scanner readFile = null;
        try {
            readFile = new Scanner(new File(filename));
            while (readFile.hasNext()) {
                //Mở rộng vùng nhớ
                if(PNTL == null){
                    PNTL = new ChiTietPhieuNhapTaiLieu[1];
                    n++;
                }else {
                    PNTL = Arrays.copyOf(PNTL, ++n);
                }
                PNTL[n-1] = new ChiTietPhieuNhapTaiLieu();
                PNTL[n-1].docFilePhieuNhap(readFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            readFile.close();
        }
	}

	public void ghiFileDanhSach(String filename) throws FileNotFoundException {
        PrintWriter writeFile = new PrintWriter(new File(filename));
        try {
            for (int i = 0; i < n; i++) {
                PNTL[i].ghiFilePhieuNhap(writeFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeFile.close();
        }
	}
}



		
		
	

