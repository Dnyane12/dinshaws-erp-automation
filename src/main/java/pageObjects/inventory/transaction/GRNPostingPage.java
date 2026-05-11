package pageObjects.inventory.transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitHelper;

public class GRNPostingPage {
WebDriver driver;
private static Logger logger =LogManager.getLogger(GRNPostingPage.class);

	public GRNPostingPage(WebDriver driver) {
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	
	
	
	By homeIcon =By.xpath("//div[@class='igx-navbar__left']//igx-icon[normalize-space(text())='home' and contains(@class,'material-icons')]");
	
	@FindBy(xpath="//igx-card[contains(@id,'igx-card')]/following::h3[contains(text(),'Accounts')]")
	private WebElement accountsMod;

	By voucherLink=By.xpath("//div[contains(@class,'mb-50')]//span[normalize-space(text())='Voucher']");
	
	By dotSpinner = By.xpath("//div[@class='dot-spinner']");
	
	@FindBy(xpath="//igx-nav-drawer[@id='project-menu']//span[contains(normalize-space(text()),'GRN Posting')]")
	private WebElement GRNPostingLink;
		
	@FindBy(xpath="//input[contains(@class,'p-0') and contains(@placeholder,'GRN Posting Search')]")
	private WebElement GRNPostingSearch;
		
	@FindBy(xpath="(//igx-icon[contains(@class,'material-icons') and contains(normalize-space(text()),'forward')])[1]")
	private WebElement forwardArrowIcon;
	
	@FindBy(xpath="//button[normalize-space(text())='Post GRN' and contains(@id,'l_grnposting_btn-width-selector')]")
	private WebElement postGRNButton;
	
	@FindBy(xpath="//button[normalize-space(text())='Submit' and contains(@id,'l_action_save_btn-width-selector')]")
	private WebElement submitButton;
	
	@FindBy(xpath="//div[contains(normalize-space(text()),'Voucher Created successfully') and @class='igx-snackbar__message']")
	private WebElement succMsg;
	
	
	
	
	
	
	//Action Methods
	
	public void clickHomeIcon(){
		logger.info("Waiting and Clicking Home Icon");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		WaitHelper.waitForClickable(driver, homeIcon, 10);
		WaitHelper.waitForRefreshAndClick(driver, homeIcon, 10);
	}
	
	public void clickAccountsMod(){
		logger.info("Waiting and Clicking accounts Module");
		WaitHelper.waitForClickable(driver, accountsMod, 10);
		accountsMod.click();
	}

	public void clickVoucherLink(){
		//logger.info("Waiting and Clicking voucherLink");	
		//WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);	
		//WaitHelper.waitForRefreshAndClick(driver,voucherLink,10);
		    logger.info("Waiting for dotSpinner invisibility");
		    WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 20);
		    logger.info("dotSpinner invisible, now clicking voucherLink"); // if this logs, spinner wait passed
		    WaitHelper.waitForRefreshAndClick(driver, voucherLink, 20);
		    logger.info("voucherLink clicked successfully");		
	}

	public void clickGRNPostingLink(){
		logger.info("Waiting and Clicking GRNPosting form Link");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});", GRNPostingLink);
		WaitHelper.waitForClickable(driver, GRNPostingLink, 20);
		GRNPostingLink.click();
		
		//WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
	}
	
	
	public void SearchGRNNo(String GRNNo) {
		logger.info("Waiting for invisibility of dotSpinner");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 20);
				
		logger.info("Waiting and Clicking GRNPostingSearch field.");
		WaitHelper.waitForClickable(driver, GRNPostingSearch,10);
		GRNPostingSearch.click();
		GRNPostingSearch.sendKeys(GRNNo);
	}
	
	public void clickForwordArrowIcon() {
		logger.info("Waiting and Clicking forward Arrow Icon field.");
		WaitHelper.waitForClickable(driver, forwardArrowIcon,10);
		forwardArrowIcon.click();		
	}
	
	public void clickPostGRNButton() {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		
		logger.info("Waiting and Clicking forward Arrow Icon field.");
		WaitHelper.waitForClickable(driver, postGRNButton,10);
		postGRNButton.click();		
	}
	
	
	public void clickSubmitButton() {
		logger.info("Waiting and Clicking forward Arrow Icon field.");
		WaitHelper.waitForClickable(driver, submitButton,10);
		submitButton.click();	
	
		
//code for rapid/multiple clicks		
//		logger.info("Waiting for submit button to be clickable.");
//	    WaitHelper.waitForClickable(driver, submitButton, 10);
//	    
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//	    for (int i = 0; i < times; i++) {
//	        js.executeScript("arguments[0].click();", submitButton);
//	        logger.info("Clicked submit button " + i + " times via JavaScript.");
//	    }
//	    logger.info("Clicked submit button " + times + " times via JavaScript.");
	}
	
	
	public String extractSuccMsg() {
		WaitHelper.waitForVisible(driver, succMsg, 10);
		return succMsg.getText();
	}
	
	
	public String extractVoucherNo() {
		String successMessage= extractSuccMsg();
		 String parts[] =successMessage.trim().split("ID:");
		String voucherNo= parts[1];
		System.out.println("voucherNo in grnPostingPage class: "+voucherNo);
		return voucherNo;
	}
	
	
	
	//Getters and Setters
	public By getHomeIcon() {
		return homeIcon;
	}

	public WebElement getAccountsMod() {
		return accountsMod;
	}

	public By getVoucherLink() {
		return voucherLink;
	}

	public By getDotSpinner() {
		return dotSpinner;
	}

	public WebElement getGRNPostingSearch() {
		return GRNPostingSearch;
	}

	public WebElement getForwardArrowIcon() {
		return forwardArrowIcon;
	}

	public WebElement getPostGRNButton() {
		return postGRNButton;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	
}
