package com.zeenews.amp;

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
import com.zee.mobpages.ZeeNewsIndiaPage;
import com.zee.mobpages.ZeeNewsLatestNewsPage;
import com.zee.mobpages.ZeeNewsLiveTvPage;
import com.zee.mobpages.ZeeNewsSportsPage;
import com.zee.mobpages.ZeeNewsEntertainmentPage;
import com.zee.mobpages.ZeeNewsHomePage;
import com.zee.utils.GenericUtility;

public class TestBrokenLinksForAMPWithEnglishVersion extends MobUIAction{

	XSSFWorkbook workbook = GenericUtility.getGenericUtility().getWorkbook();
	XSSFSheet sheet = GenericUtility.getGenericUtility().getXSSFSheet(workbook, "BrokenLinksForEnglish");
	List<BrokenURLsEntity> arrayList = new ArrayList<BrokenURLsEntity>();

	@Test(priority = 1, testName = "Validate, Broken Links for HOME for AMP", enabled = true)
	@Parameters("URL")
	public void test01_ValidateBrokenLinksOfHomePage(String url) {
		Assertions softAssert = new Assertions();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Validate Home Page has been loaded");
		softAssert.assertTrue(homePage.validateHomePageOpened(url), "Validated, User has been Landed at : Home Page");
		
		testLogger.info("STEP : Open the Top Head Story");
		ZeeNewsIndiaPage indiaPage = homePage.openTopHeadStory();
		handleBannerAdvertise();
		
		testLogger.info("STEP : Fetch the AMP URL From Article Page");
		String ampURL = indiaPage.findAMPUrlForMob();
		
		testLogger.info("STEP : Navigate to AMP URL : " + ampURL);
		navigateToPage(ampURL);
		
		testLogger.info("STEP : Validate AMP URL Page has been loaded");
		softAssert.assertTrue(homePage.validateAMPPageOpened(ampURL), "Validated, User has been Landed at : Home Page");
		
		testLogger.info("STEP : Validate the Broken Links at AMP Page");
		arrayList.addAll(homePage.checkBrokenLinksofAMPUrlForZeeNewIndia(ampURL, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found : " + homePage.getTotalBrokenLinks());
		softAssert.assertEquals(homePage.getTotalBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 2, testName = "Validate, Broken Links for Live TV Page for AMP", enabled = true)
	@Parameters("URL")
	public void test02_ValidateBrokenLinksOfLiveTVPage(String url) {
		Assertions softAssert = new Assertions();
		ZeeNewsLiveTvPage liveTvPage = new ZeeNewsLiveTvPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Live TV and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForZeeNewsIndia(), Constants.ZeeNewsIndia.LIVE_TV);
		
		testLogger.info("STEP : Validate Live TV Page has been opened");
		softAssert.assertTrue(liveTvPage.validateLiveTvPageOpened(), "Validated, User has been Landed at : Live TV Page");
		handleBannerAdvertise();
		
		testLogger.info("STEP : Open the Videos and Validate the Broken Links at AMP Page");
		arrayList.addAll(liveTvPage.validateBrokenLinksAtAMPPage(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found : " + liveTvPage.getBrokenLinks());
		softAssert.assertEquals(liveTvPage.getBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 3, testName = "Validate, Broken Links for Latest News Page for AMP", enabled = true)
	@Parameters("URL")
	public void test03_ValidateBrokenLinksOfLatestNewsPage(String url) {
		Assertions softAssert = new Assertions();
		ZeeNewsLatestNewsPage latestNewsPage = new ZeeNewsLatestNewsPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Latest News and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForZeeNewsIndia(), Constants.ZeeNewsIndia.LATEST_NEWS);
		
		testLogger.info("STEP : Validate Latest News Page has been opened");
		softAssert.assertTrue(latestNewsPage.validateLatestNewsPageOpened(), "Validated, User has been Landed at : Lastest News Page");
		handleBannerAdvertise();
		
		testLogger.info("STEP : Open the Article and Validate the Broken Links at AMP Page");
		arrayList.addAll(latestNewsPage.validateBrokenLinksAtAMPPage(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found : " + latestNewsPage.getBrokenLinks());
		softAssert.assertEquals(latestNewsPage.getBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 4, testName = "Validate, Broken Links for India Page for AMP", enabled = true)
	@Parameters("URL")
	public void test04_ValidateBrokenLinksOfIndiaPage(String url) {
		Assertions softAssert = new Assertions();
		ZeeNewsIndiaPage zeeNewsIndiaPage = new ZeeNewsIndiaPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : India Page and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForZeeNewsIndia(), Constants.ZeeNewsIndia.INDIA);
		
		testLogger.info("STEP : Validate India Page has been opened");
		softAssert.assertTrue(zeeNewsIndiaPage.validateIndiaPageOpened(), "Validated, User has been Landed at : India Page");
		handleBannerAdvertise();
		
		testLogger.info("STEP : Open the Article and Validate the Broken Links at AMP Page");
		arrayList.addAll(zeeNewsIndiaPage.validateBrokenLinksAtAMPPage(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found : " + zeeNewsIndiaPage.getBrokenLinks());
		softAssert.assertEquals(zeeNewsIndiaPage.getBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 5, testName = "Validate, Broken Links for Entertainment Page for AMP", enabled = true)
	@Parameters("URL")
	public void test05_ValidateBrokenLinksOfEntertainmentPage(String url) {
		Assertions softAssert = new Assertions();
		ZeeNewsEntertainmentPage zeeNewsEntertainmentPage = new ZeeNewsEntertainmentPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Entertainment Page and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForZeeNewsIndia(), Constants.ZeeNewsIndia.ENTERTAINMENT);
		
		testLogger.info("STEP : Validate Entertainment Page has been opened");
		softAssert.assertTrue(zeeNewsEntertainmentPage.validateEntertainmentPageOpened(), "Validated, User has been Landed at : Entertainment Page");
		handleBannerAdvertise();
		
		testLogger.info("STEP : Open the Article and Validate the Broken Links at AMP Page");
		arrayList.addAll(zeeNewsEntertainmentPage.validateBrokenLinksAtAMPPage(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found : " + zeeNewsEntertainmentPage.getBrokenLinks());
		softAssert.assertEquals(zeeNewsEntertainmentPage.getBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 6, testName = "Validate, Broken Links for Sports Page for AMP", enabled = true)
	@Parameters("URL")
	public void test06_ValidateBrokenLinksOfSportsPage(String url) {
		Assertions softAssert = new Assertions();
		ZeeNewsSportsPage newsSportsPage = new ZeeNewsSportsPage();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Move To Crawler Menu : Sports Page and Validate Page has been loaded");
		clickByDynXpath(homePage.getCrawlerMenuForZeeNewsIndia(), Constants.ZeeNewsIndia.SPORTS);
		
		testLogger.info("STEP : Validate Sports Page has been opened");
		softAssert.assertTrue(newsSportsPage.validateSportsPageOpened(), "Validated, User has been Landed at : Sports Page");
		handleBannerAdvertise();
		
		testLogger.info("STEP : Open the Article and Validate the Broken Links at AMP Page");
		arrayList.addAll(newsSportsPage.validateBrokenLinksAtAMPPage(url, workbook, sheet));
		
		testLogger.info("STEP : Validate any broken Links Found : " + newsSportsPage.getBrokenLinks());
		softAssert.assertEquals(newsSportsPage.getBrokenLinks(), 0, "Validated, Found Broken Links at Pages");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 99, alwaysRun = true, enabled = true)
	@Parameters("JENKINS_JOB_NAME")
	public void writeDataInExcel(String jobName) {
		genericUtility.writeDataInWorkBookForBrokenLinks(workbook, sheet, arrayList, Constants.ZeeNewsIndia.BROKEN_LINK_FOR_AMP_SHEET_NAME, jobName);
	}
}
