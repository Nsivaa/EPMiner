package data;

/**
 * La classe DiscreteAttribute che estende la classe Attribute e modella un attributo
 * discreto rappresentando l’insieme di valori distinti del relativo dominio.
 */
public class DiscreteAttribute extends Attribute {
    /**
     * Array di stringhe, una per ciascun valore discreto, che rappresenta il domino dell’attributo.
     */
    private String[] values;

    /**
     * Invoca il costruttore della classe madre e
     * avvalora l'array values[ ] con i valori discreti in input.
     * @param name
     * @param index
     * @param values
     */
    DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        this.values = new String[values.length];

        for(int i = 0; i < values.length; ++i) {
            this.values[i] = values[i];
        }
    }

    /**
     * Restituisce la cardinalità del membro values.
     * @return numero di valori discreti dell'attributo
     */
    public int getNumberOfDistinctValues() {
        return values.length;
    }

    /**
     * Restituisce il valore in posizione i del membro values.
     * @param index
     * @return valore nel domin-value: Stringio dell’attributo
     */
    public String getValue(int index) {
        return values[index];
    }
}
