package it.univpm.twitterProject.exception;

public class FilterNotFoundException extends ClassNotFoundException {

	private static final long serialVersionUID = 1L;

	public FilterNotFoundException() {
		super();
	}

	public FilterNotFoundException(String text) {
		super(text);
	}
}
