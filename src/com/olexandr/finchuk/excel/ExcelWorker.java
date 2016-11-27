package com.olexandr.finchuk.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by Olexandr Finchuk on 27.11.2016.
 */
public class ExcelWorker {
    XSSFWorkbook workbook;
    String filename;

    public ExcelWorker(String fileName) {
        this.filename = fileName;
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            try (FileOutputStream out = new FileOutputStream(fileName)) {
                XSSFWorkbook w = new XSSFWorkbook();
                w.write(out);
                file = new FileInputStream(fileName);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        try {
            workbook = new XSSFWorkbook(file);
            if(workbook.getNumberOfSheets()==0)    {
                workbook.createSheet("First Sheet");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String str, int rowNumb,int colNumb){
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = null;
        Cell cell = null;
        if (sheet.getRow(rowNumb)== null) {
            row = sheet.createRow(rowNumb);
        }
        else {
            row = sheet.getRow(rowNumb);
        }
        cell = row.createCell(colNumb);
        cell.setCellValue(str);

    }
    public void commit() {
        try (FileOutputStream out = new FileOutputStream(filename)) {
            workbook.write(out);
            System.out.println("Excel written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
