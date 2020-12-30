public class Fusee extends Objet{

    Fusee(Grille grille, int i){
        this.grille = grille;
        this.i = i;
    }

    public void execute() {
        for (int col=0;col<grille.gril.length;col++) {
            if (grille.gril[col][i].getIs() != ' ' && grille.gril[col][i].getIs() != '-' && grille.gril[col][i].getIs() != 'a') {
                grille.gril[col][i] = new Case('s');
            }
        }
    }
}
