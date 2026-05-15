package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperationsUtility {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelOperationsUtility(String filePath, String sheetName) {

        try {

            FileInputStream fis = new FileInputStream(filePath);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet(sheetName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    
    
    public String getCellData(int rowNum, String columnName) {

        Map<String, Integer> columnMap = new HashMap<>();

        Row headerRow = sheet.getRow(0);

        for (int i = 0; i < headerRow.getLastCellNum(); i++) {

            columnMap.put(
                    headerRow.getCell(i).getStringCellValue().trim(),
                    i);
        }

        int colNum = columnMap.get(columnName);

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(
                sheet.getRow(rowNum).getCell(colNum));
    }
    
    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}