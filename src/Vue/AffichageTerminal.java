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

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setPartie(Partie partie){
        this.partie = partie;
        this.niveau = partie.getLvl();
        controleur = new Controleur(partie);
    }

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

    public void afficheEtat(boolean animAlea){
        System.out.println("Score : " + partie.getScore() + " points");
        System.out.println("Vous avez sauvé " + (niveau.getNb_animaux()-partie.getAnimRes()) + "/" + niveau.getNb_animaux() + " animaux");
        if (niveau.getNb_coup_max() != -1){
            System.out.println("Il vous reste " + partie.getCoupRes() + " coups");
        }
        afficherGrille();
        controleur.demandeAction(animAlea);
    }

    public void afficherGrille(){
        System.out.print("   ");
        for (int k=0;k<niveau.getGrid().gril[0].length;k++){
            System.out.print((char)(k+65) + " ");
        }
        System.out.println();
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

    public void afficherObjetPossible(ArrayList<String> liste){
        System.out.print("Vous pouvez utiliser : ");
        for (String s: liste){
            System.out.print(s+", ");
        }
        System.out.println();
        System.out.println("Que voulez-vous utiliser ? (0 pour revenir en arrière)");
    }

    public void affichageFinDePartie(int finJeu){
        if (finJeu != 2 ){
            if (finJeu == 1) {
                System.out.println("Vous avez épuisé le nombre de coups disponible.");
            } else if (finJeu == 3){
                System.out.println("Plus aucun coup n'est possible.");
            }
            System.out.println("Vous n'avez pas réussi à sauver tous les ours !");
            System.out.println(":(");
            System.out.println("Ce n'est pas grave, vous réussirez une prochaine fois !");

        } else {
            System.out.println("Bravo, vous avez sauvé tous les ours !");
            System.out.println(":)");
            System.out.println("Vous avez obtenu un score de " + this.partie.getScore() + " points");
            joueur.setObjAcces(joueur.remplirListeObj(niveau.getObjDispo()));
            controleur.miseAJour(joueur, niveau);
            if (this.partie.getScore() > niveau.recupDernierScore(niveau.best_score)){
                System.out.println("Vous êtes désormais dans le top 5 des meilleurs joueurs de ce niveau !");
                controleur.miseAJourScore(this.partie.getScore(),this.joueur.getNom(),this.niveau);
            }
        }
        Controleur.lancement(joueur);
    }

}
