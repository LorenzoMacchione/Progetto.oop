package it.univpm.twitterProject.service.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe per eseguire un parsing generico
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class parser {

	/**
	 * Esegue il parser su una stringa ottenendone un JsonObject
	 * 
	 * @param String: stringa su cui eseguire il parsing
	 * @return JSONObject: JsonObject ottenuto dalla stringa
	 * @throws ParseException. se ci sono errori durante il parsing
	 */

	public static JSONObject parserJO(String Json) throws ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(Json);
		JSONObject jO = (JSONObject) obj;
		return jO;
	}
}