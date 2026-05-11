package flowPack.inventoryModuleFlow.masterFlow;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import pageObjects.inventory.master.PurchaseOrderPages;
import pageObjects.setup.HomePage;
import utils.PropertyReader;
import utils.WaitHelper;

public class PurchaseOrderFlow {
	WebDriver driver;
	LoginFlow loginFlow;
	HomePage homePage;
	PurchaseOrderPages poPages;
	PropertyReader propReader;
	public static final Logger logger = LogManager.getLogger(PurchaseOrderFlow.class);

	public PurchaseOrderFlow(WebDriver driver) {
		logger.info("called PurchaseOrderFlow constructor  in PurchaseOrderFlow");
		this.driver = driver;
		loginFlow = new LoginFlow(driver);
		homePage = new HomePage(driver);
		poPages = new PurchaseOrderPages(driver);
		propReader = new PropertyReader("InventoryModule/PurchaseOrderTestData.properties");
	}

	public void prapareEnv() {
		logger.info("called prapareEnv() method in PurchaseOrderFlow");
		loginFlow.loginFlowCheck();
		homePage.clickInventoryModule();

		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 5);
		poPages.clickPurSubModAndPOLink();
	}

	// method to execute purchase order creation.
	public String creatingPurchaseOrder() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 5);
		poPages.clickCreateNewBtn();

		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 20);

		poPages.selectSeries(propReader.getProperty("SeriesDropOption"));

		poPages.selectParty(propReader.getProperty("PartyDropOption"));

		poPages.selectDispatchMode(propReader.getProperty("DispatchModeDropOption"));

		poPages.enterDeliveryDate(propReader.getProperty("DeliveryDateValue"));

		poPages.enterDeliveryAt(propReader.getProperty("DeliveryAtValue"));

		poPages.enterDeliveryPoint(propReader.getProperty("DeliveryPointValue"));

		poPages.enterPreferredTran(propReader.getProperty("PreferredTransValue"));

		poPages.clickItemDetailTab();

		poPages.selectItem(propReader.getProperty("ItemDropOption"));

		poPages.enterQuantity(propReader.getProperty("QuantityValue"));

		poPages.enterRate(propReader.getProperty("rateValue"));

		poPages.enterDiscount(propReader.getProperty("discountValue"));

		poPages.enterPkgForword(propReader.getProperty("pkgForwordValue"));

		poPages.enterAddButtonItemDtl();

		poPages.clickTermConTab();

		poPages.selectTermCondDrop(propReader.getProperty("TermsAndConDropOption"));

		poPages.enterAddButtonTerConTab();

		poPages.clickPayTermTab();

		poPages.enterX(propReader.getProperty("xValue"));

		poPages.enterY(propReader.getProperty("yValue"));

		poPages.selectPaymentTerm(propReader.getProperty("PaymentTermDropOption"));

		poPages.clickPaymentTermAddBtn();

		poPages.clickSubmitBtn();

		return poPages.getPoNo();
	}

	// method to execute purchase order creation.
	public void poflowUptoRateForMultiItems() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 10);
		poPages.clickCreateNewBtn();

		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 20);

		poPages.selectSeries(propReader.getProperty("SeriesDropOption"));

		poPages.selectParty(propReader.getProperty("PartyDropOption"));

		poPages.selectDispatchMode(propReader.getProperty("DispatchModeDropOption"));

		poPages.enterDeliveryDate(propReader.getProperty("DeliveryDateValue"));

		poPages.enterDeliveryAt(propReader.getProperty("DeliveryAtValue"));

		poPages.enterDeliveryPoint(propReader.getProperty("DeliveryPointValue"));

		poPages.enterPreferredTran(propReader.getProperty("PreferredTransValue"));

		poPages.clickItemDetailTab();
		logger.info("Adding first item in purchase order");

		poPages.selectItem(propReader.getProperty("ItemDropOption"));

		poPages.enterQuantity(propReader.getProperty("QuantityValue"));

		poPages.enterRate(propReader.getProperty("rateValue"));

		poPages.enterDiscount(propReader.getProperty("discountValue"));

		poPages.enterPkgForword(propReader.getProperty("pkgForwordValue"));

		poPages.enterAddButtonItemDtl();

		logger.info("First item added successfully in purchase order");
		
		logger.info("Adding second item in purchase order");

		poPages.selectItem(propReader.getProperty("ItemDropOption2"));

		poPages.enterQuantity(propReader.getProperty("QuantityValue2"));

		poPages.enterRate(propReader.getProperty("rateValue2"));

		poPages.enterDiscount(propReader.getProperty("discountValue2"));

		poPages.enterPkgForword(propReader.getProperty("pkgForwordValue2"));

		poPages.enterAddButtonItemDtl();

		logger.info("Second item added successfully in purchase order");

		
	}

	public String flowAfterAddingRateForMultiItems() {
		poPages.clickTermConTab();

		poPages.selectTermCondDrop(propReader.getProperty("TermsAndConDropOption"));

		poPages.enterAddButtonTerConTab();

		poPages.clickPayTermTab();

		poPages.enterX(propReader.getProperty("xValue"));

		poPages.enterY(propReader.getProperty("yValue"));

		poPages.selectPaymentTerm(propReader.getProperty("PaymentTermDropOption"));

		poPages.clickPaymentTermAddBtn();

		poPages.clickSubmitBtn();

		return poPages.getPoNo();
	}
	
	
	
	
	
	// method to get flow upto rate entering ,to validate GST calcualations.
	public void flowUptoRateEntering() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 10);
		poPages.clickCreateNewBtn();

		WaitHelper.waitForInvisibilityOfElementLocated(driver, poPages.getDotSpinner(), 20);
		WaitHelper.waitForClickable(driver, poPages.getSeriesDropdownField(), 10);
		poPages.selectSeries(propReader.getProperty("SeriesDropOption"));

		WaitHelper.waitForClickable(driver, poPages.getPartyDropdown(), 10);
		poPages.selectParty(propReader.getProperty("PartyDropOption"));

		poPages.selectDispatchMode(propReader.getProperty("DispatchModeDropOption"));

		poPages.enterDeliveryDate(propReader.getProperty("DeliveryDateValue"));

		poPages.enterDeliveryAt(propReader.getProperty("DeliveryAtValue"));

		poPages.enterDeliveryPoint(propReader.getProperty("DeliveryPointValue"));

		poPages.enterPreferredTran(propReader.getProperty("PreferredTransValue"));

		poPages.clickItemDetailTab();

		poPages.selectItem(propReader.getProperty("ItemDropOption"));

		poPages.enterQuantity(propReader.getProperty("QuantityValue"));

		poPages.enterRate(propReader.getProperty("rateValue"));
	}
	
	
	

}
