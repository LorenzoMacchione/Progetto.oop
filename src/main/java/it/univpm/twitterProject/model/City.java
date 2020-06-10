package it.univpm.twitterProject.model;

public class City {
	private String Name;
	private double[] coordinates;
	
	public City(String name, double[] coordinates) {
		super();
		Name = name;
		this.coordinates = coordinates;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
