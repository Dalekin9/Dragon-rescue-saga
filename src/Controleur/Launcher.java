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
        tab[0] = new Niveau(new Grille(new Case[7][7]),1,2,50,true);
        tab[1] = new Niveau(new Grille(new Case[8][5]),2,5,-1,true);
        tab[2] = new Niveau(new Grille(new Case[9][7]),3,3,-1,true);
        tab[3] = new Niveau(new Grille(new Case[8][9]),4,12,70,false);
        tab[4] = new Niveau(new Grille(new Case[8][9]),5,5,50,false);
        return tab;
    }

    public static void main(String[] args) throws IOException {

        /*
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

         */


        /*ObjectOutputStream oos = null;
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

         */


  /*          //Demande au joueur s'il veut jouer en mode texte ou graphique
            if (demandeJeu().equals("t")){
                Game.trouverJoueur();
            }else {
                //a voir si comme game on modifie pour pouvir relancer simplement apres fin jeu
                InterfaceJeu.lancement();
            }

            */

        /*
        } catch (final IOException e) {
            e.printStackTrace();
        }

         */
        if (demandeJeu().equals("t")){
            trouverJoueur();
        }else {
            //a voir si comme game on modifie pour pouvir relancer simplement apres fin jeu
            Controleur.lancement();
        }




        //a voir si comme game on modifie pour pouvir relancer simplement apres fin jeu
           // InterfaceJeu.lancement();


    }

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

    public static void trouverJoueur(){
        String rep = demandeJoueur();
        Joueur joueur;
        if (rep.equals("c")){
            joueur = connexion();
        } else {
            joueur = inscription();
        }
        Controleur.lancement(joueur);
    }

    public static String demandeJoueur(){
        System.out.println("Souhaitez-vous vous Co(nnecter) ou vous I(nscrire) ?");
        String rep;
        boolean ok;
        do{
            rep = new Scanner(System.in).next();
            switch (rep.toLowerCase(Locale.ROOT)) {
                case "co", "connecter", "i", "inscrire","connexion","inscription" -> ok = true;
                default -> {
                    ok = false;
                    System.out.println("Vous n'avez pas répondu correctement !");
                    System.out.println("Souhaitez-vous vous Co(nnecter) ou vous I(nscrire) ?");
                }
            }
        }while(!ok);
        return String.valueOf(rep.toLowerCase().charAt(0));
    }

    //fonction de connexion
    public static Joueur connexion(){
        System.out.println("Entrez votre identifiant : (ou i si vous voulez vous inscrire)");
        String rep ;
        Joueur joueur = null;
        boolean ok = false;
        do {
            rep = new Scanner(System.in).next();
            if (rep.equals("i")) {
                joueur = inscription();
                ok =true;
            } else if (!Joueur.rechercheId(rep)) { //regarder si present dans joueur.ser (ici cas negatif)
                System.out.println("Identifiant introuvable.");
                System.out.println("Entrez votre identifiant : (ou i si vous voulez vous inscrire)");
                ok = false;
            } else { // l'identifiant est présent dans joueur.ser, on demande le mot de passe
                System.out.println("Entrez votre mot de passe :");
                String repMdp = new Scanner(System.in).next();
                if (!Joueur.rechercheMdp(rep, repMdp)) { // mauvais mot de passe
                    ok = false;
                    System.out.println("Mot de passe incorrect !");
                    System.out.println("Entrez votre identifiant : (ou i si vous voulez vous inscrire)");
                } else { //mot de pass correct
                    joueur = Joueur.getJoueur(rep);
                    ok = true;
                }
            }
        } while (!ok);
        return joueur;
    }

    //fonction d'inscription
    public static Joueur inscription(){
        System.out.println("Choisissez un identifiant : (ou c si vous voulez vous connecter)");
        String rep ;
        Joueur joueur = null;
        boolean ok = false;
        do {
            rep = new Scanner(System.in).next();
            if (rep.equals("c")) {
                joueur = connexion();
                ok = true;
            } else if (!Joueur.rechercheId(rep)) { //regarder si present dans joueur.ser (ici cas negatif donc demande mot de passe)
                System.out.println("Entrez votre mot de passe :");
                String repMdp = new Scanner(System.in).next();
                System.out.println("Entrez votre mot de passe une nouvelle fois :");
                String repMdp2 = new Scanner(System.in).next();
                if (repMdp.equals(repMdp2)) { // les 2 mots de passe sont pareil donc création du joueur et ecriture sur joueur.ser
                    joueur = Joueur.creerJoueur(rep, repMdp);
                    ok = true;
                } else { //mot de pass incorrect
                    ok = false;
                    System.out.println("Vos mots de passes sont différents !");
                    System.out.println("Choisissez un identifiant : (ou c si vous voulez vous connecter)");
                }
            } else { // l'identifiant est présent dans joueur.ser, on ne peut pas l'utiliser
                System.out.println("Identifiant déjà utilisé.");
                System.out.println("Choisissez un identifiant : (ou c si vous voulez vous connecter)");
                ok = false;
            }
        } while (!ok);
        return joueur;
    }



}
