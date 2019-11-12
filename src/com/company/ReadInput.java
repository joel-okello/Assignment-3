package com.company;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class ReadInput {
    public static int n;
    public static int k;
    public static int[] heapValues;


    public static void main(String[] args) {

        int userHeaps[][] = readValuesFromUser();

        System.out.println(" Finished reading input from the user");

        System.out.println(" ");
        System.out.println("  Heaps from users input ");

        LinkedList llist = new LinkedList();
        for (int n_heaps = 1; n_heaps <= n/k; n_heaps++) {
            int heapElements[] = userHeaps[n_heaps-1];
            llist.add(new MinHeap(k, n, 1,heapElements));
        }


        for (int i = 0; i < llist.size(); i++) {
            MinHeap currentHeap = (MinHeap) llist.get(i);

            printContentsOfAHeap(currentHeap);
        }



//        int removedItems = 0;
//        while(removedItems<n){
//            System.out.println(" After removing "+(int)(removedItems+1) +" element");
//            RemoveSmallestItem(llist);
//
//            for (int i = 0; i < llist.size(); i++) {
//                MinHeap currentHeap = (MinHeap) llist.get(i);
//
//                printContentsOfAHeap(currentHeap);
//            }
//            removedItems++;
//        }



    }

    public  static int[][] readValuesFromUser(){
        Scanner input = new Scanner( System.in);
        System.out.println("Enter value of n");
        n  = input.nextInt();

        System.out.println("Enter value of k");
        k = input.nextInt();

        // create a int array to save user input
        int[] n_integer_values = new int[n];
        // loop over array to save user input
        System.out.println("Please enter array elements");
        for (int i = 0; i < n; i++) {
            int userInput = input.nextInt();
            n_integer_values[i] = userInput;
        }
        heapValues = n_integer_values;

        System.out.println("The Int array input from user is : ");

        System.out.println(Arrays.toString(n_integer_values));


        return createArraysOfHeaps(n_integer_values);
    }

    public static  int[][] createArraysOfHeaps(int[] n_integer_values){
        int index = 0;
        int[][] arraysOfHeaps = new int[k][];
        for( int i = 0; i < heapValues.length; i += k ){
            arraysOfHeaps[index] = Arrays.copyOfRange( n_integer_values, i, (i+k));
            index++;
        }

        return  arraysOfHeaps;

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

//    public static void RemoveSmallestItem(LinkedList lList){
//        for(int cur_index=0; cur_index< lList.size(); cur_index++){
//            MinHeap currentHeap = (MinHeap) lList.get(cur_index);
//            if(nextHeapHasElements(cur_index,lList)){
//                MinHeap nextHeap = (MinHeap) lList.get(cur_index+1);
//                int nextHeapsMinValue = nextHeap.getMinValue();
//                currentHeap.replaceMinValue(nextHeapsMinValue);
//            }
//            else{
//                currentHeap.removeMinValue();
//                int heapsSize = currentHeap.getSize();
//                if(heapsSize==0){
//                    lList.remove(cur_index);
//                }
//            }
//        }
//
//    }



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










