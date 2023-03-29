package com.zee.cricketcountry;

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
import com.zee.webpages.CricketCountryHomePage;
import com.zee.webpages.CricketCountryInterviewsPage;
import com.zee.webpages.CricketCountryMomentsOfArticlePage;
import com.zee.webpages.CricketCountryNewsPage;
import com.zee.webpages.CricketCountryPhotoPage;
import com.zee.webpages.CricketCountrySeriesPage;
import com.zee.webpages.CricketCountryT20WorldCup;
import com.zee.webpages.ZeeNewsHomePage;

public class TestCricketCountryBrokenLinksForWeb extends WebAction {

	XSSFWorkbook workbook = GenericUtility.getGenericUtility().getWorkbook();
	XSSFSheet sheet = GenericUtility.getGenericUtility().getXSSFSheet(workbook, "BrokenLinks");
	List<BrokenURLsEntity> arrayList = new ArrayList<BrokenURLsEntity>();
	
	@Test(priority = 1, testName = "Validate, Broken Links at Home Page on Web", enabled = true)
	@Parameters("URL")
	public void test01_ValidateBrokenLinksOfHomeMainPage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Validate Home Page has been loaded");
		assertions.assertTrue(countryHomePage.validateHomePageOpened(url), "Validated, User has been Landed at : Home Page");
		
		testLogger.info("STEP : Validate the Broken Links at Main Page");
		arrayList.addAll(countryHomePage.checkBrokenLinksForMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countryHomePage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 2, testName = "Validate, Broken Links at News Page on Web", enabled = true)
	@Parameters("URL")
	public void test02_ValidateBrokenLinksOfTwoArticlesForNews(String url) {
		Assertions assertions = new Assertions();
		CricketCountryNewsPage countryNewsPage = new CricketCountryNewsPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : NEWS and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForCricketCountry(), Constants.NEWS);
		assertions.assertTrue(countryNewsPage.validateNewsPageOpened(), "Validate, User has been Landed at : " + Constants.NEWS);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.NEWS + " Page");
		arrayList.addAll(countryNewsPage.checkBrokenLinksForMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Articles at " + Constants.NEWS + " Page");
		arrayList.addAll(countryNewsPage.checkBrokenLinksForCricketCountry(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countryNewsPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 3, testName = "Validate, Broken Links at T20 World Cup Web", enabled = true)
	@Parameters("URL")
	public void test03_ValidateBrokenLinksOfMainPageOfT20WorldCup(String url) {
		Assertions assertions = new Assertions();
		CricketCountryT20WorldCup countryT20WorldCup = new CricketCountryT20WorldCup();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : T20 World Cup Page and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.T_20_WORLD_CUP);
		assertions.assertTrue(countryT20WorldCup.validateT20WorldCupOpened(), "Validate, User has been Landed at : " + Constants.T_20_WORLD_CUP);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.T_20_WORLD_CUP + " Page");
		arrayList.addAll(countryT20WorldCup.checkBrokenLinksForMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countryT20WorldCup.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 4, testName = "Validate, Broken Links at Series Page of Web", enabled = true)
	@Parameters("URL")
	public void test04_ValidateBrokenLinksOfMainPageOfSeries(String url) {
		Assertions assertions = new Assertions();
		CricketCountrySeriesPage countrySeriesPage = new CricketCountrySeriesPage();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Series Page and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.SERIES);
		assertions.assertTrue(countrySeriesPage.validateSeriesOpened(), "Validate, User has been Landed at : " + Constants.SERIES);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.SERIES + " Page");
		arrayList.addAll(countrySeriesPage.checkBrokenLinksForMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countrySeriesPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 5, testName = "Validate, Broken Links at Moments In History Page on Web", enabled = true)
	@Parameters("URL")
	public void test05_ValidateBrokenLinksOfTwoArticlesForMomentsInHistoryPage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryMomentsOfArticlePage countryMomentsOfArticlePage = new CricketCountryMomentsOfArticlePage();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Moments In History and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.MOMENTS_IN_HISTORY);
		assertions.assertTrue(countryMomentsOfArticlePage.validateMomentsOfArticleOpened(), "Validate, User has been Landed at : " + Constants.MOMENTS_IN_HISTORY);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.MOMENTS_IN_HISTORY + " Page");
		arrayList.addAll(countryMomentsOfArticlePage.checkBrokenLinksForMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Articles at " + Constants.MOMENTS_IN_HISTORY + " Page");
		arrayList.addAll(countryMomentsOfArticlePage.checkBrokenLinksForCricketCountry(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countryMomentsOfArticlePage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 6, testName = "Validate, Broken Links at Photos Page on Web", enabled = true)
	@Parameters("URL")
	public void test06_ValidateBrokenLinksOfMainPageAndTwoPhotosSectionForPhotoPage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryPhotoPage countryPhotoPage = new CricketCountryPhotoPage();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Photos and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.PHOTOS);
		assertions.assertTrue(countryPhotoPage.validatePhotosPageOpened(), "Validate, User has been Landed at : " + Constants.PHOTOS);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.PHOTOS + " Page");
		arrayList.addAll(countryPhotoPage.checkBrokenLinksForMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Photos at " + Constants.PHOTOS + " Page");
		arrayList.addAll(countryPhotoPage.checkBrokenLinksForListingPhotosForCricketCountry(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countryPhotoPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 7, testName = "Validate, Broken Links at Interviews Page on Web", enabled = false)
	@Parameters("URL")
	public void test07_ValidateBrokenLinksOfTwoArticlesForInterviews(String url) {
		Assertions assertions = new Assertions();
		CricketCountryInterviewsPage countryInterviewsPage = new CricketCountryInterviewsPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Features and Select Interviews Page and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForCricketCountry(), Constants.FEATURES);
		clickByDynXpath(homePage.getCrawlerMenuForCricketCountry(), Constants.INTERVIEWS);
		String currentPageURL = driver.getCurrentUrl();
		assertions.assertTrue(assertions.assertContains("interviews", currentPageURL), "Validate, User has been Landed at : " + currentPageURL);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: facts-and-figures Page");
		arrayList.addAll(countryInterviewsPage.validateBrokenLinksAtMainPage(workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Articles at News Page");
		arrayList.addAll(countryInterviewsPage.validateBrokenLinksForArticle(workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found");
		assertions.assertEquals(countryInterviewsPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 99, alwaysRun = true, enabled = true)
	@Parameters("JENKINS_JOB_NAME")
	public void writeDataInExcel(String jobName) {
		genericUtility.writeDataInWorkBookForBrokenLinks(workbook, sheet, arrayList, Constants.BROKEN_LINK_CRICKET_COUNTRY_ENGLISH_WEB, jobName);
	}
}
