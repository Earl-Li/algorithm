package com.atlisheng.algor.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertionSort {
    public static void insertionSort(int[] arr){
        log.info("insertionSorting...");
        if (arr==null || arr.length<2){
            return;
        }
        for (int i=1;i<arr.length;i++){//取1-i范围
            /*for (int j = i; j > 0; j--) {//j从i-1顺序遍历
                if (arr[j]<arr[j-1]){//如果j下标元素小于j-1下标元素，j号和j-1号互换位置
                    swap(arr,j,j-1);
                }
            }*/
            for (int j = i; j > 0 && arr[j]<arr[j-1]; j--) {//j从i-1顺序遍历
                swap(arr,j,j-1);
            }
        }
    }
    public static void swap(int[] arr,int fromIndex,int toIndex){
        arr[fromIndex]=arr[fromIndex]^arr[toIndex];
        arr[toIndex]=arr[fromIndex]^arr[toIndex];
        arr[fromIndex]=arr[fromIndex]^arr[toIndex];
    }
}
