package pageObjects.production;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitHelper;

public class WorkOrderStatusPage {
WebDriver driver;

public WorkOrderStatusPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}
	
	
	@FindBy(xpath = "(//igx-nav-drawer[@id='project-menu']//span[contains(normalize-space(.),'Work Order Status')])[1]")
	public WebElement workOrderStatusMenu;
	
	
	@FindBy(xpath = "(//div[@id='1_title' and contains(normalize-space(.),'Confirmation')])[1]")
	public WebElement confPopup;
	
	@FindBy(xpath = "//button[@role='button' and contains(normalize-space(.),'OK')]")
	public WebElement confOkBtn;
		
	@FindBy(xpath = "(//button[.//span//span[normalize-space(.)='Start']])[1]")
	public WebElement startBtn;
	
	@FindBy(xpath = "//label[normalize-space(.)='Work Order From'] /ancestor::igx-date-picker //input[contains(@class,'igx-date-picker__input-date')]")
	public WebElement workOrderFromDate;
	
	 By dotSpinner = By.xpath("//div[@class='dot-spinner']");
	
	 @FindBy(xpath = "//igx-card[contains(@id,'igx-card')]//div[@class='justify-start']//h3[contains(normalize-space(text()),'Production')]")
	 WebElement productionMenu;
	
	 By transactionMenu =By.xpath("//div[@class='mb-50']//span[@class='fs-13 menu-l1-name'][normalize-space()='Transaction']");
	
	 @FindBy(xpath = "(//igx-grid-row[@role='row'])[1]")
	 WebElement gridFirstRow;
		 
	 @FindBy(xpath = "((//igx-grid-row[@role='row'])[1]//igx-grid-cell[1])[1]")
	 WebElement firstWorkOrderCell;
	 
	 @FindBy(xpath = "//input[@placeholder='Work Order Status Search']")
	 WebElement globalSearchBar;
	 
	 @FindBy(xpath = "//div[contains(text(),'Regrettably, no such data was found for Work Order. Kindly consider revising your selection.')]")
	 WebElement noDataFoundMsgField;
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	//Actions
	public void clickWorkOrderStatusMenu() {
		WaitHelper.waitForClickable(driver, workOrderStatusMenu, 10);
		workOrderStatusMenu.click();
		WaitHelper.waitForVisible(driver, confPopup, 20);		
		WaitHelper.waitForClickable(driver, confOkBtn, 10);
		confOkBtn.click();
	}
	
	
	public void clickStartBtn() {
		WaitHelper.waitForClickable(driver, startBtn, 20);
		startBtn.click();
	}
	
	public void enterWorkOrderFromDate(String fromDate) {
		WaitHelper.waitForClickable(driver, workOrderFromDate, 10);
		workOrderFromDate.click();
		workOrderFromDate.sendKeys(fromDate);
	}


	public By getDotSpinner() {
		return dotSpinner;
	}
	
	
	 public void clickProductionMenu() {
	        WaitHelper.waitForClickable(driver, productionMenu, 10);
	        productionMenu.click();
	    }
	 
	 public void clickTransactionMenu() {
	        WaitHelper.waitForRefreshAndClick(driver, transactionMenu, 10);
	    }

	 public void searchWoInGlobalSearchBar(String workOrderNo) {
		 WaitHelper.waitForClickable(driver, globalSearchBar, 10);
		 globalSearchBar.clear();
		// globalSearchBar.click();
		 globalSearchBar.sendKeys(workOrderNo);
	 }
	 
		public WebElement getWorkOrderStatusMenu() {
			return workOrderStatusMenu;
		}


		public WebElement getWorkOrderFromDate() {
			return workOrderFromDate;
		}


		public WebElement getGridFirstRow() {
			return gridFirstRow;
		}


		public WebElement getFirstWorkOrderCell() {
			return firstWorkOrderCell;
		}


		public WebElement getGlobalSearchBar() {
			return globalSearchBar;
		}


		public WebElement getNoDataFoundMsgField() {
			return noDataFoundMsgField;
		}
	
	    
	    
}
