package pageObjects.sales.transaction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import models.DispatchStockColorRow;
import utils.SimpleDropUtil;
import utils.WaitHelper;
import utils.WaitUtilityDuplicate;

public class SaleDispatchPage {
   WebDriver driver; 
   public String rowColor="";
   private static Logger logger = LogManager.getLogger(SaleDispatchPage.class);
   
   
   public SaleDispatchPage(WebDriver driver) {
	   this.driver= driver;
	   PageFactory.initElements(driver,this);
   }
   
   
   
   
	//locators 
   @FindBy(xpath="//h5[normalize-space(text())='Activities that lead to the selling of goods or services']")
	private WebElement  salesModuleLink;
   
    @FindBy(xpath="(//span[normalize-space(text())='Transaction' and contains(@class,'fs-13')])[1]")
    private WebElement transactionLink;
		   		   	       
    By dotSpinner = By.xpath("/html/body/app-root/div/div/div/div/div");
    
	@FindBy(xpath="(//igx-nav-drawer[@id='project-menu']//span[contains(normalize-space(text()),'Sale Dispatch')])[1]")
	private WebElement saleDispatchLink;
   
   	
	@FindBy(xpath="//div[normalize-space(text())='Confirmation' and contains(@id,'1_title')]")
	private WebElement confirmPopup;
	
	@FindBy(xpath="//button[normalize-space(text())='OK' and contains(@class,'igx-button')]")
	private WebElement okBtn;

	By route =By.xpath("//igx-simple-combo[@id='l_desp_filter_route']//input[@class='igx-input-group__input']");
	/// //(//label[normalize-space(text())='Route']/following::input[@class='igx-input-group__input'])[1]
	
	By routeOptPopup =By.xpath("//div/span[normalize-space()='7232-umred']");
	
    @FindBy(xpath="//label[normalize-space(text())='Location']/parent::div[@class='igx-input-group__notch']/following-sibling::div//input")
	private WebElement location;
	
	@FindBy(xpath="//label[normalize-space()='Location']/following::input[contains(@class,'igx-input-group__input')][1]")
	private WebElement locationDrop;
	
	@FindBy(xpath="(//label[normalize-space(text())='Delivery Date' and contains(@class,'igx-input-group__label')]/following::input[contains(@class,'igx-date-picker__input-date')])[1]")
	private WebElement deliveryDate;
	
	@FindBy(xpath="(//igx-grid-row[.//span or .//div]//igx-checkbox)[last()]")
	private WebElement checkRecd;
	
	
	@FindBy(xpath="//button[normalize-space(text())='Create Sale Dispatch' and @id='l_sale_despatch_create-width-selector']")
	private WebElement createSaleDispatchBtn;
	
	@FindBy(xpath="//div[@id='l_despatch_silected_saleorder-width-selector']//igx-grid[@id='l_despatch_silected_saleorder_igx_grid']//igx-grid-row")
	 List<WebElement> Recordsgrid ;

	@FindBy(xpath="//igx-grid-cell[contains(@id,'l_despatch_silected_saleorder_igx_grid') and @data-visibleindex='2' and @data-rowindex=0]")
	private WebElement gridRow1Item;
	
	@FindBy(xpath="//label[contains(@id,'igx-label') and contains(normalize-space(),'Location')] /following::input[contains(@class,'igx-input-group__input')][1]")
	private WebElement locationDropField;
	
	@FindBy(xpath="//button[contains(@id,'l_action_save_btn-width-selector') and normalize-space(text())='Submit']")
	private WebElement submitButton;
	
	By stockUnavaiErrorMsg = By.xpath("//div[ @class='igx-snackbar__message' and contains(normalize-space(text()),'Batch detail not available for item')]");
	
	By deleteSymbolBy= By.xpath("//igx-icon[@class='material-icons igx-icon' and contains(normalize-space(text()),'delete')]");
	
	@FindBy(xpath="//div[ @id='1_title' and contains(normalize-space(text()),'Confirmation')]")
	private WebElement confPopup;
	
	@FindBy(xpath="//div[contains(normalize-space(text()),'Sale Dispatch created successfully: ID:') and @class='igx-snackbar__message']")
	private WebElement saveSuccMsg;
	
