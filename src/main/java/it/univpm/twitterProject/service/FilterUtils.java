
package it.univpm.twitterProject.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.exception.DataIllegalArgumentException;
import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.utils.filter.FilterDistCap;
import it.univpm.twitterProject.utils.filter.FilterDistPoint;


public class FilterUtils {
	public static boolean check(Object value, String operator, Object th) {
		if (th instanceof Number && value instanceof Number) {

			Double thC = ((Number) th).doubleValue();
			Double valuec = ((Number) value).doubleValue();
			 if (operator.equals("=="))
				return value.equals(th);
			 if (operator.equals("$gt"))
				return valuec > thC;
			 if (operator.equals("$gte"))
				return valuec >= thC;
			 if (operator.equals("$lt"))
				return valuec < thC;
			 if (operator.equals("$lte"))
				return valuec <= thC;

		} else if (th instanceof String && value instanceof String)
			return value.equals(th);
		
		else if(value instanceof Coord) {
			try {
			if (operator.equals("$cc")) {
				
			String city = (String) ((JSONObject) th).get("city");
			double range = (long) ((JSONObject) th).get("range");
			FilterDistCap fdc = new FilterDistCap(city,range);
			return fdc.app((Coord) value);
			
		}else if(operator.equals("$cp")){
			double range = (long) ((JSONObject) th).get("range");
			double lat = (long) ((JSONObject) th).get("lat");
			double lon = (long) ((JSONObject) th).get("lon");
			FilterDistPoint fdp = new FilterDistPoint(range,lat,lon);
			return fdp.app((Coord) value);}
			
		}catch(Exception e) {throw new DataIllegalArgumentException("Errore nell'immisione dei dati");} ;
		}
		else if (operator.equals("$bt")) {
			Long valuec = ((Number) value).longValue();
			Long[] thC1 = new Long[2];
					thC1[0]= (Long) ((JSONArray)th).get(0);
					thC1[1]= (Long) ((JSONArray)th).get(1);
			return thC1[0] < valuec && valuec < thC1[1];
		}
		return false;
	}
	
	
}
