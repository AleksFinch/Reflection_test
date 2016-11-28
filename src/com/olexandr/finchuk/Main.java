package com.olexandr.finchuk;

import com.olexandr.finchuk.creators.ArrayGenerator;
import com.olexandr.finchuk.excel.ExcelWorker;
import com.olexandr.finchuk.reflections.Analyzer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Olexandr Finchuk
 */
public class Main {

    public static void main(String[] args){

          Analyzer analyzer = new Analyzer("src");
          analyzer.analyzeAllWithExcel(1,10000,100);




    }


}
