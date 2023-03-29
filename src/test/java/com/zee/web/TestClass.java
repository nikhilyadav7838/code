package com.zee.web;

import java.io.IOException;
import java.io.StringReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class TestClass {

	public static void main(String[] args) throws IOException {
		String url = "https://zeenews.india.com/football/fifa-world-cup-opening-ceremony-bts-jungkook-to-perform-when-where-to-watch-who-are-performers-at-opening-ceremony-in-qatar-2022-2537371.html/amp";
		org.jsoup.nodes.Document document = Jsoup.connect(url).get();
		Elements links = document.select("a[href]");  
		links.addAll(document.select("script[src]"));
//		links = document.select("a[href]"); 
		System.out.println("Size of the List : " + links.size());
        for (Element link : links) {  
        	if(link.attr("src").startsWith("https") || link.attr("href").startsWith("https")) {
        		if(link.attr("src").isEmpty())
            		System.out.println("\nlink : " + link.attr("href"));
            	else
            		System.out.println("\nlink : " + link.attr("src"));
        	}
        }  
		
	}
	
	private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try  
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
}
