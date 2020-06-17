package it.univpm.twitterProject.service;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.utils.filter.Filter;
import it.univpm.twitterProject.model.Tweet;

public class AppFilter {

	private ArrayList<Tweet> FilteredTweet = new ArrayList<Tweet>();

	public ArrayList<Tweet> Filtring(Filter f) {
		for (Tweet t : StartClass.getAllTweet()) {
			if (f.filter(t))
				FilteredTweet.add(t);
		}
		return FilteredTweet;
	}

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