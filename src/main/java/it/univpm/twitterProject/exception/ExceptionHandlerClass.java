package it.univpm.twitterProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe che gestiti gli errori generati da eventuali chiamate errate da parte
 * del Client.
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */

@ControllerAdvice
public class ExceptionHandlerClass {

	/**
	 * Risponde quando viene lanciato l'errore DataIllegalArgumentException
	 * restituendo le informazioni sull'errore, con stato BAD_REQUEST.
	 * 
	 * @return ResponseEntity del getClass e getMessage dell'eccezione
	 */

	@ExceptionHandler(value = { DataIllegalArgumentException.class })
	public ResponseEntity<Object> handleDataIllegalArgumentException(DataIllegalArgumentException e) {

		return new ResponseEntity<>(e.getClass().getCanonicalName() + ": " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Risponde quando viene lanciato l'errore FilterNotFoundException restituendo
	 * le informazioni sull'errore, con stato BAD_REQUEST.
	 * 
	 * @return ResponseEntity del getClass e getMessage dell'eccezione
	 */

	@ExceptionHandler(value = { FilterNotFoundException.class })
	public ResponseEntity<Object> handleFilterNotFoundException(FilterNotFoundException e) {

		return new ResponseEntity<>(e.getClass().getCanonicalName() + ": " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Risponde quando viene lanciato l'errore IllegalCoordException restituendo le
	 * informazioni sull'errore, con stato BAD_REQUEST.
	 * 
	 * @return ResponseEntity del getClass e getMessage dell'eccezione
	 */

	@ExceptionHandler(value = { IllegalCoordException.class })
	public ResponseEntity<Object> handleIllegalCoordException(IllegalCoordException e) {

		return new ResponseEntity<>(e.getClass().getCanonicalName() + ": " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Risponde quando viene lanciato l'errore IllegalStringException restituendo le
	 * informazioni sull'errore, con stato BAD_REQUEST.
	 * 
	 * @return ResponseEntity del getClass e getMessage dell'eccezione
	 */

	@ExceptionHandler(value = { IllegalStringException.class })
	public ResponseEntity<Object> handleIllegalStringException(IllegalStringException e) {

		return new ResponseEntity<>(e.getClass().getCanonicalName() + ": " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Risponde quando viene lanciato l'errore TweetsNotFoundException restituendo
	 * le informazioni sull'errore, con stato INTERNAL_SERVER_ERROR.
	 * 
	 * @return ResponseEntity del getClass e getMessage dell'eccezione
	 */

	@ExceptionHandler(value = { TweetsNotFoundException.class })
	public ResponseEntity<Object> handleTweetsNotFoundException(TweetsNotFoundException e) {

		return new ResponseEntity<>(e.getClass().getCanonicalName() + ": " + e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}