import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Niveau implements Serializable {
    
    private Grille grid;
    public int id;
    protected int nb_animaux;
    protected int nb_coup_min;
    protected int nb_point_min;
    protected int[] best_score;
    protected ArrayList<Character> listColor;

    public Niveau(Grille grille, int numero, int animaux, int coup, int point){
        grid = grille;
        id = numero;
        nb_animaux = animaux;
        nb_coup_min = coup;
        nb_point_min = point;
        best_score = new int[]{0, 0, 0, 0, 0};
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
        if (nb_coup_min != 0){
            System.out.println(nb_coup_min+" coups maximum");
        }
        if (nb_point_min != 0) {
            System.out.println("Score de " + nb_point_min + " points");
        }
        afficher_score();
    }
    
    public void afficher_score(){
        System.out.println("Meilleurs scores :");
        for (int i=0;i<5;i++){
            if (best_score[i] != 0){
                System.out.println(i+1 +" : "+best_score[i]);
            } else {
                System.out.println(i+1 +" : Pas encore de score");
            }
        }
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
    
    public int getNb_coup_min() {
        return nb_coup_min;
    }
}
