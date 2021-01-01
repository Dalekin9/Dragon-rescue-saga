package Controleur;

import Modele.*;
import Vue.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controleur {
    private static AffichageTerminal vueTerm;
    private Vue vueGraph;
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

    public void setVueGraph(Vue vueGraph){
        this.vueGraph = vueGraph;
    }

    public static void lancement(Joueur gameur){
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
        int level =-1;
        System.out.println("Voici les niveaux auxquels vous avez accès :");
        vueTerm.afficheNiveauPossible();
        System.out.println("Choisissez le niveau auquel vous voulez jouer :");
        level = Integer.parseInt(new Scanner(System.in).next());
        return level;
    }

    public void demandeAction(){
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
                //do while pr coordonnées
                //-> lance actionBloc(int[]) dans modele(partie)
                break;
            case "o":
            case "objet":
                //do while pr coordonnées
                //-> lance actionObj(nom obj,int[])  dans modele(partie)
                break;
            default:
                System.out.println("Entrée incorrecte !");
                demandeAction();
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
        intab[0] = Integer.parseInt(coords[1]);
        intab[1] = Integer.parseInt(coords[0]);
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
        return partie.getLvl().getGrid().gril[i][j].getIs() != ' ' && partie.getLvl().getGrid().gril[i][j].getIs() != '-' && partie.getLvl().getGrid().gril[i][j].getIs() != 'a';
    }

}
