package it.univpm.twitterProject.database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.exception.TweetsNotFoundException;
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

/**
 * Classe che gestisce il dataset
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

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
		AllMetadata.add(new Metadata("text", "String", "Testo del tweet"));
		AllMetadata.add(new Metadata("day", "int", "Numero del giorno nel mese"));
		AllMetadata.add(new Metadata("month", "int", "Numero del mese"));
		AllMetadata.add(new Metadata("year", "int", "Anno"));
		AllMetadata.add(new Metadata("hour", "int", "ora"));
		AllMetadata.add(new Metadata("minute", "int", "Minuto"));
		AllMetadata.add(new Metadata("followers", "long", "Numero di followers dell'autore del tweet"));
		AllMetadata.add(new Metadata("lat", "double", "Latitudine corrispondente a dove è stato generato il tweet"));
		AllMetadata.add(new Metadata("lon", "double", "Longitudine corrispondente a dove è stato generato il tweet"));
		AllMetadata.add(new Metadata("geo", "Coord", "coordinate"));

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

	/**
	 * Getter che restituisce tutti i tweet come JsonObject
	 * 
	 * @return JsonObject contenente tutti i tweet
	 */

	public static JSONObject getAllTweetJO() {
		JSONObject ob = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Tweet t : AllTweet) {
			JSONObject obj = new JSONObject();
			obj.put("id", t.getId());
			obj.put("name", t.getName());
			obj.put("screen_name", t.getScreen_name());
			obj.put("text", t.getText());
			obj.put("day", t.getDay());
			obj.put("month", t.getMonth());
			obj.put("year", t.getYear());
			obj.put("hour", t.getHour());
			obj.put("minute", t.getMinute());
			obj.put("followers", t.getFollowers());
			obj.put("lat", t.getLat());
			obj.put("lon", t.getLon());

			arr.add(obj);
		}
		ob.put("Tutti i tweet", arr);
		return ob;
	}

	/**
	 * Getter che restituisce tutti i metadati come JsonObject
	 * 
	 * @return JsonObject contenente tutti i metadati
	 */

	public static JSONObject getAllMetadataJO() {
		JSONObject ob = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Metadata m : AllMetadata) {
			JSONObject obj = new JSONObject();
			obj.put("alias", m.getAlias());
			obj.put("type", m.getType());
			obj.put("sourcefield", m.getSourceField());

			arr.add(obj);
		}
		ob.put("Tutti i metadata", arr);
		return ob;
	}

	/**
	 * Getter che restituisce tutte le citta' come JsonObject
	 * 
	 * @return JsonObject contenente tutte le città
	 */

	public static JSONObject getAllCityJO() {
		JSONObject ob = new JSONObject();
		ob.putAll(AllCity);
		return ob;
	}

	/**
	 * Inizializza i tweet richiamando il parser
	 * 
	 * @throws ParseException se ci sono errori durante il parsing
	 */

	public static void setAllTweet() throws ParseException {
		parserTweet p = new parserTweet();
		AllTweet = p.parsing(downloadTweets());
	}

	/**
	 * Inizializza i tweet con argomento e quantita' a scelta richiamando il parser
	 * 
	 * @param arg Argomento dei tweet su cui si vuole lavorare (default:terremoto) 
	 * @param qt Quantita' di tweet su cui si vuole lavorare (default: 100)
	 * @throws ParseException se ci sono errori durante il parsing
	 * @throws TweetsNotFoundException se non ci sono tweet sull'argomento
	 */

	public static void setAllTweet(String arg, int qt) throws ParseException, TweetsNotFoundException {
		parserTweet p = new parserTweet();
		AllTweet = p.parsing(downloadTweets(arg, qt));
		if (AllTweet.size() == 0) {
			throw new TweetsNotFoundException("Non ho trovato Tweet");
		}
	}

	/**
	 * Scarica l'elenco dei tweet
	 * 
	 * @return Una stringa rapresentante in jsonobject i tweet
	 */

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

	/**
	 * Scarica l'elenco dei tweet con argomento e quantita' a scelta
	 * 
	 * @param arg Argomento dei tweet su cui si vuole lavorare (default:terremoto)
	 * @param qt Quantita' di tweet su cui si vuole lavorare (default: 100) 
	 * @return Una stringa rapresentante in jsonobject i tweet
	 */

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

	/**
	 * legge l'elenco delle citta'
	 * 
	 * @return Una stringa rapresentante in jsonobject le citta'
	 */

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
}