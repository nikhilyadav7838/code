package com.zee.webpages;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.zee.actions.WebAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.manager.WebDriverManager;
import com.zee.utils.TestLogger;

public abstract class GenericWebPage {

	protected WebDriver driver;
	private int brokenLinksCounter;
	public static final String ARTICLE_LISTING_FOR_CRICKET_COUNTRY = "//div[@class='article-list']/a";
	public static final String ARTICLE_LISTING_FOR_ZEE_NEWS_INDIA = "//div[@class='section-tumbnail-container']/descendant::li/div[contains(@class,'news_description')]/a";
	public static final String ARTICLE_LISTING_FOR_INDIACOM = "//div[3]/div/article[5]/a";
	public static final String SRC_LINKS = "//*[@src]";
	public static final String HREF_LINKS = "//*[@href]";	
	public static final String CRAWLER_MENU_OF_CRICKETCOUNTRY = "(//div[@class='container']/descendant::a[contains(text(),'valueTobeReplace')])[1]";
	public static final String PARENT_MENU = "//div[@class='nav-menu']/descendant::span[text()='valueTobeReplace']/parent::a";
	public static final String INTERNAL_SERVER_ERROR = "//*[text()='Internal Server Error']";
	public static final String CRAWLER_MENU_OF_ZEENEWS = "(//div[@class='menucaterory_container']/descendant::a[contains(text(),'valueTobeReplace')])[1]";
	
	protected GenericWebPage() {
		if(this.driver == null)
			this.driver = WebDriverManager.getWebDriverManager().getDriver();
	}
	
	public String getParentMenuForZeeNewsIndia() {
		return PARENT_MENU;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForCricketCountry(XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(ARTICLE_LISTING_FOR_CRICKET_COUNTRY, Constants.HREF_TAG);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + mainURL);
					WebAction.getInstance().navigateToPage(mainURL);
					resources.addAll(getResourceURLListByAttribute(SRC_LINKS, Constants.SRC_TAG));
					resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG));
					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(mainURL);
					for (String resourceURL : resources) {
						checkStatusCodeAndResponseTime(resourceURL, entity);
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + ARTICLE_LISTING_FOR_CRICKET_COUNTRY + " or listing not visible.");
		}
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForZeeNewsIndia(XSSFWorkbook workbook, XSSFSheet sheet, boolean isAllResourceCheck) {
		brokenLinksCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(ARTICLE_LISTING_FOR_ZEE_NEWS_INDIA, Constants.HREF_TAG);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + mainURL);
					WebAction.getInstance().navigateToPage(mainURL);
					resources.addAll(getResourceURLListByAttribute(SRC_LINKS, Constants.SRC_TAG));
					if(isAllResourceCheck)
						resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG));
					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(mainURL);
					for (String resourceURL : resources) {
						checkStatusCodeAndResponseTime(resourceURL, entity);
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + ARTICLE_LISTING_FOR_ZEE_NEWS_INDIA + " or listing not visible.");
		}
		return list;
	}
	public List<BrokenURLsEntity> checkBrokenLinksForIndia(XSSFWorkbook workbook, XSSFSheet sheet, boolean isAllResourceCheck) {
		brokenLinksCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(ARTICLE_LISTING_FOR_INDIACOM, Constants.HREF_TAG);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + mainURL);
					WebAction.getInstance().navigateToPage(mainURL);
					resources.addAll(getResourceURLListByAttribute(SRC_LINKS, Constants.SRC_TAG));
					if(isAllResourceCheck)
						resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG));
					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(mainURL);
					for (String resourceURL : resources) {
						checkStatusCodeAndResponseTime(resourceURL, entity);
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + ARTICLE_LISTING_FOR_INDIACOM + " or listing not visible.");
		}
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForMainPage(XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> resources = new ArrayList();
		BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
//		resources.addAll(getResourceURLListByAttribute(SRC_LINKS, Constants.SRC_TAG));
		resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG));
		entity.setArticleURL(driver.getCurrentUrl());
		for (String resourceURL : resources) {
			checkStatusCodeAndResponseTime(resourceURL, entity);
		}
		list.add(entity);
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksofMainPageForZeeNewsIndia(XSSFWorkbook workbook, XSSFSheet sheet, boolean isAllResourceCheck) {
		brokenLinksCounter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> resources = new ArrayList();
		BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
		resources.addAll(getResourceURLListByAttribute(SRC_LINKS, Constants.SRC_TAG));
		if(isAllResourceCheck)
			resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG));
		entity.setArticleURL(driver.getCurrentUrl());
		for (String resourceURL : resources) {
			checkStatusCodeAndResponseTime(resourceURL, entity);
		}
		list.add(entity);
		return list;
	}
	
	public int getTotalBrokenLinks() {
		return brokenLinksCounter;
	}
	
	public List<String> getResourceURLListByAttribute(String locator, String attribute) {
		List<String> resources = new ArrayList();
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		for(WebElement element : elements) {
			resources.add(element.getAttribute(attribute).trim());
		}
		return resources;
	}
	
	public void checkStatusCodeAndResponseTime(String resourceURL, BrokenURLsEntity entity) {
		if(!(resourceURL.isEmpty() && resourceURL.isBlank()) && resourceURL.startsWith(Constants.PROTOCOL)) {
			TestLogger.getInstance().info("STEP : Validate the Resource URL : " + resourceURL);
			int statusCode = WebAction.getInstance().getStatusCodeOfURL(resourceURL);
			if(String.valueOf(statusCode).startsWith("4") || String.valueOf(statusCode).startsWith("5") || WebAction.getInstance().getResponseTime().startsWith("2")) {
				entity.setResourceURL(resourceURL);
				if(WebAction.getInstance().getResponseTime().startsWith("2") && (String.valueOf(statusCode).startsWith("4") || String.valueOf(statusCode).startsWith("5"))) {
					entity.setStatusCode(String.valueOf(statusCode) + " "  + Constants.ORANGE);
					entity.setResponseTime(WebAction.getInstance().getResponseTime() + Constants.SECONDS  + " " + Constants.RED);
				}else if(WebAction.getInstance().getResponseTime().startsWith("2") && String.valueOf(statusCode).startsWith("2")){
					entity.setStatusCode(String.valueOf(statusCode) + " ");
					entity.setResponseTime(WebAction.getInstance().getResponseTime() + Constants.SECONDS  + " " + Constants.RED);
				}else {
					entity.setStatusCode(String.valueOf(statusCode) + " " + Constants.ORANGE);
					entity.setResponseTime(WebAction.getInstance().getResponseTime() + Constants.SECONDS  + " ");
				}
				brokenLinksCounter++;
			}
		}
	}
}
