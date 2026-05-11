package pageObjects.setup;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import pageObjects.sales.master.SPartyMasterPage;
import utils.WaitHelper;

public class HomePage {
	WebDriver driver;
	private static final Logger logger = LogManager.getLogger(SPartyMasterPage.class);

	public HomePage(WebDriver driver) {
		logger.info("called HomeFlow constructor in HomeFlow");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//igx-card//h3[normalize-space(text())='Inventory']")
	private WebElement inventoryMod;

	@FindBy(xpath = "(//span[contains(@class,'fs-12')]//b)[2]")
	private WebElement loginEntity;

	@FindBy(xpath = "//div[contains(@class,'p-1')]")
	private WebElement entChangeSection;
	
	By dotSpinner =By.xpath("//div[@class='dot-spinner']");

	@FindBy(xpath = "//div[contains(@class,'fs-12 ') and normalize-space(text())='Dairy Plant UAT']")
	private WebElement dairyPlantEnt;

	// locating sales Module
	By salesModuleLink = By.xpath("//igx-card[contains(@id,'igx-card')]//h3[normalize-space(text())='Sales']");
	

	// Locating config Master
	@FindBy(xpath = "(//span[contains(normalize-space(.), 'Configure Master') and contains(@class,'fs-13')])[1]")
	private WebElement configMasterLink;

	By storeLink= By.xpath("(//app-menu-tree//span[normalize-space(text())='Stores'])[1]");
	
	@FindBy(xpath="(//app-menu-tree//span[normalize-space(text())='Transaction' and contains(@class,'fs-13')])[1]")
	private WebElement transactionLink;
	
	@FindBy(xpath="//span[contains(text(),'Multi-Sale Order (New)') and contains(@class,'fs-12')]")
	private WebElement multiSaleOrderLink;
	By transactionLink1 = By.xpath("(//app-menu-tree//span[normalize-space(text())='Transaction' and contains(@class,'fs-13')])[1]");
	
	@FindBy(xpath="//igx-card[contains(@id,'igx-card')]/following::h3[contains(text(),'Accounts')]")
	private WebElement accountModLink;
	
	@FindBy(xpath="//igx-nav-drawer[contains(@id,'project-menu')]//span[contains(normalize-space(text()),'Voucher')]")
	private WebElement voucherLink;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void clickAccountMod() {
		WaitHelper.waitForClickable(driver, accountModLink, 10);
		accountModLink.click();
	}
	
	
	public void clickInventoryModule() {
		logger.info("clicking inventoryMod  in HomePage");
		WaitHelper.waitForClickable(driver, inventoryMod, 10);
		inventoryMod.click();
	}
	
	public void clickConfMstLink() {
		WaitHelper.waitForClickable(driver,configMasterLink, 10);
		configMasterLink.click();
	}

	
	public void clickSalesModule() {		
		WaitHelper.waitForRefreshAndClick1(driver, salesModuleLink, 10);
		logger.info("done clicking salesModuleLink");
	}

	public void clickTransLink() {
		WaitHelper.waitForRefreshAndClick(driver, transactionLink1, 10);
	}
	


	
	public WebElement getInventoryMod() {
		return inventoryMod;
	}

	public WebElement getLoginEntity() {
		return loginEntity;
	}

	public WebElement getEntChangeSection() {
		return entChangeSection;
	}

	public WebElement getDairyPlantEnt() {
		return dairyPlantEnt;
	}

	public By getSalesModuleLink() {
		return salesModuleLink;
	}

	public WebElement getConfigMasterLink() {
		return configMasterLink;
	}

	public By getStoreLink() {
		return storeLink;
	}

	public void setStoreLink(By storeLink) {
		this.storeLink = storeLink;
	}

	public WebElement getTransactionLink() {
		return transactionLink;
	}

	public void setTransactionLink(WebElement transactionLink) {
		this.transactionLink = transactionLink;
	}

	public By getTransactionLink1() {
		return transactionLink1;
	}

	public By getDotSpinner() {
		return dotSpinner;
	}


	public WebElement getAccountModLink() {
		return accountModLink;
	}


	public WebElement getVoucherLink() {
		return voucherLink;
	}

	
	
}
