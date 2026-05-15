package dao;

//import com.mycompany.project.model.SalesOrderDBModel;
//import com.mycompany.project.util.DatabaseUtility;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.SaleOrderDBModel;
import utils.DatabaseUtility;

public class SaleOrderDao {

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



