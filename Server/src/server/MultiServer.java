package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * La classe MultiServer permette di gestire la comunicazione con client multipli, istanziando per ognuno di essi
 * un oggetto ServerOneClient, sfruttando il multithreading.
 */
public class MultiServer {
    /**
     * Porta di default per la connessione al client.
     */
    private static final int PORT = 8080;
    /**
     * Crea un oggetto MultiServer.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MultiServer multiServer = new MultiServer();
    }
    /**
     * Avvia il server invocando il metodo run().
     * @throws IOException
     */
    MultiServer() throws IOException {
        run();
    }
    /**
     * Il server crea un socket e costruisce un oggetto ServerOneClient a partire da esso per ascoltare nuove richieste
     *  di connessione da client sulla porta di default.
     *  @throws IOException
     */
    private void run() throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server avviato");
        try {
            while(true){
                Socket socket = s.accept();
                System.out.println(socket);
                System.out.println("Nuovo client connesso");
                try {
                    new ServerOneClient(socket);
                }catch(IOException e) {
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }
}
