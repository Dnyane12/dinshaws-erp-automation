package pageObjects.accounts.master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonUtilForDropdown;
import utils.InputUtility;
import utils.WaitHelper;

public class JournalVoucherMultiLinePage {
WebDriver driver;
private static final Logger logger = LogManager.getLogger(JournalVoucherMultiLinePage.class);	


	public JournalVoucherMultiLinePage(WebDriver driver){
	this.driver= driver;
	PageFactory.initElements(driver, this);
    }
	

	By dotSpinner= By.xpath("/html/body/app-root/div/div/div/div/div");
	
	
	@FindBy(xpath="(//label[contains(@id,'igx-label') and normalize-space(text())='Series']/following::input[@role='combobox'])[1]")
	private WebElement seriesDrop;
	
	@FindBy(xpath="//span[normalize-space(text())='JV Multi-Line']")
	private WebElement seriesOptPopup;
	
			
	@FindBy(xpath="//igx-card[contains(@id,'igx-card')]//h3[normalize-space(text())='Accounts']")
	private WebElement accModule;
	
	@FindBy(xpath="//igx-nav-drawer[contains(@id,'project-menu')]//span[normalize-space(text())='Voucher']")
	private WebElement voucherLink;
	
	@FindBy(xpath="(//igx-nav-drawer[contains(@id,'project-menu')]//span[contains(normalize-space(.),'Journal Voucher Multi Line')])[1]")
	private WebElement jvMultiPageLink;
	
	@FindBy(xpath="(//div[contains(@class,'display-table')]//button[contains(normalize-space(.),'Create New')])[1]")
	private WebElement createNewBtn;
				
	@FindBy(xpath="//label[normalize-space(text())='Voucher Amount' and contains(@id,'igx-label')]/following::input[contains(@id,'l_voud_b1_voucher_amount')]")
	private WebElement voucherAmount;
	
	@FindBy(xpath="(//label[contains(@id,'igx-label') and normalize-space(text())='Txn Code']/following::input[@role='combobox'])[1]")
	private WebElement txnCodeDrop;
					
	@FindBy(xpath="(//div[@id='l_voud_b2_account_list-width-selector']//label[contains(@id,'igx-label') and normalize-space(text())='Account']/following::input[@role='combobox'])[1]")
	private WebElement debitToAccountDrop;
	
	@FindBy(xpath="//button[contains(@id,'l_action_save_btn-width-selector') and normalize-space(text())='Submit']")
	private WebElement submitButton;
	
	@FindBy(xpath="(//label[contains(@id,'igx-label') and normalize-space(text())='Account']/following::input[@role='combobox'])[1]")
	private WebElement accountDrop;
	
	@FindBy(xpath="//igx-snackbar//div[contains(normalize-space(text()),'Voucher Created successfully')]")
	private WebElement succMsg;
	
	@FindBy(xpath="(//div/input[contains(@placeholder,'Search')])[1]")
	private WebElement menuSearchBar;
	
	@FindBy(xpath="(//div/span[contains(normalize-space(text()),'Journal Voucher Multi Line')])[1]")
	private WebElement jvMultiLineMenuLink;
	
	@FindBy(xpath="//button[normalize-space(text())='Add']")
	private WebElement addButton;
	
	@FindBy(xpath="//igx-grid-row[@role='row']")
	private WebElement gridRow;
	
	
	
	
	//action Methods 
	
	public void clickAccountModule() {
		WaitHelper.waitForClickable(driver, accModule, 10);
		accModule.click();
	}
	
	public void clickVoucherLink() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		//voucherLink.click();
	
