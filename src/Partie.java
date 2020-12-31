import Modele.Case;
import Modele.Grille;
import Modele.Joueur;
import Modele.Niveau;
import Modele.Objet.Bombe;
import Modele.Objet.Fusee;
import Modele.Objet.Objet;
import Modele.Objet.Pioche;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

public class Partie {
    private Joueur joueur;
    private Niveau lvl;
    private int score,coupRes,animRes;

    public Partie(Joueur pers, Niveau niveau){
        joueur = pers;
        lvl = niveau;
        score = 0;
        coupRes = lvl.getNb_coup_max();
        animRes = lvl.getNb_animaux();
    }

    //Convertis les coordonnées données pour qu'elles soient utilisées
    public int[] coordsInt(String[] coords){
        int[] intab = new int[2];
        intab[0] = Integer.parseInt(coords[1]);
        intab[1] = Integer.parseInt(coords[0]);
        return intab;
    }

    public boolean coordsVerif(String[] coords) {
        int longueur = lvl.getGrid().getLongu();
        int hauteur = lvl.getGrid().getHaut();
        ArrayList nmbrs = new ArrayList();
        ArrayList letters = new ArrayList();
        for (int i = 0; i < hauteur; i++) nmbrs.add(String.valueOf(i));
        for (int j = 0; j < longueur; j++) letters.add(String.valueOf((char) ('a' + j)));
        if (nmbrs.contains(coords[1]) && letters.contains(coords[0].toLowerCase())) {
            for (int k = 0; k < longueur; k++) {
                if (letters.get(k).equals(coords[0].toLowerCase())) {
                    coords[0] = Integer.toString(k);
                }
            }
            String tmp = coords[1];
            coords[1] = coords[0];
            coords[0] = tmp;
        } else {
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
                        System.out.println("Entrez les coordonnées : (ex: B4)");
                        coordsStr = joueur.recupCoords();
                        if (coordsVerif(coordsStr)) {
                            coords = coordsInt(coordsStr);
                            if (coupValide(coords[0],coords[1])){
                                flag = true;
                            }
                        }
                    }while(!flag);
                    lvl.getGrid().supprimer(coords[0],coords[1]);
                    //recherche si des coups spéciaux ont été réalisés
                    if (lvl.id >= 3) {
                        if (lvl.getGrid().coupSpecialLigne()) {
                            ligne = true;
                            posLignne = lvl.getGrid().coupSpecialLignePos();
                        }
                    }
                    if (lvl.id >= 4) {
                        if (lvl.getGrid().coupSpecialBlocs()) {
                            bloc = true;
                            posBloc = lvl.getGrid().coupSpecialBlocsPos();
                        }
                    }
                    score += lvl.getGrid().points();
                    //remplacer après suppression de bloc
                    remplacement(animAlea);
                    //s'occupe de poser les objets quand il y a des coups spéciaux
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
                    ArrayList<String> liste = voirObjPossible(joueur.getObjAcces(),lvl.getObjDispo());
                    if (! liste.isEmpty()) {
                        boolean repOk = false;
                        do {
                            Objet.afficherPossible(liste);
                            String rep = new Scanner(System.in).next();
                            switch (rep.toLowerCase(Locale.ROOT)) {
                                case "0" -> repOk = true;
                                case "bombe" -> {
                                    do {
                                        System.out.println("Ou voulez utiliser la Bombe ? (ex: B4)");
                                        coordsStr = joueur.recupCoords();
                                        if (coordsVerif(coordsStr)) {
                                            coords = coordsInt(coordsStr);
                                            if (coupValide(coords[0], coords[1])) {
                                                flag = true;
                                            }
                                        }
                                    } while (!flag);
                                    Bombe bom = new Bombe(this.lvl.getGrid(), coords[0], coords[1]);
                                    bom.execute();
                                    remplacement(animAlea);
                                    repOk = true;
                                    coupRes--;
                                }
                                case "fusee", "fusée" -> {
                                    do {
                                        System.out.println("Ou voulez utiliser la Fusée ? (ex: B)");
                                        String coordFus = new Scanner(System.in).next();
                                        coordsStr = new String[]{coordFus, "0"};
                                        if (coordsVerif(coordsStr)) {
                                            coords = coordsInt(coordsStr);
                                            if (coupValide(coords[0], coords[1])) {
                                                flag = true;
                                            }
                                        }
                                    } while (!flag);
                                    Fusee fus = new Fusee(this.lvl.getGrid(), coords[1]);
                                    fus.execute();
                                    remplacement(animAlea);
                                    repOk = true;
                                    coupRes--;
                                }
                                case "pioche" -> {
                                    do {
                                        System.out.println("Ou voulez utiliser la Pioche ? (ex: B4)");
                                        coordsStr = joueur.recupCoords();
                                        if (coordsVerif(coordsStr)) {
                                            coords = coordsInt(coordsStr);
                                            if (coupValide(coords[0], coords[1])) {
                                                flag = true;
                                            }
                                        }
                                    } while (!flag);
                                    Pioche pio = new Pioche(this.lvl.getGrid(), coords[0], coords[1]);
                                    pio.execute();
                                    remplacement(animAlea);
                                    repOk = true;
                                    coupRes--;
                                }
                                default -> System.out.println("Réponse non reconnue ! Choisissez un objet (ou 0 pour revenir en arrière)");
                            }
                        }while (! repOk);
                    }
                    break;
                default:
                    System.out.println("Réponse non reconnue, choisissez B ou O");
                    break;
            }
        }while(!flag);
    }

    public void jouer(){
        lvl.afficher();
        do{
            System.out.println("Score : " + score + " points");
            System.out.println("Vous avez sauvé " + (lvl.getNb_animaux()-animRes) + "/" + lvl.getNb_animaux() + " animaux");
            if (lvl.getNb_coup_max() != -1){
                System.out.println("Il vous reste " + this.coupRes + " coups");
            }
            lvl.getGrid().afficher();
            int animAle = lvl.getGrid().aDAnimaux();
            uneAction(animAle < 5);
        }while(finJeu() == 0);
        affichageFin();
    }

    //Vérfie les conditions de fin de jeu
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
            joueur.setObjAcces(joueur.remplirListeObj(this.lvl.getObjDispo()));
            miseAJour(joueur);
            if (this.score > this.lvl.recupDernierScore(this.lvl.best_score)){
                System.out.println("Vous êtes désormais dans le top 5 des meilleurs joueurs de ce niveau !");
                miseAJourScore(this.score,this.joueur.getNom());
            }
        }
        Game.lancement(joueur);
    }

    public void miseAJour(Joueur joueur){
        ArrayList<Integer> update = joueur.getNivAcess();
        if (!update.contains(lvl.id+1)){
            update.add(lvl.id + 1);
        }
        joueur.setNivAcess(update);
        ArrayList<Joueur> liste = new ArrayList<>();
        ObjectInputStream ois;
        ObjectOutputStream oos;
        try {
            FileInputStream fis = new FileInputStream("joueur.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Joueur test = (Joueur) ois.readObject();
                    if (test.getNom() == joueur.getNom()){
                        liste.add(joueur);
                    }else {
                        liste.add(test);
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream("joueur.ser");
            oos = new ObjectOutputStream(fos);
            for (Joueur a : liste) {
                oos.writeObject(a);
            }
            oos.flush();
            oos.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void miseAJourScore(int score, String joueur){
        if (! this.lvl.best_score.isEmpty()) {
            int last = this.lvl.recupDernierScore(this.lvl.best_score);
            this.lvl.best_score.remove(last);
        }
        this.lvl.best_score.put(score,joueur);
        this.lvl.best_score = new TreeMap<Integer,String>(this.lvl.best_score);
        this.lvl.remplir_Grille();
        Niveau a = new Niveau(new Grille(new Case[this.lvl.getGrid().gril.length][this.lvl.getGrid().gril[0].length]),lvl.id,lvl.getNb_animaux(),lvl.getNb_coup_max(), lvl.isDecale());
        a.best_score = lvl.best_score;
        ArrayList<Niveau> levels = new ArrayList<>();
        ObjectInputStream ois;
        ObjectOutputStream oos;
        try {
            FileInputStream fis = new FileInputStream("level.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Niveau test = (Niveau) ois.readObject();
                    if (test.id == lvl.id){
                        levels.add(lvl);
                    }else {
                        levels.add(test);
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream("level.ser");
            oos = new ObjectOutputStream(fos);
            for (Niveau level : levels) {
                oos.writeObject(level);
            }
            oos.flush();
            oos.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    ArrayList<String> voirObjPossible(ArrayList<String> a, ArrayList<String> b){
        ArrayList<String> fin = new ArrayList<>();
        fin = a;
        for (String s: b){
            if(!fin.contains(s)){
                fin.add(s);
            }
        }
        return fin;
    }

    public void remplacement(boolean animAlea){
        if (lvl.isDecale()) {
            lvl.getGrid().faireDescendreQuandDecale();
            lvl.getGrid().decaler();
        } else {
            lvl.getGrid().faireDescendre(animAlea);
        }
        //vérifie qu'il n'y a plus d'animaux en bas
        while(lvl.getGrid().animalEnBas()) {
            int  ajout = lvl.getGrid().supprimerAnimalEnBas();
            score += ajout;
            animRes -= ajout/1000;
            if (lvl.isDecale()) {
                lvl.getGrid().faireDescendreQuandDecale();
                lvl.getGrid().decaler();
            } else {
                lvl.getGrid().faireDescendre(animAlea);
            }
        }
    }

}
