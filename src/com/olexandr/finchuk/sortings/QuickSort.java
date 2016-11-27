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
public class QuickSort extends Sorting{
    public QuickSort(){
    }

    /**
     *
     * @return sorted array
     */
    @Override
    @SortMethod
    public   int [] sort(int[] arr)
    {

        sortedArr = new int[arr.length];
        System.arraycopy( arr, 0, sortedArr, 0, arr.length );
       return quickquickSort( 0, sortedArr.length -1);
    }

    /**
     * Quick Sort
     * Sort array using recursion, partition and comparison
     * @param left - left index
     * @param right - right index
     * @return - sorted array of ints
     */
    private  int [] quickquickSort( int left, int right)
    {

        if (sortedArr.length < 2)
            return sortedArr;
        int index = partition(left, right);
        if (left < index - 1) {
            quickquickSort( left, index - 1);
        }
        if (index < right) {
            quickquickSort(index, right);
        }
        return sortedArr;

    }

    /**
     * places all the elements  smaller than pivot to the left of pivot in the part of array
     * places all the elements larger than pivot to the right of the pivot in the part of array
     * pivot - middle element of sortedArr[left,...,right]
     * @param left -  beginning index(inclusive) of the part of  array we will transform
     * @param right - end index(inclusive) of the part of array we will transform
     * @return transformed array
     */
    private  int  partition( int left, int  right)
    {
        int i = left;
        int j = right;
        int temp;
        int pivot = sortedArr[(left+right)/2];
        while (i<=j)
        {
            while ( sortedArr[i]< pivot)
        {
            i++;
        }
            while ( sortedArr[j]> pivot)
            {
                j--;
            }
            if ( i <= j)
            {
                temp = sortedArr[i];
                sortedArr[i]= sortedArr[j];
                sortedArr [j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

}
