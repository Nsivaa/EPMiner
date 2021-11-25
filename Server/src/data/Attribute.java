package data;

import java.io.Serializable;

/**
 * Classe astratta Attribute modella un generico attributo discreto o continuo.
 */
public abstract class Attribute implements Serializable {

    /**
     * Nome simbolico dell'attributo.
     */
    private String name;
    /**
     * Identificativo numerico dell'attributo (indica la posizione della colonna).
     */
    private int index;

    /**
     * Inizializza i valori dei membri name, index.
     * @param name
     * @param index
     */
    public Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * Restituisce il valore nel membro name.
     * @return nome dell'attributo
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce il valore nel membro index.
     * @return identificativo numerico dell'attributo
     */
    public int getIndex() {
        return index;
    }


    /**
     *
     * Restituisce il valore del membro name.
     * @return dentificativo
     */
    public String toString() {//da chiedere alla prof
        return name;
    }
}
