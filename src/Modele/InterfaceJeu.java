package Modele;

import Controleur.Controleur;
import Vue.AffichageGraphique;

public class InterfaceJeu {

    private AffichageGraphique view ;
    private Controleur controleur;
    public InterfaceJeu (){
        controleur =  new Controleur();
        view = new AffichageGraphique(controleur);
        //controleur.setView(view);
        view.setVisible (true);
        //view.sommaire();
        //view.miseAJour();
        //controleur.sliderMoved();
    }


}
