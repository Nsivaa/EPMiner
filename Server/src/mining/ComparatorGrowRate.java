package mining;

import java.util.Comparator;
/**
 * La Classe ComparatorGrowRate si occupa di confrontare due oggetti EmergingPattern
 * rispetto al loro valore di grow rate, implementando l'interfaccia Comparator.
 */
class ComparatorGrowRate implements Comparator<EmergingPattern> {
  /**
  * Restituisce il risultato del confronto tra due EmergingPattern in base al loro
  * grow rate.
  * @return risultato confronto
  */
  @Override
  public int compare(EmergingPattern e1, EmergingPattern e2) {
    if(e1.getGrowrate() == e2.getGrowrate()) {
      return 0;
    }
    else if(e1.getGrowrate() > e2.getGrowrate()) {
      return 1;
    }
     else {
       return -1;
    }
  }
}
