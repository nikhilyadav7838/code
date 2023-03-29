package com.zee.cricketcountry.mob;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zee.actions.MobUIAction;
import com.zee.base.Assertions;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.mobpages.CricketCountryFeaturesPage;
import com.zee.mobpages.CricketCountryHomePage;
import com.zee.mobpages.CricketCountryNewsPage;
import com.zee.mobpages.CricketCountryPhotoPage;
import com.zee.mobpages.CricketCountryPointsTablePage;
import com.zee.mobpages.CricketCountryT20WorldCup;
import com.zee.utils.GenericUtility;

public class TestCricketCountryBrokenLinksHindiSiteForMob extends MobUIAction {

	XSSFWorkbook workbook = GenericUtility.getGenericUtility().getWorkbook();
	XSSFSheet sheet = GenericUtility.getGenericUtility().getXSSFSheet(workbook, "BrokenLinksForHindi");
	List<BrokenURLsEntity> arrayList = new ArrayList<BrokenURLsEntity>();
	
	@Test(priority = 1, testName = "Validate, Broken Links at Home Page on Mob", enabled = true)
	@Parameters("URL")
	public void test01_ValidateBrokenLinksOfHomeMainPage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Validate Home Page has been loaded");
		assertions.assertTrue(countryHomePage.validateHomePageOpened(url), "Validated, User has been Landed at : Home Page");
		
		testLogger.info("STEP : Validate the Broken Links at Main Page of Home Page");
		arrayList.addAll(countryHomePage.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at Home Page");
		assertions.assertEquals(countryHomePage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 2, testName = "Validate, Broken Links at News Page on Mob", enabled = true)
	@Parameters("URL")
	public void test02_ValidateBrokenLinksOfTwoArticlesForNews(String url) {
		Assertions assertions = new Assertions();
		CricketCountryNewsPage countryNewsPage = new CricketCountryNewsPage();
		CricketCountryHomePage homePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : NEWS and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForCricketCountry(), Constants.NEWS_HINDI);
		assertions.assertTrue(countryNewsPage.validateNewsPageOpened(), "Validate, User has been Landed at : " + Constants.NEWS);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.NEWS + " Page");
		arrayList.addAll(countryNewsPage.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Articles at " + Constants.NEWS + " Page");
		arrayList.addAll(countryNewsPage.checkBrokenLinksForCricketCountryOfHindiVersion(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at News Page");
		assertions.assertEquals(countryNewsPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 3, testName = "Validate, Broken Links at T20 World Cup Mob", enabled = true)
	@Parameters("URL")
	public void test03_ValidateBrokenLinksOfMainPageOfT20WorldCup(String url) {
		Assertions assertions = new Assertions();
		CricketCountryT20WorldCup countryT20WorldCup = new CricketCountryT20WorldCup();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : T20 World Cup Page and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.T_20_WORLD_CUP_HINDI);
		assertions.assertTrue(countryT20WorldCup.validateT20WorldCupOpened(), "Validate, User has been Landed at : " + Constants.T_20_WORLD_CUP);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.T_20_WORLD_CUP + " Page");
		arrayList.addAll(countryT20WorldCup.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at T20 World Cup");
		assertions.assertEquals(countryT20WorldCup.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 4, testName = "Validate, Broken Links at T20 World Cup Schedule Mob", enabled = true)
	@Parameters("URL")
	public void test04_ValidateBrokenLinksOfMainPageOfT20WorldCupSchedule(String url) {
		Assertions assertions = new Assertions();
		CricketCountryT20WorldCup countryT20WorldCup = new CricketCountryT20WorldCup();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : T20 World Cup Schedule Page and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.T_20_WORLD_CUP_SCHEDULE_HINDI);
		assertions.assertTrue(countryT20WorldCup.validateT20WorldCupScheduleOpened(), "Validate, User has been Landed at : " + Constants.T_20_WORLD_CUP_SCHEDULE);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.T_20_WORLD_CUP_SCHEDULE + " Page");
		arrayList.addAll(countryT20WorldCup.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at T20 World Cup Schedule Page");
		assertions.assertEquals(countryT20WorldCup.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 5, testName = "Validate, Broken Links at Photos Page on Mob", enabled = true)
	@Parameters("URL")
	public void test05_ValidateBrokenLinksOfMainPageAndTwoPhotosSectionForPhotoPage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryPhotoPage countryPhotoPage = new CricketCountryPhotoPage();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Photos and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.PHOTOS_HINDI);
		assertions.assertTrue(countryPhotoPage.validatePhotosPageOpened(), "Validate, User has been Landed at : " + Constants.PHOTOS_HINDI);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.PHOTOS + " Page");
		arrayList.addAll(countryPhotoPage.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Photos at " + Constants.PHOTOS + " Page");
		arrayList.addAll(countryPhotoPage.checkBrokenLinksForListingPhotosForCricketCountryOfHindiVersion(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at Photos Page");
		assertions.assertEquals(countryPhotoPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 6, testName = "Validate, Broken Links at Features Page on Mob", enabled = true)
	@Parameters("URL")
	public void test06_ValidateBrokenLinksOfMainPageAndTwoListingArticleOfFeaturesPage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryFeaturesPage countryFeaturesPage = new CricketCountryFeaturesPage();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Features and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.FEATURES_HINDI);
		assertions.assertTrue(countryFeaturesPage.validateFeaturesOpened(), "Validate, User has been Landed at : " + Constants.FEATURES_HINDI);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.FEATURES + " Page");
		arrayList.addAll(countryFeaturesPage.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate the Broken Links for Listing Articles at " + Constants.FEATURES + " Page");
		arrayList.addAll(countryFeaturesPage.checkBrokenLinksForCricketCountryOfHindiVersion(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at Features");
		assertions.assertEquals(countryFeaturesPage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 7, testName = "Validate, Broken Links at Points Table Page on Mob", enabled = true)
	@Parameters("URL")
	public void test07_ValidateBrokenLinksOfMainPageOfPointTablePage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryPointsTablePage cPointsTablePage = new CricketCountryPointsTablePage();
		CricketCountryHomePage countryHomePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Points Table and Validate Page has been loaded");
		clickByDynXpath(countryHomePage.getCrawlerMenuForCricketCountry(), Constants.POINTS_TABLE);
		assertions.assertTrue(cPointsTablePage.validatePointsTableOpened(), "Validate, User has been Landed at : " + Constants.POINTS_TABLE);
		
		testLogger.info("STEP : Validate the Broken Links at Main Page: " + Constants.POINTS_TABLE + " Page");
		arrayList.addAll(cPointsTablePage.checkBrokenLinksForMainPageOfCricketCountry(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found at Points Table Page");
		assertions.assertEquals(cPointsTablePage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		assertions.assertAll();
	}
	
	@Test(priority = 99, alwaysRun = true, enabled = true)
	@Parameters("JENKINS_JOB_NAME")
	public void writeDataInExcel(String jobName) {
		genericUtility.writeDataInWorkBookForBrokenLinks(workbook, sheet, arrayList, Constants.BROKEN_LINK_CRICKET_COUNTRY_HINDI_MOB, jobName);
	}
}
