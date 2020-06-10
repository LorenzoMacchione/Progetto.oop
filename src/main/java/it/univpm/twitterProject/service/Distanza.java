package it.univpm.twitterProject.service;

public class Distanza {

	private double R = 6371;

	public double CalcDist(double[] cCity, double[] cTweet) {

		double[] cCityrad = new double[2];
		double[] cTweetrad = new double[2];
		double fi;
		double p, d;

		// Converte i gradi in radianti
		cCityrad[0] = cCity[0] * Math.PI / 180;
		cCityrad[1] = cCity[1] * Math.PI / 180;
		cTweetrad[0] = cTweet[0] * Math.PI / 180;
		cTweetrad[1] = cTweet[1] * Math.PI / 180;

		// Calcola l'angolo compreso fi
		fi = Math.abs(cCityrad[1] - cTweetrad[1]);

		// Calcola il terzo lato del triangolo sferico
		p = Math.acos(Math.sin(cTweetrad[0]) * Math.sin(cCityrad[0])
				+ Math.cos(cTweetrad[0]) * Math.cos(cCityrad[0]) * Math.cos(fi));

		// Calcola la distanza sulla superficie terrestre R = ~6371 km
		d = p * R;

		return (d);

	}
}