public class Game {
    
    public static void main(String[] args){
        Case[][] cas = new Case[6][8];
        Grille a = new Grille(cas);
        a.remplirBlocs();
        a.afficher();
        a.supprimer(2,4);
        System.out.println(a.coupValide(2,4));
        a.afficher();
        System.out.println(a.ontEteSupprime());
        System.out.println(a.points());
        a.remplacer();
        a.afficher();
    }
}
