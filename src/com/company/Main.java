package com.company;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int n = 12;
        int k = 3;
        int max_value = n;
        int min_value = 1;

        int elements_per_heap = n / k;

        LinkedList llist = new LinkedList();
        for (int n_heaps = 1; n_heaps <= k; n_heaps++) {
//            int[] a_heap = new MinHeap(elements_per_heap, max_value, min_value);
//            llist.add(a_heap);
        }



        for (int i = 0; i < llist.size(); i++) {
            MinHeap currentHeap = (MinHeap) llist.get(i);
            printContentsOfAHeap(currentHeap);
        }



        //need to arrange the linked list and perform replacements

        //an array to store minimums of all of the linked list heaps
        int[] minHeapValues = new int[llist.size()];

//        for (int i = 0; i < llist.size(); i++) {
//            MinHeap currentHeap = (MinHeap) llist.get(i);
//            minHeapValues[i] = currentHeap.getMinValue();
//            if(notFirstHeap(i)){
//                currentHeap.replaceMinValue(minHeapValues[i-1]);
//            }
//            else{
//
//            }
//
//
//            System.out.println(Arrays.toString(currentHeap.getHeap()));
//        }




    }

    public  static  void printContentsOfAHeap(MinHeap currentHeap){
        System.out.println(Arrays.toString(currentHeap.getHeap()));

    }

    public  static boolean notFirstHeap(int index){
        return index > 0;
    }

}










