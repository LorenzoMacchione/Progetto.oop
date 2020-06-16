package it.univpm.twitterProject.utils.filter;

import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.service.Distanza;

public class FilterDistPoint {
	private Coord point;
	private double range;
	
	public FilterDistPoint(double range, double lat, double lon) {
		this.range=range;
		point = new Coord(lat,lon);
	}

	public boolean app(Coord tco) {
		Distanza d = new Distanza();
		 double dist = d.CalcDist(point, tco);
		if (dist<=range) {return true;}
		return false;
	}
}
