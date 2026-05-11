package pageObjects.sales.transaction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtilityDuplicate;
import utils.WaitHelper;
public class TaxInvoicePage {
WebDriver driver;


public TaxInvoicePage(WebDriver driver) {
	this.driver=driver;  
	PageFactory.initElements(driver,this);
}


@FindBy(xpath="(//span[contains(normalize-space(text()),'Tax Invoice') and contains(@class,'fs-12')])[1]")
private WebElement taxInvoiceLink;

private By dotSpinner = By.xpath("/html/body/app-root/div/div/div/div/div") ;

@FindBy(xpath="(//label[contains(normalize-space(text()),'Disptach No') and contains(@id,'igx-label')]/following::input[@class='igx-input-group__input'])[1]")
private WebElement dispatchDrop;		

@FindBy(xpath="(//label[contains(@id,'igx-label') and normalize-space(text())='Route']/following::input[@class='igx-input-group__input'])[1]")
private WebElement routeDrop;	

@FindBy(xpath="//button[@id='l_generate_tax_invoice-width-selector']")
private WebElement generateTaxInvBtn;	

@FindBy(xpath="//button[@id='l_action_save_btn-width-selector' and normalize-space(text())='Submit']")
private WebElement submitBtn;	

@FindBy(xpath="//button[@id='l_action_reset_btn-width-selector' and normalize-space(text())='Reset']")
private WebElement resetBtn;

@FindBy(xpath="(//label[contains(@id,'igx-label') and normalize-space(text())='Disptach No']/following::input[@role='combobox'])[1]")
private WebElement dispatchNo;

@FindBy(xpath="//igx-grid-row//div[contains(@class,'igx-grid__td')]")
private List<WebElement> dataRow;

@FindBy(xpath="//igx-icon[contains(@class,'material-icons') and normalize-space(text())='visibility']")
private WebElement viewButtonIcon;

@FindBy(xpath="(//button[normalize-space(text())='Ok' and contains(@class,'igx-button')])[1]")
private WebElement okButton;

@FindBy(xpath="//igx-icon[contains(@class,'material-icons')  and contains(normalize-space(text()),'chat')]")
private WebElement remarkField;

@FindBy(xpath="//label[contains(@id,'igx-label')  and contains(normalize-space(text()),'Remark')]")
private WebElement remarkLabelInsidePopup;

@FindBy(xpath="//textarea[contains(@id,'l_grid_slrd_remarks')  and contains(@class,'ng-untouched')]")
private WebElement remarkTextArea;

@FindBy(xpath="//button[contains(@id,'l_enquiry_entry_btn-width-selector')  and contains(normalize-space(text()),'Save & Close')]")
private WebElement saveCloseButton;

@FindBy(xpath="//div[@class='igx-snackbar__message' and contains(normalize-space(text()),'Tax Invoice Saved successfully')]")
private WebElement succMsg;

@FindBy(xpath="(//label[contains(@id,'igx-label') and normalize-space(text())='Vehicle No.']/following::input[conatins(@id,'l_sldh_vehicle_no_display'])[1]")
private WebElement venhicleNo;

















//Action Methods
public void clickTaxInvoiceLink(){
	WaitHelper.waitForClickable(driver,taxInvoiceLink,10);
	taxInvoiceLink.click();	
}

public void searchAndSelectDispatchNo(String dispatchNoLabel,String dispatchNoOption) {
	WaitUtilityDuplicate.selectFromComboWithoutSearch(driver,dispatchNoLabel, dispatchNoOption);
}

public void clickGenerateTaxInvoiceButton() {
	generateTaxInvBtn.click();
	WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
}


public void clickSubmitButton() {
	submitBtn.click();
}
    
    

public void clickResetButton() {
	WaitHelper.waitForClickable(driver, resetBtn, 10);
	resetBtn.click();
}



public void addRemark() {
	WaitHelper.waitForClickable(driver, remarkField, 10);
	remarkField.click();
	WaitHelper.waitForVisible(driver, remarkLabelInsidePopup, 10);
	WaitHelper.waitForClickable(driver, remarkTextArea, 10);
	remarkTextArea.sendKeys("Adding Remark..");
	saveCloseButton.click();
}


public void clickViewButton() {
	WaitHelper.waitForClickable(driver, viewButtonIcon, 10);
	viewButtonIcon.click();
	WaitHelper.waitForClickable(driver, okButton, 10);
	okButton.click();
	
}


public boolean succMsgDisplayStatus() {
	WaitHelper.waitForVisible(driver, succMsg, 10);	
	return succMsg.isDisplayed();	
}



public String extractSuccMsg() {
	WaitHelper.waitForClickable(driver, succMsg, 10);
	return succMsg.getText();	
}



public String getRouteValue() {
	WaitHelper.waitForVisible(driver,routeDrop, 10);
	return routeDrop.getAttribute("value");
}












//Getters and Setters

public WebElement getTaxInvoiceLink() {
	return taxInvoiceLink;
}

public By getDotSpinner() {
	return dotSpinner;
}

public WebElement getDispatchDrop() {
	return dispatchDrop;
}

public WebElement getRouteDrop() {
	return routeDrop;
}

public WebElement getGenerateTaxInvBtn() {
	return generateTaxInvBtn;
}

public WebElement getSubmitBtn() {
	return submitBtn;
}

public WebElement getResetBtn() {
	return resetBtn;
}

public List<WebElement> getDataRow() {
	return dataRow;
}

public WebElement getRemarkField() {
	return remarkField;
}

public void setRemarkField(WebElement remarkField) {
	this.remarkField = remarkField;
}

public WebElement getDispatchNo() {
	return dispatchNo;
}

public WebElement getViewButtonIcon() {
	return viewButtonIcon;
}

public WebElement getRemarkLabelInsidePopup() {
	return remarkLabelInsidePopup;
}

public WebElement getRemarkTextArea() {
	return remarkTextArea;
}

public WebElement getSaveCloseButton() {
	return saveCloseButton;
}

public WebElement getOkButton() {
	return okButton;
}

public WebElement getSuccMsg() {
	return succMsg;
}

public WebElement getVenhicleNo() {
	return venhicleNo;
}	








}
