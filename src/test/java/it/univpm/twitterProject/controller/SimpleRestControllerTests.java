package it.univpm.twitterProject.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import it.univpm.twitterProject.exception.DataIllegalArgumentException;

class SimpleRestControllerTests {
	
	private simpleRestController src = new simpleRestController();
	private JSONArray arrOK = new JSONArray();
	private JSONArray arrBAD = new JSONArray();
	private double iOK;
	private double iBAD;
	private String filterOK;
	private String filterBAD;
	private ResponseEntity<String> res1;
	private ResponseEntity<String> res2;
	private ResponseEntity<String> res3;
	
	@BeforeEach
	void setUp() throws Exception {
		filterOK = "{\"followers\":100}";
		filterBAD = "{\"follower\":100}";
		iOK = 100;
		iBAD = -1;
		arrOK.add("Ancona");
		arrBAD.add("Macerata");
		res1 = new ResponseEntity<String>("Nessun parametro trovato, array impostato a default", HttpStatus.OK);
		res2 = new ResponseEntity<String>("Array modificato", HttpStatus.OK);
		res3 = new ResponseEntity<String>("Qt deve essere maggiore di 0", HttpStatus.OK);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test1() {
		assertThrows(DataIllegalArgumentException.class, ()->src.stat(arrBAD, filterOK, iOK));
		assertThrows(DataIllegalArgumentException.class, ()->src.stat(arrOK, filterBAD, iOK));
		assertThrows(DataIllegalArgumentException.class, ()->src.stat(arrOK, filterOK, iBAD));
	}
	
	@Test
	void test2() {
		try {
			assertEquals(res1, src.setTweet("terremoto", 100));
			assertEquals(res2, src.setTweet("mare", 50));
			assertEquals(res3, src.setTweet("cielo", -100));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}