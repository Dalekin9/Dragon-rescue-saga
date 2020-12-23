public class Bombe{

    Bombe(){}

    public void execute(Grille grille, int i, int j) {
        grille.gril[i][j] = new Case('s');
        if (i>0){
            grille.gril[i-1][j] = new Case('s');
            if (j>0){
                grille.gril[i-1][j-1] = new Case('s');
                grille.gril[i][j-1] = new Case('s');
            }
            if (j<grille.gril[0].length-1){
                grille.gril[i-1][j+1] = new Case('s');
                grille.gril[i][j+1] = new Case('s');
            }
        }
        if (i<grille.gril.length-1){
            grille.gril[i+1][j] = new Case('s');
            if (j>0){
                grille.gril[i+1][j-1] = new Case('s');
            }
            if (j<grille.gril[0].length-1){
                grille.gril[i+1][j+1] = new Case('s');
            }
        }

    }
}
