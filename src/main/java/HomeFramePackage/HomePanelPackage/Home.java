
package HomeFramePackage.HomePanelPackage;

import HomeFramePackage.HomeMenuPackage.EventMenuSelected;
import KhoanThuPackage.KhoanThuPanel;
import LoginFramePackage.LoginFrame;
import TaiKhoanPackage.TaiKhoanPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import javax.swing.UIManager;
import raven.popup.GlassPanePopup;
public class Home extends javax.swing.JFrame {
    // Ho ten
    public void setName(String name){
        menuPanel.setName(name);
    }
    
    public Home() {
        GlassPanePopup.install(this);
        initComponents();
        
        menuPanel.addEventMenuSelected(new EventMenuSelected(){
           
            @Override
            public void selected(int index) {
                
                switch (index) {
                    case 0 -> setForm(new HomePanel());
                    case 1 -> setForm(new TaiKhoanPanel());
                    case 2 -> setForm(new KhoanThuPanel());
                    case 6 -> {
                        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                            Home.this,
                            "Bạn có chắc chắn muốn đăng xuất không?",
                            "Xác nhận đăng xuất",
                            javax.swing.JOptionPane.YES_NO_OPTION,
                            javax.swing.JOptionPane.QUESTION_MESSAGE
                        );
                        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                            LoginFrame reg = new LoginFrame();
                            reg.setVisible(true);
                            reg.pack();
                            Home.this.dispose();
                        }
                    }
                    default -> setForm(new HomePanel());
                }
            }
            
        });
        setForm(new HomePanel());
    }
    
    
    public void setForm(JComponent component){
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        mainPanel.removeAll();
        mainPanel.add(component, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(mainPanel);
        SwingUtilities.invokeLater(() -> {
            mainPanel.revalidate(); // Xác thực lại layout
            mainPanel.repaint(); // Vẽ lại mainPanel
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new HomeFramePackage.HomeMenuPackage.MenuPanel();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private HomeFramePackage.HomeMenuPackage.MenuPanel menuPanel;
    // End of variables declaration//GEN-END:variables
}
