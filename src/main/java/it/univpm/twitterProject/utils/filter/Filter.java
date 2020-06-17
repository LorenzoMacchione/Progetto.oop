package it.univpm.twitterProject.utils.filter;

import it.univpm.twitterProject.model.Tweet;

/** Rappresenta l interfaccia per i filtri.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public interface Filter {

	
	/** questo metodo restituisce True se il tweet rispetta le caratteristiche 
	 * richeste del filtro che implementa l'interfaccia.
	 * @param tweet su cui si vuol esegurire la verifica.
	 * @return risposta.
	*/
	
	public boolean filter(Tweet t);
}
