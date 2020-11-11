public class Case {
    
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
    //1 = jaune
    //2 = violet
    //3 = bleu
    //4 = vert
    
}
