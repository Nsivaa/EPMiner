package utility;

/**
 * La classe Queue modella una coda generica usata per contenere i pattern frequenti
 *
 */
public class Queue<T> {

    /**
     * Inizio della coda.
     */
    private Record begin = null;

    /**
     * Fine della coda.
     */
    private Record end = null;

    /**
     * La classe Record modella l'oggetto contenuto all'interno della coda
     */
    private class Record {

        /**
         * Elemento di tipo T generico
         */
        T elem;

        /**
         * Puntatore al record successivo.
         */
        Record next;

        /**
         * Costruttore della classe Record. Avvalora elem e next.
         * @param e
         */
        Record(T e) {
            this.elem = e;
            this.next = null;
        }
    }


    /**
     * Restituisce vero se la coda è vuota e falso altrimenti
     * @return true se begin=null o false altrimenti
     */
    public boolean isEmpty() {
        return this.begin == null;
    }

    /**
     * Inserisce nuovo record nella coda.
     * @param e
     */
    public void enqueue(T e) {
        if (this.isEmpty())
            this.begin = this.end = new Record(e);
        else {
            this.end.next = new Record(e);
            this.end = this.end.next;
        }
    }

    /**
     * Restituisce l'elemento contenuto nel primo record della coda.
     * @return elemento contenuto nel primo record della coda
     * @throws EmptyQueueException
     */
    public Object first() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Coda vuota");
        }

        return this.begin.elem;
    }

    /**
     * Toglie un elemento dalla coda.
     * @throws EmptyQueueException
     */
    public void dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Coda vuota");
        }
        if (this.begin == this.end) {
            if (this.begin == null)
                System.out.println("The queue is empty!");
            else
                this.begin = this.end = null;
        } else {
            begin = begin.next;
        }
    }

}