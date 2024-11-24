/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ModelHoGiaDinh extends Model {
    
    String ID, chuHo, soPhong, sdtChuHo;
    int soNguoi;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getChuHo() {
        return chuHo;
    }

    public void setChuHo(String chuHo) {
        this.chuHo = chuHo;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getSdtChuHo() {
        return sdtChuHo;
    }

    public void setSdtChuHo(String sdtChuHo) {
        this.sdtChuHo = sdtChuHo;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }
    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "chuHo":
                return chuHo;
            case "soPhong":
                return soPhong;
            case "sdtChuHo":
                return sdtChuHo;
            case "soNguoi":
                return soNguoi;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    
}
