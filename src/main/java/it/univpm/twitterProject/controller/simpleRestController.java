package it.univpm.twitterProject.controller;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.jsonParser;

@RestController
public class simpleRestController {

	@GetMapping("/getMetadata")
	public ArrayList<Tweet> getData() throws ParseException {
		ArrayList<Tweet> t = jsonParser.parserTweet(StartClass.downloadTweets());
		return t;
	}
}
