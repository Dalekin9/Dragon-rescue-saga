import java.util.ArrayList;

public class Partie {
    private Joueur joueur;
    private Niveau lvl;
    private int score,coupRes,animRes;

    public Partie(Joueur pers, Niveau niveau){
        joueur = pers;
        lvl = niveau;
        score = 0;
        coupRes = lvl.getNb_coup_max();
        animRes = lvl.nb_animaux;
    }

    public int[] coordsInt(String[] coords){
        int[] intab = new int[2];
        intab[0] = Integer.parseInt(coords[1]);
        intab[1] = Integer.parseInt(coords[0]);
        return intab;
    }

    public boolean coordsVerif(String[] coords){
        int longueur = lvl.getGrid().getLongu();
        int hauteur = lvl.getGrid().getHaut();
        ArrayList nmbrs = new ArrayList();
        ArrayList letters = new ArrayList();
        for(int i = 0; i<hauteur; i++)nmbrs.add(String.valueOf(i));
        for(int j = 0; j<longueur; j++)letters.add(String.valueOf((char)('a'+ j)));
        if(nmbrs.contains(coords[0]) && letters.contains(coords[1].toLowerCase())){
            for(int k = 0; k<longueur; k++) {
                if (letters.get(k).equals(coords[1].toLowerCase())) {
                    coords[1] = Integer.toString(k);
                }
            }
            String tmp = coords[0];
            coords[0]  = coords[1];
            coords[1]  = tmp;
        }else if(nmbrs.contains(coords[1]) && letters.contains(coords[0].toLowerCase())){
            for(int k = 0; k<longueur; k++) {
                if (letters.get(k).equals(coords[0].toLowerCase())) {
                    coords[0] = Integer.toString(k);
                }
            }
        }else{
            System.out.println("Les coordonées ne sont pas valides");
            return false;
        }
        return true;
    }

    public int[] coordonnées(){
        String[] coordsStr;
        int[] coords = new int[2];
        boolean flag = false;
        do {
            coordsStr = joueur.recupCoords();
            if (coordsVerif(coordsStr)) {
                coords = coordsInt(coordsStr);
                flag = true;
            }
        }while(!flag);
        return coords;
    }

    public void useObj(int obj){
        int[] coords = coordonnées();
        Objet x ;
        if(obj == 3){
            x = new Fusee(lvl.getGrid(),coords[0]);
        }else if(obj == 2){
            x = new Pioche(lvl.getGrid(), coords[0], coords[1]);
        }else {
            x = new Bombe(lvl.getGrid(), coords[0], coords[1]);
        }
        x.execute();
        lvl.getGrid().faireDescendre(false);
        if(lvl.getGrid().animalEnBas()){
            int  ajout = lvl.getGrid().supprimerAnimalEnBas();
            score += ajout;
            animRes -= ajout/1000;
        }
        lvl.getGrid().faireDescendre(false);
        lvl.getGrid().decaler();
    }

    public void casseBloc(boolean animAlea){
        int[] coords = coordonnées();
        lvl.getGrid().supprimer(coords[0],coords[1]);
        if(lvl.getGrid().coupSpecialLigne()){
        //    lvl.getGrid()
        }
        score += lvl.getGrid().points();
        lvl.getGrid().faireDescendre(animAlea);
        if(lvl.getGrid().animalEnBas()){
            int  ajout = lvl.getGrid().supprimerAnimalEnBas();
            score += ajout;
            animRes -= ajout/1000;
        }
        lvl.getGrid().faireDescendre(animAlea);
        lvl.getGrid().decaler();
        coupRes--;
    }



    public void uneAction(boolean animAlea){
        System.out.println("Voulez-vous supprimer un bloc ou utiliser un objet? (B(loc)/O(bjet))");
        boolean flag = false;
        do {
            switch (joueur.repJoueur().toLowerCase()) {
                case "b":
                case "bloc":
                    casseBloc(animAlea);
                    flag = true;
                    break;
                case "o":
                case "objet":
                    useObj(joueur.choixObjet());
                    flag = true;
                    break;
                default:
                    System.out.println("Réponse non reconnue, choisissez B ou O");
                    break;
            }
        }while(!flag);
    }

    public void jouer(){
        lvl.afficher();
        do{
            System.out.println("Votre score est: "+score);
            lvl.getGrid().afficher();
            uneAction(false);
        }while(finJeu() == 0);
        if(finJeu() == 1){
            System.out.println("Vous avez perdu :(");
        }else{
            System.out.println("Bravo vous avez complété le niveau " + lvl.id+ " :)");
        }
    }

    public int finJeu(){
        if(coupRes == 0){
            return 1;
        }else if(animRes == 0 || score >= lvl.nb_point_min){
            return 2;
        }else{
            return 0;
        }
    }
}