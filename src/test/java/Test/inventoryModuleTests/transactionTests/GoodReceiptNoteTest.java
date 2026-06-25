package Test.inventoryModuleTests.transactionTests;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import dataProviders.GrnDataProvider;
import flowPack.inventoryModuleFlow.transactionFlow.GoodReceiptNoteFlow;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import models.GrnItemData;
import models.GrnTestData;
import pageObjects.inventory.transaction.GoodReceiptNotePage;
import utils.CommonDatabaseUtility;
import utils.PropertyReader;
import utils.ScreenshotUtility;
import utils.WaitHelper;

@Listeners(utils.TestListener.class)
public class GoodReceiptNoteTest extends SetUp {
	GoodReceiptNoteFlow grnFlow;
	GoodReceiptNotePage grnPage;
	SoftAssert softAssert;
	PropertyReader propReader;
	String poNo;
	LoginFlow loginFlow;
	HomeFlow homeFlow;
	 private static Set<String> generatedGrnNumbers = new HashSet<>();
	private static final Logger logger = LogManager.getLogger(GoodReceiptNoteTest.class);

	@BeforeClass
	public void objIni() {
		logger.info("Initializing page objects and flow classes for Good Receipt Note tests.");
		grnFlow = new GoodReceiptNoteFlow(driver);
		grnPage = new GoodReceiptNotePage(driver);
		propReader = new PropertyReader("InventoryModule/grnTestData.properties");
		poNo = propReader.getProperty("poNo");
		grnFlow.prepareEnvToStartingFormLogin();
	}

