package api.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
    
    private String filePath;
    private FileInputStream fis;
    private FileOutputStream fos;
    private Workbook workbook;
    private Sheet sheet;

    // Constructor to initialize the file path
    public XLUtility(String filePath) {
        this.filePath = filePath;
    }

    // Method to get the total number of rows in a sheet
    public int getRowCount(String sheetName) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowCount;
    }

    // Method to get the total number of columns in a specific row
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fis.close();
        return cellCount;
    }

    // Method to get the cell data (String) from a specific cell
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        String data;
        try {
            data = cell.getStringCellValue();
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fis.close();
        return data;
    }

    // Method to set data into a specific cell
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum); // Create the row if it doesn't exist
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum); // Create the cell if it doesn't exist
        }
        cell.setCellValue(data);
        fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

    // Method to add a new sheet to the workbook
    public void createSheet(String sheetName) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        workbook.createSheet(sheetName);
        fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }

    // Method to check if a sheet exists
    public boolean isSheetExist(String sheetName) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        int index = workbook.getSheetIndex(sheetName);
        workbook.close();
        fis.close();
        return index != -1;
    }
}
