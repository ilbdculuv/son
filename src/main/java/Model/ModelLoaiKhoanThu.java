package Model;

public class ModelLoaiKhoanThu extends Model {

    private String ID;
    private String tenLoaiKhoanThu;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenLoaiKhoanThu() {
        return tenLoaiKhoanThu;
    }

    public void setTenLoaiKhoanThu(String tenLoaiKhoanThu) {
        this.tenLoaiKhoanThu = tenLoaiKhoanThu;
    }


    public ModelLoaiKhoanThu(String tenkhoanthu_id, String tenkhoanthu_name) {
        this.ID = tenkhoanthu_id;
        this.tenLoaiKhoanThu = tenkhoanthu_name;
    }

    @Override
    public String toString() {
        return tenLoaiKhoanThu;
    }

    @Override
    public Object getValue(String columnName) {
        switch (columnName) {
            case "tenKhoanThu_ID":
                return ID;
            case "tenKhoanThu_Name":
                return tenLoaiKhoanThu;
            default:
                throw new IllegalArgumentException("Unsupported column: " + columnName);
        }
    }
}
