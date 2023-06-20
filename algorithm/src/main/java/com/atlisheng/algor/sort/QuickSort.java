package com.atlisheng.algor.sort;

public class QuickSort {
    public static void quickSort(int[] arr){
        if (arr==null || arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int left,int right){
        if (left>right){
            return;
        }
        swap(arr,left+(int)Math.random()*(right-left+1),right);
        int[] partition = partition(arr, left, right);
        quickSort(arr,left,partition[0]-1);
        quickSort(arr,partition[1]+1,right);
    }
    public static int[] partition(int[] arr,int left,int right){
        int less=left-1;
        int more=right;
        while(left<more){
            if (arr[left]<arr[right]){
                swap(arr,++less,left++);
            }else if (arr[left]>arr[right]){
                swap(arr,--more,left);
            }else {
                left++;
            }
        }
        swap(arr,more,right);
        return new int[] {less+1,more};
    }
    public static void swap(int[] arr,int from,int to){
        int temp=arr[from];
        arr[from]=arr[to];
        arr[to]=temp;
    }
}
