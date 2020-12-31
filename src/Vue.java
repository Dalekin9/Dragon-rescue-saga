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

public class Vue extends JFrame {

    private JPanel infoJeu;
    private JPanel jeu;
    private JPanel infoObjet;
    private Controleur control;
    private Color saved;
    private CardLayout cl = new CardLayout();
    private JPanel main = new JPanel(cl);

    public Vue(Controleur controleur){
        control = controleur;
        setTitle("Bear Rescue Saga");
        setSize(550,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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
                connexion();
            }
        });

        JButton inf = new JButton("S'inscrire");
        inf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inscription();
            }
        });

        //ajout des boutons au panneau de boutons
        buttons.add(av, gbc);
        buttons.add(inf, gbc);

        gbc.weighty = 1;
        pan.add(buttons, gbc);
        main.add("Ecran de connexion",pan); //ajout du panneau du début à la Vue
        ((CardLayout)main.getLayout()).show(main,"Ecran de connexion");
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
                if (Joueur.rechercheId(ident.getText())){
                    System.out.println("good joueur");
                    Joueur test = Joueur.getJoueur(ident.getText());
                    if (pswd.getText().equals(test.getMdp())) {
                        sommaire();
                    }
                    System.out.println(pswd.getText().equals(test.getMdp()));
                    System.out.println(test.getMdp());
                    System.out.println(pswd.getText());
                    System.out.println("balo pas le mdp");
                }
                gbc.gridx=1;
                JLabel erreur =new JLabel("Identifiant/Mot de passe incorrect !");
                panneauButt.add(erreur,gbc);
                panneauCo.add(panneauButt);
                main.add("Inscription",panneauCo);
                ((CardLayout)main.getLayout()).show(main,"Inscription");
            }
        });

        JButton av = new JButton("S'inscrire");
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inscription();
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
                if (!Joueur.rechercheId(ident.getText()) && ! ident.getText().equals("")) {
                    if (pswd.getText().equals(pswd2.getText()) && !pswd.getText().equals("")) {
                        Joueur.creerJoueur(ident.getText(),pswd.getText());
                        sommaire();
                    } else {
                        gbc.gridx = 1;
                        JLabel erreur = new JLabel("Mots de passe différents !");
                        panneauButt.add(erreur, gbc);
                        panneauCo.add(panneauButt);
                        main.add("Inscription",panneauCo);
                        ((CardLayout)main.getLayout()).show(main,"Inscription");
                    }
                } else {
                    gbc.gridx = 1;
                    JLabel erreur = new JLabel("Identifiant déjà utilisé !");
                    panneauButt.add(erreur, gbc);
                    panneauCo.add(panneauButt);
                    main.add("Inscription",panneauCo);
                    ((CardLayout)main.getLayout()).show(main,"Inscription");
                }
            }
        });

        JButton av = new JButton("Se connecter");
        av.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connexion();
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
                Thread thread = new Thread() {
                    public void run() {
                        modeAventure();
                    }
                };
                thread.start();
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
        main.add("SOMMAIRE",pan); //ajout du panneau du début à la Vue
        ((CardLayout)main.getLayout()).show(main,"SOMMAIRE");
    }

    public void modeAventure() {
        JPanel pan = new JPanel();
        JLabel l = new JLabel("FCK YOU");
        pan.add(l);
        main.add("MERDE",pan);
        ((CardLayout)main.getLayout()).show(main,"MERDE");
    }

    public void modeInfini(){
        //lancer le mode infini
    }

    public void regles(){
        //lancer les regles
        //penser a faire ca aussi pour le mode texte au debut
    }

}
