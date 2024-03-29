package com.zee.webpages;

import org.openqa.selenium.By;

public class CricketCountryFeaturesPage extends GenericWebPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateFeaturesOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("articles") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
