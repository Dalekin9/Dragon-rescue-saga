public class Partie {
    private Joueur joueur;
    private Niveau lvl;
    private int score;

    public Partie(Joueur pers, Niveau niveau){
        joueur = pers;
        lvl = niveau;
        score = 0;
    }

    public int[] coordsInt(char[] coords){
        int[] coordsInt = new int[2];
        int longueur = lvl.getGrid().getLongu();
        int hauteur = lvl.getGrid().getHaut();
        for(int i = 1; i<= hauteur; i++){

        }
    }

    public void uneAction(){
        System.out.println("Voulez-vous supprimer un bloc ou utiliser un objet? (B(loc)/O(bjet))");
        boolean flag = false;
        char[] coords;
        do {
            switch (joueur.repJoueur()) {
                case "B":
                case "Bloc":
                    coords = joueur.recupCoords();

                    flag = true;
                    break;
                case "O":
                case "Objet":
                    flag = true;
                    break;
                default:
                    System.out.println("Réponse non reconnue, choisissez B ou O");
                    break;
            }
        }while(flag);
    }
}
