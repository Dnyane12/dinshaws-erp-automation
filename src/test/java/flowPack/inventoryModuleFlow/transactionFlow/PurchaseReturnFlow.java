package flowPack.inventoryModuleFlow.transactionFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import pageObjects.inventory.transaction.PurchaseReturnPage;
import pageObjects.setup.HomePage;
import utils.PropertyReader;
import utils.WaitHelper;

public class PurchaseReturnFlow {	
	WebDriver driver;
	LoginFlow loginFlow;
	HomeFlow homeFlow;
	PropertyReader propReader;	
	PurchaseReturnPage prnPages;
	HomePage homePage; 
	public static final Logger logger =LogManager.getLogger(PurchaseReturnPage.class);
	

	public PurchaseReturnFlow(WebDriver driver) {
		this.driver=driver;
		loginFlow=new LoginFlow(driver);
		homeFlow= new HomeFlow(driver);
		homePage = new HomePage(driver);
		propReader = new PropertyReader("InventoryModule/PurchaseReturnTestData.properties");
		prnPages= new PurchaseReturnPage(driver);
				 
	}
	
	
	public void prepEnvFromLogin() {
		logger.info("called creatingPurchaseOrder flow method in purchasReturnflow");	
		
		logger.info("Executing login flow");
		loginFlow.loginFlowCheck();
		
		//logger.info("validateLoginEntity method in Home flow is called to execute entity check");
		//homeFlow.validateLoginEntity();
		
		logger.info("calling clickToInventoryModule method in purchasReturnflow");
		homeFlow.clickInvModule();
				
		WaitHelper.waitForClickable(driver, prnPages.getPurchaseSubModLink(), 10);		
		prnPages.clickPurchaseSubModuleLink();
		logger.info("calling click Purchase SubMod Link method in purchasReturnflow");
				
		WaitHelper.waitForClickable(driver, prnPages.getPrnLink(), 10);		
		prnPages.clickPrnLink();
		logger.info("calling clicking Purchaase Return Note Link method in purchasReturnflow");
	}
	
	
	
