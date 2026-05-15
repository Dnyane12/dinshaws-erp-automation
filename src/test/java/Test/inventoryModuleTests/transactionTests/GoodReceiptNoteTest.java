package Test.inventoryModuleTests.transactionTests;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import dataProviders.GrnDataProvider;
import flowPack.inventoryModuleFlow.transactionFlow.GoodReceiptNoteFlow;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import models.GrnTestData;
import pageObjects.inventory.transaction.GoodReceiptNotePage;
import utils.PropertyReader;
import utils.ScreenshotUtility;
import utils.WaitHelper;

public class GoodReceiptNoteTest extends SetUp {
	GoodReceiptNoteFlow grnFlow;
	GoodReceiptNotePage grnPage;
	SoftAssert softAssert;
	PropertyReader propReader;
	String poNo;
	LoginFlow loginFlow;
	HomeFlow homeFlow;
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
	
	
	@Test(description = "Test to validate GRN Flow along with success msg validation.", enabled = true)
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
		
		Assert.assertNotNull(grnNo);
		Assert.assertFalse(grnNo.isEmpty());
		Assert.assertTrue(actualMsg.contains("GRN Created successfully"));
		System.out.println("GRN Created: " + grnNo);
	}
	

	@Test(description = "Test to validate navigation upto listing page.", enabled = false)
	public void validateListingPageHeading() {
		try {
			WaitHelper.waitForVisible(driver, grnPage.getListingPageHeader(), 10);
			String actualHeading = grnPage.extractListingPageHeading();
			String expectedHeading = "Good Receipt Note";

			System.out.println("actualHeading:" + actualHeading);
			System.out.println("expectedHeading:" + expectedHeading);

			Assert.assertEquals(actualHeading, expectedHeading, "Heading mismatch!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test(description="Test to validate GRN No creation on listing page.",enabled = true)
	public void validateGrnNoGenAndUniqueGrnNoCreation() {
		String actGrnNo = grnFlow.checkGrnNoCreation();
		System.out.println("actGrnNo:" + actGrnNo);

		softAssert.assertNotNull(actGrnNo, "The GRN No is not generated, it is null!");
		softAssert.assertAll();
	}

	
	@Test(description="Test to validate GRN form loading",enabled = false)
	public void validateFormLoading() {
		WaitHelper.waitForVisible(driver, grnPage.getListingPageHeader(), 10);
		
		grnPage.clickCreateNewButton();
		logger.info("Clicked Create New button");
		System.out.println("Test1 for Jenkins auto build setup");
		
	}

		
	@Test(description="Validate PO data integrity in GRN.",enabled = false)
	public void validatePoDataInGRNInfoTab() {
		try {
			grnFlow.checkPoDataInGRNInfoTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test(description= "Test to validate the auto population of PO record in GRN details tab.",enabled = false)
	public void validatePoDataInGRNDetailsTab() {
		try {
			grnFlow.checkPoDataInGRNInfoTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test(description="Test to validate the PO No belong to the selected Vendor or not." ,enabled = false)
	public void validatePoNoAgainstVendor() {
		grnFlow.flowUptoPoNoSel();
		grnFlow.checkPoNoAgainstVendor();

		// compare the sel vendor and actual vendor ,using the DB table of po with
		// query.
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
	public void validateLandedRateCaculations() {

	}

	
	
	@Test(enabled = false, description = "Test to validate proper navigation of GRN form")
	public void validateGRNNavigation() {
		logger.info("locating list page title field and extracting its value");
		WebElement listPageTitleField = WaitHelper.waitForClickable(driver, grnPage.getListpageHeader(), 10);

		String actGrnListPageTitle = listPageTitleField.getText().trim();
		String expGrnListPageTitle = "Good Receipt Note";

		System.out.println("actGrnListPageTitle: " + actGrnListPageTitle);

		logger.info("validating proper navigation of GRN form,using listing page title comparison.");
		Assert.assertEquals(actGrnListPageTitle, expGrnListPageTitle,
				"Mismatch of GRN list page title!,GRN form navigation is not proper.");
	}

	
	
	
	@Test(description="Test to validate back button functionality",enabled = false)
	public void validateBackButtonFuct() {

		WaitHelper.waitForClickable(driver, grnPage.getBackButton(), 10);

	}

	
	@Test(description= "Test to validate Reset button Functionality.",enabled = false)
	public void validateResetButtonFunct() {
		grnFlow.flowToCheckResetFun();
		softAssert.assertEquals(grnPage.getSelPoNoField().getAttribute("value"), " A02-CP-25-000026");
		softAssert.assertEquals(grnPage.getSelVendorField().getAttribute("value"),
				"SUP0000199 / MACK STEEL CO / 27AEKPB7252F1ZM");

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

	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				// softAssert.assertAll();
				System.out.println("Test Failed! Capturing Screenshot...");
				ScreenshotUtility.takeScreenshot(driver, result.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// driver.quit();
	}
}
