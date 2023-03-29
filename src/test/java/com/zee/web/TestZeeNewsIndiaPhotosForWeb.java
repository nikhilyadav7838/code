package com.zee.web;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zee.actions.WebAction;
import com.zee.base.Assertions;
import com.zee.constants.Constants;
import com.zee.webpages.ZeeNewsHomePage;
import com.zee.webpages.ZeeNewsIndiaPhotoPage;

public class TestZeeNewsIndiaPhotosForWeb extends WebAction {
	
	@Test(priority = 1, testName = "Validate, the total count of images are showing correct, after open the collection of Images.")
	@Parameters("URL")
	public void test01_ValidateTotalCountOfImageAtPhotoPage(@Optional String url) throws InterruptedException {
		Assertions assertions = new Assertions();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		ZeeNewsIndiaPhotoPage photoPage = new ZeeNewsIndiaPhotoPage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		
		testLogger.info("STEP : Validate Photo Page has been loaded");
		clickByDynXpath(homePage.getParentMenu(), Constants.PHOTOS);
		assertions.assertTrue(photoPage.validatePhotoPageOpened(), "Validated, User has been Landed at : Home Page");
		handleBannerAdvertise();
		
		assertions.assertEquals(photoPage.validateThePhotosCountFromOutsideToInside(), "Validated, the total count of outsize Images are equal to Total Count of Inside Images.");
		assertions.assertAll();
	}
}
