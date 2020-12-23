import java.io.*;
import java.util.Arrays;

public class Game {

    public static Niveau[] init(){
        Niveau[] tab = new Niveau[5];
        tab[0] = new Niveau(new Grille(new Case[7][7]),1,2,0,0);
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

        Case[][] cas = new Case[6][6];
        for (int i=0;i<cas.length;i++){
            for (int j=0;j<cas[0].length;j++){
                cas[i][j] = new Case('V');
            }
        }
        cas[0][0] =  new Case('s', 'V');
        cas[0][1]=new Case('s','V');
        cas[0][2]=new Case('s','V');
        cas[0][3] =new Case('s','V');
        cas[0][4] =new Case('s','V');

        cas[1][0] =  new Case('s', 'V');
        cas[1][1]=new Case('s','V');
        cas[1][2]=new Case('s','V');
        cas[1][3] =new Case('s','V');
        cas[1][4] =new Case('s','V');

        cas[2][0] =  new Case('s', 'V');
        cas[2][1]=new Case('s','V');
        cas[2][2]=new Case('s','V');
        cas[2][3] =new Case('s','V');
        cas[2][4] =new Case('s','V');

        cas[3][0] =  new Case('s', 'V');
        cas[3][1]=new Case('s','V');
        cas[3][2]=new Case('s','V');
        cas[3][3] =new Case('s','V');
        cas[3][4] =new Case('s','V');

        cas[4][0] =  new Case('s', 'V');
        cas[4][1]=new Case('s','V');
        cas[4][2]=new Case('s','V');
        cas[4][3] =new Case('s','V');
        cas[4][4] =new Case('s','V');
        Grille test = new Grille(cas);
        test.afficher();
        System.out.println("tab du haut en mode tt est supp");
        test.afficherC();

        int[] pos = test.coupSpecialBlocsPos();

        test.faireDescendre(false);
        test.afficher();
        System.out.println("tab du haut en mode jai fais descendre les blocs");
        test.afficherC();

        test.poserBallon(pos);
        test.afficher();
        System.out.println("ballon");
        test.afficherC();



    }
}
