package Test.inventoryModuleTests.masterTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Test.setUpTests.SetUp;
import flowPack.inventoryModuleFlow.masterFlow.PurchaseOrderFlow;
import models.PartyMasterDTO;
import pageObjects.inventory.master.PurchaseOrderPages;
import utils.ExcelDataMappingUtil;
import utils.PropertyReader;
import utils.TestListener;
import utils.WaitHelper;

@Listeners(TestListener.class)
public class PurchaseOrderTest extends SetUp {
	PurchaseOrderPages poPages;
	PurchaseOrderFlow poFlow;
	public static final Logger logger = LogManager.getLogger(PurchaseOrderFlow.class);
	PropertyReader propReader;
	SoftAssert softAssert;

	@BeforeClass
	public void objInitilization() {
		logger.info("called objInitilization method in PurchaseOrderTest");
		poPages = new PurchaseOrderPages(driver);
		poFlow = new PurchaseOrderFlow(driver);
		propReader = new PropertyReader("InventoryModule/PurchaseOrderTestData.properties");
		poFlow.prapareEnv();
		softAssert = new SoftAssert();
	}

	// Test to validate purchase order creation.
	@Test(enabled = true, priority = 0)
	public void validatePurchaseOrderCreation() {
		logger.info("called validatePurchaseOrderCreation method in PurchaseOrderTest");
		String poNo = poFlow.creatingPurchaseOrder();

		String expeSuccMsg = "Purchase Order Save successfully";
		String actSuccMsg = poPages.getSaveSucMsgText();

		System.out.println("expeUpSuccMsg: " + expeSuccMsg + ",actSuccMsg: " + actSuccMsg);
		softAssert.assertTrue(actSuccMsg.startsWith(expeSuccMsg), "Record is not created successfully.");
		softAssert.assertAll();
	}

	// Test to validate the update functionality of PO.
	@Test(enabled = false, priority = 1)
	public void validatePoUpdateFun() {
		// poFlow.prapareEnv();
		poPages.searchPoNumber(propReader.getProperty("PoNumberForUpdation"));

		WaitHelper.waitForClickable(driver, poPages.getEditIcon(), 10);
		poPages.clickEditIcon();

		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 10);
		WaitHelper.waitForClickable(driver, poPages.getDeliveryAt(), 10);
		poPages.getDeliveryAt().click();

		poPages.getDeliveryAt().sendKeys(propReader.getProperty("DeliveryAtUpdatedValue"));
		WaitHelper.waitForClickable(driver, poPages.getUpdateBtn(), 10);
		poPages.clickUpdateBtn();

