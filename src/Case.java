import java.io.Serializable;

public class Case implements Serializable {

    private char is;
    private char color;

    public Case(char est){
        is = est;
        color = est;
    }

    //utile pour les objets
    public Case(char est, char colo){
        is = est;
        color = colo;
    }

    public char getIs(){
        return is;
    }

    public char getColor() { return color;}

    public void setIs(char c){
        is = c;
    }

    public void supprimer(){
        is = 's';
    }

    public void remplacer(Case c){
        is = c.is;
        c.is = 's';

    }

    //'s' = Supprimé
    //'a' = animal
    //'1' = fusée
    //'2' = ballon
    //'3' = bombe
    //'4' = pioche
    //J = jaune
    //O = orange
    //R = rouge
    //B = bleu
    //V = violet
    //Vide = espace
    //Obstacle = -

}
