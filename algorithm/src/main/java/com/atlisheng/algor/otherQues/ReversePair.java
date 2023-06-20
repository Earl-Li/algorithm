package com.atlisheng.algor.otherQues;

import com.atlisheng.algor.utils.ArrayUtils;

public class ReversePair {
    public static int reversePair(int[] arr){
        ArrayUtils.reverseArray(arr);
        int right=arr.length-1;
        return getReversePair(arr, 0, right);
    }
    public static int getReversePair(int[] arr,int left,int right){
        if (arr==null || arr.length<2 || right==left){
            return 0;
        }
        int midIndex=left+((right-left)>>1);
        return getReversePair(arr,left,midIndex)+
                getReversePair(arr,midIndex+1,right)+
                merge(arr,left,midIndex,right);
    }
    public static int merge(int[] arr,int left,int midIndex,int right){
        int[] help=new int[right-left+1];
        int leftIndex=left;
        int rightIndex=midIndex+1;
        int helpIndex=0;
        int res=0;
        while(leftIndex<=midIndex && rightIndex<=right){
            res+=arr[leftIndex]<arr[rightIndex]?right-rightIndex+1:0;
            help[helpIndex++]=arr[leftIndex]<arr[rightIndex]?arr[leftIndex++]:arr[rightIndex++];
        }
        while(leftIndex<=midIndex){
            help[helpIndex++]=arr[leftIndex++];
        }
        while(rightIndex<=right){
            help[helpIndex++]=arr[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[left+i]=help[i];
        }
        return res;
    }
}
