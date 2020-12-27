public class Fusee extends Objet{

    Fusee(Grille grille, int ligne){
        grid = grille;
        lig = ligne;
    }

    public void execute() {
        for (int col=0;col<grid.gril.length;col++) {
            grid.gril[lig][col] = new Case('s');
        }
    }
}
