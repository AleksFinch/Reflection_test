package com.olexandr.finchuk;

import com.olexandr.finchuk.creators.ArrayGenerator;
import com.olexandr.finchuk.reflections.Analyzer;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Olexandr Finchuk
 */
public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer("src",100);
        analyzer.analyzeAllForAll();
        analyzer.setArrSize(10000);
        analyzer.analyzeAllForAll();

    }


}
