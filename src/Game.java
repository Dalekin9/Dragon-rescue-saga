import java.io.*;
import java.util.Arrays;

public class Game {

    //a deplacer dans launcher
    public static Niveau[] init(){
        Niveau[] tab = new Niveau[5];
        tab[0] = new Niveau(new Grille(new Case[7][7]),1,2,5,3000);
        tab[1] = new Niveau(new Grille(new Case[8][5]),2,5,0,0);
        tab[2] = new Niveau(new Grille(new Case[9][7]),3,3,0,0);
        tab[3] = new Niveau(new Grille(new Case[8][9]),4,12,0,0);
        tab[4] = new Niveau(new Grille(new Case[8][9]),5,5,0,0);
        return tab;
    }
    
    
    public static void lancement(){
        /**/
        Joueur me = new Joueur("Ugo");
        Niveau[] levels = init();
        int level = choixLevel();
        levels[0].remplir_Grille();
        Partie pA = new Partie(me,levels[0]);
        pA.jouer();
    }

    // a deplacer dans launcher pck aussi utiliser pr l'interface
    public static int choixLevel(){
        //demande un level
        //regarde si acces au level
        //si oui ok
        //si non alors le dire et préciser quel level max est autorise

        //ou alors commencer par dire liste des lvl autorisé
        //demande au joueur
        //si level ok alors ok
        //si non alors redemande
    }
}