	public void prepEnvToExeFromAcc() {
		prnPages.clickHomeIcon();
		homePage.clickInventoryModule();
		prnPages.clickPurchaseSubModuleLink();
		
		// Capture current URL before clicking
	    String previousUrl = driver.getCurrentUrl();
	    System.out.println("Previous URL: " + previousUrl);
	    
		prnPages.clickPrnLink();
		
		// Capture current URL after clicking
		 String currentUrl = driver.getCurrentUrl();
		    System.out.println("current Url: " + currentUrl);

		    WaitHelper.waitToDealWithCatche(driver,previousUrl);
			
		    // Capture current URL after clicking and waiting
		    String currentUrl1 = driver.getCurrentUrl();
		    System.out.println("current Url1: " + currentUrl1);
	}
	
	
		public String executingFlowOfPRN(String grnNo) {
								
			logger.info("clicking create New button");
	        prnPages.clickCreateNewButton();
	        
	        logger.info("selecting GRN No.");
	        WaitHelper.waitForInvisibilityOfElementLocated(driver, prnPages.getDotSpinner(), 10);	
	        prnPages.selectGrnNo(grnNo);
			//prnPages.selectGrnNo(propReader.getProperty("grnDropLabel"),propReader.getProperty("grnDropOption"));
			
			
			logger.info("clicking grn details tab");
			//WaitHelper.waitForClickable(driver, prnPages.getGrnDetailsTab(), 20);
			prnPages.clickGrnDetailsTab();
			
			logger.info("clicking edit button icon");
			WaitHelper.waitForClickable(driver, prnPages.getEditIcon(), 10);
			prnPages.clickEditButtonIcon();
			
			logger.info("entering return from store field value");
			WaitHelper.waitForClickable(driver, prnPages.getReturnFromStoreField(), 10);
			prnPages.enterReturnFromStore(propReader.getProperty("returnFromStoreFieldValue"));;
			
			logger.info("entering return from rej field value");
			WaitHelper.waitForClickable(driver, prnPages.getReturnFromRejField(), 10);
			prnPages.enterReturnFromRej(propReader.getProperty("returnFromRejFieldValue"));
			
			logger.info("clicking update button");
			WaitHelper.waitForClickable(driver, prnPages.getUpdateButton(), 10);
			prnPages.clickUpdateButton();
			
			logger.info("clicking submit button");
			WaitHelper.waitForClickable(driver, prnPages.getSubmitBtn(), 10);
			prnPages.clickSubmitButton();
			
			String prnNo=extractPrnNoFromUI();
			return prnNo;
					
		}
		
		
		public String extractPrnNoFromUI() {
			WaitHelper.waitForVisible(driver, prnPages.getSuccMsgField(), 10);
			String compSuccMsg= prnPages.getSuccMsgField().getText();
			String prnNo= compSuccMsg.substring(compSuccMsg.indexOf(":")+1).trim();
			logger.info("PRN No. generated is: "+prnNo);
			return prnNo;
		}
		
		
		public void flowToValidatePRNCalculation() {
			logger.info("called creatingPurchaseOrder flow method in purchasReturnflow");
			
			logger.info("Executing login flow");
			loginFlow.loginFlowCheck();
			
			logger.info("calling clickToInventoryModule method in purchasReturnflow");
			 WaitHelper.waitForInvisibilityOfElementLocated(driver, prnPages.getDotSpinner(), 10);
			homeFlow.clickInvModule();
			
			 WaitHelper.waitForInvisibilityOfElementLocated(driver, prnPages.getDotSpinner(), 10);
			
			WaitHelper.waitForClickable(driver, prnPages.getPurchaseSubModLink(), 10);
			prnPages.clickPurchaseSubModuleLink();
			logger.info("calling click Purchase SubMod Link method in purchasReturnflow");
			
			WaitHelper.waitForClickable(driver, prnPages.getPrnLink(), 10);
			prnPages.clickPrnLink();
			logger.info("calling clicking Purchaase Return Note Link method in purchasReturnflow");
	
	
}
		
		
		public boolean extractGRNSelStatus() {
			WaitHelper.waitForVisible(driver, prnPages.getGrnNoDrop(), 10);
		    boolean isGRNSel=  prnPages.getGrnNoDrop().isSelected();
			return isGRNSel;
		}

		
		public boolean extractVendorEnabilityStatus() {
			WaitHelper.waitForVisible(driver, prnPages.getVendorDropList(), 10);
		    boolean isVendorEnable = prnPages.getVendorDropList().isEnabled();
			return isVendorEnable;		    
		}
		
		public boolean extractVendorSelStatus() {
			WaitHelper.waitForVisible(driver, prnPages.getVendorDropList(), 10);
			boolean isVendorSel= prnPages.getVendorDropList().isSelected();
			return isVendorSel;
		}
		
		
//		public void selectGRNNo() {
//			WaitHelper.waitForClickable(driver, prnPages.getGrnNoDrop(), 10);
//			prnPages.getGrnNoDrop().click();
//			prnPages.getGrnNoDrop().sendKeys(propReader.getProperty("grnDropOption"));
//			
//			WaitHelper.waitForClickable(driver, prnPages.getGrnNoDropOptField(), 10);
//			prnPages.getGrnNoDropOptField().click();
//		}
		
		
		public void extractGRNDataFromDB() {
		String grnNo=propReader.getProperty("grnDropOption");
		String query="select t.ingh_doc_no,"
				+ "t.ingh_party_acnt,"
				+ "t.ingh_transport_mode,"
				+ "t.ingh_lr_date,"
				+ "t.ingh_invoice_no,"
				+ "t.ingh_invoice_dt,"
				+ "t.ingh_trade_name "
				+ " from inv_grn_hdr t "
				+ "where t.ingh_doc_no ="+grnNo;
		
		
		}
		
		

}


	


	