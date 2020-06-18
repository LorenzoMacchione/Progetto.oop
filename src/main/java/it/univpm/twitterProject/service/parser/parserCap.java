package it.univpm.twitterProject.service.parser;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.Coord;

/**
 * Classe per eseguire il parsing delle città
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class parserCap extends parser {

	/**
	 * Metodo che esegue il parsing
	 * 
	 * @param String: stringa del JsonObject
	 * @return HashMap: rapresentante le città con relative coordinate
	 * @throws ParserException: se ci sono problemi durante il parsing
	 */

	public HashMap<String, Coord> parsing(String data) throws ParseException {

		HashMap<String, Coord> City = new HashMap<String, Coord>();
		JSONObject jObj = new JSONObject();
		jObj = parserJO(data);

		JSONArray ar = (JSONArray) jObj.get("CapoluoghiDiRegione");
		for (Object o : ar) {
			double d1 = (Double) ((JSONObject) o).get("Latitudine");
			double d2 = (Double) ((JSONObject) o).get("Longitudine");
			String s = (String) ((JSONObject) o).get("Città");
			Coord coord = new Coord(d1, d2);
			City.put(s, coord);
		}
		return City;
	}
}