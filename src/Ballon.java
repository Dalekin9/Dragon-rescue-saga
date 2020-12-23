public class Ballon{

    public char color;

    Ballon(char c){
        color = c;
    }

    public void execute(Grille grille, int i, int y) {
        for (int ligne=0;ligne<grille.gril.length;ligne++){
            for (int col=0;col<grille.gril.length;col++){
                if (grille.gril[ligne][col].getIs() == this.color){
                    grille.gril[ligne][col] = new Case('s');
                }
            }
        }
    }
}
