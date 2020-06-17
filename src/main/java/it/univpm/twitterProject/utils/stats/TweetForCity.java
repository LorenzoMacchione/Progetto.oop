package it.univpm.twitterProject.utils.stats;

import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.Distanza;

/** Rappresenta il numero di tweet nelle varie città.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class TweetForCity {
	private int dist;
	private JSONArray statCity = new JSONArray();

	public TweetForCity(int dist) {
		super();
		this.dist = dist;
	}

	
	/** Metodo che calcola la statistica.
	 * 
	 * @return JSONObject: contenente la statistica.
	*/
	
	public JSONArray AppStat() {
		Distanza d = new Distanza();
		for (Map.Entry<String, Coord> entry : StartClass.getAllCity().entrySet()) {
			JSONObject obj = new JSONObject();
			obj.put("città", entry.getKey());
			obj.put("n° tweet", 0);
			for (Tweet t : StartClass.getAllTweet()) {
				if (d.CalcDist(entry.getValue(), t.getGeo()) < dist) {
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