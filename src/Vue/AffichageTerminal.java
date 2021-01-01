package Vue;

import Controleur.Controleur;
import Modele.Joueur;
import Modele.Niveau;
import Modele.Partie;

import java.util.ArrayList;

public class AffichageTerminal {

    Joueur joueur;
    Niveau niveau;
    Partie partie;
    Controleur controleur;

    public AffichageTerminal(){}

    public void afficheNiveauPossible(){
        int parcours = 0;
        ArrayList<Integer> copie = joueur.getNivAcess();
        for (Integer parcrs: copie){
            if (parcours/3 == 0 && parcours != 0){
                System.out.println();
            }
            System.out.print(" ----- \n | "+  + copie.get(parcours) + " | \n ----- ");
            parcours++;
        }
        System.out.println();
    }

    //affichage des propriétés du niveau en mode texte
    public void afficherPresentNiveau(){
        System.out.println("Niveau "+niveau.id);
        System.out.println("Objectifs :");
        System.out.println("Sauver "+niveau.getNb_animaux()+" ours");
        if (niveau.getNb_coup_max() != -1){
            System.out.println(niveau.getNb_coup_max()+" coups maximum");
        }
        afficher_score();
    }

    public void afficher_score(){
        System.out.println("Meilleurs scores :");
        int compt = 1;
        for (var item : niveau.best_score.entrySet()) {
            System.out.println(compt + " : " + item.getKey() +" -> " + item.getValue());
            compt++;
        }
        while (compt <= 5){
            System.out.println(compt + " : Pas encore de score");
            compt++;
        }
        System.out.println();
    }

    public void afficheEtat(){
        System.out.println("Score : " + partie.getScore() + " points");
        System.out.println("Vous avez sauvé " + (niveau.getNb_animaux()-partie.getAnimRes()) + "/" + niveau.getNb_animaux() + " animaux");
        if (niveau.getNb_coup_max() != -1){
            System.out.println("Il vous reste " + partie.getCoupRes() + " coups");
        }
        afficherGrille();
        controleur.demandeAction();
    }

    public void afficherGrille(){
        System.out.print("   ");
        for (int k=0;k<niveau.getGrid().gril[0].length;k++){
            System.out.print((char)(k+65) + " ");
        }
        System.out.println();System.out.println();
        for (int i = 0;i<niveau.getGrid().gril.length;i++) {
            if (i<10) {
                System.out.print(i+ "  ");
            }else {
                System.out.print(i+" ");
            }
            for (int j=0;j<niveau.getGrid().gril[0].length;j++) {
                System.out.print(niveau.getGrid().gril[i][j].getIs() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
