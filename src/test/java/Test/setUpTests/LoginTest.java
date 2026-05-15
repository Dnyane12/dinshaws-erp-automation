package Test.setUpTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import flowPack.setUpFlow.LoginFlow;
import pageObjects.setup.LoginPage;
import utils.WaitHelper;

public class LoginTest extends SetUp{
	LoginPage loginPage;
	LoginFlow loginFlow;
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	
	
	@BeforeClass(groups = {"sales-flow"})
	public void objInti() {
		logger.info("calling objInti(),@BeforeClass method of LoginTest.");
		loginPage = new LoginPage(driver);
		loginFlow = new LoginFlow(driver);		
	}

	
//Test to validate login is succussesful or not.	
@Test(groups = {"sales-flow"})
public void validateLogin() {
	try {
		logger.info("Calling loginFlowCheck() method of LoginFlow class in Login Test");
		loginFlow.loginFlowCheck() ;
		
		String expMsg ="Login Successful";
		
		logger.info("Extracting the actual login message through getText method");
		WaitHelper.waitForClickable(driver, loginPage.getLoginSussMessageField(), 10);		
		String actualMsg= loginPage.getLoginSussMessageField().getText();
		
		logger.info("Validating actual and expected Login Message using assert");		
		Assert.assertEquals(actualMsg,expMsg,"The login Is unsuccussesful!");
		
	} catch (Exception e) {
		e.printStackTrace();
	}	
  }

}
