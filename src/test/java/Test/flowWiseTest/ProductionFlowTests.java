package Test.flowWiseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Test.setUpTests.SetUp;
import flowPack.productionFlow.ProcessTransactionFlow;
import flowPack.productionFlow.WorkOrderFlow;
import flowPack.productionFlow.WorkOrderStatusFlow;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;

public class ProductionFlowTests extends SetUp{
	LoginFlow loginFlow;
	HomeFlow homeFlow;	
	WorkOrderFlow workOFlow;
	WorkOrderStatusFlow workOSFlow;
	ProcessTransactionFlow procTransFlow;
	
	
	@BeforeClass(groups = "Production module-flow",alwaysRun = true)
	public void completeFlowSetup() {
		System.out.println("completeFlowSetup method in production flow test class is called.");
		homeFlow = new HomeFlow(driver);
		loginFlow = new LoginFlow(driver);
		workOFlow = new WorkOrderFlow(driver);
		workOSFlow = new WorkOrderStatusFlow(driver);
		procTransFlow = new ProcessTransactionFlow(driver);
	}
	
	
	//1.Flow to Execute work oredr flow
    @Test(enabled=true,groups = "Production module-flow",priority=0)
    public void validateWorkOrderFlow() { 
    	workOFlow.startFromLogin();
    	workOFlow.createWorkOrder();
    }

       
    //flow to execute work order status flow.
    @Test(enabled=true,groups = "Production module-flow",priority=1, dependsOnMethods = "validateWorkOrderFlow" )
    public void validateWorkOrderStatusFlow() {
    	try {
    		workOSFlow.directlyNavigateToWorkOrderStatus();
    		workOSFlow.workOrderStatusFlowToStartWO();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    
  //flow to execute work order status flow. dependsOnMethods = "validateWorkOrderFlow"
    @Test(enabled=true,groups = "Production module-flow",priority=2, dependsOnMethods = "validateWorkOrderStatusFlow")
    public void validateProcessTransactionFlow() {
    	try {
    		//procTransFlow.directlyNavigateToProcessTransaction();
    		procTransFlow.flowFromLogin();
    		procTransFlow.processTransactionFlow();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
   
}
