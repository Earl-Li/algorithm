package com.atlisheng.algor.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BubbleSort {
    public static void bubbleSort(int[] arr){
        log.info("bubbleSorting...");
        if (arr==null || arr.length<2) {
            return;
        }
        for (int i=arr.length-1;i>=0;i--){
            for (int j=0;j<i;j++){//单次循环最大的放最右边
                if (arr[j]>arr[j+1]){//相邻两个数大的应该在右边，否则大的右移
                    swap(arr,j,j+1);
                }
            }
        }
    }
    public static void swap(int[] arr,int fromIndex,int toIndex){
        arr[fromIndex]=arr[fromIndex]^arr[toIndex];
        arr[toIndex]=arr[fromIndex]^arr[toIndex];
        arr[fromIndex]=arr[fromIndex]^arr[toIndex];
    }
    /*public static void swap(int[] arr,int fromIndex,int toIndex){
        int temp=arr[fromIndex];
        arr[fromIndex]=arr[toIndex];
        arr[toIndex]=temp;
    }*/
}
