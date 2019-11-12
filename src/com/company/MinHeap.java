package com.company;


import java.util.Arrays;

// Java implementation of Min Heap
public class MinHeap {
    private int[] Heap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;


    //constructor for MinHeap class create a minheap object
    public MinHeap(int no_of_elements, int max, int min)
    {
        int[] randonArray = generateRandomHeap( no_of_elements,  max, min);
        this.Heap = new int[no_of_elements+1];
        this.maxsize = no_of_elements;
        for (int i = 0; i< no_of_elements; i++ ){
            insert(randonArray[i]);
        }

    }


    //

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
        return pos >= (size / 2) && pos <= size;
    }

    // Function to swap two nodes of the heap
    private void swap(int firstpos, int secondpos)
    {
        int tmp;
        tmp = Heap[firstpos];
        Heap[firstpos] = Heap[secondpos];
        Heap[secondpos] = tmp;
    }

    // Function to heapify the node at pos
    private void minHeapify(int pos)
    {

        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[leftChild(pos)]
                    || Heap[pos] > Heap[rightChild(pos)]) {

                // Swap with the left child and heapify
                // the left child
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }


    // Function to insert a node into the heap
    public void insert(int element)
    {
        if (size >= maxsize) {
            return;
        }
        Heap[++size] = element;
        int current = size;

        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Function to build the min heap using
    // the minHeapify
    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public int getMinValue(){
        return Heap[FRONT];

    }

    // Function to replace the Minimum Value in the heap with a new value
    public void replaceMinValue(int newValue){
        Heap[FRONT] = newValue;
        minHeapify(FRONT);

    }

    // Function to remove and return the minimum element from the heap
    public int removeMinValue(){
        int minValue = Heap[FRONT];
        int lastValueInHeap = Heap[size];
        //move last value to root of heap
        Heap[FRONT] = lastValueInHeap;

        //reduce the size of the heap
        size = size - 1;
        //reorganise the heap
        minHeapify(FRONT);

        return minValue;
    }



    //create a head a specified size with members between a max and min value
    public int[] generateRandomHeap(int size, int max, int min)
    {
        int[] heap = new int[size];
        for(int i = 0 ; i < size ; i++){
            heap[i] = (int)(Math.random() * ((max - min) + 1)) + min;
        }

        return  heap;

    }

    public int[] getHeap(){

        // return elements in the heap array excluding index zero since the head of the heap is at index one
        int[] heapElements = Arrays
                .copyOfRange(
                        Heap,FRONT, size+1);

        return heapElements;
    }


}





