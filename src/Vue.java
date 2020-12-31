import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Vue extends JFrame {

    private JPanel infoJeu;
    private JPanel infoObjet;
    private Partie partie;
    int longu;
    int haut;
    private JPanel jeu;
    private Controleur control;
    private CardLayout cl = new CardLayout();
    private JPanel main = new JPanel(cl);
    private JButton [][] grille ;


    public Vue(Partie partie, Controleur controleur){
        this.partie = partie;
        longu = partie.getLvl().getGrid().getLongu();
        haut = partie.getLvl().getGrid().gril.length;
        jeu = new JPanel(new GridLayout(haut, longu,5,5));
        grille = new JButton[haut][longu];
        control = controleur;
        infoJeu = new JPanel();
        setTitle("Bear Rescue Saga");
        setSize(550,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

   /* public class Carre extends JPanel implements MouseInputListener {
        Random rand = new Random();
        boolean etat;
        int xClick, yClick;
        Modele mod;

        public Carre() {
            int x = rand.nextInt(Cadre.this.getWidth());
            int y = rand.nextInt(Cadre.this.getHeight());
            mod = new Modele();
            setBounds(x, y, 50, 50);
            setBackground(mod.getColor());
            etat = false;
        }
    }*/

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
                initGame();
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
        main.add("SOMMAIRE",pan); //ajout du panneau du début à la Vue
        add(main);
        main.setVisible(true);
    }

    public void modeAventure() {
        //ecranChoixLvl();      <------ debutLvl(int n); < ----- InterfaceJeu();
        JPanel pan = new JPanel();
        JLabel l = new JLabel("FCK YOU");
        pan.add(l);
        main.add("MERDE",pan);
        ((CardLayout)main.getLayout()).show(main,"MERDE");
    }

    public void modeInfini(){

    }

    public void debutLvl(int n){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.2;

        JLabel infosJeu = new JLabel(partie.getLvl().afficherHtml());
        infoJeu.add(infosJeu);
        // à modif : add(infoJeu,gbc);
    }

    public void updateGrille(){
        Grille grid = partie.getLvl().getGrid();
        String color = "" ;
        Icon icon;
        JButton butt;
        jeu = new JPanel(new GridLayout(haut,longu));
        for(int i = 0; i<haut; i++){
            for(int j = 0; j<longu; j ++ ){
                butt = new JButton();
                switch (grid.gril[i][j].getIs()){
                    case 'B':
                        color = "blue";
                        break;
                    case 'J':
                        color = "yellow";
                        break;
                    case 'O':
                        color = "orange";
                        break;
                    case 'R':
                        color = "red";
                        break;
                    case 'V':
                        color = "purple";
                        break;
                    case 'a':
                        color = "al";
                        butt.setContentAreaFilled(false);
                        butt.setOpaque(false);
                        butt.setBorder(null);
                        break;
                    default:
                        color = "";
                        butt.setContentAreaFilled(false);
                        butt.setOpaque(false);
                        butt.setBorder(null);
                        break;
                }
                icon = new ImageIcon("C:\\Users\\Dalekin\\Desktop\\Colors-Icons\\"+color+".png");
                butt.setIcon(icon);
                butt.setPreferredSize(new Dimension(50,50));
                grille[i][j] = butt;
                jeu.add(butt);
            }
        }
    }

    public void initGame(){
        Icon icon = new ImageIcon("C:\\Users\\Dalekin\\Pictures\\raw.jpg");
        JPanel game = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        updateGrille();
        partie.getLvl().getGrid().supprimer(2,0);
        updateGrille();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.6;
        game.add(jeu,gbc);
        main.add("game",game);

        ((CardLayout)main.getLayout()).show(main,"game");
    }

    public void regles(){

    }

}
