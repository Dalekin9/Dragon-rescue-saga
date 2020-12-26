public class Ballon extends Objet{

    public char color;
    int col;

    Ballon(char c,Grille grid,int lig,int col){
        this.lig = lig;
        this.col = col;
        this.grid = grid;
        color = c;
    }

    public void execute() {
        for (int ligne=0;ligne<grid.getHaut();ligne++){
            for (int col=0;col<grid.getLongu();col++){
                if (grid.gril[ligne][col].getIs() == this.color){
                    grid.gril[ligne][col] = new Case('s');
                }
            }
        }
    }

}
