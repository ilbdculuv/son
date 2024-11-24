/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package HomeFramePackage.ThongBao;

import Database.Service;
import Model.ModelThongBao;
import KhoanThuPackage.CreateKhoanThu;
import Model.ModelKhoanThu;
import Model.CheckBoxTableHeader;
import Model.TableHeaderAlignment;
import javax.swing.table.DefaultTableModel;


import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author Admin
 */
public class PanelThongBao extends javax.swing.JPanel {

    /**
     * Creates new form NotificationsPanel
     */
    public PanelThongBao() {
        initComponents();
        init();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Thêm padding
    table.getColumnModel().getColumn(3).setCellRenderer(renderer);
        loadData();
        
    }
    private void init(){
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:40;"
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
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập thông tin cần tìm...");
        //txtSearch.putClientProperty(FlatClientProperties. TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("sample/icon/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc: 15;"
                + "borderWidth: 0;"
                + "focusWidth: 0;"
                + "innerFocusWidth: 0;"
                + "margin: 5, 20, 5, 20;"
                + "background: $Panel.background;");
        
         table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeader(table, 0));
         new TableHeaderAlignment(table);
    }
    private void adjustRowHeight() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int rowHeight = 0;

            // Duyệt qua tất cả các cột trong hàng để tính chiều cao tối đa
            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            // Cập nhật chiều cao hàng
            table.setRowHeight(row, rowHeight);
        }
    }

    private void adjustColumnWidths() {
        
        // Lặp qua tất cả các cột
        for (int column = 0; column < table.getColumnCount(); column++) {
            int maxWidth = 0;

            // Duyệt qua tất cả các hàng trong cột
            for (int row = 0; row < table.getRowCount(); row++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                maxWidth = Math.max(maxWidth, comp.getPreferredSize().width);
            }

            // Cập nhật chiều rộng cột
            table.getColumnModel().getColumn(column).setPreferredWidth(maxWidth + 40);  // Thêm khoảng trống
        }

        // Cập nhật lại header của bảng
        table.getTableHeader().resizeAndRepaint();
    }
    private Service service = new Service();
    private void loadData() {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);  // Xóa dữ liệu cũ (nếu có)
            
            // Lấy và thêm dữ liệu mới từ cơ sở dữ liệu
            List<ModelThongBao> list = service.getAll(ModelThongBao.class, null);
            for (ModelThongBao tb : list) {
                model.addRow(tb.toTableRow(table.getRowCount() + 1));  // Thêm dòng dữ liệu mới vào bảng
            }

            // Áp dụng MultilineTableCellRenderer chỉ khi cần
            table.getColumnModel().getColumn(3).setCellRenderer(new MultilineTableCellRenderer());

            // Cập nhật chiều rộng cột và chiều cao hàng chỉ một lần sau khi dữ liệu được cập nhật
            adjustColumnWidths();  // Cập nhật chiều rộng cột
            adjustRowHeight();     // Cập nhật chiều cao hàng

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ModelThongBao> getSelectedData(){
        List<ModelThongBao> list = new ArrayList<>();
        for(int i = 0; i<table.getRowCount(); i++){
            if((boolean) table.getValueAt(i,0)){
                ModelThongBao data = (ModelThongBao)table.getValueAt(i, 2);
                list.add(data);
            }
        }
        return list;
    }
    
    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Dừng chỉnh sửa nếu bảng đang ở trạng thái chỉnh sửa
            if (table.isEditing() && table.getCellEditor() != null) {
                table.getCellEditor().stopCellEditing();
            }

            // Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);

            // Kiểm tra từ khóa tìm kiếm, nếu null hoặc rỗng, tải toàn bộ dữ liệu
            if (search == null || search.trim().isEmpty()) {
                search = null; // Tải toàn bộ dữ liệu
            }

            // Lấy danh sách dữ liệu
            List<ModelThongBao> list = service.getAll(ModelThongBao.class, search);

            // Kiểm tra và hiển thị dữ liệu
            if (list != null) {
                for (ModelThongBao d : list) {
                    model.addRow(d.toTableRow(table.getRowCount() + 1));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lbTitle = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        buttonAdd = new Model.ButtonAction();
        buttonDelete = new Model.ButtonAction();

        setPreferredSize(new java.awt.Dimension(1160, 680));

        panel.setBackground(new java.awt.Color(255, 255, 255));

        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Ngày đăng", "this", "Nội dung"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(25);
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(0).setMaxWidth(25);
            table.getColumnModel().getColumn(1).setMinWidth(80);
            table.getColumnModel().getColumn(1).setPreferredWidth(80);
            table.getColumnModel().getColumn(1).setMaxWidth(80);
            table.getColumnModel().getColumn(2).setMinWidth(0);
            table.getColumnModel().getColumn(2).setPreferredWidth(0);
            table.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        lbTitle.setBackground(new java.awt.Color(255, 255, 255));
        lbTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTitle.setText("Thông báo");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        buttonAdd.setText("Thêm");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonDelete.setText("Xóa");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scroll)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(43, Short.MAX_VALUE))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
   
    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
         CreateThongBao create = new CreateThongBao();
        DefaultOption option = new DefaultOption() {
        @Override
        public boolean closeWhenClickOutside() {
            return true;
        }
    };
    String actions[] = new String[]{"Cancel", "Save"};
    GlassPanePopup.showPopup(new SimplePopupBorder(create, "Tạo thông báo mới", actions, (pc, i) -> {
        if (i == 1) { // Nếu nhấn "Save"
            try {
                ModelThongBao data = create.getData(); // Lấy dữ liệu từ CreateThongBao

                // Kiểm tra nội dung không rỗng
                if (data.getNoiDung() == null || data.getNoiDung().isEmpty()) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Nội dung thông báo không được để trống.", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Lưu thông báo mới vào cơ sở dữ liệu
                service.create(data); 

                // Đóng cửa sổ popup sau khi lưu
                pc.closePopup();

                // Hiển thị thông báo thành công
                Notifications.getInstance().show(
                    Notifications.Type.SUCCESS, 
                    "Thông báo mới đã được tạo thành công!"
                );

                // Gọi lại loadData() để làm mới bảng
                loadData(); // Cập nhật lại bảng hiển thị thông báo mới
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pc.closePopup();
        }
    }), option);
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        List<ModelThongBao> list = getSelectedData();
        if (!list.isEmpty()){
              DefaultOption option = new DefaultOption(){ // hien thi bang de minh edit
                @Override
                public boolean closeWhenClickOutside(){
                    return true;
                }
            };
            String actions[] = new String[]{"Cancel", "Delete"};
            JLabel label = new JLabel("Bạn có muốn xóa " + list.size()+" mục?");
            label.setBorder(new EmptyBorder(0, 25, 0, 25));
             
            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Xác nhận xóa", actions, (pc, i) ->{
                if(i == 1){
                    try{
                        for(ModelThongBao d: list){
                            service.delete(d);
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Xóa thành công");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    loadData();
                }else{
                    pc.closePopup();
                }
            }),option);
        }else{
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để xóa!");
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchData(txtSearch.getText().trim());
    }//GEN-LAST:event_txtSearchKeyReleased
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Model.ButtonAction buttonAdd;
    private Model.ButtonAction buttonDelete;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
