package com.zee.webpages;

import org.openqa.selenium.By;

public class ZeeNewsSportsPage extends GenericWebPage {

	public String getCrawlerMenuForZeeNews() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public boolean validateSportsPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("entertainment") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
