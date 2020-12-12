import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Grille implements Serializable {
    
    Case[][] gril;

    public Grille(Case[][] grid){
        gril = grid;
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
    
    //---------------------------------------------------------
    //              --- PARTIE 2 ---                          -
    //       gestions des coups dans une partie               -
    //---------------------------------------------------------
    
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
    
    public int points(){
        int compt = this.ontEteSupprime();
        return compt*compt*10;
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
    
    public boolean animalEnBas(){
        for (int i=0;i<gril[0].length;i++){
            if (gril[gril.length-1][i].getIs() == 'a'){
                return true;
            }
        }
        return false;
    }
    
    public void supprimerAnimalEnBas(){
        for (int i=0;i<gril[0].length;i++){
            if (gril[gril.length-1][i].getIs() == 'a'){
                gril[gril.length-1][i].setIs('s');
            }
        }
    }
    
    //--------------------------------------------------------
    
    public boolean contientColonneVide() {
        for (int i = 0; i < gril[0].length; i++) {
            ArrayList<Character> test = new ArrayList<Character>();
            for (int j = 0; j < gril.length; j++) {
                test.add(gril[j][i].getIs());
            }
            if (!test.contains('V') && !test.contains('J') && !test.contains('O') && !test.contains('R') && !test.contains('B')) {
                return test.contains('s');
            }
        }
        return false;
    }
    
    public int getColonneVide(){
        for (int i = 0; i < gril[0].length; i++) {
            ArrayList<Character> test = new ArrayList<Character>();
            for (int j = 0; j < gril.length; j++) {
                test.add(gril[j][i].getIs());
            }
            if (!test.contains('V') && !test.contains('J') && !test.contains('O') && !test.contains('R') && !test.contains('B')) {
                return i;
            }
        }
        return -1;
    }
    
    public void decale(){
        int pos = getColonneVide();
        if (pos == gril[0].length-1){
            for (int i=0;i<gril.length;i++){
                if (gril[i][gril[0].length-1].getIs() != '-') {
                    gril[i][gril[0].length - 1].setIs(' ');
                }
            }
        } else {
            for (int i=0;i<gril.length;i++){
                if (gril[i][pos].getIs() != ' ' && gril[i][pos].getIs() != '-') {
                    if (gril[i][pos + 1].getIs() == ' ' || gril[i][pos + 1].getIs() == '-') {
                        gril[i][pos].setIs(' ');
                    } else {
                        char sub = gril[i][pos+1].getIs();
                        gril[i][pos] = new Case(sub);
                        gril[i][pos+1]= new Case('s');
                    }
    
                }
            }
        }
    }
    
    public void decaler(){
        while (this.contientColonneVide()){
            this.decale();
        }
    }
    
    //--------------------------------------------------------
    
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
    
    public void faireDescendre(){
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
    
    
    //---------------------------------------------------------
    //              --- PARTIE 2 ---                          -
    //         gestion liée à la fin du jeu                   -
    //---------------------------------------------------------
    
    
    //---------------------------------------------------------
    //              --- PARTIE 2 ---                          -
    //       affichage du niveau et des scores                -
    //---------------------------------------------------------
    
    public void remplir_Niveau_1(ArrayList<Character> liste){
        for (int i=0;i<5;i++){
            if ((5-i-1) == 4){
                gril[1][2] = gril[1][3] = gril[1][4] = gril[3][0] = gril[3][1] = gril[4][0] = gril[4][1] = gril[5][3] = gril[6][3] = gril[5][6] = gril[6][6]= new Case(liste.get(4));
                liste.remove(4);
            } else if ((5-i-1) == 3){
                gril[1][5] = gril[1][6] = gril[2][5] = gril[2][6] = gril[5][1] = gril[5][2] = gril[6][1]  = gril[6][2]= new Case(liste.get(3));
                liste.remove(3);
            } else if ((5-i-1) == 2){
                gril[2][2] = gril[2][3] = gril[2][4] = gril[3][2] = gril[3][3] = gril[3][4] = gril[4][2] = gril[4][3] = gril[4][4] = new Case(liste.get(2));
                liste.remove(2);
            } else if ((5-i-1) == 1){
                gril[1][0] = gril[1][1] = gril[2][0] = gril[2][1] = gril[5][0] = gril[6][0] = gril[5][4] = gril[5][5] = gril[6][4] = gril[6][5] = new Case(liste.get(1));
                liste.remove(1);
            } else {
                gril[3][5] = gril[3][6] = gril[4][5] = gril[4][6] = new Case(liste.get(0));
            }
            //pose les animaux
            gril[0][1] = gril[0][5] = new Case('a');
            //pose les blocs vide du depart
            gril[0][0] = gril[0][2] = gril[0][3] = gril[0][4] = gril[0][6] = new Case(' ');
        }
    }
    
    public void remplir_Niveau_2(ArrayList<Character> liste){
        for (int i=0;i<3;i++){
            if ((3-i-1)==2){
                gril[0][1] = gril[0][3] = gril[1][1] = gril[1][3] = gril[4][1] = gril[4][3] = gril[5][1] = gril[5][3] = gril[6][0] = gril[6][4] = gril[7][0] = gril[7][4]= new Case(liste.get(2));
                liste.remove(2);
            }
            else if ((3-i-1) == 1){
                gril[2][0] = gril[2][4] = gril[3][0] = gril[3][4] = gril[3][2] = gril[4][2] = gril[5][2] = gril[6][2]  = gril[7][2]= new Case(liste.get(1));
                liste.remove(1);
            } else {
                gril[2][1] = gril[2][3] = gril[3][1] = gril[3][3] = gril[4][0] = gril[4][4] = gril[5][0] = gril[5][4] = gril[6][1] = gril[6][3] = gril[7][1] = gril[7][3] = new Case(liste.get(0));
            }
            //pose les animaux
            gril[0][2] = gril[1][2] = gril[2][2] = gril[1][0] = gril[1][4] = new Case('a');
            //pose les blocs vides
            gril[0][0] = gril[0][4] = new Case (' ');
        }
    }
    
    public void remplir_Niveau_3(ArrayList<Character> liste){
        for (int i=0;i<3;i++){
            if ((3-i-1)==2){
                gril[1][3] = gril[2][3] = gril[3][2] = gril[4][2] = gril[4][0] = gril[6][0] = gril[7][2] = gril[8][2] = gril[7][4] = gril[8][4] = gril[1][5] = gril[2][5] = gril[3][5] = gril[4][5] = new Case(liste.get(2));
                liste.remove(2);
            } else if ((3-i-1) == 1){
                gril[1][2] = gril[2][2] = gril[3][4] = gril[4][4] = gril[3][6] = gril[4][6] = gril[5][0] = gril[5][1]  = gril[5][3] = gril[5][5] = gril[6][1] = gril[6][3] = gril[6][5] = gril[7][0] = gril[7][1] = gril[8][0] = gril[8][1] = new Case(liste.get(1));
                liste.remove(1);
            } else {
                gril[1][4] = gril[1][6] = gril[2][4] = gril[2][6] = gril[3][3] = gril[4][3] = gril[4][1] = gril[5][2] = gril[6][2] = gril[5][4] = gril[6][4] = gril[5][6] = gril[6][6] = gril[7][3] = gril[8][3] = new Case(liste.get(0));
            }
            //pose les animaux
            gril[0][2] = gril[0][4] = gril[0][6] = new Case('a');
            //pose les blocs vides
            gril[0][0] = gril[0][1] = gril[1][0] = gril[1][1] = gril[2][0] = gril[2][1] = gril[3][0] = gril[3][1] = gril[0][3] = gril[0][5] =  new Case(' ');
            //pose les blocs fixes
            gril[7][5] = gril[7][6] = gril[8][5] = gril[8][6] = new Case('-');
        }
    }
    
    public void remplir_Niveau_4(ArrayList<Character> liste){
        for (int i=0;i<8;i++){
            for (int j=0;j<9;j++){
                gril[i][j] = new Case (liste.get((int) (Math.random()*3)));
            }
        }
        //pose les animaux
        gril[3][1] = gril[3][3] = gril[3][5] = gril[3][7] = new Case('a');
    }
    
    public void remplir_Niveau_5(ArrayList<Character> liste){
        //pose les blocs de couleur supérieur
        for (int i=0;i<4;i++){
            for (int j=0;j<9;j++){
                gril[i][j] = new Case (liste.get((int) (Math.random()*3)));
            }
        }
        //pose les blocs de couleur inférieur
        gril[6][2] = gril[7][3] = gril[6][4] = gril[7][5] = gril[6][6] = new Case(liste.get(0));
        gril[7][2] = gril[6][3] = gril[7][4] = gril[6][5] = gril[7][6] = new Case(liste.get(1));
        //pose les animaux
        gril[5][2] = gril[5][3] = gril[5][4] = gril[5][5] = gril[5][6] = new Case('a');
        //pose les blocs fixes
        gril[3][0] = gril[3][8] =gril[5][1] = gril[5][7] = gril[6][1] = gril[6][7] = gril[7][1] = gril[7][7]
                = gril[4][0] = gril[4][1] = gril[4][2] = gril[4][3] = gril[4][4]= gril[4][5] = gril[4][6] = gril[4][7] = gril[4][8] = new Case('-');
        //pose les emplacements vides
        gril[0][0] = gril[0][8] = gril[5][0] = gril[5][8] = gril[6][0] = gril[6][8] = gril[7][0] = gril[7][8] = new Case(' ');
    }
    
}
