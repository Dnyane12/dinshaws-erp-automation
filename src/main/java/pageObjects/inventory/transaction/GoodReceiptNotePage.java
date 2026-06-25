package pageObjects.inventory.transaction;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import models.GrnItemData;
import utils.SimpleDropUtil;
import utils.WaitHelper;
import utils.WaitUtilityDuplicate;

public class GoodReceiptNotePage {
	WebDriver driver;
	private static Logger logger = LogManager.getLogger(GoodReceiptNotePage.class);

	public GoodReceiptNotePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//igx-nav-drawer//span[contains(@class, 'menu-l1-name') and normalize-space()='Stores']")
	private WebElement storeLink;

	By goodReceiptNoteFormLink = By.xpath("(//span[contains(normalize-space(.),'Good Receipt Note') and contains(@class,'fs-12')])[1]");

	@FindBy(xpath = "//div//button[contains(normalize-space(.),'Create New') and contains(@class,'icon-button')]")
	private WebElement createNewButton;

	By vendorDropField = By.xpath("(//label[normalize-space(text()='Vendor')]/following::input[@class='igx-input-group__input'])[6]");

	By vendorDropOptField = By.xpath("//div//span[contains(normalize-space(text()),'++')]");

	@FindBy(xpath = "(//label[normalize-space(text())='PO No.']/following::igx-icon[normalize-space(text())='expand_more'])[1]")
	private WebElement poNoDropList;
	
	@FindBy(xpath = "(//label[normalize-space(text())='PO No.']/following::input[@role='combobox'])[1]")
	private WebElement poDropdownField;

	By poNoSearchPopup = By.xpath("//input[@name='searchInput']");

	@FindBy(xpath = "(//igx-checkbox[contains(@id,'igx-checkbox')])[3]")
	private WebElement poOptionCheckbox;

	@FindBy(xpath = "//button[normalize-space(text())='Fetch Data' and @id='l_grn_create_fetch_data-width-selector']")
	private WebElement fetchDataButton;

	By grnInfoTab = By.xpath("//span//app-g-label[normalize-space(text())='GRN Info']");

	@FindBy(xpath = "(//label[normalize-space(text())='Transporter Mode']/following::igx-icon[normalize-space(text())='expand_more'])[1]")
	private WebElement transporterMode;

	@FindBy(xpath = "//igx-grid-row[.//igx-grid-cell[contains(@id,'l_inv_grnpo_igx_grid_')]]")
	private List<WebElement> gridRows;

	@FindBy(xpath = "//igx-grid")
	private WebElement grid;
	
	@FindBy(xpath = "//input[@id='l_ingh_lr_no']")
	private WebElement lrNoField;

	@FindBy(xpath = "(//input[contains(@class,'igx-date-picker__input-date')])[3]")
	private WebElement lrDateField;

	@FindBy(xpath = "//input[(@id='l_ingh_invoice_no')]")
	private WebElement invoiceNoField;

	@FindBy(xpath = "(//input[contains(@class,'igx-date-picker__input-date')])[4]")
	private WebElement invoiceDateField;

	@FindBy(xpath = "(//app-g-label[normalize-space((text())='GRN Details')])[3]")
	private WebElement grnDetailsTab;

	@FindBy(xpath = "//igx-icon[normalize-space(text())='edit' and contains(@class,'material-icons')]")
	private WebElement editIcon;

	@FindBy(xpath = "(//app-g-label[normalize-space((text())='GRN PO')])[1]")
	private WebElement grnPoTab;

	@FindBy(xpath = "//input[contains(@id,'l_ingd_inv_qty')]")
	private WebElement invoiceQtyField;

	@FindBy(xpath = "//input[contains(@id,'l_ingd_recd_qty')]")
	private WebElement receivedQtyField;

	@FindBy(xpath = "//input[contains(@id,'l_ingd_accepted_qty')]")
	private WebElement acceptedQtyField;

	@FindBy(xpath = "(//button[normalize-space(text())='Update'])[1]")
	private WebElement updateButton;

	@FindBy(xpath = "//span[contains(normalize-space(text()),'Total Net Value')]")
	private WebElement netvalueWholeText;

