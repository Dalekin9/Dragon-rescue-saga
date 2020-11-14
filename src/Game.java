import java.io.*;

public class Game {
    
    public static void main(String[] args){
        final Niveau level1= new Niveau(new Grille(new Case[4][5]),1,3,15,5000,new int[][]{{1,2}},new int[][]{{2,4}});
        ObjectOutputStream oos = null;
    
        try {
            final FileOutputStream fichier = new FileOutputStream("levels.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(level1);
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