	By okButton= By.xpath("//button[contains(@class,'igx-button') and contains(normalize-space(text()),'OK')]");
	 
	By successMsg= By.xpath("//div[@class='igx-snackbar__message' and contains(normalize-space(text()),'Sale Dispatch created successfully:')]");

	By routeFieldOpt =By.xpath("//span[normalize-space(text())='7232-umred']");
	
	By gridRows =By.xpath("//igx-grid-row[@role='row' and @aria-selected='false']");
	
	private By itemCellBy     = By.xpath(".//igx-grid-cell[ @data-visibleindex='2' and contains(@id,'l_despatch_silected_saleorder_igx_grid')]");
	private By disQtyCellBy    = By.xpath(".//igx-grid-cell[ @data-visibleindex='5' and contains(@id,'l_despatch_silected_saleorder_igx_grid')]");
	private By orderQtyCellBy = By.xpath(".//igx-grid-cell[@data-visibleindex='3'and contains(@class,'igx-grid__td--number')]");
	private By stockQtyCellBy    = By.xpath(".//igx-grid-cell[ @data-visibleindex='4' and contains(@id,'l_despatch_silected_saleorder_igx_grid')]");
	
	
	@FindBy(xpath=".//div[contains(normalize-space(text()),'𝐒𝐂𝐇𝐄𝐌𝐄')]")
	private List<WebElement> rowsWithSchemeLabel;
	
	@FindBy(xpath=".//div[contains(normalize-space(text()),'𝐅𝐑𝐄𝐄')]")
	private List<WebElement> freeItemRow;
	
	@FindBy(xpath="(//igx-display-container//igx-grid-row//igx-grid-cell[contains(@id,'l_despatch_silected_saleorder_igx_grid')])[1]")
	private WebElement saleOrderNoRowField;
	
	
	
	
	
	
	
	// Action Methods
	public List<DispatchStockColorRow> getDispatchGridData() {
		List<DispatchStockColorRow> rowsData = new ArrayList<>();

		for (WebElement row : Recordsgrid) {

			WebElement itemCell = row.findElement(itemCellBy);
			WebElement orderQtyCell = row.findElement(orderQtyCellBy);
			WebElement disQtyCell = row.findElement(disQtyCellBy);
            WebElement stockQtyCell=row.findElement(stockQtyCellBy);
			
			String item = itemCell.getText().trim();
			String orderQty = orderQtyCell.getText().trim();
			String disQty = disQtyCell.getText().trim();
			String stockQty= stockQtyCell.getText().trim();
			String orderQtyColor = orderQtyCell.getCssValue("color");
			rowColor = row.getCssValue("background-color");

			
			System.out.println("disQty: " +disQty +",orderQty: "+ orderQty +",stockQty:"+stockQty+", item: " +item);
			System.out.println("rowColor:  " + rowColor);
			
			boolean deleteBtnVisible = row.findElements(deleteSymbolBy).size() > 0 && row.findElement(deleteSymbolBy).isDisplayed();
			rowsData.add(new DispatchStockColorRow(item, orderQty, disQty,stockQty,rowColor,deleteBtnVisible));
		}
		return rowsData;
	}
	
	

	
	
	
	
	public void clickTransactionLink() {
		WaitHelper.waitForClickable(driver, transactionLink, 10);
		transactionLink.click();
	}
		
	
	public void clickSalesModule() {
		 logger.info("ing for sales module link and clicking");
		 WaitHelper.waitForVisible(driver, salesModuleLink, 10);
		 salesModuleLink.click();
		 logger.info("done clicking salesModuleLink");
		}

	
	public void clickSaleDispatchLink() {
		WaitHelper.waitForClickable(driver, saleDispatchLink, 10);
		saleDispatchLink.click();
	}
	

	public void clickSaleDisLinkAfterSaleDisp() {	
		WaitHelper.waitForClickable(driver, saleDispatchLink, 10);
		saleDispatchLink.click();
		WaitHelper.waitForVisible(driver, confirmPopup, 10);
		WaitHelper.waitForClickable(driver, okBtn, 10);
		okBtn.click();
	}
	
