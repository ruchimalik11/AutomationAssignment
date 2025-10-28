package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.constants.Browser;

public abstract class BrowserUtility {
	
	protected static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	Logger logger= LoggerUtility.getLogger(this.getClass());

	
	
	

	public WebDriver getDriver() {
		return driver.get();
	}

	

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);;//initialise the instance variable
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching browser for " +browserName);

		if(browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());
		} else if(browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());
		}
		else {
			logger.error("Choose either edge or chrome....");

			System.err.print("choose either edge or chrome....");
		}
	}
	
	public BrowserUtility(Browser browserName, boolean isHeadless ){
		logger.info("Launching browser for " +browserName);
		if(browserName==Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			driver.set(new ChromeDriver(options));
			}
			
			else {
				driver.set(new ChromeDriver());
			}
		} else if(browserName==Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options= new EdgeOptions();
				options.addArguments("--headlesss=old");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			}
			else {
				driver.set(new EdgeDriver());
			}
			
			driver.set(new EdgeDriver());
		} else if(browserName==Browser.FIREFOX) {
			
	      driver.set(new FirefoxDriver());
		}
		
		else {
			logger.error("Choose either edge or chrome....");
			System.err.print("choose valid browser....");
		}
	}
	
	public void goToWebsite(String url) {
		logger.info("Visiting the website " +url);
		driver.get().get(url);	
	}
	
	public void maximizeWindow() {
		logger.info("Maximising the browser window");

		driver.get().manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		logger.info("Finding element with the locator " +locator);

		WebElement element=driver.get().findElement(locator);//find element
		logger.info("Element found and now performing click ");

		element.click();
	}
	
	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator " +locator);

		WebElement element=driver.get().findElement(locator);
		logger.info("Element found and now trying to enter text " +textToEnter);
		element.sendKeys(textToEnter);
		
	}
	
	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator " +locator);

		WebElement element= driver.get().findElement(locator);
		logger.info("Element found and now returning the visible text " +element.getText());

		return element.getText();
	}
	
public String takesScreenshot(String name) {
		
		TakesScreenshot screenshot=(TakesScreenshot)driver.get();
		File screenshotdata=screenshot.getScreenshotAs(OutputType.FILE);
		Date date= new Date();
		SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
		String timeStamp= format.format(date);
		String path=System.getProperty("user.dir") + "/screenshots/" +name+ " - " +timeStamp+".png";
		File screenshotFile= new File(path);
		screenshotFile.getParentFile().mkdirs();
		try {
			FileUtils.copyFile(screenshotdata, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
