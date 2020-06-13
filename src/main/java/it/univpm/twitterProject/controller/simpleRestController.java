package it.univpm.twitterProject.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.twitterProject.model.City;
import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.AppFilter;
import it.univpm.twitterProject.service.Distanza;
import it.univpm.twitterProject.filter.FilterDistCap;

@RestController
public class simpleRestController {

	@GetMapping("/getAllTweet")
	public JSONObject getData() {
		return StartClass.getAllTweet();

	}

	@PostMapping("/getFilteredTweets")
	public JSONObject getTweets(@RequestBody JSONObject coor) {
		AppFilter af = new AppFilter();
		String city = (String) coor.get("city");
		Integer dist = (Integer) coor.get("dist");
		FilterDistCap f = new FilterDistCap(city, dist);
		af.Filtring(f);
		return af.getFilteredTweet();
	}

	@PostMapping("/getStats")
	public JSONArray getStats(@RequestBody JSONObject dist) {
		JSONArray statCity = new JSONArray();
		Distanza d = new Distanza();
		for (City c : StartClass.AllCity) {
			JSONObject obj = new JSONObject();
			obj.put("città", c.getName());
			obj.put("n° tweet", 0);
			for (Tweet t : StartClass.AllTweet) {
					if (d.CalcDist(c.getCoordinates(), t.getGeo()) < (Integer) dist.get("dist")) {
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
