package com.zee.mobpages;

import org.openqa.selenium.By;

import com.zee.constants.Constants;

public class CricketCountryT20WorldCup extends GenericMobPage{

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateT20WorldCupOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("t20-world-cup-2022") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty() && !driver.findElements(By.xpath(switchDynamicXpath(BREADCRUMB, Constants.T20_WORLD_CUP))).isEmpty();
	}
	
	public boolean validateT20WorldCupScheduleOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("t20-world-cup-2022-full-schedule") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty() && !driver.findElements(By.xpath(switchDynamicXpath(BREADCRUMB, Constants.T_20_WORLD_CUP_SCHEDULE))).isEmpty();
	}
}
