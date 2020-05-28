package it.univpm.twitterProject.model;

public class Tweet {
	
	private String user;
	private String text;
	private String geo;
	
	public Tweet(String user, String text, String geo) {
		super();
		this.user = user;
		this.text = text;
		this.geo = geo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}
	
	
	
	
	
}
