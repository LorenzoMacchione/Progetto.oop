package it.univpm.twitterProject.model;

public class Tweet {

	private Long id;
	private String text;

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

}
