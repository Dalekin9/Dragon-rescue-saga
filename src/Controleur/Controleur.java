package Controleur;

import Modele.Joueur;
import Modele.Niveau;
import Modele.Partie;
import Vue.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Controleur {
    private static AffichageTerminal vueTerm = new AffichageTerminal();
    private static AffichageGraphique vueGraph = new AffichageGraphique(new Controleur());
    private Partie partie;

    public Controleur(){
    }

    public Controleur(Partie partie){
        this.partie = partie;
    }

    public void setPartie(Partie partie){
        this.partie = partie;
    }

    public void setVueTerm(AffichageTerminal vueTerm){
        this.vueTerm = vueTerm;
    }

    public void setVueGraph(AffichageGraphique affichageGraphiqueGraph){
        this.vueGraph = affichageGraphiqueGraph;
    }

    //commun
    public boolean connexionPossible(String ident, String pswd){
        if (Joueur.rechercheId(ident)){
            Joueur test = Joueur.getJoueur(ident);
            if (pswd.equals(test.getMdp())) {
                return true;
            }
        }
        return false;
    }

    public boolean inscriptionPossible(String ident, String pswd, String pswd2){
        if (!Joueur.rechercheId(ident)) {
            if (pswd.equals(pswd2)) {
                return true;
            }
        }
        return false;
    }



    //texte
    public static void lancement(Joueur gameur){
        vueTerm.setJoueur(gameur);
        int level = choixLevel(gameur);
        while (! gameur.levelEstPossible(level)){
            System.out.println("Vous n'avez pas accès à ce niveau !");
            level = choixLevel(gameur);
        }
        Niveau vaJouerA = Niveau.recupNiveau(level);
        Objects.requireNonNull(vaJouerA).remplir_Grille();
        Partie pA = new Partie(gameur,vaJouerA);

        pA.jouer();
    }

    public static int choixLevel(Joueur joueur){
        int level;
        System.out.println("Voici les niveaux auxquels vous avez accès :");
        vueTerm.afficheNiveauPossible();
        System.out.println("Choisissez le niveau auquel vous voulez jouer :");
        level = Integer.parseInt(new Scanner(System.in).next());
        return level;
    }

    public void demandeAction(boolean animAlea){
        System.out.println("Voulez-vous supprimer un bloc ou utiliser un objet? (B(loc)/O(bjet))");
        String[] coordsStr;
        int[] coords = new int[2];
        String a = new Scanner(System.in).next();
        switch (a.toLowerCase()) {
            case "b":
            case "bloc":
                boolean flag=false;
                do {
                    System.out.println("Entrez les coordonnées : (ex: B4)");
                    coordsStr = recupCoords();
                    if (coordsVerif(coordsStr)) {
                        coords = coordsInt(coordsStr);
                        if (coupValide(coords[0],coords[1])){
                            flag = true;
                        }
                    }
                }while(!flag);
                partie.actionBloc(coords,animAlea);
                break;
            case "o":
            case "objet":
                ArrayList<String> liste = partie.voirObjPossible(partie.getJoueur().getObjAcces(),partie.getLvl().getObjDispo());
                if (!liste.isEmpty()) {
                    boolean repOk = false;
                    do {
                        flag = false;
                        vueTerm.afficherObjetPossible(liste);
                        String rep = new Scanner(System.in).next();
                        switch (rep.toLowerCase(Locale.ROOT)) {
                            case "0" -> repOk = true;
                            case "bombe" -> {
                                do {
                                    System.out.println("Ou voulez utiliser la Bombe ? (ex: B4)");
                                    coordsStr = recupCoords();
                                    if (coordsVerif(coordsStr)) {
                                        coords = coordsInt(coordsStr);
                                        if (coupValide(coords[0], coords[1])) {
                                            flag = true;
                                        }
                                    }
                                } while (!flag);
                                partie.actionObj(animAlea,"bombe",coords);
                                repOk=true;
                            }
                            case "fusee", "fusée" -> {
                                do {
                                    System.out.println("Ou voulez utiliser la Fusée ? (ex: B)");
                                    String coordFus = new Scanner(System.in).next();
                                    coordsStr = new String[]{coordFus, "0"};
                                    if (coordsVerif(coordsStr)) {
                                        coords = coordsInt(coordsStr);
                                        if (coupValideFus(coords[1])) {
                                            flag = true;
                                            System.out.println("rgbnh,j;,hngbfvds");
                                        }
                                    }
                                } while (!flag);
                                partie.actionObj(animAlea,"fusee",coords);
                                repOk=true;
                            }
                            case "pioche" -> {
                                do {
                                    System.out.println("Ou voulez utiliser la Pioche ? (ex: B4)");
                                    coordsStr = recupCoords();
                                    if (coordsVerif(coordsStr)) {
                                        coords = coordsInt(coordsStr);
                                        if (coupValide(coords[0], coords[1])) {
                                            flag = true;
                                        }
                                    }
                                } while (!flag);
                                partie.actionObj(animAlea,"pioche",coords);
                                repOk=true;
                            }
                            default -> System.out.println("Réponse non reconnue ! Choisissez un objet (ou 0 pour revenir en arrière)");
                        }
                    }while (! repOk);
                } else {
                    System.out.println("Pas d'obejt disponible :(");
                }
                break;
            default:
                System.out.println("Entrée incorrecte !");
                demandeAction(animAlea);
        }
    }

    // Recupère les coordonnées de la case qui doit être affectée
    public String[] recupCoords(){
        String coords ;
        String[] coordStr = new String[2];
        do{
            coords = new Scanner(System.in).next();
            if(coords.length() != 2)System.out.println("L'entrée n'a pas la bonne taille. Réessayez !");
        }while(coords.length() != 2);
        coordStr[0] = coords.substring(0,1);
        coordStr[1] = coords.substring(1,2);
        return coordStr;
    }

    //Convertis les coordonnées données pour qu'elles soient utilisées
    public int[] coordsInt(String[] coords){
        int[] intab = new int[2];
        intab[0] = Integer.parseInt(coords[0]);
        intab[1] = Integer.parseInt(coords[1]);
        System.out.println("i : " + intab[0]);
        System.out.println("j : "+intab[1]);
        return intab;
    }

    public boolean coordsVerif(String[] coords) {
        int longueur = partie.getLvl().getGrid().getLongu();
        int hauteur = partie.getLvl().getGrid().getHaut();
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
        return partie.getLvl().getGrid().gril[i][j].getIs() != ' ' &&
                partie.getLvl().getGrid().gril[i][j].getIs() != '-' &&
                partie.getLvl().getGrid().gril[i][j].getIs() != 'a';
    }

    public boolean coupValideFus(int j){
        for (int i=0;i<partie.getLvl().getGrid().gril.length;i++){
            if (partie.getLvl().getGrid().gril[i][j].getIs() != ' ' &&
                    partie.getLvl().getGrid().gril[i][j].getIs() != '-' &&
                    partie.getLvl().getGrid().gril[i][j].getIs() != 'a'){
                return true;
            }
        }
        return false;
    }

    public void miseAJour(Joueur joueur,Niveau lvl){
        joueur.miseAJour(joueur,lvl);
    }

    public void miseAJourScore(int score,String name,Niveau lvl){
        lvl.miseAJourScore(score,name,lvl);
    }


    //graphique

    public static void lancement (){
        vueGraph = new AffichageGraphique(new Controleur());
        vueGraph.setVisible(true);
        vueGraph.ecranCo();
        //vueGraph.sommaire();
        //jeu.view.modeAventure();
        //jeu.view.regles();
        //jeu.view.presentationLevel(Launcher.init()[0]);
        //jeu.view.finLevel(Launcher.init()[0]);
    }

    public void connexion(){
        vueGraph.connexion();
    }

    public void inscription(){
        vueGraph.inscription();
    }

    public void seConnecter(String ident, String pswd){
        if (connexionPossible(ident,pswd)){
            Joueur a = Joueur.getJoueur(ident);
            for (int b: a.getNivAcess()){
                System.out.println(b);
            }
            vueGraph.setJoueur(a);
            vueGraph.sommaire();
        }
    }

    public void sInscrire(String ident, String pswd, String pswd2){
        if(inscriptionPossible(ident,pswd,pswd2)){
            vueGraph.setJoueur(Joueur.creerJoueur(ident,pswd));
            vueGraph.sommaire();
        }
    }

    public void modeAventure(){
        vueGraph.modeAventure();
    }

    public void modeInfini(){
        vueGraph.modeInfini();
    }

    public void regles(){
        vueGraph.regles();
    }

    public void choixLevel(Joueur joueur, int id){
        if (joueur.levelEstPossible(id)) {
            Niveau level = Niveau.recupNiveau(id);
            vueGraph.setNiveau(level);
            vueGraph.presentationLevel(level);
        }
    }

 public void goSommaire(){
     vueGraph.sommaire();
 }

}
