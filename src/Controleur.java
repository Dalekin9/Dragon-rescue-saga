import javax.swing.*;
import java.awt.*;

public class Controleur {
    private Modele model;
    private Vue view;

    public Controleur(Modele modele, Vue vue){
        model = modele;
        view = vue;
    }

    public Controleur(Modele modele){
        model = modele;
    }


    public void setView(Vue view) {
        this.view = view;
    }
}
