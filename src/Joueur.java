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
    public char[] recupCoords(){
        System.out.println("Entrez les coordonnés de la case");
        String coords = scaRep.next();
        if(coords.length() == 2){
            char[] coordChar = new char[2];
            coordChar[0] = coords.charAt(0);
            coordChar[1] = coords.charAt(1);
            return coordChar;
        }
        return null;
    }



    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

}