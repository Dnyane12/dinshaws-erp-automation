package Test.accountsTest.masterTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Test.setUpTests.SetUp;
import flowPack.accountsModuleFlow.masterFlow.AccountPayableFlow;
import flowPack.accountsModuleFlow.masterFlow.JournalVoucherMultiLineFlow;
import pageObjects.accounts.master.AccountPayablePage;
import pageObjects.accounts.master.JournalVoucherMultiLinePage;
import utils.PropertyReader;

public class JournalVoucherMultiLineTest extends SetUp{
	JournalVoucherMultiLinePage jvMultiPage;
	JournalVoucherMultiLineFlow jvMultiFlow;
	PropertyReader propReader;
	SoftAssert softAssert;
	private static final Logger logger = LogManager.getLogger(JournalVoucherMultiLineTest.class);
	
	
	@BeforeClass
	public void objInit() {
		jvMultiPage = new JournalVoucherMultiLinePage(driver);
		jvMultiFlow =new JournalVoucherMultiLineFlow(driver);
		propReader =new PropertyReader("accountsModule/JVMultilineTestData.properties");
		softAssert= new SoftAssert();
	}
		

	@Test
	public void JVMultilineValidation() {
		logger.info("Executing accountPayablecreateVali test");
		jvMultiFlow.jvMultiLineFlow();
		
		String actualMsg= jvMultiPage.getSuccMssage();
		String expectedMsg= "Voucher Created successfully";	
		
		softAssert.assertTrue(actualMsg.startsWith(expectedMsg),"JV multiline creation failed");	
		softAssert.assertAll();
	}
	
	
	
	@Test(enabled=false,description="Test to validate the dependency of debit To account dropdown enability on complete info filiing of Credit To account")
	public void validateCreditToDebitAccDependency() {
		//on after selecting credit to account ,directly select debit to account ,
		//without filling all credit to account details,it should be disabled.
		logger.info("Executing validateCreditToDebitAccDependency test");
		
		jvMultiFlow.executeLogin();			
		jvMultiPage.clickAccountModule();
		jvMultiPage.clickVoucherLink();
		jvMultiPage.clickJvMultiPageLink();
		jvMultiPage.clickCreateNewButton();		
		jvMultiPage.selectAccount(propReader.getProperty("accCodeDropValue"));
		
		boolean enabilityStatus= jvMultiPage.getDebitToAccountDrop().isEnabled();
		softAssert.assertFalse(enabilityStatus,"Dependency of Debit To account enability on Credit To account info filling not working as expected !");
		//accountPayablePage.selectDebitToAccount(propReader.getProperty("debitToAccount"));
	}
}
