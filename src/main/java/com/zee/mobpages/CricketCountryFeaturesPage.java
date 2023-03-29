package com.zee.mobpages;

import org.openqa.selenium.By;

import com.zee.constants.Constants;

public class CricketCountryFeaturesPage extends GenericMobPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateFeaturesOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("articles") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty() && !driver.findElements(By.xpath(switchDynamicXpath(BREADCRUMB, "Features"))).isEmpty();
	}
}
