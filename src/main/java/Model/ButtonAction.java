package Model;

import javax.swing.JButton;
import com.formdev.flatlaf.FlatClientProperties;

public class ButtonAction extends JButton {
    
    public ButtonAction() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;" // Corrected focusWidth
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background;");
    }
}
