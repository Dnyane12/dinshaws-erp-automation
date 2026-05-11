package Test.flowWiseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Test.inventoryModuleTests.transactionTests.GoodReceiptNoteTest;
import Test.setUpTests.SetUp;
import flowPack.inventoryModuleFlow.masterFlow.PurchaseOrderFlow;
import flowPack.inventoryModuleFlow.transactionFlow.GRNPostingFlow;
import flowPack.inventoryModuleFlow.transactionFlow.GRNPrintingFlow;
import flowPack.inventoryModuleFlow.transactionFlow.GoodReceiptNoteFlow;
import flowPack.inventoryModuleFlow.transactionFlow.POPrintingRepFlow;
import flowPack.inventoryModuleFlow.transactionFlow.PRNPrintingRepFlow;
import flowPack.inventoryModuleFlow.transactionFlow.PurchaseReturnFlow;
import flowPack.inventoryModuleFlow.transactionFlow.StockStatusRepFlow;
import flowPack.inventoryModuleFlow.transactionFlow.VoucherPrintingRepFlow;
import flowPack.salesModuleFlow.transactionFlow.MultiSaleOrderFlow;
import flowPack.salesModuleFlow.transactionFlow.SaleDispatchFlow;
import flowPack.salesModuleFlow.transactionFlow.TaxInvoiceFlow;
import flowPack.setUpFlow.HomeFlow;
import flowPack.setUpFlow.LoginFlow;
import pageObjects.inventory.transaction.GRNPostingPage;
import pageObjects.inventory.transaction.GoodReceiptNotePage;
import utils.WaitHelper;

public class InventoryFlowTests extends SetUp{
	    //This class going to execute only the flow test cases.
		PurchaseOrderFlow poFlow;
		GoodReceiptNoteFlow grnFlow;
		GoodReceiptNotePage grnPage;
		GRNPostingFlow grnPostFlow;
		VoucherPrintingRepFlow vpRepFlow;		
		PurchaseReturnFlow prnFlow;
		PRNPrintingRepFlow prnRepFlow;
		GRNPrintingFlow grnRepFlow;
		POPrintingRepFlow poRepFlow;
		StockStatusRepFlow stockStRepFlow;
		
		String poNo;
		String grnNo;
		String voucherNo;

		private static Logger logger =LogManager.getLogger(InventoryFlowTests.class);
			
		@BeforeClass(groups = "Inventory-flow")
		public void compInvFlow(){
			System.out.println("called compInvFlow() ,@Before class method.");
			System.out.println("driver: "+driver);			
			poFlow = new PurchaseOrderFlow(driver);		
			grnFlow= new GoodReceiptNoteFlow(driver);		
			grnPostFlow = new GRNPostingFlow(driver);
			vpRepFlow = new VoucherPrintingRepFlow(driver);
			prnFlow= new PurchaseReturnFlow(driver);
			prnRepFlow= new PRNPrintingRepFlow(driver);
			grnRepFlow= new GRNPrintingFlow(driver);
			poRepFlow= new POPrintingRepFlow(driver);
			grnPage = new GoodReceiptNotePage(driver);
		}
		
		
			    @Test(groups = "Inventory-flow" ,priority=0)
			    public void validatePurchaseOrderCreation() {
			    	try {
			    	poFlow.prapareEnv();
			    	poNo= poFlow.creatingPurchaseOrder();
			    	}catch(Exception e) {
			    		 logger.error("validatePurchaseOrderCreation failed: {}", e.getMessage(), e);  // prints to console
			            throw e;  // rethrow so TestNG still marks it as failed
			    	}
			    }

			    
			    @Test( enabled=true,groups = "Inventory-flow",dependsOnMethods = "validatePurchaseOrderCreation",priority=1)			    
			    public void validateGRNCreation(){
			    	grnFlow.prepareEnvToDirectlyOpenGRNForm();
			    	 grnNo= grnFlow.executeGrnFlow(poNo);
			    }

			    	   			    
			    @Test(enabled=true,groups = "Inventory-flow",dependsOnMethods = "validateGRNCreation",priority=2)
			    public void validateGRNPosting() {
			    	try {
			    	grnPostFlow.openFormDirectlyfromInvToAcc();
			    	System.out.println("grnNo in validateGRNPosting method in  inventory flowtestclass: "+grnNo);
			    	
			        voucherNo =grnPostFlow.executeGRNPostingFlow(grnNo);
			    	} catch (Exception e) {
			            logger.error("validateGRNPosting failed: {}", e.getMessage(), e);  // prints to console
			            throw e;  // rethrow so TestNG still marks it as failed
			        }
			    }
			    
			    
			    @Test(enabled=true,groups = "Inventory-flow", dependsOnMethods = "validateGRNCreation",priority=3)
			    public void createPurchaseRutNote() {
			    	try {
			    	prnFlow.prepEnvToExeFromAcc();
			    	String prnNo=prnFlow.executingFlowOfPRN(grnNo);
			    	} catch (Exception e) {
			            logger.error("createPurchaseRutNote failed: {}", e.getMessage(), e);  // prints to console
			            throw e;  // rethrow so TestNG still marks it as failed
			        }
			    }
			    
			    
			    //,dependsOnMethods = "validateGRNPosting"
			    @Test(enabled=false,groups = "Inventory-flow", dependsOnMethods ="validateGRNPosting")
			    public void validateAccPayableVoucherRep() throws InterruptedException {
			    	try{
			    		vpRepFlow.repFlowExeFromGRNPosting();
			    	    vpRepFlow.executeVPFlow(voucherNo);
			    } catch (Exception e) {
		            logger.error("validateAccPayableVoucherRep failed: {}", e.getMessage(), e);  // prints to console
		            throw e;  // rethrow so TestNG still marks it as failed
		        }
			    }
			    
			    
			   
			    
			    @Test(enabled=false,groups = "Inventory-flow", dependsOnMethods = "createPurchaseRutNote")
			    public void executeFlowOfPRNPrintingRep() {
			    	prnRepFlow.prepEnvdirectlyAfterPrn();
			    	prnRepFlow.executePRNRepPrintFlow();
			    }
			    
			    
			    
			    @Test(enabled=false,groups = "Inventory-flow")
			    public void executeFlowOfGRNPrintingRep() {
			    	grnRepFlow.prepEnvdirectlyAfterPrn();
			    	grnRepFlow.executeGRNRepFlow();
			    }
			    
			    @Test(enabled=false,groups = "Inventory-flow", dependsOnMethods = "validatePurchaseOrderCreation")
			    public void executeFlowOfPurchaseOrderRep() {
			    	poRepFlow.prepEnvdirectlyOpenPoRep();
			    	poRepFlow.executePoRepFlow();
			    }
			    
			    @Test(enabled=false,groups = "Inventory-flow", dependsOnMethods = "validatePurchaseOrderCreation")
			    public void executeFlowOfStockStatusRep() {
			    	stockStRepFlow.prepEnvdirOpenStockStatusRep();
			    	stockStRepFlow.executeStockStaRepFlow();
			    }
			    
			    @Test(enabled=false,groups = "Inventory-flow", dependsOnMethods = "validatePurchaseOrderCreation")
			    public void executeFlowOfLedgerPrinting() {
			    	
			    }
			    
			    
}
