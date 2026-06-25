package Test.flowWiseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Test.salesModuleTests.transTest.MultiSaleOrderTest;
import Test.salesModuleTests.transTest.SalesDispatchTest;
import Test.salesModuleTests.transTest.TaxInvoiceTest;
import Test.setUpTests.SetUp;
import flowPack.salesModuleFlow.transactionFlow.MultiSaleOrderFlow;
import flowPack.salesModuleFlow.transactionFlow.SaleDispatchFlow;
import flowPack.salesModuleFlow.transactionFlow.TaxInvoiceFlow;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;

public class SalesFlowTests extends SetUp{
	/*(E2E Flow Tests (Few, Critical) ,Purpose: 
	Validate complete business flow ,Ensure no blockers in production-like scenarios ,Catch high-impact failures)*/
	
	//This class going to execute only the flow test cases.
	LoginFlow loginFlow;
	HomeFlow homeFlow;
	MultiSaleOrderFlow saleOFlow;
	SaleDispatchFlow saleDisFlow;
	TaxInvoiceFlow taxInvFlow;
	String dispatchNo;
	
	
	@BeforeClass(groups = "sales-flow")
	public void completeFlowSetup(){
	homeFlow= new HomeFlow(driver);
	loginFlow = new LoginFlow(driver);
	saleOFlow = new MultiSaleOrderFlow(driver);
	saleDisFlow = new SaleDispatchFlow(driver);	
	taxInvFlow = new TaxInvoiceFlow(driver);
	}
	
	
	         //Flow to Execute multi sale oredr flow
		    @Test(enabled=true,groups = "sales-flow",priority=0)
		    public void createSaleOrder() { 
		    	saleOFlow.prepareEnvironment();
		    	saleOFlow.createMultiSaleOrder();
		    }

		    
		     //,dependsOnMethods = "createSaleOrder"
		     //Flow to execute Sale Dispatch Flow.
		    @Test(enabled=true,groups = "sales-flow",priority=1 )
		    public void createSaleDispatch() {
		    	try {
		    	saleDisFlow.prapareEnvToDirectlyOpenSDForm();
		         dispatchNo=saleDisFlow.createSaleDispatchEntry(5);
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    }

		    
		    //,dependsOnMethods = "createSaleDispatch"
		    //Flow to execute Tax Invoice Flow.
		    @Test(enabled=true,groups = "sales-flow",priority=2)
		    public void createTaxInvoice() {
		    	System.out.print("dispatchNo in createTaxInvoice() of E2EFlowTest class:"+dispatchNo);
		    	taxInvFlow.prepareEnvToDirectlyOpenSDForm();
		    	taxInvFlow.createTaxInvoice(dispatchNo);	    	
		    }
			
}
	

	
	

