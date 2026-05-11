package Test.inventoryModuleTests.transactionTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import flowPack.inventoryModuleFlow.transactionFlow.MaterialRequisitionFlow;
import pageObjects.inventory.transaction.MaterialRequisitionPage;

public class MaterialRequisitionTest extends SetUp{
	MaterialRequisitionPage matReqPage;
	MaterialRequisitionFlow matReqFlow;
	SoftAssert softAssert;
	Logger logger;
	
	@BeforeClass
	public void initializeObjects() {
		matReqPage = new MaterialRequisitionPage(driver);
		matReqFlow = new MaterialRequisitionFlow(driver);
		softAssert = new SoftAssert();
		logger= LogManager.getLogger(MaterialRequisitionTest.class);
	}
	
	
	@Test(description="Material Requisition Flow")
	public void validateMaterialRequisitionFlow() {
		matReqFlow.validateMRFlowSteps();
	
		logger.info("Validating success message after submitting material requisition form");
		String expectedSuccMsg = "Material Requisition Created successfully";
		String actualSuccMsg = matReqPage.extractSuccMsg();
		
		softAssert.assertTrue(matReqPage.getSuccessMsg().isDisplayed(),"Success message is not displayed");
		softAssert.assertEquals(actualSuccMsg, expectedSuccMsg, "Success message did not match expected value");
		softAssert.assertAll();
	}
	
	
}
