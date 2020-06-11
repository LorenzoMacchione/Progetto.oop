package it.univpm.twitterProject.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import it.univpm.twitterProject.filter.Filter;
import it.univpm.twitterProject.model.Tweet;

public class ServFilter {

	private final static String path = "it.univpm.twitterProject.filter.";

	public static Filter instanceFilter(String column, String operator, Object param)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

		Filter filtro;
		String filterName = new String("Filter" + column + operator);
		String ClassFilterName = path.concat(filterName);

		Class<?> cls = Class.forName(ClassFilterName);
		Constructor<?> ct = cls.getDeclaredConstructor(Object.class);
		filtro = (Filter) ct.newInstance(param);

		return filtro;
	}

	public static ArrayList<Tweet> filtering(Filter filtro, ArrayList<Tweet> tweetsList) {

		ArrayList<Tweet> filtredTweet = new ArrayList<Tweet>();

		for (Tweet t : tweetsList) {

			if (filtro.filter(t))
				filtredTweet.add(t);
		}

		return filtredTweet;

	}

}
