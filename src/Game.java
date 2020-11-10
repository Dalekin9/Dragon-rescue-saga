public class Game {
    
    public static void main(String[] args){
        Case[][] cas = new Case[4][6];
        Grille a = new Grille(cas);
        a.remplirBlocs();
        a.afficher();
        a.supprimer(2,4);
        a.afficher();
        a.remplacer();
        a.afficher();
    }
}
