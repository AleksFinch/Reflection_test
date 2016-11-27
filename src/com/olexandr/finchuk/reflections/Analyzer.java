package com.olexandr.finchuk.reflections;

import com.olexandr.finchuk.annotations.Filler;
import com.olexandr.finchuk.annotations.FillingClass;
import com.olexandr.finchuk.annotations.SortMethod;
import com.olexandr.finchuk.sortings.Sorting;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Olexandr Finchuk on 27.11.2016.
 */
public class Analyzer {
    String rootPath;
    ArrayList<Class<?>> sortingClazzes;
    ArrayList<Class<?>> fillingClasses;
    int arrSize;

    public Analyzer(String rootPath, int arrSize) throws RuntimeException {
        this.rootPath = rootPath;
        sortingClazzes = new ArrayList<>();
        fillingClasses = new ArrayList<>();
        if (arrSize < 0) {
            throw new RuntimeException("Size of array is smaller than 0");
        }
        this.arrSize = arrSize;
        findClazzes(rootPath);
    }

    private void findClazzes(String path) {
        File file = new File(path.replace(".", "\\"));
        File[] files = file.getAbsoluteFile().listFiles();
        ArrayList<Class<?>> clazzes = new ArrayList<>();
        for (File f :
                files) {
            if (f.isDirectory()) {
                findClazzes(path + "." + f.getName());
            } else {
                try {
                    Class<?> clazz = Class.forName(path.replace("src.", "") + "." + f.getName().replace(".java", ""));
                    if (clazz.getSuperclass()!=null && clazz.getSuperclass().equals(Sorting.class)) {
                        sortingClazzes.add(clazz);
                    } else if (clazz.isAnnotationPresent(FillingClass.class)) {
                        fillingClasses.add(clazz);
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("Class " + f.getName() + " is not found in current package");
                }
            }
        }

    }

    public void analyzeAllForAll() {
        HashMap<String,int[]> allDiffArrays = getAllDiffArrays();
        long begTime;
        long endTime;
        System.out.format("|%-20s|%-15s|%-30s|%20s|\n",
                "Sort type",
                "Size of array",
                "Array generator",
                "Time(nanoseconds)");

        for (Class<?> sorter :
                sortingClazzes) {

            for (Method m :
                    getMethodsWithAnnotations(sorter, SortMethod.class)) {
                Iterator it = allDiffArrays.entrySet().iterator();
                while(it.hasNext()){
                    Object sorterObj = getDefaultInstance(sorter);

                    begTime = System.nanoTime();
                    Map.Entry pair= (Map.Entry) it.next();
                    int[] array = (int[]) pair.getValue();
                    invokeMethod(sorterObj,m,array);
                    endTime = System.nanoTime();
                    System.out.println(String.format("|%88s|","").replace(' ','-'));
                    System.out.format("|%-20s|%-15d|%-30s|%20d|\n",
                            sorter.getSimpleName(),
                            array.length ,
                            pair.getKey() ,
                            (endTime - begTime));

                }


            }


        }
        System.out.println(String.format("|%88s|\n","").replace(' ','-'));

    }


    public void invokeAllWithExcel() {
        //TODO: Excel
    }

    private Object invokeMethod(Object obj, Method method, Object... params) {
        try {
            return method.invoke(obj, params);
        } catch (IllegalAccessException ex) {
            System.out.println("Couldn't access object of class");
        } catch (InvocationTargetException ex) {
            System.out.println("Can't call this method on this instance of class ");
        }
        catch (NullPointerException ex){
            System.out.println("Invalid parameters");
        }

        return null;

    }
    private Object getDefaultInstance(Class<?> clazz){
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Method> getMethodsWithAnnotations(Class<?> clazz, Class<?> annotation) {
        ArrayList<Method> methods = new ArrayList<>();
        for (Method m :
                clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent((Class<? extends Annotation>) annotation))
                methods.add(m);

        }
        return methods;
    }

    private HashMap<String,int[]> getAllDiffArrays() {

        HashMap<String,int[]> allDiffArrays = new HashMap<>();

        for (Class<?> filler :
                fillingClasses) {
            ArrayList<Method> fillMethds = getMethodsWithAnnotations(filler, Filler.class);
            for (Method m :
                    fillMethds) {
                if (m.getReturnType().equals(int[].class) && m.getParameterCount() == 1) {
                    int[] array = (int[]) invokeMethod(null, m, arrSize);
                    allDiffArrays.put(m.getName(),array);
                }


            }
        }
        return allDiffArrays;
    }

    public int getArrSize() {
        return arrSize;
    }

    public void setArrSize(int arrSize) throws RuntimeException {

            if (arrSize < 0) {
                throw new RuntimeException("Size of array is smaller than 0");
            }
            this.arrSize = arrSize;

    }
}
