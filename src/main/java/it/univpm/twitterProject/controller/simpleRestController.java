package it.univpm.twitterProject.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.twitterProject.model.StartClass;

@RestController
public class simpleRestController {

	@GetMapping("/getAllTweet")
	public JSONObject getData() {
		return StartClass.getAllTweet();

	}

}
