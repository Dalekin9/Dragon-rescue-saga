import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Launcher {

    public static Niveau[] init(){
        Niveau[] tab = new Niveau[5];
        tab[0] = new Niveau(new Grille(new Case[7][7]),1,2,5,3000);
        tab[1] = new Niveau(new Grille(new Case[8][5]),2,5,0,0);
        tab[2] = new Niveau(new Grille(new Case[9][7]),3,3,0,0);
        tab[3] = new Niveau(new Grille(new Case[8][9]),4,12,0,0);
        tab[4] = new Niveau(new Grille(new Case[8][9]),5,5,0,0);
        return tab;
    }

    public static void main(String[] args){
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
            //Demande au joueur s'il veut jouer en mode texte ou graphique
            if (demandeJeu().equals("T")){
                Game.lancement();
            }else {
                InterfaceJeu.lancement();
            }
        } catch (final IOException e) {
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

    public static String demandeJeu(){
            System.out.println("De quelle façcon voulez-vous jouer ? T(exte) ou I(nterface) ?");
            String coords ;
            boolean ok =false;
            do{
                coords = new Scanner(System.in).next();
                if(!coords.equals("T") && !coords.equals("Texte") && !coords.equals("I") && !coords.equals("Interface") ){
                    System.out.println("Vous n'avez pas répondu correctement !");
                    System.out.println("De quelle façcon voulez-vous jouer ? T(exte) ou I(nterface) ?");
                    ok = false;
                }
                ok = true;
            }while(!ok);

            return coords;
    }



}
