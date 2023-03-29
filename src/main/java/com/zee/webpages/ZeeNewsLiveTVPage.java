package com.zee.webpages;

import org.openqa.selenium.By;

public class ZeeNewsLiveTVPage extends GenericWebPage {
	
	public String getCrawlerMenuForZeeNews() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public boolean validateLiveTVPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("live-tv") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
