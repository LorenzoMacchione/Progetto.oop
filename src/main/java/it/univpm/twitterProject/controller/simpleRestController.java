package it.univpm.twitterProject.controller;

import java.util.ArrayList;


import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.twitterProject.model.Tweet;

import it.univpm.twitterProject.model.StartClass;

@RestController
public class simpleRestController {
	
 @GetMapping("/getMetadata")
 public String get() {
	 //ArrayList<Tweet> c = new ArrayList<Tweet>();
	 String c = StartClass.fillTweets();	 
	//c=StartClass.getTweets();
	 return c;
 }
}
