package com.company;


import java.util.*;


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



        Radix radix = new Radix();
        System.out.println();

        System.out.print(" K is "+k + " n is "+n);

        ArrayList sortedArrayList = radix.sort(llist, k);

        LinkedList sortedList = new LinkedList();

        for(int counter = 0; counter < sortedArrayList.size(); counter++){
            sortedList.add(sortedArrayList.get(counter));
        }


        System.out.print(" After sorting linked list ");

        for (int i = 0; i < sortedList.size(); i++) {
            MinHeap currentHeap = (MinHeap) sortedList.get(i);

            printContentsOfAHeap(currentHeap);
        }


        System.out.print(" Removind items from the linked list ");




        int removedItems = 0;
        while(removedItems<n){
            System.out.println(" After removing "+(int)(removedItems+1) +" element");
            removedItems++;
            RemoveSmallestItem(sortedList);

            for (int i = 0; i < sortedList.size(); i++) {
                MinHeap currentHeap = (MinHeap) sortedList.get(i);

                printContentsOfAHeap(currentHeap);
            }
        }



    }

    public  static int[][] readValuesFromUser(){

        Scanner input = new Scanner( System.in);

        try {

            System.out.println("Enter value of n");
            while (!input.hasNextInt()) {
                System.out.println("That's not an integer, enter value of n again!");
                input.next(); // this is important!
            }
            n  = input.nextInt();

            System.out.println("Enter value of k");
            while (!input.hasNextInt()) {
                System.out.println("That's not an integer, enter value of k again!");
                input.next(); // this is important!
            }
            k = input.nextInt();

            if ( n % k == 0 ){
                // create a int array to save user input
                int[] n_integer_values = new int[n];

                // loop over array to save user input
                System.out.println("Enter set of integer values per line for the array elements");
                for ( int i = 0; i < n; i++ ) {
                    while (!input.hasNextInt()) {
                        System.out.println("That's not an integer, enter that value again!");
                        input.next(); // this is important!
                    }
                    int userInput =  input.nextInt();

                    while ( userInput <= 0 || userInput > n) {
                        System.out.println("Invalid:: The integer value must be in range of 1 to " + n);
                        System.out.println("Enter the value next value again to continue..");
                        input.next(); // this is important!
                        userInput = input.nextInt();
                    }
                        n_integer_values[i] = userInput;



                }
                heapValues = n_integer_values;

                System.out.println("The set of Integer array input from user is : ");

                System.out.println(Arrays.toString(n_integer_values));
            }else {
                System.out.println( n+" is not a mulitple of "+k);
                System.out.println("Run the program again with valid values of n and k ");
            }

        }
        catch ( InputMismatchException e ) {
            System.out.println("Invalid Input..Please try again..");

        }

        return createArraysOfHeaps(heapValues);
    }

    public static  int[][] createArraysOfHeaps(int[] n_integer_values){
        int index = 0;
        int[][] arraysOfHeaps = new int[k+1][];
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

    public static int RemoveSmallestItem(LinkedList lList){
        MinHeap firstHeap = (MinHeap)lList.get(0);
        int ItemRemoved = firstHeap.getMinValue();
        for(int cur_index=0; cur_index< lList.size(); cur_index++){
            MinHeap currentHeap = (MinHeap) lList.get(cur_index);
            if(nextHeapHasElements(cur_index,lList)){
                MinHeap nextHeap = (MinHeap) lList.get(cur_index+1);
                int nextHeapsMinValue = nextHeap.getMinValue();
                currentHeap.replaceMinValue(nextHeapsMinValue);
            }
            else{
                currentHeap.removeMinValue();
                int heapsSize = currentHeap.getSize();
                if(heapsSize==0){
                    lList.remove(cur_index);
                }
            }
        }

        return ItemRemoved;
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










