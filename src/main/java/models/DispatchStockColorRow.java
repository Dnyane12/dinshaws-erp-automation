package models;

import java.util.List;

import org.openqa.selenium.WebElement;

public class DispatchStockColorRow {
	private String item;
    private String orderQty;
    private String disQty;
    private String stockQty;
    private String rowColor;
    private String orderQtyColor;
    boolean deleteBtnVisible;

    // constructor
    public DispatchStockColorRow(String item, String orderQty,String disQty,String stockQty,String rowColor,boolean deleteBtnVisible) {
        this.item = item;
        this.orderQty = orderQty;
        this.disQty = disQty;
        this.stockQty = stockQty;
        this.rowColor = rowColor;
        this.deleteBtnVisible=deleteBtnVisible;
    }

    public String getItem() { return item; }
    public String getOrderQty() { return orderQty; }
    public String getDisQty() { return disQty; }
    public String getStockQty() { return stockQty; }
    public String getRowColor() { return rowColor; }
    public boolean isDeleteBtnVisible() { return deleteBtnVisible; }
}

