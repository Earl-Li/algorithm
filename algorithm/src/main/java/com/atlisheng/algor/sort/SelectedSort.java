package com.atlisheng.algor.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelectedSort {
    public static void selectedSort(int[] arr){
        log.info("selectedSorting...");
        if (arr==null || arr.length<2){
            return;
        }
        for (int i=0;i<arr.length;i++){
            int minValueIndex=i;//minValueIndex接收内层遍历最小值的下标
            for (int j=i;j<arr.length;j++){
                minValueIndex=arr[minValueIndex]<arr[j]?minValueIndex:j;//如果当前元素比当前最小值小，把当前元素下标赋值给minValueIndex
            }
            swap(arr,i,minValueIndex);//内层遍历第一个元素与本次遍历最小值位置互换
        }
    }
    //数组元素互换方法swap,注意必须定义成静态方法，因为静态方法调用实例方法必须通过引用调用，而静态方法调用静态方法可以省略类名
    private static void swap(int[] arr,int fromIndex,int toIndex){
        int temp=arr[fromIndex];
        arr[fromIndex]=arr[toIndex];
        arr[toIndex]=temp;
    }
}
