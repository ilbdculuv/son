package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelTaiKhoan extends Model {
    // Thuộc tính
    private String ID;
    private String hoTen;
    private Date ngaySinh;
    private String sdt; // Số điện thoại dạng số nguyên (int)
    private String CCCD;
    private String soPhong;
    private String tenDangNhap;
    private String ghiChu;
    private boolean gioiTinh = true;

    // Constructor
    public ModelTaiKhoan(String hoTen, Date ngaySinh, String sdt, String CCCD, String soPhong, String tenDangNhap, String ghiChu, boolean gioiTinh, String ID) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.CCCD = CCCD;
        this.soPhong = soPhong;
        this.tenDangNhap = tenDangNhap;
        this.ghiChu = ghiChu;
        this.gioiTinh = gioiTinh;
        this.ID = ID;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    // Getter và Setter
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    


    // Chuyển đối tượng thành dòng dữ liệu (ví dụ để hiển thị trong bảng)
    public Object[] toTableRow(int rowNum) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return new Object[] {
            false, // Một giá trị check-box mặc định (ví dụ trong JTable)
            rowNum,
            hoTen,
            CCCD,
            gioiTinh? "Nam" : "Nữ",
            ngaySinh == null ? "" : df.format(ngaySinh), // Định dạng ngày sinh
            sdt,           
            soPhong,
            tenDangNhap,
            ghiChu,
            this
        };
    }
    public String GhiChu() {
        return "Không có ghi chú nào."; // Tùy chỉnh nội dung ghi chú
    }

    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "hoTen":
                return hoTen;
            case "ngaySinh":
                return ngaySinh;
            case "SĐT":
                return sdt;
            case "CCCD":
                return CCCD;
            case "soPhong":
                return soPhong;
            case "tenDangNhap":
                return tenDangNhap;
            case "ghiChu":
                return ghiChu;
            case "gioiTinh":
                return gioiTinh;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
}

