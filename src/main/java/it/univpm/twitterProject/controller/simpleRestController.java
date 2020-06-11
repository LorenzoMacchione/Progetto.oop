package it.univpm.twitterProject.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.ServFilter;
import it.univpm.twitterProject.service.parser.parserTweet;
import it.univpm.twitterProject.filter.Filter;

@RestController
public class simpleRestController {

	@GetMapping("/getAllTweet")
	public JSONObject getData() {
		return StartClass.getAllTweet();

	}
	
	@PostMapping("/getFilteredTweets")
	public ArrayList<Tweet> getTweets(@RequestBody Filter filtro) {
		return ServFilter.filtering(filtro, parserTweet.parsing(StartClass.downloadTweets()));
	}

}
