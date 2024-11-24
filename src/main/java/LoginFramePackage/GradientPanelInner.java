
package LoginFramePackage;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GradientPanelInner extends javax.swing.JPanel {

    public GradientPanelInner() {
        initComponents();
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics grphcs){
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        //Tạo màu gradient
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint grd = new GradientPaint(0, 0, Color.decode("#43cea2"), 0 ,getWidth(), Color.decode("#185a9d"));
        grphcs2d.setPaint(grd);
        //Làm mềm cạnh ô vuông
        grphcs2d.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(grphcs);
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
