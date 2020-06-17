package it.univpm.twitterProject.utils.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.exception.FilterNotFoundException;
import it.univpm.twitterProject.model.Test;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.FilterUtils;
import it.univpm.twitterProject.service.parser.parser;

public class GenericFilterTweet implements Filter {

	private ArrayList<Test> tests = new ArrayList<Test>();
	private Test singleTest;
	private boolean and = false;
	private boolean or = false;

	public GenericFilterTweet(String s) throws FilterNotFoundException {
		try {
			JSONObject jO = parser.parserJO(s);
			JSONArray ar = new JSONArray();

			if (jO.get("$and") != null) {
				and = true;
				ar = (JSONArray) jO.get("$and");
				for (Object o : ar) {
					JSONObject json = (JSONObject) o;
					tests.add(new Test(json));
				}

			} else {
				if (jO.get("$or") != null) {
					or = true;
					ar = (JSONArray) jO.get("$or");
					for (Object o : ar) {
						JSONObject json = (JSONObject) o;
						tests.add(new Test(json));
					}
				} else {
					singleTest = new Test(jO);
				}
			}
		} catch (ParseException e) {
			throw new FilterNotFoundException("Filter errato");
		}
	}

	public boolean and(Tweet g) {
		for (Test t : tests) {
			if (!tester(t, g)) {
				return false;
			}
		}
		return true;
	}

	public boolean or(Tweet g) {
		for (Test t : tests) {
			if (tester(t, g)) {
				return true;
			}
		}
		return false;
	}

	public boolean tester(Test t, Tweet tweet) {
		Method m = null;
		Object o = null;
		try {
			m = tweet.getClass().getMethod("get" + t.getArg().substring(0, 1).toUpperCase() + t.getArg().substring(1),
					null);
			o = m.invoke(tweet);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (t.getDescriptor() != "") {
			if (t.getDescriptor() == "$not")
				return !FilterUtils.check(o, t.getOperetor(), t.getObj());
			else if (t.getDescriptor() == "$in") {
				for (Object ob : (JSONArray) t.getObj()) {
					if (FilterUtils.check(o, t.getOperetor(), ob)) {
						return true;
					}
				}
				return false;
			} else if (t.getDescriptor() == "$nin") {
				for (Object ob : (JSONArray) t.getObj()) {
					if (FilterUtils.check(o, t.getOperetor(), ob)) {
						return false;
					}
				}
				return true;

			}

		} else
			return FilterUtils.check(o, t.getOperetor(), t.getObj());

		return false;
	}

	@Override
	public boolean filter(Tweet t) {
		// TODO Auto-generated method stub
		if (and) {
			return and(t);
		} else if (or) {
			return or(t);
		} else
			return tester(singleTest, t);
	}
}
