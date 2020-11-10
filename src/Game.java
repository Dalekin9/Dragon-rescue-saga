public class Game {
    
    public static void main(String[] args){
        Case[][] cas = new Case[10][8];
        Grille a = new Grille(cas);
        a.remplirBlocs();
        a.afficher();
        System.out.println(a.gril.length);
        System.out.println(a.gril[2].length);
    }
}
