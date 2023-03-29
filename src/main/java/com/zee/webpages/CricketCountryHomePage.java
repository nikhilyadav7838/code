package com.zee.webpages;

import org.openqa.selenium.By;

public class CricketCountryHomePage extends GenericWebPage{
	
	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateHomePageOpened(String homePageURL) {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains(homePageURL) && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
