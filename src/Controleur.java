import javax.swing.*;
import java.awt.*;

public class Controleur {
    private Partie partie;
    private Vue view;

    public Controleur(Partie partie, Vue vue){
        this.partie = partie;
        view = vue;
    }

    public Controleur(Partie partie){
        this.partie = partie;
    }


    public void setView(Vue view) {
        this.view = view;
    }
}
