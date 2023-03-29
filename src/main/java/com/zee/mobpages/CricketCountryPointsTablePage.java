package com.zee.mobpages;

import org.openqa.selenium.By;

public class CricketCountryPointsTablePage extends GenericMobPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validatePointsTableOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("points-table") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty() && !driver.findElements(By.xpath(switchDynamicXpath(BREADCRUMB, "Points Table"))).isEmpty();
	}
}
