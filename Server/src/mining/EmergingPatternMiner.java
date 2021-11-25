package mining;

import data.*;
import data.EmptySetException;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * La classe EmergingPatternMiner opera su una lista di FrequentPattern e ne scopre un EmergingPattern
 */
public class EmergingPatternMiner implements Iterable<EmergingPattern>, Serializable {
    /**
     * Lista di oggetti EmergingPattern che definiscono il pattern.
     */
    private List<EmergingPattern> epList = new LinkedList<>();
    /**
     * Calcola il growrate di tutti gli oggetti presenti in fpList, e aggiunge ad epList quelli che soddisfano la
     * condizione di minimo grow rate.
     * Lancia un'eccezione se il dataset è vuoto.
     * @param dataBackground
     * @param fpList
     * @param minG
     * @throws EmptySetException
     */
    public EmergingPatternMiner(Data dataBackground, FrequentPatternMiner fpList, float minG) throws EmptySetException {
        if(dataBackground.getNumberOfExamples() == 0) {
            throw new EmptySetException("Dataset background vuoto");
        }
        for (FrequentPattern fp : fpList) {
            try {
                EmergingPattern EP = computeEmergingPattern(dataBackground, fp, minG);
                epList.add(EP);
            }catch(EmergingPatternException e) {

            }
        }
        epList.sort(new ComparatorGrowRate());
    }
    /**
     * Il valore di grow rate è calcolato come il rapporto tra il supporto di un FrequentPattern
     * e il supporto dell'intero dataset.
     * @param dataBackground
     * @param fp
     * @return grow rate di fp
     */
    float computeGrowRate(Data dataBackground, FrequentPattern fp) {
        return (fp.getSupport() / fp.computeSupport(dataBackground));
    }
    /**
     * Verifica che il valore di grow rate di fp sia superiore al minimo. In caso affermativo, restituisce un nuovo
     * EmergingPattern partendo da fp, altrimenti lancia un'eccezione.
     * @param fp
     * @param dataBackground
     * @param minGR
     * @return l'emerging pattern creato da fp se la condizione sul grow rate è soddisfatta, null altrimenti
     * @throws EmergingPatternException
     */
    EmergingPattern computeEmergingPattern(Data dataBackground, FrequentPattern fp, float minGR) throws EmergingPatternException {
        float growRate = computeGrowRate(dataBackground, fp);
        EmergingPattern EP;
        if(growRate >= minGR){
            EP = new EmergingPattern(fp, growRate);
        }
        else{
            throw new EmergingPatternException("Grow rate superiore al minimo");
        }
        return EP;
    }
    /**
     * Restituisce lo stato dell'oggetto sotto forma di stringa.
     * @return Stringa rappresentante il valore di epList
     */
    public String toString() {
        String value = "";
        int i = 0;
        for (EmergingPattern ep: this) {
            value+=i+1 + ":" + ep+ "\n" ;
            i++;
        }
        return value;
    }


    /**
     * Restituisce un iteratore di tipo EmergingPattern per scandire la lista epList.
     * @return un iteratore di elementi di tipo EmergingPattern
     */
    public Iterator<EmergingPattern> iterator() {
        return epList.iterator();
    }
    /**
     * Esporta lo stato dell'oggetto su un file il cui nome è preso come argomento.
     * Lancia le relative eccezioni se si verifica un errore.
     * @param nomeFile
     *  @throws FileNotFoundException
     *  @throws IOException
     */
    public void salva(String nomeFile) throws  FileNotFoundException,IOException {
        FileOutputStream fileOutput = new FileOutputStream(nomeFile);
        ObjectOutputStream streamOutput = new ObjectOutputStream(fileOutput);
        streamOutput.writeObject(this);
        streamOutput.close();
    }
    /**
     * Importa un oggetto EmergingPatternMiner da un file il cui nome è preso come argomento.
     * Lancia le relative eccezioni se si verifica un errore.
     * @return FrequentPatternMiner memorizzato nel file
     * @param nomeFile
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static EmergingPatternMiner carica(String nomeFile) throws FileNotFoundException,IOException,ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream(nomeFile);
        ObjectInputStream streamInput = new ObjectInputStream(fileInput);
        EmergingPatternMiner epminer = (EmergingPatternMiner) streamInput.readObject();
        streamInput.close();
        return epminer;
    }
}
