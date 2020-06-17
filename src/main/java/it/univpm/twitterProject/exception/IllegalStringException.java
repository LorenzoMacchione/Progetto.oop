package it.univpm.twitterProject.exception;

public class IllegalStringException extends DataIllegalArgumentException {

	private static final long serialVersionUID = 1L;
	static String t = "Non ammessi dati di tipo stringa per questo comando.";

	public IllegalStringException() {
		super(t);
	}

	IllegalStringException(String text) {
		super(text);
	}
}
