package com.zee.mobpages;

import org.openqa.selenium.By;

public class CricketCountryMomentsOfArticlePage extends GenericMobPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateMomentsOfArticleOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("moments-in-history") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
