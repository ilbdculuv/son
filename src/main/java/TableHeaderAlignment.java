import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class TableHeaderAlignment {
    private final TableCellRenderer oldHeaderRenderer;

    public TableHeaderAlignment(JTable table) {
        this.oldHeaderRenderer = table.getTableHeader().getDefaultRenderer();

        // Cập nhật renderer cho tất cả các tiêu đề cột
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) oldHeaderRenderer.getTableCellRendererComponent(jtable, o, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);  // Căn giữa cho tiêu đề cột
                return label;
            }
        });
    }
}
