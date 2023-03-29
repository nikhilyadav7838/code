package com.zee.webpages;

import org.openqa.selenium.By;

public class ZeeNewsIndiaPage extends GenericWebPage {
	
	public String getCrawlerMenuForZeeNews() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public boolean validateIndiaPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("india") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
}
