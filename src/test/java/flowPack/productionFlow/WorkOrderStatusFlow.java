package flowPack.productionFlow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import flowPack.setUpFlow.LoginFlow;
import pageObjects.production.WorkOrderStatusPage;
import utils.PropertyReader;
import utils.WaitHelper;

public class WorkOrderStatusFlow {
WebDriver driver;
LoginFlow loginFlow;
PropertyReader propReader;
WorkOrderStatusPage workOSPage;
private static Logger logger = LogManager.getLogger(WorkOrderStatusFlow.class);


	public WorkOrderStatusFlow(WebDriver driver) {
        this.driver = driver;
        loginFlow = new LoginFlow(driver);
        workOSPage = new WorkOrderStatusPage(driver);
        propReader = new PropertyReader("productionModule/WorkOrderStatusTestData.properties");
       
	}

	
	public void directlyNavigateToWorkOrderStatus() {
		workOSPage.clickWorkOrderStatusMenu();	
	}
	
	
	public void flowFromLogin() {
		logger.info("===== Starting GRN Flow Execution, Step 1: Performing Login Flow=====");
		loginFlow.loginFlowCheck();
		
		logger.info("clicking inventoryMod  in HomePage");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, workOSPage.getDotSpinner(), 10);
		workOSPage.clickProductionMenu();
	    workOSPage.clickTransactionMenu();	
	}
	
	public String extractFirstWONoBeforeMakingStatusStart() {
        WaitHelper.waitForClickable(driver, workOSPage.getGridFirstRow(), 10);
        WaitHelper.waitForClickable(driver, workOSPage.getFirstWorkOrderCell(), 10);
        
        String firstWorkOrderNo = workOSPage.getFirstWorkOrderCell().getText();
        System.out.println("First Work Order Number,where working on work order to start: " + firstWorkOrderNo);
		return firstWorkOrderNo;
    }
	
	
	public void searchWOByWONo(String workOrderNo) {
		workOSPage.searchWoInGlobalSearchBar(workOrderNo);	
	}
	
	
	//flow method
	public void workOrderStatusFlowToStartWO(){
		workOSPage.clickWorkOrderStatusMenu();	
		logger.info("Clicked on Work Order Status menu to navigate to the WO status page.");
		
		workOSPage.enterWorkOrderFromDate(propReader.getProperty("workOrderFromDate"));
		logger.info("Entered the from date in the from date field to filter the WO list based on the from date.");
		
		String firstWONo= extractFirstWONoBeforeMakingStatusStart();
		logger.info("Extracted the first WO number from the list before clicking on start button to verify whether the WO status is changed to 'Started' or not.");
		
		workOSPage.clickStartBtn();		
		logger.info("Clicked on topest start button of the WO to change the WO status to 'Started'.");
		
		searchWOByWONo(firstWONo);
		logger.info("Searching the same WO in the list after clicking on start button to verify whether the WO status is changed to 'Started' or not.");
		
		logger.info("Searching the same WO in the list after clicking on start button to verify whether the WO status is changed to 'Started' or not.");
		WaitHelper.waitForVisible(driver, workOSPage.getNoDataFoundMsgField(), 10);
		
		if(workOSPage.getNoDataFoundMsgField().isDisplayed()) {
			logger.info("WO status is successfully changed to 'Started' as the WO is not visible in the list after clicking on start button.");			
		}else {
			logger.error("WO status is not changed to 'Started' as the WO is still visible in the list after clicking on start button.");
		}
		
	}
}
