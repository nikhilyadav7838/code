package com.zee.webpages;

import org.openqa.selenium.By;

public class ZeeNewsEntertainmentPage extends GenericWebPage {

	public String getCrawlerMenuForZeeNews() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public boolean validateEntertainmentPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("entertainment") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
