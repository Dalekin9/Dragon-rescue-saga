public class Bombe extends Objet{
    int col;
    Bombe(Grille grille, int i, int j){
        grid = grille;
        lig = i;
        col = j;
    }

    public void execute() {
        grid.gril[lig][col] = new Case('s');
        if (lig>0){
            grid.gril[lig-1][col] = new Case('s');
            if (col>0){
                grid.gril[lig-1][col-1] = new Case('s');
                grid.gril[lig][col-1] = new Case('s');
            }
            if (col<grid.gril[0].length-1){
                grid.gril[lig-1][col+1] = new Case('s');
                grid.gril[lig][col+1] = new Case('s');
            }
        }
        if (lig<grid.gril.length-1){
            grid.gril[lig+1][col] = new Case('s');
            if (col>0){
                grid.gril[lig+1][col-1] = new Case('s');
            }
            if (col<grid.gril[0].length-1){
                grid.gril[lig+1][col+1] = new Case('s');
            }
        }

    }
}
