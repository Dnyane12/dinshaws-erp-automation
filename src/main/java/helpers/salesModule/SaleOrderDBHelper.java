package helpers.salesModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.SaleOrderDBModel;
import utils.DatabaseUtility;

public class SaleOrderDBHelper {
	 public static Map<String, String> getItemOrderQtyBySaleOrderNo() {
	        Map<String, String> itemOrderQtyMap = new LinkedHashMap<>();
	        String query = "SELECT t.sorh_doc_no, "+
	        	      " k.sord_item_name, "+
	        	      " k.sord_qty "+ 
	        	" FROM sl_so_hdr t "+
	        	" JOIN sl_so_dtl k "+ 
	        	     " ON t.sorh_id = k.sord_soid_sorh "+
	        	" WHERE t.sorh_doc_no = ( "+
	        	    " SELECT t2.sorh_doc_no "+
	        	    " FROM sl_so_hdr t2 "+
	        	    " WHERE DATE(t2.sorh_dt) = CURRENT_DATE "+
	        	    " ORDER BY t2.createdon DESC "+
	        	    " LIMIT 1 "+
	        	" ) "; 
	        
	        try {
	            ResultSet rs = DatabaseUtility.executeSelectQuery(query);

	            while (rs.next()) {            	
	            	String latestSONoFromDB= rs.getString("sorh_doc_no");
	            	System.out.println("latestSONoFromDB: "+latestSONoFromDB);
	            	
	                itemOrderQtyMap.put(
	                    rs.getString("sord_item_name").trim(),
	                    rs.getString("sord_qty").trim()
	                );
	            }
	        } catch (Exception e) {
	            //throw new RuntimeException("Failed to fetch Sale Order data", e);
	        	e.printStackTrace();
	        }
	        return itemOrderQtyMap;
	    }
	 
	 
	 	 
		    public SaleOrderDBModel fetchLatestSalesOrder(String partyId) {
		        String query = "SELECT t.sorh_doc_no, t.sorh_status " +
		                       "FROM sl_so_hdr t " +
		                       "WHERE t.sorh_party_id_acnt = ? " +
		                       "AND DATE(t.sorh_dt) = CURRENT_DATE " +
		                       "ORDER BY t.createdon DESC " +
		                       "LIMIT 1";
		        try (ResultSet rs = DatabaseUtility.executeSelectQuery(query, partyId)) {
		            if (!rs.next()) return null;

		            SaleOrderDBModel order = new SaleOrderDBModel();
		            order.setSaleOrderNo(rs.getString("sorh_doc_no"));
		            order.setStatus(rs.getString("sorh_status"));
		            return order;
		        } catch (SQLException e) {
		            throw new RuntimeException("Error fetching sales order from DB", e);
		        }
		    }
		

}
