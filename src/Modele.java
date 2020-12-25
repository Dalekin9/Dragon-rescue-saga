import java.awt.*;

public class Modele {
    private Color couleur;

    public Modele(int r, int g, int b){
        couleur = new Color(r,g,b);
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
