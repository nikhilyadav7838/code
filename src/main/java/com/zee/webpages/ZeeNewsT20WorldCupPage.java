package com.zee.webpages;

import org.openqa.selenium.By;

public class ZeeNewsT20WorldCupPage extends GenericWebPage {

	public String getCrawlerMenuForZeeNews() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public boolean validateT20WorldCupPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("t20-world-cup") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
