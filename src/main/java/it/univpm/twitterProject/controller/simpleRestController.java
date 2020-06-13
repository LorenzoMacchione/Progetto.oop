package it.univpm.twitterProject.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.service.AppFilter;
import it.univpm.twitterProject.stats.TweetForCity;
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
		TweetForCity tfc = new TweetForCity((int) dist.get(dist));
		return tfc.AppStat();
	}
}