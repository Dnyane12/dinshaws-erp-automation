package flowPack.setUpFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Test.fieldWiseTests.ItemMasterFieldWiseTests;
import pageObjects.setup.HomePage;
import utils.WaitHelper;

public class HomeFlow {
	 WebDriver driver;
	 HomePage homePage;
	 private static final Logger logger= LogManager.getLogger(ItemMasterFieldWiseTests.class);
	 
	 public HomeFlow(WebDriver driver) {
		logger.info("called HomeFlow constructor in HomeFlow");
		this.driver = driver;
		homePage = new HomePage(driver);
	}

	 
	//Test to click inventory module
		public void clickToInvModAndConfMstLink(){
			try {
				  logger.info("called clickToInventoryModule method in HomeFlow");
				  WaitHelper.waitForInvisibilityOfElementLocated(driver, homePage.getDotSpinner(), 10);		
				  homePage.clickInventoryModule();
				  
				  logger.info("clicking config mast link in inventory module.");
				  WaitHelper.waitForInvisibilityOfElementLocated(driver, homePage.getDotSpinner(), 10);
				  homePage.clickConfMstLink();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	 	 
		
		public void clickInvModAndStoreLink() {		
			WaitHelper.waitForClickable(driver,homePage.getInventoryMod(), 10);
			homePage.getInventoryMod().click();
			
			logger.info("ing and clicking store link");
			WaitHelper.waitForInvisibilityOfElementLocated(driver, homePage.getDotSpinner(), 10);
			WaitHelper.waitForRefreshAndClick(driver, homePage.getStoreLink(), 10);	
		}	
		
		
		
		public void clickInvModule() {		
			WaitHelper.waitForClickable(driver,homePage.getInventoryMod(), 10);
			homePage.getInventoryMod().click();
		}
		
		
		
	
	//flow to click to sales module
	 public void clickToSalesModule() throws InterruptedException {
		logger.info("called clickToSalesModule method in HomeFlow");
		WaitHelper.waitForClickable(driver, homePage.getSalesModuleLink(), 10);
		homePage.getSalesModuleLink();
		
		homePage.clickSalesModule();
		WaitHelper.waitForClickable(driver, homePage.getConfigMasterLink(), 10);
		homePage.getConfigMasterLink();
	}
	
	
	 
		public void clickSalesModuleAndTransLink() {
			logger.info("ing for sales module link and clicking");
			WaitHelper.waitForInvisibilityOfElementLocated(driver,homePage.getDotSpinner(), 10);
			
			WaitHelper.waitForRefreshAndClick(driver,  homePage.getSalesModuleLink(), 10);		
			logger.info("done clicking salesModuleLink");
				
			logger.info("Waiting for invisibility of dotSpinner.");
			WaitHelper.waitForInvisibilityOfElementLocated(driver,homePage.getDotSpinner(), 10);
			
			WaitHelper.waitForClickable(driver, homePage.getTransactionLink(), 10);
			homePage.getTransactionLink().click();
			logger.info("done clicking transactionLink");
		}
	 
	 
	 
	 
		
	//Test to click to sales module
	public void clickToSalesModule1() throws InterruptedException {
			logger.info("called clickToSalesModule method in HomeFlow");
			homePage.clickSalesModule();	
			homePage.clickTransLink();
		}
	
	
					
	public void clickAccModAndVoucherLink() {
		WaitHelper.waitForClickable(driver, homePage.getAccountModLink(), 10);
		homePage.getAccountModLink().click();
		
		WaitHelper.waitForInvisibilityOfElementLocated(driver,homePage.getDotSpinner(), 10);
		WaitHelper.waitForClickable(driver, homePage.getVoucherLink(), 10);
		homePage.getVoucherLink();
	}
	
		
	
	//Test to check the login entity and then chenge it to diary plant if it is not a login Entity
	public void validateLoginEntity() {		
		  WaitHelper.waitForClickable(driver, homePage.getLoginEntity(), 10);
		
		String loginEntityText =homePage.getLoginEntity().getText().trim();
		System.out.println("loginEntity :"+loginEntityText);
			
	  if(!loginEntityText.equals("Dairy Plant UAT")) {	
		  WaitHelper.waitForClickable(driver, homePage.getLoginEntity(), 10);
		  homePage.getLoginEntity().click();
		  
		  WaitHelper.waitForClickable(driver,homePage.getEntChangeSection(), 10);
		  WaitHelper.waitForClickable(driver,homePage.getDairyPlantEnt(), 10);
		  homePage.getDairyPlantEnt().click();
	  }else {
			System.out.println("loginEntityText :"+loginEntityText);
		}	
	}
	
	
	
	
	
}