		String expeUpSuccMsg = "Purchase Order Update successfully";
		String actUpSuccMsg = poPages.getUpdateSucMsgText().trim().split(",ID:")[0].trim();
		softAssert.assertEquals(expeUpSuccMsg, actUpSuccMsg, "Record is not updated successfully.");
		softAssert.assertAll();
	}

	// Test to validate purchase order flow.
	@Test(enabled = false)
	public void validatePoGrossAmount() {
		poFlow.flowUptoRateEntering();

		WaitHelper.waitForClickable(driver, poPages.getPoQtyEntered(), 10);
		String poQtyEnteredFieldValue = poPages.getPoQtyEntered().getAttribute("value");

		WaitHelper.waitForClickable(driver, poPages.getPoRateEntered(), 10);
		String poRateEnteredFieldValue = poPages.getPoRateEntered().getAttribute("value");

		WaitHelper.waitForClickable(driver, poPages.getPoGrossAmt(), 10);
		String poGrossAmtFieldValue = poPages.getPoGrossAmt().getAttribute("value");

		WaitHelper.waitForClickable(driver, poPages.getPoCgst(), 10);
		String poCgstFieldValue = poPages.getPoCgst().getAttribute("value");

		WaitHelper.waitForClickable(driver, poPages.getPoSgst(), 10);
		String poSgstFieldValue = poPages.getPoSgst().getAttribute("value");

		WaitHelper.waitForClickable(driver, poPages.getPoNetAmount(), 10);
		String poNetAmtFieldValue = poPages.getPoNetAmount().getAttribute("value");

		System.out.println("poQtyEnteredFieldValue:" + poQtyEnteredFieldValue);
		System.out.println("poRateEnteredFieldValue:" + poRateEnteredFieldValue);
		System.out.println("poGrossAmtFieldValue:" + poGrossAmtFieldValue);
		System.out.println("poCgstFieldValue:" + poCgstFieldValue);
		System.out.println("poSgstFieldValue:" + poSgstFieldValue);
		System.out.println("poNetAmtFieldValue:" + poNetAmtFieldValue);

		// Convert to double (safer for decimal values)
		double poQty = Double.parseDouble(poQtyEnteredFieldValue);
		double rate = Double.parseDouble(poRateEnteredFieldValue);
		double grossAmt = Double.parseDouble(poGrossAmtFieldValue);
		double poCgstValue = Double.parseDouble(poCgstFieldValue);
		double poSgstValue = Double.parseDouble(poSgstFieldValue);
		double poNetAmtValue = Double.parseDouble(poNetAmtFieldValue);

		double expGrossAmt = poQty * rate;
		double actGrossAmt = Double.parseDouble(poSgstFieldValue);

		softAssert.assertEquals(expGrossAmt, actGrossAmt,
				"Actual gross amount and expected gross amount are not matching,improper calculations!");
		softAssert.assertAll();
	}

	@Test(enabled = false)
	public void validatePoNetAmount() {
		poFlow.flowUptoRateEntering();

		WaitHelper.waitForVisible(driver, poPages.getPoQtyEntered(), 10);
		double actPoQty = Double.parseDouble(poPages.getPoQtyEntered().getAttribute("value"));

		WaitHelper.waitForVisible(driver, poPages.getPoRateEntered(), 10);
		double actRate = Double.parseDouble(poPages.getPoRateEntered().getAttribute("value"));

		WaitHelper.waitForVisible(driver, poPages.getDiscount(), 10);
		double actDiscount = Double.parseDouble(poPages.getDiscount().getAttribute("value"));

		WaitHelper.waitForVisible(driver, poPages.getPkgForward(), 10);
		double actPkgForward = Double.parseDouble(poPages.getPkgForward().getAttribute("value"));

		WaitHelper.waitForVisible(driver, poPages.getPoGrossAmt(), 20);
		double actGrossAmt = Double.parseDouble(poPages.getPoGrossAmt().getAttribute("value"));

		WaitHelper.waitForVisible(driver, poPages.getPoNetAmount(), 10);
		double actPoNetAmtValue = Double.parseDouble(poPages.getPoNetAmount().getAttribute("value"));

		logger.info("capturing the expected po qty,rate ,discount and PackForward,which was entered.");
		double expPoQty = Double.parseDouble(propReader.getProperty("QuantityValue"));
		double expRate = Double.parseDouble(propReader.getProperty("rateValue"));
		double expDiscount = Double.parseDouble(propReader.getProperty("discountValue"));
		double expPackForward = Double.parseDouble(propReader.getProperty("pkgForwordValue"));

		double expGrossAmount = expPoQty * expRate;
		double discountAmount = (expGrossAmount * expDiscount) / 100;
		double amountAfterDiscount = (expGrossAmount - discountAmount);
		double expNetAmount = (amountAfterDiscount + expPackForward);

		softAssert.assertEquals(actPoQty, expPoQty, "Actual and expected PO quantities are not matching !");
		softAssert.assertEquals(actRate, expRate, "Actual and expected PO rate are not matching !");
		softAssert.assertEquals(actDiscount, expDiscount, "Actual and expected PO discount are not matching !");
		softAssert.assertEquals(actPkgForward, expPackForward,
				"Actual and expected PO package forword are not matching !");
		softAssert.assertEquals(actGrossAmt, expGrossAmount, "Actual and expected PO gross amount are not matching !");
		softAssert.assertEquals(actPoNetAmtValue, expNetAmount, "Actual and expected PO net amount are not matching !");

		softAssert.assertAll();

		System.out.println("actPoQty:" + actPoQty + ", expPoQty: " + expPoQty);
		System.out.println("actRate:" + actRate + ", expRate: " + expRate);
		System.out.println("actDiscount:" + actDiscount + ", expDiscount: " + expDiscount);
		System.out.println("actPkgForward:" + actPkgForward + ", expPackForward: " + expPackForward);
		System.out.println("actGrossAmt:" + actGrossAmt + ", expGrossAmount: " + expGrossAmount);
		System.out.println("actPoNetAmtValue:" + actPoNetAmtValue + ", expNetAmount: " + expNetAmount);

	}

	@Test(enabled = false, description = "Test case to validate the Unique PO Numbers generation.")
	public void validateUniquePONoGeneration() {
		logger.info("called validateUniquePONoGeneration method in PurchaseOrderTest");
		String poNo1 = poFlow.creatingPurchaseOrder();
		System.out.println("poNo1: " + poNo1);

		String poNo2 = poFlow.creatingPurchaseOrder();
		System.out.println("poNo2: " + poNo2);

		logger.info("Validating the presence of generated PO numbers for two purchase orders created.");
		softAssert.assertFalse(poNo1.trim().isEmpty(),
				"PO number is not generated for first purchase order, PO number field is empty.");
		softAssert.assertFalse(poNo2.trim().isEmpty(),
				"PO number is not generated for second purchase order, PO number field is empty.");

		logger.info("Validating the non-nullity of generated PO numbers for two purchase orders created.");
		softAssert.assertNotNull(poNo1, "PO number is not generated for first purchase order.");
		softAssert.assertNotNull(poNo2, "PO number is not generated for second purchase order.");

		logger.info("Validating the uniqueness of generated PO numbers for two purchase orders created.");
		softAssert.assertNotEquals(poNo1, poNo2,
				"Purchase order numbers getting generated are not unique,duplicate PO numbers are generated.");
		softAssert.assertAll();
	}

	@Test(enabled = false, description = "Verify supplier details are fetched correctly from Party Master into Purchase Order form")
	public void verifyPartyDetailsFetchedCorrectlyInPO() {
		logger.info("Starting supplier mapping validation test");
		ExcelDataMappingUtil excelUtility = new ExcelDataMappingUtil();
		Map<String, String> testData = excelUtility.getTestData("Sheet1", "TC_PO_01");
		PartyMasterDTO expectedParty = new PartyMasterDTO();

		expectedParty.setPartyName(testData.get("PartyName"));
		expectedParty.setAddress(testData.get("Address"));
		expectedParty.setCity(testData.get("City"));
		expectedParty.setCountry(testData.get("Country"));
		expectedParty.setState(testData.get("State"));
		expectedParty.setPin(testData.get("Pin"));
		expectedParty.setPhone(testData.get("Phone"));

		logger.info("executing flow before it to select party and fetch the details in PO form.");
		// poFlow.prapareEnv();

		logger.info("Navigating to Purchase Order creation form clicking on create new button.");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 5);
		poPages.clickCreateNewBtn();

		logger.info("Selecting series from PO form");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 20);
		poPages.selectSeries(propReader.getProperty("SeriesDropOption"));

		logger.info("Selecting party from PO form");
		poPages.selectParty(expectedParty.getPartyName());

		logger.info("Validating auto-populated fields");

		softAssert.assertEquals(poPages.getAddress().trim(), expectedParty.getAddress().trim(),
				"Incorrect address populated.");
		softAssert.assertEquals(poPages.getCity().trim(), expectedParty.getCity().trim(), "Incorrect city populated.");
		softAssert.assertEquals(poPages.getCountry().trim(), expectedParty.getCountry().trim(),
				"Incorrect country populated.");
		softAssert.assertEquals(poPages.getState().trim(), expectedParty.getState().trim(),
				"Incorrect state populated.");
		softAssert.assertEquals(poPages.getPin().trim(), expectedParty.getPin().trim(), "Incorrect pin populated.");
		softAssert.assertEquals(poPages.getPhone().trim(), expectedParty.getPhone().trim(),
				"Incorrect phone populated.");

		softAssert.assertAll();
		logger.info("Supplier mapping validation completed successfully");
	}

	@Test(enabled = false, description = "Test case to validate the addition of multiple items in a single purchase order.")
	public void validateMultiItemsAdditionInSinglePO() {
		logger.info("called validateMultiItemsAdditionInSinglePO method in PurchaseOrderTest");
		poFlow.poflowUptoRateForMultiItems();

		WaitHelper.waitForVisibilityOfAllElements(driver, poPages.getAllItemsAdded(), 10);
		List<WebElement> allItems = poPages.getAllItemsAdded();

		String size = propReader.getProperty("expectedSize");
		int expectedSize = Integer.parseInt(size);
		int actualSize = allItems.size();

		logger.info("1. Validate Rows Count");
		softAssert.assertEquals(actualSize, expectedSize, "Actual and expected added item rows count is not matching.");
		System.out.println("Expected size: " + expectedSize + ", Actual size: " + actualSize);

		logger.info("2. Validate each item Row Is Displayed");
		int rowNum = 0;
		for (WebElement row : allItems) {
			boolean status = row.isDisplayed();

			softAssert.assertTrue(status, "Row number " + rowNum + " is not displayed in the grid.");
			System.out.println("Row " + rowNum + "Dispaly Status: " + status);
			rowNum++;
		}

		logger.info("3. Vallidating Item Names");
		// Expected item names from properties file
		List<String> expectedItemCodes = List.of(propReader.getProperty("expectedItemCodes").split(","));

		// Actual item names from grid
		List<String> actualItemCodes = new ArrayList<>();

		for (int i = 0; i < actualSize; i++) {
			WebElement itemCodeCell = driver.findElement(By.xpath(
					"//igx-display-container//igx-grid-cell[@data-rowindex='" + i + "' and @data-visibleindex='0']"));
			actualItemCodes.add(itemCodeCell.getText().trim());
		}

		logger.info("Expected Item Codes : " + expectedItemCodes);
		logger.info("Actual Item Codes : " + actualItemCodes);

		System.out.println("Expected Item Codes : " + expectedItemCodes);
		System.out.println("Actual Item Codes : " + actualItemCodes);

		// Validate all item names
		softAssert.assertEquals(actualItemCodes, expectedItemCodes, "Actual and expected item codes are not matching.");

		String poNo = poFlow.flowAfterAddingRateForMultiItems();

		String expeSuccMsg = "Purchase Order Save successfully";
		String actSuccMsg = poPages.getSaveSucMsgText();

		System.out.println("expeSuccMsg: " + expeSuccMsg + ",actSuccMsg: " + actSuccMsg);
		softAssert.assertTrue(actSuccMsg.startsWith(expeSuccMsg), "Record is not created successfully.");
		softAssert.assertNotNull(poNo, "PO number is not generated for the purchase order with multiple items.");
		softAssert.assertAll();
	}

	// Test to validate that other fields are deactive until series field is
	// selected.
	@Test(enabled = false)
	public void validateDeactivityOfOtherFields() {
		WaitHelper.waitForVisible(driver, poPages.getSeriesDropdownField(), 10);
		boolean selectStatus = driver.findElement(poPages.getSeriesDropdownField()).isSelected();

		if (!selectStatus) {
			WaitHelper.waitForClickable(driver, poPages.getPartyDropdown(), 10);
			// WebElement partyDrop = poPages.getPartyDropdown();
			// boolean enabilityStatus =partyDrop.isEnabled();
//	   if(!enabilityStatus) {
//		Assert.assertFalse(enabilityStatus,"Other fields are active,when series field is not selected.");
//	   }else {
//		Assert.assertFalse(enabilityStatus,"Other fields are deactive,when series field is not selected.");
//	   }
		}
	}

	// Test to validate the party code is provided in dropdown list or not.
	@Test(enabled = false)
	public void validatePartyCodeForPartyDrop() {
		WaitHelper.waitForClickable(driver, poPages.getSeriesDropdownField(), 10);
		poPages.selectSeries(propReader.getProperty("SeriesDropOption"));

		WaitHelper.waitForClickable(driver, poPages.getPartyDropdown(), 10);
		poPages.selectParty(propReader.getProperty("PartyDropOption"));
		// String partyValue= poPages.getPartyDropdown().getText();
		// partyValue.trim();
	}

}
