package mining;

import data.DiscreteAttribute;
/**
 * La classe DiscreteItem estende la classe astratta Item e rappresenta la coppia
 * (Attributo discreto - valore discreto).
 */
class DiscreteItem extends Item {
    /**
     Richiama il costruttore di Item passandogli come argomento attribute e value.
     @param attribute
     @param value
     */
    DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }
    /**
     * Confronta l'oggetto value preso come argomento con il campo value di DiscreteItem.
     * @return vero se uguale, falso altrimenti
     */
    boolean checkItemCondition(Object value) {
        return getValue().equals(value);
    }
}
