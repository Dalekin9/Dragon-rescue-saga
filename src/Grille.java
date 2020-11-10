import java.util.Random;

public class Grille {
    
    Case[][] gril;

    public Grille(Case[][] grid){
        gril = grid;
    }
    
    public void remplirAnimaux(int x, int y, String animal){
        gril[x][y].setIs(animal.charAt(0));
    }
    
    public void remplirBlocs(){
        for(int i=0;i<gril.length;i++){
            for(int j = 0; j<gril[i].length;j++) {
                Random rand = new Random();
                int res = rand.nextInt(4);
                gril[i][j].setIs(2);
            }
        }
    }
    
    public void afficher(){
        for(int i=0;i<gril.length;i++){
            for(int j=0;j<gril[i].length;j++){
                System.out.print(gril[i][j] + " ");
            }
        }
        System.out.println();
    }
}
