package com.codice.clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;


/**
 * La classe ControllerInput è il controller della scena relativa alla schermata d'inserimento
 * dati di input per effettuare la ricerca
 */
public class ControllerInput {
    /**
     * Campo dove viene inserito il minimo grow rate nella gui
     */
    @FXML private TextField mgr;
    /**
     * Campo dove viene inserito il minimo support rate nella gui
     */
    @FXML private TextField msu;
    /**
     * Campo dove viene inserita la tabella di target
     */
    @FXML private TextField tta;
    /**
     * Campo dove viene inserita la tabella di background
     */
    @FXML private TextField tba;
    /**
     * Stage dell'interfaccia
     */
    private Stage stage;
    /**
     * Scena dell'interfaccia
     */
    private Scene scene;
    /**
     * Campo dove viene memorizzato il supporto minimo
     */
    private float minSup ;
    /**
     * Campo dove viene memorizzato il minimo grow rate
     */
    private float minGr ;
    /**
     * Campo dove viene memorizzata la tabella di target
     */
    private String tabTarget ;
    /**
     * Campo dove viene memorizzata la tabella di background
     */
    private String tabBackground;
    /**
     * Campo dove viene memorizzata l'opzione
     */
    private int opzione;

    /**
     * Metodo che avvalora l'opzione (nuova ricerca o ricerca in archivio)
     */
     void setOpzione(int opzione) {
        this.opzione = opzione;
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
     * Metodo che cambia scena e manda i dati all'applicazione server
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    private void avanza(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        getValori();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        ControllerConnessione controllerConnessione = (ControllerConnessione) stage.getUserData();
        if (minGr>0  && minSup>0 && minSup<=1 && tabTarget.length()!=0 && tabBackground.length()!=0){
            ObjectInputStream in = controllerConnessione.getIn();
            ObjectOutputStream out = controllerConnessione.getOut();
            try{
            out.writeObject(opzione);
            out.writeObject(minSup);
            out.writeObject(minGr);
            out.writeObject(tabTarget);
            out.writeObject(tabBackground);
            String risposta ="";
            risposta=(String) (in.readObject());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ControllerOutput.fxml"));
            Parent root  = loader.load();
            ControllerOutput controller = loader.getController();
            controller.setOutput(risposta);
            scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.show();
            }
            catch(SocketException e){stampareWarning("Server disconnesso, riavviare applicazione"); }
        }
        else {
            if (!tabBackground.equals("!!!")  && !tabTarget.equals("!!!")  && minSup != 0 && minGr != 0) {
                String err = "";
                if (minSup <= 0 || minSup > 1) {
                    err = err + "Il minimo supporto deve essere un numero compreso tra 0 e 1" + "\n";
                }
                if (minGr <= 0) {
                    err = err + "Il minimo grow rate deve essere un numero maggiore di 0" + "\n";
                }
                if (tabTarget.length() == 0) {
                    err = err + "Il campo Tabella Target non può essere vuoto" + "\n";
                }
                if (tabBackground.length() == 0) {
                    err = err + "Il campo Tabella Background non può essere vuoto" + "\n";
                }
                stampareWarning(err);
            }
        }

    }

    /**
     * Metodo che avvalora il minimo supporto, il minimo grow rate, la tabella do target e quella di background
     */
    @FXML
    private void getValori() {
        try{
            tabBackground=tba.getText();
            tabTarget=tta.getText();
            minGr=Float.parseFloat(mgr.getText());
            minSup=Float.parseFloat(msu.getText());
        }
        catch (NumberFormatException e){ stampareWarning("MinGrow e MinSupp devono essere  numeri");
        tabBackground="!!!";
        tabTarget="!!!";
        minSup=0;
        minGr=0;
        }

    }

    /**
     * Metodo che genera un warning
     */
    private void stampareWarning(String err)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ATTENZIONE");
        alert.setHeaderText(err);
        alert.show();
    }


    @FXML
    private void initialize() {}
}
