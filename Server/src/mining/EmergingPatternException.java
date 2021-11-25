package mining;

/**
 * La classe EmerginPatternException modella l’eccezione che occorre qualora
 * il pattern corrente non soddisfa la condizione di minimo grow rate.
 */
class EmergingPatternException extends Exception{
    EmergingPatternException(String errore) {
        super(errore);
    }
}
