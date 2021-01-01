package Vue;

import Controleur.Controleur;
import Modele.Joueur;
import Modele.Niveau;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.chrono.IsoChronology;
import java.util.HashMap;
import java.util.Map;

public class AffichageGraphique extends JFrame {

    private Controleur control;
    private Color saved;
    private CardLayout cl = new CardLayout();
    private JPanel main = new JPanel(cl);
    private Joueur joueur;
    private Niveau niveau;

    public AffichageGraphique(Controleur controleur){
        control = controleur;
        setTitle("Bear Rescue Saga");
        setSize(550,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        main.add("Connexion",panneauCo);
        ((CardLayout)main.getLayout()).show(main,"Connexion");
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
        ((CardLayout)main.getLayout()).show(main,"Inscription");
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
        ((CardLayout)main.getLayout()).show(main,"SOMMAIRE");
        add(main);
        setVisible(true);
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
        add(main);
        setVisible(true);
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
        lvl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,2);
            }
        });
        levels.add(lvl2,gbc);
        gbc.gridx=0;
        gbc.gridy=1;
        JButton lvl3 = new JButton("Niveau 3");
        lvl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,3);
            }
        });
        levels.add(lvl3,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        JButton lvl4 = new JButton("Niveau 4");
        lvl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.choixLevel(joueur,4);
            }
        });
        levels.add(lvl4,gbc);
        gbc.gridx=0;
        gbc.gridy=2;
        JButton lvl5 = new JButton("Niveau 5");
        lvl1.addActionListener(new ActionListener() {
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
                control.voirLesLevels();
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

        main.add(level);
        add(main);

        setVisible(true);
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
                control.voirLesLevels();
            }
        });
        next.setBorder(BorderFactory.createLineBorder(Color.black));
        next.setPreferredSize(new Dimension(100,40));
        next.setHorizontalAlignment(SwingConstants.CENTER);
        level.add(next,gbc);

        main.add(level);
        add(main);
        setVisible(true);
    }

    public void modeInfini(){
        //lancer le mode infini
        JPanel pan = new JPanel();
        JLabel l = new JLabel("MODE INFINI");
        pan.add(l);
        main.add("INFINI",pan);
        add(main);
        setVisible(true);
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
        add(main);
        setVisible(true);
    }

}
