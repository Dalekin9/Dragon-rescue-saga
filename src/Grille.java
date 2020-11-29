import java.io.Serializable;
import java.util.Random;

public class Grille implements Serializable {
    
    Case[][] gril;
    private final int haut,longu;

    public Grille(Case[][] grid){
        gril = grid;
        haut = gril.length;
        longu = gril[0].length;
    }
    
    //On suppose les coordonnées x et y sont toujours correctes
    //Rempli le tableau avec les animaux
    public void poserAnimaux(int[][] tab){
        for (int i = 0;i<tab.length;i++){
            gril[tab[i][0]][tab[i][1]] = new Case('a');
        }
    }
    
    //On suppose les coordonnées x et y sont toujours correctes
    //Rempli le tableau de blocs fixes
    public void poserFixe(int[][] tab){
        for (int i = 0;i<tab.length;i++){
            gril[tab[i][0]][tab[i][1]] = new Case('n');
        }
    }
    
    //on suppose les coordonnées x et y toujours corrects
    //rempli le tableau d'endroit vide
    public void poserVide(int[][] tab){
        for (int i = 0;i<tab.length;i++){
            gril[tab[i][0]][tab[i][1]] = new Case('v');
        }
    }
    
    //rempli le tableau de blocs de couleur
    public void remplirBlocs(){
        for(int i=0;i<gril.length;i++){
            for(int j = 0; j<gril[i].length;j++) {
                Random rand = new Random();
                gril[i][j] = new Case((char)((rand.nextInt(4)+1)+'0'));
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
    
    public boolean estRemplie(){
        for (int i=0;i<gril.length;i++){
            for (int j=0;j<gril[i].length;j++){
                if (gril[i][j].getIs() == 's'){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void supprimer(int x, int y){
        int res = gril[y][x].getIs();
        gril[y][x].setIs('s');
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
        while(!estRemplie()){
            for (int i=0;i<gril.length;i++){
                for (int j=0;j<gril[i].length;j++){
                    if (gril[i][j].getIs() == 's'){
                        if (i-1 < 0){
                            Random rand = new Random();
                            gril[i][j].setIs((char)((rand.nextInt(4)+1)+'0'));
                        } else {
                            gril[i][j].remplacer(gril[i - 1][j]);
                        }
                    }
                }
            }
        }
    }
    
    public int ontEteSupprime(){
        int compt = 0;
        for (int i=0;i<gril.length;i++){
            for (int j=0;j<gril[i].length;j++){
                if (gril[i][j].getIs() == 's'){
                    compt++;
                }
            }
        }
        return compt;
    }
    
    public int points(){
        int compt = this.ontEteSupprime();
        return compt*compt*10;
    }
    
    public boolean coupValide(int x,int y){
        int res = gril[x][y].getIs();
        if (x-1 > -1 && gril[x-1][y].getIs() == res){
            return true;
        } else if (x+1 < gril.length && gril[x+1][y].getIs() == res){
            return true;
        } else if (y-1 > -1 && gril[x][y-1].getIs() == res){
            return true;
        } else if (y+1 < gril.length && gril[x][y+1].getIs() == res){
            return true;
        } else {
            return false;
        }
    }

    public int getHaut() {
        return haut;
    }

    public int getLongu() {
        return longu;
    }
}
