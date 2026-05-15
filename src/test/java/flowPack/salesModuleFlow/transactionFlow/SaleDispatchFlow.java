package flowPack.salesModuleFlow.transactionFlow;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import dao.SaleOrderDao;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import models.SaleOrderDBModel;
import pageObjects.sales.transaction.SaleDispatchPage;
import utils.PropertyReader;
import utils.WaitHelper;

public class SaleDispatchFlow {
WebDriver driver;
SaleDispatchPage saledisPage;	
PropertyReader propReader;
LoginFlow loginFlow;
HomeFlow homeFlow;
private SaleOrderDao dao;
String dispatchNo;
private static Logger logger = LogManager.getLogger();


public SaleDispatchFlow(WebDriver driver) {
	this.driver=driver;
	saledisPage = new SaleDispatchPage(driver);
	loginFlow = new LoginFlow(driver);	
	homeFlow = new HomeFlow(driver);	
	 dao = new SaleOrderDao();
	propReader= new PropertyReader("salesModule/SaleDispatchTestData.peoperties");
}



public void prapareEnvToDirectlyOpenSDForm() {
	try {
	logger.info("ing for sale dispatch Link to be clickable and then clicking it");	
	saledisPage.clickSaleDisLinkAfterSaleDisp();
	
	logger.info("ing for route to be clickable and then selecting it");					
	saledisPage.selectRoute(propReader.getProperty("routeOpt"));
	
	//WaitHelper.waitForClickable(driver, saledisPage.getRoute(), 10);
	//saledisPage.selectRoute(propReader.getProperty("routeDropOpt").trim());
			
	}catch(Exception e) {
		e.printStackTrace();
	}
}


    //method to execute the repated flow upto sale disaptch record fetch.
	public void prapareEnvStaringFromLogin() {
		logger.info("calling loggingFlowCheck method of loginFlow");
		loginFlow.loginFlowCheck();
		
		saledisPage.clickSalesModule();
	
		saledisPage.clickTransactionLink();	

		logger.info("ing for sale dispatch Link to be clickable and then clicking it");
		saledisPage.clickSaleDispatchLink();
		
		
		String locationSelected= saledisPage.getLocationSelected();	
		System.out.println("locationSelected:"+locationSelected);
		
		logger.info("Selecting location if it is not selected already.");
		if( locationSelected == null || locationSelected.isEmpty()) {			
			//saledisPage.selectLocation(propReader.getProperty("locationLabel"), propReader.getProperty("locationOpt"));
		}
		
			logger.info("ing for route to be clickable and then selecting it");								
			saledisPage.selectRoute(propReader.getProperty("routeOpt"));
						
			String deliveryDateSelected= saledisPage.getDeliveryDateSelected();
			System.out.println("deliveryDateSelected:"+deliveryDateSelected);
					
			
		logger.info("Selecting Delivery date if it is not selected already.");
		if(deliveryDateSelected == null || deliveryDateSelected.isEmpty()) {
			//logger.info("ing for delivery date to be clickable and then clicking it");
			//.until(ExpectedConditions.elementToBeClickable(saledisPage.getDeliveryDate()));
			//saledisPage.selectDiliveryDate();
		}
	 }

	
	public void disFlowToCheckSaleOrderData() {
		WaitHelper.waitForClickable(driver, saledisPage.getCheckRecd(), 10);
		saledisPage.selectCheckbox();
		WaitHelper.waitForClickable(driver, saledisPage.getCreateSaleDispatchBtn(), 10);
		saledisPage.clickCreateSaleDispatchBtn();
	}
	
	
	
	 public SaleOrderDBModel getLatestSalesOrderForParty(String partyId) {
		 SaleOrderDBModel order = dao.fetchLatestSalesOrder(partyId);
	        if (order == null) {
	            throw new AssertionError("No sales order found for partyId: " + partyId);
	        }
	        return order;
	    }
	
	
	public void flowToViewDispatchRecords() {
		WaitHelper.waitForClickable(driver, saledisPage.getCheckRecd(), 10);
		saledisPage.selectCheckbox();
		WaitHelper.waitForClickable(driver, saledisPage.getCreateSaleDispatchBtn(), 10);
		saledisPage.clickCreateSaleDispatchBtn();	
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saledisPage.getDotSpinner(), 10);
	}
	
	

	
     //Method to execute Sale dispatch flow.
     public String createSaleDispatchEntry(int times){
		try {		
		WaitHelper.waitForClickable(driver, saledisPage.getCheckRecd(), 10);
		saledisPage.selectCheckbox();
		
		saledisPage.clickCreateSaleDispatchBtn();	
		
		saledisPage.extractSaleOrderNoFromGrid();
		
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saledisPage.getDotSpinner(), 10);
		saledisPage.clickSubmitButton();

	    if(saledisPage.getStockUnavaiErrorMsgDisplayStatus()) {
	    	String stockerrorMsg=saledisPage.getStockUnavailableErrorMsg();
	    	System.out.println("stockerrorMsg: "+stockerrorMsg);
	    	
	    	String deleteRecord= propReader.getProperty("deleteItemRecord");
	    	System.out.println("deleteRecord: "+deleteRecord);
	    	
	    	if(deleteRecord.equals("Yes")) {
	    		saledisPage.deleteRecord();	
	    		saledisPage.clickSubmitButton1();	    		
	    	}	    		
	     }		
	    saledisPage.clickConfOkButton();
	    dispatchNo= extractDispatchNo();
	    System.out.println("dispatchNo from method createSaleDispatchEntry(): "+dispatchNo);	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dispatchNo;
	}
     
     
	public String extractDispatchNo() {
		WaitHelper.waitForVisible(driver, saledisPage.getSaveSuccMsg(), 10);
 		String succMsg=saledisPage.getSaveSuccMsg().getText();
		String dispatchNo= succMsg.split("ID:")[1].trim();
		System.out.println("dispatchNo from method extractDispatchNo(): "+dispatchNo);
		return dispatchNo;		
	}
          
     
	public String checkAsterickMarkDisplay() {
	  WaitHelper.waitForClickable(driver, saledisPage.getLocationDropField(), 10);	
	  String required= saledisPage.getLocationDropField().getAttribute("required");	
	  System.out.println("required:"+required);
	  return required;	
	}
	
	
	
	
	
	
	
	
	/*
	 * public void extractSaleOrderNo() { logger.
	 * info("Calling getLatestSalesOrderForParty method in Test class,where fetching latest sale order from database."
	 * ); SaleOrderDBModel dbOrder =
	 * getLatestSalesOrderForParty(propReader.getProperty("partyId")); String
	 * saleOrderNo= dbOrder.getSaleOrderNo(); }
	 */
	
	
}
