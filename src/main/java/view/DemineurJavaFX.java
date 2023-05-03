package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DemineurJavaFX extends Application {

    public DemineurJavaFX() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Demineur en JavaFX");

        FXMLLoader fxmlLoaderExpert = new FXMLLoader(getClass().getResource("/view/Expert.fxml"));
        Parent rootExpert = fxmlLoaderExpert.load();
        Scene sceneExpert = new Scene(rootExpert,600,400);

        primaryStage.setScene(sceneExpert);
        primaryStage.show();


    }
}
