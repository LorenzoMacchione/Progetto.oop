package it.univpm.twitterProject.exception;

public class DataIllegalArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	DataIllegalArgumentException(String text) {
		super(text);
	}
}
