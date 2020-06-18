package it.univpm.twitterProject.utils.filter;

import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.exception.DataIllegalArgumentException;
import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.service.Distanza;


/** Rappresenta il filtro per la distanza da i capoluoghi di regione.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class FilterDistCap {

	private String city;
	private double range;

	public FilterDistCap(String city, double range) throws DataIllegalArgumentException {
		super();
		if (!StartClass.getAllCity().containsKey(city)) {
			city = city.substring(0, 1).toUpperCase() + city.substring(1);
			if (!StartClass.getAllCity().containsKey(city)) {
				throw new DataIllegalArgumentException("Città non presente tra i capoluoghi di regione");
			}
		}
		this.city = city;
		this.range = range;
	}

	/** Funzione che applica il filtro
	 * 
	 * @param tco coordinata tipo Coord da analizzare
	 * @return boolean risposta del filtro
	*/
	
	public boolean app(Coord tco) {
		Distanza d = new Distanza();
		double dist = d.CalcDist(StartClass.getAllCity().get(city), tco);
		if (dist <= range) {
			return true;
		}
		return false;
	}
}