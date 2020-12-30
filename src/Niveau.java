import java.io.*;
import java.util.*;

public class Niveau implements Serializable {
    
    private Grille grid;
    public int id;
    protected int nb_animaux;
    protected int nb_coup_max;
    protected Map<Integer,String> best_score;
    protected ArrayList<Character> listColor;
    protected ArrayList<String> objDispo;
    protected boolean decale;

    public Niveau(Grille grille, int numero, int animaux, int coup, boolean decale){
        grid = grille;
        id = numero;
        nb_animaux = animaux;
        nb_coup_max = coup;
        best_score = new HashMap<>();
        this.decale = decale;
        listColor = listeColor();
        objDispo = remplirObjetDispo(id);

    }
    
    //remplissage des grilles selon le niveau
    public void remplir_Grille(){
        if (id == 1){
            grid.remplir_Niveau_1(this.liste(5));
        }else if( id == 2){
            grid.remplir_Niveau_2(this.liste(3));
        }else if (id == 3) {
            grid.remplir_Niveau_3(this.liste(3));
        } else if (id == 4){
            grid.remplir_Niveau_4(this.liste(3));
        }else {
            grid.remplir_Niveau_5(this.liste(3));
        }
    }

    public ArrayList<String> remplirObjetDispo(int id){
        ArrayList<String> liste = new ArrayList<>();
        if (id >= 3){
            liste.add("Fusee");
        }
        if (id >= 4){
            liste.add("Bombe");
        }
        if (id >= 5){
            liste.add("Pioche");
        }
        return liste;
    }
    
    
    //---------------------------------------------------------
    //                   --- PARTIE 2 ---                     -
    //                  liste de couleurs                     -
    //---------------------------------------------------------
    
    
    public int chiffre_aleatoire(int longueur){
        Random rand = new Random();
        return rand.nextInt(longueur);
    }
    
    public ArrayList<Character> listeColor(){
        ArrayList<Character> chiffre = new ArrayList<Character>();
        chiffre.add('J'); chiffre.add('O'); chiffre.add('R'); chiffre.add('B'); chiffre.add('V');
        return chiffre;
    }
    
    public ArrayList<Character> liste(int longueur){
        ArrayList<Character> chiffre = listeColor();
        ArrayList<Character> fin = new ArrayList<Character>();
        for (int i=0;i<longueur;i++){
            int compt = chiffre_aleatoire(chiffre.size());
            fin.add(chiffre.get(compt));
            chiffre.remove(compt);
        }
        return fin;
    }
    
    
    //---------------------------------------------------------
    //              --- PARTIE 2 ---                          -
    //       affichage du niveau et des scores                -
    //---------------------------------------------------------
    
    //affichage des propriétés du niveau en mode texte
    public void afficher(){
        System.out.println("Niveau "+id);
        System.out.println("Objectifs :");
        System.out.println("Sauver "+nb_animaux+" ours");
        if (nb_coup_max != -1){
            System.out.println(nb_coup_max+" coups maximum");
        }
        afficher_score();
    }
    
    public void afficher_score(){
        System.out.println("Meilleurs scores :");
        int compt = 1;
        for (var item : best_score.entrySet()) {
            System.out.println(compt + " : " + item.getKey() +" -> " + item.getValue());
            compt++;
        }
        while (compt <= 5){
            System.out.println(compt + " : Pas encore de score");
            compt++;
        }
        System.out.println();
    }
    
    
    //---------------------------------------------------------
    //              --- PARTIE 3 ---                          -
    //      contient les getters (et les setters)             -
    //---------------------------------------------------------
    
    public Grille getGrid() {
        return grid;
    }
    
    public int getNb_animaux() {
        return nb_animaux;
    }
    
    public int getNb_coup_max() {
        return nb_coup_max;
    }


    public static Niveau recupNiveau(int level){
        ObjectInputStream ois = null;
        try {

            FileInputStream fis = new FileInputStream("level.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Niveau test = (Niveau) ois.readObject();
                    if (test.id == level) {
                        return test;
                    }
                }
            }
            return null;
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public int recupDernierScore(Map<Integer,String> scores){
        int min = -1;
        for (var item : scores.entrySet()) {
            if (item.getKey() < min){
                min = item.getKey();
            }
        }
        return min;
    }
}
