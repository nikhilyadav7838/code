package com.zee.web;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zee.actions.WebAction;
import com.zee.base.Assertions;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.utils.GenericUtility;
import com.zee.webpages.ZeeNewsEntertainmentPage;
import com.zee.webpages.ZeeNewsHomePage;
import com.zee.webpages.ZeeNewsIndiaPage;
import com.zee.webpages.ZeeNewsIndiaPhotoPage;
import com.zee.webpages.ZeeNewsLatestNewsPage;
import com.zee.webpages.ZeeNewsLiveTVPage;
import com.zee.webpages.ZeeNewsSportsPage;
import com.zee.webpages.ZeeNewsT20WorldCupPage;
import com.zee.webpages.ZeeNewsVideosPage;

public class TestZeeNewsIndiaBrokenLinksForWeb extends WebAction {
	
	XSSFWorkbook workbook = GenericUtility.getGenericUtility().getWorkbook();
	XSSFSheet sheet = GenericUtility.getGenericUtility().getXSSFSheet(workbook, "BrokenLinksForEnglish");
	List<BrokenURLsEntity> arrayList = new ArrayList<BrokenURLsEntity>();

	@Test(priority = 1, testName = "Validate, Broken Links for Live TV for Web", enabled = true)
	@Parameters("URL")
	public void test01_ValidateBrokenLinksOfLiveTVPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsLiveTVPage liveTVPage = new ZeeNewsLiveTVPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : LIVE TV and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenu(), Constants.LIVE_TV);
		assertions.assertTrue(liveTVPage.validateLiveTVPageOpened(), "Validate, User has been Landed at : " + Constants.LIVE_TV);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.LIVE_TV + " Page");
		arrayList.addAll(liveTVPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, true));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(liveTVPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 2, testName = "Validate, Broken Links for Latest News for Web", enabled = true)
	@Parameters("URL")
	public void test02_ValidateBrokenLinksOfTwoListingForLatestNewsPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsLatestNewsPage newsPage = new ZeeNewsLatestNewsPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : LATEST NEWS and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenu(), Constants.LATEST_NEWS);
		assertions.assertTrue(newsPage.validateLatestNewsPageOpened(), "Validate, User has been Landed at : " + Constants.LATEST_NEWS);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.LATEST_NEWS + " Page");
		arrayList.addAll(newsPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate the Broken Links for 2 Listing Articles at " + Constants.LATEST_NEWS + " Page");
		arrayList.addAll(newsPage.checkBrokenLinksForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(newsPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 3, testName = "Validate, Broken Links for India Page for Web", enabled = true)
	@Parameters("URL")
	public void test03_ValidateBrokenLinksOfTwoListingForIndiaPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsIndiaPage indiaPage = new ZeeNewsIndiaPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : INDIA and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenu(), Constants.INDIA);
		assertions.assertTrue(indiaPage.validateIndiaPageOpened(), "Validate, User has been Landed at : " + Constants.INDIA);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.INDIA + " Page");
		arrayList.addAll(indiaPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate the Broken Links for 2 Listing Articles at " + Constants.INDIA + " Page");
		arrayList.addAll(indiaPage.checkBrokenLinksForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(indiaPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 4, testName = "Validate, Broken Links for T20 World Cup Page for Web", enabled = false)
	@Parameters("URL")
	public void test04_ValidateBrokenLinksOfMainPageForT20WorldCupPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsT20WorldCupPage t20WorldCupPage = new ZeeNewsT20WorldCupPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : " + Constants.T20_WORLD_CUP + " and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenu(), Constants.T20_WORLD_CUP);
		assertions.assertTrue(t20WorldCupPage.validateT20WorldCupPageOpened(), "Validate, User has been Landed at : " + Constants.T20_WORLD_CUP);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.T20_WORLD_CUP + " Page");
		arrayList.addAll(t20WorldCupPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(t20WorldCupPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 5, testName = "Validate, Broken Links for Entertainment Page for Web", enabled = true)
	@Parameters("URL")
	public void test05_ValidateBrokenLinksOfTwoListingForEntertainmentPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsEntertainmentPage entertainmentPage = new ZeeNewsEntertainmentPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : " + Constants.ENTERTAINMENT + " and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenu(), Constants.ENTERTAINMENT);
		assertions.assertTrue(entertainmentPage.validateEntertainmentPageOpened(), "Validate, User has been Landed at : " + Constants.ENTERTAINMENT);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.ENTERTAINMENT + " Page");
		arrayList.addAll(entertainmentPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate the Broken Links for 2 Listing Articles at " + Constants.ENTERTAINMENT + " Page");
		arrayList.addAll(entertainmentPage.checkBrokenLinksForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(entertainmentPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 6, testName = "Validate, Broken Links for Sports Page for Web", enabled = true)
	@Parameters("URL")
	public void test06_ValidateBrokenLinksOfTwoListingForSportsPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsSportsPage sportsPage = new ZeeNewsSportsPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : " + Constants.SPORTS + " and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenu(), Constants.SPORTS);
		assertions.assertTrue(sportsPage.validateSportsPageOpened(), "Validate, User has been Landed at : " + Constants.SPORTS);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.SPORTS + " Page");
		arrayList.addAll(sportsPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate the Broken Links for 2 Listing Articles at " + Constants.SPORTS + " Page");
		arrayList.addAll(sportsPage.checkBrokenLinksForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(sportsPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 7, testName = "Validate, Broken Links for Video Page for Web", enabled = true)
	@Parameters("URL")
	public void test07_ValidateBrokenLinksOfVideoPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsVideosPage videosPage = new ZeeNewsVideosPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Menu : " + Constants.ZeeNewsIndia.VIDEOS_MENU + " and Validate Page has been loaded");
		clickByDynXpath(homePage.getParentMenuForZeeNewsIndia(), Constants.ZeeNewsIndia.VIDEOS_MENU);
		
		assertions.assertTrue(videosPage.validateVideoPageOpened(), "Validate, User has been Landed at : " + Constants.ZeeNewsIndia.VIDEOS_MENU);
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.ZeeNewsIndia.VIDEOS_MENU + " Page");
		arrayList.addAll(videosPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate the Broken Links for Trending Videos at " + Constants.ZeeNewsIndia.VIDEOS_MENU + " Page");
		arrayList.addAll(videosPage.checkBrokenLinksForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(videosPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 8, testName = "Validate, Broken Links for Photo Page for Web", enabled = true)
	@Parameters("URL")
	public void test08_ValidateBrokenLinksOfPhotoPage(String url) {
		Assertions assertions = new Assertions();
		ZeeNewsIndiaPhotoPage photoPage = new ZeeNewsIndiaPhotoPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Validate Photo Page has been loaded");
		clickByDynXpath(homePage.getParentMenu(), Constants.PHOTOS);
		assertions.assertTrue(photoPage.validatePhotoPageOpened(), "Validated, User has been Landed at : Home Page");
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.PHOTOS + " Page");
		arrayList.addAll(photoPage.checkBrokenLinksofMainPageForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate the Broken Links for All Photos Album " + Constants.PHOTOS + " Page");
		arrayList.addAll(photoPage.checkBrokenLinksForZeeNewsIndia(workbook, sheet, false));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(photoPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 99, alwaysRun = true, enabled = true)
	@Parameters("JENKINS_JOB_NAME")
	public void writeDataInExcel(String jobName) {
		genericUtility.writeDataInWorkBookForBrokenLinks(workbook, sheet, arrayList, Constants.BROKEN_LINK_SHEET_NAME, jobName);
	}
}
