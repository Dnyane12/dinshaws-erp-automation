package pageObjects.inventory.transaction;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;
import utils.WaitUtilityDuplicate;

import org.openqa.selenium.WebDriver;

public class PurchaseReturnPage {
	 WebDriver driver;
	 public static final Logger logger =LogManager.getLogger(PurchaseReturnPage.class);	
		
		public PurchaseReturnPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		

		By purchaseSubModLink =By.xpath("(//span[normalize-space(text())='Purchase' and contains(@class,'fs-13')])[1]");
		
		
		@FindBy(xpath="//span[contains(normalize-space(.),'Purchase Return')and contains(@class,'fs-12')]")
		private WebElement prnLink;
				
		By dotSpinner =By.xpath("/html/body/app-root/div/div/div/div/div");

		By createNewButton = By.xpath("//div[contains(@class,'tw-flex')]//button[normalize-space(text())='Create New' and contains(@class,'icon-button')]");
		
		@FindBy(xpath="//div[contains(normalize-space(text()),'PRN Created successfully ID') and contains(@class,'igx-snackbar__message')]")
		private WebElement succMsgField;
		
		

		@FindBy(xpath="(//label[contains(normalize-space(text()),'GRN No')]/following::input[@role='combobox'])[4]")
		private WebElement grnNoDrop;
		
		@FindBy(xpath="//div[@role='listbox']//igx-display-container[contains(@class,'igx-display-container')]")
		private WebElement grnNoDropOptList;
		
		
		
		
		@FindBy(xpath="(//label[normalize-space(text())='Vendor' and contains(@id,'igx-label')]/following::input[@class='igx-input-group__input'])[1]")
		private WebElement vendorDropList;
		

		By grnDetailsTab =By.xpath("//div[@class='igx-tabs__header-item-inner']//app-g-label[normalize-space(text())='GRN Details']");
		
	
		By errorLoggerOverlay =By.xpath("//div[contains(@class,'right-container')]//div[contains(@class,'error-logger-api-response-overlay')]");
	
		By errorLogArrowDownBtn =By.xpath("//div//igx-icon[text()='keyboard_arrow_down']");
		
		@FindBy(xpath="(//igx-icon[contains(normalize-space(.),'edit') and contains(@class,'material-icons')])[1]")
		private WebElement editIcon;
		
		

		@FindBy(xpath="//input[contains(@id,'l_purchase_return_accepted_quanaity')]")
		private WebElement returnFromStoreField;
		
		

		@FindBy(xpath="//input[contains(@id,'l_purchase_return_rejected_quanaity')]")
		private WebElement returnFromRejField;
		
		

		@FindBy(xpath="//button[contains(@id,'l_grn_details_update_btn-width-selector')]")
		private WebElement updateButton;
		
		
		@FindBy(xpath="(//label[normalize-space(text())='Freight Logic' and contains(@id,'igx-label')]/following::igx-icon[contains(normalize-space(text()),'expand_more')])[1]")
		private WebElement freightLogicDrop;
		
		@FindBy(xpath="nput[contains(@id,'l_ingh_freight_amount')]")
		private WebElement freightAmt;
		
		@FindBy(xpath="//button[contains(@id,'l_grn_apply_freight_logic-width-selector')]")
		private WebElement AapplayButton;
		
		
		@FindBy(xpath="	//span[contains(normalize-space(text()),'Total Net Value:')]")
		private WebElement totalNetAmt;

		
		@FindBy(xpath="//button[contains(normalize-space(text()),'Submit')]")
		private WebElement submitBtn;
		
			
		@FindBy(xpath="(//button[contains(normalize-space(text()),'Reset')])[1]")
		private WebElement resetBtn;
		
		
		@FindBy(xpath="(//button[contains(normalize-space(text()),'Back')])[1]")
		private WebElement backBtn;
			
		
		@FindBy(xpath="(//label[normalize-space(text())='Vendor' and contains(@id,'igx-label')]/following::input[contains(@id,'l_ingh_party_name')])[1]")
		private WebElement vendorValue;
		
		
		@FindBy(xpath="(//label[normalize-space(text())='Transporter' and contains(@id,'igx-label')]/following::input[contains(@id,'l_ingh_transporter_name')])[1]")
		private WebElement transporterValue;
		
