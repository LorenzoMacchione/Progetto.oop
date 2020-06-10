package it.univpm.twitterProject.model;

public class Tweet {

	private Long id;
	private String text;
	private double[] geo = new double[2];

	public Tweet(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Tweet() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setGeo(double c1, double c2) {
		this.geo[0] = c1;
		this.geo[1] = c2;
	}

	public double[] getGeo() {
		return geo;
	}
	
}