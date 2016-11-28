package com.olexandr.finchuk.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

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
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(double numb, int rowNumb, int colNumb, String sheetName) {
        XSSFSheet sheet = workbook.getSheet(sheetName) == null ?
                workbook.createSheet(sheetName) :
                workbook.getSheet(sheetName);

        Row row = null;
        Cell cell = null;
        if (sheet.getRow(rowNumb) == null) {
            row = sheet.createRow(rowNumb);
        } else {
            row = sheet.getRow(rowNumb);
        }
        cell = row.createCell(colNumb);
        cell.setCellValue(numb);
        cell.setCellType(CellType.NUMERIC);

    }



    public void createChart(int seriesNumb, int seriesLength, String sheetName, ArrayList<String> seriesNames){

        Sheet sheet = workbook.getSheet(sheetName) == null ?
                workbook.createSheet(sheetName) :
                workbook.getSheet(sheetName);

        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 250);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, seriesLength- 1));
        for(int i=1;i<=seriesNumb;i++){
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(i, i, 0, seriesLength - 1));
            data.addSeries(xs, ys1).setTitle(seriesNames.get(i-1));
        }
        chart.plot(data, bottomAxis, leftAxis);


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
