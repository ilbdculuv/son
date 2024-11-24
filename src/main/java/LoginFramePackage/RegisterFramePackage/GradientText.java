package LoginFramePackage.RegisterFramePackage;

import javax.swing.*;
import java.awt.*;

public class GradientText extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Tạo gradient
        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#808080"), 0, getHeight(), Color.decode("#3fada8"));
        g2d.setPaint(gradient);

        // Thiết lập font chữ
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // Vẽ chữ
        String text = "Đăng kí tài khoản";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2;

        // Vẽ chữ
        g2d.drawString(text, x, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

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
