package it.univpm.twitterProject.service;

import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.Tweet;

public class jsonParser {

	public static ArrayList<Tweet> parserTweet(String data) throws ParseException {
		
		Random gen = new Random();
		JSONParser parser = new JSONParser();
		ArrayList<Tweet> tweetsList = new ArrayList<Tweet>();

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
				tweetsList.add(tweet);
			} else {
				
				Double a1 = gen.nextDouble()*10+36;
				Double a2 = gen.nextDouble()*5+7;
				tweet.setGeo(a1, a2);
				tweetsList.add(tweet);
			}
		}

		return tweetsList;
	}

}
