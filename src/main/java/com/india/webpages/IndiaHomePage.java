package com.india.webpages;

import org.openqa.selenium.By;

import com.zee.actions.WebAction;
import com.zee.mobpages.ZeeNewsIndiaPage;
import com.zee.utils.TestLogger;
import com.zee.webpages.GenericWebPage;

public class IndiaHomePage extends GenericWebPage{
 
	public final String TITLE_OF_HOME_PAGE = "Latest News, Breaking News, LIVE News, Top News Headlines, Viral Video, Cricket LIVE, Sports, Entertainment, Business, Health, Lifestyle and Utility News | India.Com News";
	private static String topStoryAtHomePage = "(//main[@class='common-all-content']/descendant::div[contains(@class,'lazy-image shine')]/descendant::img)[1]";
	//private static String topStoryAtHomePageForMob = "(//div[@id='homepagea']/descendant::div[contains(@class,'single_news')]/descendant::a)[1]";
	private static String videosMenu = "//div[2]/div[1]/div/div/ul[@class='nav navbar-nav']/li[7]/a";
	private static String photoMenu = "//div[2]/div[1]/div/div/ul[@class='nav navbar-nav']/li[6]/a";
	
	public String getParentMenu() {
		return PARENT_MENU;
	}
	
	public String getCrawlerMenu() {
		return CRAWLER_MENU_OF_ZEENEWS;
	}
	
	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_CRICKETCOUNTRY;
	}
	
	public void openTopHeadStory() {
		clickOnElement(topStoryAtHomePage);
		try {
			WebAction.getInstance().waitForPageToLoad(30);
		}catch (Exception e) {
			TestLogger.getInstance()
			
			
			.error(e.getMessage());
		}
		
	}
	
	/*public ZeeNewsIndiaPage openTopHeadStoryForMob() {
		clickOnElement(topStoryAtHomePageForMob);
		try {
			WebAction.getInstance().waitForPageToLoad(30);
		}catch (Exception e) {
			TestLogger.getInstance().error(e.getMessage());
		}
		return new ZeeNewsIndiaPage();
	}*/
	
	public void clickAndopenVideos() {
		clickOnElement(videosMenu);
	}
	
	public void clickOnElement(String element) {
		if(!driver.findElements(By.xpath(element)).isEmpty()) {
			driver.findElement(By.xpath(element)).click();
		}			
	}
	
	public void clickOnPhotoSection() {
		clickOnElement(photoMenu);
	}
	
}


