
package HomeFramePackage.HomeMenuPackage;

import HomeFramePackage.HomeMenuPackage.MenuModel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

public class MenuPanel extends javax.swing.JPanel {

    private EventMenuSelected event;
    
    public void addEventMenuSelected(EventMenuSelected event){
        this.event = event;
        menuList1.addEventMenuSelected(event);
    }
    public MenuPanel() {
        initComponents();
        setOpaque(false);
        init();
    }
    
    private void init(){
        menuList1.addItem(new MenuModel("TrangChu","Trang chủ",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("TaiKhoan","Tài khoản",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("KhoanThu","Khoản thu",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("ThuPhi","Thu phí",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("NhanKhau","Nhân khẩu",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("KhieuNai","Khiếu nại",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("DangXuat","Đăng xuất",MenuModel.MenuType.MENU));
        menuList1.setFixedCellHeight(50);
        
    }

    @Override
    public void paintComponent(Graphics grphcs){
        super.paintComponent(grphcs);
        
        // Chuyển đổi Graphics sang Graphics2D
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        
        // Tạo màu gradient
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        GradientPaint grd = new GradientPaint(0, 0, Color.decode("#007991"), 0, 1024, Color.decode("#78ffd6"));
        grphcs2d.setPaint(grd);

        // Tạo hình chữ nhật bo tròn chỉ góc trên bên phải
        int width = getWidth();
        int height = getHeight();
        int cornerRadius = 40; // Bán kính bo tròn góc

        // Vẽ đường viền bằng Path2D
        Path2D path = new Path2D.Double();
        
        // Vẽ phần còn lại của hình chữ nhật, chỉ bo tròn góc trên bên phải
        path.moveTo(0, 0); // Bắt đầu từ góc trên bên trái
        path.lineTo(width - cornerRadius, 0); // Vẽ đường ngang đến góc trên bên phải
        path.quadTo(width, 0, width, cornerRadius); // Bo tròn góc trên bên phải
        path.lineTo(width, height); // Vẽ đường dọc xuống góc dưới bên phải
        path.lineTo(0, height); // Vẽ đường ngang xuống góc dưới bên trái
        path.closePath(); // Đóng lại đường viền

        // Vẽ hình theo path đã tạo
        grphcs2d.fill(path); // Điền màu cho hình
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        menuList1 = new HomeFramePackage.HomeMenuPackage.MenuList<>();

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jLabel3.setText(" Quản lý chung cư");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menuList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(menuList1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private HomeFramePackage.HomeMenuPackage.MenuList<String> menuList1;
    // End of variables declaration//GEN-END:variables
}
