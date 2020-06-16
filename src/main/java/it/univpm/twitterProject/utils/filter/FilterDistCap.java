package it.univpm.twitterProject.utils.filter;

import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.Distanza;

public class FilterDistCap implements Filter {
	private String city;
	private double range;

	public FilterDistCap(String city, double dist) {
		super();
		this.city = city;
		this.range = dist;
	}

	@Override
	public boolean filter(Tweet t) {
		Coord co = StartClass.getAllCity().get(city);
		Distanza d = new Distanza();
		double dist = d.CalcDist(co, t.getGeo());
		if (dist <= range) {
			return true;
		}
		return false;
	}
}