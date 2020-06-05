package it.univpm.twitterProject.service;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.Tweet;

public class jsonParser {

	public static ArrayList<Tweet> parserTweet(String data) throws ParseException {

		JSONParser parser = new JSONParser();
		Tweet tweet = new Tweet();
		ArrayList<Tweet> tweetsList = new ArrayList<Tweet>();

		Object obj = parser.parse(data);
		JSONObject jO = (JSONObject) obj;
		JSONArray ar = (JSONArray) jO.get("statuses");

		for (Object o : ar) {

			tweet.setId((Long) ((JSONObject) o).get("id"));
			tweet.setText((String) ((JSONObject) o).get("text"));

			tweetsList.add(tweet);
		}

		return tweetsList;
	}

}
