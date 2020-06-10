package it.univpm.twitterProject;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.twitterProject.model.StartClass;

@SpringBootApplication
public class twitterProjectApplication {

	public static void main(String[] args) throws ParseException {

		StartClass.setAllCity();
		StartClass.setAllTweet();
		SpringApplication.run(twitterProjectApplication.class, args);

	}

}
