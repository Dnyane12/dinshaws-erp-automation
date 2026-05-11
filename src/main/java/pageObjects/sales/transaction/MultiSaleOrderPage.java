package pageObjects.sales.transaction;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtilForDropdown;
import utils.DBUtilityForRoutePartyMapping;
import utils.PropertyReader;
import utils.WaitHelper;
import utils.WaitUtilityDuplicate;

public class MultiSaleOrderPage{
	WebDriver driver;
	DBUtilityForRoutePartyMapping dbUtil;
	private static final Logger logger = LogManager.getLogger(MultiSaleOrderPage.class);
	PropertyReader propReader = new PropertyReader("salesModule/MultiSaleOrderTestData.properties");
	
	public MultiSaleOrderPage(WebDriver driver) {
		this.driver=driver;
		dbUtil = new DBUtilityForRoutePartyMapping();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='mb-50']//span[normalize-space(text())='Transaction']")
	private WebElement transactionLink;
	By dotSpinner= By.xpath("/html/body/app-root/div/div/div/div/div");
	@FindBy(xpath="//span[contains(text(),'Multi-Sale Order (New)') and contains(@class,'fs-12')]")
	private WebElement multiSaleOrderLink;
	@FindBy(xpath="//span[contains(@class,'igx-dialog__window-message') and normalize-space(text())='Are you sure you want to go Home, you will potentially lose your changes?']")
	private WebElement confMessg;
	@FindBy(xpath="//div[contains(@class,'igx-dialog__window-title') and normalize-space(text())='Confirmation']")
	private WebElement confPopup;
	@FindBy(xpath="//button[contains(@class,'igx-button') and normalize-space(text())='OK']")
	private WebElement confOkBtn;
	@FindBy(xpath="//label[normalize-space(text())='Route']/following::igx-icon[1]")
	private WebElement routeDropIcon;
	@FindBy(xpath="//igx-combo-item[@id='igx-drop-down-item-91']//span[normalize-space(text())='Product Local']")
	private WebElement routeDropOption;
	@FindBy(xpath="(//label[normalize-space(text())='Party' and contains(@id,'igx-label')]/following::igx-icon[normalize-space(text())='expand_more'])[1]")
	private WebElement partyDropIcon;
	@FindBy(xpath="//button[@id='l_sale_order_multi_create-width-selector' and normalize-space(text())='Get Order']")
	private WebElement getOrderButton;
	@FindBy(xpath="(//input[contains(@class,'grid-input-custom') and  @id='0-4'])[1]")
	private WebElement sv500Input;
	@FindBy(xpath="(//input[contains(@class,'grid-input-custom') and @id='0-8'])[1]")
	private WebElement ahar500Input;	
	By ahar2L= By.xpath("(//input[contains(@class,'grid-input-custom') and @id='0-0'])[1]");
	@FindBy(xpath="//button[contains(@class,'igx-button') and normalize-space(text())='Quick Save']")
	private WebElement quickSaveButton;
	@FindBy(xpath="(//label[normalize-space(text())='Status']/following::igx-icon[contains(@class,'material-icons') and contains(.,'expand_more')])[1]")
	private WebElement orderStatusDropIcon;
	@FindBy(xpath="(//div[contains(@class,'igx-input-group__bundle-main')]//input[contains(@class,'igx-input-group__input')])[8]")
	private WebElement orderStatusDropField;
	@FindBy(xpath="//app-g-btn-all-button[contains(@class,'ml-4px')]//button[normalize-space(text())='Submit' and @id='l_action_save_btn-width-selector']")
	private WebElement submitButtton;	
	By routeSearchBox = By.xpath("(//label[normalize-space(text())='Route']/following::input[contains(@class,'igx-input-group__input')])[1]");
	By routeOptionPopup = By.xpath("//span[normalize-space(text())='7232-umred']");
	@FindBy(xpath="//div[@id='1_title' and normalize-space(text())='Confirmation']")
	private WebElement submitConfPopup;
	@FindBy(xpath="//button[normalize-space(text())='CANCEL']/following-sibling::button[normalize-space(text())='OK' and contains(@class,'igx-button')]")
	private WebElement conOkButton;
	@FindBy(xpath="//div[contains(@class,'mb-50')]//span[normalize-space()='Configure Master']")
	private WebElement configMstLink;
	@FindBy(xpath="//span[contains(normalize-space(.),'Party Master' )]")
	private WebElement partyMasterLink;
	@FindBy(xpath="//div[contains(normalize-space(text()),'Confirmation' ) and @id='1_title']")
	private WebElement confPop;
	@FindBy(xpath="//button[normalize-space(text())='OK']")
	private WebElement okBtn;
	@FindBy(xpath="//input[contains(@id,'l_acnt_account_name')]")
	private WebElement partyNameSerachField;
	@FindBy(xpath="//igx-icon[contains(normalize-space(),'edit')]")
	private WebElement editIcon;
	@FindBy(xpath="//div[contains(@class,'display-cell')]//span[contains(., 'Multi-Sale Order (New)')]")
	private WebElement pageTitle;
	@FindBy(xpath="//label[normalize-space(.)='Doc Type']/following::input[contains(@class,'igx-input-group__input')][1]")
	private WebElement docTypeField;
	By partyDropField = By.xpath("(//label[normalize-space()='Party' and contains(@id,'igx-label')]/following::input[@role='combobox'])[1]");
	@FindBy(xpath="//igx-grid-row[@data-rowindex='0']//input[contains(@class,'grid-input-custom')]")
	private WebElement saleOrderInputRow;
	//.//igx-grid-cell[contains(@class,'igx-grid')]/input[contains(@class,'grid-input-custom')]
	@FindBy(xpath="//igx-grid-row[@data-rowindex='0']//input[contains(@class,'grid-input-custom')]")
    private List<WebElement> saleOrderInputBoxes;
	By saleDispatchLink = By.xpath("//span[contains(normalize-space(.),'Sale Dispatch')]");
	@FindBy(xpath="//div[contains(normalize-space(.),'Confirmation') and @id='1_title']")
	private WebElement saleDisConfPopup;
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement okButton;
	@FindBy(xpath="//igx-grid-row[@data-rowindex='0']")
	private WebElement firstRowSoReportData;
	@FindBy(xpath="//div[@class='mb-50']//span[normalize-space()='Report' and contains(@class,'fs-13')]")
	private WebElement reportLink;
	@FindBy(xpath="//span[contains(normalize-space(.),'Sale Order Printing') and contains(@class,'fs-12')]")
	private WebElement saleOrderReportLink;
	@FindBy(xpath="//button[contains(normalize-space(.),'View') and contains(@id,'l_slih_view-width-selector')]")
    private WebElement viewButton;
     By saleDisGlobalSearchBtn =By.xpath("//div//input[@placeholder='Sale Dispatch Search' and @type='text']");
	@FindBy(xpath="(//div[normalize-space(text())='Regrettably, no data was found. Kindly consider revising your selection' and contains(@class,'mt-2')])[1]")
	private WebElement noDataFoundMsgField;
	@FindBy(xpath="//div[@class='igx-snackbar__message' and normalize-space(text())='Sale OrderSave successfully']")
	private WebElement successMsg;
	By partyOptPop= By.xpath("//span[normalize-space(text())='BSwasth Dairy' and contains(@class,'combo-col')]");
	@FindBy(xpath="//igx-grid-row[@data-rowindex='0']//input[contains(@class,'grid-input-custom')]")
	private List<WebElement> inputBoxesList;
	@FindBy(xpath="(//igx-display-container[contains(@class,'igx-display-container')])[1]")
	private WebElement grid;
	@FindBy(xpath="(//igx-horizontal-virtual-WaitHelper[contains(@class,'igx-vWaitHelper--horizontal')])[1]")
	private WebElement topScroller;
	@FindBy(xpath="//igx-grid-row[@data-rowindex='0']//input[contains(@class,'grid-input-custom')]")
	private WebElement hiddenItemInputs;
	
