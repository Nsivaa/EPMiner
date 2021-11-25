package utility;

/**
 * Eccezione lanciata nel caso in cui si cerca di leggere/cancellare da una coda vuota.
 */
public class EmptyQueueException extends Exception{

    /**
     * Il costruttore richiama il costruttore della superclasse e le passa il messagio di errore.
     * @param error

     */
    EmptyQueueException(String error){
        super(error);
    }
}