	@BeforeMethod
	public void initSoftAssert() {
		softAssert = new SoftAssert();
	}

	
	@DataProvider(name = "grnTestData")
	public Object[][] getGrnData() {

		List<GrnTestData> dataList = GrnDataProvider.getGrnTestData();
		Object[][] data = new Object[dataList.size()][1];
		for (int i = 0; i < dataList.size(); i++) {
			data[i][0] = dataList.get(i);
		}
		return data;
	}
	
	
	@Test(description = "Test to validate GRN Flow along with success msg validation.", enabled = false)
	public void validateGrnFlow() {
		try {
			String grnNo = grnFlow.executeGrnFlow(poNo);
			String ActSuccMsg = grnPage.extractSubmitSuccMessage();
			String expectedSuccMsg = "GRN Created successfully";
			Assert.assertTrue(ActSuccMsg.contains(expectedSuccMsg), "GRN creation Unsuccessful!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
			
	@Test(dataProvider = "grnTestData", description = "Validate GRN Flow,through data provider and excel with multiple test data sets.", enabled = false)
	public void validateGrnFlow1(GrnTestData data) {
				
		String grnNo = grnFlow.executeGrnFlow1(data);
		String actualMsg = grnPage.extractSubmitSuccMessage();
			
		generatedGrnNumbers.add(grnNo);
		logger.info("GRN Created: " + grnNo);
		Assert.assertTrue(actualMsg.contains("GRN Created successfully"));
		softAssert.assertNotNull(grnNo, "The GRN No is not generated, it is null!");
		softAssert.assertFalse(grnNo.isEmpty(), "The GRN No is not generated, it is empty!");
		   
		// UNIQUE VALIDATION
		Assert.assertFalse(generatedGrnNumbers.contains(grnNo), "Duplicate GRN Number generated: " + grnNo);
		softAssert.assertAll();		
	}
	

	@Test(description = "Test to validate navigation upto listing page.", enabled = false)
	public void validateListingPageHeading() {
		try {
			//WaitHelper.waitForVisible(driver, grnPage.getListingPageHeader(), 10);
			//String actualHeading = grnPage.extractListingPageHeading();
			String expectedHeading = "Good Receipt Note";

//			System.out.println("actualHeading:" + actualHeading);
//			System.out.println("expectedHeading:" + expectedHeading);
//
//			Assert.assertEquals(actualHeading, expectedHeading, "Heading mismatch!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test(description="Test to validate GRN form loading",enabled = false)
	public void validateFormLoading() {	
		grnPage.clickCreateNewButton();
		logger.info("Clicked Create New button");
		
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);		
		String actTitle = grnPage.getGRNFormTitle().trim();
		String expTitle = "Good Receipt Note / Create";
		
		System.out.println("actTitle:" + actTitle);
		System.out.println("expTitle:" + expTitle);
		
		Assert.assertEquals(actTitle, expTitle, "GRN form title mismatch! Form may not have loaded properly.");
	}
	

		
	@Test(description = "Validate PO data integrity in GRN")
	public void validatePoDataInGRNInfoTab() {
		try {
			// Fetch PO Data
			String poNo = grnFlow.fetchPoData();
			WaitHelper.waitForVisible(driver, grnPage.getGrid(), 10);
			// UI Data
			List<GrnItemData> uiItems = grnPage.getGrnItemDetails();

	        // DB Query
	        String query =
	                "SELECT " +
	                "t.pod_item_name, " +
	                "t.pod_qty, " +
	                "t.pod_rate " +
	                "FROM inv_po_dtl t " +
	                "JOIN inv_po_hdr k " +
	                "ON t.pod_po_id_poh = k.poh_po_id " +
	                "WHERE k.poh_po_no = ?";

			List<Map<String, String>> dbRows = CommonDatabaseUtility.getMultipleRows(query, poNo);
			// Row Count Validation
			Assert.assertEquals(uiItems.size(), dbRows.size(), "UI and DB row count mismatch");

			// Data Validation
			for (int i = 0; i < uiItems.size(); i++) {
				GrnItemData ui = uiItems.get(i);
				Map<String, String> db = dbRows.get(i);

				Assert.assertEquals(ui.getItemName().trim(), db.get("pod_item_name").trim(), "Item Name mismatch");
				Assert.assertEquals(ui.getQuantity().trim(), db.get("pod_qty").trim(), "Quantity mismatch");
				Assert.assertEquals(ui.getRate().trim(), db.get("pod_rate").trim(), "Rate mismatch");
			}
			logger.info("PO Data validation successful");
		} catch (Exception e) {
			logger.error("Validation failed", e);
			Assert.fail(e.getMessage());
		}
	}

	
	@Test(description="Test to validate the PO No belong to the selected Vendor or not." ,enabled = false)
	public void validatePoNoAgainstVendor() {
		grnFlow.flowUptoPoNoSel();
		String selPoNo= grnPage.extractSelPoNo();
		String selVendor= grnPage.extractSelVendor();

		//database query to validate po No and vendor mapping are correct or not.
        //select t_po_no from inv_po_hdr t where t.vendor="vendor_code";
	}

	
	@Test(description="Test to validate the max limit of the input field.",enabled = false)
	public void maxLimitForInputFields() {
		grnFlow.checkMaxLimitOfInputField();

		grnPage.enterLrNo("123456789123456781234");
		logger.info("LR Number entered");

		grnPage.enterInvoiceNo("123456789123456781234");
		logger.info("Invoice Number entered");

		String actualLrNo = grnPage.getLrNoField().getAttribute("value");
		String actualInvoiceNo = grnPage.getInvoiceNoField().getAttribute("value");

		int maxAllowedLength = 20;
		int actLrNoLength = actualLrNo.length();
		int actInvNoLength = actualInvoiceNo.length();

		softAssert.assertEquals(actLrNoLength, maxAllowedLength, "LR No max length validation failed");
		softAssert.assertEquals(actInvNoLength, maxAllowedLength, "Invoice No max length validation failed");

		softAssert.assertAll();
	}

	
	
	@Test(description="Test to validate Landed rate calculations for ITC=\"YES\" and ITC=\"NO\"",enabled = false)
	public void validateLandedRateCaculations(String poNo) {
		grnFlow.flowUptoRemark(poNo);
		
		//if(grnPage.getItcEligibility().equalsIgnoreCase("YES")) {
			//calculation for ITC="YES"
			//landedRate=Gross Amount/Quantity
		//}
		//else {
			//calculation for ITC="NO"
			//landedRate=Net Amount/Quantity
		//}
				
		String query=
		  "select t_item_name,"+
		  "t_pod_qty,"+
		  "t_pod_gross_amt,"+
		  "t_pod_net_amt,"+
		  "t_itc_eligibility"+ 
		  "from inv_po_dtl t"+
		  "join inv_po_hdr k" +
		  "on t.pod_po_id_poh=k.poh_po_id"+ 
		  "where k.poh_po_no=+ poNo +";
		
		 //Database query to validate the landed rate calculations are correct or not.
		//select t.landed_rate from inv_grn_dtl t where t.grn_no="grn_no";
	}
	
	
	@Test(description="Test to validate the consistency of landed rate between UI and Database.",enabled = false)
	public void validateLandedRateConsistencyBetUIAndDatabase(String grnNo) {
		//fetch landed rate from UI
		//String uiLandedRate=grnPage.extractLandedRateFromUI();
		
		//fetch landed rate from database
		String query="select t.landed_rate from inv_grn_dtl t where t.grn_no=" + grnNo;
		//String dbLandedRate=CommonDatabaseUtility.getSingleData(query);
		
		//Assert.assertEquals(uiLandedRate, dbLandedRate, "Landed Rate mismatch between UI and Database");
	}
	
		
	@Test(description="Test to validate back button functionality",enabled = false)
	public void validateBackButtonFuct() {	
		grnPage.clickCreateNewButton();
		logger.info("Clicked Create New button,Waiting for GRN form to load...");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
        
        logger.info("Validating GRN form opened successfully");
        softAssert.assertTrue(grnPage.isGrnFormDisplayed(),"GRN form is not displayed");

        String formUrl = driver.getCurrentUrl();
        logger.info("Current Form URL : " + formUrl);      
        
		grnPage.clickBackButton();
		logger.info("Clicked Back button Waiting for listing page to load after clicking back button...");	
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
				
		String actualUrl = driver.getCurrentUrl();
	    logger.info("Current Listing URL : " + actualUrl);
		
	    softAssert.assertFalse(actualUrl.contains("create"),"Failed to navigate back to GRN listing page");
	    softAssert.assertTrue(grnPage.isGRNListingGridDisplayed(),"GRN listing grid is not displayed after clicking Back button");

	    softAssert.assertAll();
	    logger.info("Back button navigation validated successfully");
	
	}

	
	@Test(description= "Test to validate Reset button Functionality.",enabled = false)
	public void validateResetButtonFunct() {
		grnFlow.flowToCheckResetFun();
		softAssert.assertEquals(grnPage.getSelPoNoField().getAttribute("value"), " A02-CP-25-000026");
		softAssert.assertEquals(grnPage.getSelVendorField().getAttribute("value"),"SUP0000199 / MACK STEEL CO / 27AEKPB7252F1ZM");

		logger.info("locating & clicking Reset Button");
		grnPage.clickResetButton();

		// check after reset
		softAssert.assertEquals(grnPage.getSelPoNoField().getAttribute("value"), "", "Po no not reset.");
		softAssert.assertEquals(grnPage.getSelVendorField().getAttribute("value"), "", "vendor not reset.");
		// softAssert.assertEquals(grnPage.getLrNoField().getAttribute("value"), "","lR
		// no not reset.");
		// softAssert.assertEquals(grnPage.getInvoiceNoField().getAttribute("value"),
		// "","InvoiceNo not reset.");

		System.out.println("Value of po field after reset:" + grnPage.getSelPoNoField().getAttribute("value"));
		System.out.println("Value of vendor field after reset:" + grnPage.getSelVendorField().getAttribute("value"));

		softAssert.assertAll();
	}

	
	@Test(description = "Validate mandatory fields in GRN form")
	public void validateMandatoryFields() {
		//Here at lest one field is empty to check the mandatory validation.
	    logger.info("Starting mandatory field validation");
	    grnFlow.executeGrnFlowToValidateMandField(poNo);

	    logger.info("Validating highlighting of the field to validate the mandatory field validation.");
	    
	    validateMandatoryField("PO No", grnPage.isFieldHighlighted(grnPage.getPoDropdownField()));
	    validateMandatoryField("Vendor", grnPage.isFieldHighlighted(grnPage.getSelVendorField()));
	    validateMandatoryField("LR No", grnPage.isFieldHighlighted(grnPage.getLrNoField()));
	    validateMandatoryField("Invoice No", grnPage.isFieldHighlighted(grnPage.getInvoiceNoField()));

		softAssert.assertFalse(grnPage.isSubmitButtonEnabled(), "Submit button should be disabled");
	    logger.info("Mandatory validation completed");
	    softAssert.assertAll();
	}
	
	
	// Reusable assertion helper method
    private void validateMandatoryField(String fieldName, boolean isHighlighted) {
        logger.info("Validating mandatory field : " + fieldName);
        softAssert.assertTrue(isHighlighted,fieldName + " field is not highlighted as mandatory");
    }
	
    
    @Test(description = "Test to extract PO quantity from listing page grid", enabled =true)
    public void extractGRNNoFromListingPage() throws InterruptedException {
    WaitHelper.waitForInvisibilityOfElementLocated(driver,grnPage.getDotSpinner(), 10);
	WaitHelper.waitForVisible(driver, grnPage.getGrid(), 10);
	//grnPage.extractGRNNoFromGrid();
	List<String> grnNos= grnPage.extractAllGRNNumbers();
	System.out.println("Extracted GRN Numbers from listing page:"+ grnNos);
     }
    
    
    @Test(description = "Test to search GRN No on listing page", enabled = true)
    public void searchGRNNoOnListingPage() throws InterruptedException {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
		WaitHelper.waitForVisible(driver, grnPage.getGrid(), 10);
		boolean foundStatus= grnPage.findGRNNofromListingPage();
		Assert.assertTrue(foundStatus, "Searched GRN No not found in the listing page");
	}
}


