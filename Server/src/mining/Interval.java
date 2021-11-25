package mining;

import java.io.Serializable;
/**
 * La classe Interval modella un intervallo reale [inf ,sup[.
 */
class Interval implements Serializable {
    /**
     * Estremo inferiore dell'intervallo.
     */
    private float inf;
    /**
     * Estremo superiore dell'intervallo.
     */
    private float sup;
    /**
     * Avvalora i due attributi inf e sup con i parametri del costruttore.
     * @param inf
     * @param sup
     */
    Interval(float inf, float sup) {
        this.inf = inf;
        this.sup = sup;
    }
    /**
     * Avvalora il campo inf con il valore passato come argomento.
     * @param inf
     */
    void setInf(float inf) {
        this.inf = inf;
    }
    /**
     * Avvalora il campo sup con il valore passato come argomento.
     * @param sup
     */
    void setSup(float sup) {
        this.sup = sup;
    }
    /**
     * Restituisce il campo inf.
     * @return estremo inferiore
     */
    float getInf() {
        return this.inf;
    }
    /**
     * Restituisce il campo sup.
     * @return estremo superiore
     */
    float getSup() {
        return this.sup;
    }
    /**
     * Restituisce vero se il parametro è compreso tra i valori min e sup e falso altrimenti.
     * @param value
     * @return esito della verifica
     */
    boolean checkValueInclusion(float value) {
        return (value >= this.inf && value < this.sup);
    }
    /**
     * Restituisce lo stato dell'oggetto sotto forma di stringa.
     * @return riferimento ad una stringa in cui si rappresenta l’intervallo [inf,sup[
     */
    public String toString(){
        return ("[" + this.inf + "," + this.sup + "[");
    }
}
