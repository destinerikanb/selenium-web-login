package org.destinenb.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
    private static final String FILE_PATH = "src/main/resources/testdata.xlsx";

    public static String[][] getTestData(String sheetName) throws FileNotFoundException {
        String[][] data = null;
        try(FileInputStream fis = new FileInputStream(new File(FILE_PATH))){
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null){
                throw new RuntimeException("Sheet Null");
            }
            int rowCount = sheet.getPhysicalNumberOfRows();
            if (rowCount < 1){
                throw new RuntimeException();
            }
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new String[rowCount - 1][colCount - 1];

            for (int i=1; i<rowCount; i++){
                Row row = sheet.getRow(i);
                for (int j=1; j<colCount; j++){
                    data[i-1][j-1] = row.getCell(j).getStringCellValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
