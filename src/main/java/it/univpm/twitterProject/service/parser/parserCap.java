package it.univpm.twitterProject.service.parser;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import it.univpm.twitterProject.model.City;

public class parserCap extends parser {

	public ArrayList<City> parsing(String data) throws ParseException {

		ArrayList<City> cityList = new ArrayList<City>();
		JSONObject jObj = new JSONObject();
		jObj = parserJO(data);

		JSONArray ar = (JSONArray) jObj.get("CapoluoghiDiRegione");
		for (Object o : ar) {
			double[] d = new double[2];
			d[0] = (Double) ((JSONObject) o).get("Latitudine");
			d[1] = (Double) ((JSONObject) o).get("Longitudine");
			String s = (String) ((JSONObject) o).get("Città");
			City c = new City(s, d);
			cityList.add(c);
		}
		return cityList;
	}
}