	public void selectLocation(String label ,String opt){
		logger.info("ing for clickability of location Selecting it");
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		
		WaitHelper.waitForClickable(driver, location, 10);
		//UtilityDuplicate.selectFromComboWithoutSearch(driver, , label, opt);
		WaitUtilityDuplicate.selectFromComboWithoutSearch(driver, label, opt);
		
	}
	
	
	public void selectRoute3(String routeOpt) {
	    WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		//WaitHelper.waitForClickable(driver, route, 10);	
		    WebElement element = WaitHelper.waitForClickable(driver, route,10);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);		
		//driver.findElement(route).click();
		driver.findElement(route).sendKeys(routeOpt);
		
		WaitHelper.waitForClickable(driver, routeOptPopup,10);
		driver.findElement(routeOptPopup).click();
	}

	public void selectRoute(String routeOpt) {
		WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
		WaitHelper.waitForRefreshAndClick(driver, route, 10);
		driver.findElement(route).sendKeys(routeOpt);
				
		WaitHelper.waitForClickable(driver, routeOptPopup, 10).click();
		//SimpleDropUtil.selectDropOption(driver,route,routeOptPopup,routeOpt);
	}

	
	public String extractSaleOrderNoFromGrid() {
		WaitHelper.waitForVisible(driver, saleOrderNoRowField, 10);
		String saleOrderNo= saleOrderNoRowField.getText().trim();
		System.out.println("saleOrderNo:"+saleOrderNo);
		return saleOrderNo;
	}




	
	
	
	
	
		
	public void selectRoute1(String routeOpt) {		
		WaitHelper.waitForVisible(driver,route , 10);
	List<WebElement> routeInputs = driver.findElements(route);
		
			for (WebElement el : routeInputs) {
			    if (el.isDisplayed() && el.isEnabled()) {
			    	el.click();
			        el.sendKeys(routeOpt);
			        WaitHelper.waitForClickable(driver, routeOptPopup, 10);
			        //routeOptPopup.click();
			        break;
			    }		
			}			
	}
	
	
	
	
	public List<WebElement> extractGridRows() {
		WaitHelper.waitForVisibilityOfAllElements(driver,Recordsgrid , 10);
		List<WebElement> allRows= Recordsgrid;
		return allRows; 
	}
	
	
	public List<DispatchStockColorRow> extractRowsStockAndColor() {	
		List<WebElement> dispatchRows= extractGridRows();
		List<DispatchStockColorRow> result = new ArrayList<>();
		
		for(WebElement row: dispatchRows) {
			String color= row.getCssValue("background-color");
			List<WebElement> orderQtyList= row.findElements(orderQtyCellBy);
			
			
			
			//String stock= row.findElement(stockAvlValuesForAllRows).getText().trim();
			
			//result.add(new DispatchStockColorRow(stock,color));			
			//System.out.println("result:"+result);			
		}
		return result;		
	}
	
	
	
	//select delivery date on second time of route selection,not needed for dafualt values	
	public void selectDiliveryDate() {
	  logger.info("ing for clickability of delivery date and  Selecting it");
	  WaitHelper.waitForClickable(driver, deliveryDate, 10);	  
	  deliveryDate.click(); 
	  deliveryDate.sendKeys("07/01/2025"); 
	  }
	 
		
	public void selectCheckbox() {
		logger.info("Selecting record to dispatch using checkbox");	  
		 checkRecd.click();		
	}
	
	
	public void clickCreateSaleDispatchBtn() {
		logger.info("clicking Create Sale Dispatch Btn");
		WaitHelper.waitForClickable(driver, createSaleDispatchBtn, 10);	  
		createSaleDispatchBtn.click();	
		
	}


	
	public void getRecordWiseGridColor() {
		WaitHelper.waitForVisibilityOfAllElements(driver, Recordsgrid, 10);
		for(WebElement row:Recordsgrid) {
			String color= row.getCssValue("color");
			System.out.println("color"+color);
		}
	    
	    String expectedRedColor ="rgba(255, 0, 0, 1)";
	    String expectedOrangeColor ="rgba(255, 165, 0, 1)";    
	}
	
	
	
	//method to extract the selected location value
	public String getLocationSelected() {
		try {			
			WaitHelper.waitForInvisibilityOfElementLocated(driver, dotSpinner, 10);
			WaitHelper.waitForClickable(driver,locationDropField, 10);
			String locationSelected= locationDropField.getAttribute("value").trim();
			System.out.println("locationSelected:"+locationSelected);
			return locationSelected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getDeliveryDateSelected() {
		try {
			WaitHelper.waitForVisible(driver, deliveryDate, 10);
			
			String deliveryDateSelected= deliveryDate.getAttribute("value").trim();
			System.out.println("deliveryDateSelected:"+deliveryDateSelected);
			
			return deliveryDateSelected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//method to delete the complete item record ,as stock not available for that item.
	public void deleteRecord() {
		logger.info("deleteRecord() methid is called in sale dispatch page.");
		WaitHelper.waitForClickable(driver, deleteSymbolBy, 10);
		driver.findElement(deleteSymbolBy).click();
	}
	
	//method to click submit button
	public void clickSubmitButton() {
		logger.info("calling clickSubmitButton() method in sale Dispatch.");
		WaitHelper.waitForClickable(driver, submitButton, 10);
		submitButton.click();
	
	}
	
	
	//method to click submit button
		public void clickSubmitButton1() {
			WaitHelper.waitForInvisibilityOfElementLocated(driver, stockUnavaiErrorMsg, 10);
			
			logger.info("calling clickSubmitButton() method in sale Dispatch.");
			WaitHelper.waitForClickable(driver, submitButton, 10);
			submitButton.click();
			
			WaitHelper.waitForClickable(driver, okBtn, 10);
			okBtn.click();			
        }	
	
	public void clickConfOkButton() {
		WaitHelper.waitForVisible(driver, confPopup, 10);
		driver.findElement(okButton).click();		
	}
	
	public String getSuccessMessage() {
		WaitHelper.waitForVisible(driver, successMsg, 10);
		String successMsgDisplayed= driver.findElement(successMsg).getText();
		return successMsgDisplayed;
	}
	
	
	
	public String getStockUnavailableErrorMsg() {
		logger.info("Executing method getStockUnavailableErrorMsg() in sale disapatch page.");
	    WaitHelper.waitForVisible(driver, stockUnavaiErrorMsg, 10);	
	    return  driver.findElement(stockUnavaiErrorMsg).getText();       
	}
	
	
	
	
	public Boolean getStockUnavaiErrorMsgDisplayStatus() {
		logger.info("Executing method getStockUnavailableErrorMsg() in sale disapatch page.");
        //WaitHelper.ForVisible(driver, stockUnavaiErrorMsg, 10);
        
		boolean stockUnvailMsgDisplayStatus= WaitHelper.isElementVisible(driver, stockUnavaiErrorMsg, 10);
        System.out.println("stockUnvailMsgDisplayStatus: "+stockUnvailMsgDisplayStatus);
        
		return stockUnvailMsgDisplayStatus;
	}
	
	
		
	
	  public LinkedHashMap<String,String> returnUiItemOrderQtyMap() { 
	  //Fetching data from sale dispatch UI. 	  
	  LinkedHashMap<String, String> uiItemOrderQtyMap = new LinkedHashMap<>(); 
	  // List<WebElement> rows =driver.findElements(gridRows);
	   
	   WaitHelper.waitForPresenceOfElementLocated(driver, gridRows, 10);
	   List<WebElement> rows = driver.findElements(gridRows)
	           .stream()
	           .filter(WebElement::isDisplayed)
	           .toList();
	   
	   
	  // list of igx-grid-row 
	   for (WebElement row : rows) { 
	   String itemName = row.findElement(itemCellBy) .getText() .trim(); 
	   String orderQty = row.findElement(orderQtyCellBy) .getAttribute("value") .trim();
	   uiItemOrderQtyMap.put(itemName, orderQty); 
	   System.out.println( "itemName: " + itemName + ", orderQty: " + orderQty ); 
	   } 
	    return uiItemOrderQtyMap;
	  
	  }
	 

	/*
	 * @Test private List<String> getRowWiseOrderQty() {
	 * .until(ExpectedConditions.visibilityOfAllElements(allOrderQtyOfRecords));
	 * allOrderQtyOfRecords.get return null; 
	 * }
	 */
	
	
	
	
	
	
	
	  
	  
	  
	
	//getters and setters to access private data members and member functions in other class

	public WebElement getSaleDispatchLink() {
		return saleDispatchLink;
	}



	public WebElement getTransactionLink() {
		return transactionLink;
	}



	public void setTransactionLink(WebElement transactionLink) {
		this.transactionLink = transactionLink;
	}



	public void setSaleDispatchLink(WebElement saleDispatchLink) {
		this.saleDispatchLink = saleDispatchLink;
	}



	public WebElement getConfirmPopup() {
		return confirmPopup;
	}



	public void setConfirmPopup(WebElement confirmPopup) {
		this.confirmPopup = confirmPopup;
	}



	public WebElement getOkBtn() {
		return okBtn;
	}



	public void setOkBtn(WebElement okBtn) {
		this.okBtn = okBtn;
	}

	public By getRoute() {
		return route;
	}


	public void setRoute(By route) {
		this.route = route;
	}


	public WebElement getLocation() {
		return location;
	}



	public void setLocation(WebElement location) {
		this.location = location;
	}



	public WebElement getCheckRecd() {
		return checkRecd;
	}



	public void setCheckRecd(WebElement checkRecd) {
		this.checkRecd = checkRecd;
	}



	public WebElement getCreateSaleDispatchBtn() {
		return createSaleDispatchBtn;
	}



	public void setCreateSaleDispatchBtn(WebElement createSaleDispatchBtn) {
		this.createSaleDispatchBtn = createSaleDispatchBtn;
	}



	public WebElement getDeliveryDate() {
		return deliveryDate;
	}



	public void setDeliveryDate(WebElement deliveryDate) {
		this.deliveryDate = deliveryDate;
	}



	public List<WebElement> getRecordsgrid() {
		return Recordsgrid;
	}



	public void setRecordsgrid(List<WebElement> Recordsgrid) {
		Recordsgrid = Recordsgrid;
	}



	public WebElement getGridRow1Item() {
		return gridRow1Item;
	}



	public void setGridRow1Item(WebElement gridRow1Item) {
		this.gridRow1Item = gridRow1Item;
	}

	public WebElement getSalesModuleLink() {
		return salesModuleLink;
	}


	public void setSalesModuleLink(WebElement salesModuleLink) {
		this.salesModuleLink = salesModuleLink;
	}


	public By getDotSpinner() {
		return dotSpinner;
	}


	public void setDotSpinner(By dotSpinner) {
		this.dotSpinner = dotSpinner;
	}

	public WebElement getLocationDropField() {
		return locationDropField;
	}

	public void setLocationDropField(WebElement locationDropField) {
		this.locationDropField = locationDropField;
	}

	public WebElement getLocationDrop() {
		return locationDrop;
	}
	
	
	public By getRouteOptPopup() {
		return routeOptPopup;
	}

	public void setRouteOptPopup(By routeOptPopup) {
		this.routeOptPopup = routeOptPopup;
	}
	
	//public By getRecordgridColor() {
	//	return Recordsgrid;
	//}

	//public void setRecordgridColor(By recordsgrid) {
	//	Recordsgrid = recordsgrid;
	//}


	public WebElement getSubmitButton() {
		return submitButton;
	}


	public By getStockUnavaiErrorMsg() {
		return stockUnavaiErrorMsg;
	}


	public By getDeleteSymbol() {
		return deleteSymbolBy;
	}


	public WebElement getConfPopup() {
		return confPopup;
	}


	public By getOkButton() {
		return okButton;
	}

	public By getGridRows() {
		return gridRows;
	}


	public By getItemCellBy() {
		return itemCellBy;
	}


	public By getOrderQtyCellBy() {
		return orderQtyCellBy;
	}


	public List<WebElement> getRowsWithSchemeLabel() {
		return rowsWithSchemeLabel;
	}


	public List<WebElement> getFreeItemRow() {
		return freeItemRow;
	}

	public String getRowColor() {
		return rowColor;
	}

	public By getDisQtyCellBy() {
		return disQtyCellBy;
	}

	public By getStockQtyCellBy() {
		return stockQtyCellBy;
	}

	public WebElement getSaveSuccMsg() {
		return saveSuccMsg;
	}

	public By getRouteFieldOpt() {
		return routeFieldOpt;
	}

	
	
	
}
