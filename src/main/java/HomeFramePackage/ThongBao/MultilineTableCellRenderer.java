/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HomeFramePackage.ThongBao;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

class MultilineTableCellRenderer extends JTextArea implements TableCellRenderer {

    public MultilineTableCellRenderer() {
        setLineWrap(true);  // Cho phép xuống dòng
        setWrapStyleWord(true);  // Cho phép xuống dòng khi gặp khoảng trắng
        setOpaque(true);  // Đảm bảo renderer có nền màu
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : "");  // Đặt giá trị cho cell

        // Lấy chiều rộng cột và trừ đi 10px để tạo không gian cho việc xuống dòng
        int columnWidth = table.getColumnModel().getColumn(column).getWidth() - 10;

        // Tính toán chiều cao dựa trên nội dung
        setSize(columnWidth, Short.MAX_VALUE);  // Đặt chiều rộng theo cột và chiều cao lớn nhất để tính toán
        int preferredHeight = getPreferredSize().height;  // Lấy chiều cao ưa thích sau khi tính toán nội dung

        // Cập nhật chiều cao hàng nếu cần thiết
        if (table.getRowHeight(row) != preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }

        // Đặt màu nền và màu chữ tùy thuộc vào trạng thái lựa chọn
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        return this;  // Trả về renderer
    }
}
