package pageObjects.inventory.transaction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtilForDropdown;
import utils.WaitHelper;

public class MaterialRequisitionPage {
WebDriver driver;

public MaterialRequisitionPage(WebDriver driver){
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

By dotSpinner= By.xpath("/html/body/app-root/div/div/div/div/div");

@FindBy(xpath="//div//span[contains(normalize-space(text()),'Material Requisition')]")
private WebElement matReqLink;

@FindBy(xpath="//div//button[contains(normalize-space(text()),'Create New')]")
private WebElement createNewButton;

@FindBy(xpath="(//label[normalize-space(text())='Requisition For']/following::input[@role='combobox'])[1]")
private WebElement reqForDrop;

@FindBy(xpath="(//label[normalize-space(text())='Requested To']/following::input[@role='combobox'])[1]")
private WebElement reqToDrop;

@FindBy(xpath="(//label[normalize-space(text())='Remark']/following::input[contains(@id,'l_imrh_req_remarks')])[1]")
private WebElement remark;

@FindBy(xpath="(//label[normalize-space(text())='Item']/following::input[@role='combobox'])[1]")
private WebElement itemDrop;

@FindBy(xpath="(//label[normalize-space(text())='Quantity']/following::input[contains(@id,'l_imrd_qty_rec')])[1]")
private WebElement quantity;

@FindBy(xpath="(//label[normalize-space(text())='Purpose']/following::input[@role='combobox'])[1]")
private WebElement purposeDrop;

@FindBy(xpath="(//label[normalize-space(text())='Cost Center' and contains(@id,'igx-label')]/following::input[@role='combobox'])[5]")
private WebElement costCenterDrop;

@FindBy(xpath="//button[normalize-space(text())='Add' and contains(@id,'l_imrd_save_btn-width-selector')]")
private WebElement addButton;

@FindBy(xpath="//button[normalize-space(text())='Submit' and contains(@id,'l_action_save_btn-width-selector')]")
private WebElement submitButton;

@FindBy(xpath="//igx-snackbar//div[contains(normalize-space(text()),'Material Requisition Created successfully')]")
private WebElement successMsg;















//Action Methods
public void clickMatReqLink() {
	WaitHelper.waitForVisible(driver, matReqLink, 10);
	WaitHelper.waitForClickable(driver, matReqLink, 10);
	matReqLink.click();
}
public void clickCreateNewButton() {
	WaitHelper.waitForClickable(driver, createNewButton, 10);
	createNewButton.click();
}

public void selectRequiForDrop(String reqForDropValue) {
	WaitHelper.waitForClickable(driver, reqForDrop, 10);
	 CommonUtilForDropdown.selectFromIgxDropdown(driver, reqForDrop, reqForDropValue);
}

public void selectRequestToDrop(String reqToDropValue) {
	WaitHelper.waitForClickable(driver, reqToDrop, 10);
	 CommonUtilForDropdown.selectFromIgxDropdown(driver, reqToDrop, reqToDropValue);
}

public void enterRemark(String remarkValue) {
	WaitHelper.waitForClickable(driver, remark, 10);
	remark.click();
	remark.sendKeys(remarkValue);
}

public void selectItemDrop(String itemDropValue) {
	WaitHelper.waitForClickable(driver, itemDrop, 10);
	 CommonUtilForDropdown.selectFromIgxDropdown(driver, itemDrop, itemDropValue);
}


public void enterQuantity(String quantityValue) {
	WaitHelper.waitForClickable(driver, quantity, 10);
	quantity.click();
	quantity.sendKeys(quantityValue);
}

public void selectPurposeDrop(String purposeDropValue) {
	WaitHelper.waitForClickable(driver, purposeDrop, 10);
	CommonUtilForDropdown.selectFromIgxDropdown(driver, purposeDrop, purposeDropValue);
}

public void selectCostCenterDrop(String costcenterDropValue) {
	WaitHelper.waitForClickable(driver, costCenterDrop, 10);
	CommonUtilForDropdown.selectFromIgxDropdown(driver, costCenterDrop, costcenterDropValue);
}

public void clickAddButton() {
	WaitHelper.waitForClickable(driver, addButton, 10);
	addButton.click();
}


public void clickSubmitButton() {
	WaitHelper.waitForClickable(driver, submitButton, 10);
	submitButton.click();
}


public String extractSuccMsg() {
	WaitHelper.waitForVisible(driver, successMsg, 10);
	String succMsg= successMsg.getText().trim().split("ID:")[0].trim();
	System.out.println("Success message shown: "+succMsg);
	return succMsg;	
}


public void extractMaterRequiNo() {
	WaitHelper.waitForVisible(driver, successMsg, 10);
	String materialReqNo= successMsg.getText().trim().split(":")[1].trim();
	System.out.println("Material Requisition Number: "+materialReqNo);
	
}




//Getters and Setters
public WebElement getSuccessMsg() {
	return successMsg;
}

}
