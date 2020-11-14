import java.io.Serializable;

public class Niveau implements Serializable {
    
    protected Grille grid;
    public int id;
    protected int nb_animaux;
    protected int nb_coup_min;
    protected int nb_point_min;
    protected int[] best_score;
    protected boolean acces;
    protected int[][] pos_animal;
    protected int[][] pos_neutre;

    public Niveau(Grille grille, int numero, int animaux, int coup, int point, int[][] anim, int[][] neutre){
        grid = grille;
        grid.remplirBlocs();
        id = numero;
        nb_animaux = animaux;
        nb_coup_min = coup;
        nb_point_min = point;
        best_score = new int[]{0, 0, 0, 0, 0};
        acces = false;
        grid.poserAnimaux(anim);
        grid.poserFixe(neutre);
        pos_animal = anim;
        pos_neutre = neutre;
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
    
}
