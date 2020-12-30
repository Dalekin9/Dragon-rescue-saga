public class Ballon extends Objet{

    public char color;
    int j;

    Ballon(char c,Grille grille, int i, int y){
        color = c;
        this.i = i;
        this.j = y;
        this.grille = grille;
    }

    public void execute() {
        for (int ligne=0;ligne<grille.gril.length;ligne++){
            for (int col=0;col<grille.gril.length;col++){
                if (grille.gril[ligne][col].getIs() == this.color){
                    grille.gril[ligne][col] = new Case('s');
                }
            }
        }
    }
}
