package com.company;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int n = 6;
        int k = 1;
        int max_value = n;
        int min_value = 1;

        int elements_per_heap = n / k;

        LinkedList llist = new LinkedList();
        for (int n_heaps = 1; n_heaps <= k; n_heaps++) {

            int heapElement[] = new int[0];
            llist.add(new MinHeap(elements_per_heap, max_value, min_value,heapElement));

        }

        for (int i = 0; i < llist.size(); i++) {
            MinHeap currentHeap = (MinHeap) llist.get(i);

            printContentsOfAHeap(currentHeap);
        }

        Radix radix = new Radix();
        System.out.println(radix.sort(llist, k));

        for (int i = 0; i < radix.sort(llist, k).size(); i++) {
            MinHeap currentHeap = (MinHeap) radix.sort(llist, k).get(i);
            printContentsOfAHeap(currentHeap);
        }


        int removedItems = 0;
        while(removedItems<5){
            System.out.println(" After removing "+(int)(removedItems+1) +" element");
            removedItems++;
            RemoveSmallestItem(llist);

            for (int i = 0; i < llist.size(); i++) {
                MinHeap currentHeap = (MinHeap) llist.get(i);

                printContentsOfAHeap(currentHeap);
            }
        }










        //need to arrange the linked list and perform replacements

        //an array to store minimums of all of the linked list heaps
//        int[] minHeapValues = new int[llist.size()];

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

    public static void RemoveSmallestItem(LinkedList lList){
        for(int cur_index=0; cur_index< lList.size(); cur_index++){
           MinHeap currentHeap = (MinHeap) lList.get(cur_index);
           if(nextHeapHasElements(cur_index,lList)){
               MinHeap nextHeap = (MinHeap) lList.get(cur_index+1);
               int nextHeapsMinValue = nextHeap.getMinValue();
               currentHeap.replaceMinValue(nextHeapsMinValue);
           }
           else{
               currentHeap.removeMinValue();
           }
        }

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

}










