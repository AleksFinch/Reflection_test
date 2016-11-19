/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olexandr.finchuk.sorting;

/**
 *
 * @author Olexandr Finchuk
 */
import java.lang.reflect.*;

public class PedashLab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    int [] arr = {10, 9, 8, 7,6,5,4,3,2,1};
    Class quickSort;
    Constructor constructor;
    Method methodSort;
    Method methodShow;

    Object myObj;
        try{
   quickSort = Class.forName("pedash.lab1.QuickSort");
    }
catch(ClassNotFoundException ex){
    System.out.println("This class is not found ");
    quickSort = null;
}
        try{
         constructor = quickSort.getConstructor(int[].class);
        }
        catch(NoSuchMethodException ex){
            System.out.println("There is no counstructor with these parametres in class." + quickSort.getName());
            constructor = null;
        }
        try{
        myObj = constructor.newInstance(arr);
        }
        catch( InstantiationException ex){
            System.out.println("Couldnt create an instance of class " + quickSort.getName());
            myObj = null;
        }
        catch( IllegalAccessException ex){
           System.out.println("Couldnt create an instance of class " + quickSort.getName()+". Ilegal access!");
           myObj = null;

        }
        catch (InvocationTargetException ex){
           System.out.println("Couldnt create an instance of class " + quickSort.getName()+". Invocation !");
           myObj = null;
        }
        try{
         methodSort= quickSort.getMethod("sort");
        }
        catch( NoSuchMethodException ex){
            System.out.println("There is no method sort in " + quickSort.getName());
            methodSort = null;
        }
        try{
        methodSort.invoke(myObj);
        }
        catch(IllegalAccessException ex){
            System.out.println("Couldn't access object of class");
        }
        catch(InvocationTargetException ex){
            System.out.println("Cant call this method on this instance of class");
        }
        try{
         methodShow= quickSort.getMethod("showSortedArray");
        }
        catch( NoSuchMethodException ex){
            System.out.println("There is no method showArray in " + quickSort.getName());
            methodShow = null;
        }
        try{
        methodShow.invoke(myObj);
        }
        catch(IllegalAccessException ex){
            System.out.println("Couldn't access object of class");
        }
        catch(InvocationTargetException ex){
            System.out.println("Cant call this method on this instance of class");
        }
        
}
}
