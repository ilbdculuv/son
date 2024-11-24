
package LoginFramePackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LogoPanel extends javax.swing.JPanel {

    private BufferedImage image;

    public LogoPanel() {
        initComponents();
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics grphcs){
        super.paintComponent(grphcs); // Gọi super để vẽ các thành phần cơ bản của JPanel
        
        // Đặt ảnh nền
        try {
            // Đường dẫn tới ảnh trong thư mục resources/images
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/big_icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(LogoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Vẽ ảnh lên JPanel
        if (image != null) {
            grphcs.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
