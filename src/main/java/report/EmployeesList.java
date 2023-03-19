package report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeesList {
    static String path = "src/main/FilesRepository/test.xlsx";
    static int sheetNumber = 0;

    public static ArrayList getList() {
        ArrayList<HashMap> allEmployes = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                HashMap<String, String> oneEmpolye = new HashMap<String, String>();
                oneEmpolye.put("surname", row.getCell(1).getStringCellValue());
                oneEmpolye.put("name", row.getCell(0).getStringCellValue());
                oneEmpolye.put("id", String.valueOf(i));

                allEmployes.add(oneEmpolye);
            }

            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return allEmployes;
    }

    public static String getNameSurnameById(int i) {
        StringBuilder result = new StringBuilder();
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            Row row = sheet.getRow(i);

            result.append(row.getCell(0).getStringCellValue());
            result.append(" " + row.getCell(1).getStringCellValue());
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

    public static String getAfiliacionById(int i) {
        StringBuilder result = new StringBuilder();
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            Row row = sheet.getRow(i);

            result.append(row.getCell(2).getStringCellValue());
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

    public static String getNieById(int i) {
        StringBuilder result = new StringBuilder();
        try {
            FileInputStream file = new FileInputStream(new File(path));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            Row row = sheet.getRow(i);

            result.append(row.getCell(3).getStringCellValue());
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

}

