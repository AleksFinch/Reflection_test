
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
import java.lang.NullPointerException;
public abstract class Sorting {
    int [] arr;
    int [] sortedArr;
    public int [] getArr(){
        return arr;
    }
    public int [] getSortedArr(){
        try {
            return sortedArr;
        }
        catch(NullPointerException ex ){
            if (sortedArr == null){
                System.out.println("The array wasn't sorted yet. \nPlease, use sort() method on your object first.");
            }
            return null;
        }
    }
    public abstract int [] sort();
    public  void showSortedArray(){
        showArray(getSortedArr());
    }
    public  void showUnsortedArray(){
        showArray(arr);
    }
    private static void showArray(int [] array){
           {
        for (int i = 0, n = array.length; i < n; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    }
}
