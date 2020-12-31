import Modele.Joueur;
import Modele.Niveau;

import java.util.Objects;
import java.util.Scanner;

public class Game {

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


    public static void trouverJoueur(){
        String rep = Joueur.demandeJoueur();
        Joueur joueur;
        if (rep.equals("c")){
            joueur = Joueur.connexion();
        } else {
            joueur = Joueur.inscription();
        }
        lancement(joueur);
    }

    public static int choixLevel(Joueur joueur){
        int level =-1;
        System.out.println("Voici les niveaux auxquels vous avez accès :");
        joueur.afficheNiveauPossible();
        System.out.println("Choisissez le niveau auquel vous voulez jouer :");
        level = Integer.parseInt(new Scanner(System.in).next());
        return level;
    }
}
