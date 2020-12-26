public class Pioche extends Objet{
    int col;

    Pioche(Grille grille, int i, int j){
        grid = grille;
        lig = i;
        col = j;
    }

    public void execute() {
        grid.gril[lig][col] = new Case('s');
    }
}
