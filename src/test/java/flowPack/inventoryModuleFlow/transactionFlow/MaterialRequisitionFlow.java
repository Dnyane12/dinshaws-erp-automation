package flowPack.inventoryModuleFlow.transactionFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import pageObjects.inventory.transaction.MaterialRequisitionPage;
import utils.PropertyReader;

public class MaterialRequisitionFlow {
WebDriver driver;
MaterialRequisitionPage matReqPage;
PropertyReader propReader;
LoginFlow loginFlow;
HomeFlow homeFlow;
Logger logger;

public MaterialRequisitionFlow(WebDriver driver) {
	this.driver = driver;
	matReqPage = new MaterialRequisitionPage(driver);
	propReader = new PropertyReader("InventoryModule/MaterialRequisitionTestData.properties");
	loginFlow = new LoginFlow(driver);
	homeFlow = new HomeFlow(driver);
	logger = LogManager.getLogger(MaterialRequisitionFlow.class);
}


public void validateMRFlowSteps() {
	logger.info("called validateMRFlowSteps method in MaterialRequisitionFlow");
	logger.info("Executing login flow before validating material requisition flow");
	loginFlow.loginFlowCheck();
	
	logger.info("Login successful, now navigating to inventory module and store link");
	homeFlow.clickInvModAndStoreLink();	
	
	logger.info("Navigating to material requisition page");
	matReqPage.clickMatReqLink();
	
	logger.info("Clicking on create new button to start material requisition flow");
	matReqPage.clickCreateNewButton();
	
	logger.info("selecting requisition for dropdown value");
	matReqPage.selectRequiForDrop(propReader.getProperty("reqForDropValue").trim());
	
	logger.info("selecting request to drop value");
	matReqPage.selectRequestToDrop(propReader.getProperty("reqToDropValue").trim());
	
	logger.info("entering remark in remark field");
	matReqPage.enterRemark(propReader.getProperty("remarkValue").trim());
	
	logger.info("selecting item drop value");
	matReqPage.selectItemDrop(propReader.getProperty("itemDropValue").trim());
	
	logger.info("entering quantity value");
	matReqPage.enterQuantity(propReader.getProperty("quantityValue").trim());
	
	logger.info("selecting purpose drop value");
	matReqPage.selectPurposeDrop(propReader.getProperty("purposeDropValue").trim());
	
	logger.info("selecting cost center drop value");
	matReqPage.selectCostCenterDrop(propReader.getProperty("costcenterDropValue").trim());
	
	
	logger.info("clicking add button to add the material requisition line item");
	matReqPage.clickAddButton();
	
	logger.info("clicking submit button to submit the material requisition form");
	matReqPage.clickSubmitButton();
	
	logger.info("Material requisition form submitted successfully, now extracting material requisition number from the sucess popup");
	matReqPage.extractMaterRequiNo();
}
}
