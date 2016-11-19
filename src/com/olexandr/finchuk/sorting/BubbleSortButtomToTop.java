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
public class BubbleSortButtomToTop extends Sorting {
    public BubbleSortButtomToTop(int [] arr){
        this.arr = arr;
    }
    @Override
    public int [] sort(){
        sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        for (int i = 0, n = sortedArr.length; i < n - 1; i++) {
            boolean swaped = false;
            for (int j = sortedArr.length - 1; j > 0 + i; j--) {
                if (sortedArr[j] < sortedArr[j - 1]) {
                    int temp = sortedArr[j];
                    sortedArr[j] = sortedArr[j - 1];
                    sortedArr[j - 1] = temp;
                    swaped = true;
                }
            }
            if (!swaped) {
                break;
            }
        }
        return sortedArr;
    }
}
