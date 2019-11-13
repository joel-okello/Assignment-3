package com.company;


import java.util.*;

import static com.company.ReadInput.RemoveSmallestItem;

public class Main {

    public static void main(String[] args) {
        
        int max = 1000;
        int min = 1;
        int n = 10000;


        int [] values_for_k = {10000,1000,100,10,1};




        for (int i = 0; i<values_for_k.length;i++) {

            long start_time = System.nanoTime();


            int [] random_values = generateRandomArrayOfValues(n,  max, min);
            int number_of_heaps = n/values_for_k[i];
            int elements_per_heap = values_for_k[i];
            int n_items_in_linked_list = n;



            int generated_heaps[][] =  createArraysOfHeaps(random_values,number_of_heaps,elements_per_heap);

            LinkedList llist = makeLinkedListOfHeaps(generated_heaps,number_of_heaps,n,elements_per_heap);

            LinkedList sortedList = sortLinkedListUsingRadix( llist, elements_per_heap);

            removeItemsFromLinkedList(sortedList,n_items_in_linked_list);




            long stop_time = System.nanoTime();

            long elapsed_time =  stop_time - start_time;

            System.out.println("K = "+values_for_k[i]+" Elapsed time is "+elapsed_time );

        }
    }

        public static LinkedList makeLinkedListOfHeaps(int[][] generated_heaps,int number_of_heaps, int max_element_in_heap, int elements_per_heap){
            LinkedList llist = new LinkedList();
            for (int count_heaps = 1; count_heaps <= number_of_heaps; count_heaps++) {
                int heapElements[] = generated_heaps[count_heaps-1];

                llist.add(new MinHeap(elements_per_heap, max_element_in_heap, 1,heapElements));
            }


            return llist;
        }


    public static  LinkedList sortLinkedListUsingRadix(LinkedList llist, int elements_per_heap){

        Radix radix = new Radix();

        ArrayList sortedArrayList = radix.sort(llist, elements_per_heap);

        LinkedList sortedList = new LinkedList();

        for(int counter = 0; counter < sortedArrayList.size(); counter++){
            sortedList.add(sortedArrayList.get(counter));
        }

        return  sortedList;
    }




    public static  boolean nextHeapHasElements(int heapIndex, LinkedList lList){
        if(isLastHeap(heapIndex,lList.size())){
            return  false;
        }

        MinHeap nextHeap = (MinHeap) lList.get(heapIndex + 1);

        return nextHeap.getSize() > 0;
    }

    public static boolean isLastHeap(int heapIndex, int heapSize){
        //index starts at zero, max index is less than size by one
        return heapIndex >= heapSize-1;

    }





    public static  boolean currentHeapHasElements(MinHeap currentHeap){
        return  currentHeap.getSize() > 0;
    }


    public  static  void printContentsOfAHeap(MinHeap currentHeap){
        System.out.println(Arrays.toString(currentHeap.getHeap()));

    }

    public  static boolean notFirstHeap(int index){
        return index > 0;
    }

    //create a head a specified size with members between a max and min value
    public static int[] generateRandomArrayOfValues(int no_of_values, int max, int min)
    {
        int[] input_array = new int[no_of_values];

        // define the range
        int range = max - min + 1;

        for(int i = 0 ; i < no_of_values ; i++){
            input_array[i] = (int)(Math.random() * range) + min;
        }

        return  input_array;

    }

    public static  int[][] createArraysOfHeaps(int[] random_values,int number_of_heaps,int elements_per_heap){

        int[][] arraysOfHeaps = new int[number_of_heaps][];
        for( int heap_no = 1; heap_no <= number_of_heaps; heap_no ++ ){
            arraysOfHeaps[heap_no-1] = Arrays.copyOfRange( random_values, (heap_no-1)*elements_per_heap, (heap_no)*elements_per_heap);
        }

        return  arraysOfHeaps;

    }

    public static void removeItemsFromLinkedList(LinkedList sortedList,int n_items_in_linked_list){

        int removedItems = 0;
        while(removedItems<n_items_in_linked_list){
            removedItems++;
            int removedItem = RemoveSmallestItem(sortedList);
//                System.out.print(removedItem);
        }

    }

}










