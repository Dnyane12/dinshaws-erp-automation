package flowPack.setUpFlow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import pageObjects.setup.LoginPage;
import utils.PropertyReader;

public class LoginFlow{
	WebDriver driver;
	LoginPage loginPage; 
	PropertyReader prop ;
	private static final Logger logger = LogManager.getLogger(LoginFlow.class);	
	
	
	public LoginFlow(WebDriver driver) {		
		logger.info("called LoginFlow constructor in LoginFlow");
        this.driver = driver;
        loginPage = new LoginPage(driver);	    
	    prop = new PropertyReader("SetUp/application.properties");
    }
	
	
	public void loginFlowCheck() {
		try {							
			logger.info("called loginFlowCheck method in LoginFlow");			
			driver.manage().window().maximize();		
			driver.get(prop.getProperty("url"));
			
			//logger.info("clicking advance button as the url is not safe.");
			//loginPage.clickAdvanceBtn();
						
			logger.info("Entering the username");
			loginPage.enterUsernameField(prop.getProperty("username"));
			
			logger.info("Entering Password");
			loginPage.enterPasswordField(prop.getProperty("password"));
			
			logger.info("clicking login button");
			loginPage.clickLoginButton();
		} catch (Exception e) {
			System.out.println("Login Unsuccussesful!");
			e.printStackTrace();
		}
	}
		
}
