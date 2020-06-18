package it.univpm.twitterProject.exception;

/**
 * Rappresenta un'eccezione personalizzata che estende
 * DataIllegalArgumentException
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

public class IllegalCoordException extends DataIllegalArgumentException {

	private static final long serialVersionUID = 1L;

	IllegalCoordException(String text) {
		super(text);
	}

	static String t = "Non ammessi dati di tipo Coord per questo comando.";

	public IllegalCoordException() {
		super(t);
	}
}
