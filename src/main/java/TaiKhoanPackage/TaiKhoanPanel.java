package TaiKhoanPackage;

import Database.DatabaseConnection;
import Database.Service;
import Model.CheckBoxTableHeader;
import Model.TableHeaderAlignment;
import Model.ModelTaiKhoan;
import PhanTrangPackage.EventPagination;
import PhanTrangPackage.Style.PaginationItemRenderStyle1;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;


public class TaiKhoanPanel extends javax.swing.JPanel {

   
    public TaiKhoanPanel() {
        initComponents();
        init();
        loadData(1);
    }
    private void init(){
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
            + "arc:25;"
            + "background:$Table.background");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
            + "height: 30;"
            + "hoverBackground:null;"
            + "pressedBackground:null;"
            + "separatorColor:$TableHeader.background;"
            + "font:bold;");

        table.putClientProperty(FlatClientProperties.STYLE,"" 
            + "rowHeight:30;"
            + "showHorizontalLines:true;"
            + "intercellSpacing: 0, 1;"
            + "cellFocusColor: $TableHeader.hoverBackground;"
            + "selectionBackground: $Panel.background;" // Sử dụng màu nền của panel
            + "selectionForeground: $Table.foreground;");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,""
            + "trackArc:999;"
            + "trackInsets: 3,3,3,3;"
            + "thumbInsets: 3,3,3,3;"
            + "background: $Table.background;");
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:bold +5;");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập thông tin cần tìm...");

        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
            + "arc: 15;"
            + "borderWidth: 0;"
            + "focusWidth: 0;"
            + "innerFocusWidth: 0;"
            + "margin: 5, 20, 5, 20;"
            + "background: $Panel.background;");

        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeader(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
         
        IbTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15)); // Font in đậm
        IbTitle.setForeground(java.awt.Color.BLACK); // Đặt màu chữ
        IbTitle.putClientProperty(FlatClientProperties.STYLE, "font: bold;"); // Đảm bảo in đậm nếu FlatLaf can thiệp
        IbTitle.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18)); // Cỡ chữ là 20
         
        try {
            Connection con = DatabaseConnection.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
     }
     
    public Service service = new Service();
    private void initPagination() {
        // Sử dụng pagination1 thay vì pagination
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());

        // Lắng nghe sự kiện thay đổi trang
        pagination1.addEventPagination(new EventPagination() {
            public void pageChanged(int page) {
                loadData(page); // Tải dữ liệu khi trang thay đổi
            }
        });

        // Tính toán số trang
        int totalCount = service.getTotalCount("tai_khoan");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 10;  // Số bản ghi trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

        pagination1.setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

        // Thêm phân trang vào giao diện
        lbTitle.add(pagination1);
        lbTitle.revalidate();
        lbTitle.repaint();
    }


    // Phương thức tải dữ liệu cho bảng
    private void loadData(int page) {
        try {
            // Lấy model của bảng
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Nếu bảng đang chỉnh sửa, dừng chỉnh sửa ô
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }

            // Xóa hết các dòng hiện tại trong bảng
            model.setRowCount(0);

            // Tính số bản ghi mỗi trang
            int recordsPerPage = 10;
            int offset = (page - 1) * recordsPerPage;  // Tính offset dựa trên trang

            // Lấy dữ liệu của trang hiện tại
            List<ModelTaiKhoan> list = service.getPage(ModelTaiKhoan.class, offset, recordsPerPage);

            // Thêm các dòng vào bảng
            for (ModelTaiKhoan d : list) {
                model.addRow(d.toTableRow(table.getRowCount() + 1));  // Thêm dòng vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Phuong thuc de Search trong bang
    private void searchData(String search){
        try {
            DefaultTableModel model =(DefaultTableModel)table.getModel();
            if(table.isEditing()){
                table.getCellEditor().stopCellEditing();
            }
            model.setRowCount(0);
            List<ModelTaiKhoan> list = service.getAll(ModelTaiKhoan.class,search);
            for(ModelTaiKhoan d:list){
                model.addRow(d.toTableRow(table.getRowCount() + 1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<ModelTaiKhoan> getSelectedTaiKhoan() {
        List<ModelTaiKhoan> list = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            if ((boolean) table.getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = table.getValueAt(i, 10);  // Lấy giá trị ở cột thứ 1

                // Kiểm tra kiểu dữ liệu và ép kiểu nếu đúng
                if (data instanceof ModelTaiKhoan) {
                    ModelTaiKhoan taiKhoan = (ModelTaiKhoan) data;
                    list.add(taiKhoan);
                } else {
                    // Xử lý lỗi nếu không phải kiểu ModelTaiKhoan
                    System.out.println("Dữ liệu không phải kiểu ModelTaiKhoan tại dòng " + i);
                }
            }
        }

        return list;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        buttonEdit = new Model.ButtonAction();
        buttonDelete = new Model.ButtonAction();
        buttonApprove = new Model.ButtonAction();
        IbTitle = new javax.swing.JLabel();
        pagination1 = new PhanTrangPackage.Pagination();

        setPreferredSize(new java.awt.Dimension(1168, 1024));

        lbTitle.setBackground(new java.awt.Color(255, 255, 255));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "STT", "Họ tên", "CCCD", "Giới tính", "Ngày sinh", "SĐT", "Số phòng", "Tên đăng nhập", "Ghi chú", "ModelTaiKhoan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMinWidth(30);
            table.getColumnModel().getColumn(1).setMaxWidth(30);
            table.getColumnModel().getColumn(2).setMinWidth(150);
            table.getColumnModel().getColumn(2).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setMaxWidth(150);
            table.getColumnModel().getColumn(3).setMinWidth(120);
            table.getColumnModel().getColumn(3).setPreferredWidth(120);
            table.getColumnModel().getColumn(3).setMaxWidth(120);
            table.getColumnModel().getColumn(4).setMinWidth(65);
            table.getColumnModel().getColumn(4).setPreferredWidth(65);
            table.getColumnModel().getColumn(4).setMaxWidth(65);
            table.getColumnModel().getColumn(5).setMinWidth(80);
            table.getColumnModel().getColumn(5).setPreferredWidth(80);
            table.getColumnModel().getColumn(5).setMaxWidth(80);
            table.getColumnModel().getColumn(6).setMinWidth(110);
            table.getColumnModel().getColumn(6).setPreferredWidth(110);
            table.getColumnModel().getColumn(6).setMaxWidth(110);
            table.getColumnModel().getColumn(7).setMinWidth(70);
            table.getColumnModel().getColumn(7).setPreferredWidth(70);
            table.getColumnModel().getColumn(7).setMaxWidth(70);
            table.getColumnModel().getColumn(8).setMinWidth(150);
            table.getColumnModel().getColumn(8).setPreferredWidth(150);
            table.getColumnModel().getColumn(8).setMaxWidth(150);
            table.getColumnModel().getColumn(10).setMinWidth(0);
            table.getColumnModel().getColumn(10).setPreferredWidth(0);
            table.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        buttonEdit.setText("Chỉnh sửa");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setText("Xóa");

        buttonApprove.setText("Duyệt");
        buttonApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonApproveActionPerformed(evt);
            }
        });

        IbTitle.setText("Tài khoản");

        javax.swing.GroupLayout lbTitleLayout = new javax.swing.GroupLayout(lbTitle);
        lbTitle.setLayout(lbTitleLayout);
        lbTitleLayout.setHorizontalGroup(
            lbTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lbTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll)
                    .addComponent(jSeparator1)
                    .addGroup(lbTitleLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(lbTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lbTitleLayout.createSequentialGroup()
                                .addComponent(IbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(lbTitleLayout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(buttonApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))))
                .addContainerGap())
            .addGroup(lbTitleLayout.createSequentialGroup()
                .addGap(464, 464, 464)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(528, Short.MAX_VALUE))
        );
        lbTitleLayout.setVerticalGroup(
            lbTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbTitleLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(IbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lbTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonApprove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        List<ModelTaiKhoan> list = getSelectedTaiKhoan();
        if (list.isEmpty()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để chỉnh sửa!");
        }else if(list.size() != 1){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn duy nhất một mục!");
        }else{
        
        // Truyền đối tượng tài khoản hiện tại vào form để chỉnh sửa
        ModelTaiKhoan currentTaiKhoan = (ModelTaiKhoan) getSelectedTaiKhoan().get(0); // Lấy đối tượng tài khoản hiện tại, ví dụ từ cơ sở dữ liệu hoặc bảng
        // Tạo một form chỉnh sửa tài khoản và truyền đối tượng tài khoản hiện tại vào form
        EditTaiKhoan editForm = new EditTaiKhoan(currentTaiKhoan);
        // Tạo một cửa sổ Popup để chỉnh sửa
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Cancel", "Save"};

        // Hiển thị Popup và xử lý sự kiện khi nhấn nút Save hoặc Cancel
        GlassPanePopup.showPopup(new SimplePopupBorder(editForm, "Chỉnh sửa tài khoản", actions, (pc, i) -> {
            if (i == 1) {  // Nếu nhấn Save
                try {
                    // Lấy dữ liệu từ form chỉnh sửa và truyền tài khoản hiện tại vào phương thức getData
                    ModelTaiKhoan updatedTaiKhoan = editForm.getData(currentTaiKhoan);  // Truyền tài khoản hiện tại vào form để lấy dữ liệu cập nhật
                    service.edit(updatedTaiKhoan);  // Gọi phương thức edit trong ServiceTaiKhoan để cập nhật tài khoản

                    // Đóng Popup và thông báo thành công
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Tài khoản đã được chỉnh sửa thành công");

                    // Tải lại dữ liệu từ database để cập nhật bảng
                    loadData(1);  // Hàm load lại dữ liệu sau khi chỉnh sửa
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi chỉnh sửa tài khoản!");
                }
            } else {  // Nếu nhấn Cancel
                pc.closePopup();
            }
        }), option);
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        
    }//GEN-LAST:event_txtSearchActionPerformed

    private void buttonApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonApproveActionPerformed
        try {
            // Lấy tất cả các hàng được chọn từ JTable (có checkbox)
            int[] selectedRows = table.getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một dòng để duyệt.");
                return;
            }

            // Kết nối tới cơ sở dữ liệu
            Connection conn = Database.DatabaseConnection.getConnection();

            // Chuẩn bị câu lệnh SQL
            String updateTaiKhoanQuery = "UPDATE tai_khoan SET ghiChu = ? WHERE ID = ?";
            String insertNguoiDungQuery = "INSERT INTO nguoi_dung (hoTen, ngaySinh, SĐT, tenDangNhap, CCCD, matKhau, soPhong) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement updateStatement = conn.prepareStatement(updateTaiKhoanQuery);
            PreparedStatement insertStatement = conn.prepareStatement(insertNguoiDungQuery);

            // Duyệt qua tất cả các dòng được chọn
            for (int row : selectedRows) {
                // Lấy giá trị ID từ bảng JTable (dòng hiện tại)
                int id = (Integer) table.getValueAt(row, 1); // Cột 1 là ID

                // Truy vấn dữ liệu chi tiết từ bảng tai_khoan (bảng MySQL)
                String selectQuery = "SELECT * FROM tai_khoan WHERE ID = ?";
                PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
                selectStatement.setInt(1, id); // Lấy dữ liệu theo ID
                ResultSet rs = selectStatement.executeQuery();

                if (rs.next()) {
                    // Lấy thông tin từ bảng tai_khoan
                    String hoTen = rs.getString("hoTen");
                    String ngaySinh = rs.getString("ngaySinh");
                    String sdt = rs.getString("SĐT");
                    String cccd = rs.getString("CCCD");
                    String matKhau = rs.getString("matKhau");
                    String tenDangNhap = rs.getString("tenDangNhap");
                    String soPhong = rs.getString("soPhong");
                    String ghiChu = "đã duyệt";

                    // Cập nhật ghi chú trong bảng tai_khoan
                    updateStatement.setString(1, ghiChu);
                    updateStatement.setInt(2, id); // Cập nhật theo ID
                    updateStatement.executeUpdate();

                    // Chèn dữ liệu vào bảng nguoi_dung
                    insertStatement.setString(1, hoTen);
                    insertStatement.setString(2, ngaySinh);
                    insertStatement.setString(3, sdt);
                    insertStatement.setString(4, tenDangNhap);
                    insertStatement.setString(5, cccd);
                    insertStatement.setString(6, matKhau);
                    insertStatement.setString(7, soPhong);
                    insertStatement.executeUpdate(); // Thực thi chèn dữ liệu
                }
                rs.close();
            }

            // Thông báo thành công
            JOptionPane.showMessageDialog(this, "Duyệt thành công! Dữ liệu đã được cập nhật và chuyển sang bảng 'nguoi_dung'.");

            // Làm mới dữ liệu trên bảng
            loadData(1);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }    
    }//GEN-LAST:event_buttonApproveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IbTitle;
    private Model.ButtonAction buttonApprove;
    private Model.ButtonAction buttonDelete;
    private Model.ButtonAction buttonEdit;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel lbTitle;
    private PhanTrangPackage.Pagination pagination1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
