public class Niveau1 extends Niveau {
    
    public Niveau1() {
        super(new Grille(new Case[8][8]), 1, 2, 0, 0);
        this.grid = creer_grille();
    }
    
    public Grille creer_grille(){
        Grille fin = new Grille(new Case[8][8]);
        fin.remplirBlocs();
        fin.poserAnimaux(new int[][]{{1,2},{3,5}});
        return fin;
    }
    
}
