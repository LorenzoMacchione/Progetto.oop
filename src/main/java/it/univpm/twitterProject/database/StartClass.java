package it.univpm.twitterProject.database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.City;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.parser.parserCap;
import it.univpm.twitterProject.service.parser.parserTweet;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StartClass {

	public static ArrayList<Tweet> AllTweet;
	public static ArrayList<City> AllCity;

	public static String downloadTweets() {

		String url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=terremoto&count=100&lang=it";
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

	public static String downloadCity() {

		String file = "src\\main\\java\\it\\univpm\\twitterProject\\database\\JSONCapRegioni.txt";
		String data = "";
		String line = "";

		try {
			FileInputStream fin = new FileInputStream(file);

			try {
				InputStreamReader inR = new InputStreamReader(fin);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
			} finally {
				fin.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void setAllTweet() throws ParseException {
		parserTweet p = new parserTweet();
		AllTweet = p.parsing(downloadTweets());
	}

	public static void setAllCity() throws ParseException {
		parserCap p = new parserCap();
		AllCity = p.parsing(downloadCity());
	}

	public static JSONObject getAllTweet() {
		JSONObject jO = new JSONObject();
		JSONArray ar = new JSONArray();
		for (Tweet t : AllTweet) {
			JSONObject jTweet = new JSONObject();
			jTweet.put("created_at", t.getCreated());
			jTweet.put("name", t.getName());
			jTweet.put("screen_name", t.getScreen_name());
			jTweet.put("id", t.getId());
			jTweet.put("text", t.getText());
			jTweet.put("geo", t.getGeo());
			ar.add(jTweet);
		}
		jO.put("Tweets", ar);
		return jO;
	}

}