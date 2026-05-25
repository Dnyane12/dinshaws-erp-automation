package flowPack.inventoryModuleFlow.transactionFlow;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import models.GrnTestData;
import pageObjects.inventory.transaction.GoodReceiptNotePage;
import pageObjects.setup.HomePage;
import utils.PropertyReader;
import utils.WaitHelper;

public class GoodReceiptNoteFlow {
	WebDriver driver;
	GoodReceiptNotePage grnPage;
	LoginFlow loginFlow;
	HomeFlow homeFlow;
	PropertyReader propReader;
	private static final Logger logger = LogManager.getLogger(GoodReceiptNoteFlow.class);

	public GoodReceiptNoteFlow(WebDriver driver) {
		this.driver = driver;
		logger.info("called GoodReceiptNoteFlow constructor");
		grnPage = new GoodReceiptNotePage(driver);
		loginFlow = new LoginFlow(driver);
		homeFlow = new HomeFlow(driver);
		propReader = new PropertyReader("InventoryModule/grnTestData.properties");
	}

	public void prepareEnvToDirectlyOpenGRNForm() {
		logger.info("calling openGRNFormDirectly() method in GRN Flow class.");
		logger.info("Waiting for invisibility of dotSpinner.");

		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
		WaitHelper.waitForClickable(driver, grnPage.getStoreLink(), 10);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", grnPage.getStoreLink());
		js.executeScript("arguments[0].click()", grnPage.getStoreLink());

		WaitHelper.waitForClickable(driver, grnPage.getGoodReceiptNoteFormLink(), 10);
		// Capture current URL before clicking
		String previousUrl = driver.getCurrentUrl();
		System.out.println("Previous URL: " + previousUrl);

		grnPage.clickGrnLink();
		logger.info("Opened Good Receipt Note form");

		String currentUrl = driver.getCurrentUrl();
		System.out.println("current Url: " + currentUrl);

		WaitHelper.waitToDealWithCatche(driver, previousUrl);

		String currentUrl1 = driver.getCurrentUrl();
		System.out.println("current Url1: " + currentUrl1);

		logger.info("Waiting for invisibility of dotspinner.");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);

	}

	public void prepareEnvToStartingFormLogin() {
		try {
			logger.info("===== Starting GRN Flow Execution, Step 1: Performing Login Flow=====");
			loginFlow.loginFlowCheck();

			logger.info("clicking inventoryMod  in HomePage");
			WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 20);
			logger.info("Step 2: Navigating to Inventory → Store → GRN");
			homeFlow.clickInvModAndStoreLink();

			grnPage.clickGrnLink();
			logger.info("Opened Good Receipt Note form");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to execute GRN Flow OR create GRN
	public String executeGrnFlow(String poNo) {
		try {
			// WaitHelper.waitForInvisibilityOfElementLocated(driver,
			// grnPage.getDotSpinner(), 10);
			grnPage.clickCreateNewButton();
			logger.info("Clicked Create New button");

			logger.info("Waiting for invisibility of dotSpinner.");
			WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);

			logger.info("Step 3: Selecting Vendor and PO Number");
			WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);

			grnPage.selectVendor(propReader.getProperty("vendorDropOption"));
			logger.info("Vendor Selected: {}", propReader.getProperty("vendorDropOption"));

			WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
			System.out.println("poNo in flow class: " + poNo);
			grnPage.enterPoNoToSearch(poNo);
			// logger.info("PO Number Selected: {}",
			// propReader.getProperty("poNoDropOption"));

			WaitHelper.waitForClickable(driver, grnPage.getFetchDataButton(), 10);
			grnPage.clickFetchData();
			logger.info("Clicked Fetch Data button");

			WaitHelper.waitForVisible(driver, grnPage.getGridRow(), 10);
			logger.info("Step 4: Entering GRN Information");
			grnPage.clickGrnInfoTab();

			WaitHelper.waitForClickable(driver, grnPage.getTransporterMode(), 20);
			grnPage.selectTransporterMode(propReader.getProperty("transporterModeLabel"),
					propReader.getProperty("transporterModeOption"));
			logger.info("Transporter mode selected: {}", propReader.getProperty("transporterModeOption"));

			grnPage.enterLrNo(propReader.getProperty("lrNo"));
			logger.info("LR Number entered");

			grnPage.enterLrDate(propReader.getProperty("lrDate"));
			logger.info("LR Date entered");

			grnPage.enterInvoiceNo(propReader.getProperty("InvoiceNo"));
			logger.info("Invoice Number entered");

			grnPage.enterInvoiceDate(propReader.getProperty("invoiceDate"));
			logger.info("Invoice Date entered");

			logger.info("Step 5: Editing GRN Details and Quantities");
			grnPage.clickGrnDetailsTab();

//			int poRecordsCount = Integer.parseInt(propReader.getProperty("poRecordsCount"));						
//			System.out.println("poRecordsCount: " + poRecordsCount);

//			for (int i = 1; i <= poRecordsCount; i++) {				
//                System.out.println("Processing record number: " + i);

//                int itemRowIndex = i; // Set the row index for the current record

			WaitHelper.waitForClickable(driver, grnPage.getEditIcon(), 10);
			grnPage.clickEditIcon();
			grnPage.enterInvoiceQuantity(propReader.getProperty("invoiceQty"));
			logger.info("Invoice Qty entered: {}", propReader.getProperty("invoiceQty"));

			grnPage.enterReceivedQuantity(propReader.getProperty("receivedQty"));
			logger.info("Received Qty entered: {}", propReader.getProperty("receivedQty"));

			grnPage.enterAcceptedQuantity(propReader.getProperty("acceptedQty"));
			logger.info("Accepted Qty entered: {}", propReader.getProperty("acceptedQty"));

			grnPage.enterRemark(propReader.getProperty("remark"));
			logger.info("Remark entered");

			grnPage.clickUpdateButton();
//				logger.info("Update button clicked");
//				System.out.println("Updated record number: " + i);

			// Give backend time to trigger recalculation
			WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 20);

			logger.info("Extracting Net Amount from GRN Details");
			// WaitHelper.waitForVisible(driver, grnPage.getNetvalueWholeText(), 20);
			grnPage.extractTotalNetAmount();
			logger.info("Entered Net Amount in GRN Info tab");

			grnPage.clickSubmitButton();
			logger.info("Clicked Submit button");

			String grnNoCreated = extractGrnNo();

			logger.info("===== GRN Flow Executed Successfully =====");
			return grnNoCreated;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String executeGrnFlow1(GrnTestData data) {
		try {
			grnPage.clickCreateNewButton();
			WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
			grnPage.selectVendor(data.getVendorDropOption());
			grnPage.enterPoNoToSearch(data.getPoNoDropOption());
			grnPage.clickFetchData();
			WaitHelper.waitForVisible(driver, grnPage.getGridRow(), 10);
			grnPage.clickGrnInfoTab();
    		grnPage.selectTransporterMode(data.getTransporterModeLabel(),data.getTransporterModeOption());
			grnPage.enterLrNo(data.getLrNo());
			grnPage.enterLrDate(data.getLrDate());
			grnPage.enterInvoiceNo(data.getInvoiceNo());
			grnPage.enterInvoiceDate(data.getInvoiceDate());
			grnPage.clickGrnDetailsTab();
			grnPage.clickEditIcon();
			grnPage.enterInvoiceQuantity(data.getInvoiceQty());
			grnPage.enterReceivedQuantity(data.getReceivedQty());
			grnPage.enterAcceptedQuantity(data.getAcceptedQty());
			grnPage.enterRemark(data.getRemark());
			grnPage.clickUpdateButton();			
			WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 20);			
			grnPage.extractTotalNetAmount();			
			grnPage.clickSubmitButton();			
			return grnPage.extractGrnNo();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	// Method to execute GRN Flow OR create GRN
		public void executeGrnFlowToValidateMandField(String poNo) {
			try {
				// WaitHelper.waitForInvisibilityOfElementLocated(driver,
				// grnPage.getDotSpinner(), 10);
				grnPage.clickCreateNewButton();
				logger.info("Clicked Create New button");

				logger.info("Waiting for invisibility of dotSpinner.");
				WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);

				logger.info("Step 3: Selecting Vendor and PO Number");
				WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);

				grnPage.selectVendor(propReader.getProperty("vendorDropOption"));
				logger.info("Vendor Selected: {}", propReader.getProperty("vendorDropOption"));

				WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
				System.out.println("poNo in flow class: " + poNo);
				grnPage.enterPoNoToSearch(poNo);
			
				WaitHelper.waitForClickable(driver, grnPage.getFetchDataButton(), 10);
				grnPage.clickFetchData();
				logger.info("Clicked Fetch Data button");

				WaitHelper.waitForVisible(driver, grnPage.getGridRow(), 10);
				logger.info("Step 4: Entering GRN Information");
				grnPage.clickGrnInfoTab();

				WaitHelper.waitForClickable(driver, grnPage.getTransporterMode(), 20);
				grnPage.selectTransporterMode(propReader.getProperty("transporterModeLabel"),
						propReader.getProperty("transporterModeOption"));
				logger.info("Transporter mode selected: {}", propReader.getProperty("transporterModeOption"));

				grnPage.enterLrNo(propReader.getProperty("lrNo"));
				logger.info("LR Number entered");

				grnPage.enterLrDate(propReader.getProperty("lrDate"));
				logger.info("LR Date entered");

				//InvoiceNo is mandatory field,entering null value to, validate its mandatory nature.
				grnPage.enterInvoiceNo(propReader.getProperty(""));
				logger.info("Invoice Number entered");

				grnPage.enterInvoiceDate(propReader.getProperty("invoiceDate"));
				logger.info("Invoice Date entered");

				logger.info("Step 5: Editing GRN Details and Quantities");
				grnPage.clickGrnDetailsTab();

				WaitHelper.waitForClickable(driver, grnPage.getEditIcon(), 10);
				grnPage.clickEditIcon();
				grnPage.enterInvoiceQuantity(propReader.getProperty("invoiceQty"));
				logger.info("Invoice Qty entered: {}", propReader.getProperty("invoiceQty"));

				grnPage.enterReceivedQuantity(propReader.getProperty("receivedQty"));
				logger.info("Received Qty entered: {}", propReader.getProperty("receivedQty"));

				grnPage.enterAcceptedQuantity(propReader.getProperty("acceptedQty"));
				logger.info("Accepted Qty entered: {}", propReader.getProperty("acceptedQty"));

				grnPage.enterRemark(propReader.getProperty("remark"));
				logger.info("Remark entered");
				
				grnPage.clickUpdateButton();
				WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 20);

				logger.info("Extracting Net Amount from GRN Details");				
				grnPage.extractTotalNetAmount();
				logger.info("Entered Net Amount in GRN Info tab");

				//grnPage.clickSubmitButton();
				logger.info("Clicked Submit button");

				//String grnNoCreated = extractGrnNo();
				logger.info("===== GRN Flow Executed Successfully =====");
				//return grnNoCreated;
			} catch (Exception e) {
				e.printStackTrace();
				//return null;
			}

		}
	
	public String extractGrnNo() {
		String compSuccMsg = grnPage.extractGrnNo();
		String parts[] = compSuccMsg.trim().split("ID:");
		String grnNo = parts[1];
		System.out.println("grnNo: " + grnNo);
		return grnNo;
	}

	public void flowUptoPoNoSel() {
		WaitHelper.waitForClickable(driver, grnPage.getCreateNewButton(), 10);
		grnPage.clickCreateNewButton();
		logger.info("Clicked Create New button");

		logger.info("Step 3: Selecting Vendor and PO Number");
		WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);
		grnPage.selectVendor(propReader.getProperty("vendorDropOption"));
		logger.info("Vendor Selected: {}", propReader.getProperty("vendorDropOption"));

		WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
		grnPage.enterPoNoToSearch(propReader.getProperty("poNoDropOption"));
		logger.info("PO Number Selected: {}", propReader.getProperty("poNoDropOption"));
	}

	// Method to check proper creation of GRN no.
	public String checkGrnNoCreation() {
		WaitHelper.waitForClickable(driver, grnPage.getListingTitle(), 10);
		String extractedGrnNo = grnPage.extractGrnNoCreated();
		System.out.println("extractedGrnNo:" + extractedGrnNo);
		return extractedGrnNo;
	}

	public String fetchPoData() {

	    WaitHelper.waitForClickable(driver, grnPage.getCreateNewButton(), 10);
	    grnPage.clickCreateNewButton();
	    logger.info("Clicked Create New button");

	    WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
	    WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);
	    grnPage.selectVendor(propReader.getProperty("vendorDropOption"));

	    String poNo = propReader.getProperty("poNoDropOption");

	    WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
	    grnPage.enterPoNoToSearch(poNo);

	    logger.info("PO Number Selected: {}", poNo);

	    WaitHelper.waitForClickable(driver, grnPage.getFetchDataButton(), 10);
	    grnPage.clickFetchData();

	    logger.info("Clicked Fetch Data button");

	    return poNo;
	}

	
	

	public void checkMaxLimitOfInputField() {
		WaitHelper.waitForClickable(driver, grnPage.getCreateNewButton(), 10);
		grnPage.clickCreateNewButton();
		logger.info("Clicked Create New button");

		logger.info("Step 3: Selecting Vendor and PO Number");
		WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);
		grnPage.selectVendor(propReader.getProperty("vendorDropOption"));
		logger.info("Vendor Selected: {}", propReader.getProperty("vendorDropOption"));

		WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
		grnPage.enterPoNoToSearch(propReader.getProperty("poNoDropOption"));
		logger.info("PO Number Selected: {}", propReader.getProperty("poNoDropOption"));

		WaitHelper.waitForClickable(driver, grnPage.getFetchDataButton(), 10);
		grnPage.clickFetchData();
		logger.info("Clicked Fetch Data button");

		logger.info("Step 4: Entering GRN Information");
		WaitHelper.waitForClickable(driver, grnPage.getGoodReceiptNoteFormLink(), 10);
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
		WaitHelper.waitForClickable(driver, grnPage.getGrnInfoTab(), 10);
		grnPage.clickGrnInfoTab();

		WaitHelper.waitForClickable(driver, grnPage.getTransporterMode(), 10);
		grnPage.selectTransporterMode(propReader.getProperty("transporterModeLabel"),
				propReader.getProperty("transporterModeOption"));
		logger.info("Transporter mode selected: {}", propReader.getProperty("transporterModeOption"));

	}

	public void flowToCheckResetFun() {
		WaitHelper.waitForClickable(driver, grnPage.getCreateNewButton(), 10);
		grnPage.clickCreateNewButton();
		logger.info("Clicked Create New button");

		logger.info("Step 3: Selecting Vendor and PO Number");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
		WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);
		grnPage.selectVendor(propReader.getProperty("vendorDropOption"));
		logger.info("Vendor Selected: {}", propReader.getProperty("vendorDropOption"));

		WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
		grnPage.enterPoNoToSearch(propReader.getProperty("poNoDropOption"));
		logger.info("PO Number Selected: {}", propReader.getProperty("poNoDropOption"));

		WaitHelper.waitForClickable(driver, grnPage.getFetchDataButton(), 10);
		grnPage.clickFetchData();
		logger.info("Clicked Fetch Data button");

		logger.info("Step 4: Entering GRN Information");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);
		WaitHelper.waitForClickable(driver, grnPage.getGrnInfoTab(), 10);
		grnPage.clickGrnInfoTab();

		WaitHelper.waitForClickable(driver, grnPage.getTransporterMode(), 10);
		grnPage.selectTransporterMode(propReader.getProperty("transporterModeLabel"),
				propReader.getProperty("transporterModeOption"));
		logger.info("Transporter mode selected: {}", propReader.getProperty("transporterModeOption"));

		grnPage.enterLrNo(propReader.getProperty("lrNo"));
		logger.info("LR Number entered");

		grnPage.enterLrDate(propReader.getProperty("lrDate"));
		logger.info("LR Date entered");

		grnPage.enterInvoiceNo(propReader.getProperty("InvoiceNo"));
		logger.info("Invoice Number entered");

		grnPage.enterInvoiceDate(propReader.getProperty("invoiceDate"));
		logger.info("Invoice Date entered");

		logger.info("Step 5: Editing GRN Details and Quantities");
		grnPage.clickGrnDetailsTab();

		grnPage.clickEditIcon();

		grnPage.enterInvoiceQuantity(propReader.getProperty("invoiceQty"));
		logger.info("Invoice Qty entered: {}", propReader.getProperty("invoiceQty"));

		grnPage.enterReceivedQuantity(propReader.getProperty("receivedQty"));
		logger.info("Received Qty entered: {}", propReader.getProperty("receivedQty"));

		grnPage.enterAcceptedQuantity(propReader.getProperty("acceptedQty"));
		logger.info("Accepted Qty entered: {}", propReader.getProperty("acceptedQty"));

		grnPage.enterRemark(propReader.getProperty("remark"));
		logger.info("Remark entered");

		grnPage.clickUpdateButton();
		logger.info("Update button clicked");

		logger.info("Extracting Net Amount from GRN Details");
		WaitHelper.waitForClickable(driver, grnPage.getNetvalueWholeText(), 10);
		grnPage.extractTotalNetAmount();

		logger.info("Entered Net Amount in GRN Info tab");

	}
	
	public void flowUptoRemark(String poNo) {		
			try {
				grnPage.clickCreateNewButton();
				logger.info("Clicked Create New button");

				logger.info("Waiting for invisibility of dotSpinner.");
				WaitHelper.waitForInvisibilityOfElementLocated(driver, grnPage.getDotSpinner(), 10);

				logger.info("Step 3: Selecting Vendor and PO Number");
				WaitHelper.waitForClickable(driver, grnPage.getVendorDropField(), 10);

				grnPage.selectVendor(propReader.getProperty("vendorDropOption"));
				logger.info("Vendor Selected: {}", propReader.getProperty("vendorDropOption"));

				WaitHelper.waitForClickable(driver, grnPage.getPoNoDropList(), 10);
				System.out.println("poNo in flow class: " + poNo);
				grnPage.enterPoNoToSearch(poNo);

				WaitHelper.waitForClickable(driver, grnPage.getFetchDataButton(), 10);
				grnPage.clickFetchData();
				logger.info("Clicked Fetch Data button");

				WaitHelper.waitForVisible(driver, grnPage.getGridRow(), 10);
				logger.info("Step 4: Entering GRN Information");
				grnPage.clickGrnInfoTab();

				WaitHelper.waitForClickable(driver, grnPage.getTransporterMode(), 20);
				grnPage.selectTransporterMode(propReader.getProperty("transporterModeLabel"),
						propReader.getProperty("transporterModeOption"));
				logger.info("Transporter mode selected: {}", propReader.getProperty("transporterModeOption"));

				grnPage.enterLrNo(propReader.getProperty("lrNo"));
				logger.info("LR Number entered");

				grnPage.enterLrDate(propReader.getProperty("lrDate"));
				logger.info("LR Date entered");

				grnPage.enterInvoiceNo(propReader.getProperty("InvoiceNo"));
				logger.info("Invoice Number entered");

				grnPage.enterInvoiceDate(propReader.getProperty("invoiceDate"));
				logger.info("Invoice Date entered");

				logger.info("Step 5: Editing GRN Details and Quantities");
				grnPage.clickGrnDetailsTab();

				WaitHelper.waitForClickable(driver, grnPage.getEditIcon(), 10);
				grnPage.clickEditIcon();
				grnPage.enterInvoiceQuantity(propReader.getProperty("invoiceQty"));
				logger.info("Invoice Qty entered: {}", propReader.getProperty("invoiceQty"));

				grnPage.enterReceivedQuantity(propReader.getProperty("receivedQty"));
				logger.info("Received Qty entered: {}", propReader.getProperty("receivedQty"));

				grnPage.enterAcceptedQuantity(propReader.getProperty("acceptedQty"));
				logger.info("Accepted Qty entered: {}", propReader.getProperty("acceptedQty"));

				grnPage.enterRemark(propReader.getProperty("remark"));
				logger.info("Remark entered");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	
	
	
	
	
	
	
	
	
	
}














