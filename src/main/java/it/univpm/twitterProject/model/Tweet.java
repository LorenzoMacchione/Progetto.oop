package it.univpm.twitterProject.model;

public class Tweet {

	private Long id;
	private String name;
	private String screen_name;
	private String text;
	private String created;
	private int followers;
	
	private double[] geo = new double[2];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

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