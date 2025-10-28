package com.ui.tests;
import static com.constants.Browser.*;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.pojos.User;
import com.utility.LoggerUtility;


@Listeners(com.ui.listeners.TestListener.class)

public class LoginTest extends TestBase{
	
	 /*
     * 1. Test script has to be small!!
     * 2. You cannot have conditional statements, loops and try catch in the test methods
     * 3. test scripts should only follow the test steps
     * 4. no local variables(reduce)
     * 5. Atleast one assertion
     */


	
	
@Test(description="Verifies with the valid user is able to login into the application", groups ={"e2e","sanity"}, 
dataProviderClass=	com.ui.dataproviders.LoginDataProvider.class, dataProvider="LoginTestDataProvider")
public void loginTest(User user) {
	   
	
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),"Ruchi Malik");
		
	}


@Test(description="Verifies with the valid user is able to login into the application", groups ={"e2e","sanity"}, 
dataProviderClass=	com.ui.dataproviders.LoginDataProvider.class, dataProvider="LoginTestExcelDataProvider",retryAnalyzer=com.ui.listeners.MyRetryAnalyser.class)
public void loginExcelest(User user) {
   
Logger logger=LoggerUtility.getLogger(this.getClass());

	assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),"Ruchi Malik");
	
	
}


}
