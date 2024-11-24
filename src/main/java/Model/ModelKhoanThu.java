/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import Model.ModelLoaiKhoanThu;

public class ModelKhoanThu extends Model {
    private String ID = "abc";
    private ModelLoaiKhoanThu loaiKhoanThu;

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu = tenKhoanThu;
    }
    private int soTienThu;
    private Date ngayBatDauThu;
    private Date ngayKetThuc;
    private String moTa;
    private String tenKhoanThu;
    
    public ModelKhoanThu(String makhoanthu, String tenKhoanThu, ModelLoaiKhoanThu tenloaikhoanthu, Date ngaybatdauthu, int sotienthu, Date ngayketthuc, String mota) {
        super();
        this.ID = makhoanthu;
        this.tenKhoanThu = tenKhoanThu;
        this.loaiKhoanThu = tenloaikhoanthu;
        this.soTienThu = sotienthu;
        this.ngayBatDauThu = ngaybatdauthu;
        this.ngayKetThuc = ngayketthuc;
        this.moTa = mota;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ModelLoaiKhoanThu getLoaiKhoanThu() {
        return loaiKhoanThu;
    }

    public void setLoaiKhoanThu(ModelLoaiKhoanThu loaiKhoanThu) {
        this.loaiKhoanThu = loaiKhoanThu;
    }

    public int getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(int soTienThu) {
        this.soTienThu = soTienThu;
    }

    public Date getNgayBatDauThu() {
        return ngayBatDauThu;
    }

    public void setNgayBatDauThu(Date ngayBatDauThu) {
        this.ngayBatDauThu = ngayBatDauThu;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "tenKhoanThu_ID":
                return loaiKhoanThu;
            case "soTienThu":
                return soTienThu;
            case "ngayBatDauThu":
                return ngayBatDauThu;
            case "ngayKetThuc":
                return ngayKetThuc;
            case "moTa":
                return moTa;
            case "tenKhoanThu":
                return tenKhoanThu;
            case "maKhoanThu":
                return ID;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    
    public Object[] toTableRow(int rowNum){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        NumberFormat nf = new DecimalFormat("#, ##0.##");
        return new Object[]{
            false, 
            rowNum, 
            this,  
            tenKhoanThu,
            loaiKhoanThu, 
            ngayBatDauThu == null ? "" : df.format(ngayBatDauThu), 
            ngayKetThuc == null ? "" : df.format(ngayKetThuc), 
            nf.format(soTienThu) +"đ", 
            moTa
        };
    }

    
    @Override
    public String toString() {
        return String.valueOf(ID); // Hoặc có thể thêm thông tin chi tiết hơn
    }

}
