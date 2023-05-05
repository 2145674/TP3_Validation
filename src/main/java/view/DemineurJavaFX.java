package view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;

public class DemineurJavaFX {

    public void creationDuModeJavaFX(){

        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        Parent root  =  fxmlLoader.load();
        Scene  scene  =  new  Scene(root);


        return (scene);
    }


}
