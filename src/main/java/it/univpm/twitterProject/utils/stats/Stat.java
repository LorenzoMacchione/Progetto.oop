package it.univpm.twitterProject.utils.stats;

import org.json.simple.JSONObject;

/**
 * Rappresenta l interfaccia per le statistiche.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public interface Stat {

	/**
	 * Metodo che restituisce le statistiche sotto forma di JSONObject.
	 * 
	 * @return JSONObject: contenente le statistiche
	 */
	public JSONObject getStatJo();
}
