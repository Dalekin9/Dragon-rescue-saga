import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Grille implements Serializable {

    Case[][] gril;
    int longu,haut;

    public Grille(Case[][] grid){
        gril = grid;
        longu = grid[0].length;
        haut = grid.length;
    }

    public void afficher(){
        System.out.print("   ");
        for (int k=0;k<gril[0].length;k++){
            System.out.print((char)(k+65) + " ");
        }
        System.out.println();System.out.println();
        for (int i = 0;i<gril.length;i++) {
            if (i<10) {
                System.out.print(i+ "  ");
            }else {
                System.out.print(i+" ");
            }
            for (int j=0;j<gril[0].length;j++) {
                System.out.print(gril[i][j].getIs() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void afficherC(){
        for (Case[] cases : gril) {
            for (Case aCase : cases) {
                System.out.print(aCase.getColor() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //---------------------------------------------------------
    //                   --- PARTIE 2 ---                     -
    //             suppression et remplacement                -
    //---------------------------------------------------------

    public void supprimer(int x, int y){
            char res = gril[x][y].getIs();
            gril[x][y] = new Case('s');
            if (x > 0) {
                if (gril[x-1][y].getIs() == res) {
                    supprimer(x - 1, y);
                }
            }
            if (x < gril.length-1){
                if (gril[x+1][y].getIs() == res) {
                    supprimer(x + 1, y);
                }
            }
            if (y > 0 ){
                if(gril[x][y-1].getIs() == res) {
                    supprimer(x, y - 1);
                }
            }
            if (y < gril[0].length-1 ){
                if(gril[x][y+1].getIs() == res) {
                    supprimer(x, y + 1);
                }
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

    public int supprimerAnimalEnBas(){
        int points = 0;
        for (int i=0;i<gril[0].length;i++){
            if (gril[gril.length-1][i].getIs() == 'a'){
                gril[gril.length-1][i].setIs('s');
                points += 1000;
            }
        }
        return points;
    }

    public int aDAnimaux(){
        int nombre = 0;
        for (int i=0;i<gril.length;i++){
            for (int j=0;j<gril[0].length;j++) {
                if (gril[i][j].getIs() == 'a') {
                    nombre++;
                }
            }
        }
        return nombre;
    }

    //--------------------------------------------------------

    public boolean estVide(int pos,Grille grille){
        for (int i=0;i<grille.gril.length;i++){
            if (grille.gril[i][pos].getIs() == 'a' || grille.gril[i][pos].getIs() == 'V' || grille.gril[i][pos].getIs() == 'J' || grille.gril[i][pos].getIs() == 'R' ||
                    grille.gril[i][pos].getIs() == 'O' || grille.gril[i][pos].getIs() == 'B'){
                return false;
            }
        }
        return true;
    }

    public void decale(int pos){
        if (pos == gril[0].length-1){
            for (int i=0;i<gril.length;i++){
                if (gril[i][gril[0].length-1].getIs() != '-') {
                    gril[i][gril[0].length-1].setIs(' ');
                }
            }
        } else {
            for (int i=0;i<gril.length;i++){
                if (gril[i][pos].getIs() != '-') {
                    if (gril[i][pos + 1].getIs() == ' ') {
                        gril[i][pos].setIs(' ');
                    } else if (gril[i][pos + 1].getIs() == '-') {
                        gril[i][pos].setIs('s');
                    } else {
                        char sub = gril[i][pos + 1].getIs();
                        gril[i][pos] = new Case(sub);
                        gril[i][pos + 1] = new Case(' ');
                    }
                }
            }
        }
    }

    public void faireDescendreQuandDecale(){
        while (!remplie()) {
            for (int i = 0; i < gril.length; i++) {
                for (int j = 0; j < gril[i].length; j++) {
                    if (gril[i][j].getIs() == 's') {
                        if (i == 0) {
                            gril[i][j] = new Case(' ');
                        } else {
                            if (gril[i - 1][j].getIs() != ' ' && gril[i-1][j].getIs() != '-') {
                                gril[i][j] = new Case(gril[i - 1][j].getIs());
                                gril[i - 1][j] = new Case('s');
                            } else {
                                gril[i][j] = new Case(' ');
                            }
                        }
                    }
                }
            }
        }
    }

    public void decaler(){
        while (pasFiniDeSwitch())
        for (int i=0;i<gril[0].length;i++){
            if (estVide(i,this)){
                decale(i);
                faireDescendreQuandDecale();
            }
        }
    }

    public boolean pasFiniDeSwitch(){
        int vide = 0;
        for (int i=0;i<gril[0].length;i++){
            if (estVide(i,this)){
                vide++;
            }
            else{
                if (vide !=0){
                    return true;
                }
            }
        }
        return false;
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

    public void faireDescendre(boolean animAlea){
        while(!remplie()){
            ArrayList<Character> liste = new ArrayList<Character>();
            liste.add('J'); liste.add('O'); liste.add('R'); liste.add('B'); liste.add('V');
            if (animAlea){
                liste.add('a');
            }
            for (int i=0;i<gril.length;i++){
                for (int j=0;j<gril[i].length;j++){
                    if (gril[i][j].getIs() == 's' ){
                        if (i-1 < 0){
                            int pos = (int) (Math.random()*liste.size());
                            gril[i][j] = new Case(liste.get(pos));
                        } else {
                            if ( gril[i-1][j].getIs() != ' ' && gril[i-1][j].getIs() != '-') {
                                gril[i][j] = new Case(gril[i - 1][j].getIs());
                                gril[i-1][j] = new Case('s');
                            } else {
                                int pos = (int) (Math.random()*liste.size());
                                gril[i][j] = new Case(liste.get(pos));
                            }
                        }
                    }
                }
            }
        }
    }


    //---------------------------------------------------------
    //                   --- PARTIE 2 ---                     -
    //                 gestions des coups                     -
    //---------------------------------------------------------

    public boolean coupPossible(){
        for (int i=0;i< gril.length;i++){
            for (int j=0;j<gril[0].length;j++){
                if (i>0){
                    if(j>0){
                        if (gril[i-1][j-1].getIs() == gril[i][j].getIs()){
                            return true;
                        }
                    }
                    if (j<gril[0].length-1){
                        if (gril[i-1][j+1].getIs() == gril[i][j].getIs()){
                            return true;
                        }
                    }
                }
                if (i<gril.length-1){
                    if(j>0){
                        if (gril[i+1][j-1].getIs() == gril[i][j].getIs()){
                            return true;
                        }
                    }
                    if (j<gril[0].length-1){
                        if (gril[i+1][j+1].getIs() == gril[i][j].getIs()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //----------------------------------------------------------

    public boolean coupSpecialLigne(){
        for (int i = 0;i<gril.length;i++){
            ArrayList<Character> test = new ArrayList<>();
            for (int j=0;j<gril[i].length;j++){
                test.add(gril[i][j].getIs());
            }
            if (!test.contains('V') && !test.contains('J') && !test.contains('O') && !test.contains('R') && !test.contains('B')) {
                if (test.contains('s')){
                    return true;
                }
            }
        }
        return false;
    }

    //il est important de noter que cette fonction s'appelle avant le remplacement des cases supprimées
    //sinon on ne peut récuperer la position de la ligne supprimée
    public int coupSpecialLignePos(){
        for (int i = 0;i<gril.length;i++){
            ArrayList<Character> test = new ArrayList<>();
            for (int j=0;j<gril[i].length;j++){
                test.add(gril[i][j].getIs());
            }
            if (!test.contains('V') && !test.contains('J') && !test.contains('O') && !test.contains('R') && !test.contains('B')) {
                if (test.contains('s')){
                    return i;
                }
            }
        }
        return -1;
    }

    public void poserFusee(int pos){
        gril[pos][0] = new Case('1',gril[pos][0].getColor());
    }

    //----------------------------------------------------------

    //regarde si un coup special de bloc a ete realisé
    //cad si un carré de 5*5 bloc de meme couleur a ete supprimé
    public boolean coupSpecialBlocs(){
        boolean test = false;
        for (int i = 0;i<gril.length-5;i++){
            for (int j=0;j<gril[0].length-5;j++){
                if (testCinq(i,j)){
                    if (testCinq(i+1,j)){
                        if (testCinq(i+2,j)){
                            if (testCinq(i+3,j)){
                                if (testCinq(i+4,j)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    //renvoie la position du premier carré d'un bloc qui a ete supprimé
    public int[] coupSpecialBlocsPos(){
        for (int i = 0;i<gril.length-5;i++){
            for (int j=0;j<gril[0].length-5;j++){
                if (testCinq(i,j)){
                    if (testCinq(i+1,j)){
                        if (testCinq(i+2,j)){
                            if (testCinq(i+3,j)){
                                if (testCinq(i+4,j)){
                                    return new int[]{i,j};
                                }
                            }
                        }
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }

    //regarde si pour une position i j
    //  - les 4 blocs a sa droite sont de la meme couleur
    //  - si c'est le cas on repete le processus pour les 4 lignes suivantes en partant de la meme colonne
    // si tout est bon alors c'est qu'un coup special a ete realisé
    public boolean testCinq(int ligneDepart, int colonneDepart){
        return gril[ligneDepart][colonneDepart].getColor() == gril[ligneDepart][colonneDepart + 1].getColor() &&
                gril[ligneDepart][colonneDepart + 1].getColor() == gril[ligneDepart][colonneDepart + 2].getColor() &&
                gril[ligneDepart][colonneDepart + 2].getColor() == gril[ligneDepart][colonneDepart + 3].getColor() &&
                gril[ligneDepart][colonneDepart + 3].getColor() == gril[ligneDepart][colonneDepart + 4].getColor();
    }

    public void poserBallon(int[] pos){
        gril[pos[0]][pos[1]] = new Case('2',gril[pos[0]][pos[1]].getColor());
    }



    //--------------------------------------------------------

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

    //---------------------------------------------------------
    //              --- PARTIE 2 ---                          -
    //                  Getters                               -
    //---------------------------------------------------------

    public int getLongu() {
        return longu;
    }

    public int getHaut() {
        return haut;
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
                gril[1][2] = new Case(liste.get(4));gril[1][3] = new Case(liste.get(4)); gril[1][4] = new Case(liste.get(4));
                gril[3][0] = new Case(liste.get(4)); gril[3][1] = new Case(liste.get(4)); gril[4][0] = new Case(liste.get(4));
                gril[4][1] = new Case(liste.get(4)); gril[5][3] = new Case(liste.get(4)); gril[6][3] = new Case(liste.get(4));
                gril[5][6] = new Case(liste.get(4)); gril[6][6] = new Case(liste.get(4));
                liste.remove(4);
            } else if ((5-i-1) == 3){
                gril[1][5] = new Case(liste.get(3)); gril[1][6] = new Case(liste.get(3)); gril[2][5] = new Case(liste.get(3));
                gril[2][6] = new Case(liste.get(3)); gril[5][1] = new Case(liste.get(3)); gril[5][2] = new Case(liste.get(3));
                gril[6][1] = new Case(liste.get(3)); gril[6][2] = new Case(liste.get(3));
                liste.remove(3);
            } else if ((5-i-1) == 2){
                gril[2][2] = new Case(liste.get(2)); gril[2][3] = new Case(liste.get(2)); gril[2][4] = new Case(liste.get(2));
                gril[3][2] = new Case(liste.get(2)); gril[3][3] = new Case(liste.get(2)); gril[3][4] = new Case(liste.get(2));
                gril[4][2] = new Case(liste.get(2)); gril[4][3] = new Case(liste.get(2)); gril[4][4] = new Case(liste.get(2));
                liste.remove(2);
            } else if ((5-i-1) == 1){
                gril[1][0] = new Case(liste.get(1)); gril[1][1] = new Case(liste.get(1)); gril[2][0] = new Case(liste.get(1));
                gril[2][1] = new Case(liste.get(1)); gril[5][0] = new Case(liste.get(1)); gril[6][0] = new Case(liste.get(1));
                gril[5][4] = new Case(liste.get(1)); gril[5][5] = new Case(liste.get(1)); gril[6][4] = new Case(liste.get(1));
                gril[6][5] = new Case(liste.get(1));
                liste.remove(1);
            } else {
                gril[3][5] = new Case(liste.get(0)); gril[3][6] = new Case(liste.get(0));
                gril[4][5] = new Case(liste.get(0)); gril[4][6] = new Case(liste.get(0));
            }
            //pose les animaux
            gril[0][1] = new Case('a'); gril[0][5] = new Case('a');
            //pose les blocs vide du depart
            gril[0][0] = gril[0][2] = gril[0][3] = gril[0][4] = gril[0][6] = new Case(' ');
        }
    }

    public void remplir_Niveau_2(ArrayList<Character> liste){
        for (int i=0;i<3;i++){
            if ((3-i-1)==2){
                gril[0][1] = new Case(liste.get(2)); gril[0][3] = new Case(liste.get(2)); gril[1][1] = new Case(liste.get(2));
                gril[1][3] = new Case(liste.get(2)); gril[4][1] = new Case(liste.get(2)); gril[4][3] = new Case(liste.get(2));
                gril[5][1] = new Case(liste.get(2)); gril[5][3] = new Case(liste.get(2)); gril[6][0] = new Case(liste.get(2));
                gril[6][4] = new Case(liste.get(2)); gril[7][0] = new Case(liste.get(2)); gril[7][4] = new Case(liste.get(2));
                liste.remove(2);
            }
            else if ((3-i-1) == 1){
                gril[2][0] = new Case(liste.get(1)); gril[2][4] = new Case(liste.get(1)); gril[3][0] = new Case(liste.get(1));
                gril[3][4] = new Case(liste.get(1)); gril[3][2] = new Case(liste.get(1)); gril[4][2] = new Case(liste.get(1));
                gril[5][2] = new Case(liste.get(1)); gril[6][2] = new Case(liste.get(1)); gril[7][2] = new Case(liste.get(1));
                liste.remove(1);
            } else {
                gril[2][1] = new Case(liste.get(0)); gril[2][3] = new Case(liste.get(0)); gril[3][1] = new Case(liste.get(0));
                gril[3][3] = new Case(liste.get(0)); gril[4][0] = new Case(liste.get(0)); gril[4][4] = new Case(liste.get(0));
                gril[5][0] = new Case(liste.get(0)); gril[5][4] = new Case(liste.get(0)); gril[6][1] = new Case(liste.get(0));
                gril[6][3] = new Case(liste.get(0)); gril[7][1] = new Case(liste.get(0)); gril[7][3] = new Case(liste.get(0));
            }
            //pose les animaux
            gril[0][2] = new Case('a'); gril[1][2] = new Case('a');
            gril[2][2] = new Case('a'); gril[1][0] = new Case('a');
            gril[1][4] = new Case('a');
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
                gril[1][2] = new Case(liste.get(1)); gril[2][2] = new Case(liste.get(1)); gril[3][4] = new Case(liste.get(1));
                gril[4][4] = new Case(liste.get(1)); gril[3][6] = new Case(liste.get(1)); gril[4][6] = new Case(liste.get(1));
                gril[5][0] = new Case(liste.get(1)); gril[5][1] = new Case(liste.get(1)); gril[5][3] = new Case(liste.get(1));
                gril[5][5] = new Case(liste.get(1)); gril[6][1] = new Case(liste.get(1)); gril[6][3] = new Case(liste.get(1));
                gril[6][5] = new Case(liste.get(1)); gril[7][0] = new Case(liste.get(1)); gril[7][1] = new Case(liste.get(1));
                gril[8][0] = new Case(liste.get(1)); gril[8][1] = new Case(liste.get(1));
                liste.remove(1);
            } else {
                gril[1][4] = new Case(liste.get(0)); gril[1][6] = new Case(liste.get(0)); gril[2][4] = new Case(liste.get(0));
                gril[2][6] = new Case(liste.get(0)); gril[3][3] = new Case(liste.get(0)); gril[4][3] = new Case(liste.get(0));
                gril[4][1] = new Case(liste.get(0)); gril[5][2] = new Case(liste.get(0)); gril[6][2] = new Case(liste.get(0));
                gril[5][4] = new Case(liste.get(0)); gril[6][4] = new Case(liste.get(0)); gril[5][6] = new Case(liste.get(0));
                gril[6][6] = new Case(liste.get(0)); gril[7][3] = new Case(liste.get(0)); gril[8][3] = new Case(liste.get(0));
            }
            //pose les animaux
            gril[0][2] = new Case('a'); gril[0][4] = new Case('a'); gril[0][6] = new Case('a');
            //pose les blocs vides
            gril[0][0] =  new Case(' '); gril[0][1] =  new Case(' '); gril[1][0] =  new Case(' ');
            gril[1][1] =   new Case(' ');gril[2][0] =   new Case(' ');gril[2][1] =   new Case(' ');
            gril[3][0] =  new Case(' '); gril[3][1] =  new Case(' '); gril[0][3] =  new Case(' ');
            gril[0][5] =  new Case(' ');
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
        gril[3][1] = new Case('a'); gril[3][3] = new Case('a');
        gril[3][5] = new Case('a'); gril[3][7] = new Case('a');
    }

    public void remplir_Niveau_5(ArrayList<Character> liste){
        //pose les blocs de couleur supérieur
        for (int i=0;i<4;i++){
            for (int j=0;j<9;j++){
                gril[i][j] = new Case (liste.get((int) (Math.random()*3)));
            }
        }
        //pose les blocs de couleur inférieur
        gril[6][2] = new Case(liste.get(0)); gril[7][3] = new Case(liste.get(0));
        gril[6][4] = new Case(liste.get(0)); gril[7][5] = new Case(liste.get(0)); gril[6][6] = new Case(liste.get(0));
        gril[7][2] = new Case(liste.get(1)); gril[6][3] = new Case(liste.get(1));
        gril[7][4] = new Case(liste.get(1)); gril[6][5] = new Case(liste.get(1)); gril[7][6] = new Case(liste.get(1));
        //pose les animaux
        gril[5][2] = new Case('a'); gril[5][3] = new Case('a');
        gril[5][4] = new Case('a'); gril[5][5] = new Case('a'); gril[5][6] = new Case('a');
        //pose les blocs fixes
        gril[3][0] = new Case('-'); gril[3][8] = new Case('-'); gril[5][1] = new Case('-');
        gril[5][7] = new Case('-'); gril[6][1] = new Case('-'); gril[6][7] = new Case('-');
        gril[7][1] = new Case('-'); gril[7][7] = new Case('-'); gril[4][0] = new Case('-');
        gril[4][1] = new Case('-'); gril[4][2] = new Case('-'); gril[4][3] = new Case('-');
        gril[4][4] = new Case('-'); gril[4][5] = new Case('-'); gril[4][6] = new Case('-');
        gril[4][7] = new Case('-'); gril[4][8] = new Case('-');
        //pose les emplacements vides
        gril[0][0] = gril[0][8] = gril[5][0] = gril[5][8] = gril[6][0] = gril[6][8] = gril[7][0] = gril[7][8] = new Case(' ');
    }

}
