package com.zee.webpages;

import org.openqa.selenium.By;

public class CricketCountryMomentsOfArticlePage extends GenericWebPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateMomentsOfArticleOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("moments-in-history") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
