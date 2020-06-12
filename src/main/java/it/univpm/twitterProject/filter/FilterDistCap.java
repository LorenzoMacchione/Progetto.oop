package it.univpm.twitterProject.filter;

import it.univpm.twitterProject.model.City;
import it.univpm.twitterProject.model.StartClass;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.Distanza;

public class FilterDistCap implements Filter {
	
	private String città;
	private double range;
	
	public FilterDistCap(String città, double dist) {
		super();
		this.città = città;
		this.range = dist;
	}

	@Override
	public boolean filter(Tweet t) {
		City cit = new City();
		for (City c:StartClass.AllCity) {
			if (c.getName().contentEquals(città))  {
				cit=c;
			}
		}
		Distanza d = new Distanza();
		 double dist = d.CalcDist(cit.getCoordinates(), t.getGeo());
		if (dist<=range) {return true;}
		return false;
	}

}
