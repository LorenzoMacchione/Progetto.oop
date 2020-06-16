package it.univpm.twitterProject.database;

import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.model.Metadata;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.parser.parserCap;
import it.univpm.twitterProject.service.parser.parserTweet;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class StartClass {

	private static ArrayList<Tweet> AllTweet;
	private static ArrayList<Metadata> AllMetadata = new ArrayList<Metadata>();
	private static ArrayList<String> AllDescriptor = new ArrayList<String>();
	private static ArrayList<String> AllOperetor = new ArrayList<String>();
	private static HashMap<String, Coord> AllCity;

	public static ArrayList<String> getAllDescriptor() {
		return AllDescriptor;
	}

	public static void setAllDescriptor() {
		AllDescriptor.add("$not");
		AllDescriptor.add("$in");
		AllDescriptor.add("$ni");
	}

	public static ArrayList<String> getAllOperetor() {
		return AllOperetor;
	}

	public static void setAllOperetor() {
		AllOperetor.add("$gt");
		AllOperetor.add("$gte");
		AllOperetor.add("$lt");
		AllOperetor.add("$lte");
		AllOperetor.add("$bt");
		AllOperetor.add("$cc");
		AllOperetor.add("$cp");
	}

	public static ArrayList<Metadata> getAllMetadata() {
		return AllMetadata;
	}

	public static void setAllMetadata() {
		AllMetadata.add(new Metadata("id", "long", "Codice identificativo dell'autore del tweet"));
		AllMetadata.add(new Metadata("name", "String", "Nome dell'autore del tweet"));
		AllMetadata.add(new Metadata("screen_name", "String", "Nickname dell'autore del tweet"));
		AllMetadata.add(new Metadata("text", "String", ""));
		AllMetadata.add(new Metadata("created", "String", "Data e ora di crrezione del tweet"));
		AllMetadata.add(new Metadata("followers", "long", "Numero di followers dell'autore del tweet"));
		AllMetadata.add(new Metadata("lat", "double", "Latitudine corrispondente a dove è stato generato il tweet"));
		AllMetadata.add(new Metadata("lon", "double", "Longitudine corrispondente a dove è stato generato il tweet"));
		AllMetadata.add(new Metadata("geo","Coord","coordinate"));

	}

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

	public static String downloadTweets(String arg, int qt) {

		String url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=" + arg
				+ "&count=" + qt + "&lang=it";
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

	public static void setAllTweet(String arg, int qt) throws ParseException {
		parserTweet p = new parserTweet();
		AllTweet = p.parsing(downloadTweets(arg, qt));
	}

	public static void setAllCity() throws ParseException {
		parserCap p = new parserCap();
		AllCity = p.parsing(downloadCity());
	}
	
	

	public static ArrayList<Tweet> getAllTweet() {
		return AllTweet;
	}

	public static HashMap<String, Coord> getAllCity() {
		return AllCity;
	}
}