package com.zee.webpages;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zee.actions.WebAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.utils.TestLogger;

public class ZeeNewsIndiaPhotoPage extends GenericWebPage{

	private int brokenLinksCount;
	private static String firstImage = ".row.morephotosnav1 .photo-thumb-img1 a";
	private static String sizeOfImages = "//div[@class='photo-thumb-img1']/..//span[@class='video_duration']"; 
	private static String totalImages = ".pcount>.ptotal";
	private static final String TOTAL_PHOTOS_ALBUM = "//div[@class='container']/descendant::div[@class='photo_title']/a";
	
	public String getParentMenu() {
		return PARENT_MENU;
	}
	
	@Override
	public List<BrokenURLsEntity> checkBrokenLinksForZeeNewsIndia(XSSFWorkbook workbook, XSSFSheet sheet, boolean isAllResourceCheck) {
		brokenLinksCount = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(TOTAL_PHOTOS_ALBUM, Constants.HREF_TAG);
		if(!mainURLs.isEmpty()) {
			for(String mainURL : mainURLs) {
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
		}else {
			TestLogger.getInstance().error("There is Some issue with locator - " + TOTAL_PHOTOS_ALBUM + " or listing not visible.");
		}
		return list;
	}
	
	@Override
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
				brokenLinksCount++;
			}
		}
	}
	
	@Override
	public int getTotalBrokenLinks() {
		return brokenLinksCount;
	}
	
	public boolean validatePhotoPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains("photos") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
	
	public void waitForImageToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(firstImage)));
	}
	
	public String getTitleOfImage() {
		return driver.findElement(By.tagName("h1")).getText();
	}
	
	public int getCountOfImagesPresentInsidePhotoPageImage() {
		return Integer.parseInt(driver.findElement(By.xpath(sizeOfImages)).getText());
	}
	
	public void selectTheFirstPictureAppearOnPhotoSectionPage() {
		WebElement firstPhotoInSection = driver.findElements(By.cssSelector(firstImage)).get(0);
		firstPhotoInSection.click();
	}
	
	public int getTotalNumberOfImagesPresentAfterClickOnImageFromPhotoSectionPage() {
		return (driver.findElements(By.cssSelector(totalImages)).size());
	}
	
	public boolean validateThePhotosCountFromOutsideToInside() {
		int totalCountFromOutside = getCountOfImagesPresentInsidePhotoPageImage();
		TestLogger.getInstance().info("STEP: Select and Open the First Image.");
		selectTheFirstPictureAppearOnPhotoSectionPage();
		int totalCountFromInside = getTotalNumberOfImagesPresentAfterClickOnImageFromPhotoSectionPage();
		TestLogger.getInstance().info("STEP: Validate, Total count of Outside Image are : " + totalCountFromOutside + " and Inside Total Images are : " + totalCountFromInside);
		return totalCountFromOutside == totalCountFromInside;
	}
}
