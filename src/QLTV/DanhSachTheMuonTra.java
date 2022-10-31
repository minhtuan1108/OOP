package QLTV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class DanhSachTheMuonTra {
    SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
    Scanner sc = new Scanner(System.in);
    private ChiTietTheMuonTra[] DSTM;//Danh sách thẻ mượn
    private ChiTietTheMuonTra[] DSTT;//Danh sách thẻ trả
    private int n1, n2;//lần lượt làm chiều dài thực cho mảng DSTM, DSTT
    private DanhSachTaiLieu DSTL;
    private DanhSachDocGia DSDG;

    DanhSachTheMuonTra(){

    }

    DanhSachTheMuonTra(DanhSachTaiLieu dstl, DanhSachDocGia dsdg){
        DSDG = dsdg;
        DSTL = dstl;
    }

    //KIỂM TRA THẺ MƯỢN THÔNG QUA MÃ THẺ
    public ChiTietTheMuonTra checkMaThe(String maThe){
        for(int i = 0; i < n1;i++){
            if(DSTM[i].getMaThe().equals(maThe))
                return DSTM[i];
        }
        return null;
    }

    //KIỂM TRA TÍNH ĐÚNG ĐẮN CỦA MÃ THẺ
    public boolean checkMaThe(String maThe, int k){
        for(int i = 0; i < n1;i++){
            if(DSTM[i].getMaThe().equals(maThe) && i!=k)
                return true;
        }
        return false;
    }

    //KIỂM TRA MÃ ĐỌC GIẢ CÓ TRÙNG VỚI CÁC MÃ TRONG DANH SÁCH MƯỢN KHÔNG
    public boolean checkMaDGTrung(String maDG){
        //Vì đã tạo phần tử mới trong DSTM nên duyệt chỉ n1 -1 phần tử
        for(int i = 0; i < n1 - 1; i++){
            if(DSTM[i].getMaDG().equals(maDG))
                return true;
        }
        return false;
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

    //SO SÁNH NGÀY MƯỢN BÉ HƠN NGÀY TRẢ
    public boolean isALessThanB(String ngayMuon, String hanTra){
        Date a, b;
        try {
            a = day.parse(ngayMuon);
            b = day.parse(hanTra);
            if(a.compareTo(b) >= 0)
                return false;
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //SO SÁNH NGÀY TRẢ VỚI NGÀY MƯỢN VÀ HẠN TRẢ
    //NẾU A BÉ HƠN HAY BẰNG B THÌ TRẢ VỀ TRUE
    public boolean isBEqualOrThanA(String ngayTruoc, String ngaySau){
        Date a, b;
        try {
            a = day.parse(ngayTruoc);
            b = day.parse(ngaySau);
            if(a.compareTo(b) > 0)
                return false;
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //IN CÁC THẺ MƯỢN BẰNG MÃ THẺ
    public void printTM(String maThe){
        System.out.println("*==================================================================================================================*");
        System.out.println("|                                    DANH SÁCH CÁC TÀI LIỆU MÀ ĐỌC GIẢ NÀY MƯỢN                                    |");
        System.out.println("*==================================================================================================================*");
        for(int i = 0; i < n1; i++){
            if(DSTM[i].getMaThe().equals(maThe)){
                DSTM[i].xuatTheMuon();
            }
        }
        System.out.println("*==================================================================================================================*");
    }

    //LẤY THẺ MƯỢN VỚI MÃ THẺ VÀ MÃ TÀI LIỆU
    public ChiTietTheMuonTra getCTTM(String maThe, String maTL){
        for(int i = 0; i < n1; i++){
            if(DSTM[i].getMaThe().equals(maThe) && DSTM[i].getMaTL().equals(maTL)){
                return DSTM[i];
            }
        }
        return null;
    }

    //CHO MƯỢN TÀI LIỆU
    public void choMuonTaiLieu() {
        //KIỂM TRA TÀI LIỆU
        if(DSTL.n <= 0){
            System.out.println("Hiện tại chưa có tài liệu!");
            return;
        }

        //KIỂM TRA ĐỌC GIẢ
        if(DSDG.n <= 0){
            System.out.println("Hiện tại chưa có đọc giả! Vui lòng lập thẻ đọc giả trước khi cho mượn tài liệu!");
            return;
        }
        String maThe;
        boolean flag;
        do {
            System.out.print("Nhập mã thẻ mượn (có 5 ký tự): ");
            maThe = sc.nextLine();
            flag = (maThe.length() != 5);
            if (flag)
                System.out.println("Độ dài mã thẻ không hợp lệ! Vui lòng nhập lại");
        } while (flag);

        //KIỂM TRA MÃ THẺ
        ChiTietTheMuonTra theMuon = checkMaThe(maThe);

        //Nếu danh sách thẻ mượn rỗng thì tại một đối tượng mới cho danh sách
        if (DSTM == null) {
            DSTM = new ChiTietTheMuonTra[1];
            n1++;
        } else DSTM = Arrays.copyOf(DSTM, ++n1);//Ngược lại thì mở rộng 1 ô nhớ cho danh sách
        DSTM[n1-1] = new ChiTietTheMuonTra();

        //NẾU MÃ THẺ ĐÃ CÓ TRONG DANH SÁCH THẺ MƯỢN THÌ KHÔNG CẦN NHẬP MÃ ĐỌC GIẢ
        if (theMuon != null) {
            System.out.println("Thẻ \"" + maThe + "\" là của đọc giả \"" + theMuon.getTenDG() + "\" .");
            flag = true;
            DSTM[n1 - 1].setMaThe(maThe);
            DSTM[n1 - 1].setMaDG(theMuon.getMaDG());
            DSTM[n1 - 1].setTenDG(theMuon.getTenDG());
        } else {
            //NGƯỢC LẠI THÌ NHẬP MÃ ĐỘC GIẢ
            String maDG;
            DocGia dg;
            do {
                flag = false;
                System.out.print("Nhập mã của đọc giả mượn tài liệu: ");
                maDG = sc.nextLine();
                if (maDG.length() != 5) {
                    System.out.println("Độ dài mã đọc giả không hợp lệ");
                    flag = true;
                } else {
                    //Kiểm tra xem mã đọc giả có trùng với ai trong danh sách mượn không
                    if(checkMaDGTrung(maDG)){
                        System.out.println("Mã đọc giả không đúng, bị trùng với người khác! " );
                        flag = true;
                    }else {
                        //kiểm tra xem đọc giả đó có trong DSDG hay chưa
                        dg = DSDG.checkMaDG(maDG);
                        if (dg == null) {
                            flag = true;
                            System.out.println("Không có mã đọc giả " + maDG + " trong danh sách đọc giả! Vui lòng nhập lại!");
                        } else {
                            DSTM[n1 - 1].setMaThe(maThe);
                            DSTM[n1 - 1].setMaDG(maDG);
                            DSTM[n1 - 1].setTenDG(dg.getTen());
                        }
                    }
                }
            } while (flag);
        }

        //NHẬP MÃ TÀI LIỆU
        String maTL;
        TaiLieu tl;
        do{
            System.out.print("Nhập mã tài liệu muốn mượn: ");
            maTL = sc.nextLine();
            tl = DSTL.checkMaTL(maTL);
            if(tl == null){
                System.out.println("Chưa có tài liệu \"" + maTL + "\" trong thư viện! Vui lòng nhập lại");
            }else{
                if(tl.getSlcon() == 0){
                    System.out.println("Tài liệu \"" + tl.getTenTL() + "\" đã bị mượn hết!");
                    //XÓA THẺ MƯỢN ĐÃ TẠO
                    if(n1==1) {
                        DSTM = null;
                        n1--;
                    }else DSTM = Arrays.copyOf(DSTM,--n1);
                    System.out.println("Nhấn Enter để thoát chức năng cho mượn tài liệu!");
                    sc.nextLine();
                    return;
                }
                DSTM[n1-1].setMaTL(maTL);
                DSTM[n1-1].setTenTL(tl.getTenTL());
                DSTM[n1-1].setGia((tl.getGia()));
            }
        }while(tl == null);

        //NHẬP NGÀY MƯỢN
        String ngayMuonTra;
        System.out.println("Ngày mượn phải theo định dạng: dd/MM/yyyy. Nếu không sẽ báo lỗi!");
        do{
            System.out.print("Nhập ngày mượn :");
            ngayMuonTra = sc.nextLine();
            if(!checkNgayTheoFormat(ngayMuonTra)){
                System.out.println("Ngày mượn không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy!");
            }else DSTM[n1-1].setNgayMuon(ngayMuonTra);
        }while(!checkNgayTheoFormat(ngayMuonTra));

        //NHẬP SỐ LƯỢNG MƯỢN
        int slMuon;
        do{
            flag = false;
            System.out.print("Nhập số lượng muốn mượn: ");
            try {
                slMuon = Integer.parseInt(sc.nextLine());
                if(slMuon < 0) {
                    flag = true;
                    System.out.println("Số lượng không được bé hơn 0!");
                }else{
                    if(tl.getSlcon()-slMuon < 0){
                        System.out.println("Số lượng của tài liệu \"" + tl.getTenTL() + "\" chỉ còn " +tl.getSlcon());
                        flag = true;
                    }else {
                        tl.setSlCon(tl.getSlcon() - slMuon);
                        DSTM[n1-1].setSlMuon(slMuon);
                    }
                }
            }catch (NumberFormatException nfe){
                flag = true;
                System.out.println("Nhập 1 số nguyên! Đừng nhập chữ!");
            }
        }while(flag);


        //NHẬP HẠN TRẢ
        System.out.println("Hạn trả phải theo định dạng: dd/MM/yyyy và lớn hơn ngày mượn . Nếu không sẽ báo lỗi!");
        do{
            System.out.print("Nhập hạn trả :");
            ngayMuonTra = sc.nextLine();
            if(!isALessThanB(DSTM[n1-1].getNgayMuon(),ngayMuonTra)){
                System.out.println("Hạn trả không hợp lệ! Hạn trả phải đúng định dạng và lớn hơn ngày mượn!");
            }else DSTM[n1-1].setHanTra(ngayMuonTra);
        }while(!isALessThanB(DSTM[n1-1].getNgayMuon(),ngayMuonTra));
    }

    //XÓA THẺ MƯỢN
    public ChiTietTheMuonTra deleteTM(String maThe, String maTL){
        ChiTietTheMuonTra temp;
        for(int i = 0; i < n1; i++){
            if(DSTM[i].getMaThe().equals(maThe) && DSTM[i].getMaTL().equals(maTL)){
                temp = DSTM[i];
                //Nếu danh sách chỉ có 1 thẻ mượn thì xóa danh sách
                if(n1 == 1){
                    DSTM = null;
                    n1--;
                }else{
                    for(int j = i; j < n1-1; j++){
                        DSTM[j]=DSTM[j+1];
                    }
                    DSTM = Arrays.copyOf(DSTM,--n1);
                }
                return temp;
            }
        }
        return null;
    }

    //NHẬN TRẢ TÀI LIỆU
    public void nhanTraTaiLieu(){
        if(n1 <= 0){
            System.out.println("Hiện chưa có đọc giả nào mượn tài liệu!");
            return;
        }
        //NHẬP MÃ THẺ TRẢ TÀI LIỆU
        String maThe;
        do{
            System.out.print("Nhập mã thẻ mượn muốn trả tài liệu: ");
            maThe = sc.nextLine();
            if(checkMaThe(maThe) == null){
                System.out.println("Không tìm thấy thẻ mượn \"" + maThe + "\" trong danh sách mượn tài liệu! Vui lòng nhập lại!");
            }else printTM(maThe);
        }while(checkMaThe(maThe) == null);

        //NHẬP MÃ TÀI LIỆU MÀ THẺ ĐÓ MƯỢN
        String maTL;
        ChiTietTheMuonTra temp;
        do{
            System.out.print("Nhập mã tài liệu nhận trả: ");
            maTL = sc.nextLine();
            temp = getCTTM(maThe, maTL);
            if(temp == null)
                System.out.println("Đọc giả không mượn tài liệu có mã \""+maTL+"\"!");
            else{
                if(DSTT == null){
                    DSTT = new ChiTietTheMuonTra[++n2];
                }else DSTT = Arrays.copyOf(DSTT,++n2);
                DSTT[n2-1] = temp;
            }
        }while(temp == null);

        //LẤY THÔNG TIN CỦA TÀI LIỆU MÀ ĐỌC GIẢ ĐANG MƯỢN
        TaiLieu TL = DSTL.checkMaTL(temp.getMaTL());

        //NHẬP THÔNG TIN PHIẾU TRẢ TÀI LIỆU
        nhapPhieuTraTL(DSTT[n2-1], TL);
    }

    //NHẬP THÔNG TIN TRẢ TÀI LIỆU (NGÀY TRẢ, SL TRẢ, PHẠT)
    public void nhapPhieuTraTL(ChiTietTheMuonTra CTMT, TaiLieu TL){
        //NHẬP NGÀY TRẢ
        String ngayTra;
        System.out.println("Ngày trả tài liệu phải theo định dạng dd/MM/yyyy!");
        do {
            System.out.println("Nhập ngày trả tài liệu: ");
            ngayTra = sc.nextLine();
            //Nếu ngày mượn lớn hơn ngày trả thì báo nhập lại
            if(!isBEqualOrThanA(CTMT.getNgayMuon(),ngayTra)){
                System.out.println("Ngày trả phải lớn hơn hoặc bằng ngày mượn và đúng định dạng! Vui lòng nhập lại!");
            }else {
                //Nếu hạn trả bé hơn ngày trả thì báo quá hạn
                if(isALessThanB(CTMT.getHanTra(), ngayTra)){
                    System.out.println("Tài liệu này đã quá hạn trả!");
                    System.out.println("Hạn trả là: " + CTMT.getNgayTra() + ".");
                    CTMT.setNgayTra(ngayTra);
                }
            }
        }while(!isBEqualOrThanA(CTMT.getNgayMuon(),ngayTra));

        //NHẬP SỐ LƯỢNG TRẢ VỚI THẺ TRẢ THÌ PHẢI TRẢ MỘT LẦN HẾT TÀI LIỆU CỦA MỘT CHI TIẾT THẺ TRẢ
        boolean flag;
        int slTra;
        do{
            flag = false;
            System.out.print("Nhập số lượng tài liệu trả: ");
            try{
                slTra = Integer.parseInt(sc.nextLine());
                if(slTra < 0) {
                    System.out.println("Số lượng không được âm!");
                    flag = true;
                } else {
                    //Nếu số lượng trả là 0 có nghĩa là đã mất hoặc hỏng tài liệu
                    if(slTra == 0){
                        TL.setSlCo(TL.getSlco()-CTMT.getSlMuon());
                        deleteTM(CTMT.getMaThe(),CTMT.getMaTL());
                        CTMT.setSlTra(slTra);
                        System.out.println("Đọc giả đã làm mất tất cả tài liệu mượn!");
                        break;
                    }

                    //Nếu số lượng trả bằng số lượng mượn thì xóa thẻ mượn
                    if(slTra == CTMT.getSlMuon()){
                        TL.setSlCon(TL.getSlcon() + slTra);
                        deleteTM(CTMT.getMaThe(),CTMT.getMaTL());
                        CTMT.setSlTra(slTra);
                        break;
                    }

                    //Nếu số lượng trả bé hơn số lượng mượn thì đã có tài liệu bị mất hoặc hỏng
                    if(slTra < CTMT.getSlMuon()){
                        TL.setSlCo(TL.getSlco() - (CTMT.getSlMuon() - slTra));
                        TL.setSlCon(TL.getSlcon() + slTra);
                        deleteTM(CTMT.getMaThe(),CTMT.getMaTL());
                        CTMT.setSlTra(slTra);
                        System.out.println("Đọc giả làm mất " + (CTMT.getSlMuon() - slTra) + " tài liệu!");
                        break;
                    }

                    if (slTra > CTMT.getSlMuon()) {
                        System.out.println("Số lượng trả không thể lớn hơn số lượng mượn!");
                        flag = true;
                    }
                }
            }catch (NumberFormatException nfe){
                System.out.println("Vui lòng nhập số không có chữ!");
                flag = true;
            }
        }while(flag);


        //NHẬP PHẠT QUÁ HẠN
        int phat;
        do{
            flag = false;
            System.out.print("Nhập tiền phạt quá hạn: ");
            try{
                phat = Integer.parseInt(sc.nextLine());
                if(phat < 0) {
                    System.out.println("Phí phạt không được âm!");
                    flag = true;
                }else CTMT.setPhatQuaHan(phat);
            }catch (NumberFormatException nfe){
                System.out.println("Vui lòng nhập số không có chữ!");
                flag = true;
            }
        }while(flag);

        //NHẬP PHẠT HỎNG MẤT
        do{
            flag = false;
            System.out.print("Nhập tiền phạt hỏng mất tài liệu: ");
            try{
                phat = Integer.parseInt(sc.nextLine());
                if(phat < 0) {
                    System.out.println("Phí phạt không được âm!");
                    flag = true;
                }else CTMT.setPhatHongMat(phat);
            }catch (NumberFormatException nfe){
                System.out.println("Vui lòng nhập số không có chữ!");
                flag = true;
            }
        }while(flag);
    }

    //THỐNG KÊ TÀI LIỆU QUÁ HẠN
    public void thongKeQuaHan(){
        Date current = new Date();
        String curDate = day.format(current);
        boolean flag = true;
        System.out.println("*================================================================================================================*");
        System.out.println("|                                           DANH SÁCH TÀI LIỆU QUÁ HẠN                                           |");
        System.out.println("*================================================================================================================*");
        System.out.printf("| %-30s | %-30s | %-8s | %-15s | %-15s |\n","TÊN ĐỌC GIẢ","TÊN TÀI LIỆU","SL MƯỢN","NGÀY MƯỢN","HẠN TRẢ");
        System.out.println("*================================================================================================================*");
        for(int i = 0; i < n1;i++){
            //Nếu hạn trả bé hơn ngày hiện tại thì đó là tài liệu quá hạn
            if(isALessThanB(DSTM[i].getHanTra(), curDate)){
                DSTM[i].xuatTLQH();
                flag = false;
            }
        }
        if(flag){
            System.out.println("| Không có tài liệu nào quá hạn!                                                                                 |");
        }
        System.out.println("*================================================================================================================*");
    }

    //THỐNG KÊ TÀI LIỆU MƯỢN
    public void thongKeTaiLieuMuon(){
        System.out.println("*============================================================================================================================================================*");
        System.out.println("|                                                                  DANH SÁCH MƯỢN TÀI LIỆU                                                                   |");
        System.out.println("*============================================================================================================================================================*");
        System.out.printf("| %-8s | %-8s | %-30s | %-8s | %-30s | %-8s | %-15s | %-8s | %-15s |\n","MÃ THẺ","MÃ ĐG","TÊN ĐỌC GIẢ","MÃ TL", "TÊN TÀI LIỆU","GIÁ","NGÀY MƯỢN", "SL MƯỢN","HẠN TRẢ");
        System.out.println("*============================================================================================================================================================*");
        for(int i = 0; i < n1 ; i++){
            DSTM[i].xuatTheMuon();
        }
        System.out.println("*============================================================================================================================================================*");
    }

    //THỐNG KÊ TÀI LIỆU HỎNG MẤT
    public void thongKeTaiLieuHongMat(){
        System.out.println("*==============================================================================================*");
        System.out.println("|                                   DANH SÁCH TÀI LIỆU HỎNG MẤT                                |");
        System.out.println("*==============================================================================================*");
        System.out.printf("| %-30s | %-30s | %-8s | %-15s |\n", "TÊN ĐỌC GIẢ", "TÊN TÀI LIỆU","SL MẤT","NGÀY BÁO");
        System.out.println("*==============================================================================================*");
        for(int i = 0; i < n2 ; i++){
            if(DSTT[i].getPhatHongMat() != 0)
                DSTT[i].xuatTLHM();
        }
        System.out.println("*=============================================================================================*");
    }

    //THỐNG KÊ TÀI DANH SÁCH TRẢ TÀI LIỆU
    public void thongKeDSTraTaiLieu(){
        System.out.println("*===============================================================================================================================================================================================================*");
        System.out.println("|                                                                                            DANH SÁCH TÀI LIỆU ĐÃ TRẢ                                                                                          |");
        System.out.println("*===============================================================================================================================================================================================================*");
        System.out.printf("| %-8s | %-8s | %-30s | %-8s | %-30s | %-8s | %-15s | %-8s | %-15s | %-15s | %-8s | %-8s | %-8s |\n","MÃ THẺ","MÃ ĐG","TÊN ĐỌC GIẢ","MÃ TL", "TÊN TÀI LIỆU","GIÁ","NGÀY MƯỢN", "SL MƯỢN","HẠN TRẢ","NGÀY TRẢ","SL TRẢ","PHẠT QH","PHẠT H-M");
        System.out.println("*===============================================================================================================================================================================================================*");
        for(int i = 0; i< n2; i++){
            DSTT[i].xuatTheTra();
        }
        System.out.println("*===============================================================================================================================================================================================================*");
    }

    //ĐỌC FILE DANH SÁCH THẺ MƯỢN
    public void docFileDSTheMuon(String filename) throws FileNotFoundException{
        Scanner readFile = null;
        try {
            readFile = new Scanner(new File(filename));
            while (readFile.hasNext()) {
                if (DSTM == null)
                    DSTM = new ChiTietTheMuonTra[++n1];
                else DSTM = Arrays.copyOf(DSTM, ++n1);
                DSTM[n1 - 1] = new ChiTietTheMuonTra();
                DSTM[n1 - 1].docFileTM(readFile);
                readFile.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            readFile.close();
        }
    }

    //ĐỌC FILE DANH SÁCH THẺ TRẢ
    public void docFileDSTheTra(String filename) throws FileNotFoundException{
        Scanner readFile = null;
        try {
            readFile = new Scanner(new File(filename));
            while (readFile.hasNext()) {
                if (DSTT == null)
                    DSTT = new ChiTietTheMuonTra[++n2];
                else DSTT = Arrays.copyOf(DSTT, ++n2);
                DSTT[n2 - 1] = new ChiTietTheMuonTra();
                DSTT[n2 - 1].docFileTT(readFile);
                readFile.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            readFile.close();
        }
    }

    //GHI FILE DANH SÁCH THẺ MƯỢN
    public void ghiFileDSTheMuon(String filename) throws FileNotFoundException{
        PrintWriter writeFile = null;
        try{
            writeFile = new PrintWriter(new File(filename));
            for(int i = 0; i < n1 ; i++){
                DSTM[i].ghiFileTM(writeFile);
                writeFile.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            writeFile.close();
        }
    }

    //GHI FILE DANH SÁCH THẺ TRẢ
    public void ghiFileDSTheTra(String filename) throws FileNotFoundException{
        PrintWriter writeFile = null;
        try{
            writeFile = new PrintWriter(new File(filename));
            for(int i = 0; i < n2 ; i++){
                DSTT[i].ghiFileTT(writeFile);
                writeFile.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            writeFile.close();
        }
    }

}
