package Modele;

import Controleur.Controleur;
import Modele.Objet.Bombe;
import Modele.Objet.Fusee;
import Modele.Objet.Pioche;
import Vue.AffichageTerminal;

import java.io.*;
import java.util.ArrayList;
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


    //---------------------------------------------------------
    //                   --- PARTIE 1 ---                     -
    //                 Getters et Setters                     -
    //---------------------------------------------------------


    public Joueur getJoueur(){
        return joueur;
    }

    public int getScore(){
        return score;
    }

    public int getAnimRes(){
        return animRes;
    }

    public int getCoupRes(){
        return coupRes;
    }

    public Niveau getLvl(){
        return lvl;
    }


    //---------------------------------------------------------
    //                   --- PARTIE 2 ---                     -
    //                        Actions                         -
    //---------------------------------------------------------


    //action quand decide de supprimer un bloc
    public void actionBloc(int[] coords, boolean animAlea){
        boolean ligne = false;
        int posLignne = -1;
        boolean bloc = false;
        int[] posBloc = new int[]{-1,-1};
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
            lvl.getGrid().poserFusee(posLignne,coords[1]);
        }
        if (bloc){
            lvl.getGrid().poserBallon(posBloc);
        }
        coupRes--;
    }

    //action quand decide d'utiliser un objet
    public void actionObj(boolean animAlea,String obj, int[] coords){
        if (obj.equals("bombe")){
            System.out.println("1");
            Bombe bom = new Bombe(this.lvl.getGrid(), coords[0], coords[1]);
            bom.execute();
        } else if(obj.equals("fusee")){
            System.out.println("2");
            Fusee fus = new Fusee(this.lvl.getGrid(), coords[1]);
            fus.execute();
        } else {
            System.out.println("3");
            Pioche pio = new Pioche(this.lvl.getGrid(), coords[0], coords[1]);
            pio.execute();
        }
        remplacement(animAlea);
        coupRes--;
    }

    //remplacement du tableau après une action
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

    //---------------------------------------------------------
    //                   --- PARTIE 3 ---                     -
    //                      Méthodes                          -
    //---------------------------------------------------------

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

    //regarde les objet possible lors de la partie
    // -> mise en commun des objet accessible du Joueur
    //    et des objets disponible du niveau
    public ArrayList<String> voirObjPossible(ArrayList<String> a, ArrayList<String> b){
        ArrayList<String> fin = new ArrayList<>();
        fin = a;
        for (String s: b){
            if(!fin.contains(s)){
                fin.add(s);
            }
        }
        return fin;
    }


}
