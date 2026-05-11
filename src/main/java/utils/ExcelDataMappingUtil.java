package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataMappingUtil {

	Logger logger = LogManager.getLogger(ExcelDataMappingUtil.class);

	public Map<String, String> getTestData(String sheetName, String testCaseID) {

		Map<String, String> dataMap = new HashMap<>();

		String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/PartyMasterTestData.xlsx";
		logger.info("Excel file path: " + filePath);
		System.out.println("Excel file path: " + filePath);

		try {
			FileInputStream fis = new FileInputStream(filePath);

			Workbook workbook = new XSSFWorkbook(fis);
			
			Sheet sheet = workbook.getSheet(sheetName);
			logger.info("Excel sheet name: " + sheetName);
			System.out.println("Excel sheet name: " + sheetName);

			Row headerRow = sheet.getRow(0);
			logger.info("headerRow: " + headerRow);
			logger.info("Header row found with " + headerRow.getPhysicalNumberOfCells() + " columns.");
			System.out.println("headerRow:" + headerRow);

			int rowCount = sheet.getPhysicalNumberOfRows();

			for (int i = 1; i < rowCount; i++) {

				Row row = sheet.getRow(i);
				String currentTestCaseID = row.getCell(0).getStringCellValue();

				if (currentTestCaseID.equalsIgnoreCase(testCaseID)) {
					int cellCount = row.getPhysicalNumberOfCells();

					for (int j = 0; j < cellCount; j++) {
						String key = headerRow.getCell(j).getStringCellValue();
						DataFormatter formatter = new DataFormatter();
						String value = formatter.formatCellValue(row.getCell(j));
						dataMap.put(key, value);
					}
					break;
				}
			}

			workbook.close();
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataMap;
	}
}
