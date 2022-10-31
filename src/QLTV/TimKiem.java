package QLTV;

import java.io.FileNotFoundException;

public class TimKiem {
    DanhSachTaiLieu DSTL = new DanhSachTaiLieu();

    TimKiem(){
        try {
            DSTL.docFileDanhSach("DanhSachTaiLieu.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void chucNang()
    {
        DSTL.Search();
    }
}
