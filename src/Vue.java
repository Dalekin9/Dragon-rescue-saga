import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vue extends JFrame {

    private JPanel infoJeu;
    private JPanel jeu;
    private JPanel infoObjet;
    private JSlider[] tab;
    private Modele model;
    private Controleur control;
    private Color saved;

    public Vue(Modele mod, Controleur controleur){
        model = mod;
        control = controleur;
        setTitle("Bear Rescue Saga");
        setSize(550,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
    }

    public void sommaire(){
        //création d'un panneau pour l'ajouter à la Vue
        JPanel pan = new JPanel(new GridBagLayout());

        //mise à jour des contraintes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(30,0,0,0);

        pan.add(new JLabel("<html><h1><strong>Bear Rescue Saga</strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        //création du panneau des boutons
        JPanel buttons = new JPanel(new GridBagLayout());

        //création des boutons et de leur évènement lié
        JButton av = new JButton("Mode Aventure");
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeAventure();
            }
        });

        JButton inf = new JButton("Mode Infini");
        inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeInfini();
            }
        });

        JButton regles = new JButton("Règles du jeu");
        regles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regles();
            }
        });

        //ajout des boutons au panneau de boutons
        buttons.add(av, gbc);
        buttons.add(inf, gbc);
        buttons.add(regles, gbc);

        gbc.weighty = 1;
        pan.add(buttons, gbc); //ajout du panneau de bouton au panneau du début
        this.add(pan); //ajout du panneau du début à la Vue
    }

    public void modeAventure(){
        //choix du level
        //apres le level lancer partie == fonction a appeller dans le choix du level
    }

    public void modeInfini(){
        //lancer le mode infini
    }

    public void regles(){
        //lancer les regles
        //penser a faire ca aussi pour le mode texte au debut
    }

}
