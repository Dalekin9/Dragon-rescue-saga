import java.io.*;
import java.util.Arrays;

public class Game {
    
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
        /*ObjectOutputStream oos = null;
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
                lvl.remplir_Grille();
                lvl.afficher();
                lvl.getGrid().afficher();
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
        }*/
        Joueur me = new Joueur("Ugo");
        Niveau[] levels = init();
        levels[0].remplir_Grille();
        Partie pA = new Partie(me,levels[0]);
        pA.jouer();
        /*Case[][] cas = new Case[4][4];
        for (int i=0;i<cas.length;i++){
            for (int j=0;j<cas[0].length;j++){
                cas[i][j] = new Case('V');
            }
        }
        cas[0][0] =  new Case('s');
        cas[0][1]=new Case('s');
        cas[0][2]=new Case('s');
        cas[0][3] =new Case('s');
        Grille test = new Grille(cas);
        test.afficher();
        System.out.println("colo");
        //test.afficherC();
        
        //int pos = test.coupSpecialLignePos();
        
        test.faireDescendre(false);
        test.afficher();
        System.out.println("colo");
        //test.afficherC();
        
        //test.poserFusee(0,1, pos);
        test.afficher();
        System.out.println("colo");
        //test.afficherC();*/
        
        
        
    }
}
