package flowPack.accountsModuleFlow.masterFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import flowPack.setUpFlow.LoginFlow;
import pageObjects.accounts.master.AccountPayablePage;
import pageObjects.accounts.master.JournalVoucherMultiLinePage;
import utils.PropertyReader;
import utils.WaitHelper;

public class JournalVoucherMultiLineFlow {
	WebDriver driver;
	JournalVoucherMultiLinePage jvMulPage;
	LoginFlow loginFlow;
	PropertyReader propReader;
	private static final Logger logger = LogManager.getLogger(AccountPayableFlow.class);
	
	//Constructor to initialize the driver and page objects
	public JournalVoucherMultiLineFlow(WebDriver driver){
		logger.info("called JournalVoucherMultiLineFlow constructor");
		logger.info("Initializing JournalVoucherMultiLineFlow with WebDriver instance");
		this.driver= driver;
		loginFlow= new LoginFlow(driver);
		jvMulPage= new JournalVoucherMultiLinePage(driver);
		propReader =new PropertyReader("accountsModule/JVMultilineTestData.properties");
	}
	
			
		public void jvMultiLineFlow() {
			executeLogin();		
			logger.info("Navigating to account module");
			jvMulPage.clickAccountModule();
			
			logger.info("Clicking on Voucher link");
			jvMulPage.clickVoucherLink();
						
			logger.info("Clicking on Journal Voucher Multiline link");
			//jvMulPage.clickJvMultiPageLink();
			jvMulPage.searchSelectFormName();
			
			
			logger.info("Clicking on Create New button");
			jvMulPage.clickCreateNewButton();	
			
			logger.info("Selecting series from dropdown");
			jvMulPage.selectSeries(propReader.getProperty("seriesDopValue").trim());
			
			logger.info("Selecting credit account from dropdown");
			jvMulPage.selectAccount(propReader.getProperty("accCodeDropValue").trim());
			
			logger.info("Entering voucher amount");
			jvMulPage.enterVoucherAmount(propReader.getProperty("voucherInputLable").trim(), propReader.getProperty("voucherInputIdPart").trim(), propReader.getProperty("voucherAmount").trim());
			
			logger.info("Entering narration");
			jvMulPage.enterNarration(propReader.getProperty("narrationInputLable").trim(), propReader.getProperty("narrationInputIdPart").trim(), propReader.getProperty("narrationValue").trim());
			
			logger.info("Entering bill number");
			jvMulPage.enterBillNo(propReader.getProperty("billNoInputLable").trim(), propReader.getProperty("billNoInputIdPart").trim(), propReader.getProperty("billNoValue").trim());
			
			logger.info("Selecting transaction code from dropdown");
			jvMulPage.selectTxnCode(propReader.getProperty("txnCodeValue").trim());
			
			logger.info("Selecting debit account from dropdown");
			jvMulPage.selectDebitToAccount(propReader.getProperty("debitToAccount").trim());
			
			logger.info("clicking add button to add the line item");
			jvMulPage.clickAddButton();
			
			boolean isGridRowDis=jvMulPage.isGridRowAdded();
			System.out.println("Is grid row added after clicking add button: "+isGridRowDis);
			
			logger.info("Clicking on submit button");
			if(isGridRowDis) {
				logger.info("Grid row added successfully, proceeding to click submit button");
				jvMulPage.clickSubmitButton();
			} else {
				logger.error("Grid row was not added successfully, cannot proceed to click submit button");
			}
			
		}
		
		
		public void executeLogin() {
			logger.info("Logging in with valid credentials");
			loginFlow.loginFlowCheck();
			
			logger.info("Waiting for any loading spinners to disappear after login");
			WaitHelper.waitForInvisibilityOfElementLocated(driver,jvMulPage.getDotSpinner(),20);
		}
	
	
	
}
