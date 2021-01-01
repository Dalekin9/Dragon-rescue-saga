package Modele;
import Modele.*;
import Controleur.*;
import Vue.*;
import java.io.*;
import java.util.ArrayList;

public class Joueur implements java.io.Serializable {
    private String nom ;
    private String mdp;
    private ArrayList<Integer> nivAcess;
    private ArrayList<String> objAcces;
    static final long serialVersionUID = 10203040;

    public Joueur(final String nom, final String mdp) {
        this.nom = nom;
        this.mdp = mdp;
        nivAcess = new ArrayList<Integer>();
        nivAcess.add(1);
        objAcces = new ArrayList<String>();
    }



    public String getMdp(){return this.mdp;}

    public String getNom() {
        return this.nom;
    }

    public ArrayList<Integer> getNivAcess(){
        return nivAcess;
    }

     public ArrayList<String> getObjAcces() {return objAcces;}

     public void setObjAcces(ArrayList<String> update){objAcces = update;}

    public void setNivAcess(ArrayList<Integer> update){
        nivAcess = update;
    }

    //demande au joueur s'il souhaite se connecter ou s'incrire

    //fonction qui recherche si l'identifiant est présent dans joueur.ser
    public static boolean rechercheId(String id){
        ObjectInputStream ois;
        try {

            FileInputStream fis = new FileInputStream("joueur.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Joueur test = (Joueur) ois.readObject();
                    if (test.nom.equals(id)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    //fonction qui recherche le mot de passe lié à un identifiant
    //pour verifier sa correspondance
    public static boolean rechercheMdp(String id, String mdp){
        ObjectInputStream ois = null;
        try {

            FileInputStream fis = new FileInputStream("joueur.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Joueur test = (Joueur) ois.readObject();
                    if (test.nom.equals(id)) {
                        return test.mdp.equals(mdp);
                    }
                }
            }
            return false;
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    //fonction qui récupère un joueur en fonction de son identifiant dans joueur.ser
    public static Joueur getJoueur(String id){
        ObjectInputStream ois = null;
        try {

            FileInputStream fis = new FileInputStream("joueur.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Joueur test = (Joueur) ois.readObject();
                    if (test.nom.equals(id)) {
                        return test;
                    }
                }
            }
            return null;
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    //fonction qui créer un joueur et l'ecris sur joueur.ser
    public static Joueur creerJoueur(String id, String mdp) {
            ArrayList<Joueur> liste = new ArrayList<>();
            ObjectInputStream ois;
            ObjectOutputStream oos;
            try {
                FileInputStream fis = new FileInputStream("joueur.ser");
                if (fis.available() != 0) {
                    ois = new ObjectInputStream(fis);
                    while (fis.available() != 0) {
                        Joueur test = (Joueur) ois.readObject();
                        liste.add(test);
                    }
                }
                Joueur nouveau = new Joueur(id, mdp);
                liste.add(nouveau);
                FileOutputStream fos = new FileOutputStream("joueur.ser");
                oos = new ObjectOutputStream(fos);
                for (Joueur a : liste) {
                    oos.writeObject(a);
                }
                oos.flush();
                oos.close();
            } catch (final IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
    }

    public boolean levelEstPossible(int niveau){
        return nivAcess.contains(niveau);
    }

    public ArrayList<String> remplirListeObj(ArrayList<String> objDispo) {
        for (String s : objDispo) {
            if (!this.objAcces.contains(s)) {
                this.objAcces.add(s);
            }
        }
        return objAcces;
    }

    public void miseAJour(Joueur joueur, Niveau lvl){
        ArrayList<Integer> update = joueur.getNivAcess();
        if (!update.contains(lvl.id+1)){
            update.add(lvl.id + 1);
        }
        joueur.setNivAcess(update);
        ArrayList<Joueur> liste = new ArrayList<>();
        ObjectInputStream ois;
        ObjectOutputStream oos;
        try {
            FileInputStream fis = new FileInputStream("joueur.ser");
            if (fis.available() != 0) {
                ois = new ObjectInputStream(fis);
                while (fis.available() != 0) {
                    Joueur test = (Joueur) ois.readObject();
                    if (test.getNom() == joueur.getNom()){
                        liste.add(joueur);
                    }else {
                        liste.add(test);
                    }
                }
            }
            FileOutputStream fos = new FileOutputStream("joueur.ser");
            oos = new ObjectOutputStream(fos);
            for (Joueur a : liste) {
                oos.writeObject(a);
            }
            oos.flush();
            oos.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}