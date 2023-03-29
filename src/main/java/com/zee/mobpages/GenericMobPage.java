package com.zee.mobpages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zee.actions.MobUIAction;
import com.zee.actions.WebAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.manager.MobDriverManager;
import com.zee.manager.WebDriverManager;
import com.zee.utils.TestLogger;

import groovy.time.Duration;

public abstract class GenericMobPage {

	public WebDriver driver;
	private int brokenLinksCounter;
	private WebDriverWait wait;
	public static final String ARTICLE_LISTING = "//div[@class='article-list']/a";
	public static final String AMP_LOC = "//head/link[@rel='amphtml']";
	public static final String SRC_LINKS = "//*[@src]";
	public static final String HREF_LINKS = "//*[@href]";
	public static final String MOB_HREF_LINKS = "//link[@href]";
	public static final String MOB_SRC_LINKS = "//script[@src]";
	public static final String INTERNAL_SERVER_ERROR = "//*[text()='Internal Server Error']";
	private static final String LISTING_VIDEOS = "(//div[@class='livetv-morevideos']/descendant::h2/a)";
	public static final String BREADCRUMB = "(//li[@class='breadcrumb-item']/a[contains(text(),'valueTobeReplace')])[last()]";
	public static final String CRAWLER_MENU_OF_CRICKETCOUNTRY = "(//div[@class='container']/descendant::a[contains(text(),'valueTobeReplace')])[1]";
	public static final String CRAWLER_MENU_OF_ZEENEWS_INDIA = "(//div[@class='menucaterory_container']/descendant::a[contains(text(),'valueTobeReplace')])[1]";
	
	protected GenericMobPage() {
		if(this.driver == null)
			this.driver = MobDriverManager.mobDriverManager().getDriver();
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForCricketCountry(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(ARTICLE_LISTING, Constants.HREF_TAG, baseURL, false);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					String finalurl = mainURL;
					if(!mainURL.startsWith(Constants.PROTOCOL))
						finalurl = baseURL + mainURL;
					TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + finalurl);
					MobUIAction.getInstance().navigateToPage(finalurl);					
					resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG, baseURL, true));
					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(finalurl);
					driver.navigate().refresh();
					for (String resourceURL : resources) {
						if(!(resourceURL.isEmpty() && resourceURL.isBlank()) && resourceURL.startsWith(Constants.PROTOCOL)) {
							TestLogger.getInstance().info("STEP : Validate the Resource URL : " + resourceURL);
							int statusCode = MobUIAction.getInstance().getStatusCodeOfURL(resourceURL);
							TestLogger.getInstance().info("STEP : Validate the Status Code : " + statusCode);
							checkStatusCodeAndResponseTime(resourceURL, entity);
						}
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + ARTICLE_LISTING + " or listing not visible.");
		}
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForCricketCountryOfHindiVersion(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(ARTICLE_LISTING, Constants.HREF_TAG, baseURL, false);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					String finalurl = mainURL;
					if(mainURL.startsWith(Constants.HINDI_SITE_TAG)) {
						finalurl = baseURL.replace(Constants.HINDI_SITE_TAG, "") + mainURL;
					} else if(!mainURL.startsWith(Constants.PROTOCOL)) {
						finalurl = baseURL + mainURL;
					}
					TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + finalurl);
					MobUIAction.getInstance().navigateToPage(finalurl);					
					resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG, baseURL, true));
