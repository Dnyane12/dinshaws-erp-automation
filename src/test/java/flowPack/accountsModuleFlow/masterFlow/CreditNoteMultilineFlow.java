package flowPack.accountsModuleFlow.masterFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import flowPack.setUpFlow.LoginFlow;
import pageObjects.accounts.master.CreditNoteMultilinePage;
import pageObjects.accounts.master.CreditNotePage;
import utils.PropertyReader;
import utils.WaitHelper;

public class CreditNoteMultilineFlow {
	WebDriver driver;
	CreditNoteMultilinePage CreditNoteMlPage;
	LoginFlow loginFlow;
	PropertyReader propReader;
	private static final Logger logger = LogManager.getLogger(CreditNoteMultilineFlow.class);

		public CreditNoteMultilineFlow(WebDriver driver) {
			this.driver=driver;	
			loginFlow= new LoginFlow(driver);
			CreditNoteMlPage = new CreditNoteMultilinePage(driver);
			propReader =new PropertyReader("accountsModule/CreditNoteMultilineTestData.properties");
	    }
			
		public void creditNoteMlFlow() {
			executeLogin();	
			logger.info("Navigating to account module");
			CreditNoteMlPage.clickAccountModule();
			
			logger.info("Navigating to voucher link");
			CreditNoteMlPage.clickVoucherLink();
			
			logger.info("Navigating to credit note multiline link");
			CreditNoteMlPage.searchSelectFormName();
			
			logger.info("Clicking on create new button");
			CreditNoteMlPage.clickCreateNewButton();
			
			logger.info("Selecting value from credit to account dropdown");
			CreditNoteMlPage.selectCreditToAccount(propReader.getProperty("accCodeDropValue"));
			
			logger.info("Selecting value from txn code dropdown");
			CreditNoteMlPage.selectTxnCode(propReader.getProperty("txnCodeValue"));
			
			logger.info("Entering bill no");
			CreditNoteMlPage.enterBillNo(propReader.getProperty("billNoInputLable"), propReader.getProperty("billNoInputIdPart"), propReader.getProperty("billNoValue"));
			//CreditNoteMlPage.selectDebitToAccount(propReader.getProperty("debitToAccount"));
			
			logger.info("Entering voucher amount");
			CreditNoteMlPage.enterVoucherAmount(propReader.getProperty("voucherInputLable"), propReader.getProperty("voucherInputIdPart"), propReader.getProperty("voucherAmount"));
			
			logger.info("Entering narration");
			CreditNoteMlPage.enterNarration(propReader.getProperty("narrationInputLable"), propReader.getProperty("narrationInputIdPart"), propReader.getProperty("narrationValue"));			
			
			logger.info("Clicking on add button to add the line item");
			CreditNoteMlPage.clickAddButton();
			
			logger.info("Clicking on submit button to submit the voucher");
			CreditNoteMlPage.clickSubmitButton();
		}
		
		public void executeLogin() {
			logger.info("Executing login flow for CreditNoteMultilineFlow");
			loginFlow.loginFlowCheck();
			WaitHelper.waitForInvisibilityOfElementLocated(driver,CreditNoteMlPage.getDotSpinner(),20);
		}
}
