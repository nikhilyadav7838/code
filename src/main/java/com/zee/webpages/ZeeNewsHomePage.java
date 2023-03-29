package com.zee.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.zee.actions.WebAction;
import com.zee.manager.WebDriverManager;
import com.zee.mobpages.ZeeNewsIndiaPage;
import com.zee.utils.TestLogger;

public class ZeeNewsHomePage extends GenericWebPage {

	public final String TITLE_OF_HOME_PAGE = "Zee News: Latest News, Live Breaking News, Today News, India Political News Updates";
	private static String topStoryAtHomePage = "(//div[@id='homepagea']/descendant::div[contains(@class,'top-story-desktop')]/descendant::a)[1]";
	private static String topStoryAtHomePageForMob = "(//div[@id='homepagea']/descendant::div[contains(@class,'single_news')]/descendant::a)[1]";
	private static String videosMenu = "//div[@id='sticky_header']/descendant::ul[@class='submenu']/li/descendant::a[@class='watch_menu']";
	private static String photoMenu = ".submenu .photos_menu";
	
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
			TestLogger.getInstance().error(e.getMessage());
		}
		
	}
	
	public ZeeNewsIndiaPage openTopHeadStoryForMob() {
		clickOnElement(topStoryAtHomePageForMob);
		try {
			WebAction.getInstance().waitForPageToLoad(30);
		}catch (Exception e) {
			TestLogger.getInstance().error(e.getMessage());
		}
		return new ZeeNewsIndiaPage();
	}
	
	public void clickAndopenVideos() {
		clickOnElement(videosMenu);
	}
	
	public void clickOnElement(String element) {
		if(!driver.findElements(By.xpath(element)).isEmpty()) {
			driver.findElement(By.xpath(element)).click();
		}			
	}
	
	public void clickOnPhotoSection() {
		driver.findElement(By.cssSelector(photoMenu)).click();
	}
	
}
