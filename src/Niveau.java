public class Niveau {
    private Grille grid;
    public int id;
    public String name;

    public Niveau(Grille grille, int numero, String nom){
        grid = grille;
        id = numero;
        name = nom;
    }
}
