import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Grille implements Serializable {
    
    Case[][] gril;

    public Grille(Case[][] grid){
        gril = grid;
    }
    
    //on suppose les coordonnées x et y toujours corrects
    //rempli le tableau avec les animaux
    public void poserAnimaux(int[][] tab){
        for (int[] ints : tab) {
            gril[ints[0]][ints[1]] = new Case('a');
        }
    }
    
    //on suppose les coordonnées x et y toujours corrects
    //rempli le tableau de bloc fixe
    public void poserFixe(int[][] tab){
        for (int[] ints : tab) {
            gril[ints[0]][ints[1]] = new Case('n');
        }
    }
    
    //on suppose les coordonnées x et y toujours corrects
    //rempli le tableau d'endroit vide
    public void poserVide(int[][] tab){
        for (int[] ints : tab) {
            gril[ints[0]][ints[1]] = new Case('v');
        }
    }
    
    //rempli le tableau de bloc de couleur
    public void remplirBlocs(){
        for(int i=0;i<gril.length;i++){
            for(int j = 0; j<gril[i].length;j++) {
                Random rand = new Random();
                gril[i][j] = new Case((char)((rand.nextInt(4)+1)+'0'));
            }
        }
    }
    
    public int chiffre_aleatoire(int longueur){
        Random rand = new Random();
        return rand.nextInt(longueur);
    }
    
    public ArrayList<Character> liste(){
        ArrayList<Character> chiffre = new ArrayList<Character>();
        chiffre.add('J'); chiffre.add('O'); chiffre.add('R'); chiffre.add('B'); chiffre.add('V');
        return chiffre;
    }
    
    //rempli si le Niveau est le 1er (car démo)
    public void remplir_Niveau_1(){
        ArrayList<Character> chiffre = this.liste();
        for (int i=0;i<5;i++){
            int pos = this.chiffre_aleatoire(chiffre.size());
            if (i==0){
                gril[1][2] = gril[1][3] = gril[1][4] = gril[3][0] = gril[3][1] = gril[4][0] = gril[4][1] = gril[5][3] = gril[6][3] = gril[5][6] = gril[6][6]= new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else if (i == 1){
                gril[1][5] = gril[1][6] = gril[2][5] = gril[2][6] = gril[5][1] = gril[5][2] = gril[6][1]  = gril[6][2]= new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else if (i== 2){
                gril[2][2] = gril[2][3] = gril[2][4] = gril[3][2] = gril[3][3] = gril[3][4] = gril[4][2] = gril[4][3] = gril[4][4] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else if (i== 3){
                gril[1][0] = gril[1][1] = gril[2][0] = gril[2][1] = gril[5][0] = gril[6][0] = gril[5][4] = gril[5][5] = gril[6][4] = gril[6][5] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else {
                gril[3][5] = gril[3][6] = gril[4][5] = gril[4][6] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            }
        }
    }
    
    //rempli si le Niveau est le 2eme (car démo)
    public void remplir_Niveau_2(){
        ArrayList<Character> chiffre = this.liste();
        for (int i=0;i<3;i++){
            int pos = this.chiffre_aleatoire(chiffre.size());
            if (i==0){
                gril[0][1] = gril[0][3] = gril[1][1] = gril[1][3] = gril[4][1] = gril[4][3] = gril[5][1] = gril[5][3] = gril[6][0] = gril[6][4] = gril[7][0] = gril[7][4]= new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else if (i == 1){
                gril[2][0] = gril[2][4] = gril[3][0] = gril[3][4] = gril[3][2] = gril[4][2] = gril[5][2] = gril[6][2]  = gril[7][2]= new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else {
                gril[2][1] = gril[2][3] = gril[3][1] = gril[3][3] = gril[4][0] = gril[4][4] = gril[5][0] = gril[5][4] = gril[6][1] = gril[6][3] = gril[7][1] = gril[7][3] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            }
        }
    }
    
    //rempli si le Niveau est le 3eme (car démo)
    public void remplir_Niveau_3(){
        ArrayList<Character> chiffre = this.liste();
        for (int i=0;i<3;i++){
            int pos = this.chiffre_aleatoire(chiffre.size());
            if (i==0){
                gril[1][3] = gril[2][3] = gril[3][2] = gril[4][2] = gril[4][0] = gril[6][0] = gril[7][2] = gril[8][2] = gril[7][4] = gril[8][4] = gril[1][5] = gril[2][5] = gril[3][5] = gril[4][5] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else if (i == 1){
                gril[1][2] = gril[2][2] = gril[3][4] = gril[4][4] = gril[3][6] = gril[4][6] = gril[5][0] = gril[5][1]  = gril[5][3] = gril[5][5] = gril[6][1] = gril[6][3] = gril[6][5] = gril[7][0] = gril[7][1] = gril[8][0] = gril[8][1] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            } else {
                gril[1][4] = gril[1][6] = gril[2][4] = gril[2][6] = gril[3][3] = gril[4][3] = gril[4][1] = gril[5][2] = gril[6][2] = gril[5][4] = gril[6][4] = gril[5][6] = gril[6][6] = gril[7][3] = gril[8][3] = new Case(chiffre.get(pos));
                chiffre.remove(pos);
            }
        }
    }
    
    public void afficher(){
        for (Case[] cases : gril) {
            for (Case aCase : cases) {
                System.out.print(aCase.getIs() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public boolean remplie(){
        for (Case[] cases : gril) {
            for (Case aCase : cases) {
                if (aCase.getIs() == 's') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void supprimer(int x, int y){
        int res = gril[x][y].getIs();
        gril[x][y].setIs('s');
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
        for (Case[] cases : gril) {
            for (Case aCase : cases) {
                if (aCase.getIs() == 's') {
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
    
}
