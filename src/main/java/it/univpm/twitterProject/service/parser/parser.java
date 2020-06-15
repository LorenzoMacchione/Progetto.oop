package it.univpm.twitterProject.service.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class parser {

	
	public static JSONObject parserJO (String Json) throws ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(Json);
		JSONObject jO = (JSONObject) obj;
		return jO;
	}
	
} 

