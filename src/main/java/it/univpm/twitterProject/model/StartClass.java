package it.univpm.twitterProject.model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class StartClass {
	
	private static  ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	
	
	
	public static String fillTweets () {
		String data = "";
		try {
		String url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/search/tweets.json?q=terremoto&count=100"; 
		String fileName = "";
		URLConnection openConnection = new URL(url).openConnection();
		InputStream in = openConnection.getInputStream();
		
		 
		 String line = "";
		 try {
		   InputStreamReader inR = new InputStreamReader( in );
		   BufferedReader buf = new BufferedReader( inR );
		  
		   while ( ( line = buf.readLine() ) != null ) {
			   data+= line;
		   }
		 } finally {
		   in.close();
		 }
		System.out.println( data );
		
		JSONObject obj = (JSONObject) JSONValue.parseWithException(data);	 //parse JSON Object
		System.out.println( "OK" );
		
		
		}
		catch (Exception e) {}
		return data;
		
	}
	
	public static ArrayList<Tweet> getTweets() {
		return tweets;
	}
	
}
