package com.codice.clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * La classe ControllerMenu è il controller della scena relativa al menu di selezione dell' operazione nella gui
 */

public class ControllerMenu {
    /**
     * Stage dell'interfaccia
     */
    private Stage stage;
    /**
     * Scena dell'interfaccia
     */
    private Scene scene;

    /**
     * Metodo che richiama cambiaScena permettendo all'utente di inserire i valori per una nuova ricerca
     * @throws IOException
     */
    @FXML
    private void nRicerca(ActionEvent actionEvent) throws IOException {
        cambiaScena(actionEvent, 1);
    }

    /**
     * Metodo che richiama cambiaScena permettendo all'utente di inserire i valori per fare una ricerca nell'archivio
     * @throws IOException
     */

    @FXML
    private void aRicerca(ActionEvent actionEvent) throws IOException {
        cambiaScena(actionEvent, 2);
    }

    /**
     * Metodo che cambia scena passando alla schermata d'input
     * @throws IOException
     */

    private void cambiaScena(ActionEvent actionEvent, int i)throws IOException{
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ControllerInput.fxml"));
        Parent root  = loader.load();
        ControllerInput controller = loader.getController();
        controller.setOpzione(i);
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.show();
    }


}
