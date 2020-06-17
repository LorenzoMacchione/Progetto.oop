package it.univpm.twitterProject;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.twitterProject.database.StartClass;

/**
 * All'avvio scarica il dataset e inizializza costanti
 * 
 * @author Lorenzo Macchione
 * @author Donato Mariano
 */


@SpringBootApplication
public class twitterProjectApplication {

	public static void main(String[] args) throws ParseException {
		StartClass.setAllCity();
		StartClass.setAllTweet();
		StartClass.setAllDescriptor();
		StartClass.setAllMetadata();
		StartClass.setAllOperetor();
		SpringApplication.run(twitterProjectApplication.class, args);
	}
}