		@FindBy(xpath="(//label[normalize-space(text())='Transporter Mode' and contains(@id,'igx-label')]/following::input[contains(@class,'igx-input-group__input')])[1]")
		private WebElement transporterModeValue;
		
		
		@FindBy(xpath="(//label[normalize-space(text())='LR Date' and contains(@id,'igx-label')]/following::input[contains(@class,'igx-date-picker')])[1]")
		private WebElement lrDateValue;
		
			
		@FindBy(xpath="(//label[normalize-space(text())='Invoice No.' and contains(@id,'igx-label')]/following::input[contains(@id,'l_ingh_invoice_no')])[1]")
		private WebElement invoiceNoValue;
		
		
		@FindBy(xpath="(//label[normalize-space(text())='Invoice Date' and contains(@id,'igx-label')]/following::input[contains(@class,'igx-date-picker')])[1]")
		private WebElement invoiceDateValue;
		
		
		@FindBy(xpath="//div[@class='igx-snackbar__message' and contains(normalize-space(text()),'PRN Created successfully ID:')]")
		private WebElement submitBtnSucusessMsg;
		
	 
		
		@FindBy(xpath="(//igx-icon[contains(@class,'material-icons') and contains(normalize-space(text()),'visibility')])[1]")
		private WebElement viewIcon;
		
		
		
