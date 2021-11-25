package server;

import data.Data;
import data.EmptySetException;
import database.DatabaseConnectionException;
import database.NoValueException;
import mining.EmergingPatternMiner;
import mining.FrequentPatternMiner;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
/**
 * La classe ServeOneClient si occupa della comunicazione con un singolo client, dedicandovi un thread.
 */
class ServerOneClient extends Thread {
    /**
     * Socket lato server per la comunicazione con il client.
     */
    private Socket socket;
    /**
     * Stream di oggetti in input al server.
     */
    private ObjectInputStream in;
    /**
     * Stream di oggetti in output dal server.
     */
    private ObjectOutputStream out;
    /**
     * Avvalora il campo socket del server con l'elemento preso come argomento. Definisce poi gli stream di I/O
     * del server a partire da esso e avvia il thread.
     * @param socket
     *@throws IOException
     */
    ServerOneClient(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        start();
    }
    /**
     * Gestisce le richieste del client attraverso lo stream. Permette di avvalorare archive con i pattern appresi,
     * e di salvarne/caricarne i dati attraverso un file.
     */
    public void run() {
        EmergingPatternMiner epMiner = null;
        FrequentPatternMiner fpMiner = null;
        String rStr = "";
        try {
            while (true) {
                int opzione = (int) in.readObject();
                float minSup = (float) in.readObject();
                float minGr = (float) in.readObject();
                String targetName = (String) in.readObject();
                String backgroundName = (String) in.readObject();
                if (opzione == 1) {
                    try {
                        Data dTarget = new Data(targetName);
                        fpMiner = new FrequentPatternMiner(dTarget, minSup);
                        fpMiner.salva("FP" + targetName + "MS" + minSup + ".dat");
                        rStr+="Frequent patterns\n" + fpMiner+ "\n";
                    } catch (SQLException e) {
                        rStr+="Tabella target non trovata\n";
                    } catch (EmptySetException e) {
                        rStr+="Dataset vuoto\n";
                    }
                    if(fpMiner != null){
                        try {
                            Data dataBackground = new Data(backgroundName);
                            epMiner = new EmergingPatternMiner(dataBackground, fpMiner, minGr);
                            epMiner.salva("EP" + backgroundName + "MS" + minSup + "MG" + minGr + ".dat");
                            rStr+="Emerging patterns\n" + epMiner;
                        } catch (SQLException e){
                            rStr+="Tabella background non trovata  \n";
                        }catch (EmptySetException e){
                            rStr+="Dataset vuoto \n";
                        }
                    }
                }
                else { //opzione==2
                    try{
                        fpMiner = FrequentPatternMiner.carica("FP" + targetName + "MS" + minSup + ".dat");
                        rStr+="Frequent patterns\n" + fpMiner + "\n";
                    }catch (IOException e){
                        rStr+="File FrequentPatterns non trovato\n";
                    }
                    try{
                        epMiner = EmergingPatternMiner.carica("EP" + backgroundName + "MS" + minSup + "MG" + minGr + ".dat");
                        rStr+="Emerging patterns\n" + epMiner;
                    }catch(IOException e){
                        rStr+="File EmergingPatterns non trovato\n";
                    }
                }
                salvaInArchivio(minSup, minGr, backgroundName, targetName,  rStr);
                rStr="";
            }
        }catch (SocketException e) {
            System.out.println("Client disconnesso");
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoValueException e) {
            e.printStackTrace();
        }catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Permette di salvare i risultati della ricerca su un file .txt
     * @param minGr
     * @param minSup
     * @param backgroundName
     * @param rStr
     * @param targetName
     * @throws IOException
     */
    private void salvaInArchivio(float minSup, float minGr,  String backgroundName, String targetName, String rStr) throws IOException {
        String nomeArchivio = "Arc" + minSup + "-" + minGr + "-" + targetName + "-" + backgroundName + ".txt";
        PrintWriter outStream = new PrintWriter(nomeArchivio);
        outStream.print(rStr);
        outStream.close();
    }
}
