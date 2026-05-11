package Test.accountsTest.masterTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Test.setUpTests.SetUp;
import flowPack.accountsModuleFlow.masterFlow.CreditNoteFlow;
import flowPack.accountsModuleFlow.masterFlow.DebitNoteFlow;
import pageObjects.accounts.master.CreditNotePage;
import pageObjects.accounts.master.DebitNotePage;

public class CreditNoteTest extends SetUp{
	CreditNotePage creditNotePage;
	CreditNoteFlow creditNoteFlow;
	Logger logger = LogManager.getLogger(CreditNoteTest.class);
	
	@BeforeClass
	public void objInit() {
		logger.info("Initializing the objects of CreditNoteTest class");
		creditNotePage = new CreditNotePage(driver);
		creditNoteFlow =new CreditNoteFlow(driver);
	}
		
	@Test
	public void validateCreditNoteCeation() {
		logger.info("Starting of validateCreditNoteCeation test case");
		creditNoteFlow.creditNoteFlow();
	}
}
