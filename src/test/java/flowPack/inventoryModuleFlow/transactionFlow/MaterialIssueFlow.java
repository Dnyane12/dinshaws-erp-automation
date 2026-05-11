package flowPack.inventoryModuleFlow.transactionFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import pageObjects.inventory.transaction.MaterialIssuePage;
import utils.PropertyReader;


public class MaterialIssueFlow {
WebDriver driver;
MaterialIssuePage materialIssuePage;
HomeFlow homeFlow;
LoginFlow loginFlow;
Logger logger;
PropertyReader propReader;


public MaterialIssueFlow(WebDriver driver) {
	this.driver = driver;
	materialIssuePage = new MaterialIssuePage(driver);
	propReader = new PropertyReader("InventoryModule/MaterialIssueTestData.properties");
	loginFlow = new LoginFlow(driver);
	homeFlow = new HomeFlow(driver);
	logger = LogManager.getLogger(MaterialIssueFlow.class);
}

public void createMaterialIssue() {	
	logger.info("called validateMRFlowSteps method in MaterialRequisitionFlow");
	logger.info("Executing login flow before validating material requisition flow");
	loginFlow.loginFlowCheck();
	
	logger.info("Login successful, now navigating to inventory module and store link");
	homeFlow.clickInvModAndStoreLink();	
	
	materialIssuePage.clickMatIssueLink();
	logger.info("Clicked on Material Issue menu");
	
	materialIssuePage.clickCreateNewButton();
	logger.info("Clicked on Create New button");
	
	materialIssuePage.selectFromLocForDrop(propReader.getProperty("FromLocation"));
	logger.info("Selected From Location: " + propReader.getProperty("FromLocation"));
	
	materialIssuePage.selectRequiNoFromDrop((propReader.getProperty("RequisionNo")).trim());
	logger.info("Selected Requisition No.: " + (propReader.getProperty("RequisionNo")).trim());
		
	materialIssuePage.clickArrowDown();
	logger.info("done clicking arrow down icon");
	
	
	materialIssuePage.selectItemFromDrop(propReader.getProperty("Item"));
	logger.info("Selected Item: " + propReader.getProperty("Item"));
	
	materialIssuePage.selectBatchFromPopup();
	logger.info("Selected Batch");
	
//	materialIssuePage.selectExpiryDate(propReader.getProperty("expiryDate"));
//	logger.info("Selected Expiry Date: " + propReader.getProperty("expiryDate"));
	
	materialIssuePage.enterAdjustedQty(propReader.getProperty("AdjustedQtyValue"));
	logger.info("Entered Adjusted Qty: " + propReader.getProperty("AdjustedQtyValue"));
	
	materialIssuePage.clickSaveButton();
	logger.info("Clicked on Save button");
	
	materialIssuePage.clickSubmitButton();
	logger.info("Clicked on Submit button");
	
	logger.info("Material Issue form submitted successfully, now extracting material Issue number from the sucess popup");
	materialIssuePage.extractMaterIssueNo();
}


}
