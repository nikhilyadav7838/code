package com.zee.mobpages;

import org.openqa.selenium.By;

public class CricketCountrySeriesPage extends GenericMobPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateSeriesOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("series") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
