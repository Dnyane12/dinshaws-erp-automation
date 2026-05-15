package models;

public class GrnTestData {

	
	 // Vendor
    private String VendorDropLabel;
    private String vendorDropOption;

    // PO No
    private String poNoDropLabel;
    private String poNoDropOption;

    // Transporter Mode
    private String transporterModeLabel;
    private String transporterModeOption;

    // LR Details
    private String lrNo;
    private String lrDate;

    // Invoice Details
    private String invoiceDate;
    private String InvoiceNo;

    // Quantity Details
    private String invoiceQty;
    private String receivedQty;
    private String acceptedQty;


    private String remark;
    // =========================
    // GETTERS AND SETTERS
    // =========================

    public String getVendorDropLabel() {
        return VendorDropLabel;
    }

    public void setVendorDropLabel(String vendorDropLabel) {
        VendorDropLabel = vendorDropLabel;
    }

    public String getVendorDropOption() {
        return vendorDropOption;
    }

    public void setVendorDropOption(String vendorDropOption) {
        this.vendorDropOption = vendorDropOption;
    }

    public String getPoNoDropLabel() {
        return poNoDropLabel;
    }

    public void setPoNoDropLabel(String poNoDropLabel) {
        this.poNoDropLabel = poNoDropLabel;
    }

    public String getPoNoDropOption() {
        return poNoDropOption;
    }

    public void setPoNoDropOption(String poNoDropOption) {
        this.poNoDropOption = poNoDropOption;
    }

    public String getTransporterModeLabel() {
        return transporterModeLabel;
    }

    public void setTransporterModeLabel(String transporterModeLabel) {
        this.transporterModeLabel = transporterModeLabel;
    }

    public String getTransporterModeOption() {
        return transporterModeOption;
    }

    public void setTransporterModeOption(String transporterModeOption) {
        this.transporterModeOption = transporterModeOption;
    }

    public String getLrNo() {
        return lrNo;
    }

    public void setLrNo(String lrNo) {
        this.lrNo = lrNo;
    }

    public String getLrDate() {
        return lrDate;
    }

    public void setLrDate(String lrDate) {
        this.lrDate = lrDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getInvoiceQty() {
        return invoiceQty;
    }

    public void setInvoiceQty(String invoiceQty) {
        this.invoiceQty = invoiceQty;
    }

    public String getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(String receivedQty) {
        this.receivedQty = receivedQty;
    }

    public String getAcceptedQty() {
        return acceptedQty;
    }

    public void setAcceptedQty(String acceptedQty) {
        this.acceptedQty = acceptedQty;
    }

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	@Override
    public String toString() {
        return "GrnTestData{" +
                "VendorDropLabel='" + VendorDropLabel + '\'' +
                ", vendorDropOption='" + vendorDropOption + '\'' +
                ", poNoDropLabel='" + poNoDropLabel + '\'' +
                ", poNoDropOption='" + poNoDropOption + '\'' +
                ", transporterModeLabel='" + transporterModeLabel + '\'' +
                ", transporterModeOption='" + transporterModeOption + '\'' +
                ", lrNo='" + lrNo + '\'' +
                ", lrDate='" + lrDate + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", InvoiceNo='" + InvoiceNo + '\'' +
                ", invoiceQty='" + invoiceQty + '\'' +
                ", receivedQty='" + receivedQty + '\'' +
                ", acceptedQty='" + acceptedQty + '\'' +
                '}';
    }
	
	
	
	
	
	
	
	
	
	

    
    
    
	
//    private String runMode;
//    private String testCaseName;
//    private String vendor;
//    private String poNo;
//    private String transporterMode;
//    private String lrNo;
//    private String lrDate;
//    private String invoiceNo;
//    private String invoiceDate;
//    private String invoiceQty;
//    private String receivedQty;
//    private String acceptedQty;
//    private String remark;
//
//    public String getRunMode() {
//        return runMode;
//    }
//
//    public void setRunMode(String runMode) {
//        this.runMode = runMode;
//    }
//
//    public String getTestCaseName() {
//        return testCaseName;
//    }
//
//    public void setTestCaseName(String testCaseName) {
//        this.testCaseName = testCaseName;
//    }
//
//    public String getVendor() {
//        return vendor;
//    }
//
//    public void setVendor(String vendor) {
//        this.vendor = vendor;
//    }
//
//    public String getPoNo() {
//        return poNo;
//    }
//
//    public void setPoNo(String poNo) {
//        this.poNo = poNo;
//    }
//
//    public String getTransporterMode() {
//        return transporterMode;
//    }
//
//    public void setTransporterMode(String transporterMode) {
//        this.transporterMode = transporterMode;
//    }
//
//    public String getLrNo() {
//        return lrNo;
//    }
//
//    public void setLrNo(String lrNo) {
//        this.lrNo = lrNo;
//    }
//
//    public String getLrDate() {
//        return lrDate;
//    }
//
//    public void setLrDate(String lrDate) {
//        this.lrDate = lrDate;
//    }
//
//    public String getInvoiceNo() {
//        return invoiceNo;
//    }
//
//    public void setInvoiceNo(String invoiceNo) {
//        this.invoiceNo = invoiceNo;
//    }
//
//    public String getInvoiceDate() {
//        return invoiceDate;
//    }
//
//    public void setInvoiceDate(String invoiceDate) {
//        this.invoiceDate = invoiceDate;
//    }
//
//    public String getInvoiceQty() {
//        return invoiceQty;
//    }
//
//    public void setInvoiceQty(String invoiceQty) {
//        this.invoiceQty = invoiceQty;
//    }
//
//    public String getReceivedQty() {
//        return receivedQty;
//    }
//
//    public void setReceivedQty(String receivedQty) {
//        this.receivedQty = receivedQty;
//    }
//
//    public String getAcceptedQty() {
//        return acceptedQty;
//    }
//
//    public void setAcceptedQty(String acceptedQty) {
//        this.acceptedQty = acceptedQty;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
}