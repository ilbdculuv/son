
package HomeFramePackage.HomeMenuPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class MenuItem extends javax.swing.JPanel {
 
    private boolean selected;
    private boolean over;

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }
    
    
    public MenuItem(MenuModel data) {
        initComponents();
        setOpaque(false);
        if(data.getType()==MenuModel.MenuType.MENU){
            Icon.setIcon(data.toIcon());
            menuItem.setText(data.getName());
        }else if (data.getType()==MenuModel.MenuType.TITLE){
            Icon.setText(data.getName());
            Icon.setFont(new Font("Calibri",Font.BOLD,14));
            menuItem.setVisible(false);
        }else{
            menuItem.setText(" ");
        }
    }
    
    public void setSelected(boolean s){
        this.selected = s;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs){
        if(selected || over){
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        grphcs2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if(selected){
            grphcs2d.setColor(new Color(255,255,255,80));
        }else{
            grphcs2d.setColor(new Color(255,255,255,20));
        }
        grphcs2d.fillRoundRect(0, 0, getWidth()-20, getHeight(), 0, 0);
        }
        super.paintComponent(grphcs);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuItem = new javax.swing.JLabel();
        Icon = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(201, 35));

        menuItem.setBackground(new java.awt.Color(255, 255, 255));
        menuItem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        menuItem.setForeground(new java.awt.Color(255, 255, 255));
        menuItem.setText("MenuItem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(menuItem)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Icon, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(menuItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel menuItem;
    // End of variables declaration//GEN-END:variables
}
