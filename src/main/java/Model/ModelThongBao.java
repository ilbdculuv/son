package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ModelThongBao extends Model {
    private String noiDung;
    private Date ngayDang;
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    // Constructor
    public ModelThongBao(String noiDung, Date ngayDang, String ID){
        this.noiDung = noiDung;
        this.ngayDang = ngayDang;
        this.ID = ID;
    }

    // Getter và Setter cho noiDung
    public void setNoiDung(String noiDung){
        this.noiDung = noiDung;
    }

    public String getNoiDung(){
        return noiDung;
    }

    // Phương thức chuyển đối tượng thành chuỗi, có thể thêm thông tin chi tiết
    @Override
    public String toString() {
        return noiDung; // Trả về nội dung thông báo
    }

    // Phương thức toTableRow trả về mảng đối tượng tương ứng với mỗi dòng trong bảng
    public Object[] toTableRow(int index) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return new Object[]{false, ngayDang == null ? "" : df.format(ngayDang), this, noiDung }; // Trả về mảng chứa chỉ số thứ tự và nội dung thông báo
    }

    

    @Override
    
    public Object getValue(String columnName) {
        switch (columnName) {
            case "ID":
                return ID;
            case "thongBao":
                return noiDung;
            case "ngayDang":
                return ngayDang;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
    
}
