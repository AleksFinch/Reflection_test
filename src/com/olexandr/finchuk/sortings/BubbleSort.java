/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olexandr.finchuk.sortings;

import com.olexandr.finchuk.annotations.SortMethod;

/**
 *
 * @author Olexander Finchuk
 */
public class BubbleSort extends Sorting {
    public BubbleSort(){

    }
    @Override
    @SortMethod
    public  int []  sort(int[]arr){
        sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
          for (int i = 0, n = sortedArr.length; i < n - 1; i++) {
            boolean swaped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArr[j] > sortedArr[j + 1]) {
                    int temp = sortedArr[j];
                    sortedArr[j] = sortedArr[j + 1];
                    sortedArr[j + 1] = temp;
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
