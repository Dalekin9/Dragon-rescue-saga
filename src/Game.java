import java.io.*;
import java.util.Arrays;

public class Game {
    
    public static Niveau[] init(){
        Niveau[] tab = new Niveau[2];
        tab[0] = new Niveau(new Grille(new Case[7][7]),1,2,0,0,new int[][]{{0,1},{0,5}},null, new int[][]{{0,0},{0,2},{0,3},{0,4},{0,6}});
        tab[1] = new Niveau(new Grille(new Case[8][5]),2,5,0,0,new int[][]{{0,2},{1,2},{2,2},{1,0},{1,4}},null, new int[][]{{0,0},{0,4}});
        tab[2] = new Niveau(new Grille(new Case[9][7]),3,3,0,0,new int[][]{{0,2},{0,4},{0,6}},null, new int[][]{{0,0},{0,1},{1,0},{1,1},{2,0},{2,1},{3,0},{3,1},{0,3},{0,5},{7,5},{7,6},{8,5},{8,6}});
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
                lvl.setGrid(lvl.remplir_Grille());
                System.out.println("Niveau : ");
                System.out.println("id : " + lvl.id);
                System.out.println("animaux : " + lvl.getNb_animaux());
                for (int i=0;i<lvl.getGrid().gril.length;i++){
                    for (int j=0;j<lvl.getGrid().gril[i].length;j++){
                        System.out.print(lvl.getGrid().gril[i][j].getIs() + " ");
                    }
                    System.out.println();
                }
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
