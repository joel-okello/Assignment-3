package com.company;



import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Example {
    public  int[] Heap = {0,4,3,2,11} ;
    public  int size = 4;
    public  int FRONT = 1;
    public  int maxsize = 4;



    public static void main(String[] args) {

        Example example = new Example();
        System.out.print(" Starting Heap");
        example.print();
        System.out.println(" Start reoganising ");

        example.minHeapify(1);
        System.out.println("  Heaps from users input ");





    }

    // Function to heapify the node at pos
    private void minHeapify(int pos)
    {
        System.out.println("  Call to min heapify ");
        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos) ) {
            System.out.println("  Is not leaf node pos " +pos);

            System.out.println(" Heap[pos] > Heap[leftChild(pos)] " + (Heap[pos] > Heap[leftChild(pos)]));

            if (Heap[pos] > Heap[leftChild(pos)]
                    || ((rightChild(pos) <= size) && Heap[pos] > Heap[rightChild(pos)])) {
                System.out.println("  Children are less than the parent " +pos);


                // if current pos node is greater than its left child swap it with its left child
                if (rightChildExists(pos) && Heap[rightChild(pos)] > Heap[leftChild(pos)]) {
                    if(nodeIsPartOfCurrentHeap(leftChild(pos)))
                    {
                        swap(pos, leftChild(pos));
                        System.out.println(" Swapped top "+pos +"and the left child "+leftChild(pos));
                        print();
                        System.out.print( " called out to heapify pos "+leftChild(pos));
                        minHeapify(leftChild(pos));

                    }


                }

                // if current pos node is greater than its left child swap it with its left child
                if (rightChildExists(pos) && Heap[rightChild(pos)] <= Heap[leftChild(pos)]) {
                    if(nodeIsPartOfCurrentHeap(rightChild(pos))) {
                        swap(pos, rightChild(pos));
                        System.out.print(" Swapped top "+pos +"and the right child"+rightChild(pos));
                        print();
                        System.out.print( " called out to heapify pos "+rightChild(pos));
                        minHeapify(rightChild(pos));
                    }

                }

                if(!rightChildExists(pos) && nodeIsPartOfCurrentHeap(leftChild(pos))){
                    swap(pos, leftChild(pos));
                    System.out.print(" Swapped top "+pos +"and the left child "+leftChild(pos));
                    print();
                }
            }
        }


    }




    // Function to return the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos)
    {
        return pos / 2;
    }

    // Function to return the position of the
    // left child for the node currently at pos
    private int leftChild(int pos)
    {
        return (2 * pos);
    }

    // Function to return the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Function that returns true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos)
    {
        int posLeftChild = leftChild(pos);
        return posLeftChild > size ;
    }

    // Function to swap two nodes of the heap
    private void swap(int firstpos, int secondpos)
    {
        int tmp;
        tmp = Heap[firstpos];
        Heap[firstpos] = Heap[secondpos];
        Heap[secondpos] = tmp;
    }

    private  boolean rightChildExists(int pos){
        return  rightChild(pos) <= size;
    }

    private boolean nodeIsPartOfCurrentHeap(int pos){
        return pos <= size;
    }

    public void print(){
        System.out.print(" [ ");
        int counter = 0;
        while(counter< maxsize){
            System.out.print(Heap[counter+1]+",");
            counter++;
        }
        System.out.print(']');
        System.out.println("");

    }












}










