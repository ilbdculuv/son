// Màu sắc của mấy cái card đc design ở đây, và đây cũng là nơi Lâm phải thay mấy cái string kia bằng dữ liệu lấy từ csdl
package HomeFramePackage.HomeCardPackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class CardsGroupPanel extends javax.swing.JPanel {

    
    public CardsGroupPanel() {
        
        initComponents();
        setOpaque(false);
        URL nhanKhauUrl = getClass().getResource("/images/NhanKhau.png");
        URL thuPhiUrl = getClass().getResource("/images/ThuPhi.png");
        URL congNoUrl = getClass().getResource("/images/CongNo.png");
        URL tuThienUrl = getClass().getResource("/images/TuThien.png");
        jLabel1.setText("Tổng quan tháng " + java.time.LocalDate.now().getMonthValue() + " năm " + java.time.LocalDate.now().getYear());
        
        // Kết nối đến cơ sở dữ liệu
        try (Connection conn = Database.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Lấy tổng số hộ
            String sqlTongSoHo = "SELECT COUNT(*) AS tongSoHo FROM ho_gia_dinh";
            ResultSet rsTongSoHo = stmt.executeQuery(sqlTongSoHo);
            int tongSoHo = 0;
            if (rsTongSoHo.next()) {
                tongSoHo = rsTongSoHo.getInt("tongSoHo");
            }
            /*
            // Lấy tổng tiền thu phí
            String sqlTongTienThuPhi = "SELECT SUM(tien_thu_phi) AS tongTienThuPhi FROM thu_phi";
            ResultSet rsTongTienThuPhi = stmt.executeQuery(sqlTongTienThuPhi);
            double tongTienThuPhi = 0;
            if (rsTongTienThuPhi.next()) {
                tongTienThuPhi = rsTongTienThuPhi.getDouble("tongTienThuPhi");
            }
            
            // Lấy tổng công nợ
            String sqlTongCongNo = "SELECT SUM(cong_no) AS tongCongNo FROM cong_no";
            ResultSet rsTongCongNo = stmt.executeQuery(sqlTongCongNo);
            double tongCongNo = 0;
            if (rsTongCongNo.next()) {
                tongCongNo = rsTongCongNo.getDouble("tongCongNo");
            }

            // Lấy tổng đóng góp thiện nguyện
            String sqlTongDongGopThienNguyen = "SELECT SUM(tien_tu_thien) AS tongTienDongGopThienNguyen FROM tu_thien";
            ResultSet rsTongTienDongGop = stmt.executeQuery(sqlTongDongGopThienNguyen);
            double tongTienDongGopThienNguyen = 0;
            if (rsTongTienDongGop.next()) {
                tongTienDongGopThienNguyen = rsTongTienDongGop.getDouble("tongTienDongGopThienNguyen");
            }
            */
            // Cập nhật dữ liệu vào các CardPanel
            if (nhanKhauUrl != null && thuPhiUrl != null && congNoUrl != null && tuThienUrl != null) {
                cardPanel1.setData(new CardModel(new ImageIcon(nhanKhauUrl), "Số hộ chưa nộp phí", String.valueOf(tongSoHo)));
                cardPanel2.setData(new CardModel(new ImageIcon(thuPhiUrl), "Tổng tiền đã thu", "1.000.000"));
                cardPanel3.setData(new CardModel(new ImageIcon(congNoUrl), "Tổng tiền cần thu", "1.000.000"));
                cardPanel4.setData(new CardModel(new ImageIcon(tuThienUrl), "Tổng đóng góp", "1.000.000"));
            } else {
                System.out.println("Không tìm thấy một hoặc nhiều hình ảnh.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel1 = new HomeFramePackage.HomeCardPackage.CardPanel();
        cardPanel2 = new HomeFramePackage.HomeCardPackage.CardPanel();
        cardPanel3 = new HomeFramePackage.HomeCardPackage.CardPanel();
        cardPanel4 = new HomeFramePackage.HomeCardPackage.CardPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        cardPanel1.setColor1(new java.awt.Color(51, 153, 255));
        cardPanel1.setColor2(new java.awt.Color(102, 255, 204));

        cardPanel2.setColor1(new java.awt.Color(0, 102, 153));

        cardPanel3.setColor1(new java.awt.Color(101, 78, 163));
        cardPanel3.setColor2(new java.awt.Color(234, 175, 200));

        cardPanel4.setColor1(new java.awt.Color(69, 104, 220));
        cardPanel4.setColor2(new java.awt.Color(172, 182, 229));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setText("Tổng quan tháng này");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel1;
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel2;
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel3;
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel4;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
