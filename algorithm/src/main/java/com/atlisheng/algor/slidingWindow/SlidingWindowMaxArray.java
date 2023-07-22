package com.atlisheng.algor.slidingWindow;

import java.util.LinkedList;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 滑动窗口:滑动窗口是窗口只能右边界或左边界向右滑情况下，维持窗口内部最大值或最小值快速更新的结构，更新手段就是比当前小或等于的都弹出
 * @创建日期 2023/07/21
 * @since 1.0.0
 */
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr,int widthOfWindow){
        if (arr==null || widthOfWindow<1 || arr.length<widthOfWindow){//数组为空或者窗口宽度为0或者数组长度小于窗口宽度都直接返回null
            return null;
        }
        LinkedList<Integer> qmax=new LinkedList<>();//qmax就是滑动窗口，这个是有边界右移情况下维持最大值更新
        int[] res=new int[arr.length-widthOfWindow+1];//弄长度为数组长度-滑动窗口长度+1的res数组来存储滑动窗口变化过程中的最大值信息
        int index=0;
        for (int i = 0; i < arr.length; i++) {
            while(!qmax.isEmpty() && arr[qmax.peekLast()]<=arr[i]){//当qmax不为空且qmax最后一个小于等于当前的元素
                qmax.pollLast();//qmax弹出最后一个
            }
            qmax.addLast(i);//直到qmax中小于等于当前值的全部弹出将当前值加到末尾,注意存的是当前值的下标
            if (qmax.peekFirst()==i-widthOfWindow){//如果滑动窗口首位的下标超出滑动窗口的大小范围，头部元素弹出，即只有当当前最大值超出滑动窗口范围时，最大值被弹出
                qmax.pollFirst();
            }
            if(i>=widthOfWindow-1){
                res[index++]=arr[qmax.peekFirst()];//当循环到i等于滑动窗口长度时，res从下标0开始依次保存滑动窗口头部即当前滑动窗口最大值
            }
        }
        return res;
    }
    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
        int w = 3;
        printArray(getMaxWindow(arr, w));
    }

}
