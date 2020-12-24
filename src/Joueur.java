import java.util.Scanner;
import java.util.ArrayList;

public class Joueur implements java.io.Serializable {
    private String nom ;
    private Scanner scaRep = new Scanner(System.in);
    private ArrayList<Niveau> nivAcess= new ArrayList<Niveau>();

    public Joueur(final String nom) {
        this.nom = nom;
    }

    public String repJoueur(){
        return scaRep.next();
    }

    // Recupère les coordonnées de la case qui doit être affectée
    public String[] recupCoords(){
        System.out.println("Entrez les coordonnés de la case(sous forme NombreLettre)");
        String coords ;
        String[] coordStr = new String[2];
        do{
            coords = scaRep.next();
            if(coords.length() != 2)System.out.println("L'entrée n'a pas la bonne taille.Réessayez");
        }while(coords.length() != 2);
        coordStr[0] = coords.substring(0,1);
        coordStr[1] = coords.substring(1,2);
        return coordStr;
    }



    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

}