//					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(finalurl);
					driver.navigate().refresh();
					for (String resourceURL : resources) {
						if(!(resourceURL.isEmpty() && resourceURL.isBlank()) && resourceURL.startsWith(Constants.PROTOCOL) && !resourceURL.endsWith("void(0)")) {
							TestLogger.getInstance().info("STEP : Validate the Resource URL : " + resourceURL);
							int statusCode = MobUIAction.getInstance().getStatusCodeOfURL(resourceURL);
							checkStatusCodeAndResponseTime(resourceURL, entity);
						}
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + ARTICLE_LISTING + " or listing not visible.");
		}
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForMainPageOfCricketCountry(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> resources = new ArrayList();
		BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
		resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG, baseURL, true));
		entity.setArticleURL(driver.getCurrentUrl());
		for (String resourceURL : resources) {
			if(!(resourceURL.endsWith(Constants.HINDI_SITE_TAG) || resourceURL.endsWith("/hi/") || resourceURL.endsWith("void(0)"))) {
				checkStatusCodeAndResponseTime(resourceURL, entity);
			}
		}
		list.add(entity);
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForMainPageOfZeeNewsIndia(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> resources = new ArrayList();
		BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
		resources.addAll(getResourceURLListByAttribute(MOB_HREF_LINKS, Constants.HREF_TAG, baseURL, true));
		entity.setArticleURL(driver.getCurrentUrl());
		for (String resourceURL : resources) {
			checkStatusCodeAndResponseTime(resourceURL, entity);
		}
		list.add(entity);
		return list;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForZeeNewsIndia(List<String> mainURLs, String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					String finalurl = mainURL;
					if(!mainURL.startsWith(Constants.PROTOCOL))
						finalurl = baseURL + mainURL;
					TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + finalurl);
					MobUIAction.getInstance().navigateToPage(finalurl);					
					resources.addAll(getResourceURLListByAttribute(MOB_HREF_LINKS, Constants.HREF_TAG, baseURL, true));
					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(finalurl);
					driver.navigate().refresh();
					for (String resourceURL : resources) {
						if(!(resourceURL.isEmpty() && resourceURL.isBlank()) && resourceURL.startsWith(Constants.PROTOCOL)) {
							checkStatusCodeAndResponseTime(resourceURL, entity);
						}
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + LISTING_VIDEOS + " or listing not visible.");
		}
		return list;
	}
	
	public List<String> getResourceURLListByAttribute(String locator, String attribute, String baseURL, boolean appendBaseURL) {
		List<String> resources = new ArrayList();
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if(appendBaseURL) {
			for(WebElement element : elements) {
				if(!element.getAttribute(attribute).trim().isBlank() || !element.getAttribute(attribute).trim().isEmpty()) {
					if(!element.getAttribute(attribute).trim().startsWith(Constants.PROTOCOL) && !element.getAttribute(attribute).trim().startsWith("/")) {
						String resource = baseURL + "/" + element.getAttribute(attribute).trim();
						resources.add(resource);
					}else if(element.getAttribute(attribute).trim().startsWith(Constants.HINDI_SITE_TAG)) {
						String resource = baseURL.replace(Constants.HINDI_SITE_TAG, "") + element.getAttribute(attribute).trim();
						resources.add(resource);					
					}else if(!element.getAttribute(attribute).trim().startsWith(Constants.PROTOCOL)){
						String resource = baseURL + element.getAttribute(attribute).trim();
						resources.add(resource);
					}else {
						resources.add(element.getAttribute(attribute).trim());
					}
				}
			}
		}else {
			for(WebElement element : elements) {
				resources.add(element.getAttribute(attribute).trim());
			}
		}	
		return resources;
	}
	
	public int getTotalBrokenLinks() {
		return brokenLinksCounter;
	}
	
	private void checkStatusCodeAndResponseTime(String resourceURL, BrokenURLsEntity entity) {
		if(!(resourceURL.isEmpty() && resourceURL.isBlank()) && resourceURL.startsWith(Constants.PROTOCOL)) {
			TestLogger.getInstance().info("STEP : Validate the Status Code and Response Time for Resource URL : " + resourceURL);
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
	
	public String switchDynamicXpath(String xpath, String substitute) {
		return xpath.replace("valueTobeReplace", substitute);
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksofAMPUrlForZeeNewIndia(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		brokenLinksCounter = 0;
		Document document = null;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> resources = new ArrayList();
		BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
		try {
			document = Jsoup.connect(baseURL).get();
		} catch (IOException e) {
			
		}
		if(document != null) {
			Elements links = document.select(Constants.ZeeNewsIndia.ANCHOR_HREF_TAG);  
			links.addAll(document.select(Constants.ZeeNewsIndia.SCRIPT_SRC_TAG));
			entity.setArticleURL(baseURL);
			for(Element element : links) {
				if(element.attr(Constants.ZeeNewsIndia.SRC_TAG).startsWith(Constants.ZeeNewsIndia.PROTOCOL) || element.attr(Constants.ZeeNewsIndia.HREF_TAG).startsWith(Constants.ZeeNewsIndia.PROTOCOL)) {
	        		if(element.attr(Constants.ZeeNewsIndia.HREF_TAG).isEmpty())
	        			resources.add(element.attr(Constants.ZeeNewsIndia.SRC_TAG));
	            	else
	            		resources.add(element.attr(Constants.ZeeNewsIndia.HREF_TAG));
	        	}
			}
			for (String resourceURL : resources) {
				checkStatusCodeAndResponseTime(resourceURL, entity);
			}
			list.add(entity);
		}else {
			TestLogger.getInstance().info("There is some issue with document Object, it may be null or not having data.");
		}
		return list;
	}
	
	public boolean validateAMPPageOpened(String url) {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains(url) && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
	
	public String findAMPUrlForMob() {
		return driver.findElement(By.xpath(AMP_LOC)).getAttribute(Constants.ZeeNewsIndia.HREF_TAG);
	}
	
	public boolean isVisible(String locator) {
		return !driver.findElements(By.xpath(locator)).isEmpty();
	}
	
	public boolean isInvisible(String locator) {
		return driver.findElements(By.xpath(locator)).isEmpty();
	}
	
	public void navigateToAMPUrl(String url) {
		TestLogger.getInstance().info("STEP : Navigate to Listing URL : " + url);
		MobUIAction.getInstance().navigateToPage(url);
		TestLogger.getInstance().info("STEP : Find the AMP URL and Navigate to AMP URL");
		String ampURL = findAMPUrlForMob();
		TestLogger.getInstance().info("STEP : Navigate to AMP URL : " + ampURL);
		MobUIAction.getInstance().navigateToPage(ampURL);
	}
}
