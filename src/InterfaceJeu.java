import java.util.concurrent.TimeUnit;

public class InterfaceJeu {

    private Vue view ;
    private Controleur controleur;
    public InterfaceJeu (){
        controleur =  new Controleur();
        view = new Vue (controleur);
        controleur.setView(view);
        view.setVisible (true);
        //view.sommaire();
        //view.miseAJour();
        //controleur.sliderMoved();
    }
    public static void lancement (){
        InterfaceJeu jeu = new InterfaceJeu ();
        //jeu.view.ecranCo();
        //jeu.view.sommaire();
        //jeu.view.modeAventure();
        //jeu.view.regles();
        //jeu.view.presentationLevel(Launcher.init()[0]);
        jeu.view.finLevel(Launcher.init()[0]);
    }

}
