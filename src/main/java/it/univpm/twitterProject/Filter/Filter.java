package it.univpm.twitterProject.filter;

import it.univpm.twitterProject.model.Tweet;

public interface Filter {

	public boolean filter(Tweet t);

}
