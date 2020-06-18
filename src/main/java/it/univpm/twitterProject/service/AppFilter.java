package it.univpm.twitterProject.service;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.exception.TweetsNotFoundException;
import it.univpm.twitterProject.utils.filter.Filter;
import it.univpm.twitterProject.model.Tweet;

/**
 * Classe che applica il filtro sull'array
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class AppFilter {

	private ArrayList<Tweet> FilteredTweet = new ArrayList<Tweet>();

	/**
	 * Metodo che applica il filtro
	 * 
	 * @param Filter: l'oggeto che implementa l'interfaccia filter
	 * @return ArrayList: l'arraylist dei tweet filtrati
	 * @throws TweetsNotFoundException: se dopo il filtraggio l'array è vuoto
	 */

	public ArrayList<Tweet> Filtring(Filter f) throws TweetsNotFoundException {
		for (Tweet t : StartClass.getAllTweet()) {
			if (f.filter(t))
				FilteredTweet.add(t);
		}
		if (FilteredTweet.size() == 0) {
			throw new TweetsNotFoundException("L'array filtrato non ha elementi");
		}
		return FilteredTweet;
	}

	/**
	 * Getter dell'array filtrato come jsonObject
	 * 
	 * @return il JsonObject contenete l'array filtrato
	 */
	public JSONObject getAllFilteredTweetJO() {
		JSONObject ob = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Tweet t : FilteredTweet) {
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
		ob.put("Tutti i tweet filtrati", arr);
		return ob;
	}

	public ArrayList<Tweet> getFilteredTweet() {
		return FilteredTweet;
	}
}