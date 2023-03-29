package demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zee.actions.WebAction;
import com.zee.base.Assertions;
import com.zee.constants.Constants;
import com.zee.webpages.ZeeNewsHomePage;
import com.zee.webpages.ZeeNewsIndiaPhotoPage;

public class demo1 extends WebAction{
	
	public void validation() {
		
		
	}
	@Test
	@Parameters("URL")
	public void test01_Demo (@Optional String url) throws InterruptedException, MalformedURLException, IOException {
		Assertions assertions = new Assertions();
		ZeeNewsHomePage homePage = new ZeeNewsHomePage();
		ZeeNewsIndiaPhotoPage photoPage = new ZeeNewsIndiaPhotoPage();
		
		testLogger.log(new Exception().getStackTrace()[0].getMethodName());
		testLogger.info("STEP : Navigate to the Page");
		navigateToPage(url);
		driver.findElement(By.xpath("//span[@class='signTxt']")).click();
		HttpURLConnection c=(HttpURLConnection)new URL(url)
				.openConnection();
				c.setRequestMethod("HEAD");
				c.connect();
				int r = c.getResponseCode();
				System.out.println(r);
				assertions.assertEquals(201, r);
		Thread.sleep(5000);
		String currenturl = driver.getTitle();
		System.out.println(currenturl);
		
		
		
	}

}
