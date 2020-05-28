package it.univpm.twitterProject.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.model.Tweet;

@RestController
public class simpleRestController {
	
	@RequestMapping(value = "start", method = RequestMethod.GET)
	public ArrayList<Tweet> getMetadata(){
			return StartClass.getTweets();
		}	
}
