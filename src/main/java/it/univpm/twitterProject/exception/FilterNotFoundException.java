package it.univpm.twitterProject.exception;

/**
 * Rappresenta un'eccezione personalizzata di tipo ClassNotFoundException
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class FilterNotFoundException extends ClassNotFoundException {

	private static final long serialVersionUID = 1L;

	public FilterNotFoundException() {
		super();
	}

	public FilterNotFoundException(String text) {
		super(text);
	}
}
