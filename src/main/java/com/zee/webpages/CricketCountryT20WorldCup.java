package com.zee.webpages;

import org.openqa.selenium.By;

public class CricketCountryT20WorldCup extends GenericWebPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateT20WorldCupOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("t20-world-cup-2022") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
	
	public boolean validateT20WorldCupScheduleOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("t20-world-cup-2022-full-schedule") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
