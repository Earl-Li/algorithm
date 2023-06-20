package com.atlisheng.algor.utils;

import com.atlisheng.algor.sort.InsertionSort;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 数组工具类,以下方法都是经过测试的
 * @创建日期 2023/06/10
 * @since 1.0.0
 */
@Slf4j
public class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * @param maxSize  随机数组最大容量
     * @param maxValue 随机数组最大整数值
     * @return {@link int[] 随机数组}
     * @描述 生成随机数组
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/09
     * @since 1.0.0
     * 原理：
     *      Math.random() --> [0,1)的所有小数等概率返回一个
     *      Math.random()*N --> [0,N)的所有小数等概率返回一个
     *      (int) Math.random()*N --> [0,N-1]的所有整数等概率返回一个
     * 疑问：
     *      为什么减去的是(int)(Math.random()*(maxValue)
     */
    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] randomArr=new int[(int)(Math.random()*(maxSize+1))];//数组长度随机
        for (int i=0;i<randomArr.length;i++){
            randomArr[i]=(int)(Math.random()*(maxValue+1))-(int)(Math.random()*(maxValue));//再减一个是为了产生负数
        }
        return randomArr;
    }

    /**
     * @param maxSize  随机数组最大容量
     * @param maxValue 随机数组最大整数值
     * @return {@link int[] }
     * @描述 生成随机数组
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/10
     * @since 1.0.0
     * 生成一个随机数组并进行一次排序
     */
    public static int[] generateRandomOrderArray(int maxSize,int maxValue){
        int[] randomOrderArray=generateRandomArray(maxSize,maxValue);
        InsertionSort.insertionSort(randomOrderArray);
        return randomOrderArray;
    }

    /**
     * @return int 奇数个返回中间下标，偶数个返回前半部分最后一个下标,返回-1表示数组范围格式不正确
     * @描述 找到数组片段的中间数的下标,
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/10
     * @since 1.0.0
     */
    public static int findMidIndex(int startIndex,int endIndex){
        if (endIndex==-1){
            log.info("数组长度为0");
        }
        if (startIndex>endIndex){
            log.info("数组范围错误");
            return -1;
        }
        return startIndex+(endIndex-startIndex)/2;
    }

    /**
     * @param value 指定值
     * @param arr   数组
     * @return int
     * @描述 找出有序数组中满足大于等于指定值的最左侧元素的下标
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/11
     * @since 1.0.0
     */
    public static int findFirstBigThanValueIndexOfOrderedArray(int value,int[] arr){
        int index2=arr.length-1;
        if (index2==-1){
            log.info("数组"+arr+"长度为0");
        }
        int index1=0,midIndex=findMidIndex(index1,index2),target=-1;
        while(midIndex!=index1 && midIndex!=index2){
            midIndex=findMidIndex(index1,index2);
            if (arr[midIndex]>=value){
                target=midIndex;
                index2=midIndex-1;
            }else{
                index1=midIndex+1;
            }
        }
        if (target==-1){
            log.info("数组中没有比"+value+"大的数");
            return -1;
        }
        return target;
    }

    /**
     * @param arr 数组
     * @描述 遍历所有数组
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/11
     * @since 1.0.0
     */
    public static void showAll(int[] arr){
        for (int ele:arr) {
            System.out.print(ele+",");
        }
        System.out.println();
    }

    /**
     * @param arr 翻转数组
     * @return {@link int[] 翻转后的数组，原数组也直接被翻转了 }
     * @描述 翻转数组
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/20
     * @since 1.0.0
     */
    public static int[] reverseArray(int[] arr){
        int[] help=new int[arr.length];
        for (int i = arr.length-1; i >=0; i--) {
            help[arr.length-1-i]=arr[i];
        }
        for (int i = 0; i < help.length; i++) {
            arr[i]=help[i];
        }
        return help;
    }
}
