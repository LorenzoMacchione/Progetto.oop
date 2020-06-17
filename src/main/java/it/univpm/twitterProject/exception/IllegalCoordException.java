package it.univpm.twitterProject.exception;

public class IllegalCoordException extends DataIllegalArgumentException {
	
	
	private static final long serialVersionUID = 1L;
	IllegalCoordException(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	static String t="Non ammessi dati di tipo Coord per questo comando.";
	public IllegalCoordException() {
		super(t);
	}
}
