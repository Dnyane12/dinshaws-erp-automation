package pageObjects.inventory.transaction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtilForDropdown;
import utils.WaitHelper;

public class MaterialIssuePage {
WebDriver driver;


public MaterialIssuePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}


By dotSpinner= By.xpath("/html/body/app-root/div/div/div/div/div");

@FindBy(xpath = "(//div//span[contains(normalize-space(text()),'Material Issue')])[1]")
private WebElement materialIssueMenu;

@FindBy(xpath="//div//button[contains(normalize-space(text()),'Create New')]")
private WebElement createNewButton;

@FindBy(xpath="(//label[normalize-space(text())='From Location']/following::input[@role='combobox'])[1]")
private WebElement fromLocationDrop;

@FindBy(xpath="(//label[normalize-space(text())='Requisition No.']/following::input[@role='combobox'])[1]")
private WebElement requisitionNoDrop;

@FindBy(xpath="//div//span[contains(normalize-space(text()),'Transaction Id is required')]")
private WebElement errorPopup;

@FindBy(xpath="//div//igx-icon[normalize-space(text())='keyboard_arrow_down']")
private WebElement arraowDownIcon;

@FindBy(xpath="(//label[normalize-space(text())='Item']/following::input[@role='combobox'])[1]")
private WebElement itemDrop;
	
@FindBy(xpath = "(//label[normalize-space(text())='Batch']/following::input[@role='combobox'])[1]")
private WebElement batchDrop;
			
@FindBy(xpath = "//div//igx-combo-item[contains(@id,'igx-drop-down-item')]")
private WebElement batchSelector;

@FindBy(xpath = "(//label[normalize-space(text())='Expiry Date']/following::input[@role='combobox'])[1]")
private WebElement expiryDate;

@FindBy(xpath = "(//label[normalize-space(text())='Adjusted Qty']/following::input[contains(@id,'l_invd_qty')])[1]")
private WebElement adjustedQty;

@FindBy(xpath = "//button[normalize-space(text())='Save' and contains(@id,'l_invd_detail_save-width-selector')]")
private WebElement saveButton;

@FindBy(xpath = "//button[normalize-space(text())='Submit' and contains(@id,'l_action_save_btn-width-selector')]")
private WebElement submitButton;

@FindBy(xpath="//igx-snackbar//div[contains(normalize-space(text()),'Transaction Created successfully')]")
private WebElement successMsg;














//Action Methods
public void clickMatIssueLink() {
	WaitHelper.waitForVisible(driver, materialIssueMenu, 10);
	WaitHelper.waitForClickable(driver, materialIssueMenu, 10);
	materialIssueMenu.click();
}
public void clickCreateNewButton() {
	WaitHelper.waitForClickable(driver, createNewButton, 10);
	createNewButton.click();
}

public void selectFromLocForDrop(String FromLocation) {
	WaitHelper.waitForClickable(driver, fromLocationDrop, 10);
	 CommonUtilForDropdown.selectFromIgxDropdown(driver, fromLocationDrop, FromLocation);
}

public void selectRequiNoFromDrop(String RequisionNo) {
	WaitHelper.waitForClickable(driver, requisitionNoDrop, 10);
	 CommonUtilForDropdown.selectFromIgxDropdown(driver, requisitionNoDrop, RequisionNo);
}

public void selectItemFromDrop(String Item) {
	WaitHelper.waitForClickable(driver, itemDrop, 10);
	 CommonUtilForDropdown.selectFromIgxDropdown(driver, itemDrop, Item);
}
	
public void selectBatchFromPopup() {
	WaitHelper.waitForClickable(driver, batchDrop, 10);
	batchDrop.click();
	WaitHelper.waitForClickable(driver, batchSelector, 10);
	batchSelector.click();
}

public void selectExpiryDate(String expiryDateValue) {
	WaitHelper.waitForClickable(driver, expiryDate, 10);
	expiryDate.click();
	expiryDate.sendKeys(expiryDateValue);
}

public void enterAdjustedQty(String AdjustedQtyValue) {
	WaitHelper.waitForClickable(driver, adjustedQty, 10);
	adjustedQty.click();
	adjustedQty.sendKeys(AdjustedQtyValue);
}

public void clickSaveButton() {
	WaitHelper.waitForClickable(driver, saveButton, 10);
	saveButton.click();
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


public void extractMaterIssueNo() {
	WaitHelper.waitForVisible(driver, successMsg, 10);
	String materialIssueNo= successMsg.getText().trim().split(":")[1].trim();
	System.out.println("Material Requisition Number: "+materialIssueNo);	
}


public void clickArrowDown() {
	WaitHelper.waitForVisible(driver, errorPopup, 10);
	WaitHelper.waitForClickable(driver, arraowDownIcon, 10);
	arraowDownIcon.click();
}







//Getter Methods
public WebElement getSuccessMsg() {
	return successMsg;
}



}
