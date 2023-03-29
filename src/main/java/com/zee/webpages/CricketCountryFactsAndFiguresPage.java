package com.zee.webpages;

public class CricketCountryFactsAndFiguresPage extends GenericWebPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateFactsAndFigurePageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("facts-and-figures");
	}
}
