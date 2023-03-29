package com.zee.webpages;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zee.actions.WebAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.manager.WebDriverManager;
import com.zee.utils.TestLogger;

public class ZeeNewsVideosPage extends GenericWebPage{

	private WebDriverWait driverWait;
	private int brokenLinksCount;
	private static String advertiseTagOnVideo= "//div[@class='video_player']/descendant::div[@class='playkit-left-controls']/child::span[text()='Advertisement']";
	private static String skipAdvertiseOnVideo = "//div[contains(@class,'videoAdUiSkipContainer')]/descendant::button[@aria-label='Skip Ad']";
	private static String videoPlay = "//div[@class='playkit-bottom-bar']/descendant::button[contains(@class,'playkit-control-button') and @aria-label='Pause']";
	private static String videoPause = "//div[@class='playkit-bottom-bar']/descendant::button[contains(@class,'playkit-control-button') and @aria-label='Play']";
	private static String seekReverseButton = "//div[@class='playkit-tooltip']/button[@aria-label='Seek backwards']";
	private static String replayButton = "//button[@class='playkit-pre-playback-play-button']";
	private static String videoSectionFrame = "//div[@class='playkit-ads-container']/descendant::iframe[@allow='autoplay']";
	private static final String TRENDING_VIDEOS = "//div[@class='english-trending-videos']/descendant::div[@class='trending-videos-container']/a";
	
	public boolean validateVideoPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		TestLogger.getInstance().info("The Current URL :: " + childPageURL);
		return childPageURL.contains("videos") && driver.findElements(By.xpath(INTERNAL_SERVER_ERROR)).isEmpty();
	}
	
	@Override
	public List<BrokenURLsEntity> checkBrokenLinksofMainPageForZeeNewsIndia(XSSFWorkbook workbook, XSSFSheet sheet, boolean isAllResourceCheck) {
		brokenLinksCount = 0;
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
	
	@Override
	public List<BrokenURLsEntity> checkBrokenLinksForZeeNewsIndia(XSSFWorkbook workbook, XSSFSheet sheet, boolean isAllResourceCheck) {
		brokenLinksCount = 0;
		List<BrokenURLsEntity> list = new ArrayList();
		List<String> mainURLs = getResourceURLListByAttribute(TRENDING_VIDEOS, Constants.HREF_TAG);
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
			TestLogger.getInstance().error("There is Some issue with locator - " + TRENDING_VIDEOS + " or listing not visible.");
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
	
	
	public void handleAdvertise() {
		/*
		 * driverWait = new WebDriverWait(driver, 5);
		 * if(!driver.findElements(By.className("playkit-spinner")).isEmpty()) {
		 * driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className
		 * ("playkit-spinner"))); }
		 */
		driverWait = new WebDriverWait(driver, 10);
		if(driver.findElements(By.xpath(advertiseTagOnVideo)).isEmpty() && driver.findElements(By.xpath(replayButton)).isEmpty()) {
//		if(driver.findElements(By.xpath(advertiseTagOnVideo)).isEmpty() && driver.findElements(By.xpath(seekReverseButton)).isEmpty() && driver.findElements(By.xpath(replayButton)).isEmpty()) {
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advertiseTagOnVideo)));
			if(!driver.findElements(By.xpath(advertiseTagOnVideo)).isEmpty()) {
				driver.switchTo().frame(driver.findElement(By.xpath(videoSectionFrame)));
				driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(skipAdvertiseOnVideo)));
				clickOnElement(skipAdvertiseOnVideo);
				driver.switchTo().defaultContent();
			}
		}else if(!driver.findElements(By.xpath(advertiseTagOnVideo)).isEmpty()) {
			driver.switchTo().frame(driver.findElement(By.xpath(videoSectionFrame)));
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(skipAdvertiseOnVideo)));
			clickOnElement(skipAdvertiseOnVideo);
			driver.switchTo().defaultContent();
		}
	}
	
	public void clickOnElement(String element) {
		if(!driver.findElements(By.xpath(element)).isEmpty())
			driver.findElement(By.xpath(element)).click();
	}
	
	public void playVideo() {
		clickOnElement(videoPause);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void pauseVideo() {
		clickOnElement(videoPlay);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void resumeVideo() {
		pauseVideo();
		playVideo();
	}
}
