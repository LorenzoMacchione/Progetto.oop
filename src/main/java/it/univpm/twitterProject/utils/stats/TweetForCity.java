package it.univpm.twitterProject.utils.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.model.City;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.Distanza;

public class TweetForCity {
	private int dist;
	private JSONArray statCity = new JSONArray();

	public TweetForCity(int dist) {
		super();
		this.dist = dist;
	}

	public JSONArray AppStat() {
		Distanza d = new Distanza();
		for (City c : StartClass.AllCity) {
			JSONObject obj = new JSONObject();
			obj.put("città", c.getName());
			obj.put("n° tweet", 0);
			for (Tweet t : StartClass.AllTweet) {
				if (d.CalcDist(c.getCoordinates(), t.getGeo()) < dist) {
					int i = (int) obj.get("n° tweet");
					i++;
					obj.put("n° tweet", i);
				}
			}
			statCity.add(obj);
		}
		return statCity;
	}
}
