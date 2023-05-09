package controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.MenuNiveauDifficulte;
import model.MenuSwitchMode;
import view.DeminCase;
import view.DemineurJavaFX;
import javafx.scene.image.ImageView;
import view.Segment;
import view.Temps;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import static javafx.application.Application.launch;

public class DemineurJavaFXController extends Application implements MenuSwitchMode, MenuNiveauDifficulte {

//    private JFrame frame = new JFrame();
//    private Button button = new Button();
//    private ImageView sourire = new ImageView(new Image("Images/sourire.gif"));
//    private ImageView oups = new ImageView(new Image("Images/oups.gif")) ;
//    private ImageView cool = new ImageView(new Image("Images/cool.gif")) ;

//    private JPanel panneauHaut = new JPanel();
//    private JPanel panneauJeux = new JPanel();
//    private GridBagLayout layoutPanneauJeux = new GridBagLayout();
//    private Segment affMines = new Segment(); //l'afficheur du nombre de mines
//    private Button boutonNouveau = new Button();
    private Segment affTemps = new Segment(); //l'afficheur du temps Ã©coulÃ©
//    private Border borderPanneaux;
    private MenuBar menu = new MenuBar();
    private Menu partie = new Menu("Partie");
    private CheckBox pause = new CheckBox("Pause");
    private Menu help = new Menu("?");
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


    public DemineurJavaFXController() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Démineur en mode JavaFX");
        partie.getItems().addAll(menuNouveau, menuAffichageEnModeJavaFX, menuDebutant, menuIntermediaire, menuExpert, menuPerso);
        menu.getMenus().add(partie);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        VBox root = new VBox();
        root.getChildren().add(menu);
        root.getChildren().add(gridPane);

        for (int ligne = 0; ligne < 10; ligne++) {
            for (int colonne = 0; colonne < 8; colonne++) {
                Button caseButton = new Button();
                caseButton.setPrefSize(25, 25);
                gridPane.add(caseButton, colonne, ligne);
            }
        }
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

//        frame = new JFrame();
//        final JFXPanel jfxPanel = new JFXPanel();
//        Line line = new Line(100, 10, 10, 110);
//        frame.add(jfxPanel);
//
//        try {
//            jbInit();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    initFX(jfxPanel);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }


    public DemineurJavaFXController(int hauteur, int largeur, int mines, int type) {
        demineur.setHAUTEUR(hauteur);
        demineur.setLARGEUR(largeur);
        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
        demineur.setnMines(mines);
        demineur.setType(type);
        demineur.setJeux(new DeminCase[demineur.getHAUTEUR()][demineur.getLARGEUR()]);

//        //création des cases
//        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
//            for (int j = 0; j < demineur.getLARGEUR(); j++) {
//                demineur.getJeux()[i][j] = new DeminCase();
//            }
//        }
//
//        //sélection du bon mode dans le JMenu
//        selectionnerLeNiveauDeDifficulte(type);
//
//        //initialisation
//        nouveau();
//
//        try {
//            //Graphisme
//            jbInit();
//           // this.setVisible(true);  revenir
//            boutonNouveau.requestFocus();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    //initialises le jeux
//    public void nouveau() {
//        temps.cancel(); //Timer Ã  0
//        boutonNouveau.setGraphic(sourire); //Icon par dÃ©faut du bouton
//        demineur.setnDrapeau(0);  //rÃ©initialisation des principaux paramÃ¨tres
//        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
//        affMines.setValeur(demineur.getnMines());
//        affTemps.setValeur(0);
//        panneauJeux.setVisible(true); //peut Ãªtre Ã  false en raison de la pause
//        pause.setSelected(false);
//
//        //Génération des mines
//        //dans la chaine, 1=mine 0=rien
//        //on créer le bon nombre de mines puis on complète par des cases vides jusqu'à obtenir le nombre de cases total
//        demineur.setMines("");
//        for (int i = 0; i < demineur.getnMines(); i++) demineur.setMines(demineur.getMines() + "1");
//        while (demineur.getMines().length() < demineur.getHAUTEUR() * demineur.getLARGEUR()) {
//            int i = (int) (Math.random() * (demineur.getMines().length() + 1));
//            demineur.setMines(demineur.getMines().substring(0, i) + "0" + demineur.getMines().substring(i));
//        }
//
//        //paramÃ¨tres des cases
//        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
//            for (int j = 0; j < demineur.getLARGEUR(); j++) {
//                demineur.getJeux()[i][j].reset();
//                demineur.getJeux()[i][j].removeMouseListener((MouseListener) this); //nÃ©cÃ©ssaire pour Ã©viter un bug lors de l'appel de nouveau() une 2Ã¨me fois
//                demineur.getJeux()[i][j].addMouseListener((MouseListener) this); //pour les clics!!!
//                if (demineur.getMines().charAt(i * demineur.getLARGEUR() + j) == '1') {
//                    demineur.getJeux()[i][j].getDeminCase().setMine(true);
//                }
//            }
//        }
//        //repaint();
//
//        //comptage pour chaque case du nombre de mines autour
//        for (int i = 0; i < demineur.getHAUTEUR(); i++) {
//            for (int j = 0; j < demineur.getLARGEUR(); j++) {
//                if (!demineur.getJeux()[i][j].getDeminCase().isMine()) {
//                    int n = 0;
//                    //try car les cases n'existent pas forcÃ©ment
//                    try {
//                        if (demineur.getJeux()[i - 1][j - 1].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i - 1][j].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i - 1][j + 1].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i][j - 1].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i][j + 1].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i + 1][j - 1].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i + 1][j].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    try {
//                        if (demineur.getJeux()[i + 1][j + 1].getDeminCase().isMine()) n++;
//                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
//                    }
//                    demineur.getJeux()[i][j].getDeminCase().setChiffre(n); //on indique Ã  la case le nombre de mines
//                }
//            }
//        }
//    }
//
//    public void creationDuModeJavaFX() {
//
//        frame = new JFrame("Démineur en mode JavaFX");
//        final JFXPanel jfxPanel = new JFXPanel();
//        Line line = new Line(100, 10, 10, 110);
//        frame.add(jfxPanel);
//
//        try {
//            jbInit();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    initFX(jfxPanel);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
//
//    private void initFX(JFXPanel jfxPanel) throws IOException {
//        Scene scene = createScene();
//        jfxPanel.setScene(scene);
//    }
//
//    private static Scene createScene() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(DemineurJavaFX.class.getResource("/view/Expert.fxml"));
//        Parent root = fxmlLoader.load();
//        Scene scene = new Scene(root, 600, 400);
//        return (scene);
//    }
//
    @Override
    public void jbInit() throws Exception {
//        int tailleX = demineur.getLARGEUR() * 16 + 20; // pour la marge
//        int tailleY = demineur.getHAUTEUR() * 16 + 20;
//        if (tailleX < 160) tailleX = 150; //taille minimum en largeur
//
//        frame.setSize(tailleX, tailleY);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
    }
//
//
//    @Override
    public void selectionnerLeNiveauDeDifficulte(int type) {
//        if (type == 1) menuDebutant.setSelected(true);
//        if (type == 2) menuIntermediaire.setSelected(true);
//        if (type == 3) menuExpert.setSelected(true);
//        if (type == 4) menuPerso.setSelected(true);
    }

}
