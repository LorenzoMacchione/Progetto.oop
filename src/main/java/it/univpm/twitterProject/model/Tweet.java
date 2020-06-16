package it.univpm.twitterProject.model;

/**Classe i cui oggetti contengono i dati di un tweet
 * scaricato da url
 * @author Lorenzo Macchione
 * @author Donato Mariano
*/

public class Tweet {

	private long id;
	private String name;
	private String screen_name;
	private String text;
	private String created;
	private long followers;
	private Coord geo;

	public Tweet(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Tweet() {
		super();
	}

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

	public long getFollowers() {
		return followers;
	}

	public void setFollowers(long i) {
		this.followers = i;
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

	public Coord getGeo() {
		return geo;
	}

	public double getLat() {
		return geo.getLat();
	}

	public double getLon() {
		return geo.getLon();
	}

	public void setGeo(Coord geo) {
		this.geo = geo;
	}
}