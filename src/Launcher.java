import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args){
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Niveau[] levels = Game.init();
        try {
            FileOutputStream fos = new FileOutputStream("level.ser");
            oos = new ObjectOutputStream(fos);
            FileInputStream fis = new FileInputStream("level.ser");
            ois = new ObjectInputStream(fis);
            //écriture des niveaux sur le fichier level.ser
            for (Niveau level : levels) {
                oos.writeObject(level);
            }
            oos.flush();

            //Demande au joueur s'il veut jouer en mode texte ou graphique
            if (demandeJeu() == 'T'){
                Game.lancement();
            }else {
                InterfaceJeu.lancement();
            }


            /* voir ou mettre cette partie dans l'interface et game
            Est ce que on ferai pas une classe pour les fonction communes de type choixLevel et joueur
            a voir avec le retour si ca pose pas de soucis avec l'interface jsp
            //Partie du joueur

            //demande au joueur s'il veut se connecter ou s'incrire
            String rep = demandeJoueur();
            //création / recherche du joueur
            Joueur gameur = joueurCrea(rep)/joueurConn(rep);


            //fin de la partie du joueur
             */



            /* a voir apres
            //lecture des niveaux sur le fichier level.ser
            while (fis.available() > 0) {
                Niveau lvl = (Niveau) ois.readObject ();
                lvl.remplir_Grille();
                lvl.afficher();
                lvl.getGrid().afficher();
            }
             */

        } catch (final IOException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static char demandeJeu(){
            System.out.println("De quelle façcon voulez-vous jouer ? T(exte) ou I(nterface) ?");
            String coords ;
            do{
                coords = new Scanner(System.in).next();
                if(!coords.equals("T") && !coords.equals("Texte") && !coords.equals("I") && !coords.equals("Interface") ){
                    System.out.println("Vous n'avez pas répondu correctement !");
                    System.out.println("De quelle façcon voulez-vous jouer ? T(exte) ou I(nterface) ?");
                }
            }while(coords.length() != 2);

            return coords.charAt(0);
    }

    public static String demandeJoueur(){
        System.out.println("Souhaitez-vous vous connecter (in) ou alors créer un compte(cr) ?");
        String coords ;
        boolean ok = true;
        do{
            coords = new Scanner(System.in).next();
            coords = coords.toLowerCase(Locale.ROOT);
            if(!coords.equals("in") && !coords.equals("cr")){
                ok = false;
                System.out.println("Vous n'avez pas répondu correctement !");
                System.out.println("Souhaitez-vous vous connecter (in) ou alors créer un compte(cr) ?");
            } else {
                ok = true;
            }
        }while(!ok);
        return coords;
    }

    /*public static Joueur joueurCrea(String rep){
        demander identifiant
        si identifiant pas present ds Joueur.ser alors
        demander code
        puis redemander pr etre sur
        et enfin serialisez le joueur sur joueur.ser
        et lancer choixLevel dans Game.java
    }
     */

    /*public static Joueur joueurConn(String rep){
        demande l'identifiant du joueur
        et son code secret (pour etre sur que ce soit le bon joueur)
        parcours le fichier Joueur.ser
        regarde si lidentifiant est present
        si present alors regarde le code
        si code bon alors -> lancer choixLevel de Game.java
        si code pas bon alors demande de se reconnecter entierement (relancer joueurConn ?)
        si identifiant pas dans le fichier alors
        dire que il est pas dedans
        voir si on redemande identifiant ou alors proposer de sincrire jsp !?
        peut etre faire qlq chose du style changer identifiant ou incrire
        et en fonction de la rep lancer joueurCre ou JoueurConn
    }
     */

}
