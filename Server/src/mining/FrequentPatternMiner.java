package mining;

import data.*;
import utility.EmptyQueueException;
import utility.Queue;
import java.io.*;
import java.util.*;
/**
 * La classe FrequentPatternMiner permette di scoprire pattern frequenti con algoritmo priori.
 */
public class FrequentPatternMiner implements Iterable<FrequentPattern>, Serializable {
 /**
  * Lista di oggetti FrequentPattern che definiscono il pattern.
  */
	private List<FrequentPattern> outputFP = new LinkedList<>();
	/**
	 * Genera tutti i pattern k=1 frequenti e per ognuno di essi genera quelli con
	 * k maggiore di 1. I pattern sono memorizzati nel menbro outputFP.
	 * Lancia un'eccezione se il dataset è vuoto.
	 * @param data
	 * @param minSup
	 * @throws EmptySetException
	 */
	public FrequentPatternMiner(Data data, float minSup) throws EmptySetException {
		Queue<FrequentPattern> fpQueue = new Queue<>();
		if (data.getNumberOfExamples() == 0) {
			throw new EmptySetException("Dataset target vuoto");
		}
		for (int i = 0; i < data.getNumberOfAttributes(); i++) {
			Attribute currentAttribute = data.getAttribute(i);
			if (currentAttribute instanceof DiscreteAttribute) {
				for (int j = 0; j < ((DiscreteAttribute) currentAttribute).getNumberOfDistinctValues(); j++) {
					DiscreteItem item = new DiscreteItem((DiscreteAttribute) currentAttribute, ((DiscreteAttribute) currentAttribute).getValue(j));
					FrequentPattern fp = new FrequentPattern();
					fp.addItem(item);
					fp.setSupport(fp.computeSupport(data));
					if (fp.getSupport() >= minSup) {
						fpQueue.enqueue(fp);
						outputFP.add(fp);
					}
				}
			}
			else if (currentAttribute instanceof ContinuousAttribute) {
				Iterator<Float> itr;
				itr=((ContinuousAttribute)currentAttribute).iterator();
				float inf;
				float sup = itr.next();
				while (itr.hasNext()) {
					FrequentPattern fp = new FrequentPattern();
					inf = sup;
					sup = itr.next();
					Interval interval=new Interval(inf, sup);
					fp.addItem(new ContinuousItem( (ContinuousAttribute)currentAttribute, interval ));
					fp.setSupport(fp.computeSupport(data));
					if (fp.getSupport() >= minSup) {
						outputFP.add(fp);
						fpQueue.enqueue(fp);
					}
				}
			}
			outputFP = expandFrequentPatterns(data, minSup, fpQueue, outputFP);
			Collections.sort(outputFP);
		}
	}
	/**
	 * Genera i raffinamenti per ogni oggetto presente in fpQueue. Se un raffinamento è frequente, esso viene aggiunto
	 * sia ad fpQueue che ad outputFP.
	 * Lancia un'eccezione se la coda è vuota.
	 * @param data
	 * @param minSup
	 * @param fpQueue
	 * @param outputFP
	 * @return lista linkata popolata con pattern frequenti
	 */
	private List<FrequentPattern> expandFrequentPatterns(Data data, float minSup, Queue<FrequentPattern> fpQueue, List<FrequentPattern> outputFP) {
		while (!fpQueue.isEmpty()) {
			FrequentPattern fp;
			try {
				fp = (FrequentPattern) fpQueue.first();
				fpQueue.dequeue();
			}catch (EmptyQueueException e) {
				return outputFP;
			}
			for (int i = 0; i < data.getNumberOfAttributes(); i++) {
				boolean found = false;
				Attribute currentAttribute = data.getAttribute(i);
				for (Item item : fp) {
					if (item.getAttribute().equals(currentAttribute)) {
						found = true;
						break;
					}
				}
				if (!found) {
					if (currentAttribute instanceof DiscreteAttribute) {
						for (int j = 0; j < ((DiscreteAttribute) currentAttribute).getNumberOfDistinctValues(); j++) {
							DiscreteItem item = new DiscreteItem((DiscreteAttribute) currentAttribute, ((DiscreteAttribute) currentAttribute).getValue(j));
							FrequentPattern newFP = refineFrequentPattern(fp, item);
							newFP.setSupport(newFP.computeSupport(data));
							if (newFP.getSupport() >= minSup) {
								fpQueue.enqueue(newFP);
								outputFP.add(newFP);
							}
						}
					}
					else {
						Iterator<Float> itr = ((ContinuousAttribute) currentAttribute).iterator();
						float inf;
						float sup = itr.next();
						while (itr.hasNext()) {
							inf = sup;
							sup = itr.next();
							ContinuousItem item = new ContinuousItem((ContinuousAttribute) currentAttribute, new Interval(inf, sup));
							FrequentPattern newFP = refineFrequentPattern(fp, item);
							newFP.setSupport(newFP.computeSupport(data));
							if (newFP.getSupport() >= minSup) {
								fpQueue.enqueue(newFP);
								outputFP.add(newFP);
							}
						}
					}
				}
			}
		}
		return outputFP;
	}
	/**
	 * Crea un nuovo pattern contenente tutti gli oggetti di FP e vi aggiunge l'oggetto Item preso come argomento.
	 * @param item
	 * @param FP
	 * @return nuovo pattern ottenuto aggiungendo l'item d'input
	 */
	FrequentPattern refineFrequentPattern(FrequentPattern FP, Item item) {
		FrequentPattern FreqP = new FrequentPattern(FP);
		FreqP.addItem(item);
		return FreqP;
	}
	/**
	 * Restituisce lo stato dell'oggetto sotto forma di stringa.
	 * @return stringa contenente il valore di OutputFP
	 */
	public String toString() {
		String value = "";
		int i = 0;
		if (!outputFP.isEmpty()) {
			for (FrequentPattern fp : this) {
				value+=i+1 + ":" + fp + "\n";
				i++;
			}
		}
		return value;
	}
	/**
	 * Restituisce un iteratore di tipo FrequenPattern per scandire la lista outputFP.
	 * @return un iteratore di elementi di tipo FrequentPattern
	 */
	public Iterator<FrequentPattern> iterator() {
		return outputFP.iterator();
	}
	/**
	 * Esporta lo stato dell'oggetto su un file il cui nome è preso come argomento.
	 * Lancia le relative eccezioni se si verifica un errore.
	 * @param nomeFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salva(String nomeFile) throws  FileNotFoundException,IOException {
		FileOutputStream fileOutput = new FileOutputStream(nomeFile);
		ObjectOutputStream streamOutput = new ObjectOutputStream(fileOutput);
		streamOutput.writeObject(this);
		streamOutput.close();
	}
	/**
	 * Importa un oggetto FrequentPatternMiner da un file il cui nome è preso come argomento.
	 * Lancia le relative eccezioni se si verifica un errore.
	 * @param nomeFile
	 * @return FrequentPatternMiner memorizzato nel file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static FrequentPatternMiner carica(String nomeFile) throws  FileNotFoundException,IOException, ClassNotFoundException {
		FileInputStream fileInput = new FileInputStream(nomeFile);
		ObjectInputStream streamInput = new ObjectInputStream(fileInput);
		FrequentPatternMiner fpminer = (FrequentPatternMiner) streamInput.readObject();
		streamInput.close();
		return fpminer;
	}
}

