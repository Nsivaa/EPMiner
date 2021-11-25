package data;

/**
 * La classe EmptySetException modella l’eccezione che occorre qualora
 * l'insieme di training fosse vuoto (non contiene transazioni/esempi).
 */
public class EmptySetException extends Exception{
    /**
     * Il costruttore richiama quello della classe madre, passandole il messagio di errore.
     * @param error
     */
    public EmptySetException(String error){
        super(error);
    }

}
