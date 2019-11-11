package com.company;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int n = 12;
        int k = 3;

        int elements_per_heap = n / k;


        LinkedList llist = new LinkedList();
        for (int n_heaps = 1; n_heaps <= k; n_heaps++) {
            llist.add(new MinHeap(elements_per_heap, n, 1));
        }

        for (int i = 0; i < llist.size(); i++) {
            MinHeap currentHeap = (MinHeap) llist.get(i);
            System.out.println(Arrays.toString(currentHeap.getHeap()));
        }


    }

}










