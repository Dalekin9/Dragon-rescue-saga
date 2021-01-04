package Controleur;

import Modele.*;
import Vue.AffichageGraphique;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
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




        /*ObjectOutputStream oos = null;
            Joueur test = new Joueur("admin","azerty");
            ArrayList<Integer> a = new ArrayList<Integer>();
            a.add(1);
            a.add(2);
            a.add(3);
            a.add(4);
            a.add(5);
            test.setNivAcess(a);
            ArrayList<String> b = new ArrayList<>();
            b.add("Fusee");
            b.add("Bombe");
            b.add("Pioche");
            test.setObjAcces(b);
            oos = new ObjectOutputStream(new FileOutputStream("joueur.ser"));
            oos.writeObject(test);*/







  /*          //Demande au joueur s'il veut jouer en mode texte ou graphique
            if (demandeJeu().equals("t")){
                Game.trouverJoueur();
            }else {
                //a voir si comme game on modifie pour pouvir relancer simplement apres fin jeu
                InterfaceJeu.lancement();
            }

            */


        } catch (final IOException e) {
            e.printStackTrace();
        }



        /*

        if (demandeJeu().equals("t")){
            trouverJoueur();
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
        System.out.println("De quelle façcon voulez-vous jouer ? T(exte) ou I(nterface) ?");
        String rep ;
        boolean ok;
        do{
            rep = new Scanner(System.in).next();
            switch (rep.toLowerCase()){
                case "t","texte","i","interface" -> ok = true;
                default -> {
                    System.out.println("Vous n'avez pas répondu correctement !");
                    System.out.println("De quelle façcon voulez-vous jouer ? T(exte) ou I(nterface) ?");
                    ok = false;
                }
            }
        }while(!ok);

        return String.valueOf(rep.toLowerCase().charAt(0));
    }
}