		@FindBy(xpath="//input[contains(@id,'l_purchase_return_quanaity') and contains(@class,'ng-untouched')]")
		private WebElement returnQtyValue;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'Rate:')and contains(@class,'pl-3')])[1]")
		private WebElement rateQtyLabelDis;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'Gross Amount:')and contains(@class,'pl-3')])[1]")
		private WebElement grossAmtQtyLabelDis;

		@FindBy(xpath="(//span[contains(normalize-space(text()),'Landed Rate:')and contains(@class,'pl-3')])[1]")
		private WebElement landedRateQtyLabelDis;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'Total Tax Amount:')and contains(@class,'pl-3')])[1]")
		private WebElement totalTaxAmtLabelDis;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'Total Tax %:')and contains(@class,'pl-3')])[1]")
		private WebElement totalTaxRatePerLabelDis;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'Total Net Amount:')and contains(@class,'pl-3')])[1]")
		private WebElement totalNetAmtLabelDis;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'SGST')and contains(@class,'pl-3')])[1]")
		private WebElement sgstPerAmtLabelDis;
		
		
		@FindBy(xpath="(//span[contains(normalize-space(text()),'CGST')and contains(@class,'pl-3')])[1]")
		private WebElement cgstPerAmtLabelDis;
		
		By homeIcon = By.xpath("//div//igx-icon[normalize-space(text())='home' and contains(@class,'material-icons')]");
		
		
		
		
		
		
		public WebElement getRateQtyLabelDis() {
			return rateQtyLabelDis;
		}

		public void setRateQtyLabelDis(WebElement rateQtyLabelDis) {
			this.rateQtyLabelDis = rateQtyLabelDis;
		}
		
		
		
		public void clickHomeIcon() {
			WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 20);
			WaitHelper.waitForClickable(driver, driver.findElement(homeIcon), 20);
			WaitHelper.waitForRefreshAndClick(driver,homeIcon, 20);
		}

		public void clickPurchaseSubModuleLink() {			
				logger.info("Waiting and Clicking voucherLink");	
				WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);	
				WaitHelper.waitForRefreshAndClick(driver,purchaseSubModLink,10);			
			
			//logger.info("clicking prnLink");
			//WaitHelper.waitForClickable(driver,purchaseSubModLink, 10);
			//purchaseSubModLink.click();
		}
		
		public void clickPrnLink() {
			logger.info("clicking prnLink");
			WaitHelper.waitForClickable(driver, prnLink, 10);
			prnLink.click();
		}
		
		
		public void clickCreateNewButton() {
			logger.info("clicking create New button");
			WaitHelper.waitForInvisibilityOfElementLocated(driver,dotSpinner, 20);	
			WaitHelper.waitForClickable(driver,createNewButton, 20);
				
			//createNewButton.click();		
			WaitHelper.waitForRefreshAndClick(driver, createNewButton, 20);			
			WaitHelper.waitForInvisibilityOfElementLocated(driver,dotSpinner, 20);
		}
		
		
		public void selectGrnNo(String grnNo){			
			WaitHelper.waitForClickable(driver, grnNoDrop, 20);	
			grnNoDrop.click();
			
			WaitHelper.waitForVisible(driver, grnNoDropOptList, 30);
			System.out.println("grnNo in purchaseReturnPage class: "+grnNo);
			grnNoDrop.sendKeys(grnNo);
						
			WebElement grnNoDropOptField =  driver.findElement(By.xpath("//span[contains(normalize-space(text()),'" + grnNo.trim() + "')]"));
			WaitHelper.waitForClickable(driver, grnNoDropOptField, 20);	
			grnNoDropOptField.click();		
		}
		
		
		public void clickGrnDetailsTab() {
			logger.info("clicling grnDetails tab");						
			WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 20);
			WaitHelper.waitForClickable(driver,errorLogArrowDownBtn, 20);
			WaitHelper.waitForRefreshAndClick(driver,errorLogArrowDownBtn, 20);
			
			WaitHelper.waitForClickable(driver,grnDetailsTab, 30);
			WaitHelper.waitForRefreshAndClick(driver,grnDetailsTab, 20);
		}
		
		
		public void clickEditButtonIcon() {
			logger.info("clicling EditButtonIcon");
			WaitHelper.waitForClickable(driver,editIcon, 10);
			editIcon.click();
		}
		
		
		public void enterReturnFromStore(String returnFromStoreFieldValue) {
			logger.info("entring the returnFromStoreFieldValue");
			WaitHelper.waitForClickable(driver,returnFromStoreField, 10);
			returnFromStoreField.click();
			returnFromStoreField.sendKeys(returnFromStoreFieldValue);
		}
		
		
		public void enterReturnFromRej(String returnFromRejFieldValue) {
			logger.info("entring the returnFromRejField value");
			WaitHelper.waitForClickable(driver, returnFromRejField, 10);
			returnFromRejField.click();
			returnFromRejField.sendKeys(returnFromRejFieldValue);
		}
		
		
		public void clickUpdateButton() {
			logger.info("clicling update button");
			WaitHelper.waitForClickable(driver,updateButton, 10);
		    updateButton.click();
		}
		
		
		public void getTotalNetAmout() {
			logger.info("getting total Net amount");
			WaitHelper.waitForClickable(driver, totalNetAmt, 10);
			String netAmtText= totalNetAmt.getText();
			
			String[] parts = netAmtText.split(":");
		    if (parts.length > 1) {
		        String netAmount = parts[1].trim(); // removes spaces
		        logger.info("Extracted Net Amount: " + netAmount);
		    } else {
		        logger.warn("Net amount text not in expected format: " + netAmtText);
		    }
		}
		
		
		public void clickSubmitButton() {
			logger.info("clicling submit button");
			WaitHelper.waitForClickable(driver,submitBtn, 10);
			submitBtn.click();
		}
		
		
		public void clickViewIconButton() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForClickable(driver,viewIcon, 10);
			viewIcon.click();
		}
		
		
		
		public String extractingFetchedReturnQty() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,returnQtyValue, 10);
			String returnQtyValueFteched=returnQtyValue.getAttribute("value");
			return returnQtyValueFteched;
			
		}	
		
		public String extractingFetchedRate() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,rateQtyLabelDis, 10);
			String RateQtyLabelDisplay=rateQtyLabelDis.getText();
			
			System.out.println("RateQtyLabelDisplay:"+RateQtyLabelDisplay);
			String parts[]= RateQtyLabelDisplay.split(":");
			
			String fetchedRateQty= parts[1];
			return fetchedRateQty;		
		}
		
		
		public String extractingFetchedGrossAmt() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,grossAmtQtyLabelDis, 10);
			String grossAmtLabelDisplay=grossAmtQtyLabelDis.getText();
			String parts[]= grossAmtLabelDisplay.split(":");
			
			String fetchedGrossAmt= parts[1];
			return fetchedGrossAmt;		
		}
		
		
		public String extractingFetchedLandedRate() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,landedRateQtyLabelDis, 10);
			String landedRateQtyDis=landedRateQtyLabelDis.getText();
			String parts[]= landedRateQtyDis.split(":");
			
			String fetchedLandedRate= parts[1];
			return fetchedLandedRate;		
		}
		
		
		public String extractingFetchedTotalTaxAmt() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,totalTaxAmtLabelDis, 10);
			String totalTaxAmtLabel=totalTaxAmtLabelDis.getText();
			String parts[]= totalTaxAmtLabel.split(":");
			
			String fetchedTotalTaxAmt= parts[1];
			return fetchedTotalTaxAmt;		
		}
		
		
		public String extractingFetchedTotalTaxPer() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,totalTaxRatePerLabelDis, 10);
			String totalTaxPerLabel=totalTaxRatePerLabelDis.getText();
			String parts[]= totalTaxPerLabel.split(":");
			
			String totalTaxPer= parts[1];
			return totalTaxPer;		
		}
		
		public String extractingFetchedTotalNetAmt() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForVisible(driver,totalNetAmtLabelDis, 10);
			String totalNetAmtLabel=totalNetAmtLabelDis.getText();
			String parts[]= totalNetAmtLabel.split(":");
			
			String totalNetAmt= parts[1];
			return totalNetAmt;		
		}
		
		
		
		public String extractingFetchedSgstAmt() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForClickable(driver,sgstPerAmtLabelDis, 10);
			String sgstAmtLabel=sgstPerAmtLabelDis.getText();
			String parts[]= sgstAmtLabel.split(":");
			
			String sgstAmt= parts[1];
			return sgstAmt;		
		}
		
		
		public String extractingFetchedCgstAmt() {
			logger.info("clicling view icon on listing page");
			WaitHelper.waitForClickable(driver, cgstPerAmtLabelDis, 10);
			String cgstAmtLabel=cgstPerAmtLabelDis.getText();
			String parts[]= cgstAmtLabel.split(":");
			
			String cgstAmt= parts[1];
			return cgstAmt;		
		}
		
		
		
		
		
		
		
		
		
		
		//Getters and Setters
		
		public By getPurchaseSubModLink() {
			return purchaseSubModLink;
		}


		public WebElement getPrnLink() {
			return prnLink;
		}


		public void setPrnLink(WebElement prnLink) {
			this.prnLink = prnLink;
		}


		public By getCreateNewButton() {
			return createNewButton;
		}


		public WebElement getGrnNoDrop() {
			return grnNoDrop;
		}


		public void setGrnNoDrop(WebElement grnNoDrop) {
			this.grnNoDrop = grnNoDrop;
		}


		public By getGrnDetailsTab() {
			return grnDetailsTab;
		}
	

		public WebElement getEditIcon() {
			return editIcon;
		}


		public void setEditIcons(WebElement editIcon) {
			this.editIcon = editIcon;
		}


		public WebElement getReturnFromStoreField() {
			return returnFromStoreField;
		}


		public void setReturnFromStoreField(WebElement returnFromStoreField) {
			this.returnFromStoreField = returnFromStoreField;
		}


		public WebElement getReturnFromRejField() {
			return returnFromRejField;
		}


		public void setReturnFromRejField(WebElement returnFromRejField) {
			this.returnFromRejField = returnFromRejField;
		}


		public WebElement getUpdateButton() {
			return updateButton;
		}


		public void setUpdateButton(WebElement updateButton) {
			this.updateButton = updateButton;
		}


		public WebElement getFreightLogicDrop() {
			return freightLogicDrop;
		}


		public void setFreightLogicDrop(WebElement freightLogicDrop) {
			this.freightLogicDrop = freightLogicDrop;
		}


		public WebElement getFreightAmt() {
			return freightAmt;
		}


		public void setFreightAmt(WebElement freightAmt) {
			this.freightAmt = freightAmt;
		}


		public WebElement getAapplayButton() {
			return AapplayButton;
		}


		public void setAapplayButton(WebElement aapplayButton) {
			AapplayButton = aapplayButton;
		}


		public WebElement getTotalNetAmt() {
			return totalNetAmt;
		}


		public void setTotalNetAmt(WebElement totalNetAmt) {
			this.totalNetAmt = totalNetAmt;
		}


		public WebElement getSubmitBtn() {
			return submitBtn;
		}


		public void setSubmitBtn(WebElement submitBtn) {
			this.submitBtn = submitBtn;
		}


		public WebElement getResetBtn() {
			return resetBtn;
		}


		public void setResetBtn(WebElement resetBtn) {
			this.resetBtn = resetBtn;
		}


		public WebElement getBackBtn() {
			return backBtn;
		}


		public void setBackBtn(WebElement backBtn) {
			this.backBtn = backBtn;
		}


		public void setPurchaseSubModLink(By purchaseSubModLink) {
			this.purchaseSubModLink = purchaseSubModLink;
		}

		public By getDotSpinner() {
			return dotSpinner;
		}

		public void setDotSpinner(By dotSpinner) {
			this.dotSpinner = dotSpinner;
		}

		public WebElement getVendorDropList() {
			return vendorDropList;
		}

		public void setVendorDropList(WebElement vendorDropList) {
			this.vendorDropList = vendorDropList;
		}

		public WebElement getVendorValue() {
			return vendorValue;
		}

		public void setVendorValue(WebElement vendorValue) {
			this.vendorValue = vendorValue;
		}

		public WebElement getTransporterValue() {
			return transporterValue;
		}

		public void setTransporterValue(WebElement transporterValue) {
			this.transporterValue = transporterValue;
		}

		public WebElement getTransporterModeValue() {
			return transporterModeValue;
		}

		public void setTransporterModeValue(WebElement transporterModeValue) {
			this.transporterModeValue = transporterModeValue;
		}

		public WebElement getLrDateValue() {
			return lrDateValue;
		}

		public void setLrDateValue(WebElement lrDateValue) {
			this.lrDateValue = lrDateValue;
		}

		public WebElement getInvoiceNoValue() {
			return invoiceNoValue;
		}

		public void setInvoiceNoValue(WebElement invoiceNoValue) {
			this.invoiceNoValue = invoiceNoValue;
		}

		public WebElement getInvoiceDateValue() {
			return invoiceDateValue;
		}

		public void setInvoiceDateValue(WebElement invoiceDateValue) {
			this.invoiceDateValue = invoiceDateValue;
		}

		public WebElement getSubmitBtnSucusessMsg() {
			return submitBtnSucusessMsg;
		}

		public void setSubmitBtnSucusessMsg(WebElement submitBtnSucusessMsg) {
			this.submitBtnSucusessMsg = submitBtnSucusessMsg;
		}

		public WebElement getViewIcon() {
			return viewIcon;
		}

		public void setViewIcon(WebElement viewIcon) {
			this.viewIcon = viewIcon;
		}

		public void setEditIcon(WebElement editIcon) {
			this.editIcon = editIcon;
		}

		public WebElement getReturnQtyValue() {
			return returnQtyValue;
		}

		public void setReturnQtyValue(WebElement returnQtyValue) {
			this.returnQtyValue = returnQtyValue;
		}

		public WebElement getGrossAmtQtyLabelDis() {
			return grossAmtQtyLabelDis;
		}

		public void setGrossAmtQtyLabelDis(WebElement grossAmtQtyLabelDis) {
			this.grossAmtQtyLabelDis = grossAmtQtyLabelDis;
		}

		public WebElement getLandedRateQtyLabelDis() {
			return landedRateQtyLabelDis;
		}

		public void setLandedRateQtyLabelDis(WebElement landedRateQtyLabelDis) {
			this.landedRateQtyLabelDis = landedRateQtyLabelDis;
		}

		public WebElement getTotalTaxAmtLabelDis() {
			return totalTaxAmtLabelDis;
		}

		public void setTotalTaxAmtLabelDis(WebElement totalTaxAmtLabelDis) {
			this.totalTaxAmtLabelDis = totalTaxAmtLabelDis;
		}

		public WebElement getTotalTaxRatePerLabelDis() {
			return totalTaxRatePerLabelDis;
		}

		public void setTotalTaxRatePerLabelDis(WebElement totalTaxRatePerLabelDis) {
			this.totalTaxRatePerLabelDis = totalTaxRatePerLabelDis;
		}

		public WebElement getTotalNetAmtLabelDis() {
			return totalNetAmtLabelDis;
		}

		public void setTotalNetAmtLabelDis(WebElement totalNetAmtLabelDis) {
			this.totalNetAmtLabelDis = totalNetAmtLabelDis;
		}

		public WebElement getSgstPerAmtLabelDis() {
			return sgstPerAmtLabelDis;
		}

		public void setSgstPerAmtLabelDis(WebElement sgstPerAmtLabelDis) {
			this.sgstPerAmtLabelDis = sgstPerAmtLabelDis;
		}

		public WebElement getCgstPerAmtLabelDis() {
			return cgstPerAmtLabelDis;
		}

		public void setCgstPerAmtLabelDis(WebElement cgstPerAmtLabelDis) {
			this.cgstPerAmtLabelDis = cgstPerAmtLabelDis;
		}

		public WebElement getSuccMsgField() {
			return succMsgField;
		}

		public WebElement getGrnNoDropOptList() {
			return grnNoDropOptList;
		}

		public By getErrorLoggerOverlay() {
			return errorLoggerOverlay;
		}

		
		
		
		
}
	











