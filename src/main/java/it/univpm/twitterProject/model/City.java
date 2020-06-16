package it.univpm.twitterProject.model;

/**Classe i cui oggetti contengono i dati di una citta'
 * utilizzati per l'implementazione di geo nei tweet
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class City {
	private String Name;
	private double[] coordinates;

	public City() {
		super();
	}

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