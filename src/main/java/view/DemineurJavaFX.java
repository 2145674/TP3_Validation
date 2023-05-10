package view;

import javafx.application.Platform;
import javafx.application.*;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class DemineurJavaFX implements MenuSwitchMode, MenuNiveauDifficulte {

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


    public Scene creationInterfaceFX() {
        partie.getItems().addAll(menuNouveau, affichageEnModeSwing, menuDebutant, menuIntermediaire, menuExpert, menuPerso);
        menu.getMenus().add(partie);
        menu.getMenus().add(apropos);
        menu.getMenus().add(help);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        VBox root = new VBox();
        root.getChildren().add(menu);
        root.getChildren().add(gridPane);



        for (int ligne = 0; ligne < 11; ligne++) {
            for (int colonne = 0; colonne < 15; colonne++) {
                javafx.scene.control.Button caseButton = new Button();
                caseButton.setPrefSize(25, 25);
                gridPane.add(caseButton, colonne, ligne);
            }
        }
        Scene scene = new Scene(root, 400, 300);

        return scene;
    }

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
