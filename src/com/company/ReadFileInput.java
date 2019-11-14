package com.company;



import java.io.*;
import java.util.*;

import static com.company.Utilities.*;


public class ReadFileInput {
    public static int n;
    public static int k;
    public static int[] heapValues;
    public static  int removal_swaps = 0;
    public  static int construction_swaps = 0;


    public static void main(String[] args) throws Exception {


        boolean success = readContentsFromFile();
        if(!success){
            throw new Exception(" reading file failed !");
        }

        int number_of_heaps = n/k;

        int[][] userHeaps = createArrayMultidimensionArrayOfHeaps(heapValues, number_of_heaps, k);


        LinkedList llist = createLinkedListOfHeapifiedHeaps(userHeaps);

        updateNumberOfSwapsInConstruction(llist);


        LinkedList sortedList = sortUsingRadixSort(llist);



        removeItemsAndPrintRemainingStructure(sortedList, n);

        System.out.println(" Number of swaps construction = "+ construction_swaps + " Number of swaps removal "+removal_swaps);



    }

    public static String remainingDataStructure(LinkedList sortedList){
        String data_structure = "";
        for(int i=0; i < sortedList.size(); i++){
            data_structure += Arrays.toString( ((MinHeap)sortedList.get(i)).getHeap());
            if(!isLastElementOfList(i,sortedList.size())){
                data_structure += " --> ";
            }
        }
        return data_structure;
    }

    public static void updateNumberOfSwapsInConstruction(LinkedList linkedList){

        for(int index = 0; index < linkedList.size(); index++){
            MinHeap curentHeap = (MinHeap)linkedList.get(index);
            construction_swaps +=  curentHeap.n_swaps_construction;
        }

    }

    public static String whiteSpace(int removed_number){
        int length = String.valueOf(removed_number).length();
        int spaces = 14 - length;
        String white_space = "";
        for (int i=0; i < spaces; i++ ){
            white_space+= " ";
        }
        return white_space;
    }




    public static  int[][] createArraysOfHeapifiedHeaps(int[] random_values,int number_of_heaps,int elements_per_heap){

        int[][] arraysOfHeaps = new int[number_of_heaps][];
        for( int heap_no = 1; heap_no <= number_of_heaps; heap_no ++ ){
            arraysOfHeaps[heap_no-1] = Arrays.copyOfRange( random_values, (heap_no-1)*elements_per_heap, (heap_no)*elements_per_heap);
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
                    removal_swaps+=currentHeap.n_swaps_remove;
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

    public static  void removeItemsAndPrintRemainingStructure(LinkedList sortedList, int n_element_in_heap){
        int removedItems = 0;

        System.out.println("");
        System.out.println("*************** Output ***************");
        System.out.println("");
        System.out.println("Removed Item  |  Remaining Data Structure");
        System.out.println("              |");

        while( removedItems < n_element_in_heap ){
            removedItems++;
            int removedItem = RemoveSmallestItem(sortedList);
            String white_space = whiteSpace(removedItem);
            String data_structure = remainingDataStructure(sortedList);
            System.out.println(removedItem+""+white_space+"| "+data_structure);
        }
    }

    public static LinkedList sortUsingRadixSort(LinkedList llist){
        Radix radix = new Radix();

        ArrayList sortedArrayList = radix.sort(llist, k);

        LinkedList sortedList = new LinkedList();

        for(int counter = 0; counter < sortedArrayList.size(); counter++){
            sortedList.add(sortedArrayList.get(counter));
        }

        return  sortedList;
    }

    public  static LinkedList createLinkedListOfHeapifiedHeaps(int[][] userHeaps){
        LinkedList llist = new LinkedList();
        for (int n_heaps = 1; n_heaps <= n/k; n_heaps++) {
            int heapElements[] = userHeaps[n_heaps-1];
            llist.add(new MinHeap(k, n, 1,heapElements));
        }

        return llist;
    }

    public static boolean readContentsFromFile() throws Exception {

        boolean success = false;

        BufferedReader br = null;
        String line = "";
        int content_line_number = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a filename eg 'assign3input2.txt'  ");
        System.out.println("(if file is not in project folder include path) ... ");
        String filename = in.nextLine();
        System.out.println("Opening " + filename);

        try {
            br = new BufferedReader(new FileReader(filename));
            System.out.println(" File found ");
            ArrayList<Integer> input_values = new ArrayList<Integer>();
            while( (line = br.readLine()) != null){
                if(line.trim().length() > 0) {
                    content_line_number++;
                    if(content_line_number == 1){
                       n =  Integer.parseInt(line.trim());
                       System.out.println(" n =  "+n);
                    }

                    if(content_line_number == 2){
                        k =  Integer.parseInt(line.trim());
                        System.out.println(" k = "+k);
                    }

                    if(content_line_number > 2){
                        String[] values = line.split("\\s+");

                        for (int counter= 0; counter<values.length; counter++) {
                            int current_int = Integer.parseInt(values[counter]);
                            if(current_int > n){
                                throw new Exception(" value "+current_int + " is greater than n "+n);
                            }
                            input_values.add(current_int);

                        }
                    }


                }

            }
            performValidationChecks();
            System.out.println(" there are : "+input_values.size() + " integer values");

            heapValues = convertIntegers(input_values);

            success = true;


        } catch (FileNotFoundException e) {
            System.err.println("Oops! Check if file exists");
            e.getMessage();
        } catch (IOException e) {
            System.err.println("Oops! Unable to read the file.");
            e.getMessage();
        }
        catch (Exception ex){
            System.err.println(" File contains none numeric charaters");
            ex.getMessage();
        }

        return success;
    }


    public static void  performValidationChecks() throws Exception {
        if(n%k !=0){
            throw new Exception(" n is not a multiple of k");
        }
    }








}











