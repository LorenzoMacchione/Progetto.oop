package it.univpm.twitterProject.model;

/**
 * Classe i cui oggetti contengono i dati di un tweet scaricato da url
 *  
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class Tweet {

	private long id;
	private String name;
	private String screen_name;
	private String text;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
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

	public void setCreated(String created) {
		String s = created.substring(4, 7);
		switch (s) {
		case "Jan":
			month = 1;
			break;
		case "Feb":
			month = 2;
			break;
		case "Mar":
			month = 3;
			break;
		case "Apr":
			month = 4;
			break;
		case "May":
			month = 5;
			break;
		case "Jun":
			month = 6;
			break;
		case "Jul":
			month = 7;
			break;
		case "Aug":
			month = 8;
			break;
		case "Sep":
			month = 9;
			break;
		case "Oct":
			month = 10;
			break;
		case "Nov":
			month = 11;
			break;
		case "Dec":
			month = 12;
			break;
		}

		s = created.substring(8, 10);
		day = Integer.parseInt(s);

		s = created.substring(11, 13);
		hour = Integer.parseInt(s);

		s = created.substring(14, 16);
		minute = Integer.parseInt(s);

		s = created.substring(26, 30);
		year = Integer.parseInt(s);
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

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
}