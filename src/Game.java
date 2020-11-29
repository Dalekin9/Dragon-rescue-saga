import java.io.*;

public class Game {
    
    public static Niveau[] init(){
        Niveau[] tab = new Niveau[2];
        tab[0] = new Niveau(new Grille(new Case[8][8]),1,2,0,0,new int[][]{{6,2},{2,2}},null, null);
        tab[1] = new Niveau(new Grille(new Case[8][8]),2,3,30,500,new int[][]{{6,2},{2,2}},null, null);
        return tab;
    }
    
    
    public static void main(String[] args){
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Niveau[] levels = init();
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
            //lecture des niveaux sur le fichier level.ser
            while (fis.available() > 0) {
                Niveau lvl = (Niveau) ois.readObject ();
                System.out.println("Niveau : ");
                System.out.println("id : " + lvl.id);
                System.out.println("acces : " + lvl.isAcces());
                System.out.println("animaux : " + lvl.getNb_animaux());
                System.out.println("coup : " + lvl.getNb_coup_min());;
            }
        } catch (final IOException | ClassNotFoundException e) {
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
