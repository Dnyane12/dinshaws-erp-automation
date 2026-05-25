package models;

public class GrnItemData {
	private String itemName;
	private String quantity;
	private String rate;
	private String uom;
	private String itemHsn;
	private String itemLocation;
	
	
	public GrnItemData(String itemName, String quantity, String rate,String itemHsn, String itemLocation, String uom) {
		this.itemName = itemName;
		this.quantity = quantity;
		this.rate = rate;
		this.itemHsn = itemHsn;
		this.itemLocation = itemLocation;
		this.uom = uom;
	}

	public String getItemName() {
		return itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getRate() {
		return rate;
	}

	public String getUom() {
		return uom;
	}
	
	public String getItemLocation() {
		return itemLocation;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}

	public String getItemHsn() {
		return itemHsn;
	}

	public void setItemHsn(String itemHsn) {
		this.itemHsn = itemHsn;
	}
	
	
	@Override
	public String toString() {
	    return "GrnItemData{" +
	            "itemName='" + itemName + '\'' +
	            ", quantity='" + quantity + '\'' +
	            ", rate='" + rate + '\'' +
	            ", itemHsn='" + itemHsn + '\'' +
	            ", itemLocation='" + itemLocation + '\'' +
	            ", uom='" + uom + '\'' +
	            '}';
	}
}
