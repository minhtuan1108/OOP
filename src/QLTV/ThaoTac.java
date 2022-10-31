package QLTV;

import java.io.FileNotFoundException;

public interface ThaoTac {
    void Insert(String maObj);
    void Delete(String maObj);
    void Adjust(String maObj);
    void Search();
    void docFileDanhSach(String filename) throws FileNotFoundException;
    void ghiFileDanhSach(String filename) throws FileNotFoundException;
}
