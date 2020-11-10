import java.util.Random;

public class Grille {
    
    Case[][] gril;

    public Grille(Case[][] grid){
        gril = grid;
    }
    
    public void remplirAnimaux(int x, int y){
        gril[x][y] = new Case(0);
    }
    
    public void remplirFixe(int x, int y){
        gril[x][y] = new Case(-1);
    }
    
    public void remplirBlocs(){
        for(int i=0;i<gril.length;i++){
            for(int j = 0; j<gril[i].length;j++) {
                Random rand = new Random();
                gril[i][j] = new Case(rand.nextInt(4)+1);
            }
        }
    }
    
    public void afficher(){
        for(int i=0;i<gril.length;i++){
            for(int j=0;j<gril[i].length;j++){
                System.out.print(gril[i][j].getIs() + " ");
            }System.out.println();
        }
        System.out.println();
    }
    
    public boolean remplie(){
        for (int i=0;i<gril.length;i++){
            for (int j=0;j<gril[i].length;j++){
                if (gril[i][j].getIs() == -10){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void supprimer(int x, int y){
        int res = gril[x][y].getIs();
        gril[x][y].setIs(-10);
        if (x-1 > -1 && gril[x-1][y].getIs() == res){
            supprimer(x-1,y);
        }
        if (x+1 < gril.length && gril[x+1][y].getIs() == res){
            supprimer(x+1,y);
        }
        if (y-1 > -1 && gril[x][y-1].getIs() == res){
            supprimer(x,y-1);
        }
        if (y+1 < gril.length && gril[x][y+1].getIs() == res){
            supprimer(x,y+1);
        }
    }
    
    public void remplacer(){
        while(!remplie()){
            for (int i=0;i<gril.length;i++){
                for (int j=0;j<gril[i].length;j++){
                    if (gril[i][j].getIs() == -10){
                        if (i-1 < 0){
                            Random rand = new Random();
                            gril[i][j].setIs(rand.nextInt(4)+1);
                        } else {
                            gril[i][j].remplacer(gril[i - 1][j]);
                        }
                    }
                }
            }
        }
    }
    
}