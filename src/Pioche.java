public class Pioche extends Objet{
    int j;
    Pioche(Grille grille, int i, int j){
        this.grille = grille;
        this.i = i;
        this.j = j;
    }

    public void execute() {
        grille.gril[i][j] = new Case('s');
    }
}
