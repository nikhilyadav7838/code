package com.zee.webpages;

import org.openqa.selenium.By;

public class CricketCountryPointsTablePage extends GenericWebPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validatePointsTableOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("points-table") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