	@FindBy(xpath = "//input[contains(@id,'l_ingh_invoice_value')]")
	private WebElement invoiceValueGrnInfoTab;

	@FindBy(xpath = "//button[normalize-space(text())='Submit']")
	private WebElement submitButton;

	By dotSpinner = By.xpath("//div[@class='dot-spinner']");

	@FindBy(xpath = "//input[contains(@id,'l_ingd_remarks')]")
	private WebElement remarkField;

	@FindBy(xpath = "//igx-grid-header[@id='l_inv_grn_hdr_igx_grid_ingh_doc_no']/following::igx-grid-cell[contains(@class,'igx-grid__td') and @id='l_inv_grn_hdr_igx_grid_0_0']")
	private WebElement listingGrnNo;

	@FindBy(xpath = "//span[normalize-space()='Good Receipt Note']")
	private WebElement listingTitle;

	@FindBy(xpath = "//div[@class='igx-snackbar__message' and contains(text(),'GRN Created successfully')]")
	private WebElement submitSuccMsg;

//	@FindBy(xpath = "//span[normalize-space(text())='Good Receipt Note' and contains(@class,'fs-18')]")
//	private WebElement listingPageHeader;

	@FindBy(xpath = "//label[normalize-space()='Vendor']/ancestor::igx-input-group//input[@role='combobox']")
	private WebElement selVendorField;

	@FindBy(xpath = "(//label[normalize-space()='PO No.']/ancestor::igx-input-group//input[@role='combobox'])[1]")
	private WebElement selPoNoField;

	@FindBy(xpath = "//button[contains(@id,'l_action_reset_btn-width-selector') and normalize-space()='Reset']")
	private WebElement resetButton;

	@FindBy(xpath = "//button[contains(@id,'l_action_back_btn-width-selector') and normalize-space()='Back']")
	private WebElement backButton;

	@FindBy(xpath = "//div[contains(@id,'1_title') and normalize-space()='Confirmation']")
	private WebElement confirmationPopup;

	@FindBy(xpath = "//button[contains(@class,'igx-button') and normalize-space()='OK']")
	private WebElement confOkButton;

	By listpageHeader = By
			.xpath("//span[contains(@class,'fs-18') and contains(normalize-space(),'Good Receipt Note')]");

	@FindBy(xpath = "//div[contains(normalize-space(text()),'GRN Created successfully ID:') and @class='igx-snackbar__message']")
	private WebElement succMsg;

	@FindBy(xpath = "//div[@class='display-cell']//span[normalize-space(text())='Good Receipt Note']")
	private WebElement grnTitleInGrnForm;

	@FindBy(xpath ="//igx-grid-row[@aria-selected='true']")
	private List<WebElement> gridRowsOnListingPage;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//Action methods

	public String extractGrnNo() {
		WaitHelper.waitForVisible(driver, succMsg, 20);
		String successMessage = succMsg.getText();
		System.out.print("successMessage:" + successMessage);
		return successMessage;
	}
	
	
//	public List<String> extractPoQtyFromGrid() {
//		WaitHelper.waitForVisibilityOfAllElements(driver, gridRows, 10);
//		for (WebElement row : gridRows) {
//			try {
//				 List<WebElement> poQtyColumns = row.findElements(By.xpath("//igx-grid-cell[contains(@aria-describedby,'l_inv_grnpo_igx_grid_pod_qty')]"));
//				  for(WebElement poQty : poQtyColumns) {
//					  System.out.println("poQty:" + poQty.getText());
//				  }
//
//				  System.out.println("poQty:" + poQty);
//				return poQty;
//			} catch (Exception e) {
//				System.out.println("PO Qty not found in this row");				
//			}
//		}
//		return null;		
	//}

	public WebElement getRemarkField() {
		return remarkField;
	}

	public By getListpageHeader() {
		return listpageHeader;
	}

	public void setRemarkField(WebElement remarkField) {
		this.remarkField = remarkField;
	}

	public By getDotSpinner() {
		return dotSpinner;
	}

	public void setDotSpinner(By dotSpinner) {
		this.dotSpinner = dotSpinner;
	}

