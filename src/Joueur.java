import java.util.Scanner;

public class Joueur implements java.io.Serializable {
    private String nom ;
    private Scanner scaRep = new Scanner(System.in);

    public Joueur(final String nom) {
        this.nom = nom;
    }

    public String repJoueur(){
        return scaRep.next();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

}