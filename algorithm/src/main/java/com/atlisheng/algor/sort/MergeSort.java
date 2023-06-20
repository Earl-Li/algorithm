package com.atlisheng.algor.sort;

public class MergeSort {
    public static void mergeSort(int[] arr,int left,int right){
        if (arr.length<2 || left==right){
            return;
        }
        int midIndex=left+((right-left)>>1);
        mergeSort(arr,left,midIndex);
        mergeSort(arr,midIndex+1,right);
        merge(arr,left,midIndex,right);
    }
    public static void merge(int[] arr,int left,int midIndex,int right){
        int[] help=new int[right-left+1];
        int leftIndex=left;
        int rightIndex=midIndex+1;
        int helpIndex=0;
        while (leftIndex<=midIndex && rightIndex<=right){
            help[helpIndex++]=arr[leftIndex]<=arr[rightIndex]?arr[leftIndex++] :arr[rightIndex++];
        }
        while (leftIndex<=midIndex){
            help[helpIndex++]=arr[leftIndex++];
        }
        while (rightIndex<=right){
            help[helpIndex++]=arr[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[left+i]=help[i];
        }
    }
}
