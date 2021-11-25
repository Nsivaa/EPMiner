package data;

import java.util.Iterator;

/**
 * La classe ContinuousAttribute estende la classe Attribute e modella
 * un attributo continuo (numerico) rappresentandone il dominio [min,max].
 */
public class ContinuousAttribute extends Attribute implements Iterable<Float>{
    /**
     * Estremo superiore di un intervallo.
     */
    private float max;
    /**
     * Estremo inferiore di un intervallo.
     */
    private float min;

    /**
     * Restituisce il valore del membro max.
     * @return estremo superiore di un intervallo
     */
    public float getMax() {
        return max;
    }

    /**
     * Restituisce il valore del membro min.
     * @return estremo inferiore dell'intervallo
     */
    public float getMin() {
        return min;
    }

    /**
     * Invoca il costruttore della classe madre e avvalora i membri.
     * @param name
     * @param index
     * @param min
     * @param max
     */
    public ContinuousAttribute(String name, int index, float min, float max) {
        super(name, index);
        this.min = min;
        this.max = max;
    }

    /**
     * Istanzia e restituisce un riferimento ad oggetto di classe
     * ContinuousAttributeIterator con numero di intervalli di discretizzazione pari a 5.
     * @return riferimento a una istanza di ContinuousAttributeIterator
     */
    public Iterator<Float> iterator(){
        return new ContinuousAttributeIterator(min, max, 5);
    }
}
