package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Utilities {

    public static boolean isLastElementOfList(int i,int linked_list_size){
        return i == linked_list_size-1;

    }

    public static int[] convertIntegersArray(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }


    public static  int[][] createArrayMultidimensionArrayOfHeaps(int[] random_values,int number_of_heaps,int elements_per_heap){

        int[][] heaps_arrays = new int[number_of_heaps][];
        for( int heap_no = 1; heap_no <= number_of_heaps; heap_no ++ ){
            heaps_arrays[heap_no-1] = Arrays.copyOfRange( random_values, (heap_no-1)*elements_per_heap, (heap_no)*elements_per_heap);
        }

        return  heaps_arrays;

    }
}
