package com.zee.cricketcountry.mob;

import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zee.actions.MobUIAction;
import com.zee.base.Assertions;
import com.zee.constants.Constants;
import com.zee.mobpages.CricketCountryHomePage;

public class TestCricketCountryGAandScoreCardCallsForMob extends MobUIAction{

	@Test(priority = 1, testName = "Validate, GA and ScoreCard Calls of Live Scores Page on Mob", enabled = true)
	@Parameters("URL")
	public void test01_ValidateGAandScoreCardCallsForLiveScorePage(String url) {
		Assertions assertions = new Assertions();
		CricketCountryHomePage homePage = new CricketCountryHomePage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Validate Live Score Page has been loaded");
		assertions.assertTrue(homePage.validateHomePageOpened(url), "Validated, User has been Landed at : Live Score Page");
		String titleOfPage = driver.getTitle();
		
		testLogger.log("STEP : Fetched GOOGLE-ANALYTICS CALLS from Network Logs");
		List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
		
		setRequestParamOfGAandScoreCardForCricketCountry(entries, Constants.CricketCountry.GA_CALLS_FOR_CRICKETCOUNTRY_MOBILE, Constants.COLLECT_URL_ATTRIBUTE, null, null, false);
		testLogger.log("Verified, T Value of GA Calls : " + googleAnalyticCalls.getT());
		assertions.assertEquals(googleAnalyticCalls.getT(), Constants.PAGE_VIEW, "Verified, T Value of GA Calls");

		testLogger.log("Verified, DT Value of GA Calls : " + googleAnalyticCalls.getDt());
		assertions.assertEquals(googleAnalyticCalls.getDt(), titleOfPage, "Verified, DT Value of GA Calls");

		testLogger.log("Verified, DL Value of GA Calls : " + googleAnalyticCalls.getDl());
		assertions.assertEquals(googleAnalyticCalls.getDl(), getCurrentPageURL(), "Verified, DL Value of GA Calls");
		
		testLogger.log("Verified, TID Value of GA Calls : " + googleAnalyticCalls.getTid());
		assertions.assertEquals(googleAnalyticCalls.getTid(), Constants.CricketCountry.UA_20888097_1, "Verified, TID Value of GA Calls");
		 
		testLogger.log("STEP : Fetched SCORE-CARD CALLS from Network Logs");
		setRequestParamOfGAandScoreCardForCricketCountry(entries, Constants.CricketCountry.SCORECARD_CALLS_FOR_CRICKETCOUNTRY_MOBILE, Constants.C1, null, null, false);
		
		testLogger.log("Verified, C2 Value of SCORE-CARD Calls : " + scoreCardCalls.getC2()); 
		assertions.assertEquals(scoreCardCalls.getC2(), Constants.C2_9254297, "Verified, C2 Attribute value of SCORE-CARD Calls");
		
		testLogger.log("Verified, C7 Value of SCORE-CARD Calls : " + scoreCardCalls.getC7()); 
		assertions.assertEquals(scoreCardCalls.getC7(), getCurrentPageURL(), "Verified, C7 Attribute value of SCORE-CARD Calls");
		  
		testLogger.log("Verified, C8 Value of SCORE-CARD Calls : " + scoreCardCalls.getC8()); 
		assertions.assertEquals(scoreCardCalls.getC8(), titleOfPage, "Verified, C8 Attribute value of SCORE-CARD Calls");

		assertions.assertAll();
	}
}
