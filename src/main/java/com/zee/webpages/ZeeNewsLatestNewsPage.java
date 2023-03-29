package com.zee.webpages;

import org.openqa.selenium.By;

public class ZeeNewsLatestNewsPage extends GenericWebPage {

	public String getCrawlerMenuForZeeNews() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public boolean validateLatestNewsPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("latest-news") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
