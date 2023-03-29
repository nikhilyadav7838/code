package com.zee.manager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.zee.utils.GenericUtility;
import com.zee.utils.TestLogger;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class MobDriverManager {

    private WebDriver driver;
    private static MobDriverManager mobDriver;

    public MobDriverManager(){
    	/*
    	 * This is Blank Constructor
    	 */
    }

    public static MobDriverManager mobDriverManager(){
        if(mobDriver == null)
            mobDriver = new MobDriverManager();
        return mobDriver;
    }
    
    private DesiredCapabilities getDesiredCapabilities(String deviceName, String browserName, String deviceType) {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--disable-notifications");
    	options.setPageLoadStrategy(PageLoadStrategy.NONE);
    	DesiredCapabilities internalCapabilities = new DesiredCapabilities();
    	internalCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
    	internalCapabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
    	internalCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    	internalCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
    	internalCapabilities.setCapability("chromedriverExecutable", GenericUtility.getGenericUtility().getChromeExePathForWindow());
    	internalCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    	internalCapabilities.setCapability("autoAcceptAlerts", true);
    	
    	LoggingPreferences logP = new LoggingPreferences();
        logP.enable(LogType.PERFORMANCE, Level.ALL);
        internalCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logP);
        if(deviceType.equalsIgnoreCase("AndroidEmulator") || deviceType.equalsIgnoreCase("AndroidReal")) {
        	internalCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        }else if (deviceType.equalsIgnoreCase("IOSSimulator") || deviceType.equalsIgnoreCase("IOSReal")) {
        	internalCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        }
        return internalCapabilities;
    }

    public WebDriver initDriver(String appiumURL, String deviceType, String deviceName, String browserName){
        try {
            driver = new AndroidDriver(new URL(appiumURL), getDesiredCapabilities(deviceName, browserName, deviceType));
        } catch (MalformedURLException e) {
           TestLogger.getInstance().error("There is some issue with creating the connection, " + e.getMessage());
        }
        return driver;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
