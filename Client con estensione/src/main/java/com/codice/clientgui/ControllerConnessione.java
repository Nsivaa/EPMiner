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
import java.net.InetAddress;
import java.net.Socket;

/**
 * La classe ControllerConnessione è il controller della scena relativa alla schermata d'inserimento
 * indirizzo Ip e porta  e alla connessione all'applicazione Server
 */
public class ControllerConnessione {
    /**
     * Campo dove viene inserito l'indirizzo ip nella gui
     */
    @FXML private TextField ipT;
    /**
     * Campo dove viene inserita la porta d'accesso nella gui
     */
    @FXML private TextField portaT;
    /**
     * Stage dell'interfaccia
     */
    private Stage stage;
    /**
     * Scena dell'interfaccia
     */
    private Scene scene;
    /**
     * Oggetto outputStream
     */
    private ObjectOutputStream out;
    /**
     * Oggetto inputStream
     */
    private ObjectInputStream in;


    /**
     * Metodo che usa indirizzo IP, porta e una socket per connettersi al Server
     */
    @FXML
    private void connetti(ActionEvent actionEvent)  {

        try{
            String ip=ipT.getText();
            int porta=Integer.parseInt(portaT.getText());
            InetAddress addr = InetAddress.getByName(ip);
            Socket socket = new Socket(addr, porta);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            cambiaScena(actionEvent);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Server non connesso");
            alert.setHeaderText("Impossibile connettersi al server, riprova più tardi");
            alert.show();
        }
    }

    /**
     * Metodo che cambia scena tornando passando al menu di selezione operazione
     * @throws IOException
     */
    private void cambiaScena(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("ControllerMenu.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setUserData(this);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.show();
    }

    /**
     * Metodo che reimposta i valori della porta e dell'indirizzo IP
     */
    @FXML
    private void resetta(ActionEvent actionEvent)  {
        ipT.setText("127.0.0.1");
        portaT.setText("8080");
    }

    ObjectInputStream getIn() {return in;}

    ObjectOutputStream getOut() {
        return out;
    }



}
