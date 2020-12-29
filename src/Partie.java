import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Partie {
    private Joueur joueur;
    private Niveau lvl;
    private int score,coupRes,animRes;

    public Partie(Joueur pers, Niveau niveau){
        joueur = pers;
        lvl = niveau;
        score = 0;
        coupRes = lvl.nb_coup_max;
        animRes = lvl.nb_animaux;
    }

    public int[] coordsInt(String[] coords){
        int[] intab = new int[2];
        intab[0] = Integer.parseInt(coords[0]);
        intab[1] = Integer.parseInt(coords[1]);
        return intab;
    }

    public boolean coordsVerif(String[] coords){
        int longueur = lvl.getGrid().getLongu();
        int hauteur = lvl.getGrid().getHaut();
        ArrayList nmbrs = new ArrayList();
        ArrayList letters = new ArrayList();
        for(int i = 0; i<hauteur; i++)nmbrs.add(String.valueOf(i));
        for(int j = 0; j<longueur; j++)letters.add(String.valueOf((char)('a'+ j)));
        if(nmbrs.contains(coords[1]) && letters.contains(coords[0].toLowerCase())){
            for(int k = 0; k<longueur; k++) {
                if (letters.get(k).equals(coords[0].toLowerCase())) {
                    coords[0] = Integer.toString(k);
                }
            }
            String tmp = coords[1];
            coords[1]  = coords[0];
            coords[0]  = tmp;
        }else{
            System.out.println("Les coordonées ne sont pas valides !");
            return false;
        }
        return true;
    }

    public boolean coupValide(int i, int j){
        return lvl.getGrid().gril[i][j].getIs() != ' ' && lvl.getGrid().gril[i][j].getIs() != '-' && lvl.getGrid().gril[i][j].getIs() != 'a';
    }


    public void uneAction(boolean animAlea){
        System.out.println("Voulez-vous supprimer un bloc ou utiliser un objet? (B(loc)/O(bjet))");
        boolean flag = false;
        String[] coordsStr;
        int[] coords = new int[2];
        int colonnes = lvl.getGrid().gril[0].length;
        do {
            String a = new Scanner(System.in).next();
            switch (a.toLowerCase()) {
                case "b":
                case "bloc":
                    boolean ligne = false;
                    int posLignne = -1;
                    boolean bloc = false;
                    int[] posBloc = new int[]{-1,-1};
                    do {
                        coordsStr = joueur.recupCoords();
                        if (coordsVerif(coordsStr)) {
                            coords = coordsInt(coordsStr);
                            if (coupValide(coords[0],coords[1])){
                                flag = true;
                            }
                        }
                    }while(!flag);
                    lvl.getGrid().supprimer(coords[0],coords[1]);
                    lvl.getGrid().afficher();
                    lvl.getGrid().afficherC();

                    if (lvl.getGrid().coupSpecialLigne()){
                        ligne = true;
                        posLignne = lvl.getGrid().coupSpecialLignePos();
                    }
                    if (lvl.getGrid().coupSpecialBlocs()){
                        bloc = true;
                        posBloc = lvl.getGrid().coupSpecialBlocsPos();
                    }
                    score += lvl.getGrid().points();
                    if (lvl.decale) {
                        lvl.getGrid().faireDescendreQuandDecale();
                        lvl.getGrid().afficher();
                        lvl.getGrid().decaler();
                    } else {
                        lvl.getGrid().faireDescendre(animAlea);
                        lvl.getGrid().afficher();
                    }
                    System.out.println("test1");
                    while(lvl.getGrid().animalEnBas()) {
                        System.out.println("suorr ko");
                        lvl.getGrid().afficher();
                        int  ajout = lvl.getGrid().supprimerAnimalEnBas();
                        lvl.getGrid().afficher();
                        System.out.println(ajout);
                        System.out.println("suorr ko222222");
                        score += ajout;
                        animRes -= ajout/1000;
                        if (lvl.decale) {
                            System.out.println("ici0");
                            lvl.getGrid().faireDescendreQuandDecale();
                            lvl.getGrid().afficher();
                            System.out.println("ici1");
                            lvl.getGrid().decaler();
                            System.out.println("suorr ko333333");
                        } else {
                            lvl.getGrid().faireDescendre(animAlea);
                        }
                    }
                    if (ligne){
                        lvl.getGrid().poserFusee(posLignne);
                    }
                    if (bloc){
                        lvl.getGrid().poserBallon(posBloc);
                    }
                    coupRes--;
                    break;
                case "o":
                case "objet":
                    flag = true;
                    coupRes--;
                    break;
                default:
                    System.out.println("Réponse non reconnue ! Choisissez B(loc) ou O(bjet)");
                    break;
            }
        }while(!flag);
    }

    public void jouer(){
        lvl.afficher();
        do{
            System.out.println("Score : " + score + " points");
            System.out.println("Vous avez sauvé " + (lvl.getNb_animaux()-animRes) + "/" + lvl.getNb_animaux() + " animaux");
            if (lvl.nb_coup_max != -1){
                System.out.println("Il vous reste " + this.coupRes + " coups");
            }
            lvl.getGrid().afficher();
            int animAle = lvl.getGrid().aDAnimaux();
            if (animAle < 5) {
                uneAction(true);
            } else {
                uneAction(false);
            }
        }while(finJeu() == 0);
        affichageFin();
    }

    public int finJeu(){
        if(coupRes == 0){
            return 1;
        }else if(animRes == 0){
            return 2;
        } else if (! this.lvl.getGrid().coupPossible()){
            return 3;
        }else{
            return 0;
        }
    }

    public void affichageFin(){
        if (finJeu() != 2 ){
            if (finJeu() == 1) {
                System.out.println("Vous avez épuisé le nombre de coups disponible.");
            } else if (finJeu() == 3){
                System.out.println("Plus aucun coup n'est possible.");
            }
            System.out.println("Vous n'avez pas réussi à sauver tous les ours !");
            System.out.println(":(");
            System.out.println("Ce n'est pas grave, vous réussirez une prochaine fois !");

        } else {
            System.out.println("Bravo, vous avez sauvé tous les ours !");
            System.out.println(":)");
            System.out.println("Vous avez obtenu un score de " + this.score + " points");
            miseAJour(joueur);
            if (this.score > this.lvl.recupDernierScore(this.lvl.best_score)){
                System.out.println("Vous êtes désormais dans le top 5 des meilleurs joueurs de ce niveau !");
                this.lvl.miseAJourScore(this.score,this.joueur.getNom());
            }
        }
        Game.lancement(joueur);
    }

    public void miseAJour(Joueur joueur){
        ArrayList<Integer> update = joueur.getNivAcess();
        update.add(lvl.id+1);
        joueur.setNivAcess(update);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("joueur.ser"));
            oos.writeObject(joueur);
            oos.close();
        }catch (IOException e){
           e.printStackTrace();
        }
    }

}
