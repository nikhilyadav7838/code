package com.zee.mobpages;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.zee.actions.MobUIAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.utils.TestLogger;

public class ZeeNewsLiveTvPage extends GenericMobPage{
	
	private static final String PAGE_HEADER = "//*[@class='pageh1' and contains(text(),'LIVE TV')]";
	private static final String LISTING_VIDEOS = "(//div[@class='livetv-morevideos']/descendant::h2/a)";
	private int counter = 0;
	
	public boolean validateLiveTvPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains(Constants.ZeeNewsIndia.LIVE_TV_IN_URL) && isInvisible(INTERNAL_SERVER_ERROR) && isVisible(PAGE_HEADER);
	}
	
	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_ZEENEWS_INDIA;
	}
	
	public List<BrokenURLsEntity> validateBrokenLinksAtAMPPage(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		counter = 0;
		List<String> listingVideosURL = getResourceURLListByAttribute(LISTING_VIDEOS, Constants.ZeeNewsIndia.HREF_TAG, baseURL, true);
		List<BrokenURLsEntity> brokenURLsEntities = new ArrayList<BrokenURLsEntity>();
		for(int i = 0; i < listingVideosURL.size(); i++) {
			String url = listingVideosURL.get(i);
			navigateToAMPUrl(url);
			brokenURLsEntities.addAll(checkBrokenLinksofAMPUrlForZeeNewIndia(baseURL, workbook, sheet));
			counter = getTotalBrokenLinks();
		}
		return brokenURLsEntities;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForZeeNewsIndia(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		List<String> mainURLs = getResourceURLListByAttribute(LISTING_VIDEOS, Constants.HREF_TAG, baseURL, false);
		return checkBrokenLinksForZeeNewsIndia(mainURLs, baseURL, workbook, sheet);
	}
	
	public int getBrokenLinks() {
		return counter;
	}
	
}
