package it.univpm.twitterProject.service.parser;

import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.Tweet;

public class parserTweet extends parser {

	public static ArrayList<Tweet> parsing(String data) {

		Random gen = new Random();
		ArrayList<Tweet> tweetsList = new ArrayList<Tweet>();
		JSONObject jObj = new JSONObject();

		try {
			jObj = parserJO(data);
		} catch (ParseException e) {
		}
		;

		JSONArray ar = (JSONArray) jObj.get("statuses");

		for (Object o : ar) {
			Tweet tweet = new Tweet();
			tweet.setId((Long) ((JSONObject) o).get("id"));
			tweet.setText((String) ((JSONObject) o).get("text"));
			JSONObject jO = new JSONObject();
			jO = (JSONObject) ((JSONObject) o).get("geo");

			if (jO != null) {
				JSONArray ar1 = (JSONArray) jO.get("coordinates");
				Double a1 = (Double) ar1.get(0);
				Double a2 = (Double) ar1.get(1);
				tweet.setGeo(a1, a2);
				tweetsList.add(tweet);

			} else {
				Double a2 = 36.7;
				Double a1 = gen.nextDouble() * 10 + 36.7;
				if (a1 < 38.2) {
					a2 = gen.nextDouble() * 3 + 12.5;
				} else {
					if (a1 < 40.6) {
						if (gen.nextInt(10) < 3) {
							a2 = gen.nextDouble() * 1 + 8.46;
						} else {
							a2 = gen.nextDouble() * 3 + 15.5;
						}
					} else {
						if (a1 < 41.2) {
							if (gen.nextInt(10) < 3) {
								a2 = gen.nextDouble() * 1 + 8.46;
							} else {
								a2 = gen.nextDouble() * 4 + 12.9;
							}
						} else {
							if (a1 < 43.6) {
								a2 = gen.nextDouble() * 4 + 10.9;
							} else {
								if (a1 < 44.5) {
									a2 = gen.nextDouble() * 4 + 8.6;
								} else {
									a2 = gen.nextDouble() * 7 + 6.8;
								}
							}
						}
					}
				}
				tweet.setGeo(a1, a2);
				tweetsList.add(tweet);

			}
		}

		return tweetsList;
	}

}