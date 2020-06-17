package it.univpm.twitterProject.utils.filter;

import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.service.Distanza;

/** Rappresenta il filtro per la distanza tra rispetto a un punto.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class FilterDistPoint {
	private Coord point;
	private double range;

	public FilterDistPoint(double range, double lat, double lon) {
		this.range = range;
		point = new Coord(lat, lon);
	}

	
	/** Funzione che applica il filtro
	 * 
	 * @param Coord: coordinata da analizzare
	 * @return boolean: risposta del filtro
	*/
	
	public boolean app(Coord tco) {
		Distanza d = new Distanza();
		double dist = d.CalcDist(point, tco);
		if (dist <= range) {
			return true;
		}
		return false;
	}
}
