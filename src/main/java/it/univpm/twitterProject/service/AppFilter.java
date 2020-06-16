package it.univpm.twitterProject.service;

import java.util.ArrayList;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.utils.filter.Filter;
import it.univpm.twitterProject.model.Tweet;

public class AppFilter {

	private ArrayList<Tweet> FilteredTweet = new ArrayList<Tweet>();

	public ArrayList<Tweet> Filtring(Filter f) {
		for (Tweet t : StartClass.getAllTweet()) {
			if (f.filter(t))
				FilteredTweet.add(t);
		}
		return FilteredTweet;
	}

	public ArrayList<Tweet> getFilteredTweet() {
		return FilteredTweet;
	}
}