package com.company;

import java.util.*;

public class Radix {

    public ArrayList<MinHeap> sort(LinkedList min_heaps, int k) {
        ArrayList<MinHeap> a_list = new ArrayList<MinHeap>(min_heaps); //convert linked list of heaps to Array list of heaps
        for (int place_value = (k-1); place_value >= 0; place_value--) {
            a_list = this.sortHeapsByPlaceValue(place_value, a_list); //sort heaps based on the position of a digit in each heap
        }
        return a_list; //sorted array list is returned
    }


    private ArrayList<MinHeap> sortHeapsByPlaceValue(int place_value, ArrayList<MinHeap> heaps) {
        ArrayList<MinHeap> sorted = new ArrayList<MinHeap>();
        int no_of_heaps = heaps.size();

        for (int i = 0; i < no_of_heaps; i++) {

            for(int j=1; j < (no_of_heaps-i); j++){

                MinHeap prev_heap = heaps.get(j-1);
                MinHeap current_heap = heaps.get(j);

                int prev_value = prev_heap.getHeap()[place_value];
                int current_value = current_heap.getHeap()[place_value];

                if(prev_value > current_value){
                    //swap elements
                    Collections.swap(heaps, j-1, j);
                }

            }

        }
        return heaps;
    }


}