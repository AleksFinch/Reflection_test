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
public class MergeSort extends Sorting {
    public MergeSort(int [] arr){
        this.arr = arr;
    }
    @Override
    public int [] sort(){
        sortedArr =  new int [arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        sortedArr = mergeSort(sortedArr);
        return sortedArr;
    }
    private int [] mergeSort(int [] array){
        if (array.length == 1)
            return array;
        int[] arr1 = new int[array.length / 2];
        int[] arr2 = new int[array.length - arr1.length];
        
        System.arraycopy(array, 0, arr1, 0, arr1.length);
        System.arraycopy(array, arr1.length, arr2, 0, arr2.length);

        arr1 = mergeSort(arr1);
        arr2 = mergeSort(arr2);
        return merge(arr1, arr2);
    }
        public static int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        for (int k = 0, i = 0, j = 0, n = result.length; k < n; k++) {
            if (i >= arr1.length)
            {
                result[k] = arr2[j];
                j++;
            }
            else if (j >= arr2.length)
            {
                result[k] = arr1[i];
                i++;
            }
            else
            {
                if (arr1[i] < arr2[j]) {
                    result[k] = arr1[i];
                    i++;
                } else {
                    result[k] = arr2[j];
                    j++;
                }
            }
        }

        return result;
    }

}
