package com.codice.clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * La classe ControllerOutput è il controller della scena relativa alla scena in cui vengono stampati gli output
 */
public class ControllerOutput {

    /**
     * Pulsante che imposta l'output mostrando il Frequent Pattern
     */
    @FXML private Button FP;

    /**
     * Pulsante che imposta l'output mostrando l'emerging Pattern
     */
    @FXML private Button EP;

    /**
     * Finestra dove viene stampato l'output
     */
    @FXML private TextArea txt;
    /**
     * Stage dell'interfaccia
     */
    private Stage stage;
    /**
     * Scena dell'interfaccia
     */
    private Scene scene;
    /**
     * Stringa dove viene memorizzato l'emerging pattern
     */
    private String emergingPattern="";
    /**
     * Stringa dove viene memorizzato il frequent pattern
     */
    private String frequentPattern="";


    /**
     * Metodo che inizializza i risultati della ricerca per la scena corrente
     */
     void setOutput(String output) {
        try{
        String[] parts = output.split("Emerging patterns");
        frequentPattern=parts[0].replace("Frequent patterns","");
        emergingPattern=parts[1];
        FP.setStyle("-fx-background-color: #6495ED");
        txt.setText(frequentPattern);}
        catch (Exception e){
            frequentPattern=output;
            emergingPattern=output;
            txt.setText(frequentPattern);
            FP.setStyle("-fx-background-color: #6495ED");
        }
    }


    /**
     * Metodo che cambia scena tornando al MENU di selezione operazione
     * @throws IOException
     */
    @FXML
    private void indietreggia(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ControllerMenu.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.show();
    }


    /**
     * Metodo che mostra a video i frequent pattern
     */
    @FXML
    private void mostrareFP(){
        txt.setText(frequentPattern);
        FP.setStyle("-fx-background-color: #6495ED");
        EP.setStyle("-fx-background-color: #616161;");
    }


    /**
     * Metodo che mostra a video gli emerging pattern
     */
    @FXML
    private void mostrareEP(){
        txt.setText(emergingPattern);
        EP.setStyle("-fx-background-color: #6495ED;");
        FP.setStyle("-fx-background-color: #616161;");
    }

    @FXML
    private void initialize() {
    }


}
