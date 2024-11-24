 package Model;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;

public class CheckBoxTableHeader extends JCheckBox implements TableCellRenderer {

    private final JTable table;
    private final int column;

    public CheckBoxTableHeader(JTable table, int column) {
        this.table = table;
        this.column = column;
        init();
    }

    public void init() {
        putClientProperty(FlatClientProperties.STYLE, "background:$table.background");
        setHorizontalAlignment(SwingConstants.CENTER);

        // Add mouse listener to the table header
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int col = table.columnAtPoint(me.getPoint());
                    if (col == column) {
                        // Toggle the selection state
                        setSelected(!isSelected());
                        selectedTableRow(isSelected());
                    }
                }
            }
        });

        // Listen for changes in the table to update the header checkbox state
        table.getModel().addTableModelListener((TableModelEvent tme) -> {
            if (tme.getColumn() == column||tme.getType() == TableModelEvent.DELETE) { 
                // them cai ||tme.getType() == TableModelEvent.DELETE ô checkbox không đánh dấu các hàng khi bảng trống
                checkRow();
            }
        });
    }

    public void checkRow() {
        if (table.getRowCount() == 0) {
            setSelected(false);
            return;
        }
        
        boolean initValue = table.getValueAt(0, column) instanceof Boolean ? (Boolean) table.getValueAt(0, column) : false;
        for (int i = 1; i < table.getRowCount(); i++) {
            if (!(table.getValueAt(i, column) instanceof Boolean)) {
                continue; // Skip non-Boolean values
            }
            boolean v = (Boolean) table.getValueAt(i, column);
            if (initValue != v) {
                putClientProperty(FlatClientProperties.SELECTED_STATE, FlatClientProperties.SELECTED_STATE_INDETERMINATE);
                table.getTableHeader().repaint();
                return;
            }
        }
        putClientProperty(FlatClientProperties.SELECTED_STATE, null);
        setSelected(initValue);
        table.getTableHeader().repaint();
    }

    public void selectedTableRow(boolean selected) {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(selected, i, column);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }

    @Override
    public void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();

        // Set color for the separator at the bottom of the header
        g2.setColor(UIManager.getColor("TableHeader.bottomSeparatorColor"));

        // Size of the separator
        float size = UIScale.scale(1f);

        // Draw the separator at the bottom of the header
        g2.fill(new Rectangle2D.Float(0, getHeight() - size, getWidth(), size));

        g2.dispose();
        super.paintComponent(grphcs);
    }
}
