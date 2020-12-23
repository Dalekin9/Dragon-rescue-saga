public class Pioche{

    Pioche(){}

    public void execute(Grille grille, int i, int j) {
        grille.gril[i][j] = new Case('s');
    }
}
