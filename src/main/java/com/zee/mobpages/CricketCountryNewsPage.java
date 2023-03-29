package com.zee.mobpages;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.zee.actions.MobUIAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.manager.MobDriverManager;
import com.zee.utils.TestLogger;

public class CricketCountryNewsPage extends GenericMobPage{
	
	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validateNewsPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		TestLogger.getInstance().info("The Current URL is : " + childPageURL);
		return childPageURL.contains("news") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty() && !driver.findElements(By.xpath(switchDynamicXpath(BREADCRUMB, Constants.NEWS))).isEmpty();
	}
	
}
