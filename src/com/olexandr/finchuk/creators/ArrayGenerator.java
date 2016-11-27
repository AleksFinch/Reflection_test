
package com.olexandr.finchuk.creators;

import com.olexandr.finchuk.annotations.Filler;
import com.olexandr.finchuk.annotations.FillingClass;

/**
 *
 * @author Olexander Finchuk
 */
@FillingClass
public class ArrayGenerator {
    /**
     * Generating sorted array
     * example : (1,2,3,7...max)
     * Fill array of size n with h successive random integers
     * step between integers : 1 to 10
     * @param n - number of array's elements
     * @return sorted array
     */
    @Filler
    public static int [] generSortedArray (int n)
    {
        int [] arr = new int[n];
        int temp = 1;
        for (int i = 0; i < n ; i++){
            arr[i] = generRandomNumber(temp+1, temp + 10);
            temp = arr[i];
        }
        return arr;
    }

    /**
     * Generating random number
     * @param min - minimum random number
     * @param max - maximum random number
     * @return int random number
     */
    private static int generRandomNumber(int min, int max) {
        return (int)(Math.random()*(max - min)+ min);
    }

    /**
     * Generating sorted array with  last random element
     * example : (1,2,3,7...max,X)
     * Fill array of size n with h-1 successive random integers
     * step between integers : 1 to 10
     * fill last element of array with random integer from -100 to 100
     * @param n - number of array's elements
     * @return sorted array with last random element
     */
    @Filler
    public static int [] generSortedArrayWithEndRandom(int n)
    {
        int [] arr = new int[n];
        int temp = 1;
        for (int i = 0; i < n -1 ; i++){
            arr[i] = generRandomNumber(temp+1, temp + 10);
            temp = arr[i];
        }
        arr[n-1] = generRandomNumber(-100, 100);
        return arr;
    }


    /**
     * Generating reversed sorted array
     * example: max, ... 7, 3, 2, 1
     * Fill array in reverse order with n successive random integers
     * step between integers: from 1 to 10
     * @param n - number of array's elements
     * @return reversed sorted array of ints
     */
    @Filler
    public static int [] generReverseSortedArray(int n)
    {
        int [] arr = new int[n];
        int temp = 1;
        for (int i = n - 1; i >= 0; i--)
        {
            arr[i] = arr[i] = generRandomNumber(temp+1, temp + 10);
            temp = arr[i];
        }
        return arr;
    }

    /**
     * Generating array with random integers
     * Fill array of size n with n random integers
     * range of elements:  from -50 to 50
     * @param n - number of array's elements
     * @return arr - array with random integers
     */
    @Filler
    public static int [] generRandomArray(int n)
    {
        int [] arr = new int[n];
        for (int i = n -1; i >= 0; i--)
        {
            int max = 100;
            arr[i] = (int)(Math.random()*max - max/2);
        }
        return arr;
    }


}
