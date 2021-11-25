package mining;

import data.Attribute;
import java.io.Serializable;
/**
 * La classe astratta Item modella un generico item (coppia attributo-valore).
 */
abstract class Item implements Serializable {
    /**
     * Attributo coinvolto nell'item.
     */
    private Attribute attribute;
    /**
     * Valore assegnato all'attributo.
     */
    private Object value;
    /**
     * Avvalora i campi dell'oggetto con gli elementi presi come argomento.
     * @param attribute
     * @param value
     */
    Item(Attribute attribute, Object value) {
        this.attribute=attribute;
        this.value=value;
    }
    /**
     * Restituisce il valore del campo attribute.
     * @return attributo membro dell'item
     */
    Attribute getAttribute() {
        return this.attribute;
    }
    /**
     * Restituisce il valore del campo value.
     * @return valore coinvolto nell'item
     */
    Object getValue() {
        return this.value;
    }
    /**
     * Confronta lo stato dell'oggetto preso come argomento con quello dell'oggetto corrente.
     * @param var1
     * @return vero se uguale, falso altrimenti
     */
    abstract boolean checkItemCondition(Object var1);
    /**
     * Restituisce lo stato dell'oggetto sotto forma di stringa.
     * @return stringa nella forma attribute=value
     */
    public String toString() {
        return getAttribute() + "=" + getValue();
    }
}
