/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olexandr.finchuk.sortings;

import com.olexandr.finchuk.annotations.ShowArray;

/**
 *
 * @author Olexander Finchuk
 */

public abstract class Sorting {
    protected int[] sortedArr;
    public abstract int [] sort(int[] arr);

    @ShowArray
    public static void showArray(int [] array){

        for (int i = 0, n = array.length; i < n; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }
}

