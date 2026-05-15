package dataProviders;

import java.util.ArrayList;
import java.util.List;

import models.GrnTestData;
import utils.ExcelOperationsUtility;

public class GrnDataProvider {

	public static List<GrnTestData> getGrnTestData() {

		List<GrnTestData> dataList = new ArrayList<>();

		ExcelOperationsUtility excel = new ExcelOperationsUtility("src/test/resources/testData/GRNTestData.xlsx","GRNData");

		int rows = excel.getRowCount();

		for (int i = 1; i <= rows; i++) {

			String runMode = excel.getCellData(i, "RunMode");

			if ("Y".equalsIgnoreCase(runMode.trim())) {

				GrnTestData data = new GrnTestData();

				data.setVendorDropLabel(excel.getCellData(i, "VendorDropLabel"));

				data.setVendorDropOption(excel.getCellData(i, "VendorDropOption"));

				data.setPoNoDropLabel(excel.getCellData(i, "PoNoDropLabel"));

				data.setPoNoDropOption(excel.getCellData(i, "PoNoDropOption"));

				data.setTransporterModeLabel(excel.getCellData(i, "TransporterModeLabel"));

				data.setTransporterModeOption(excel.getCellData(i, "TransporterModeOption"));

				data.setLrNo(excel.getCellData(i, "LrNo"));

				data.setLrDate(excel.getCellData(i, "LrDate"));

				data.setInvoiceNo(excel.getCellData(i, "InvoiceNo"));

				data.setInvoiceDate(excel.getCellData(i, "InvoiceDate"));

				data.setInvoiceQty(excel.getCellData(i, "InvoiceQty"));

				data.setReceivedQty(excel.getCellData(i, "ReceivedQty"));

				data.setAcceptedQty(excel.getCellData(i, "AcceptedQty"));

				data.setRemark(excel.getCellData(i, "Remark"));

				dataList.add(data);
			}
		}

		excel.closeWorkbook();
		return dataList;
	}
}