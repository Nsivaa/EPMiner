package data;

import java.util.Iterator;

/**
 * La classe ContiuousAttributeIterator realizza l’iteratore che itera sugli elementi
 * della sequenza composta da numValues valori reali equidistanti tra di loro (cut points)
 * compresi tra min e max ottenuti per mezzo di discretizzazione.
 */
public class ContinuousAttributeIterator implements Iterator<Float> {

	/**
	 * Minimo.
	 */
	private float min;
	/**
	 * Massimo.
	 */
	private float max;
	/**
	 * Posizione dell’iteratore nella collezione di cut point
	 * generati per [min, max[ tramite discretizzazione.
	 */
	private int j=0;
	/**
	 * Numero di intervalli di discretizzazione.
	 */
	private int numValues;

	/**
	 * Avvalora i membri attributo della classe con i parametri del costruttore.
	 * @param min
	 * @param max
	 * @param numValues
	 */
	ContinuousAttributeIterator(float min, float max, int numValues){
		this.min=min;
		this.max=max;
		this.numValues=numValues;
	}

	/**
	 * Restituisce true se j minore o uguale numValues, false altrimenti.
	 * @return risultato della verifica
	 */
	@Override
	public boolean hasNext() {
		
		return (j<=numValues);
	}

	/**
	 * Incrementa j, restituisce il cut point in posizione j-1 (min + (j-1)*(max-min)/numValues).
	 * @return cut point
	 */
	public Float next() {

		j++;
		return min+((max-min)/numValues)*(j-1);
	}

}