	public void clickStoreLink() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		WaitHelper.waitForClickable(driver, storeLink, 10);
		storeLink.click();
	}

	public void clickGrnLink() {
		WaitHelper.waitForRefreshAndClick(driver, goodReceiptNoteFormLink, 10);
	}

	public void clickCreateNewButton() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WaitHelper.waitForClickable(driver, createNewButton, 10);
		js.executeScript("arguments[0].scrollIntoView(true);", createNewButton);
		js.executeScript("arguments[0].click();", createNewButton);
		// WaitHelper.waitForRefreshAndClick(driver, createNewButton, 20);
	}

	public void selectVendor(String vendorDropOption) {
		By vendorDropOptField = By.xpath("//div//span[contains(normalize-space(text()),'" + vendorDropOption + "')]");
		SimpleDropUtil.selectDropOption(driver, vendorDropField, vendorDropOptField, vendorDropOption);
	}

	public void enterPoNoToSearch(String poNoDropOption) {
		WaitHelper.waitForClickable(driver, poNoDropList, 10);
		poNoDropList.click();

		WaitHelper.waitForClickable(driver, poNoSearchPopup, 10);
		WaitHelper.waitForRefreshAndClick(driver, poNoSearchPopup, 10);

		driver.findElement(poNoSearchPopup).sendKeys(poNoDropOption);
		WaitHelper.waitForClickable(driver, poOptionCheckbox, 10);
		poOptionCheckbox.click();
	}

	public void clickFetchData() {
		WaitHelper.waitForClickable(driver, fetchDataButton, 10);
		fetchDataButton.click();
	}

	public void clickGrnInfoTab() {
		WaitHelper.waitForClickable(driver, grnInfoTab, 10);
		WaitHelper.waitForRefreshAndClick(driver, grnInfoTab, 20);
	}

	public void selectTransporterMode(String transporterModeLabel, String transporterModeOption) {
		WaitHelper.waitForClickable(driver, transporterMode, 20);
		WaitUtilityDuplicate.selectFromComboWithoutSearch(driver, transporterModeLabel, transporterModeOption);
		// WaitUtilityDuplicate.selectFromComboWithoutSearch(driver, wait,
		// transporterModeLabel, transporterModeOption);
	}

	public void enterLrNo(String lrNo) {
		WaitHelper.waitForClickable(driver, lrNoField, 10);
		lrNoField.click();
		lrNoField.sendKeys(lrNo);
	}

	public void enterLrDate(String lrDate) {
		WaitHelper.waitForClickable(driver, lrDateField, 10);
		lrDateField.click();
		lrDateField.sendKeys(lrDate);
	}

	public void enterInvoiceNo(String InvoiceNo) {
		WaitHelper.waitForClickable(driver, invoiceNoField, 10);
		invoiceNoField.click();
		invoiceNoField.sendKeys(InvoiceNo);
	}

	public void enterInvoiceDate(String invoiceDate) {
		WaitHelper.waitForClickable(driver, invoiceDateField, 10);
		invoiceDateField.click();
		invoiceDateField.sendKeys(invoiceDate);
	}

	public void clickGrnDetailsTab() {
		WaitHelper.waitForClickable(driver, grnDetailsTab, 10);
		grnDetailsTab.click();
	}

	public void clickEditIcon(int itemRowIndex) {
		By editIconBy = By.xpath("(//igx-icon[normalize-space(text())='edit' and contains(@class,'material-icons')])["
				+ itemRowIndex + "]");
		WaitHelper.waitForClickable(driver, editIconBy, 10);
		driver.findElement(editIconBy).click();
	}

	public void clickEditIcon() {
		WaitHelper.waitForClickable(driver, editIcon, 10);
		editIcon.click();
	}

	public void enterInvoiceQuantity(String invoiceQty) {
		WaitHelper.waitForClickable(driver, invoiceQtyField, 10);
		invoiceQtyField.click();
		invoiceQtyField.sendKeys(invoiceQty);
	}

	public void enterReceivedQuantity(String receivedQty) {
		WaitHelper.waitForClickable(driver, receivedQtyField, 10);
		receivedQtyField.click();
		receivedQtyField.sendKeys(receivedQty);
	}

	public void enterAcceptedQuantity(String acceptedQty) {
		WaitHelper.waitForClickable(driver, acceptedQtyField, 10);
		acceptedQtyField.click();
		acceptedQtyField.sendKeys(acceptedQty);
	}

	public void enterRemark(String remark) {
		WaitHelper.waitForClickable(driver, remarkField, 10);
		remarkField.click();
		remarkField.sendKeys(remark);
	}

	public void clickUpdateButton() {
		WaitHelper.waitForClickable(driver, updateButton, 10);
		updateButton.click();
	}

	public void extractTotalNetAmount() {
		// Step 1: Wait for label to appear
		WaitHelper.waitForTextToBePresentInElement(driver, netvalueWholeText, "Total Net Value:", 30);

		// Step 2: Poll until a valid numeric value appears after the colon
		WaitHelper.normalWait(driver, 60);

		String netAmount = WaitHelper.waitForValidNumericValueAfterColon(driver, netvalueWholeText, 30);

		// logger.info("Extracted Net Amount: {}", netAmount);
		System.out.println("netAmount in page class:" + netAmount);

		WaitHelper.waitForRefreshAndClick(driver, grnInfoTab, 30);
		WaitHelper.waitForClickable(driver, invoiceValueGrnInfoTab, 10);
		invoiceValueGrnInfoTab.click();
		invoiceValueGrnInfoTab.sendKeys(netAmount);
		System.out.println("netAmount:" + netAmount);

	}

	public String extractGrnNoCreated() {
		WaitHelper.waitForVisible(driver, listingGrnNo, 10);
		String grnNo = listingGrnNo.getText();
		System.out.println("grnNo:" + grnNo);
		return grnNo;
	}

	public void clickSubmitButton() {
		WaitHelper.waitForClickable(driver, submitButton, 10);
		submitButton.click();
	}

	public String extractSubmitSuccMessage() {
		WaitHelper.waitForVisible(driver, submitSuccMsg, 10);
		String succMsg = submitSuccMsg.getText();
		System.out.println("succMsg:" + succMsg);
		return succMsg;
	}

	public void clickResetButton() {
		WaitHelper.waitForClickable(driver, resetButton, 10);
		resetButton.click();
		WaitHelper.waitForVisible(driver, confirmationPopup, 10);
		WaitHelper.waitForClickable(driver, confOkButton, 10);
		confOkButton.click();
	}

	public String getGRNFormTitle() {
		WaitHelper.waitForVisible(driver, grnTitleInGrnForm, 10);
		String grnFormTitle = grnTitleInGrnForm.getText();
		System.out.println("grnFormTitle:" + grnFormTitle);
		return grnFormTitle;
	}

	public void extractPoQtyFromGrid1() {
		WaitHelper.waitForVisibilityOfAllElements(driver, gridRowsOnListingPage, 10);
		//String poQty = gridRowsOnListingPage.findElements(By.xpath("//div//span[contains(normalize-space(text()),'PO Qty.:')]")).getText();
		//System.out.println("poQty:" + poQty);
	}

	public void extractGRNNoFromGrid() {
	   // WaitHelper.waitForVisibilityOfAllElements(driver, gridRows, 20);
	    for (WebElement row : gridRowsOnListingPage) {
	        try {
	        	 //System.out.println("Row Text : " + row.getText());
	            String GrnNo = row.findElement(By.xpath(".//igx-grid-cell[@aria-describedby='l_inv_grn_hdr_igx_grid_ingh_doc_no']")).getText();
	            System.out.println("GrnNo: " + GrnNo);
	            
	        } catch (Exception e) {
	            System.out.println("GrnNo not found in this row");
	        }
	    }
	}
	
	
	public List<String> extractAllGRNNumbers() throws InterruptedException {
	    Set<String> grnNumbers = new LinkedHashSet<>();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement virtualScroller = driver.findElement(By.cssSelector("igx-virtual-helper.igx-vhelper--vertical"));
	    long previousCount = -1;
	    
	    while (true) {
	        List<WebElement> rows = driver.findElements(By.xpath("//igx-display-container//igx-grid-row[@role='row']"));
	        for (WebElement row : rows) {
	            try {
	                String grnNo = row.findElement(By.xpath(".//igx-grid-cell[@aria-describedby='l_inv_grn_hdr_igx_grid_ingh_doc_no']")).getText().trim();
	                if (!grnNo.isBlank()) {
	                    grnNumbers.add(grnNo);
	                }
	            } catch (Exception e) {
	                // Ignore row
	            }
	        }
	        if (grnNumbers.size() == previousCount) {
	            break;
	        }
	        previousCount = grnNumbers.size();
	        js.executeScript(
	                "arguments[0].scrollTop += 400;",
	                virtualScroller);
	        Thread.sleep(1000);
	    }
	    System.out.println("Total GRNs Found : " + grnNumbers.size());
	    grnNumbers.forEach(System.out::println);
	    return new ArrayList<>(grnNumbers);
	}
	
		
	
	public boolean findGRNNofromListingPage() {
		String grnNo = "A02-GRN-26-00176";
		List<WebElement> rows = driver.findElements(By.xpath("//igx-grid-row[@role='row']"));
		boolean found = false;
		for(WebElement row : rows){
		    if(row.getText().contains(grnNo))
		    {
		        System.out.println("GRN Found : " + grnNo);
		        found = true;
		        break;
		    }
		}
		if(!found)
		{
		  System.out.println("GRN Not Found");
		}
		return found;
	}
	
	
	
	
	// Method to extract item details from the GRN PO  tab
	// Method to extract item details from the GRN PO  tab
	public List<GrnItemData> getGrnItemDetails() {
		WaitHelper.waitForVisibilityOfAllElements(driver, gridRows, 10);
		List<GrnItemData> grnItems = new ArrayList<>();

		if (gridRows == null || gridRows.isEmpty()) {
			return grnItems;
		}

		for (WebElement row : gridRows) {

			String itemName = safeGetCellText(row, "l_inv_grnpo_igx_grid_pod_item_name");
			String quantity = safeGetCellText(row, "l_inv_grnpo_igx_grid_pod_qty");
			String rate = safeGetCellText(row, "l_inv_grnpo_igx_grid_pod_net_rate");
			String itemHsn = safeGetCellText(row, "l_inv_grnpo_igx_grid_item_hsn_code_hsnm");
			String uom = safeGetCellText(row, "l_inv_grnpo_igx_grid_pod_po_uom_unit");
			String itemLocation = safeGetCellText(row, "l_inv_grnpo_igx_grid_stor_substore_description");

			// Debug purpose
			System.out.println("Item Name : " + itemName + " | Qty : " + quantity + " | Rate : " + rate + " | HSN : "
					+ itemHsn + " | UOM : " + uom + " | Location : " + itemLocation);

			// Use constructor that exists in GrnItemData
			GrnItemData itemData = new GrnItemData(itemName, quantity, rate, itemHsn, itemLocation, uom);
			grnItems.add(itemData);
		}
		return grnItems;
	}

	
	// Helper to safely read a cell value (returns empty string if not present)
	private String safeGetCellText(WebElement row, String columnName) {
		try {
			return getCellText(row, columnName);
		} catch (Exception e) {
			// element/cell might not be present for this row; return empty string
			return "";
		}
	}

	private String getCellText(WebElement row, String columnName) {
		return row.findElement(By.xpath(".//igx-grid-cell[contains(@aria-describedby,'" + columnName + "')]")).getText().trim();
	}
		
	public String extractSelPoNo() {
		WaitHelper.waitForClickable(driver, selPoNoField, 10);
		String selPoNo = selPoNoField.getAttribute("value");
		System.out.println("selPoNo: " + selPoNo);
		return selPoNo;
	}
	
	public String extractSelVendor() {
		WaitHelper.waitForClickable(driver, selVendorField, 10);
		String selVendor = selVendorField.getAttribute("value");
		System.out.println("selVendor: " + selVendor);
		return selVendor;
	}
	
	public void clickBackButton() {
		WaitHelper.waitForClickable(driver, backButton, 10);
		backButton.click();
	}
	
	public boolean isGrnFormDisplayed() {
		try {
			WaitHelper.waitForVisible(driver, grnTitleInGrnForm, 10);
			return grnTitleInGrnForm.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean isGRNListingGridDisplayed() {
		try {
			WaitHelper.waitForVisible(driver, grid, 10);
			return grid.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean isFieldHighlighted(WebElement element) {
	    return Boolean.parseBoolean(element.getAttribute("aria-invalid"));
	}
	
	public boolean isSubmitButtonEnabled() {
	    return submitButton.isEnabled();
	}
	
	
	
	
	
	
	
	
	
	
	
//Getter & Setters

	public WebElement getStoreLink() {
		return storeLink;
	}

	public void setStoreLink(WebElement storeLink) {
		this.storeLink = storeLink;
	}

	public By getGoodReceiptNoteFormLink() {
		return goodReceiptNoteFormLink;
	}

	public void setGoodReceiptNoteFormLink(By goodReceiptNoteFormLink) {
		this.goodReceiptNoteFormLink = goodReceiptNoteFormLink;
	}

	public WebElement getCreateNewButton() {
		return createNewButton;
	}

	public void setCreateNewButton(WebElement createNewButton) {
		this.createNewButton = createNewButton;
	}

	public By getVendorDropField() {
		return vendorDropField;
	}

	public void setVendorDropList(By vendorDropField) {
		this.vendorDropField = vendorDropField;
	}

	public WebElement getPoNoDropList() {
		return poNoDropList;
	}

	public void setPoNoDropList(WebElement poNoDropList) {
		this.poNoDropList = poNoDropList;
	}

	public By getPoNoSearchPopup() {
		return poNoSearchPopup;
	}

	public void setPoNoSearchPopup(By poNoSearchPopup) {
		this.poNoSearchPopup = poNoSearchPopup;
	}

	public WebElement getPoOptionCheckbox() {
		return poOptionCheckbox;
	}

	public void setPoOptionCheckbox(WebElement poOptionCheckbox) {
		this.poOptionCheckbox = poOptionCheckbox;
	}

	public WebElement getFetchDataButton() {
		return fetchDataButton;
	}

	public void setFetchDataButton(WebElement fetchDataButton) {
		this.fetchDataButton = fetchDataButton;
	}

	public By getGrnInfoTab() {
		return grnInfoTab;
	}

	public WebElement getTransporterMode() {
		return transporterMode;
	}

	public void setTransporterMode(WebElement transporterMode) {
		this.transporterMode = transporterMode;
	}

	public WebElement getLrNoField() {
		return lrNoField;
	}

	public WebElement getLrDate() {
		return lrDateField;
	}

	public WebElement getInvoiceNoField() {
		return invoiceNoField;
	}

	public WebElement getInvoiceDate() {
		return invoiceDateField;
	}

	public WebElement getGrnDetailsTab() {
		return grnDetailsTab;
	}

	public WebElement getEditIcon() {
		return editIcon;
	}

	public WebElement getGrnPoTab() {
		return grnPoTab;
	}

	public WebElement getInvoiceQty() {
		return invoiceQtyField;
	}

	public WebElement getReceivedQty() {
		return receivedQtyField;
	}

	public WebElement getAcceptedQty() {
		return acceptedQtyField;
	}

	public WebElement getUpdateButton() {
		return updateButton;
	}

	public WebElement getNetvalueWholeText() {
		return netvalueWholeText;
	}

	public WebElement getInvoiceValueGrnInfoTab() {
		return invoiceValueGrnInfoTab;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getListingGrnNo() {
		return listingGrnNo;
	}

	public WebElement getListingTitle() {
		return listingTitle;
	}

	public WebElement getSubmitSuccMsg() {
		return submitSuccMsg;
	}

	public WebElement getSelVendorField() {
		return selVendorField;
	}

	public WebElement getSelPoNoField() {
		return selPoNoField;
	}

	public WebElement getResetButton() {
		return resetButton;
	}

	public WebElement getConfirmationPopup() {
		return confirmationPopup;
	}

	public WebElement getConfOkButton() {
		return confOkButton;
	}

	public WebElement getGrid() {
		return grid;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public By getVendorDropOptField() {
		return vendorDropOptField;
	}

	public WebElement getSuccMsg() {
		return succMsg;
	}

//	public List<WebElement> getGridRow() {
//		return ;
//	}

	public WebElement getPoDropdownField() {
		return poDropdownField;
	}

	public List<WebElement> getGridRowsOnListingPage() {
		return gridRowsOnListingPage;
	}

	
	
}
