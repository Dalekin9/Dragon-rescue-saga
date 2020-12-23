public class Fusee{

    Fusee(){}

    public void execute(Grille grille, int i) {
        for (int col=0;col<grille.gril.length;col++) {
            grille.gril[i][col] = new Case('s');
        }
    }
}
