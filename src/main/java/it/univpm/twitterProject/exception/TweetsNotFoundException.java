package it.univpm.twitterProject.exception;

/**
 * Rappresenta un'eccezzione personalizzata
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class TweetsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TweetsNotFoundException(String text) {
		super(text);
	}
}
