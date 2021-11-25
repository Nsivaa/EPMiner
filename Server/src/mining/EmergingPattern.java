package mining;

import java.io.Serializable;
/**
 * La classe EmergingPattern estende la classe FrequentPattern e modella un pattern emergente.
 */
class EmergingPattern extends FrequentPattern implements Serializable {

    /**
     * Valore di grow rate del pattern.
     */
    private float growrate;
    /**
     * Richiama il costruttore della classe madre passandogli il FrequentPattern preso come argomento
     * e salva il valore di growrate preso come argomento nel campo corrispondente di EmergingPattern.
     * @param fp
     * @param growrate
     */
    EmergingPattern(FrequentPattern fp, float growrate) {
        super(fp);
        this.growrate = growrate;
    }
    /**
     * Restituisce il valore di grow rate dell'EmergingPattern.
     * @return grow rate
     */
    float getGrowrate() {
        return this.growrate;
    }
    /**
     * Restituisce lo stato dell'oggetto sotto forma di stringa.
     * @return stringa contenente il pattern emergente nella forma “pattern [supporto][growrate]"
     */
    public String toString() {
        String value="";
        if (getPatternLength() > 0) {
            value=super.toString() + "[" + getGrowrate() + "]";
        }
        return value;
    }
}
