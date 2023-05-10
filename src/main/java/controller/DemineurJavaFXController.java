package controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MenuNiveauDifficulte;
import model.MenuSwitchMode;
import view.Apropos;
import view.DemineurJavaFX;
import view.Segment;
import view.Temps;

import java.awt.*;

import static javafx.application.Application.launch;

public class DemineurJavaFXController extends Application {

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
    private MenuItem affichageEnModeSwing = new CheckMenuItem("Affichage en mode Swing");
    CheckMenuItem menuDebutant = new CheckMenuItem("Débutant");
    CheckMenuItem menuIntermediaire = new CheckMenuItem("Intermédiaire");
    CheckMenuItem menuExpert = new CheckMenuItem("Expert");
    CheckMenuItem menuPerso = new CheckMenuItem("Personalisé");
    private Menu apropos = new Menu("A propos");
//    private BoxLayout layoutPanneauHaut = new BoxLayout(panneauHaut,
//            BoxLayout.LINE_AXIS);
//    private Node box2; //Boxes utilisÃ©es dans le BoxLayout
//    private Node box3;
//    private Node box1;
//    private Node box4;
    private Temps temps = new Temps(affTemps); //timer sur l'affichage

    private model.Demineur demineur = new model.Demineur();


    Apropos app = new Apropos(new Frame(), "Démineur", true);

    public DemineurJavaFXController() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Démineur en mode JavaFX");
        DemineurJavaFX demineurJavaFX = new DemineurJavaFX();
        Scene scene = demineurJavaFX.creationInterfaceFX();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }




}
