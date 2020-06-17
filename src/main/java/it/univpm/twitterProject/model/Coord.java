package it.univpm.twitterProject.model;




/** Classe che contiene le coordinate
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class Coord {
	private double lat;
	private double lon;

	public Coord(double lat, double lon) {
		super();
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
}