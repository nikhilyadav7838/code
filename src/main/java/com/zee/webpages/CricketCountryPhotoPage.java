package com.zee.webpages;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import com.zee.actions.WebAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.utils.TestLogger;

public class CricketCountryPhotoPage extends GenericWebPage{

	private int photosCounter;
	private static final String PHOTO_LISTING = "//div[@class='photo-container']/descendant::div[@class='card photo-card']/a";
	
	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public boolean validatePhotosPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("photos") && !driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForListingPhotosForCricketCountry(XSSFWorkbook workbook, XSSFSheet sheet) {
		photosCounter = 0;
		int counter = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(PHOTO_LISTING, Constants.HREF_TAG);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
				counter++;
				if(counter <= 2) {
					List<String> resources = new ArrayList();
					BrokenURLsEntity entity = new BrokenURLsEntity(workbook, sheet);
					TestLogger.getInstance().info("STEP : Navigate to Photos Listing URL : " + mainURL);
					WebAction.getInstance().navigateToPage(mainURL);
					resources.addAll(getResourceURLListByAttribute(SRC_LINKS, Constants.SRC_TAG));
					resources.addAll(getResourceURLListByAttribute(HREF_LINKS, Constants.HREF_TAG));
					TestLogger.getInstance().info("INFO : The Total Resources are : " + resources.size());
					entity.setArticleURL(mainURL);
					for (String resourceURL : resources) {
						checkStatusCodeAndResponseTimeForPhotosListing(resourceURL, entity);
					}
					list.add(entity);
				}
			}
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + ARTICLE_LISTING_FOR_CRICKET_COUNTRY + " or listing not visible.");
		}
		return list;
	}
	
	private void checkStatusCodeAndResponseTimeForPhotosListing(String resourceURL, BrokenURLsEntity entity) {
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
				photosCounter++;
			}
		}
	}
	
	@Override
	public int getTotalBrokenLinks() {
		return photosCounter;
	}
}
