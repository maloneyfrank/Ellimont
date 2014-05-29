package com.frankmaloney.ellimont;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Synonym {
	public static String getSynonym(String word){
		
		ArrayList<String> synonyms = new ArrayList<String>();
		int wordChoice;
		try {
			 
			URL url = new URL("http://words.bighugelabs.com/api/2/6335633e3665700a8979976b16f63a0c/" + word + "/xml");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
	 
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
	 

			DocumentBuilderFactory builderFactory =
			        DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			try {
			    builder = builderFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
			    e.printStackTrace();  
			}
			
			   Document document = null;
			try {
				document =  builder.parse(conn.getInputStream());
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

				
			NodeList nodes =  document.getElementsByTagName("words");
			
			for(int i = 0; i < nodes.getLength(); i++){
				Node node = nodes.item(i);
				
				Element element = (Element) node;
				
				NodeList wordChoices = element.getElementsByTagName("w");
				for (int j = 0; j < wordChoices.getLength(); j++) {
				   synonyms.add(wordChoices.item(j).getTextContent());
				}
			}
			
			conn.disconnect();
	 
		  } catch (MalformedURLException e) {
	 
			e.printStackTrace();
	 
		  } catch (IOException e) {
	 
			e.printStackTrace();
	 
		  }
		Random rand = new Random();
		wordChoice = rand.nextInt(synonyms.size());
		
		return synonyms.get(wordChoice);
	}
	
}
