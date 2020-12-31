import javax.swing.*;
import java.awt.*;

public class Modele {
    private Icon icon;

    public Modele(String path){
        icon = new ImageIcon( "C:\\Users\\Dalekin\\Pictures\\raw.jpg");
    }

    public Icon getIcon() {
        return icon;
    }

    public void setCouleur(Icon icon) {
        this.icon = icon;
    }
}