		By voucher = By.xpath("//igx-nav-drawer[contains(@id,'project-menu')]//span[normalize-space(text())='Voucher']");
		WaitHelper.waitForRefreshAndClick(driver, voucher, 10);
	}	
	
	public void clickJvMultiPageLink() {
		//WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner,10);
		WaitHelper.waitForClickable(driver,jvMultiPageLink, 10);
		jvMultiPageLink.click();
	}
	public void clickCreateNewButton() {
		//WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		WaitHelper.waitForClickable(driver,createNewBtn, 10);
		createNewBtn.click();
	}
	
	public void selectAccount(String accCodeDropValue) {
		//WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner,20);
	    CommonUtilForDropdown.selectFromIgxDropdown(driver, accountDrop, accCodeDropValue);
	}
						
	public void enterVoucherAmount(String voucherInputLable,String voucherInputIdPart,String voucherAmount) {
		InputUtility.enterValueByLabel(driver, voucherInputLable, voucherInputIdPart, voucherAmount);
	}
	
	public void enterNarration(String narrationInputLable,String narrationInputIdPart,String narrationValue) {
		try {
		WebElement narration= driver.findElement(By.xpath("(//label[normalize-space(text())='Narration' and contains(@id,'igx-label')]/following::input[contains(@id,'l_voud_b1_naration_multiline')])[1]"));		
		//WaitHelper.waitForClickable(driver,narration,10);
		WaitHelper.scrollIntoView(driver, narration);
		InputUtility.enterValueByLabel(driver, narrationInputLable, narrationInputIdPart, narrationValue);
		
		//WebElement narration= driver.findElement(By.xpath("(//label[normalize-space(text())='Narration' and contains(@id,'igx-label')]/following::input[contains(@id,'l_voud_b1_naration')])[1]"));
		//WaitHelper.waitForClickable(driver,narration,10);
		//boolean status= narration.isEnabled();
		//System.out.println("Narration field enability status: "+status);
		//narration.click();
		//narration.sendKeys(narrationValue);
		//WaitHelper.scrollIntoView(driver, accModule);
		}catch(Exception e) {
			e.printStackTrace();
			 logger.error("Error while entering narration: " + e.getMessage());
			 throw e;
		}
	}
	
	public void enterBillNo(String billNoInputLable,String billNoInputIdPart,String billNoValue) {
		InputUtility.enterValueByLabel(driver, billNoInputLable, billNoInputIdPart, billNoValue);
	}
	
	public void selectTxnCode(String txnCodeValue) {	
		CommonUtilForDropdown.selectFromIgxDropdown(driver, txnCodeDrop, txnCodeValue);
	}
	
	public void selectDebitToAccount(String debitToAccount) {	
		CommonUtilForDropdown.selectFromIgxDropdown(driver, debitToAccountDrop, debitToAccount);
	}
		
	
//	public void clickSubmitButton() {
//		WaitHelper.waitForClickable(driver, submitButton, 20);
//		submitButton.click();
//	}

	
	
	public void clickSubmitButton() {

	    WaitHelper.waitForInvisibilityOfElementLocated(driver, getDotSpinner(), 10);

	    // wait until enabled first
	    WaitHelper.waitUntilEnabled(driver, submitButton, 15);

	    // scroll if needed
	    ((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView(true);", submitButton);

	    WaitHelper.waitForClickable(driver, submitButton, 10);
	    submitButton.click();
	}

	public String getSuccMssage() {
		WaitHelper.waitForVisible(driver,succMsg, 20);
		String actualMsg= succMsg.getText();					
		
		logger.info("Actual Message: " + actualMsg);
		return actualMsg;
	}
	
	public void searchSelectFormName() {
		WaitHelper.waitForClickable(driver, menuSearchBar, 10);
		menuSearchBar.click();
		menuSearchBar.sendKeys("Journal Voucher Multi Line");
		WaitHelper.waitForClickable(driver, jvMultiLineMenuLink, 10);
		jvMultiLineMenuLink.click();
	}
	
	
	public void selectSeries(String seriesDopValue) {
		try {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		WaitHelper.waitForClickable(driver, seriesDrop, 10);
		seriesDrop.click();
		seriesDrop.sendKeys(seriesDopValue);
        WaitHelper.waitForClickable(driver, seriesOptPopup, 10);
        seriesOptPopup.click();
		}catch(Exception e) {
			logger.error("Error while selecting series from dropdown: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickAddButton() {
		WaitHelper.waitForClickable(driver, addButton, 10);
		addButton.click();
	}
	
	public boolean isGridRowAdded() {
		try {
			WaitHelper.waitForVisible(driver, gridRow, 10);
			return gridRow.isDisplayed();
		} catch (Exception e) {
			logger.error("Error while verifying if grid row is added: " + e.getMessage());
			return false;
		}
	}
	
	
	
	
	
	
	
	
	//Getters and Setters
	
	public By getDotSpinner() {
		return dotSpinner;
	}

	public WebElement getAccModule() {
		return accModule;
	}

//	public By getVoucherLink() {
//		return voucherLink;
//	}

	public WebElement getJvMultiPageLink() {
		return jvMultiPageLink;
	}

	public WebElement getCreateNewBtn() {
		return createNewBtn;
	}

	public WebElement getVoucherAmount() {
		return voucherAmount;
	}

	public WebElement getTxnCodeDrop() {
		return txnCodeDrop;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getDebitToAccountDrop() {
		return debitToAccountDrop;
	}

	public WebElement getMenuSearchBar() {
		return menuSearchBar;
	}
	
	
}
