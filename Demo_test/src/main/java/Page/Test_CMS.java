package Page;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Test_CMS {
	public static void main(String[] args) throws InterruptedException {
		
	
	ChromeOptions options =new ChromeOptions();
	options.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver","C:\\Users\\nikhil.yadav\\Desktop\\chromedriver.exe");
    WebDriver driver = new ChromeDriver(options);
    driver.get("https://qa.turbineproject.in/#/dashboard/story");
    driver.manage().window().maximize();
    driver.findElement(By.xpath("//img[@class='ms-logo']")).click();
    Set <String> windows=driver.getWindowHandles();
    Iterator<String> it =windows.iterator();
    String p = it.next();
    String c = it.next();
    driver.switchTo().window(c);
    Thread.sleep(5000);
    
    
   driver.findElement(By.xpath("//input[@type='email']")).sendKeys("nikhil.yadav@India.com");
   driver.findElement(By.xpath("//input[@type='submit']")).click();
   driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Sar@1313");
   Thread.sleep(2000);
   driver.findElement(By.xpath("//input[@type='submit']")).click();
   Thread.sleep(1000);
   driver.findElement(By.xpath("//input[@type='submit']")).click();
   
   driver.switchTo().window(p);
   
   Thread.sleep(6000);
   
   driver.findElement(By.xpath("//button[@class='createBtn']")).click();
   Thread.sleep(2000);

   driver.findElement(By.xpath("//button[@class='createSlideBtn']")).click();
   
   driver.close();
   
    
	} 
	

}
	
