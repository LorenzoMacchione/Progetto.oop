package it.univpm.twitterProject.service.parser;

//import java.util.ArrayList;
//import org.json.simple.JSONArray;
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
	
	/*
	public ArrayList<T>  parserGen (String data) throws ParseException {
		JSONParser parser = new JSONParser();
		ArrayList<T> tweetsList = new ArrayList<T>();
		Object obj = parser.parse(data);
		JSONObject jO = (JSONObject) obj;
		JSONArray ar = (JSONArray) jO.get("statuses");
		
		for (Object o : ar) {
			Tweet tweet = new Tweet();
			tweet.setId((Long) ((JSONObject) o).get("id"));
			tweet.setText((String) ((JSONObject) o).get("text"));
			jO = (JSONObject) ((JSONObject) o).get("geo");
			if (jO != null) {
				JSONArray ar1 = (JSONArray) jO.get("coordinates");
				Double a1 = (Double) ar1.get(0);
				Double a2 = (Double) ar1.get(1);
				tweet.setGeo(a1, a2);
				tweetsList.add(T);
			} else {
				
				Double a1 = gen.nextDouble()*10+36;
				Double a2 = gen.nextDouble()*9+7;
				tweet.setGeo(a1, a2);
				tweetsList.add(tweet);
			}
		}

		return tweetsList;
	}
		*/


	} 

