package flowPack.salesModuleFlow.transactionFlow;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dao.SaleOrderDao;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import model.SaleOrderDBModel;
import pageObjects.sales.transaction.MultiSaleOrderPage;
import pageObjects.sales.transaction.SaleDispatchPage;
import utils.PropertyReader;
import utils.WaitHelper;
import validation.SaleOrderValidation;


public class MultiSaleOrderFlow {
LoginFlow loginFlow;
WebDriver driver;
HomeFlow homeFlow;
MultiSaleOrderPage saleObj;
SaleDispatchPage saledisPage;
PropertyReader propReader;
SaleOrderValidation sOVal;
private SaleOrderDao dao;
private static final Logger logger= LogManager.getLogger(MultiSaleOrderFlow.class);


public MultiSaleOrderFlow(WebDriver driver) {	
	 this.driver=driver;
	 loginFlow= new LoginFlow(driver);	 
	 homeFlow = new HomeFlow(driver);
	 saleObj =new MultiSaleOrderPage(driver);
     saledisPage = new SaleDispatchPage(driver);
     dao = new SaleOrderDao();
	 propReader= new PropertyReader("salesModule/MultiSaleOrderTestData.properties");
}
	


public void prepareEnvironment() {
	try {				 
		loginFlow.loginFlowCheck();		
		homeFlow.clickToSalesModule1();
				
		saleObj.clickMultiSaleLink();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}



	//Method to create multi sale order
	public void createMultiSaleOrder() {
		try {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);	
		
		saleObj.selectRoute(propReader.getProperty("routeDropOpt").trim());
				
		saleObj.selectPartyDropdown(propReader.getProperty("partyDropOpt").trim());	
					
		WaitHelper.waitForClickable(driver, saleObj.getGetOrderButton(), 10);			
		saleObj.clickGetOrderBtn();	
			
		saleObj.enterSv500Input();
		
		//WaitHelper.ForClickable(driver, saleObj.getAhar500Input(), 10);
		//saleObj.enterAhar500Input();
		
		//WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(),10);
		//WaitHelper.waitForClickable(driver, saleObj.getAhar2L(), 10);
		//saleObj.enterAhar2L();
		
		
		WaitHelper.waitForClickable(driver, saleObj.getOrderStatusDropIcon(), 10);
		saleObj.selectStatusOpt(propReader.getProperty("statusOpt"));
		
		WaitHelper.waitForClickable(driver, saleObj.getSubmitButtton(), 10);
		saleObj.clickSubmitButton();
		
		saleObj.getSuccessMsgText();
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public void checkPartyMasActiveFieldStatus() {
		saleObj.clickConfigMstLink();
		saleObj.clickPartyMasterLink(); 
		saleObj.checkActiveStatus();
		saleObj.checkParty();		
	}
	
	
	public void checkDocTypeField() {
		boolean status= saleObj.docTypeDisplayDefaultValue();
		if(status) {
			
			String defaultValueDocType=saleObj.defaultValueDocType();		
			boolean isEnabled=saleObj.docTypeEnabilityCheck();
			
			System.out.println("defaultValueDocType:"+defaultValueDocType);
			System.out.println("isEnabled status of docType fieled:"+isEnabled);					
		}
	}
	
	
	
	// MultiSaleOrderFlow.java
	public List<String> getAllPartyCodes() {
		logger.info("getAllPartyCodes() method in flow class");
	    List<String> comPartyCodesList = new ArrayList<>();

	    for (WebElement el : saleObj.getCompPartyCodeList()) {
	    	comPartyCodesList.add(el.getText().trim());
	    }
	    logger.info("printing comPartyCodesList: ");
	    System.out.println("comPartyCodesList: "+comPartyCodesList);
	    return comPartyCodesList;
	}

	
	
	
	
	public void extractSaleOrderNo() throws InterruptedException {
		WaitHelper.waitForClickable(driver, saleObj.getFirstRowSoReportData(), 10);		
		
		 WebElement scroller = driver.findElement(
		    	    By.xpath("(//igx-nav-drawer[@id='project-menu']//aside[@role='navigation']")
		    	);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 long lastScrollTop = -1;
		 while (true) {
		     long currentScrollTop = (long) js.executeScript(
		         "return arguments[0].scrollTop;", scroller
		     );
		     if (currentScrollTop == lastScrollTop) {
		         break; // reached bottom
		     }
		     lastScrollTop = currentScrollTop;
		     js.executeScript(
		         "arguments[0].scrollTop = arguments[0].scrollTop + 200;", scroller
		     );
		     Thread.sleep(300);
		 }  
		 
		 WaitHelper.waitForClickable(driver, saleObj.getReportLink(), 10);
		 saleObj.getReportLink().click();
		 WaitHelper.waitForClickable(driver, saleObj.getSaleOrderReportLink(), 10);
		 saleObj.getSaleOrderReportLink().click();
		 WaitHelper.waitForVisible(driver, saleObj.getConfPopup(), 10);		 
		 WaitHelper.waitForClickable(driver, saleObj.getOkBtn(), 10);
		 saleObj.getOkBtn().click();
		 WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);
		 WaitHelper.waitForClickable(driver, saleObj.getViewButton(), 10);
		 saleObj.getViewButton().click();		 	
		 }
	
	
	//executing flow to serach the sale order no. in sale dispatch form
	public void openDisFormAndSelectRoute() {
		try {	
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);	
		WaitHelper.waitForClickable(driver, saleObj.getSaleDispatchLink(), 10);
		saleObj.clickSaleDisLink();
		
		WaitHelper.waitForVisible(driver, saleObj.getSaleDisConfPopup(), 10);	
		saleObj.clickConfOkBtn();
		
		//WaitHelper.ForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);						
		logger.info("ing for route to be clickable and then clicking it");
				
		WaitHelper.waitForClickable(driver, saleObj.getRouteSearchbox(),10);	
		saleObj.selectRoute1(propReader.getProperty("routeDropOpt").trim());
		}catch(Exception e) {
			e.printStackTrace();
		}
					
  }
		
