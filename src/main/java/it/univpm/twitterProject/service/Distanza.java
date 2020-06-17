package it.univpm.twitterProject.service;

import it.univpm.twitterProject.model.Coord;

/** Classe che permette di calcolare la distanza tra due coordinate
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class Distanza {

	private double R = 6371;

	
	/** Calcola la distanza tra due coordinate
	 * 
	 * @param Coord: coordinate del primo punto
	 * @param Coord: coordinate del secondo punto
	 * @return double: distanza tra i due punti 
	*/
	public double CalcDist(Coord coord1, Coord coord2) {

		double[] cCityrad = new double[2];
		double[] cTweetrad = new double[2];
		double fi;
		double p, d;

		// Converte i gradi in radianti
		cCityrad[0] = coord1.getLat() * Math.PI / 180;
		cCityrad[1] = coord1.getLon() * Math.PI / 180;
		cTweetrad[0] = coord2.getLat() * Math.PI / 180;
		cTweetrad[1] = coord2.getLon() * Math.PI / 180;

		// Calcola l'angolo compreso fi
		fi = Math.abs(cCityrad[1] - cTweetrad[1]);

		// Calcola il terzo lato del triangolo sferico
		p = Math.acos(Math.sin(cTweetrad[0]) * Math.sin(cCityrad[0])
				+ Math.cos(cTweetrad[0]) * Math.cos(cCityrad[0]) * Math.cos(fi));

		// Calcola la distanza sulla superficie terrestre R = ~6371 km
		d = p * R;

		return d;
	}
}