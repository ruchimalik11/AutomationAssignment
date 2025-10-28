package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility{
	Logger logger= LoggerUtility.getLogger(this.getClass());
	
	
	

	
	public HomePage(Browser  browser, boolean isHeadless) {
		super(browser, isHeadless);
		
		//goToWebsite(readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJson(QA).getUrl());
	}

	public HomePage(WebDriver driver) {
		
		super(driver);
		goToWebsite(JSONUtility.readJson(QA).getUrl());

	}
	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(), \"Sign in\")]");//instance variable
	
	
	public LoginPage goToLoginPage() {
        logger.info("Trying to perform click to go to Sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage= new LoginPage(getDriver());
		return loginPage;
		
	}
	public void quit() {
	    WebDriver currentDriver = driver.get();
	    if (currentDriver != null) {
	        logger.info("Closing the browser session...");
	        currentDriver.quit();
	        driver.remove(); // important to clean up ThreadLocal
	    } else {
	        logger.warn("No active WebDriver session found to quit.");
	    }

}}