		public boolean searchSaleOrderNo(String saleOrderNoToSearch) {
			try{
			logger.info("clicking and searching sale order number in sale dispatch serach button");
			WaitHelper.waitForClickable(driver, saleObj.getSaleDisGlobalSearchBtn(), 10);
			saleObj.searchSONoInSaleDispatch(saleOrderNoToSearch);
					
			WaitHelper.waitForVisible(driver, saleObj.getNoDataFoundMsgField(), 10);
			String noDataFoundMsg = saleObj.GetNoDataFoundMsg();
	         
	        boolean noDataMsgDisStatus=saleObj.getNoDataFoundMsgField().isDisplayed();
	        System.out.print("noDataMsgDisStatus: "+"noDataMsgDisStatus"+", noDataFoundMsg" +noDataFoundMsg+this.getClass().getSimpleName());
			return noDataMsgDisStatus;
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		
			
	
	public void selectRouteAndParty() {
		logger.info("ing for invisibility of dotSpinner");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(), 10);	
		
		logger.info("ing and Selecting route");
		WaitHelper.waitForClickable(driver,saleObj.getRouteSearchbox() ,10);
		saleObj.selectRoutefromRouteDrop(propReader.getProperty("routeDropOpt").trim());
		
		logger.info("ing and Selecting party");
		WaitHelper.waitForClickable(driver, saleObj.getPartyDropIcon(), 10);
		saleObj.selectPartyDropdown(propReader.getProperty("partyDropOpt").trim());
	}
	
	
	
	public void clickGetOrderButtonAnd() {
		logger.info("ing and clicking GetOrderBtn");
		WaitHelper.waitForClickable(driver,saleObj.getGetOrderButton(), 10);
		saleObj.clickGetOrderBtn();

		logger.info("ing for visibility of SaleOrderInputRow");
		WaitHelper.waitForInvisibilityOfElementLocated(driver,saleObj.getDotSpinner(), 10);
		WaitHelper.waitForVisible(driver,saleObj.getSaleOrderInputRow(), 10);
	}
	
	
	
	
	public void selectRouteAndClickParty() {
		WaitHelper.waitForClickable(driver, saleObj.getRouteSearchbox(), 10);
		saleObj.selectRoute(propReader.getProperty("routeDropOpt").trim());				
		WaitHelper.waitForInvisibilityOfElementLocated(driver, saleObj.getDotSpinner(),10);
		WaitHelper.waitForClickable(driver, saleObj.getPartyDropField(), 10).click();
	}
	
	

    public SaleOrderDBModel getLatestSalesOrderForParty(String partyId) {
        SaleOrderDBModel order = dao.fetchLatestSalesOrder(partyId);

        if (order == null) {
            throw new AssertionError("No sales order found for partyId: " + partyId);
        }
        return order;
    }
	
	
	
}














