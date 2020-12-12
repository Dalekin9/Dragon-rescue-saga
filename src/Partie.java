import java.util.ArrayList;

public class Partie {
    private Joueur joueur;
    private Niveau lvl;
    private int score;

    public Partie(Joueur pers, Niveau niveau){
        joueur = pers;
        lvl = niveau;
        score = 0;
    }

    public int[] coordsInt(String[] coords){
        int[] intab = new int[2];
        intab[0] = Integer.parseInt(coords[0]);
        intab[1] = Integer.parseInt(coords[1]);
        return intab;
    }

    public boolean coordsVerif(String[] coords){
        int longueur = lvl.getGrid().getLongu();
        int hauteur = lvl.getGrid().getHaut();
        ArrayList nmbrs = new ArrayList();
        ArrayList letters = new ArrayList();
        for(int i = 0; i<hauteur; i++)nmbrs.add(i);
        for(int j = 0; j<longueur; j++)letters.add('a'+j);
        if(nmbrs.contains(coords[0]) && letters.contains(coords[1].toLowerCase())){
            for(int i = 0; i<longueur; i++) {
                if (letters.get(i).equals(coords[1].toLowerCase())) {
                    coords[1] = Integer.toString(i);
                }
            }
            String tmp = coords[0];
            coords[0]  = coords[1];
            coords[1]  = tmp;
        }else if(nmbrs.contains(coords[1]) && letters.contains(coords[0].toLowerCase())){
            for(int i = 0; i<longueur; i++) {
                if (letters.get(i).equals(coords[0].toLowerCase())) {
                    coords[0] = Integer.toString(i);
                }
            }
        }else{
            return false;
        }
        return true;
    }



    public void uneAction(){
        System.out.println("Voulez-vous supprimer un bloc ou utiliser un objet? (B(loc)/O(bjet))");
        boolean flag = false;
        String[] coordsStr;
        int[] coords = new int[2];
        do {
            switch (joueur.repJoueur()) {
                case "B":
                case "Bloc":
                    do {
                        coordsStr = joueur.recupCoords();
                        if (coordsVerif(coordsStr)) {
                            coords = coordsInt(coordsStr);
                            flag = true;
                        }
                    }while(!flag);
                    lvl.grid.supprimer(coords[0],coords[1]);
                    break;
                case "O":
                case "Objet":
                    flag = true;
                    break;
                default:
                    System.out.println("Réponse non reconnue, choisissez B ou O");
                    break;
            }
        }while(!flag);
    }
}
