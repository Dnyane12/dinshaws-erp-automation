package flowPack.salesModuleFlow.transactionFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import pageObjects.sales.transaction.TaxInvoicePage;
import utils.CommonUtilForDropdown;
import utils.PropertyReader;
import utils.WaitHelper;

public class TaxInvoiceFlow {	
	WebDriver driver;
	TaxInvoicePage taxInvPage;
	LoginFlow loginFlow;
	HomeFlow homeFlow;
	PropertyReader propReader;
	private static Logger logger;
	
	
	public TaxInvoiceFlow(WebDriver driver) {
		this.driver = driver;
		loginFlow = new LoginFlow(driver);
		homeFlow= new HomeFlow(driver);
		taxInvPage = new TaxInvoicePage(driver);
		propReader= new PropertyReader("salesModule/taxInvoiceTestData.properties");
		logger= LogManager.getLogger(TaxInvoiceFlow.class);
	}
	
	
	public void prepareEnvToDirectlyOpenSDForm() {
		try {			
			logger.info("clicking TaxInvoiceLink");
			taxInvPage.clickTaxInvoiceLink();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
			
	public void prepareEnvToRunFlowFromLogin() {
		try {
			loginFlow.loginFlowCheck();
			homeFlow.clickToSalesModule1();
			
			logger.info("clicking TaxInvoiceLink");
			taxInvPage.clickTaxInvoiceLink();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void extractDataRowValues() {
		WaitHelper.waitForVisibilityOfAllElements(driver, taxInvPage.getDataRow(), 10);
	    for(WebElement e :taxInvPage.getDataRow()) {
	    	String value= e.getText().trim();
	    	System.out.println("value:"+value);
	 }
	}
	    
	    
	public void createTaxInvoice(String dispatchNo) {	
		try {
		logger.info(" for invisibility of dotSpinner");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, taxInvPage.getDotSpinner(), 10);
		
		logger.info("ing and selecting sale dispatch number from drop list");
		CommonUtilForDropdown.selectFromIgxDropdown(driver, taxInvPage.getDispatchDrop(), dispatchNo);
		

		logger.info("clicking GenerateTaxInvBtn");
		WaitHelper.waitForClickable(driver, taxInvPage.getGenerateTaxInvBtn(), 20);
		taxInvPage.clickGenerateTaxInvoiceButton();
		
		//logger.info("clicking view button icon");
		//WaitHelper.ForClickable(driver, taxInvPage.getViewButtonIcon(), 10);
		//taxInvPage.clickViewButton();
		
		
		//logger.info("clicking remark field to add remark");
		//WaitHelper.ForClickable(driver, taxInvPage.getRemarkField(), 10);
		//taxInvPage.addRemark();
		
		logger.info("clicking submit button");
		WaitHelper.waitForClickable(driver, taxInvPage.getSubmitBtn(), 10);
		taxInvPage.clickSubmitButton();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
