package Database;
//them ti cho co ti commit
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    // Logger để ghi lại các lỗi
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    // Sử dụng Dotenv để tải các biến môi trường từ file a.env
    private static final Dotenv dotenv = Dotenv.configure().filename("a.env").load();

    private static final String SUrl = dotenv.get("SUrl");
    private static final String SUser = dotenv.get("SUser");
    private static final String SPass = dotenv.get("SPass");

    // Phương thức để tạo kết nối đến cơ sở dữ liệu
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Tải driver MySQL thủ công (nếu cần)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(SUrl, SUser, SPass);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Không tìm thấy driver MySQL", e);
        } catch (SQLException e) {
            // Ghi lại lỗi vào log thay vì sử dụng printStackTrace
            logger.log(Level.SEVERE, "Lỗi khi kết nối tới cơ sở dữ liệu", e);
        }
        return connection;
    }

    // Phương thức đóng Connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Ghi lại lỗi khi đóng kết nối
                logger.log(Level.SEVERE, "Lỗi khi đóng kết nối", e);
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Kết nối thành công!");
            closeConnection(connection);
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
