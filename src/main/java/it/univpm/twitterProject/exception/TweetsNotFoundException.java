package it.univpm.twitterProject.exception;

public class TweetsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TweetsNotFoundException(String text) {
		super(text);
	}
}
