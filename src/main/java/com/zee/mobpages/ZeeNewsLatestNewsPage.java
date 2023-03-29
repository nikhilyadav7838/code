package com.zee.mobpages;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zee.actions.MobUIAction;
import com.zee.constants.Constants;
import com.zee.entities.BrokenURLsEntity;
import com.zee.utils.TestLogger;

public class ZeeNewsLatestNewsPage extends GenericMobPage {

	private static final String PAGE_HEADER = "//*[@class='pageh1' and contains(text(),'Latest-news')]";
	private static final String LISTING_ARTICLE = "//div[@class='india_category']/descendant::div[@class='news_left']/descendant::a";
	private int counter = 0;

	public boolean validateLatestNewsPageOpened() {
		String childPageURL = driver.getCurrentUrl();
		return childPageURL.contains(Constants.ZeeNewsIndia.LATEST_NEWS_IN_URL) && isInvisible(INTERNAL_SERVER_ERROR)
				&& isVisible(PAGE_HEADER);
	}

	public String getCrawlerMenuForCricketCountry() {
		return CRAWLER_MENU_OF_ZEENEWS_INDIA;
	}

	public List<BrokenURLsEntity> validateBrokenLinksAtAMPPage(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		List<String> listingArticle = getResourceURLListByAttribute(LISTING_ARTICLE, Constants.ZeeNewsIndia.HREF_TAG, baseURL, true);
		List<BrokenURLsEntity> brokenURLsEntities = new ArrayList<BrokenURLsEntity>();
		int count = 0;
		for (String url : listingArticle) {
			if (count < 4) {
				navigateToAMPUrl(url);
				brokenURLsEntities.addAll(checkBrokenLinksofAMPUrlForZeeNewIndia(baseURL, workbook, sheet));
				counter = getTotalBrokenLinks();
			}
			count++;
		}
		return brokenURLsEntities;
	}
	
	public List<BrokenURLsEntity> checkBrokenLinksForZeeNewsIndia(String baseURL, XSSFWorkbook workbook, XSSFSheet sheet) {
		List<String> mainURLs = getResourceURLListByAttribute(LISTING_ARTICLE, Constants.HREF_TAG, baseURL, false);
		return checkBrokenLinksForZeeNewsIndia(mainURLs, baseURL, workbook, sheet);
	}

	public int getBrokenLinks() {
		return counter;

	}
}
