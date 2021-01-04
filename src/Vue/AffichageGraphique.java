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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AffichageGraphique extends JFrame {

    private Controleur control;
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


    //---------------------------------------------------------
    //                   --- PARTIE 1 ---                     -
    //                       Setters                          -
    //---------------------------------------------------------


    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
    }

    public void setNiveau(Niveau niveau){
        this.niveau = niveau;
    }


    //---------------------------------------------------------
    //                   --- PARTIE 2 ---                     -
    //                      Affichage                         -
    //---------------------------------------------------------


    //affiche de l'ecran d'accueil
    public void ecranCo(){
        setAlwaysOnTop(false);

        JLabel jLabel1 = new JLabel();

        JButton jButton3 = new JButton();
        JButton jButton4 = new JButton();
        JLabel jLabel3 = new JLabel();

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new java.awt.GridBagLayout());


        jLabel1.setText("<html><h1><strong>Rescue All Dragons</strong></h1><hr></html>");
        jLabel1.setForeground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30,0,0,0);
        sec.add(jLabel1, gbc);

        JPanel butt= new JPanel();
        butt.setOpaque(false);
        butt.setLayout(new GridBagLayout());

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        jButton3.setText("Connexion");
        jButton3.setPreferredSize(new Dimension(150,55));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.connexion();
            }
        });
        butt.add(jButton3, gbc);

        jButton4.setText("Incription");
        jButton4.setPreferredSize(new Dimension(150,55));
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.inscription();
            }
        });
        butt.add(jButton4, gbc);

        gbc.weighty = 1;

        sec.add(butt,gbc);

        princ.add(sec);
        sec.setBounds(0, 0, 550, 725);

        jLabel3.setIcon(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\principal.jpg"));
        princ.add(jLabel3);
        jLabel3.setBounds(0, 0, 550, 725);

        main.add(princ, "accueil");

        getContentPane().add(main);
        main.setBounds(0, 0, 550, 725);
        setVisible(true);
    }

    //affiche de l'ecran de connexion
    public void connexion() {

        JLabel jLabel1 = new JLabel();

        JButton jButton3 = new JButton();
        JButton jButton4 = new JButton();
        JLabel jLabel3 = new JLabel();

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("<html><h1><strong>Connexion</strong></h1><hr></html>");
        jLabel1.setForeground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 1;
        gbc.gridx=1;
        sec.add(jLabel1, gbc);

        JPanel butt= new JPanel();
        butt.setOpaque(false);
        butt.setLayout(new GridBagLayout());

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JTextField ident = new JTextField("identifiant");
        ident.setPreferredSize(new Dimension(150,30));
        ident.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 3;
        gbc.gridx++;
        gbc.gridy++;
        butt.add(ident,gbc);

        JTextField pswd = new JTextField("mot de passe");
        pswd.setPreferredSize(new Dimension(150,30));
        pswd.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy++;
        butt.add(pswd,gbc);

        jButton3.setText("Confirmer");
        jButton3.setPreferredSize(new Dimension(150,30));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.seConnecter(ident.getText(),pswd.getText());
            }
        });
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy++;
        butt.add(jButton3, gbc);

        jButton4.setText("Incription");
        jButton4.setPreferredSize(new Dimension(150,30));
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.inscription();
            }
        });
        gbc.gridy++;
        butt.add(jButton4, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        sec.add(butt,gbc);

        princ.add(sec);
        sec.setBounds(0, 0, 550, 725);

        jLabel3.setIcon(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\connexion.jpg"));
        princ.add(jLabel3);
        jLabel3.setBounds(0, 0, 550, 725);

        main.add(princ, "connexion");
        cl.show(main,"connexion");

    }

    //affichage de l'ecran d'inscription
    public void inscription(){

        JLabel jLabel1 = new JLabel();

        JButton jButton3 = new JButton();
        JButton jButton4 = new JButton();
        JLabel jLabel3 = new JLabel();

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new java.awt.GridBagLayout());


        jLabel1.setText("<html><h1><strong>Inscription</strong></h1><hr></html>");
        jLabel1.setForeground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 1;
        gbc.gridx=1;
        sec.add(jLabel1, gbc);

        JPanel butt= new JPanel();
        butt.setOpaque(false);
        butt.setLayout(new GridBagLayout());

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JTextField ident = new JTextField("identifiant");
        ident.setPreferredSize(new Dimension(150,30));
        ident.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 3;
        gbc.gridx++;
        gbc.gridy++;
        butt.add(ident,gbc);

        JTextField pswd = new JTextField("mot de passe");
        pswd.setPreferredSize(new Dimension(150,30));
        pswd.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy++;
        butt.add(pswd,gbc);

        JTextField pswd2 = new JTextField("mot de passe");
        pswd2.setPreferredSize(new Dimension(150,30));
        pswd2.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy++;
        butt.add(pswd2,gbc);

        jButton3.setText("Confirmer");
        jButton3.setPreferredSize(new Dimension(150,30));
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.sInscrire(ident.getText(),pswd.getText(),pswd2.getText());
            }
        });
        gbc.insets = new Insets(10,0,0,0);
        gbc.gridy++;
        butt.add(jButton3, gbc);

        jButton4.setText("Connexion");
        jButton4.setPreferredSize(new Dimension(150,30));
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.connexion();
            }
        });
        gbc.gridy++;
        butt.add(jButton4, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        sec.add(butt,gbc);

        princ.add(sec);
        sec.setBounds(0, 0, 550, 725);

        jLabel3.setIcon(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\connexion.jpg"));
        princ.add(jLabel3);
        jLabel3.setBounds(0, 0, 550, 725);

        main.add(princ, "inscription");
        cl.show(main,"inscription");
    }

    //affichage du sommaire
    public void sommaire(){

        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new GridBagLayout());

        jLabel1.setText("<html><h1><strong>Sommaire</strong></h1><hr></html>");
        jLabel1.setForeground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30,0,0,0);
        sec.add(jLabel1, gbc);

        JPanel butt= new JPanel();
        butt.setOpaque(false);
        butt.setLayout(new GridBagLayout());

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //création des boutons et de leur évènement lié
        JButton av = new JButton("Mode Aventure");
        av.setPreferredSize(new Dimension(150,40));
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.modeAventure();
            }
        });

        JButton inf = new JButton("Mode Infini");
        inf.setPreferredSize(new Dimension(150,40));
        inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.modeInfini();
            }
        });

        JButton regles = new JButton("Règles du jeu");
        regles.setPreferredSize(new Dimension(150,40));
        regles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.regles();
            }
        });
        gbc.insets = new Insets(0,0,70,0);
        butt.add(av, gbc);
        gbc.insets = new Insets(10,0,70,0);
        butt.add(inf,gbc);
        gbc.insets = new Insets(0,0,50,0);
        butt.add(regles,gbc);
        gbc.weighty=1;
        sec.add(butt,gbc);

        princ.add(sec);
        sec.setBounds(0, 0, 550, 725);

        jLabel3.setIcon(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\sommaire.jpg"));
        princ.add(jLabel3);
        jLabel3.setBounds(0, 0, 550, 725);

        main.add(princ, "sommaire");
        cl.show(main,"sommaire");


    }

    //affichage du mode aventure
    // -> montre tous les levels
    public void modeAventure() {

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(30,0,0,0);
        JLabel l = new JLabel("<html><h1><strong>Choix du Niveau</strong></h1><hr></html>");
        l.setForeground(Color.BLACK);
        sec.add(l,gbc);

        gbc.anchor =GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty=1;
        sec.add(choixDesLevels(),gbc);


        princ.add(sec);
        sec.setBounds(0,0,550,725);

        JLabel jLabel3 = new JLabel();
        jLabel3.setIcon(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\aventure.jpg"));
        princ.add(jLabel3);
        jLabel3.setBounds(0,0,550,725);
        main.add("AVENTURE",princ);
        cl.show(main,"AVENTURE");
    }

    //retourne un panneau contenant tous les niveaux
    public JPanel choixDesLevels(){
        JPanel levels = new JPanel(new GridBagLayout());
        levels.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridx=0;
        gbc.gridy=0;
        JButton lvl1 = new JButton("Niveau 1");
        lvl1.setPreferredSize(new Dimension(150,70));
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
        lvl2.setPreferredSize(new Dimension(150,70));
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
        lvl3.setPreferredSize(new Dimension(150,70));
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
        lvl4.setPreferredSize(new Dimension(150,70));
        lvl4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,4);
            }
        });
        levels.add(lvl4,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,0,40,0);
        JButton lvl5 = new JButton("Niveau 5");
        lvl5.setPreferredSize(new Dimension(150,70));
        lvl5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,5);
            }
        });
        levels.add(lvl5,gbc);
        gbc.gridy = 3;

        JPanel ret  = new JPanel(new BorderLayout());
        JButton retour= new JButton("Retour");
        retour.setPreferredSize(new Dimension(80,40));
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.goSommaire();
            }
        });
        ret.add(retour);
        ret.setOpaque(false);

        levels.add(ret,gbc);
        return levels;
    }

    //affiche de l'ecran de presentation du niveau choisi
    public void presentationLevel(Niveau niveau){

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(30,0,0,0);
        JLabel l = new JLabel("<html><h1><strong>Niveau " + niveau.id + " </strong></h1><hr></html>");
        l.setForeground(Color.BLACK);
        sec.add(l,gbc);

        JPanel third = new JPanel(new GridBagLayout());
        third.setOpaque(false);

        JPanel pres = new JPanel(new GridBagLayout());
        pres.setOpaque(true);
        pres.setBackground(new Color(225,225,225,120));
        JLabel objectif = new JLabel("OBJECTIFS" );
        //objectif.setBorder(BorderFactory.createLineBorder(Color.black));
        objectif.setPreferredSize(new Dimension(100,30));
        objectif.setHorizontalAlignment(SwingConstants.CENTER);
        objectif.setForeground(Color.BLACK);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,10,0);
        pres.add(objectif,gbc);

        gbc.insets = new Insets(0,0,10,0);
        JLabel numAnimHelp= new JLabel("Dragons à sauver : " + niveau.getNb_animaux());
        //numAnimHelp.setBorder(BorderFactory.createLineBorder(Color.black));
        numAnimHelp.setPreferredSize(new Dimension(150,30));
        numAnimHelp.setHorizontalAlignment(SwingConstants.CENTER);
        numAnimHelp.setForeground(Color.BLACK);

        pres.add(numAnimHelp,gbc);

        if (niveau.getNb_coup_max() != -1){
            gbc.insets = new Insets(0,0,10,0);
            JLabel numCoupMax= new JLabel("Vous aurez " + niveau.getNb_coup_max() + " coups pour finir ce niveau");
            //numCoupMax.setBorder(BorderFactory.createLineBorder(Color.black));
            numCoupMax.setPreferredSize(new Dimension(250,30));
            numCoupMax.setHorizontalAlignment(SwingConstants.CENTER);
            numCoupMax.setForeground(Color.BLACK);
            pres.add(numCoupMax,gbc);
        }

        JPanel play = new JPanel(new BorderLayout());
        JButton demarrer= new JButton("Jouer");
        demarrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initGame();
            }
        });
        demarrer.setPreferredSize(new Dimension(100,50));
        demarrer.setHorizontalAlignment(SwingConstants.CENTER);
        play.add(demarrer);
        play.setOpaque(false);


        JPanel ret  = new JPanel(new BorderLayout());

        JButton retour= new JButton("Retour");
        retour.setPreferredSize(new Dimension(80,40));
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.modeAventure();
            }
        });
        ret.add(retour);
        ret.setOpaque(false);

        GridBagConstraints abc = new GridBagConstraints();
        abc.insets = new Insets(0,0,20,0);
        abc.gridy = 1;
        third.add(pres,abc);
        abc.gridy = 2;
        third.add(play,abc);
        abc.insets = new Insets(0,0,40,0);
        abc.gridy = 3;
        third.add(ret,abc);

        gbc.weighty = 1;
        sec.add(third,gbc);


        princ.add(sec);
        sec.setBounds(0,0,550,725);

        JLabel icon = new JLabel(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\presentLevel.png"));
        princ.add(icon);
        icon.setBounds(0,0,550,725);

        main.add("PRESENTATION",princ);
        cl.show(main,"PRESENTATION");
    }

    //affichage de l'ecran de fin de niveau
    public void finLevel(Niveau niveau, int finJeu) {

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        JPanel sec = new JPanel();
        sec.setOpaque(false);
        sec.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(30, 0, 0, 0);
        JLabel l = new JLabel("<html><h1><strong>Niveau " + niveau.id + "</strong></h1><hr></html>");
        l.setForeground(Color.BLACK);
        sec.add(l,gbc);

        JPanel third = new JPanel(new GridBagLayout());
        third.setOpaque(false);

        JPanel mess = new JPanel(new GridBagLayout());
        mess.setOpaque(true);
        //mess.setBackground(new Color(225,225,225,120));

        gbc.insets = new Insets(20,10,20,10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel info = new JLabel();
       info.setForeground(Color.BLACK);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        if(finJeu == 2) {
            info.setText("Bravo, vous avez gagné !");
            //info.setForeground(new Color(144, 232, 125));
            mess.setBackground(new Color(144, 232, 125,200));
        } else {
            info.setText("Dommage, vous avez echoué.");
            //info.setForeground(new Color(229, 116, 116));
            mess.setBackground(new Color(229, 116, 116,200));
        }
        mess.add(info,gbc);

        JPanel pres = new JPanel(new GridBagLayout());
        pres.setOpaque(true);
        pres.setBackground(new Color(225,225,225,120));

        gbc.insets = new Insets(10,0,10,0);
        int compt = 1;
        for (var s : niveau.best_score.entrySet()){
            compt++;
            JLabel infoScore = new JLabel(compt +" : " + s.getValue() +" -> " + s.getKey() + " points");
            //infoScore.setBorder(BorderFactory.createLineBorder(Color.black));
            infoScore.setPreferredSize(new Dimension(200,20));
            infoScore.setHorizontalAlignment(SwingConstants.CENTER);
            infoScore.setForeground(Color.BLACK);
            gbc.gridy=compt+1;
            pres.add(infoScore,gbc);
        }
        while (compt <6){

            JLabel infoScore = new JLabel(compt + " : Pas encore de meilleur score");
            //infoScore.setBorder(BorderFactory.createLineBorder(Color.black));
            infoScore.setPreferredSize(new Dimension(200,20));
            infoScore.setHorizontalAlignment(SwingConstants.CENTER);
            infoScore.setForeground(Color.BLACK);
            gbc.gridy=compt+1;
            pres.add(infoScore,gbc);
            compt++;
        }

        //

        JPanel play = new JPanel(new BorderLayout());
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
        play.add(next);
        play.setOpaque(false);


        GridBagConstraints abc = new GridBagConstraints();
        abc.insets = new Insets(0,0,60,0);
        abc.gridy = 1;
        third.add(mess,abc);
        abc.gridy = 2;
        third.add(pres,abc);
        abc.gridy = 3;
        third.add(play,abc);
        abc.insets = new Insets(0,0,40,0);

        gbc.weighty = 1;
        sec.add(third,gbc);


        princ.add(sec);
        sec.setBounds(0,0,550,725);

        JLabel icon = new JLabel(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\presentLevel.png"));
        princ.add(icon);
        icon.setBounds(0,0,550,725);

        main.add("FIN_LEVEL",princ);
        cl.show(main,"FIN_LEVEL");


        /*
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


         */

        /*

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


         */

        /*
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
    }*/

    public void updateGrille(){
        jeu.remove(infoJeu);
        Grille grid = niveau.getGrid();
        String color;
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
        //JPanel dd = new JPanel(null);
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
        //dd.add(jeu);
        //jeu.setBounds(0,0, 550, 725);
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
        gb.gridy = 0;
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

    //affiche du mode infini
    public void modeInfini(){
        //lancer le mode infini
        JPanel pan = new JPanel();
        JLabel l = new JLabel("MODE INFINI");
        pan.add(l);
        main.add("INFINI",pan);
        cl.show(main,"INFINI");
    }

    //affichage des règles
    public void regles(){

        JLabel jLabel3 = new JLabel();

        JPanel princ = new JPanel();
        princ.setOpaque(false);
        princ.setLayout(null);

        //lancer les regles
        //penser a faire ca aussi pour le mode texte au debut
        JPanel sec = new JPanel(new GridBagLayout());
        sec.setOpaque(false);
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridy =0;
        JLabel l = new JLabel("<html><h1><strong>Regles</strong></h1><hr></html>");
        l.setForeground(Color.BLACK);
        sec.add(l,gbc);
        gbc.gridy = 1;
        JLabel regles = new JLabel("<html><pre>" +
                "     Rescue All Dragons est un jeu de Réflexion    "+
                "<br>" +
                "<br>" +
                "                 Votre Objectif :                  "+
                "<br>"+
                "           Sauver tous les dragons perdus          " +
                "<br>" +
                "<br>" +
                "Pour y parvenir, vous devrez :                     " +
                "<br>" +
                "  - Détruire des blocs de même matériaux (min 2)   " +
                "<br>" +
                "  - Amenez les dragons au sol pour les sauver      " +
                "<br>" +
                "  - Faire attention aux nombre de coups maximum    " +
                "<br>" +
                "<br>" +
                "La partie est terminée lorsque :                   " +
                "<br>" +
                "  - Vous ne pouvez plus détruire de blocs          " +
                "<br>" +
                "  - Vous avez effectué tous les coups disponibles  " +
                "<br>" +
                "  - Vous avez sauvé tous les dragons          " +
                "<br>" +
                "</pre></html>");
        regles.setForeground(Color.BLACK);
        regles.setOpaque(true);
        regles.setFont(new java.awt.Font("Gill Sans MT", Font.BOLD, 14));
        regles.setBackground(new Color(255,255,255,150));
        regles.setHorizontalAlignment(JLabel.CENTER);
        regles.setPreferredSize(new Dimension(450,350));
        regles.setBorder(BorderFactory.createLineBorder(Color.black));
        gbc.anchor = GridBagConstraints.CENTER;
        sec.add(regles,gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(20,0,0,0);
        JButton retour= new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.goSommaire();
            }
        });
        retour.setBorder(BorderFactory.createLineBorder(Color.black));
        retour.setPreferredSize(new Dimension(100,40));
        retour.setHorizontalAlignment(SwingConstants.CENTER);
        sec.add(retour,gbc);


        princ.add(sec);
        sec.setBounds(0, 0, 550, 725);

        jLabel3.setIcon(new ImageIcon("C:\\Users\\pauli\\bear-rescuse-saga\\src\\Images\\regle.png"));
        princ.add(jLabel3);
        jLabel3.setBounds(0, 0, 550, 725);

        main.add(princ, "REGLES");
        cl.show(main,"REGLES");
    }

}
