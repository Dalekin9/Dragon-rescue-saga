import java.util.concurrent.TimeUnit;

public class InterfaceJeu {

    private Vue view ;
    private Controleur controleur;
    private Modele modele;
    public InterfaceJeu (){
        modele = new Modele(0,22,76);
        controleur =  new Controleur(modele);
        view = new Vue (modele, controleur);
        controleur.setView(view);
        view.setVisible ( true );
        view.sommaire();
        //view.miseAJour();
        //controleur.sliderMoved();
    }
    public static void main ( String [] args ){ new InterfaceJeu (); }

}
