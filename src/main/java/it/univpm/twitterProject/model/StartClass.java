package it.univpm.twitterProject.model;

import java.util.ArrayList;

public class StartClass {
	
	private static  ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	public static ArrayList<Tweet> getTweets() {
		tweets.add(new Tweet("id", "testo", "località"));
		return tweets;
	}
	
}
