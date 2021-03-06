package it.univpm.twitterProject.service.parser;

import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.model.Tweet;

/**
 * Classe per eseguire il parsing dei tweet
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class parserTweet extends parser {

	/**
	 * Metodo che esegue il parsing
	 * 
	 * @param data stringa del JsonObject
	 * @return ArrayList rapresentante i tweet
	 * @throws ParseException se ci sono problemi durante il parsing
	 */

	public static ArrayList<Tweet> parsing(String data) throws ParseException {

		Random gen = new Random();
		ArrayList<Tweet> tweetsList = new ArrayList<Tweet>();
		JSONObject jObj = new JSONObject();
		jObj = parserJO(data);

		JSONArray ar = (JSONArray) jObj.get("statuses");
		for (Object o : ar) {
			Tweet tweet = new Tweet();
			tweet.setCreated((String) ((JSONObject) o).get("created_at"));
			tweet.setId((Long) ((JSONObject) o).get("id"));
			tweet.setText((String) ((JSONObject) o).get("text"));

			JSONObject j1 = new JSONObject();
			j1 = (JSONObject) ((JSONObject) o).get("user");
			if (j1 != null) {
				String nn = (String) ((JSONObject) j1).get("name");
				tweet.setName(nn);
				String cr = (String) ((JSONObject) j1).get("screen_name");
				tweet.setScreen_name(cr);
				Long i = (Long) ((JSONObject) j1).get("followers_count");
				tweet.setFollowers(i);
			}

			JSONObject jO = new JSONObject();
			jO = (JSONObject) ((JSONObject) o).get("geo");
			if (jO != null) {
				JSONArray ar1 = (JSONArray) jO.get("coordinates");
				Double a1 = (Double) ar1.get(0);
				Double a2 = (Double) ar1.get(1);
				Coord co = new Coord(a1, a2);
				tweet.setGeo(co);
				tweetsList.add(tweet);
			} else {
				Double a2 = 0.0;
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
				Coord co = new Coord(a1, a2);
				tweet.setGeo(co);
				tweetsList.add(tweet);
			}
		}
		return tweetsList;
	}
}
