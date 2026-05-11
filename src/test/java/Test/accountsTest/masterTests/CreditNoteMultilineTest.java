package Test.accountsTest.masterTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import flowPack.accountsModuleFlow.masterFlow.CreditNoteFlow;
import flowPack.accountsModuleFlow.masterFlow.CreditNoteMultilineFlow;
import pageObjects.accounts.master.CreditNoteMultilinePage;
import pageObjects.accounts.master.CreditNotePage;

public class CreditNoteMultilineTest extends SetUp{
	CreditNoteMultilinePage creditNoteMlPage;
	CreditNoteMultilineFlow creditNoteMlFlow;
	SoftAssert softAssert;
	Logger logger = LogManager.getLogger(CreditNoteMultilineTest.class);
	
	@BeforeClass
	public void objInit() {
		logger.info("Initializing the objects of CreditNoteMultilineTest class");
		creditNoteMlPage = new CreditNoteMultilinePage(driver);
		creditNoteMlFlow =new CreditNoteMultilineFlow(driver);
		softAssert= new SoftAssert();
	}
		
	@Test
	public void validateCreditNoteMlCeation() {
		logger.info("Starting of cashReceiptcreateVali test case");
		creditNoteMlFlow.creditNoteMlFlow();
		
		String actualMsg= creditNoteMlPage.getSuccMssage();
		String expectedMsg= "Voucher Created successfully";	
		
		softAssert.assertTrue(actualMsg.startsWith(expectedMsg),"Credit Note Multiline creation failed");	
		softAssert.assertAll();
	}
}
