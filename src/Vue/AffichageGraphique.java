package Vue;

import Controleur.Controleur;
import Modele.Grille;
import Modele.Joueur;
import Modele.Niveau;
import Modele.Partie;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AffichageGraphique extends JFrame {

    private Controleur control;
    private Color saved;
    private CardLayout cl = new CardLayout();
    private JPanel main = new JPanel(cl);
    private Joueur joueur;
    private Niveau niveau;
    private JPanel jeu;
    private JPanel infoJeu;
    private JButton[][] buttons;
    private int haut;
    private int longu;

    public AffichageGraphique(Controleur controleur){
        control = controleur;
        setTitle("Bear Rescue Saga");
        setSize(550,725);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
    }

    public class ButtObjet extends JButton implements MouseInputListener{

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    //setter et getter

    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
    }

    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }



    //

    public void ecranCo(){
        setAlwaysOnTop(false);
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
        JButton av = new JButton("Se connecter");
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.connexion();
            }
        });

        JButton inf = new JButton("S'inscrire");
        inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.inscription();
            }
        });

        //ajout des boutons au panneau de boutons
        buttons.add(av, gbc);
        buttons.add(inf, gbc);

        gbc.weighty = 1;
        pan.add(buttons, gbc);
        main.add("Ecran de connexion",pan);
        cl.show(main,"Ecran de connexion");
        add(main);
        main.setVisible(true);
    }

    public void connexion() {
        JPanel panneauCo = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panneauButt = new JPanel(new GridBagLayout());

        JTextField ident = new JTextField("identifiant");
        ident.getFont().deriveFont(Font.ITALIC);
        ident.setForeground(Color.gray);
        ident.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField texteField = ((JTextField)e.getSource());
                texteField.setText("");
                texteField.getFont().deriveFont(Font.PLAIN);
                texteField.setForeground(Color.black);
                texteField.removeMouseListener(this);
            }});
        ident.setPreferredSize(new Dimension(150,30));

        JTextField pswd = new JTextField("mot de passe");
        pswd.getFont().deriveFont(Font.ITALIC);
        pswd.setForeground(Color.gray);
        pswd.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField texteField = ((JTextField)e.getSource());
                texteField.setText("");
                texteField.getFont().deriveFont(Font.PLAIN);
                texteField.setForeground(Color.black);
                texteField.removeMouseListener(this);
            }});
        pswd.setPreferredSize(new Dimension(150,30));

        JButton connex = new JButton("Confirmer");
        connex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.seConnecter(ident.getText(),pswd.getText());
            }
        });

        JButton av = new JButton("S'inscrire");
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.inscription();
            }
        });

        gbc.gridx=1;
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy=1;
        panneauButt.add(ident,gbc);
        gbc.gridy=2;
        panneauButt.add(pswd,gbc);
        gbc.gridy=3;
        panneauButt.add(connex,gbc);
        gbc.gridy=5;
        panneauButt.add(av,gbc);
        panneauCo.add(panneauButt);
        main.add("connexion",panneauCo);
        cl.show(main,"connexion");
    }

    public void inscription(){
        JPanel panneauCo = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel panneauButt = new JPanel(new GridBagLayout());

        JTextField ident = new JTextField("identifiant");
        ident.getFont().deriveFont(Font.ITALIC);
        ident.setForeground(Color.gray);
        ident.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField texteField = ((JTextField)e.getSource());
                texteField.setText("");
                texteField.getFont().deriveFont(Font.PLAIN);
                texteField.setForeground(Color.black);
                texteField.removeMouseListener(this);
            }});
        ident.setPreferredSize(new Dimension(150,30));

        JTextField pswd = new JTextField("mot de passe");
        pswd.getFont().deriveFont(Font.ITALIC);
        pswd.setForeground(Color.gray);
        pswd.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField texteField = ((JTextField)e.getSource());
                texteField.setText("");
                texteField.getFont().deriveFont(Font.PLAIN);
                texteField.setForeground(Color.black);
                texteField.removeMouseListener(this);
            }});
        pswd.setPreferredSize(new Dimension(150,30));

        JTextField pswd2 = new JTextField("mot de passe");
        pswd2.getFont().deriveFont(Font.ITALIC);
        pswd2.setForeground(Color.gray);
        pswd2.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField texteField = ((JTextField)e.getSource());
                texteField.setText("");
                texteField.getFont().deriveFont(Font.PLAIN);
                texteField.setForeground(Color.black);
                texteField.removeMouseListener(this);
            }});
        pswd2.setPreferredSize(new Dimension(150,30));

        JButton inscr = new JButton("Confirmer");
        inscr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.sInscrire(ident.getText(),pswd.getText(),pswd2.getText());
            }
        });

        JButton av = new JButton("Se connecter");
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.connexion();
            }
        });

        gbc.gridx=1;
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy=1;
        panneauButt.add(ident,gbc);
        gbc.gridy=2;
        panneauButt.add(pswd,gbc);
        gbc.gridy=3;
        panneauButt.add(pswd2,gbc);
        gbc.gridy=4;
        panneauButt.add(inscr,gbc);
        gbc.gridy=5;
        panneauButt.add(av,gbc);
        panneauCo.add(panneauButt);
        main.add("Inscription",panneauCo);
        cl.show(main,"Inscription");
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
                control.modeAventure();
            }
        });

        JButton inf = new JButton("Mode Infini");
        inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.modeInfini();
            }
        });

        JButton regles = new JButton("Règles du jeu");
        regles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.regles();
            }
        });

        //ajout des boutons au panneau de boutons
        buttons.add(av, gbc);
        buttons.add(inf, gbc);
        buttons.add(regles, gbc);

        gbc.weighty = 1;

        pan.add(buttons, gbc); //ajout du panneau de bouton au panneau du début
        main.add("SOMMAIRE",pan); //ajout du panneau du début à la Vue
        cl.show(main,"SOMMAIRE");
    }

    public void modeAventure() {
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10,0,0,0);
        JLabel l = new JLabel("CHOIX DU NIVEAU");
        pan.add(l,gbc);
        gbc.anchor =GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pan.add(choixDesLevels(),gbc);
        main.add("AVENTURE",pan);
        cl.show(main,"AVENTURE");
    }

    public JPanel choixDesLevels(){
        JPanel levels = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx=0;
        gbc.gridy=0;
        JButton lvl1 = new JButton("Niveau 1");
        lvl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,1);
            }
        });
        levels.add(lvl1,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        JButton lvl2 = new JButton("Niveau 2");
        lvl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,2);
            }
        });
        levels.add(lvl2,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        JButton lvl3 = new JButton("Niveau 3");
        lvl3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,3);
            }
        });
        levels.add(lvl3,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        JButton lvl4 = new JButton("Niveau 4");
        lvl4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,4);
            }
        });
        levels.add(lvl4,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        JButton lvl5 = new JButton("Niveau 5");
        lvl5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,5);
            }
        });
        levels.add(lvl5,gbc);
        return levels;
    }

    public void presentationLevel(Niveau niveau){
        JPanel level = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,30,0);
        gbc.gridx=1;
        gbc.gridy=0;
        JLabel numLevel = new JLabel("<html><h1><strong>Niveau " + niveau.id + "</strong></h1><hr></html>");
        numLevel.setBorder(BorderFactory.createLineBorder(Color.black));
        numLevel.setPreferredSize(new Dimension(150,80));
        numLevel.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(numLevel,gbc);

        gbc.insets = new Insets(0,0,10,0);
        gbc.gridy=1;

        JLabel objectif = new JLabel("OBJECTIFS" );
        objectif.setBorder(BorderFactory.createLineBorder(Color.black));
        objectif.setPreferredSize(new Dimension(100,40));
        objectif.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(objectif,gbc);

        gbc.insets = new Insets(0,0,5,0);
        gbc.gridy=2;
        JLabel numAnimHelp= new JLabel("Ours à sauver : " + niveau.getNb_animaux());
        numAnimHelp.setBorder(BorderFactory.createLineBorder(Color.black));
        numAnimHelp.setPreferredSize(new Dimension(150,30));
        numAnimHelp.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(numAnimHelp,gbc);

        if (niveau.getNb_coup_max() != -1){
            gbc.insets = new Insets(0,0,10,0);
            gbc.gridy=3;
            JLabel numCoupMax= new JLabel("Vous aurez " + niveau.getNb_coup_max() + " coups pour finir ce niveau");
            numCoupMax.setBorder(BorderFactory.createLineBorder(Color.black));
            numCoupMax.setPreferredSize(new Dimension(250,30));
            numCoupMax.setHorizontalAlignment(SwingConstants.CENTER);
            level.add(numCoupMax,gbc);
        }

        gbc.insets = new Insets(20,0,0,0);
        gbc.gridy=4;
        JButton demarrer= new JButton("Jouer");
        demarrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initGame();
            }
        });
        demarrer.setBorder(BorderFactory.createLineBorder(Color.black));
        demarrer.setPreferredSize(new Dimension(100,40));
        demarrer.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(demarrer,gbc);

        gbc.gridy=5;
        gbc.insets = new Insets(60,0,0,0);
        gbc.gridx=0;
        JButton retour= new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.modeAventure();
            }
        });
        retour.setBorder(BorderFactory.createLineBorder(Color.black));
        retour.setPreferredSize(new Dimension(100,40));
        retour.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(retour,gbc);

        gbc.gridx=2;
        JButton leave= new JButton("Quitter");
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.exit();
            }
        });
        leave.setBorder(BorderFactory.createLineBorder(Color.black));
        leave.setPreferredSize(new Dimension(100,40));
        leave.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(leave,gbc);

        main.add("PRESENTATION",level);
        cl.show(main,"PRESENTATION");
    }

    public void finLevel(Niveau niveau){
        JPanel level = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,30,0);
        gbc.gridx=0;
        gbc.gridy=0;
        JLabel numLevel = new JLabel("<html><h1><strong>Niveau " + niveau.id + "</strong></h1><hr></html>");
        numLevel.setBorder(BorderFactory.createLineBorder(Color.black));
        numLevel.setPreferredSize(new Dimension(150,80));
        numLevel.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(numLevel,gbc);


        gbc.insets = new Insets(0,0,5,0);
        gbc.gridx=0;
        gbc.gridy=1;
        int compt = 1;
        for (var s : niveau.best_score.entrySet()){
            compt++;
            JLabel infoScore = new JLabel(compt +" : " + s.getValue() +" -> " + s.getKey() + " points");
            //infoScore.setBorder(BorderFactory.createLineBorder(Color.black));
            infoScore.setPreferredSize(new Dimension(200,20));
            infoScore.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridy=compt+1;
            level.add(infoScore,gbc);
        }
        while (compt <6){

            JLabel infoScore = new JLabel(compt + " : Pas encore de meilleur score");
            //infoScore.setBorder(BorderFactory.createLineBorder(Color.black));
            infoScore.setPreferredSize(new Dimension(200,20));
            infoScore.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridy=compt+1;
            level.add(infoScore,gbc);
            compt++;
        }

        gbc.insets = new Insets(20,0,0,0);
        gbc.gridx=0;
        gbc.gridy=compt+1;
        JButton next= new JButton("Suivant");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.modeAventure();
            }
        });
        next.setBorder(BorderFactory.createLineBorder(Color.black));
        next.setPreferredSize(new Dimension(100,40));
        next.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(next,gbc);

        main.add("END",level);
        cl.show(main,"END");
    }

    public void updateGrille(){
        jeu.remove(infoJeu);
        Grille grid = niveau.getGrid();
        String color = "";
        Icon icon;
        JButton butt;
        buttons = new JButton[haut][longu];
        infoJeu = new JPanel(new GridLayout(haut,longu));
        GridBagConstraints gridC = new GridBagConstraints();
        for(int i = 0; i<haut; i++){
            for(int j = 0; j<longu; j ++ ){
                butt = new JButton();
                switch (grid.gril[i][j].getIs()) {
                    case 'a':
                        color = "bear";
                        butt.setContentAreaFilled(false);
                        butt.setOpaque(false);
                        butt.setBorder(null);
                        break;
                    case ' ':
                    case 's':
                        color = "";
                        butt.setContentAreaFilled(false);
                        butt.setOpaque(false);
                        butt.setBorder(null);
                        break;
                    default:
                        color = String.valueOf(grid.gril[i][j].getIs());
                        break;
                }
                icon = new ImageIcon("C:\\Users\\Dalekin\\Desktop\\Colors-Icons\\"+color+".png");
                butt.setIcon(icon);
                butt.setPreferredSize(new Dimension(50,50));
                buttons[i][j] = butt;
                infoJeu.add(butt);
            }
        }
        addAllListeners();
        infoJeu.setMinimumSize(new Dimension(50*longu,50*haut));
        gridC.gridx = 0;
        gridC.gridy = 1;
        gridC.weightx = 0;
        gridC.weighty = 0.6;
        jeu.add(infoJeu, gridC);
        main.add("game",jeu);
        cl.show(main,"game");
    }

    public void initGame(){
        control.setPartie(new Partie(joueur,niveau));
        haut = niveau.getGrid().getHaut();
        longu = niveau.getGrid().getLongu();
        infoJeu = new JPanel(new GridLayout(haut, longu,10,10));
        jeu = new JPanel(new GridBagLayout());
        buttons = new JButton[haut][longu];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.2;
        gbc.weightx = 1;

        jeu.add(etatJeu(0, 1,5), gbc);

        Grille grid = niveau.getGrid();
        String color = "";
        Icon icon;
        JButton butt;
        buttons = new JButton[haut][longu];
        infoJeu = new JPanel(new GridLayout(haut,longu));
        for(int i = 0; i<haut; i++){
            for(int j = 0; j<longu; j ++ ){
                butt = new JButton();
                switch (grid.gril[i][j].getIs()) {
                    case 'a':
                        color = "bear";
                        butt.setContentAreaFilled(false);
                        butt.setOpaque(false);
                        butt.setBorder(null);
                        break;
                    case ' ':
                    case 's':
                        color = "";
                        butt.setContentAreaFilled(false);
                        butt.setOpaque(false);
                        butt.setBorder(null);
                        break;
                    default:
                        color = String.valueOf(grid.gril[i][j].getIs());
                        break;
                }
                icon = new ImageIcon("C:\\Users\\Dalekin\\Desktop\\Colors-Icons\\"+color+".png");
                butt.setIcon(icon);
                butt.setPreferredSize(new Dimension(50,50));
                buttons[i][j] = butt;
                infoJeu.add(butt);
            }
        }
        addAllListeners();
        infoJeu.setMinimumSize(new Dimension(50*longu,50*haut));
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0.6;
        jeu.add(infoJeu,gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.BELOW_BASELINE;
        jeu.add(infoObjets(), gbc);
        main.add("game",jeu);
        cl.show(main,"game");
    }

    public JPanel infoObjets(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setForeground(new Color(52, 5, 99, 255));
        panel.setVisible(true);
        GridBagConstraints gbc1 = new GridBagConstraints();
        ButtObjet button;
        String str;
        ArrayList<String> list = new ArrayList<>();
        list.add("Fusee");
        list.add("Bombe");
        list.add("Pioche");

        gbc1.gridy = 0;
        gbc1.ipadx = 20;
        for(int i = 0; i<3; i++){
            int x = i;
            button = new ButtObjet();
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image image = toolkit.getImage("C:\\Users\\Dalekin\\Desktop\\Colors-Icons\\"+list.get(x)+".png");
                    Cursor c = toolkit.createCustomCursor(image , new Point(main.getX(), main.getY()), "img");
                    main.setCursor (c);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    main.setCursor(Cursor.getDefaultCursor());
                    System.out.println(e.getXOnScreen()-getX());
                    System.out.println(e.getYOnScreen()-getY()-getInsets().top);
                    control.objetUsed(e.getXOnScreen()-getX()+20, e.getYOnScreen()-getY()+20, list.get(x));
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            button.setPreferredSize(new Dimension(70,70));
            if(joueur.getObjAcces().contains(list.get(i))){
                str = list.get(i);
            }else{
                str = list.get(i) + "Locked";
            }
            button.setIcon(new ImageIcon("C:\\Users\\Dalekin\\Desktop\\Colors-Icons\\"+ str + ".png"));
            gbc1.gridx = i;
            panel.add(button,gbc1);
            setVisible(true);
        }
        return panel;
    }

    public JPanel etatJeu(int score, int animRes, int coupRes){
        JPanel etat = new JPanel(new GridBagLayout());
        etat.setPreferredSize(new Dimension(550, 90));
        GridBagConstraints gb = new GridBagConstraints();
        JLabel scoreLabel = new JLabel();
        if(score == 0){
            scoreLabel.setText("000000");
        }
        String str = Integer.toString(score);
        String tmp = "";
        for(int i = 0; i < 6-str.length(); i++){
            tmp = tmp + "0";
        }
        tmp += str;
        scoreLabel.setText(tmp);
        gb.gridx = 0;
        gb.gridy = 3;
        gb.weighty = 0.5;
        gb.weightx = 1.;

        etat.add(scoreLabel, gb);
        return etat;
    }

    public void addAllListeners(){
        for(int i = 0; i<haut; i++){
            for(int j = 0; j<longu; j++){
                int x = i;
                int y = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        control.blockClicked(x,y);
                    }
                });
            }
        }
    }

    public void modeInfini(){
        //lancer le mode infini
        JPanel pan = new JPanel();
        JLabel l = new JLabel("MODE INFINI");
        pan.add(l);
        main.add("INFINI",pan);
        cl.show(main,"INFINI");
    }

    public void regles(){
        //lancer les regles
        //penser a faire ca aussi pour le mode texte au debut
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy =0;
        JLabel l = new JLabel("REGLES");
        pan.add(l,gbc);
        gbc.gridy = 1;
        JLabel regles = new JLabel("<html><pre>" +
                "   Bear Rescue Saga est un jeu de puzzle dont le but est de  " +
                "<br>" +
                "       parvenir à sauver la vie de tous les petits ours.     " +
                "<br>" +
                "En déplaçant des boîtes, vous êtes appelé à créer différentes" +
                "<br>" +
                "               combinaisons de même couleur.                 " +
                "<br>" +
                " Une fois l'alignement effectué, les cubes disparaissent et  " +
                "<br>" +
                "            l'animal  ciblé descend de quelques étages.      " +
                "<br>" +
                "   Ce n'est qu'une fois tous les animaux parvenus au bas de  " +
                "<br>" +
                "               l'écran que la partie se termine.             " +
                "</pre></html>");
        regles.setHorizontalAlignment(JLabel.CENTER);
        regles.setPreferredSize(new Dimension(450,250));
        regles.setBorder(BorderFactory.createLineBorder(Color.black));
        gbc.anchor = GridBagConstraints.CENTER;
        pan.add(regles,gbc);
        JButton retour= new JButton("Retour");
        retour.setBorder(BorderFactory.createLineBorder(Color.black));
        retour.setPreferredSize(new Dimension(100,40));
        retour.setHorizontalAlignment(SwingConstants.CENTER);
        pan.add(retour,gbc);

        gbc.gridx=2;
        JButton leave= new JButton("Quitter");
        leave.setBorder(BorderFactory.createLineBorder(Color.black));
        leave.setPreferredSize(new Dimension(100,40));
        leave.setHorizontalAlignment(SwingConstants.CENTER);
        pan.add(leave,gbc);
        main.add("REGLES",pan);
        cl.show(main,"REGLES");
    }

}
