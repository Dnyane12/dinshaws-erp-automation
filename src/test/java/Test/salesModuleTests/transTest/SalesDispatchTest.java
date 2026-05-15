package Test.salesModuleTests.transTest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import flowPack.salesModuleFlow.transactionFlow.SaleDispatchFlow;
import helpers.salesModule.SaleOrderDBHelper;
import models.DispatchStockColorRow;
import models.SaleOrderDBModel;
import pageObjects.sales.transaction.SaleDispatchPage;
import utils.DatabaseUtility;
import utils.PropertyReader;
//import utils.UtilityToValiStockAvailableForItem;
import utils.WaitHelper;

public class SalesDispatchTest extends SetUp {
	SaleDispatchPage saledisPage;
	SaleDispatchFlow saleDisFlow;
	SoftAssert softAssert;
	PropertyReader propReader;
	private static Logger logger = LogManager.getLogger(SalesDispatchTest.class);
	
	
	@BeforeClass(groups = {"sales-flow"})
	public void objInitilization() throws InterruptedException {
		try {
			saledisPage = new SaleDispatchPage(driver);
			saleDisFlow = new SaleDispatchFlow(driver);
			propReader = new PropertyReader("salesModule/SaleDispatchTestData.peoperties");
			softAssert = new SoftAssert();
			saleDisFlow.prapareEnvStaringFromLogin();
			//DatabaseUtility.connectToDatabase(propReader.getProperty("dbURL"), propReader.getProperty("username"), propReader.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

			
	//Test 1: Test to validate the sale dispatch flow.
	@Test(groups = {"sales-flow"})
	public void validateSaleDispatchFlow() {
		try {		
			logger.info("calling createSaleDispatchEntry method of flow class");
			 String dispatchNo= saleDisFlow.createSaleDispatchEntry(5);
			 
			 logger.info("Storing dispatch no to use in another test class.");
			 //ITestContext context
			 //context.setAttribute("dispatchNo", dispatchNo);
			 
			String actuSuccMsg= saledisPage.getSuccessMessage();			
			String expectedMsg="Sale Dispatch created successfully";
          	
			System.out.println("actuSuccMsg: "+actuSuccMsg+","+"expectedErrMsg: "+expectedMsg+","+"dispatchNo: "+dispatchNo);
			softAssert.assertTrue(actuSuccMsg.startsWith(expectedMsg),"dispalyed Success message mismatch!");
			softAssert.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//Test 2:
	@Test(enabled=false,description="Test to validate the correct sale order data is getting fetched in sale dispatch or not,here validating in case of item name and its respective order quantity.")
	public void ValidateSaleOrderFetchedData() {
		try {
			
			logger.info("fetching sale order record in sale dispatch to validate UI sale order data and DB sale order are matching or not.");
			saleDisFlow.disFlowToCheckSaleOrderData();
			
			logger.info("Fetching data from UI.");
			LinkedHashMap<String,String> uiItemOrderQtyMap = saledisPage.returnUiItemOrderQtyMap();
            			
            //logger.info("Fetching latest sale order from database,Calling getLatestSalesOrderForParty method in Test class.");
         	//logger.info("Executing query and extracting sale order no.");
            //SaleOrderDBModel dbOrder = saleDisFlow.getLatestSalesOrderForParty(propReader.getProperty("partyId"));       
         	//String saleOrderNo= dbOrder.getSaleOrderNo();
            
         	logger.info("fetch data from Database.");
            Map<String,String> dbItemOrderQtyMap = SaleOrderDBHelper.getItemOrderQtyBySaleOrderNo();
            
            for(String item: dbItemOrderQtyMap.keySet()){
            	softAssert.assertTrue(uiItemOrderQtyMap.containsKey(item),"Item missing on UI: " + item);
            	softAssert.assertEquals(uiItemOrderQtyMap.get(item), dbItemOrderQtyMap.get(item),"Order Qty mismatch for Item: " + item);
            }
            softAssert.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	//Test 3:
	@Test(enabled=false,description = "Verify Create Sale Dispatch button visibility before and after selecting grid record")
	public void validateDisplayOfCreateSaleDispatchButton() {

	    // Step 1: Ensure location is selected
	    String locationSelected = saledisPage.getLocationSelected();
	    Assert.assertNotNull(locationSelected, "Location is not selected");

	    // Step 2:  for checkbox visibility
	    WebElement recordCheckbox = saledisPage.getCheckRecd();
	    WaitHelper.waitForVisible(driver, recordCheckbox, 10);

	    // Step 3: Validate button is disabled initially
	    WebElement createSaleDispatchBtn = saledisPage.getCreateSaleDispatchBtn();
	    Assert.assertFalse(
	            createSaleDispatchBtn.isEnabled(),
	            "Create Sale Dispatch button should be disabled before selecting record"
	    );

	    // Step 4: Select checkbox
	    recordCheckbox.click();
	    
	    // Step 5:  until button is enabled
	    WaitHelper.waitForClickable(driver, createSaleDispatchBtn, 10);

	    // Step 6: Validate button is enabled after selection
	    Assert.assertTrue(
	             createSaleDispatchBtn.isEnabled(),
	            "Create Sale Dispatch button is not enabled after selecting record"
	    );
	}

	
	
	//need to verify that if the order qty is less than stock available and
	//dispatch/crate qty is zero then the record should be shown in red color
	//Test to validate the red color and delete button display for grid rows having stock zero. 
	
	    //Test4:
		@Test
		public void validateRedColorWhenStockIsZero() {
			logger.info("Selecting checkbox and clicking create sale dis btn.");
			saleDisFlow.flowToViewDispatchRecords();
			
			logger.info("calling getDispatchGridData(),fetching model class and dispatch grid data.");
			List<DispatchStockColorRow> rowsData=saledisPage.getDispatchGridData();
		    List<WebElement> gridRows=saledisPage.getRecordsgrid();

		    for (DispatchStockColorRow rowData : rowsData) {
		    	logger.info("checking disQty,orderQty,stockQty not Null and converting String into Integer");
		        int disQty =   rowData.getDisQty()!= null ? Integer.parseInt(rowData.getDisQty()): 0;
		        int orderQty = rowData.getOrderQty().replace(",","") !=null ? Integer.parseInt(rowData.getOrderQty().replace(",","")) :0;
		        int stockQty = rowData.getStockQty() != null ? Integer.parseInt(rowData.getStockQty()): 0;

		        	 
		        logger.info("validating orange color functionality,if stockQty< orderQty.");
		        if (stockQty< orderQty) {
		        	boolean deleteBtnDispStatus= rowData.isDeleteBtnVisible();
      	        	softAssert.assertTrue(deleteBtnDispStatus, "Delete button is NOT dispalyed when stock is zero");
		        	
		            softAssert.assertTrue(
		                rowData.getRowColor().contains("255, 153, 102, 1"),
		                "Row is NOT orange when order qty > stock"
		            );
		        }
		        
		        logger.info("validating Red back-ground clolor functionality for a row,if disQty == 0.");;
		        if (disQty == 0) {		        	
		            softAssert.assertTrue(
		                rowData.getRowColor().contains("255, 0, 0, 0.79"),
		                "Row is NOT red when stock is zero"
		            );
		        }
		     }
	         softAssert.assertAll();
		}
	
	
		@Test(description="",enabled=false)
		public void validateOrangeColorFun() {
			
		}
		
		
		
		
	
	//Test5:
	@Test(enabled=false,description="Test to validate Scheme Label Display For Free Item Scheme")
	private List<String> validateSchemeLabelDisForFreeItemScheme() {
		saleDisFlow.flowToViewDispatchRecords();
		
		//Step1: check Party is linked to free item scheme through DB		
        String query ="DB query to validate scheme pary link status "
        		+ "and to pass it to DB WaitHelper to ceate connection and to execute query.";
        String partyId="";
       // boolean dbLinkStatus =PartySchemeLinkDBHelper.getSchemePartyLinkStatus(query,partyId);
		
		
        //step 2: validate that is there the scheme label and free label is displayed on UI or not.
		WaitHelper.waitForVisibilityOfAllElements(driver, saledisPage.getRowsWithSchemeLabel(), 10);
		List<WebElement> schemeRows= saledisPage.getRowsWithSchemeLabel();	
		//List<WebElement> freeItemRows =saledisPage.getFreeItemRow();
		
		for(WebElement row :schemeRows) {
			boolean schemeLabelDisplayStstus= row.isDisplayed();
			System.out.println("schemeLabelDisplayStstus:"+ schemeLabelDisplayStstus);
			
			//softAssert.assertEquals(schemeLabelDisplayStstus, dbLinkStatus,"Party scheme link status mismatch for given party!");
			softAssert.assertAll();
		}		
		return null;
	}

	
	
	
	
	@Test(enabled = false)
	private void validateStockAvailableForItem(String itemName, String expectedStock) {
		//UtilityToValiStockAvailableForItem.validateStockAvailableForItem(itemName, expectedStock);
	}

	
	
	@Test(enabled = false)
	private void validateDeleteBtnFun() {

	}

	@Test(enabled = false)
	private void validateResetBtnFun() {

	}

	@Test(enabled = false)
	public void validateAsterickMarkDispaly() {
		String requiredStatus = saleDisFlow.checkAsterickMarkDisplay();
		Assert.assertNotNull(requiredStatus);
	}
	
	
	

}
