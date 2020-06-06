package it.univpm.twitterProject.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import it.univpm.twitterProject.service.jsonParser;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StartClass {
	
	private static ArrayList <Tweet> AllTweet;

	public static String downloadTweets() throws ParseException {

		String url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=terremoto&count=100";
		String data = "";
		String line = "";

		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
			} finally {
				in.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public static void setAllTweet () throws ParseException {
		
		AllTweet = jsonParser.parserTweet(StartClass.downloadTweets());
	}
	
	public static JSONObject getAllTweet () {
		JSONObject jO = new JSONObject ();
		JSONArray ar = new JSONArray ();
		for (Tweet t: AllTweet) {
		JSONObject jTweet = new JSONObject ();
		jTweet.put("id", t.getId());
		jTweet.put("text", t.getText());
		jTweet.put("geo", t.getGeo());
		ar.add(jTweet);
		}
		jO.put("Tweets", ar);
		return jO;
	}
	
}