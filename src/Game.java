public class Game {
    
    public static void main(String[] args){
        Case[][] cas = new Case[6][8];
        int a = 1;
        Niveau level1 = new Niveau1();
        level1.afficher();
        level1.grid.afficher();
        //aa.System.out.print();
        /*Niveau one = new Niveau(new Grille(new Case[6][8]),1);
        a.remplirBlocs();
        a.afficher();
        a.supprimer(2,4);
        System.out.println(a.coupValide(2,4));
        a.afficher();
        System.out.println(a.ontEteSupprime());
        System.out.println(a.points());
        a.remplacer();
        a.afficher();
         */
    }
}
