package view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.shape.Line;
import model.MenuNiveauDifficulte;
import model.MenuSwitchMode;

import javax.swing.*;
import java.io.IOException;

public class DemineurJavaFX implements MenuSwitchMode, MenuNiveauDifficulte {

    JFrame frame = new JFrame();

    private model.Demineur demineur = new model.Demineur();

    public DemineurJavaFX(int hauteur, int largeur, int mines, int type) {
        demineur.setHAUTEUR(hauteur);
        demineur.setLARGEUR(largeur);
        demineur.setnCases(demineur.getHAUTEUR() * demineur.getLARGEUR());
        demineur.setnMines(mines);
        demineur.setType(type);
        demineur.setJeux(new DeminCase[demineur.getHAUTEUR()][demineur.getLARGEUR()]);
    }

    public DemineurJavaFX() {
    }

    public void creationDuModeJavaFX() {

        frame = new JFrame("Démineur en mode JavaFX");
        final JFXPanel jfxPanel = new JFXPanel();
        Line line = new Line(100, 10, 10, 110);
        frame.add(jfxPanel);

        try {
            jbInit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    initFX(jfxPanel);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private void initFX(JFXPanel jfxPanel) throws IOException {
        Scene scene = createScene();
        jfxPanel.setScene(scene);
    }

    private static Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DemineurJavaFX.class.getResource("/view/Expert.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        return (scene);
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

    @Override
    public void selectionnerLeNiveauDeDifficulte(int type) {

    }

}
