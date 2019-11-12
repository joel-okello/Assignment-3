package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ReceiveInputs {
    public int n;
    public int k;
    public int[] heapValues;
    public  int[][] arraysOfHeaps;

    public void ReceiveInputs(){
        readValuesFromUser();
    }

    public  void readValuesFromUser(){
        Scanner input = new Scanner( System.in);
        System.out.println("Enter value of n");
        this.n  = input.nextInt();

        System.out.println("Enter value of k");
        this.k = input.nextInt();

        // create a int array to save user input
        int[] n_integer_values = new int[n];
        // loop over array to save user input
        System.out.println("Please enter array elements");
        for (int i = 0; i < n; i++) {
            int userInput = input.nextInt();
            n_integer_values[i] = userInput;
        }
        this.heapValues = n_integer_values;

        System.out.println("The Int array input from user is : ");

        System.out.println(" ");
        createArraysOfHeaps();
    }

    public void createArraysOfHeaps(){
        int index = 0;
        for( int i = 0; i < heapValues.length; i += k ){
            arraysOfHeaps[index] = Arrays.copyOfRange( heapValues, i, Math.min( heapValues.length, i+k ));
            index++;
        }

    }



}


