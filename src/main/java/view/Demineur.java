package view;

import controller.DemineurJavaFXController;
import model.*;
import javax.swing.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.event.WindowEvent;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.Border;

//le jeux...
public class Demineur
        extends JFrame
        implements MouseListener, WindowListener, ActionListener, MenuSwitchMode, MenuNiveauDifficulte {
    private JPanel panneauHaut = new JPanel();
    private JPanel panneauJeux = new JPanel();
    private GridBagLayout layoutPanneauJeux = new GridBagLayout();
    private Segment affMines = new Segment(); //l'afficheur du nombre de mines
    private JButton boutonNouveau = new JButton();
    private Segment affTemps = new Segment(); //l'afficheur du temps Ã©coulÃ©
    private Border borderPanneaux;
    private JMenuBar menu = new JMenuBar();
    private JMenu partie = new JMenu("Partie");
    private JCheckBox pause = new JCheckBox("Pause");
    private JMenu help = new JMenu("?");
    private JMenuItem menuNouveau = new JMenuItem("Nouveau");
    private JMenuItem menuAffichageEnModeJavaFX = new JCheckBoxMenuItem("Affichage en mode JavaFX");
    JCheckBoxMenuItem menuDebutant = new JCheckBoxMenuItem("Débutant");
    JCheckBoxMenuItem menuIntermediaire = new JCheckBoxMenuItem("Intermédiaire");
    JCheckBoxMenuItem menuExpert = new JCheckBoxMenuItem("Expert");
    JCheckBoxMenuItem menuPerso = new JCheckBoxMenuItem("Personalisé");
    private JMenuItem apropos = new JMenuItem("A propos");
    private BoxLayout layoutPanneauHaut = new BoxLayout(panneauHaut,
            BoxLayout.LINE_AXIS);
    private Component box2; //Boxes utilisÃ©es dans le BoxLayout
    private Component box3;
    private Component box1;
    private Component box4;
    private Icon sourire, oups, cool; //images du bouton
    private Temps temps = new Temps(affTemps); //timer sur l'affichage Segment affTemps
    private model.Demineur demineur = new model.Demineur();

    //constructeur en fonction du nombre de cases, de mines et du type.
    //Le type permet de selectionner le bon mode dans le menu.
    //type == 1 -> DÃ©butant
    //type == 2 -> IntermÃ©diaire
    //type == 3 -> Expert
    //type == 4 -> PersonnalisÃ©
    public Demineur(int hauteur, int largeur, int mines, int type) {
        demineur.setHAUTEUR(hauteur);
        demineur.setLARGEUR(largeur);
        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
        demineur.setnMines(mines);
        demineur.setType(type);
        demineur.setJeux(new DeminCase[demineur.getHAUTEUR()][demineur.getLARGEUR()]);

        //Récupérer les gif dans le fichier .jar
        URL location;
        location = java.lang.ClassLoader.getSystemResource("Images/sourire.gif");
        sourire = new ImageIcon(location);
        //location = java.lang.ClassLoader.getSystemResource("Images/oups.gif");
        //oups = new ImageIcon(location);
        location = java.lang.ClassLoader.getSystemResource("Images/oups.gif");
        oups = new ImageIcon(location);
        location = java.lang.ClassLoader.getSystemResource("Images/cool.gif");
        cool = new ImageIcon(location);

        //création des cases
        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
            for (int j = 0; j < demineur.getLARGEUR(); j++) {
                demineur.getJeux()[i][j] = new DeminCase();
            }
        }

        //sélection du bon mode dans le JMenu
        selectionnerLeNiveauDeDifficulte(type);

        //initialisation
        nouveau();

        try {
            //Graphisme
            jbInit();
            this.setVisible(true);
            boutonNouveau.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectionnerLeNiveauDeDifficulte(int type){
        if (type == 1) menuDebutant.setSelected(true);
        if (type == 2) menuIntermediaire.setSelected(true);
        if (type == 3) menuExpert.setSelected(true);
        if (type == 4) menuPerso.setSelected(true);
    }

    //initialises le jeux
    public void nouveau() {
        temps.cancel(); //Timer Ã  0
        boutonNouveau.setIcon(sourire); //Icon par dÃ©faut du bouton
        demineur.setnDrapeau(0);  //rÃ©initialisation des principaux paramÃ¨tres
        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
        affMines.setValeur(demineur.getnMines());
        affTemps.setValeur(0);
        panneauJeux.setVisible(true); //peut Ãªtre Ã  false en raison de la pause
        pause.setSelected(false);

        //Génération des mines
        //dans la chaine, 1=mine 0=rien
        //on créer le bon nombre de mines puis on complète par des cases vides jusqu'à obtenir le nombre de cases total
        demineur.setMines("");
        for (int i = 0; i < demineur.getnMines(); i++) demineur.setMines(demineur.getMines() + "1");
        while (demineur.getMines().length() < demineur.getHAUTEUR() * demineur.getLARGEUR()) {
            int i = (int) (Math.random() * (demineur.getMines().length() + 1));
            demineur.setMines(demineur.getMines().substring(0, i) + "0" + demineur.getMines().substring(i));
        }

        //paramÃ¨tres des cases
        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
            for (int j = 0; j < demineur.getLARGEUR(); j++) {
                demineur.getJeux()[i][j].reset();
                demineur.getJeux()[i][j].removeMouseListener(this); //nÃ©cÃ©ssaire pour Ã©viter un bug lors de l'appel de nouveau() une 2Ã¨me fois
                demineur.getJeux()[i][j].addMouseListener(this); //pour les clics!!!
                if (demineur.getMines().charAt(i * demineur.getLARGEUR() + j) == '1') {
                    demineur.getJeux()[i][j].getDeminCase().setMine(true);
                }
            }
        }
        repaint();

        //comptage pour chaque case du nombre de mines autour
        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
            for (int j = 0; j < demineur.getLARGEUR(); j++) {
                if (!demineur.getJeux()[i][j].getDeminCase().isMine()) {
                    int n = 0;
                    //try car les cases n'existent pas forcÃ©ment
                    try {
                        if (demineur.getJeux()[i - 1][j - 1].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i - 1][j].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i - 1][j + 1].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i][j - 1].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i][j + 1].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i + 1][j - 1].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i + 1][j].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (demineur.getJeux()[i + 1][j + 1].getDeminCase().isMine()) n++;
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    }
                    demineur.getJeux()[i][j].getDeminCase().setChiffre(n); //on indique Ã  la case le nombre de mines
                }
            }
        }
    }

    @Override
    public void jbInit() throws Exception {
        borderPanneaux = BorderFactory.createEtchedBorder(Color.white,
                new Color(156, 156, 156));
        box2 = Box.createGlue(); //utilisÃ©s dans le jPanel1 pour la disposition
        box3 = Box.createGlue();
        box1 = Box.createHorizontalStrut(8);
        box1.setSize(5, 50);
        box4 = Box.createHorizontalStrut(8);
        box4.setSize(5, 50);

        this.addWindowListener(this);

        int tailleX = demineur.getLARGEUR() * 16 + 20; //20 pour la marge
        int tailleY = demineur.getHAUTEUR() * 16 + 20;
        if (tailleX < 160) tailleX = 150; //taille minimum en largeur

        this.setSize(tailleX + 6, tailleY + 50 + 23 + 25); //6=largeur du cadre de la fenetre, 25=hauteur de la barre windows
        this.setTitle("Démineur");
        this.setResizable(false);

        //MENU
        partie.setMnemonic('P');
        menuNouveau.addActionListener(this);
        menuNouveau.setMnemonic('N');
        menuAffichageEnModeJavaFX.addActionListener(this);
        menuAffichageEnModeJavaFX.setMnemonic('J');
        menuDebutant.addActionListener(this);
        menuDebutant.setMnemonic('D');
        menuIntermediaire.addActionListener(this);
        menuIntermediaire.setMnemonic('I');
        menuExpert.addActionListener(this);
        menuExpert.setMnemonic('E');
        menuPerso.addActionListener(this);
        menuPerso.setMnemonic('P');
        partie.add(menuNouveau);
        partie.add(menuAffichageEnModeJavaFX);
        partie.add(new JSeparator());
        partie.add(menuDebutant);
        partie.add(menuIntermediaire);
        partie.add(menuExpert);
        partie.add(menuPerso);
        menu.setBorderPainted(false);
        menu.add(partie);
        pause.setMnemonic('a');
        pause.setOpaque(false);
        pause.setFocusPainted(false);
        pause.addActionListener(this);
        menu.add(pause);
        help.setMnemonic('?');
        apropos.addActionListener(this);
        apropos.setMnemonic('A');
        help.add(apropos);
        menu.add(help);
        this.setJMenuBar(menu);

        affMines.setMaximumSize(new Dimension(49, 27));
        affTemps.setMaximumSize(new Dimension(49, 27));
        boutonNouveau.setMaximumSize(new Dimension(25, 25));
        boutonNouveau.setMinimumSize(new Dimension(25, 25));
        panneauHaut.setBorder(borderPanneaux);
        panneauHaut.setPreferredSize(new Dimension(450, 50));
        panneauHaut.setLayout(layoutPanneauHaut);
        panneauJeux.setBorder(borderPanneaux);
        panneauJeux.setPreferredSize(new Dimension(tailleX, tailleY));
        panneauJeux.setLayout(layoutPanneauJeux);
        affMines.setValeur(demineur.getnMines());
        affTemps.setValeur(0);
        boutonNouveau.setPreferredSize(new Dimension(25, 25));
        boutonNouveau.setFocusPainted(false);
        boutonNouveau.setIcon(sourire);
        boutonNouveau.setMargin(new Insets(0, 0, 0, 0));
        boutonNouveau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boutonNouveau_actionPerformed(e);
            }
        });
        panneauHaut.add(box1, null);
        panneauHaut.add(affMines, null);
        panneauHaut.add(box2, null);
        panneauHaut.add(boutonNouveau, null);
        panneauHaut.add(box3, null);
        panneauHaut.add(affTemps, null);
        panneauHaut.add(box4, null);
        this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
        this.getContentPane().add(panneauJeux, BorderLayout.CENTER);

        //gr contient les graphismes de toutes les cases
        //on le crÃ©Ã© maintenant pour utiliser son GraphicsConfiguration()
        Graphisme gr = new Graphisme(this.getGraphicsConfiguration());

        //placement des cases dans la fenÃªtre
        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
            for (int j = 0; j < demineur.getLARGEUR(); j++) {
                demineur.getJeux()[i][j].getDeminCase().setGr(gr); //on indique les graphismes Ã  la cases
                panneauJeux.add(demineur.getJeux()[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0
                        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
        }
    }

    //Retourne le reperage de la case sous le clic de la souris si elle existe
    //(-1,-1) sinon
    public int[] caseClic(int x, int y) {
        int OFFSETX = (int) demineur.getJeux()[0][0].getX() + 3; //dÃ©calage par rapport au coin en haut Ã  gauche de la fenetre
        int OFFSETY = (int) demineur.getJeux()[0][0].getY() + 22;
        int posx = -1, posy = -1;
        if (x - OFFSETX >= 0) posx = (x - OFFSETX) / 16;
        if (posx >= demineur.getLARGEUR()) posx = -1;
        if (y - OFFSETY >= 0 && posx != -1) posy = (y - OFFSETY) / 16;
        if (posy >= demineur.getHAUTEUR()) posy = -1;
        if (posy == -1) posx = -1;
        int[] retour = {
                posx, posy};
        return retour;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        try {
            int x = (int) ((JPanel) e.getSource()).getLocation().getX() + e.getX() +
                    3; //retourne une exception si on est pas au dessus d'un panneau
            int y = (int) ((JPanel) e.getSource()).getLocation().getY() + e.getY() +
                    22;
            int[] coord = caseClic(x, y); //coordonnÃ©es de la case enfoncÃ©e enregistrÃ©es dans coord
            //boutonNouveau.setIcon(oups); //bouton

            //si clic droit au dessus d'une case
            if (e.getButton() == e.BUTTON3 && coord[1] != -1 && coord[0] != -1) {
                int temp = demineur.getJeux()[coord[1]][coord[0]].getDeminCase().getEtat();
                switch (temp) {
                    case 0: //affichage d'un drapeau
                        demineur.getJeux()[coord[1]][coord[0]].getDeminCase().setEtat(2);
                        demineur.setnDrapeau(demineur.getnDrapeau() + 1);
                        affMines.setValeur(demineur.getnMines() - demineur.getnDrapeau());
                        break;
                    case 2: //affichage d'un ?
                        demineur.getJeux()[coord[1]][coord[0]].getDeminCase().setEtat(3);
                        demineur.setnDrapeau(demineur.getnDrapeau() + 1);
                        affMines.setValeur(demineur.getnMines() - demineur.getnDrapeau());
                        break;
                    case 3: //RAZ
                        demineur.getJeux()[coord[1]][coord[0]].getDeminCase().setEtat(0);
                        break;
                }
                demineur.getJeux()[coord[1]][coord[0]].repaint();
            }

            //si clic gauche, on selectionne les cases autour

            y = coord[1];
            x = coord[0];
            if (e.getButton() == e.BUTTON1 && x != -1 && y != -1 &&
                    demineur.getJeux()[y][x].getDeminCase().getEtat() == 1 && demineur.getJeux()[y][x].getDeminCase().getChiffre() != 0) {
                //on enregistre les coordonnÃ©es des cases sÃ©lÃ©ctionnÃ©es
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 2; j++) {
                        demineur.getCasesSelectionnees()[i][j] = -1; //effacement de la mÃ©moire
                    }
                }
                //essai sur les huit cases autour
                try {
                    if (demineur.getJeux()[y - 1][x - 1].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y - 1][x - 1].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[0][0] = y - 1;
                        demineur.getCasesSelectionnees()[0][1] = x - 1;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y - 1][x].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y - 1][x].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[1][0] = y - 1;
                        demineur.getCasesSelectionnees()[1][1] = x;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y - 1][x + 1].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y - 1][x + 1].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[2][0] = y - 1;
                        demineur.getCasesSelectionnees()[2][1] = x + 1;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y][x - 1].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y][x - 1].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[3][0] = y;
                        demineur.getCasesSelectionnees()[3][1] = x - 1;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y][x + 1].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y][x + 1].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[4][0] = y;
                        demineur.getCasesSelectionnees()[4][1] = x + 1;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y + 1][x - 1].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y + 1][x - 1].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[5][0] = y + 1;
                        demineur.getCasesSelectionnees()[5][1] = x - 1;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y + 1][x].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y + 1][x].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[6][0] = y + 1;
                        demineur.getCasesSelectionnees()[6][1] = x;
                    }
                } catch (Exception exc) {
                }
                try {
                    if (demineur.getJeux()[y + 1][x + 1].getDeminCase().getEtat() == 0) {
                        demineur.getJeux()[y + 1][x + 1].getDeminCase().setSelected(true);
                        demineur.getCasesSelectionnees()[7][0] = y + 1;
                        demineur.getCasesSelectionnees()[7][1] = x + 1;
                    }
                } catch (Exception exc) {
                }
            }
        } catch (ClassCastException ex) {
        } //si clic n'import oÃ¹
    }

    public void mouseReleased(MouseEvent e) {

        //Si c'est le premier clic du jeux, on dÃ©marre le timer
        if (demineur.getnCases() == demineur.getHAUTEUR() * demineur.getLARGEUR() && e.getButton() == e.BUTTON1) {
            temps.cancel();
            temps = new Temps(affTemps);
            temps.start();
        }

        try {
            int x = (int) ((JPanel) e.getSource()).getLocation().getX() + e.getX() +
                    3; //gÃ©nÃ¨re des exceptions Ã  cause du cast
            int y = (int) ((JPanel) e.getSource()).getLocation().getY() + e.getY() +
                    22;
            int[] coord = caseClic(x, y); //on rÃ©cupÃ¨re les coordonnÃ©es
            boutonNouveau.setIcon(sourire); //remise du bouton sur l'icone sourire
            if (coord[0] != -1 && coord[1] != -1) { //si on est au dessus d'une case
                y = coord[1];
                x = coord[0];
                if (e.getButton() == e.BUTTON1) { //si clic gauche, on dÃ©couvre
                    decouvre(y, x);
                    repaint();
                }
                demineur.getJeux()[y][x].getDeminCase().setSelected(false); //on dÃ©selctionne la case ainsi que celle de la mÃ©moire casesSelectionnees
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[0][0]][demineur.getCasesSelectionnees()[0][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[1][0]][demineur.getCasesSelectionnees()[1][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[2][0]][demineur.getCasesSelectionnees()[2][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[3][0]][demineur.getCasesSelectionnees()[3][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[4][0]][demineur.getCasesSelectionnees()[4][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[5][0]][demineur.getCasesSelectionnees()[5][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[6][0]][demineur.getCasesSelectionnees()[6][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
                try {
                    demineur.getJeux()[demineur.getCasesSelectionnees()[7][0]][demineur.getCasesSelectionnees()[7][1]].getDeminCase().setSelected(false);
                } catch (Exception exc) {
                }
            }
        } catch (ClassCastException ex) {
        } //Si le clic n'est pas au dessus d'un panneau
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    //dÃ©clenchÃ©e par un appuie sur le bouton
    void boutonNouveau_actionPerformed(ActionEvent e) {
        if (!pause.isSelected()) nouveau();
    }

    //mÃ©thode pour dÃ©couvrir les cases
    public void decouvre(int y, int x) {
        //Si la case est normale ou avec un ?
        if ((demineur.getJeux()[y][x].getDeminCase().getEtat() == 0 || demineur.getJeux()[y][x].getDeminCase().getEtat() == 3) &&
                !demineur.getJeux()[y][x].getDeminCase().isMine()) {
            demineur.setnCases(demineur.getnCases() - 1); //nombre de cases non dÃ©couvertes
            demineur.getJeux()[y][x].getDeminCase().setEtat(1); //on indique que la case est dÃ©couverte
            if (demineur.getJeux()[y][x].getDeminCase().getChiffre() == 0) { // Si le nombre de mines autour est nul, on dÃ©couvre les cases autour
                decouvrirPartiel1(x - 1, y - 1);
                decouvrirPartiel1(x - 1, y);
                decouvrirPartiel1(x - 1, y + 1);
                decouvrirPartiel1(x, y - 1);
                decouvrirPartiel1(x, y + 1);
                decouvrirPartiel1(x + 1, y - 1);
                decouvrirPartiel1(x + 1, y);
                decouvrirPartiel1(x + 1, y + 1);
            }
        }

        //Si on est au dessus d'un chiffre
        else if (demineur.getJeux()[y][x].getDeminCase().getEtat() == 1 && demineur.getJeux()[y][x].getDeminCase().getChiffre() != 0) {
            int n = 0; //on compte le nombre de drapeaux placÃ©s
            if (decouvrirPartiel2(x - 1, y - 1)) n++;
            if (decouvrirPartiel2(x - 1, y)) n++;
            if (decouvrirPartiel2(x - 1, y + 1)) n++;
            if (decouvrirPartiel2(x, y - 1)) n++;
            if (decouvrirPartiel2(x, y + 1)) n++;
            if (decouvrirPartiel2(x + 1, y - 1)) n++;
            if (decouvrirPartiel2(x + 1, y)) n++;
            if (decouvrirPartiel2(x + 1, y + 1)) n++;

            if (n == demineur.getJeux()[y][x].getDeminCase().getChiffre()) { //si il y en a autant que le nombre de mines autour, on dÃ©couvre les 8 cases autour par un appel rÃ©cursif de decouvre(int, int)
                if (decouvrirPartiel3(x - 1, y - 1)) decouvre(y - 1, x - 1);
                if (decouvrirPartiel3(x - 1, y)) decouvre(y, x - 1);
                if (decouvrirPartiel3(x - 1, y + 1)) decouvre(y + 1, x - 1);
                if (decouvrirPartiel3(x, y - 1)) decouvre(y - 1, x);
                if (decouvrirPartiel3(x, y + 1)) decouvre(y + 1, x);
                if (decouvrirPartiel3(x + 1, y - 1)) decouvre(y - 1, x + 1);
                if (decouvrirPartiel3(x + 1, y)) decouvre(y, x + 1);
                if (decouvrirPartiel3(x + 1, y + 1)) decouvre(y + 1, x + 1);
            }
        }

        //Si on clique sur une mine
        else if ((demineur.getJeux()[y][x].getDeminCase().getEtat() == 0 || demineur.getJeux()[y][x].getDeminCase().getEtat() == 3) &&
                demineur.getJeux()[y][x].getDeminCase().isMine()) {
            temps.cancel(); //fin du timer
            demineur.getJeux()[y][x].getDeminCase().setEtat(4); //boum
            boutonNouveau.setIcon(oups);
            for (int i = 0; i < demineur.getHAUTEUR(); i++) {
                for (int j = 0; j < demineur.getLARGEUR(); j++) {
                    demineur.getJeux()[i][j].removeMouseListener(this); //on bloque les cases
                    demineur.getJeux()[i][j].getDeminCase().setBlocked(true);
                    if (!(y == i && x == j) && demineur.getMines().charAt(i * demineur.getLARGEUR() + j) == '1' &&
                            demineur.getJeux()[i][j].getDeminCase().getEtat() != 2)

                        //si il ya une mine, (recherche par rapport Ã  la chaÃ®ne mines
                        demineur.getJeux()[i][j].getDeminCase().setEtat(5); //on l' affiche
                }
            }
            //on affiche les erreurs
            for (int i = 0; i < demineur.getHAUTEUR(); i++) {
                for (int j = 0; j < demineur.getLARGEUR(); j++) {
                    if (demineur.getJeux()[i][j].getDeminCase().getEtat() == 2 && !demineur.getJeux()[i][j].getDeminCase().isMine())
                        demineur.getJeux()[i][j].
                                getDeminCase().setEtat(6);
                }
            }
        }
        //Si on gagne, c'est Ã  dire le nombre de cases restantes est Ã©gal au nombre de mines restantes
        if (demineur.getnCases() == demineur.getnMines() && !demineur.getJeux()[0][0].getDeminCase().isBlocked()) {
            temps.cancel(); //fin du timer
            affMines.setValeur(0);
            boutonNouveau.setIcon(cool);
            for (int i = 0; i < demineur.getHAUTEUR(); i++) {
                for (int j = 0; j < demineur.getLARGEUR(); j++) {
                    demineur.getJeux()[i][j].removeMouseListener(this); //on bloque les cases
                    demineur.getJeux()[i][j].getDeminCase().setBlocked(true);
                    if (demineur.getJeux()[i][j].getDeminCase().isMine())
                        demineur.getJeux()[i][j].getDeminCase().setEtat(2); //om met des drapeaux partout!!
                }
            }
        }
    }

    //si la case existe, on la dÃ©couvre et si necessaire, on appelle le dÃ©couvrement des cases autour
    public void decouvrirPartiel1(int x, int y) {
        if (x >= 0 && y >= 0 && x < demineur.getLARGEUR() && y < demineur.getHAUTEUR()) {
            if (demineur.getJeux()[y][x].getDeminCase().getEtat() == 0 && demineur.getJeux()[y][x].getDeminCase().getChiffre() != 0) {
                demineur.getJeux()[y][x].getDeminCase().setEtat(1);
                demineur.setnCases(demineur.getnCases() - 1);
            }
            if (demineur.getJeux()[y][x].getDeminCase().getEtat() == 0 && demineur.getJeux()[y][x].getDeminCase().getChiffre() == 0)
                decouvre(y, x); //Si le nombre de mines autour est nul, on dÃ©couvre les cases autour
        }
    }

    //vÃ©rifie si la case existe et si elle porte un drapeau
    public boolean decouvrirPartiel2(int x, int y) {
        if (x >= 0 && y >= 0 && x < demineur.getLARGEUR() && y < demineur.getHAUTEUR()) {
            if (demineur.getJeux()[y][x].getDeminCase().getEtat() == 2)
                return true;
        }
        return false;
    }

    //vÃ©rifie si la case existe et si elle n'est pas dÃ©couverte ou si elle porte un '?'
    public boolean decouvrirPartiel3(int x, int y) {
        if (x >= 0 && y >= 0 && x < demineur.getLARGEUR() && y < demineur.getHAUTEUR()) {
            if (demineur.getJeux()[y][x].getDeminCase().getEtat() == 0 || demineur.getJeux()[y][x].getDeminCase().getEtat() == 3)
                return true;
        }
        return false;
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        temps.stop(); //stop le timer avant de quitter
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
        try {
            temps.suspend();
        } //pause du timer si il existe!!
        catch (Exception esc) {
        }
    }

    public void windowDeiconified(WindowEvent e) {
        try {
            temps.resume();
        } //reprise du timer (si il existe!!)
        catch (Exception esc) {
        }
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void demarrerJavaFX() throws Exception {
       // Platform.startup(() -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    DemineurJavaFXController demineurJavaFXController = new DemineurJavaFXController();
                    try {
                        demineurJavaFXController.start(new Stage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        //});
    }

    //Ã©venements liÃ©s au menu
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuNouveau) nouveau();
        else if (e.getSource() == menuAffichageEnModeJavaFX) {
            menuAffichageEnModeJavaFX.setSelected(true);
            this.dispose(); // on détruit la fenetre
            System.gc();
            //Initialement en mode expert
            try {
                demarrerJavaFX();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            //ici
        } else if (e.getSource() == menuDebutant && demineur.getType() != 1) {
            if (demineur.getType() == 1) menuDebutant.setSelected(true);
            Demineur demineur = new Demineur(8, 8, 10, 1); //et on en refait une
        } else if (e.getSource() == menuDebutant && !menuDebutant.isSelected())
            menuDebutant.setSelected(true);
        else if (e.getSource() == menuIntermediaire && demineur.getType()  != 2) {
            this.dispose(); // on dÃ©truit la fenetre
            System.gc();
            if (demineur.getType()  == 2) menuIntermediaire.setSelected(true);
            Demineur demineur = new Demineur(16, 16, 40, 2);
        } else if (e.getSource() == menuIntermediaire &&
                !menuIntermediaire.isSelected()) menuIntermediaire.setSelected(true);
        else if (e.getSource() == menuExpert && demineur.getType()  != 3) {
            this.dispose(); // on dÃ©truit la fenetre
            System.gc();
            if (demineur.getType()  == 3) menuExpert.setSelected(true);
            Demineur demineur = new Demineur(16, 30, 99, 3);
        } else if (e.getSource() == menuExpert && demineur.getType()  != 4) menuExpert.setSelected(true);
        else if (e.getSource() == menuPerso) {
            //un peu particulier : tout est gÃ©rÃ© par la fenetre de personalisation
            if (demineur.getType()  == 4) menuPerso.setSelected(true);
            else menuPerso.setSelected(false);
            Personaliser perso = new Personaliser(this, "Paramètres", true, demineur.getHAUTEUR(),
                    demineur.getLARGEUR(), demineur.getnMines());
            perso.setLocation((int) this.getLocation().getX() + 20,
                    (int) this.getLocation().getY() + 20);
            perso.setVisible(true);
        } else if (e.getSource() == pause) {
            if (pause.isSelected()) {
                panneauJeux.setVisible(false);
                temps.suspend();
            } else {
                panneauJeux.setVisible(true);
                temps.resume();
            }
        } else if (e.getSource() == apropos) {
            Apropos app = new Apropos(this, "Démineur", true);
            app.setLocation((int) this.getLocation().getX() + 20,
                    (int) this.getLocation().getY() + 20);
            app.setVisible(true);
        }
    }


}
