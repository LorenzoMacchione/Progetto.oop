package it.univpm.twitterProject.utils.stats;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.model.Metadata;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.utils.filter.FilterDistCap;

/**
 * Rappresenta le statistiche relative a una citta'.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class StatCity implements Stat {

	private String name;
	private int qtTweet;
	private ArrayList<StatNumb<Tweet>> arSN = new ArrayList<StatNumb<Tweet>>();
	private ArrayList<Tweet> FilteredTweet = new ArrayList<Tweet>();

	public StatCity(ArrayList<Tweet> alt, double dist, String name) {
		this.name = name;
		FilterDistCap f = new FilterDistCap(name, dist);
		for (Tweet t : alt) {
			if (f.app(t.getGeo()))
				FilteredTweet.add(t);
		}
		qtTweet = FilteredTweet.size();
		for (Metadata m : StartClass.getAllMetadata()) {
			if (!(m.getType().equals("String") || m.getType().equals("Coord"))) {
				StatNumb<Tweet> sn = new StatNumb<Tweet>(FilteredTweet, m.getAlias());
				arSN.add(sn);
			}
		}
	}

	/**
	 * Metodo che restituisce le statistiche sotto forma di JSONObject.
	 * 
	 * @return JSONObject: contenente le statistiche
	 */

	@Override
	public JSONObject getStatJo() {
		JSONObject jO = new JSONObject();
		JSONArray ar = new JSONArray();
		jO.put("Quantità di tweet esaminati", qtTweet);
		for (StatNumb<Tweet> sn : arSN) {
			JSONObject japp = new JSONObject();
			japp = sn.getStatJo();
			japp.remove("Quantità di dati esaminati");
			ar.add(japp);
		}
		jO.put(name, ar);
		return jO;
	}
}