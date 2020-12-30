import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class Objet {

    Grille grille;
    int i;

    public abstract void execute();

    public static void afficherPossible(ArrayList<String> liste){
        System.out.print("Vous pouvez utiliser : ");
        for (String s: liste){
            System.out.print(s+", ");
        }
        System.out.println();
        System.out.println("Que voulez-vous utiliser ? (0 pour revenir en arrière");
    }
}
