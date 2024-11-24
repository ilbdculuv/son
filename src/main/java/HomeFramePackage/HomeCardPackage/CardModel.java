// đây là phần getter, setter của mấy cái card sắc màu
package HomeFramePackage.HomeCardPackage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CardModel {
    Icon icon;
    String title;
    String value;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public CardModel(Icon icon, String title, String value) {
        this.icon = icon;
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public Icon toIcon(String icon){
        return new ImageIcon(getClass().getClassLoader().getResource("/images/" + icon + ".png"));
    }
  
}
