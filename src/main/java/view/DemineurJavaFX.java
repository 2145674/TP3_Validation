package view;

import javafx.application.Platform;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import model.MenuNiveauDifficulte;
import model.MenuSwitchMode;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class DemineurJavaFX implements MenuSwitchMode, MenuNiveauDifficulte {

    JFrame frame = new JFrame();

//    private Button button = new Button();
//    private ImageView sourire = new ImageView(new Image("Images/sourire.gif"));
 //   private ImageView oups = new ImageView(new Image("Images/oups.gif")) ;
  //  private ImageView cool = new ImageView(new Image("Images/cool.gif")) ;

//    private JPanel panneauHaut = new JPanel();
//    private JPanel panneauJeux = new JPanel();
//    private GridBagLayout layoutPanneauJeux = new GridBagLayout();
//    private Segment affMines = new Segment(); //l'afficheur du nombre de mines
//    private Button boutonNouveau = new Button();
//    private Segment affTemps = new Segment(); //l'afficheur du temps Ã©coulÃ©
//    private Border borderPanneaux;
    private MenuBar menu = new MenuBar();
    private Menu partie = new Menu("Partie");
//    private CheckBox pause = new CheckBox("Pause");
//    private Menu help = new Menu("?");
    private MenuItem menuNouveau = new MenuItem("Nouveau");
    private MenuItem menuAffichageEnModeJavaFX = new CheckMenuItem("Affichage en mode JavaFX");
    CheckMenuItem menuDebutant = new CheckMenuItem("Débutant");
    CheckMenuItem menuIntermediaire = new CheckMenuItem("Intermédiaire");
    CheckMenuItem menuExpert = new CheckMenuItem("Expert");
    CheckMenuItem menuPerso = new CheckMenuItem("Personalisé");
//    private MenuItem apropos = new MenuItem("A propos");
//    private BoxLayout layoutPanneauHaut = new BoxLayout(panneauHaut,
//            BoxLayout.LINE_AXIS);
//    private Node box2; //Boxes utilisÃ©es dans le BoxLayout
//    private Node box3;
//    private Node box1;
//    private Node box4;
//    private Temps temps = new Temps(affTemps); //timer sur l'affichage

    private model.Demineur demineur = new model.Demineur();

    public DemineurJavaFX(int hauteur, int largeur, int mines, int type) {
        demineur.setHAUTEUR(hauteur);
        demineur.setLARGEUR(largeur);
        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
        demineur.setnMines(mines);
        demineur.setType(type);
        demineur.setJeux(new DeminCase[demineur.getHAUTEUR()][demineur.getLARGEUR()]);

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

      /*  try {
            //Graphisme
            jbInit();
            // this.setVisible(true);  revenir
            boutonNouveau.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public DemineurJavaFX() {
    }

    //initialises le jeux
    public void nouveau() {
//        temps.cancel(); //Timer Ã  0
////        boutonNouveau.setGraphic(sourire); //Icon par dÃ©faut du bouton
//        demineur.setnDrapeau(0);  //rÃ©initialisation des principaux paramÃ¨tres
//        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
//        affMines.setValeur(demineur.getnMines());
//        affTemps.setValeur(0);
//        panneauJeux.setVisible(true); //peut Ãªtre Ã  false en raison de la pause
//        pause.setSelected(false);

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
//                demineur.getJeux()[i][j].reset();
//                demineur.getJeux()[i][j].removeMouseListener(this); //nÃ©cÃ©ssaire pour Ã©viter un bug lors de l'appel de nouveau() une 2Ã¨me fois
//                demineur.getJeux()[i][j].addMouseListener( this); //pour les clics!!!
//                if (demineur.getMines().charAt(i * demineur.getLARGEUR() + j) == '1') {
//                    demineur.getJeux()[i][j].getDeminCase().setMine(true);
//                }
            }
        }
        //repaint();

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
    public void selectionnerLeNiveauDeDifficulte(int type) {
//        if (type == 1) menuDebutant.setSelected(true);
//        if (type == 2) menuIntermediaire.setSelected(true);
//        if (type == 3) menuExpert.setSelected(true);
//        if (type == 4) menuPerso.setSelected(true);
    }

    @Override
    public void jbInit() throws Exception {
        int tailleX = demineur.getLARGEUR() * 16 + 20; // pour la marge
        int tailleY = demineur.getHAUTEUR() * 16 + 20;
        if (tailleX < 160) tailleX = 150; //taille minimum en largeur

        frame.setSize(tailleX, tailleY);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
