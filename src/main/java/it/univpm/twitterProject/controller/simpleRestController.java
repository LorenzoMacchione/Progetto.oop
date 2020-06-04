package it.univpm.twitterProject.controller;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.twitterProject.model.StartClass;

@RestController
public class simpleRestController {

@GetMapping("/getMetadata")
 public JSONObject getData() throws ParseException {
	 JSONObject t = StartClass.downloadTweets();
	 return t;
 }
}
