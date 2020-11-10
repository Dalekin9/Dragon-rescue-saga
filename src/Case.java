public class Case {
    
    private int is;

    public Case(int est){
        is = est;
    }
    
    public int getIs(){
        return is;
    }
    
    public void setIs(int a){
        is = a;
    }
    
    public void supprimer(){
        is = -10;
    }
    
    public void remplacer(Case a){
        is = a.is;
        a.is = -10;
        
    }
    
    //-1 = neutre
    //0 = animal
    //1 = jaune
    //2 = violet
    //3 = bleu
    //4 = vert
    
}
