package it.univpm.twitterProject.exception;

/**
 * Rappresenta un'eccezione personalizzata di tipo IllegalargumentException
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class DataIllegalArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public DataIllegalArgumentException(String text) {
		super(text);
	}
}
