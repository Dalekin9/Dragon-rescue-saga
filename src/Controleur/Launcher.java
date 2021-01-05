package Controleur;


import Modele.Case;
import Modele.Grille;
import Modele.Joueur;
import Modele.Niveau;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Launcher {


    public static Niveau[] init(){
        Niveau[] tab = new Niveau[5];
        tab[0] = new Niveau(new Grille(new Case[7][7]),1,2,-1,true);
        tab[1] = new Niveau(new Grille(new Case[8][5]),2,5,-1,true);
        tab[2] = new Niveau(new Grille(new Case[9][7]),3,3,-1,true);
        tab[3] = new Niveau(new Grille(new Case[8][9]),4,12,70,false);
        tab[4] = new Niveau(new Grille(new Case[8][9]),5,5,50,false);
        return tab;
    }



    public static void main(String[] args) throws IOException {

        ObjectOutputStream oos = null;
        //ObjectInputStream ois = null;
        Niveau[] levels = init();
        try {
            FileOutputStream fos = new FileOutputStream("level.ser");
            oos = new ObjectOutputStream(fos);
            //écriture des niveaux sur le fichier level.ser
            for (Niveau level : levels) {
                oos.writeObject(level);
            }
            oos.flush();
            oos.close();

            oos = null;
            Joueur test = new Joueur("admin","azerty");
            ArrayList<Integer> a = new ArrayList<Integer>();
            a.add(1);
            a.add(2);
            a.add(3);
            a.add(4);
            a.add(5);
            test.setNivAcess(a);
            oos = new ObjectOutputStream(new FileOutputStream("joueur.ser"));
            oos.writeObject(test);
        } catch (final IOException e) {
            e.printStackTrace();
        }



/*
        if (demandeJeu().equals("t")){
            Controleur.trouverJoueur();
        }else {
            //a voir si comme game on modifie pour pouvir relancer simplement apres fin jeu
            Controleur.lancement();
        }
 */
        //a voir si comme game on modifie pour pouvir relancer simplement apres fin jeu
        Controleur.lancement();


    }

    //demande au joueur de quelle manière il souhaite jouer
    // -> Interface Graphique ou via le terminal
    public static String demandeJeu(){
        System.out.println("De quelle façcon voulez-vous jouer ?");
        System.out.println("T(exte) ou I(nterface) ?");
        String rep ;
        boolean ok;
        do{
            rep = new Scanner(System.in).next();
            switch (rep.toLowerCase()){
                case "t","texte","i","interface" -> ok = true;
                default -> {
                    System.out.println("Vous n'avez pas répondu correctement !");
                    System.out.println("De quelle façcon voulez-vous jouer ?");
                    System.out.println("T(exte) ou I(nterface) ?");
                    ok = false;
                }
            }
        }while(!ok);
        return String.valueOf(rep.toLowerCase().charAt(0));
    }
}
