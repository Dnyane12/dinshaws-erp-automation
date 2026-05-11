package Test.salesModuleTests.transTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import flowPack.salesModuleFlow.transactionFlow.MultiSaleOrderFlow;
import model.SaleOrderDBModel;
import pageObjects.sales.transaction.MultiSaleOrderPage;
import pageObjects.sales.transaction.SaleDispatchPage;
import utils.CommonDatabaseUtility;
import utils.DBUtilityForRoutePartyMapping;
import utils.DatabaseUtility;
import utils.PropertyReader;
import utils.WaitHelper;
import validation.DropdownValidator;
import validation.SaleOrderValidation;

public class MultiSaleOrderTest extends SetUp {
	MultiSaleOrderFlow saleOFlow;
	MultiSaleOrderPage saleObj;
	PropertyReader propReader;
	SaleDispatchPage saledisPage;
	DBUtilityForRoutePartyMapping dbUtil;
	private static final Logger logger = LogManager.getLogger(MultiSaleOrderTest.class);
	SoftAssert softAssert;
	Actions actions;
	SaleOrderValidation sOVal;
	
	@BeforeClass(groups = {"sales-flow"})
	public void objInti() {
		try {
			logger.info("Initilizing the objects in multi sale order test class.");
			saleOFlow = new MultiSaleOrderFlow(driver);
			saleObj = new MultiSaleOrderPage(driver);
			saledisPage = new SaleDispatchPage(driver);
			propReader = new PropertyReader("salesModule/MultiSaleOrderTestData.properties");
			dbUtil = new DBUtilityForRoutePartyMapping();
			saleOFlow.prepareEnvironment();
			softAssert = new SoftAssert();
			actions = new Actions(driver);
			sOVal =new SaleOrderValidation(driver);
			
			//DatabaseUtility.connectToDatabase(propReader.getProperty("url"), propReader.getProperty("username"),
					//propReader.getProperty("password"));
		} catch (Exception e) {
			logger.error("Object initialization failed. Aborting tests.", e);
			throw new RuntimeException(e); 
		}
	}

	
	//Test1: Test to validate multi sale order creation----working Properly.
	@Test(groups = {"sales-flow"},enabled=true)
	public void validateMultiSaleOrderCReation() {
		try {
			saleOFlow.createMultiSaleOrder();
			
			boolean suMsgDisStatus = saleObj.succMsgDisplayStatus();
			System.out.println(" multi sale order suMsgDisStatus:" + suMsgDisStatus);
			
			String expMsg= "Sale OrderSave successfully";
			String actSuccMsg= saleObj.getSuccessMsgText();
			
			softAssert.assertTrue(suMsgDisStatus, "Multi sale order Success message not displayed!");
			softAssert.assertEquals(actSuccMsg, expMsg, "Success message text mismatch after creating multi sale order!");
			softAssert.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	//Test2: Test to validate that the party list is coming routewise or not.----working Properly.
	@Test(enabled=false)
	public void validateRouteWisePartyList() {
		try {
			WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);
			logger.info("Spinner is not visible, proceeding...");
						
			WaitHelper.waitForClickable(driver, saleObj.getRouteSearchbox(), 10);
			logger.info("ing and selecting route");
			String uiSelectedRoute = saleObj.selectRoute(propReader.getProperty("routeDropOpt").trim());			
			logger.info("uiSelectedRoute :{}",uiSelectedRoute);
							
            String uiSelectedPartyCode= saleObj.selectParty(propReader.getProperty("partyDropLabel").trim(),propReader.getProperty("partyDropOpt").trim());
            logger.info("uiSelectedPartyCode: {}", uiSelectedPartyCode);
 			            
            Map<String, String> dbResult= saleObj.getDbResult(uiSelectedRoute,propReader.getProperty("entityCode").trim(),uiSelectedPartyCode);
			
			softAssert.assertEquals(dbResult.get("route_name"), uiSelectedRoute, "Route mismatch in DB");
			logger.info("Done executing assert statement for Route.");

			softAssert.assertEquals(dbResult.get("party_code"), uiSelectedPartyCode,
					"Selected party is NOT mapped to selected route");
			
			logger.info("Done executing assert statement for Party.");
			softAssert.assertAll();
			logger.info("Test completed: validateRouteWisePartyList");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	//Test3: Test to validate the sale order input values,to verify it has pricelist attached or not from its value.
	@Test(enabled = true)
	public void validateSaleOrderInputValues1() throws InterruptedException {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);
		
		WaitHelper.waitForClickable(driver, saleObj.getRouteSearchbox(), 10);
		logger.info("selecting route and party");
		saleOFlow.selectRouteAndParty();
		logger.info("clicking get order button");
		saleOFlow.clickGetOrderButtonAnd();

		saleObj.ForSOInputRowLoad();
		sOVal.validateGridInputsNotNR(saleObj,driver,softAssert);
	}

	
	
	//Test4: Test to validate that the pending record should not be visible on sale dispatch listing page.
	@Test(description="In case of pending sale order the sale order should not be observed in sale dispatch -search so no. in disaptch")
	public void searchSONoInDispatch() {
		try {
			saleOFlow.createMultiSaleOrder();
			
			WaitHelper.waitForClickable(driver, saleObj.getSaleDispatchLink(), 10);
			saleOFlow.openDisFormAndSelectRoute();
			logger.info("noDataMsgDisStatus must be displayed in dispatch on search for pending sale order");
			
			String saleOrderNoToSearch =sOVal.getSaleOrderNo();
			
			WaitHelper.waitForClickable(driver, saleObj.getSaleDisGlobalSearchBtn(), 10);
			boolean noDataMsgDisStatus = saleOFlow.searchSaleOrderNo(saleOrderNoToSearch);
			
			String soStatusDB= sOVal.getSaleOrderStatus();
             System.out.println("soStatusDB: "+soStatusDB);   //P    
			
			logger.info("checking no search data found message displayed on create screen of sale dispatch.");
			if(soStatusDB.equals("P")) {
				softAssert.assertTrue(noDataMsgDisStatus,"functionality not working properly,pending sale order no. must not be observed in sale dispatch!");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	//Test5:Test to validate the party code list is unique or not.
	@Test(description = "Validate party dropdown has no duplicate party codes")
	public void validatePartyDropForDuplicates() {

	    logger.info("Selecting route and opening party dropdown");
	    saleOFlow.selectRouteAndClickParty();

	    logger.info("Fetching all party codes");
	    List<String> partyCodes = saleOFlow.getAllPartyCodes();

	    logger.info("Validating duplicate party codes");
	    Assert.assertFalse(DropdownValidator.hasDuplicateValues(partyCodes),"Duplicate party code found in party dropdown");
	}

	
	

	//Test6:
	@Test(description = "Validate latest sales order status in DB after order creation",enabled=true)
	public void validateSaleOrderStatusWithDB() {
	logger.info("calling createMultiSaleOrder method in Test class.");	
	saleOFlow.createMultiSaleOrder();
		
	logger.info("Calling getLatestSalesOrderForParty method in Test class,where fetching latest sale order from database.");
	SaleOrderDBModel dbOrder =
			saleOFlow.getLatestSalesOrderForParty(propReader.getProperty("partyId"));

	String expectedStatus="P";
	System.out.println("expectedStatus :"+ expectedStatus+", actualStatusInDB :"+ dbOrder.getStatus());
	System.out.println("sale order no from DB:"+dbOrder.getSaleOrderNo());
	
	logger.info("validating the expected sale order status with sale order status in DB.");
	Assert.assertEquals("P", dbOrder.getStatus(),"Sale Order status mismatch in DB");
	
	}
	
	

	
	// Test to validate party drop list data is correct or not.
	@Test(enabled = false)
	public void validatePartyMstActiveFieldStatus() {

	}

	// Test to validate search function for party dropdown
	@Test(enabled = false)
	public void validatePartyDropSearchFun() {

	}

	@Test(enabled = false)
	public void validateDocTypeField() {
		saleOFlow.checkDocTypeField();
	}
	
	@Test(enabled = false)
	public void validatePartyBalnceAndFassai() {
		
	}
	
	@Test(enabled = false)
	public void validateQuickSaveFun() {
		
	}
	//Bug| Local uat- Direct tax invoice-| Getting Net amount greater than customer balance error message on submit button.
}
