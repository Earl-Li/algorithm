package com.atlisheng.algor.sort;

import com.atlisheng.algor.utils.ArrayUtils;
import org.junit.Test;

public class SortAlgorTest {
    @Test
    public void testSelectedSort(){
        int[] randomArray = ArrayUtils.generateRandomArray(30, 200);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
        System.out.println();

        SelectedSort.selectedSort(randomArray);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
    }

    @Test
    public void testBubbleSort(){
        int[] randomArray = ArrayUtils.generateRandomArray(50, 200);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
        System.out.println();

        BubbleSort.bubbleSort(randomArray);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
    }

    @Test
    public void testInsertionSort(){
        int[] randomArray = ArrayUtils.generateRandomArray(50, 200);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
        System.out.println();

        InsertionSort.insertionSort(randomArray);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
    }

    @Test
    public void testMergeSort(){
        int[] randomArray = ArrayUtils.generateRandomArray(50, 200);
        for (int node:randomArray) {
            System.out.print(node+",");
        }
        System.out.println();
        int[] arr2={1,3,4,2,5};

        MergeSort.mergeSort(randomArray,0,randomArray.length-1);
        for (int node:randomArray) {
            System.out.print(node+"、");
        }
        System.out.println();
        MergeSort.mergeSort(arr2,0,arr2.length-1);
        for (int node:arr2) {
            System.out.print(node+"、");
        }
    }

    @Test
    public void testQuickSorted(){
        int[] randomArray = ArrayUtils.generateRandomArray(50, 200);
        ArrayUtils.showAll(randomArray);

        QuickSort.quickSort(randomArray);
        ArrayUtils.showAll(randomArray);
    }

}
