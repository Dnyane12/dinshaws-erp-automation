package Test.inventoryModuleTests.transactionTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import flowPack.inventoryModuleFlow.transactionFlow.MaterialIssueFlow;
import flowPack.inventoryModuleFlow.transactionFlow.MaterialRequisitionFlow;
import pageObjects.inventory.transaction.MaterialIssuePage;
import pageObjects.inventory.transaction.MaterialRequisitionPage;

public class MaterialIssueTest extends SetUp{
	MaterialIssuePage matIssuePage;
	MaterialIssueFlow matIssueFlow;
	SoftAssert softAssert;
	Logger logger;
	
	@BeforeClass
	public void initializeObjects() {
		matIssuePage = new MaterialIssuePage(driver);
		matIssueFlow = new MaterialIssueFlow(driver);
		softAssert = new SoftAssert();
		logger= LogManager.getLogger(MaterialIssueTest.class);
	}
	
	
	@Test(description="Material Issue Flow")
	public void validateMaterialIssueFlow() {
		matIssueFlow.createMaterialIssue();
	
		logger.info("Validating success message after submitting material requisition form");
		String expectedSuccMsg = "Transaction Created successfully,";
		String actualSuccMsg = matIssuePage.extractSuccMsg();
		
		softAssert.assertTrue(matIssuePage.getSuccessMsg().isDisplayed(),"Success message is not displayed");
		softAssert.assertEquals(actualSuccMsg, expectedSuccMsg, "Success message did not match expected value");
		softAssert.assertAll();
	}
}
