import java.io.Serializable;

public class Niveau implements Serializable {
    
    private Grille grid;
    public int id;
    protected int nb_animaux;
    protected int nb_coup_min;
    protected int nb_point_min;
    protected int[] best_score;
    protected int[][] pos_animal;
    protected int[][] pos_neutre;
    protected int[][] pos_vide;

    public Niveau(Grille grille, int numero, int animaux, int coup, int point, int[][] anim, int[][] neutre, int[][] vide){
        grid = grille;
        //grid = this.remplirGrilleNiveau();
        id = numero;
        nb_animaux = animaux;
        nb_coup_min = coup;
        nb_point_min = point;
        best_score = new int[]{0, 0, 0, 0, 0};
        pos_animal = anim;
        pos_neutre = neutre;
        pos_vide = vide;
    }
    
    public Grille remplir_Grille(){
        this.remplissage_par_level();
        if (pos_animal != null) {
            grid.poserAnimaux(pos_animal);
        }
        if (pos_neutre != null) {
            grid.poserFixe(pos_neutre);
        }
        if (pos_vide != null){
            grid.poserVide(pos_vide);
        }
        return grid;
    }
    
    public void remplissage_par_level(){
        if (id == 1){
            grid.remplir_Niveau_1();
        } else if ( id == 2){
            grid.remplir_Niveau_2();
        } /*else if (id == 3){
            grid.remplir_Niveau_3;
        } */else {
            grid.remplirBlocs();
        }
    }
    
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
    
    public void setGrid(Grille grid) {
        this.grid = grid;
    }
    
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
