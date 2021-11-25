package mining;

import data.Attribute;
import data.Data;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * La classe FrequentPattern rappresenta un pattern frequente.
 */
class FrequentPattern implements Iterable<Item>, Comparable<FrequentPattern>, Serializable {
    /**
     * Lista di oggetti Item.
     */
    private List<Item> fp;
    /**
     * Valore di supporto del pattern fp.
     */
    private float support;

    /**
     * Alloca una Linked List per il campo fp.
     */
    FrequentPattern() {
        fp = new LinkedList<Item>();
    }

    /**
     * Ricopia lo stato del FrequentPattern preso come argomento nell'oggetto corrente.
     *
     * @param FP
     */
    FrequentPattern(FrequentPattern FP) {
        fp = new LinkedList<Item>();
        for (Item item : FP) {
            fp.add(item);
        }
        support = FP.getSupport();
    }

    /**
     * Aggiunge l'oggetto Item preso come argomento nell'ultima posizione della lista fp.
     *
     * @param item
     */
    void addItem(Item item) {
        fp.add(item);
    }

    /**
     * Restituisce l'oggetto Item contenuto nella lista fp in posizione index.
     *
     * @param index
     * @return item che occupa la posizione indicata in fp
     */
    Item getItem(int index) {
        return fp.get(index);
    }

    /**
     * Restituisce lo stato dell'oggetto sotto forma di Stringa.
     *
     * @return stringa stato dell'oggetto
     */
    public String toString() {
        String value = "";
        Iterator it = fp.iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            if (it.hasNext()) {
                value += "(" + item + ") AND ";
            } else {
                value += "(" + item + ")";
                value += "[" + support + "]";
            }
        }
        return value;
    }

    /**
     * Calcola il valore di supporto dell'oggetto corrente rispetto all'intero dataset passato come argomento.
     *
     * @param data
     * @return supporto/numero di transazioni
     */
    float computeSupport(Data data) {
        int suppCount = 0;
        for (int i = 0; i < data.getNumberOfExamples(); i++) {
            boolean isSupporting = true;
            for (Item item : this) {
                Attribute attribute = item.getAttribute();
                Object valueInExample = data.getAttributeValue(i, attribute.getIndex());
                if (!item.checkItemCondition(valueInExample)) {
                    isSupporting = false;
                    break;
                }
            }
            if (isSupporting) {
                suppCount++;
            }
        }
        return (float) suppCount / (float) data.getNumberOfExamples();
    }

    /**
     * Restituisce il valore del campo support dell'oggetto corrente.
     *
     * @return valore di supporto del pattern
     */
    float getSupport() {
        return support;
    }

    /**
     * Sovrascrive il valore di support dell'oggetto corrente con quello passato come argomento.
     *
     * @param support
     */
    void setSupport(float support) {
        this.support = support;
    }

    /**
     * Restituisce un intero che rappresenta la lunghezza della lista fp.
     *
     * @return lunghezza del pattern
     */
    int getPatternLength() {
        return fp.size();
    }

    /**
     * Restituisce un iteratore di tipo Item per scandire la lista fp.
     *
     * @return un iteratore di elementi di tipo Item
     */
    public Iterator<Item> iterator() {
        return fp.iterator();
    }

    /**
     * Confronta il valore support dell'oggetto corrente con quello preso come argomento.
     *
     * @return risultato del confronto
     */
    public int compareTo(FrequentPattern f) {
        if (support < f.support) {
            return -1;
        }
        if (support > f.support) {
            return 1;
        }
        return 0;

    }
}