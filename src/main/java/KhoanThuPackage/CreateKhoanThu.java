package KhoanThuPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Database.DatabaseConnection;
import Database.Service;
import Model.ModelKhoanThu;
import Model.ModelLoaiKhoanThu;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import Model.ButtonAction;
import java.util.UUID;

public class CreateKhoanThu extends javax.swing.JPanel {

    public CreateKhoanThu() {
        initComponents();
        
        datePicker.setCloseAfterSelected(true);
        datePicker1.setCloseAfterSelected(true);
        datePicker.setEditor(txtNgayBatDauThu);
        datePicker1.setEditor(txtNgayKetThuc);
    }
    
    //load các loại khoản thu vào combobox
public void loadData(Service service, ModelKhoanThu data) {
    try {
        // Lấy danh sách loại khoản thu
        List<ModelLoaiKhoanThu> listLoaiKhoanThu = service.getAll(ModelLoaiKhoanThu.class, null);

        // Kiểm tra danh sách không null
        if (listLoaiKhoanThu != null) {
            for (ModelLoaiKhoanThu pos : listLoaiKhoanThu) {
                ComboBox.addItem(pos);

                // Chọn mục phù hợp nếu có dữ liệu
                if (data != null && data.getLoaiKhoanThu() != null && data.getLoaiKhoanThu().getID() == pos.getID()) {
                    ComboBox.setSelectedItem(pos);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách loại khoản thu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Nếu có dữ liệu, thiết lập các trường
    if (data != null) {
        txtSoTienThu.setValue(data.getSoTienThu());
        txtName.setText(data.getTenKhoanThu());
        // Định dạng ngày
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (data.getNgayBatDauThu() != null) {
            txtNgayBatDauThu.setText(dateFormat.format(data.getNgayBatDauThu()));
        } else {
            txtNgayBatDauThu.setText("");
        }

        if (data.getNgayKetThuc() != null) {
            txtNgayKetThuc.setText(dateFormat.format(data.getNgayKetThuc()));
        } else {
            txtNgayKetThuc.setText("");
        }

        txtMoTa.setText(data.getMoTa());
    }
}
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker = new raven.datetime.component.date.DatePicker();
        datePicker1 = new raven.datetime.component.date.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSoTienThu = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtNgayBatDauThu = new javax.swing.JFormattedTextField();
        txtNgayKetThuc = new javax.swing.JFormattedTextField();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Tên khoản thu");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Loại khoản thu");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Số tiền thu");

        txtSoTienThu.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Ngày bắt đầu");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Ngày kết thúc");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Mô tả");

        ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxActionPerformed(evt);
            }
        });

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoTienThu)
                    .addComponent(ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtName)
                    .addComponent(jScrollPane1)
                    .addComponent(txtNgayBatDauThu)
                    .addComponent(txtNgayKetThuc))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoTienThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDauThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    //Chuyển dữ liệu thành đối tượng ModelKhoanThu
    public ModelKhoanThu getData() {
        // Validate fields before proceeding
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khoản thu không thể trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (txtSoTienThu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số tiền thu không thể trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        Object selectedItem = ComboBox.getSelectedItem();
        ModelLoaiKhoanThu loaiKhoanThu = null;

        if (selectedItem instanceof ModelLoaiKhoanThu) {
            loaiKhoanThu = (ModelLoaiKhoanThu) selectedItem;
        } else if (selectedItem instanceof String) {
            loaiKhoanThu = searchLoaiKhoanThuFromDatabase((String) selectedItem);
            if (loaiKhoanThu == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy loại khoản thu!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }

        // Continue gathering other data
        String tenKhoanThu = txtName.getText().trim();
        int soTienThu = Integer.parseInt(txtSoTienThu.getText().trim().replace(",", ""));
        Date sqlDate = Date.valueOf("1990-01-01");
        Date ngayBatDauThu = datePicker.isDateSelected() ? Date.valueOf(datePicker.getSelectedDate()) : sqlDate;
        Date ngayKetThuc = datePicker1.isDateSelected() ? Date.valueOf(datePicker1.getSelectedDate()) : sqlDate;
        String moTa = txtMoTa.getText().trim();

        return new ModelKhoanThu(UUID.randomUUID().toString(), tenKhoanThu, loaiKhoanThu, ngayBatDauThu, soTienThu, ngayKetThuc, moTa);
    }

    
    private void ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxActionPerformed
        
    }//GEN-LAST:event_ComboBoxActionPerformed
private ModelLoaiKhoanThu searchLoaiKhoanThuFromDatabase(String tenKhoanThu) {
    Connection con = null;
    PreparedStatement p = null;
    ResultSet rs = null;
    try {
        // Sử dụng phương thức getConnection() để lấy kết nối
        con = DatabaseConnection.getConnection();

        // Câu truy vấn SQL
        String query = "SELECT * FROM `loai_khoan_thu` WHERE `tenKhoanThu_Name` = ?";

        // Chuẩn bị câu truy vấn
        p = con.prepareStatement(query);
        p.setString(1, tenKhoanThu);  // Thiết lập giá trị tham số trong câu truy vấn

        // Thực thi truy vấn
        rs = p.executeQuery();

        // Nếu có kết quả trả về
        if (rs.next()) {
            // Giả sử ModelLoaiKhoanThu có constructor nhận id và name
            return new ModelLoaiKhoanThu(rs.getString("tenKhoanThu_ID"), rs.getString("tenKhoanThu_Name"));
        }
    } catch (SQLException ex) {
        // Nếu có lỗi xảy ra trong quá trình truy vấn
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Đảm bảo đóng ResultSet, PreparedStatement, và Connection trong khối finally
        try {
            if (rs != null) rs.close();
            if (p != null) p.close();
            DatabaseConnection.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return null; // Không tìm thấy loại khoản thu
}    

            

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> ComboBox;
    private raven.datetime.component.date.DatePicker datePicker;
    private raven.datetime.component.date.DatePicker datePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtNgayBatDauThu;
    private javax.swing.JFormattedTextField txtNgayKetThuc;
    private javax.swing.JFormattedTextField txtSoTienThu;
    // End of variables declaration//GEN-END:variables
}
