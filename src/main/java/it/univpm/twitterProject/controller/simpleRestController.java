package it.univpm.twitterProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.twitterProject.model.Coord;
import it.univpm.twitterProject.model.Metadata;
import it.univpm.twitterProject.model.Tweet;
import it.univpm.twitterProject.service.AppFilter;
import it.univpm.twitterProject.utils.stats.Stat;
import it.univpm.twitterProject.utils.stats.StatCity;
import it.univpm.twitterProject.utils.stats.StatNumb;
import it.univpm.twitterProject.utils.stats.TweetForCity;
import it.univpm.twitterProject.database.StartClass;
import it.univpm.twitterProject.utils.filter.Filter;
import it.univpm.twitterProject.utils.filter.FilterDistCap;
import it.univpm.twitterProject.utils.filter.GenericFilterTweet;

@RestController
public class simpleRestController {

	@GetMapping("/getAllTweet")
	public ArrayList<Tweet> getData() {
		return StartClass.getAllTweet();
	}

	@GetMapping("/getAllCity")
	public HashMap<String, Coord> getCity() {
		return StartClass.getAllCity();
	}
	
	@GetMapping("/getAllMetadata")
	public ArrayList<Metadata> getMetadata(){
		return StartClass.getAllMetadata();
	}

	@GetMapping("/setTweet")
	public ResponseEntity<String> setTweet(@RequestParam(name = "arg", defaultValue = "terremoto") String arg,
			@RequestParam(name = "qt", defaultValue = "100") int qt) throws ParseException {

		StartClass.setAllTweet(arg, qt);
		if(arg.equals("terremoto") && qt == 100) {
			return new ResponseEntity<String>("Nessun parametro trovato, array impostato a default", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Array modificato", HttpStatus.OK);
	}

	

	@GetMapping("/data")
	public ArrayList<Tweet> data (@RequestParam(name = "filter", defaultValue = "no filter") String f) throws ParseException {
		if (f.equals("no filter")) {return StartClass.getAllTweet();}
		AppFilter af = new AppFilter();
		GenericFilterTweet gft = new GenericFilterTweet(f);
		af.Filtring(gft);
		return af.getFilteredTweet();
	}

	
	
	@GetMapping("/Stat")
	public JSONArray stat(@RequestParam(name = "field") JSONArray field, @RequestParam(name = "filter", defaultValue = "no filter")String filter,@RequestParam(name = "dist", defaultValue = "-1")double dist) throws ParseException {
		ArrayList<Tweet> filteredTweet = new ArrayList<Tweet>();
		ArrayList <String> multiField = new ArrayList <String>(); 
		Stat stat;
		JSONArray out = new JSONArray();
		if (filter.equals("no filter")) {
		filteredTweet = StartClass.getAllTweet();
		}else 
		{
			AppFilter af = new AppFilter();
			GenericFilterTweet gft = new GenericFilterTweet(filter);
			af.Filtring(gft);
			filteredTweet =af.getFilteredTweet();
		}
		for (Object obj: field) {
			multiField.add((String) obj);
		}
		for(String s: multiField) {	
			if(StartClass.getAllCity().containsKey(s))
			{
				if (dist<0) {
					//aggiungere eccezione
					}
				
				stat = new StatCity(filteredTweet,dist,s);
			}else {
					stat = new StatNumb<Tweet>(filteredTweet,s);
			}
		out.add(stat.getStatJo());
		}
		
		return out;
	}
	
	
	@PostMapping("/getStats")
	public JSONArray getStats(@RequestBody JSONObject distanza) {
		TweetForCity tfc = new TweetForCity((int) distanza.get("dist"));
		return tfc.AppStat();
	}
}