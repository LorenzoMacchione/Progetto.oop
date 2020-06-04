package it.univpm.twitterProject.service;

import java.io.Reader;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.model.Tweet;

public class jsonParser {

	public static ArrayList<Tweet> parserTweet() throws ParseException {

		JSONObject parser = StartClass.downloadTweets();
		ArrayList<Tweet> tweetsList = new ArrayList<Tweet>();

		for (int i = 0; i < parser.size(); i++) {

			Tweet tweet = new Tweet();
			JSONObject parsed = (JSONObject) JSONValue.parse((Reader) parser.get(i));

			tweet.setId((Long) parsed.get("id"));
			tweet.setText((String) parsed.get("text"));

			tweetsList.add(tweet);
		}

		return tweetsList;
	}

}
