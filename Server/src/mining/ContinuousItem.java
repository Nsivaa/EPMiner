package mining;

import data.ContinuousAttribute;
/**
 * La classe ContinuousItem estende la classe astratta Item
 * e modella la coppia Attributo continuo - Intervallo di valori.
 */
public class ContinuousItem extends Item {
    /**
     * Richiama il costuttore di Item passandogli attribute e value come argomenti.
     * @param value
     * @param attribute
     */
    public ContinuousItem(ContinuousAttribute attribute, Interval value) {
        super(attribute, value);
    }
    /**
     * Verifica che il parametro value rientri nell'intervallo dell'oggetto Item.
     * @return risultato della verifica
     */
    @Override
    boolean checkItemCondition(Object value) {
        return ((Interval)this.getValue()).checkValueInclusion((float)value);
    }
    /**
     * Restituisce lo stato dell'oggetto sottoforma di stringa.
     * @return stringa che rappresenta lo stato dell’oggetto nella forma nome-attributo in [inf,sup[
     */
    @Override
    public String toString() {
        return this.getAttribute().getName() + " in " + this.getValue().toString();
    }
}
