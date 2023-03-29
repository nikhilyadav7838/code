package com.zee.webpages;

import org.openqa.selenium.By;

public class CricketCountrySeriesPage extends GenericWebPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateSeriesOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("series") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
