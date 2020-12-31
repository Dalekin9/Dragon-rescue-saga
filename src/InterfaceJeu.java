import java.util.concurrent.TimeUnit;

public class InterfaceJeu extends Game {

    private Vue view ;
    private Controleur controleur;
    private Partie partie;
    public InterfaceJeu (){
        Joueur me = new Joueur("Ugo");
        Niveau[] levels = init();
        levels[0].remplir_Grille();
        partie = new Partie(me,levels[0]);
        controleur =  new Controleur(partie);
        view = new Vue (partie, controleur);
        controleur.setView(view);
        view.setVisible ( true );
        view.sommaire();
        //view.miseAJour();
        // controleur.sliderMoved();
    }
    public static void main ( String [] args ){ new InterfaceJeu (); }

}
