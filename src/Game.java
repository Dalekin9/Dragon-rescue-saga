import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    
    public static void lancement(){
        Joueur gameur = trouverJoueur();
        System.out.println("je suis "+ gameur.getNom());
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


    public static Joueur trouverJoueur(){
        String rep = Joueur.demandeJoueur();
        if (rep.equals("in")){
            return Joueur.connexion();
        } else {
            return Joueur.inscription();
        }
    }


    public static int choixLevel(Joueur joueur){
       int level =-1;
            System.out.println("Voici les niveaux auxquels vous avez accès :");
            joueur.afficheNiveauPossible();
            System.out.println("Choisissez le niveau auquel vous voulez jouer :");
            level = Integer.parseInt(new Scanner(System.in).next());
            return level;
    }

    /* a voir apres
            //lecture des niveaux sur le fichier level.ser
            while (fis.available() > 0) {
                Niveau lvl = (Niveau) ois.readObject ();
                lvl.remplir_Grille();
                lvl.afficher();
                lvl.getGrid().afficher();
            }
             */
}