	By partyCodeList= By.xpath("//div[contains(@class,'igx-overlay__content')]//igx-combo-item[@role='option']//span[contains(@class,'combo-col')][1]");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Action Methods
	public By getPartyDropField() {
		return partyDropField;
	}


	public void clickConfigMstLink() {
		WaitHelper.waitForClickable(driver, configMstLink, 10);
		configMstLink.click();			
	}
	
	
	public void clickTransactionLink() {
		WaitHelper.waitForClickable(driver, transactionLink, 10);
		transactionLink.click();
	}
	
	public void clickMultiSaleLink() {
		WaitHelper.waitForClickable(driver, multiSaleOrderLink, 10);
		multiSaleOrderLink.click();
	}
	
	/*
	 * public void selectRouteDropdown(String routeDropOpt) {
	 * WaitHelper.ForInvisibilityOfElementLocated(driver, dotSpinner, 10);
	 * WaitHelper.ForClickable(driver, routeSeaechbox, 10);
	 * routeSeaechbox.click(); routeSeaechbox.sendKeys(routeDropOpt == null ? "" :
	 * routeDropOpt.trim()); WaitHelper.ForClickable(driver, routeOptionPopup,
	 * 10); routeOptionPopup.click(); System.out.print("routeDropOpt:"
	 * +routeDropOpt); }
	 */
	
