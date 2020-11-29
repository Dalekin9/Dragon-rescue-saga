import java.io.Serializable;

public class Case implements Serializable {
    
    private char is;

    public Case(char est){
        is = est;
    }
    
    public char getIs(){
        return is;
    }
    
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
    //'n' = neutre
    //'a' = animal
    //J = jaune
    //O = orange
    //R = rouge
    //B = bleu
    //V = violet
    //Vide = espace
    //Obstacle = -
    
}
