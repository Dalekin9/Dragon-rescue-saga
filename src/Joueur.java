import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Joueur implements java.io.Serializable {
    private String nom ;
    private String mdp;
    private ArrayList<Integer> nivAcess= new ArrayList<Integer>();

    static final long serialVersionUID = 10203040;

    public Joueur(final String nom, final String mdp) {
        this.nom = nom;
        this.mdp = mdp;
        nivAcess = new ArrayList<Integer>();
        nivAcess.add(1);
    }

    // Recupère les coordonnées de la case qui doit être affectée
    public String[] recupCoords(){
        System.out.println("Entrez les coordonnés de la case(sous forme LettreNombre)");
        String coords ;
        String[] coordStr = new String[2];
        do{
            coords = new Scanner(System.in).next();
            if(coords.length() != 2)System.out.println("L'entrée n'a pas la bonne taille. Réessayez !");
        }while(coords.length() != 2);
        coordStr[0] = coords.substring(0,1);
        coordStr[1] = coords.substring(1,2);
        return coordStr;
    }

    public String getNom() {
        return this.nom;
    }

    public ArrayList<Integer> getNivAcess(){
        return nivAcess;
    }

    public void setNivAcess(ArrayList<Integer> update){
        nivAcess = update;
    }

    //demande au joueur s'il souhaite se connecter ou s'incrire
    public static String demandeJoueur(){
        System.out.println("Souhaitez-vous vous Co(nnecter) ou vous I(nscrire) ?");
        String rep;
        boolean ok;
        do{
            rep = new Scanner(System.in).next();
            switch (rep.toLowerCase(Locale.ROOT)) {
                case "co", "connecter", "i", "inscrire","connexion","inscription" -> ok = true;
                default -> {
                    ok = false;
                    System.out.println("Vous n'avez pas répondu correctement !");
                    System.out.println("Souhaitez-vous vous Co(nnecter) ou vous I(nscrire) ?");
                }
            }
        }while(!ok);
        return String.valueOf(rep.toLowerCase().charAt(0));
    }

    //fonction de connexion
    public static Joueur connexion(){
        System.out.println("Entrez votre identifiant : (ou i si vous voulez vous inscrire)");
        String rep ;
        Joueur joueur = null;
        boolean ok = false;
        do {
            rep = new Scanner(System.in).next();
            if (rep.equals("i")) {
                joueur = inscription();
                ok =true;
            } else if (!rechercheId(rep)) { //regarder si present dans joueur.ser (ici cas negatif)
                System.out.println("Identifiant introuvable.");
                System.out.println("Entrez votre identifiant : (ou i si vous voulez vous inscrire)");
                ok = false;
            } else { // l'identifiant est présent dans joueur.ser, on demande le mot de passe
                System.out.println("Entrez votre mot de passe :");
                String repMdp = new Scanner(System.in).next();
                if (!rechercheMdp(rep, repMdp)) { // mauvais mot de passe
                    ok = false;
                    System.out.println("Mot de passe incorrect !");
                    System.out.println("Entrez votre identifiant : (ou i si vous voulez vous inscrire)");
                } else { //mot de pass correct
                    joueur = getJoueur(rep);
                    ok = true;
                }
            }
        } while (!ok);
        return joueur;
    }

    //fonction d'inscription
    public static Joueur inscription(){
        System.out.println("Choisissez un identifiant : (ou c si vous voulez vous connecter)");
        String rep ;
        Joueur joueur = null;
        boolean ok = false;
        do {
            rep = new Scanner(System.in).next();
            if (rep.equals("c")) {
                joueur = connexion();
                ok = true;
            } else if (!rechercheId(rep)) { //regarder si present dans joueur.ser (ici cas negatif donc demande mot de passe)
                System.out.println("Entrez votre mot de passe :");
                String repMdp = new Scanner(System.in).next();
                System.out.println("Entrez votre mot de passe une nouvelle fois :");
                String repMdp2 = new Scanner(System.in).next();
                if (repMdp.equals(repMdp2)) { // les 2 mots de passe sont pareil donc création du joueur et ecriture sur joueur.ser
                    joueur = creerJoueur(rep, repMdp);
                    ok = true;
                } else { //mot de pass incorrect
                    ok = false;
                    System.out.println("Vos mots de passes sont différents !");
                    System.out.println("Choisissez un identifiant : (ou c si vous voulez vous connecter)");
                }
            } else { // l'identifiant est présent dans joueur.ser, on ne peut pas l'utiliser
                System.out.println("Identifiant déjà utilisé.");
                System.out.println("Choisissez un identifiant : (ou c si vous voulez vous connecter)");
                ok = false;
            }
        } while (!ok);
        return joueur;
    }

    //fonction qui recherche si l'identifiant est présent dans joueur.ser
    public static boolean rechercheId(String id){
        ObjectInputStream ois = null;
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
        try {
            Joueur a = new Joueur(id,mdp);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("joueur.ser"));
            oos.writeObject(a);
            oos.close();
            return a;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean levelEstPossible(int niveau){
        return nivAcess.contains(niveau);
    }

    public void afficheNiveauPossible(){
        int parcours = 0;
        ArrayList<Integer> copie = this.nivAcess;
        for (Integer parcrs: copie){
            if (parcours/3 == 0 && parcours != 0){
                System.out.println();
            }
            System.out.print(" ----- \n | "+  + copie.get(parcours) + " | \n ----- ");
            parcours++;
        }
        System.out.println();
    }


}