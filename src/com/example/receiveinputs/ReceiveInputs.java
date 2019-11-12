package com.example.receiveinputs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ReceiveInputs {
    public static void main(String[] args) {

        Scanner input = new Scanner( System.in);

        System.out.println("Enter value of n");
        int n = input.nextInt();

        System.out.println("Enter value of k");
        int k = input.nextInt();

        // create a String array to save user input
        String[] n_integer_values = new String[n];
        // loop over array to save user input
        System.out.println("Please enter array elements");
        for (int i = 0; i < n; i++) {
            String userInput = input.next();
            n_integer_values[i] = userInput;
        }
        System.out.println("The String array input from user is : ");
        System.out.println( Arrays.toString( n_integer_values ) );

        System.out.println(" ");
        System.out.println("The String array of heaps of input from user is : ");
        int chunk = k; // chunk size to divide
        for( int i = 0; i < n_integer_values.length; i += chunk ){
           String heap = Arrays.toString(Arrays.copyOfRange( n_integer_values, i, Math.min( n_integer_values.length, i+chunk )));
//           sort(heap);

            System.out.print( heap );
        }
        System.out.println(" ");


    }

}
