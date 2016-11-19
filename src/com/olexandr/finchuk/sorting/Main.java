package com.olexandr.finchuk.sorting;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Olexandr Finchuk
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main main = new Main();
        String currPackage = main.getClass().getPackage().getName();
        ArrayList<Class<?>> clazzes = main.getClazzes("src\\" + currPackage);

        for (Class<?> c:
             clazzes) {
            for (Method m:
                    c.getDeclaredMethods()) {
                System.out.println(m.getName());
            }

        }
    }

    public ArrayList<Class<?> > getClazzes(String path){

        File file = new File(  path.replace(".","\\"));
        File[] files = file.getAbsoluteFile().listFiles();
        ArrayList<Class<?>> clazzes = new ArrayList<>();
        for (File f:
             files) {
            if(f.isDirectory()){
                clazzes.addAll(getClazzes(path+"." +f.getName()));
            }
            if(f.isFile()){
                try {
                    clazzes.add(Class.forName(path.replace("src\\","") + "." + f.getName().replace(".java","")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return clazzes;
    }
}
