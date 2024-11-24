package LoginFramePackage;

import LoginFramePackage.RegisterFramePackage.RegisterFrame;
import HomeFramePackage.HomePanelPackage.Home;
import LoginFramePackage.ForgotPasswordPackage.ForgotPasswordFrame;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class LoginFrame extends javax.swing.JFrame {
    
    private static final Logger logger = Logger.getLogger(LoginFrame.class.getName());
    
    public LoginFrame() {
        initComponents();
        //Set nền và icon cho chương trình
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,255,255));
        URL iconUrl = getClass().getResource("/images/big_icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(iconUrl));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradientPanelOuter1 = new LoginFramePackage.GradientPanelOuter();
        gradientPanelInner1 = new LoginFramePackage.GradientPanelInner();
        jButtonDangNhap = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTenTaiKhoan = new javax.swing.JTextField();
        jTextFieldMatKhau = new javax.swing.JPasswordField();
        jButtonQuenMatKhau = new javax.swing.JButton();
        logoPanel1 = new LoginFramePackage.LogoPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonDangKi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDangNhap.setForeground(new java.awt.Color(0, 153, 153));
        jButtonDangNhap.setText("Đăng nhập");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên tài khoản");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu");

        jTextFieldTenTaiKhoan.setForeground(new java.awt.Color(0, 102, 102));
        jTextFieldTenTaiKhoan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextFieldMatKhau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButtonQuenMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        jButtonQuenMatKhau.setText("Quên mật khẩu");
        jButtonQuenMatKhau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonQuenMatKhau.setContentAreaFilled(false);
        jButtonQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonQuenMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonQuenMatKhauMouseExited(evt);
            }
        });
        jButtonQuenMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuenMatKhauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gradientPanelInner1Layout = new javax.swing.GroupLayout(gradientPanelInner1);
        gradientPanelInner1.setLayout(gradientPanelInner1Layout);
        gradientPanelInner1Layout.setHorizontalGroup(
            gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelInner1Layout.createSequentialGroup()
                .addGroup(gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(gradientPanelInner1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonQuenMatKhau, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, gradientPanelInner1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButtonDangNhap)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        gradientPanelInner1Layout.setVerticalGroup(
            gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelInner1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonQuenMatKhau)
                .addGap(12, 12, 12)
                .addComponent(jButtonDangNhap)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout logoPanel1Layout = new javax.swing.GroupLayout(logoPanel1);
        logoPanel1.setLayout(logoPanel1Layout);
        logoPanel1Layout.setHorizontalGroup(
            logoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        logoPanel1Layout.setVerticalGroup(
            logoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý chung cư");

        jButtonDangKi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDangKi.setForeground(new java.awt.Color(0, 153, 153));
        jButtonDangKi.setText("Đăng kí");
        jButtonDangKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangKiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Chưa có tài khoản?");

        javax.swing.GroupLayout gradientPanelOuter1Layout = new javax.swing.GroupLayout(gradientPanelOuter1);
        gradientPanelOuter1.setLayout(gradientPanelOuter1Layout);
        gradientPanelOuter1Layout.setHorizontalGroup(
            gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                .addGap(0, 72, Short.MAX_VALUE)
                .addGroup(gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(gradientPanelInner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDangKi)
                        .addGap(133, 133, 133))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(logoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        gradientPanelOuter1Layout.setVerticalGroup(
            gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(logoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gradientPanelInner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDangKi)
                    .addComponent(jLabel4))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanelOuter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanelOuter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDangKiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangKiActionPerformed
        RegisterFrame SignUpFrame = new RegisterFrame();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
    }//GEN-LAST:event_jButtonDangKiActionPerformed

    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        String LoginName, Password, query, passDb = null;
        int notFound = 0;

        try{
            Connection con = Database.DatabaseConnection.getConnection();
            if( con == null){
                throw new Exception("Không thể kết nối cơ sở dữ liệu");
            }

            Statement st = con.createStatement();
            if ("".equals(jTextFieldTenTaiKhoan.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Login Name is required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(jTextFieldMatKhau.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                LoginName = jTextFieldTenTaiKhoan.getText();
                Password = jTextFieldMatKhau.getText();

                query = "SELECT * FROM nguoi_dung WHERE tenDangNhap = '" + LoginName + "'";
                ResultSet rs = (ResultSet) st.executeQuery(query);

                while (rs.next()) {
                    passDb = rs.getString("matKhau");
                    notFound = 1;
                }

                if (notFound == 1 && Password.equals(passDb)) {
                    Home HomeFrame = new Home();
                    HomeFrame.setVisible(true);
                    HomeFrame.pack();
                    HomeFrame.setLocationRelativeTo(null);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "INCORRECT LOGIN NAME OR PASSWORD", "Error",
                        JOptionPane.ERROR_MESSAGE);
                }

                jTextFieldMatKhau.setText("");
            }
        }catch(Exception e){
            logger.log(Level.SEVERE, "Đã xảy ra lỗi trong quá trình đăng nhập", e);
        }
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

    private void jButtonQuenMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuenMatKhauActionPerformed
        ForgotPasswordFrame fpf = new ForgotPasswordFrame();
        fpf.setVisible(true);
        fpf.pack();
    }//GEN-LAST:event_jButtonQuenMatKhauActionPerformed

    private void jButtonQuenMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonQuenMatKhauMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
    }//GEN-LAST:event_jButtonQuenMatKhauMouseEntered

    private void jButtonQuenMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonQuenMatKhauMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)); 
    }//GEN-LAST:event_jButtonQuenMatKhauMouseExited
                                           
    
    
    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LoginFramePackage.GradientPanelInner gradientPanelInner1;
    private LoginFramePackage.GradientPanelOuter gradientPanelOuter1;
    private javax.swing.JButton jButtonDangKi;
    private javax.swing.JButton jButtonDangNhap;
    private javax.swing.JButton jButtonQuenMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jTextFieldMatKhau;
    private javax.swing.JTextField jTextFieldTenTaiKhoan;
    private LoginFramePackage.LogoPanel logoPanel1;
    // End of variables declaration//GEN-END:variables
}