	public void ForSOInputRowLoad() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		WaitHelper.waitForVisible(driver, saleOrderInputRow, 10);
	}
	
	
	
	public void selectPartyDropdown(String partyDropOpt) {	
		WebElement partyField= driver.findElement(partyDropField);
		CommonUtilForDropdown.selectFromIgxDropdown(driver, partyField, partyDropOpt);
	}
	
	public void clickGetOrderBtn() {
		 getOrderButton.click();
	}
	
	public void enterSv500Input() {
		 WaitHelper.waitForClickable(driver, sv500Input, 10);
		 sv500Input.click();
		 sv500Input.sendKeys("2");
	}
	
	public void enterAhar500Input() {
		//JavascriptScrollUtility.scrollElementRight(driver, ahar2L, 0);
		 WaitHelper.waitForClickable(driver, ahar500Input, 10);
		 ahar500Input.click();
		 ahar500Input.sendKeys("1");
	}
		
	public void enterAhar2L() {
		 WaitHelper.waitForClickable(driver, ahar2L, 10).click();
		 WaitHelper.waitForPresenceOfElementLocated(driver, ahar2L, 10).sendKeys("1");
	}
	
	public void clickQuickSaveButton() {
		WaitHelper.waitForClickable(driver, quickSaveButton, 10);
		quickSaveButton.click();
	}
	
	public void selectStatusOpt(String statusOpt) {
		orderStatusDropField.clear();
		CommonUtilForDropdown.selectFromIgxDropdown(driver, orderStatusDropField, statusOpt);
	}

	
	public void clickSubmitButton(){
		WaitHelper.waitForClickable(driver, submitButtton, 10);
		submitButtton.click();
		
//		WaitHelper.waitForVisible(driver, submitConfPopup, 10);	
		WaitHelper.waitForClickable(driver, conOkButton, 10);
		conOkButton.click();
	}
	
	
	public void clickPartyMasterLink() {
		WaitHelper.waitForClickable(driver, partyMasterLink, 10);
		partyMasterLink.click();		
	}
	
	
	public boolean succMsgDisplayStatus() {
		WaitHelper.waitForVisible(driver, successMsg, 10);
		boolean succMsgDisplayStatus =successMsg.isDisplayed();
		return succMsgDisplayStatus;	
	}
	
	public String getSuccessMsgText() {
		WaitHelper.waitForVisible(driver, successMsg, 10);
		String successMsgText = successMsg.getText();
		System.out.println("Success message text: " + successMsgText);
		return successMsgText;	
	}
		
	
	//to check that only active parties are found observed in party drop list of multi sale order.
	public boolean checkActiveStatus() {
		WaitHelper.waitForVisible(driver, confPop, 0);
		WaitHelper.waitForClickable(driver, partyMasterLink, 10);
		partyMasterLink.click();
		
		WaitHelper.waitForClickable(driver, okBtn, 10);
		okBtn.click();
	
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 0);
		WaitHelper.waitForClickable(driver, partyNameSerachField, 10);
		partyNameSerachField.click();
        partyNameSerachField.sendKeys("BSwasth Dairy");
        
        
        WaitHelper.waitForClickable(driver, editIcon, 10);
        editIcon.click();		
        WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 0);
		
		WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox' and @name='l_acnt_isactive']"));

		boolean status = checkbox.isSelected();
		System.out.println("Checkbox is active? ,status: " + status);
		return status;
	}
	
	
	
	public void checkParty() {
		WaitHelper.waitForClickable(driver, transactionLink, 10);
		transactionLink.click();	
		
		WaitHelper.waitForClickable(driver, multiSaleOrderLink, 10);
		multiSaleOrderLink.click();	
				
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 0);
		driver.findElement(routeSearchBox).click();
		driver.findElement(routeSearchBox).sendKeys("7232-umred");
		driver.findElement(routeOptionPopup).click();
		
		WaitHelper.waitForClickable(driver, partyDropIcon, 10);
		partyDropIcon.click();	
		
		selectPartyDropdown(propReader.getProperty("partyDropOpt"));	
		
		WebElement partyField = driver.findElement(By.xpath("//label[normalize-space()='Party']/following::input[@role='combobox'][1]"));
		String selectedParty = partyField.getAttribute("value");
		System.out.println("Selected Party: " + selectedParty);

	}
	
	
	
	public String extractPageTitle() {
		WaitHelper.waitForVisible(driver, pageTitle, 10);
		String extractedPageTitle= pageTitle.getText();		
		return extractedPageTitle;
	}
	
	
	public boolean docTypeDisplayDefaultValue() {
		WebElement doctype=driver.findElement((By) docTypeField);
		WaitHelper.waitForVisible(driver, doctype, 10);
		boolean status= doctype.isDisplayed();	
		return status;		
}
	
	
	public String defaultValueDocType() {
		WebElement doctype=driver.findElement((By) docTypeField);
		String defaultValue= doctype.getAttribute("value");
		System.out.println("defaultValue:"+defaultValue);
		return defaultValue;
	}
	
	
	public boolean docTypeEnabilityCheck() {
		WebElement doctype=driver.findElement((By) docTypeField);
		boolean isEnabled= doctype.isEnabled();
		return isEnabled;		
	}
	
	
	public String selectRoute(String routeValue) {
		WebElement routeField =driver.findElement(routeSearchBox);
		CommonUtilForDropdown.selectFromIgxDropdown(driver, routeField, routeValue);
		String uiSelectedRoute =driver.findElement(routeSearchBox).getAttribute("value");
		
//		routeField.click();
//		routeField.sendKeys(routeValue);
//		
//		WaitHelper.waitForClickable(driver, routeOptionPopup,10);
//		driver.findElement(routeOptionPopup).click();
//		
//		String uiSelectedRoute =driver.findElement(routeSearchBox).getAttribute("value");
     	return uiSelectedRoute;				
	}
	
	
	public void selectRoute1(String routeValue) {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		
		WebElement routeInput = WaitHelper.waitForPresenceOfElementLocated(driver, routeSearchBox, 10);

		    //click to avoid Angular re-render issues
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].focus(); arguments[0].click();", routeInput);
			
		 // 🔥 Reset value via JS (NO clear())
		    js.executeScript("arguments[0].value = '';", WaitHelper.waitForPresenceOfElementLocated(driver, routeSearchBox, 10));
		    WaitHelper.waitForPresenceOfElementLocated(driver, routeSearchBox,10).sendKeys(routeValue);
	
		
		//WaitHelper.ForClickable(driver, routeOptionPopup,10).click();	
		WebElement option = WaitHelper.waitForPresenceOfElementLocated(driver, routeOptionPopup, 10);
			js.executeScript("arguments[0].click();", option);
			// Loader after selection
		    //WaitHelper.ForInvisibilityOfElementLocated(driver, dotSpinner, 10);

	}
	
	
	public String selectParty(String partyDropLabel,String partyDropOption){
		WaitHelper.waitForClickable(driver, partyDropField, 10).click();
		WaitHelper.waitForPresenceOfElementLocated(driver, partyDropField, 10).clear();
		WaitHelper.waitForPresenceOfElementLocated(driver, partyDropField, 10).sendKeys(partyDropOption);
	
		WaitHelper.waitForClickable(driver, partyOptPop, 10).click();
		
		logger.info("Party dropdown icon is clickable.");	
       
		String uiSelectedPartyFull = driver.findElement(partyDropField).getAttribute("value");
		//String uiSelectedPartyFull1 = partyDropField.getDomProperty("value");
		
		System.setOut(null);
		String uiSelectedPartyCode = "";

		if (uiSelectedPartyFull != null && !uiSelectedPartyFull.trim().isEmpty()) {
			String[] parts = uiSelectedPartyFull.split("\\s*/\\s*");
			uiSelectedPartyCode = parts[0].trim(); // DA3B0043
			logger.info("uiSelectedPartyCode in multi sale orderpage: {}", uiSelectedPartyCode);
		}
		return uiSelectedPartyCode;      
	}
	
	
	
	public Map<String, String> getDbResult(String uiSelectedRoute,String entityCode,String uiSelectedPartyCode){
		Map<String,String> dbResult = dbUtil.getRoutePartyMapping(uiSelectedRoute,entityCode, uiSelectedPartyCode);
		logger.info("DB route_name: {}, DB party_code: {}", dbResult.get("route_name"), dbResult.get("party_code"));
		return dbResult;
	}
	
	public void selectRoutefromRouteDrop(String routeValue) {
		WebElement routeField =driver.findElement(routeSearchBox);
		routeField.click();
		routeField.clear();
		routeField.sendKeys(routeValue);
		
		WebElement routeOpt= WaitHelper.waitForClickable(driver, routeOptionPopup,10);
		routeOpt.click();
	}
	
	
	public List<WebElement> extractInputBoxesList() {
		WaitHelper.waitForVisibilityOfAllElements(driver, inputBoxesList, 10);
		return inputBoxesList;		
	}
		
	
	public WebElement extractgrid() {
		WaitHelper.waitForVisible(driver, grid, 10);
		return grid;		
	}
	
	public void clickConfOkBtn() {
		WaitHelper.waitForVisible(driver, saleDisConfPopup, 10);
		WaitHelper.waitForClickable(driver, okBtn, 10);
		okBtn.click();
	}
	
	
	public void searchSONoInSaleDispatch(String SaleOrderNoToSearch) {
		WaitHelper.waitForClickable(driver, saleDisGlobalSearchBtn, 10).click();
		WaitHelper.waitForPresenceOfElementLocated(driver, saleDisGlobalSearchBtn, 10).sendKeys(SaleOrderNoToSearch);
	}
	
	
	
	public String GetNoDataFoundMsg() {
		WaitHelper.waitForVisible(driver, noDataFoundMsgField, 10);
		String noDataFoundMsg = noDataFoundMsgField.getText();
		return noDataFoundMsg;
	}
	
	public void clickSaleDisLink() {
		WaitHelper.waitForClickable(driver, saleDispatchLink, 10).click();
		
	}
	
	
	public List<WebElement> getCompPartyCodeList() {
	    return driver.findElements(partyCodeList);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public WebElement getTransactionLink() {
		return transactionLink;
	}

	public WebElement getMultiSaleOrderLink() {
		return multiSaleOrderLink;
	}

	public WebElement getConfMessg() {
		return confMessg;
	}

	public WebElement getConfPopup() {
		return confPopup;
	}

	public WebElement getConfOkBtn() {
		return confOkBtn;
	}

	public WebElement getRouteDropIcon() {
		return routeDropIcon;
	}

	public WebElement getRouteDropOption() {
		return routeDropOption;
	}

	public WebElement getPartyDropIcon() {
		return partyDropIcon;
	}

	public WebElement getGetOrderButton() {
		return getOrderButton;
	}

	public WebElement getSv500Input() {
		return sv500Input;
	}

	public WebElement getAhar500Input() {
		return ahar500Input;
	}

	public By getAhar2L() {
		return ahar2L;
	}

	public WebElement getQuickSaveButton() {
		return quickSaveButton;
	}

	public WebElement getOrderStatusDropIcon() {
		return orderStatusDropIcon;
	}

	public WebElement getOrderStatusDropField() {
		return orderStatusDropField;
	}

	public WebElement getSubmitButtton() {
		return submitButtton;
	}

	public By getDotSpinner() {
		return dotSpinner;
	}

	public By getRouteSearchbox() {
		return routeSearchBox;
	}

	public void setRouteSearchbox(By routeSearchBox) {
		this.routeSearchBox = routeSearchBox;
	}

	public By getRouteOptionPopup() {
		return routeOptionPopup;
	}

	public void setRouteOptionPopup(By routeOptionPopup) {
		this.routeOptionPopup = routeOptionPopup;
	}

	public WebElement getConfigMstLink() {
		return configMstLink;
	}

	public void setConfigMstLink(WebElement configMstLink) {
		this.configMstLink = configMstLink;
	}


	public WebElement getPartyMasterLink() {
		return partyMasterLink;
	}


	public void setPartyMasterLink(WebElement partyMasterLink) {
		this.partyMasterLink = partyMasterLink;
	}


	public WebElement getConOkButton() {
		return conOkButton;
	}


	public void setConOkButton(WebElement conOkButton) {
		this.conOkButton = conOkButton;
	}


	public WebElement getOkBtn() {
		return okBtn;
	}


	public void setOkBtn(WebElement okBtn) {
		this.okBtn = okBtn;
	}


	public void setConfPopup(WebElement confPopup) {
		this.confPopup = confPopup;
	}


	public WebElement getPageTitle() {
		return pageTitle;
	}


	public void setPageTitle(WebElement pageTitle) {
		this.pageTitle = pageTitle;
	}


	public WebElement getSaleOrderInputRow() {
		return saleOrderInputRow;
	}


	public List<WebElement> getSaleOrderInputBoxes() {
		return saleOrderInputBoxes;
	}


	public By getSaleDispatchLink() {
		return saleDispatchLink;
	}


	public WebElement getSaleDisConfPopup() {
		return saleDisConfPopup;
	}


	public WebElement getOkButton() {
		return okButton;
	}


	public WebElement getNoDataFoundMsgField() {
		return noDataFoundMsgField;
	}


	public WebElement getFirstRowSoReportData() {
		return firstRowSoReportData;
	}


	public WebElement getReportLink() {
		return reportLink;
	}


	public WebElement getSaleOrderReportLink() {
		return saleOrderReportLink;
	}


	public WebElement getViewButton() {
		return viewButton;
	}


	public By getSaleDisGlobalSearchBtn() {
		return saleDisGlobalSearchBtn;
	}


	public WebElement getSuccessMsg() {
		return successMsg;
	}


	public By getPartyOptPop() {
		return partyOptPop;
	}


	public void setPartyOptPop(By partyOptPop) {
		this.partyOptPop = partyOptPop;
	}


	public List<WebElement> getInputBoxesList() {
		return inputBoxesList;
	}


	public WebElement getGrid() {
		return grid;
	}


	public WebElement getTopScroller() {
		return topScroller;
	}


	public WebElement getHiddenItemInputs() {
		return hiddenItemInputs;
	}


	public By getPartyCodeList() {
		return partyCodeList;
	}


	
	
	
}
