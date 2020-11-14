import java.io.*;

public class Game {
    
    public static void main(String[] args){
        final Niveau level1 = new Niveau(new Grille(new Case[8][8]),1,2,0,0,new int[][]{{6,2},{2,2}},null);
        final Niveau level2 = new Niveau(new Grille(new Case[8][8]),2,3,0,500,new int[][]{{6,2},{2,2}},null);
        ObjectOutputStream oos = null;
    
        try {
            final FileOutputStream fichier = new FileOutputStream("level.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(level1);
            oos.writeObject(level2);
            oos.flush();
        } catch (final java.io.IOException e) {
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
        
        /*Case[][] cas = new Case[6][8];
        int a = 1;
        //aa.System.out.print();
        /*Niveau one = new Niveau(new Grille(new Case[6][8]),1);
        a.remplirBlocs();
        a.afficher();
        a.supprimer(2,4);
        System.out.println(a.coupValide(2,4));
        a.afficher();
        System.out.println(a.ontEteSupprime());
        System.out.println(a.points());
        a.remplacer();
        a.afficher();
         */
    }
}
