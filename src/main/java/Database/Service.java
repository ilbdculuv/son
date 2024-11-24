package Database;

import Model.Model;
import Model.ModelHoGiaDinh;
import Model.ModelKhoanThu;
import Model.ModelLoaiKhoanThu;
import Model.ModelTaiKhoan;
import Model.ModelThongBao;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Service {
    private static final Logger logger = Logger.getLogger(Service.class.getName());
    //CHÚ Ý: NHỮNG HÀM CÓ CHÚ THÍCH ĐC VIẾT TRÊN UPPERCASE THÌ LÀ HÀM PUBLIC, KO THÌ CHỈ PHỤC VỤ HÀM PUBLIC
    
    
    // (class, result set) -> List
    private <T extends Model> List<T> processResultSet(Class<T> clazz, ResultSet r) throws SQLException {
        List<T> result = new ArrayList<>();

        switch (clazz.getSimpleName()) {
            case "ModelKhoanThu":
                while (r.next()) {
                    String makhoanthu = r.getString("maKhoanThu");
                    String tenkhoanthu_id = r.getString("tenKhoanThu_ID");
                    String tenkhoanthu_name = r.getString("tenKhoanThu_Name");
                    int sotienthu = r.getInt("soTienThu");
                    Date ngaybatdauthu = r.getDate("ngayBatDauThu");
                    Date ngayketthuc = r.getDate("ngayKetThuc");
                    String mota = r.getString("moTa");
                    String tenkhoanthu = r.getString("tenKhoanThu");
                    ModelKhoanThu khoanThu = new ModelKhoanThu(
                        makhoanthu,
                        tenkhoanthu,
                        new ModelLoaiKhoanThu(tenkhoanthu_id, tenkhoanthu_name),
                        ngaybatdauthu,
                        sotienthu,
                        ngayketthuc,
                        mota
                    );

                    result.add((T) khoanThu); // Ép kiểu sang T
                }
                break;
                
            case "ModelLoaiKhoanThu":
                while (r.next()) {
                    String tenkhoanthu_id = r.getString("tenKhoanThu_ID");
                    String tenkhoanthu_name = r.getString("tenKhoanThu_Name");

                    ModelLoaiKhoanThu loaiKhoanThu = new ModelLoaiKhoanThu(
                        tenkhoanthu_id,
                        tenkhoanthu_name
                    );
                    
                    result.add((T) loaiKhoanThu); 
                }
                break;
                
            case "ModelThongBao":
                while (r.next()) {
                    String noiDung = r.getString("thongBao");
                    Date ngayDang = r.getDate("ngayDang");  
                    String ID = r.getString("ID");
                    
                    ModelThongBao thongBao = new ModelThongBao(
                        noiDung,
                        ngayDang,
                        ID
                    );
                    
                    result.add((T) thongBao); 
                }
                break;
                
            case "ModelTaiKhoan":
                while (r.next()) {
                    String hoTen = r.getString("hoTen");
                    String sdt = r.getString("SĐT"); // Chuyển sang String nếu cần
                    String cccd = r.getString("CCCD");
                    Date ngaySinh = r.getDate("ngaySinh");
                    String soPhong = r.getString("soPhong");
                    String tenDangNhap = r.getString("tenDangNhap");
                    String ghiChu = r.getString("ghiChu");
                    String ID = r.getString("ID");
                    boolean gioiTinh = r.getBoolean("gioiTinh");
                    ModelTaiKhoan taiKhoan = new ModelTaiKhoan(
                        hoTen,
                        ngaySinh,
                        sdt,
                        cccd,
                        soPhong,
                        tenDangNhap,
                        ghiChu,
                        gioiTinh,
                        ID
                    );
                    
                    result.add((T) taiKhoan);
                }
                break;
                 
            default:
                throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }

        return result;
    }
  
    
    
    // LẤY KẾT QUẢ TỪ DATABASE + TÌM KIẾM
    public <T extends Model> List<T> getAll(Class<T> clazz, String searchInput) throws SQLException {
        // Kiểm tra giá trị null của clazz
        if (clazz == null) {
            throw new IllegalArgumentException("Class type cannot be null.");
        }

        // Xây dựng câu truy vấn
        String query = buildLoadQuery(clazz, searchInput);

        // Kết nối và thực hiện truy vấn
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement p = con.prepareStatement(query)) {

            // Nếu có tham số tìm kiếm, gán giá trị cho các dấu "?"
            if (searchInput != null && !searchInput.trim().isEmpty()) {
                String likeSearchInput = "%" + searchInput.trim() + "%";
                int parameterCount = countPlaceholders(query); // Đếm số lượng dấu "?"
                for (int i = 1; i <= parameterCount; i++) {
                    p.setString(i, likeSearchInput);
                }
            }

            // Thực thi truy vấn và xử lý kết quả
            try (ResultSet r = p.executeQuery()) {
                return processResultSet(clazz, r); // Chuyển đổi ResultSet thành danh sách
            }

        } catch (SQLException e) {
            // Ghi log lỗi và ném lại ngoại lệ
            logger.log(Level.SEVERE, "Lỗi khi thực thi truy vấn: " + query, e);
            throw e; // Quăng lại ngoại lệ để lớp gọi xử lý
        }
    }
    
    //Đếm số "?" trong query
    private int countPlaceholders(String query) {
        int count = 0;
        for (char c : query.toCharArray()) {
            if (c == '?') {
                count++;
            }
        }
        return count;
    }
    
    // (class, search input) -> query
    private <T extends Model> String buildLoadQuery(Class<T> clazz, String searchInput) {
        String baseQuery;
        
        switch (clazz.getSimpleName()) {
            
            case "ModelKhoanThu":
                baseQuery = "SELECT * FROM `khoan_thu` JOIN `loai_khoan_thu` USING (tenKhoanThu_ID)";
                if (searchInput != null && !searchInput.isEmpty()) {
                    baseQuery += " WHERE maKhoanThu LIKE ? OR moTa LIKE ? OR soTienThu LIKE ?";
                }
                baseQuery += " ORDER BY tenKhoanThu_Name";
                return baseQuery;

            case "ModelLoaiKhoanThu":
                baseQuery = "SELECT * FROM `loai_khoan_thu`";
                return baseQuery;
                
            case "ModelThongBao":
                baseQuery = "SELECT * FROM `thong_bao`";
                if (searchInput != null && !searchInput.isEmpty()) {
                    baseQuery += " WHERE thongBao LIKE ? OR ngayDang LIKE ?";
                }
                baseQuery += " ORDER BY ngayDang DESC";
                return baseQuery;
            
            case "ModelTaiKhoan":
                baseQuery = "SELECT * FROM `tai_khoan`";
                if (searchInput != null && !searchInput.isEmpty()) {
                    baseQuery += " WHERE hoTen LIKE ? OR ghiChu LIKE ? OR ngaySinh LIKE ?";
                }
                baseQuery += " ORDER BY hoTen";
                return baseQuery;
            
            case "ModelHoGiaDinh":
            baseQuery = "SELECT * FROM `ho_gia_dinh`";
            if (searchInput != null && !searchInput.isEmpty()) {
                baseQuery += " WHERE chuHo LIKE ? OR soPhong LIKE ?";
            }
            baseQuery += " ORDER BY soPhong";
            return baseQuery;
            
            default:
                throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }
    }

    
    
    // Tạo UUID mới cho ID trong create method
    public <T extends Model> void create(T data) throws SQLException {
        // Lấy tên bảng và cột từ lớp của đối tượng
        String tableName = getTableName(data.getClass());
        List<String> columnNames = getColumnNames(data.getClass());

        // Tạo UUID mới cho ID và set vào đối tượng
        String generatedID = UUID.randomUUID().toString(); // Tạo UUID và chuyển sang String

        if (data instanceof ModelKhoanThu) {
            ((ModelKhoanThu) data).setID(generatedID);  // Set UUID cho khoan thu
        } else if (data instanceof ModelLoaiKhoanThu) {
            ((ModelLoaiKhoanThu) data).setID(generatedID);  // Set UUID cho loại khoản thu
        } else if (data instanceof ModelThongBao) {
            ((ModelThongBao) data).setID(generatedID);  // Set UUID cho thông báo
        } else if (data instanceof ModelTaiKhoan) {
            ((ModelTaiKhoan) data).setID(generatedID);  // Set UUID cho tài khoản
        } else if (data instanceof ModelHoGiaDinh) {
            ((ModelHoGiaDinh) data).setID(generatedID);  // Set UUID cho tài khoản
        }

        // Xây dựng câu lệnh INSERT
        String query = buildInsertQuery(tableName, columnNames);

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement p = con.prepareStatement(query)) {

            // Gán giá trị cho PreparedStatement từ model
            setPreparedStatementValues(p, data, columnNames);

            int rowsAffected = p.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được thêm thành công vào " + tableName + "!");
            } else {
                System.out.println("Không có dữ liệu được thêm vào cơ sở dữ liệu.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Lỗi khi thêm dữ liệu vào " + tableName, e);
            throw e; // Propagate the exception
        }
    }

    
    //Truy ra tên bảng từ tên class Model
    private String getTableName(Class<? extends Model> clazz) {
        switch (clazz.getSimpleName()) {
            case "ModelKhoanThu":
                return "khoan_thu";
            case "ModelLoaiKhoanThu":
                return "loai_khoan_thu";
            case "ModelThongBao":
                return "thong_bao";
            case "ModelTaiKhoan":
                return "tai_khoan";
            case "ModelHoGiaDinh":
                return "ho_gia_dinh";
            default:
                throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }
    }
    
    //Cho biết tên các cột của bảng trên csdl để xử lý Result Set 1 cách tương ứng
    private List<String> getColumnNames(Class<? extends Model> clazz) {
        switch (clazz.getSimpleName()) {
            case "ModelKhoanThu":
                return List.of("maKhoanThu", "tenKhoanThu_ID", "tenKhoanThu", "soTienThu", "ngayBatDauThu", "ngayKetThuc", "moTa");
            case "ModelLoaiKhoanThu":
                return List.of("tenKhoanThu_ID", "tenKhoanThu_Name");
            case "ModelThongBao":
                return List.of("ID", "thongBao", "ngayDang");
            case "ModelTaiKhoan":
                return List.of("ID", "gioiTinh", "hoTen", "ngaySinh", "SĐT", "soPhong", "ghiChu");
            case "ModelHoGiaDinh":
                return List.of("ID", "chuHo", "sdtChuHo", "soPhong", "soNguoi");
            default:
                throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }
    }
    
    private String buildInsertQuery(String tableName, List<String> columnNames) {
        String columns = String.join(", ", columnNames);
        String placeholders = String.join(", ", Collections.nCopies(columnNames.size(), "?"));
        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
    }
    
    //Set tất cả các "?" trong PreparedStatement
    private void setPreparedStatementValues(PreparedStatement p, Model model, List<String> columnNames) throws SQLException {
        for (int i = 0; i < columnNames.size(); i++) {
            Object value = model.getValue(columnNames.get(i)); // Lấy giá trị từ model
            if (value == null) {
                p.setNull(i + 1, java.sql.Types.NULL);
            } else if (value instanceof Integer) {
                p.setInt(i + 1, (Integer) value);
            } else if (value instanceof Boolean) {
                p.setBoolean(i + 1, (Boolean) value);
            } else if (value instanceof String) {
                p.setString(i + 1, (String) value);
            } else if (value instanceof java.sql.Date) {
                p.setDate(i + 1, (java.sql.Date) value);
            } else if (value instanceof ModelLoaiKhoanThu) {
                p.setString(i + 1, ((ModelLoaiKhoanThu) value).getID());
            }else{
                throw new IllegalArgumentException("Unsupported data type: " + value.getClass().getName());
            }
        }
    }

    
    //SỬA 1 BẢN GHI TRÊN DATABASE
    public <T extends Model> void edit(T data) throws SQLException {
        String tableName = getTableName(data.getClass());
        List<String> columnNames = getColumnNames(data.getClass());

        // Lấy giá trị của khóa chính từ model
        Object idValue = getIdValue(data);

        // Xây dựng câu lệnh UPDATE
        String query = buildUpdateQuery(tableName, columnNames);

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement p = con.prepareStatement(query)) {

            // Gán giá trị cho PreparedStatement từ model
            setPreparedStatementValues(p, data, columnNames);

            // Gán giá trị khóa chính vào PreparedStatement
            p.setObject(columnNames.size() + 1, idValue);

            int rowsAffected = p.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công trong " + tableName + "!");
            } else {
                System.out.println("Không có dữ liệu nào được cập nhật.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Lỗi khi cập nhật dữ liệu trong " + tableName, e);
        }
    }

    //Lấy giá trị ID của hàng đc chọn
    private <T extends Model> Object getIdValue(T data) {
        if (data instanceof ModelKhoanThu) {
            return ((ModelKhoanThu) data).getID(); // Ví dụ nếu có khóa chính là maKhoanThu
        } else if (data instanceof ModelLoaiKhoanThu) {
            return ((ModelLoaiKhoanThu) data).getID(); // Ví dụ nếu có khóa chính là tenKhoanThuId
        } else if (data instanceof ModelThongBao) {
            return ((ModelThongBao) data).getID();
        } else if (data instanceof ModelTaiKhoan) {
            return ((ModelTaiKhoan) data).getID();
        } else if (data instanceof ModelHoGiaDinh) {
            return ((ModelHoGiaDinh) data).getID();
        }
        throw new IllegalArgumentException("Không xác định được khóa chính cho model này.");
    }
    
    //Lấy TÊN khóa chính của bảng (sẽ ko cần nếu ta đặt tất cả tên khóa chính là "ID" ngay từ đầu :) )
    private String getPrimaryKey(String tableName){
        String tenKhoaChinh;
        switch(tableName){
            case "khoan_thu":
                tenKhoaChinh = "maKhoanThu";
                break;

            case "loai_khoan_thu":
                tenKhoaChinh = "tenKhoanThu_ID";
                break;
      
            default:
                tenKhoaChinh = "ID";
        }
        return tenKhoaChinh;
    }
    
    private String buildUpdateQuery(String tableName, List<String> columnNames) {
        // Xây dựng phần SET
        String setClause = columnNames.stream()
                .map(column -> column + " = ?")
                .collect(Collectors.joining(", "));
        // Tạo câu truy vấn SQL
        return "UPDATE " + tableName + " SET " + setClause + " WHERE " + getPrimaryKey(tableName) + " = ?";
    } 
    
    public <T extends Model> void delete(T data) throws SQLException {
        String tableName = getTableName(data.getClass());
        Object idValue = getIdValue(data);
        String primaryKey = getPrimaryKey(tableName);

        String query = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement p = con.prepareStatement(query)) {

            p.setObject(1, idValue);

            int rowsAffected = p.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được xóa thành công khỏi " + tableName + "!");
            } else {
                System.out.println("Không tìm thấy dữ liệu để xóa.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Lỗi khi xóa dữ liệu khỏi " + tableName, e);
            throw e; 
        }
    }
    
    // PHƯƠNG THỨC TÍNH TỔNG SỐ BẢN GHI
    public int getTotalCount(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try (Connection con = Database.DatabaseConnection.getConnection();
             PreparedStatement p = con.prepareStatement(query);
             ResultSet r = p.executeQuery()) {

            if (r.next()) {
                return r.getInt(1); // Trả về tổng số bản ghi
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Lỗi khi đếm tổng số bản ghi trong bảng " + tableName, e);
        }
        return 0; // Trả về 0 nếu có lỗi
    }
    // Phương thức xây dựng truy vấn phân trang
    private <T extends Model> String buildPagedQuery(Class<T> clazz) {
        String baseQuery;

        switch (clazz.getSimpleName()) {
            case "ModelKhoanThu" -> // Truy vấn cho ModelKhoanThu
                baseQuery = "SELECT * FROM `khoan_thu` JOIN `loai_khoan_thu` USING (tenKhoanThu_ID) ORDER BY tenKhoanThu_Name LIMIT ?, ?";

            case "ModelLoaiKhoanThu" -> // Truy vấn cho ModelLoaiKhoanThu
                baseQuery = "SELECT * FROM `loai_khoan_thu` ORDER BY tenKhoanThu_Name LIMIT ?, ?";
                
            case "ModelTaiKhoan" -> // Truy vấn cho ModelTaiKhoan
                baseQuery = "SELECT * FROM `tai_khoan` ORDER BY hoTen LIMIT ?, ?";
                
            case "ModelHoGiaDinh" -> // Truy vấn cho ModelTaiKhoan
                baseQuery = "SELECT * FROM `ho_gia_dinh` ORDER BY hoTen LIMIT ?, ?";
                
            default -> throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }

        return baseQuery;
    }
    public <T extends Model> List<T> getPage(Class<T> clazz, int offset, int limit) throws SQLException {
        if (clazz == null) {
            throw new IllegalArgumentException("Class type cannot be null.");
        }

        String query = buildPagedQuery(clazz);

        try (Connection con = Database.DatabaseConnection.getConnection();
             PreparedStatement p = con.prepareStatement(query)) {

            p.setInt(1, offset);
            p.setInt(2, limit);

            try (ResultSet r = p.executeQuery()) {
                return processResultSet(clazz, r);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Lỗi khi thực thi truy vấn: " + query, e);
            throw e;
        }
    }
}
