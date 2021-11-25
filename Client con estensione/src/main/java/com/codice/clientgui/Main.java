package com.codice.clientgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Classe Main
 */

public class Main extends Application {
    /**
     * Metodo che fa partire l'applicazione javafx
     */

    public void start(Stage stage) throws IOException {

        stage.setTitle("DataMiner");
        Parent root = FXMLLoader.load(getClass().getResource("ControllerConnessione.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}