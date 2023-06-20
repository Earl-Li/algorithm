package com.atlisheng.algor.sort;

import com.atlisheng.algor.bean.Node;
import com.atlisheng.algor.otherQues.ReversePair;
import com.atlisheng.algor.otherQues.SmallSum;
import com.atlisheng.algor.utils.ArrayUtils;
import org.junit.Test;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 测试二分法
 * @创建日期 2023/06/10
 * @since 1.0.0
 */
public class DichotomyTest {
    /**
     * @描述 二分法判断有序数组中某个数是否存在
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/10
     * @since 1.0.0
     */
    @Test
    public void testQues1(){
        int target=0;
        //生成有序随机数组
        int[] randomOrderedArray = ArrayUtils.generateRandomOrderArray(20, 10);
        //随机数组遍历
        for (int node:randomOrderedArray) {
            System.out.print(node+"、");
        }
        int index1=0,index2=randomOrderedArray.length-1,midIndex=ArrayUtils.findMidIndex(index1,index2);
        while(midIndex!=index1 && midIndex!=index2){
            midIndex=ArrayUtils.findMidIndex(index1,index2);
            if (randomOrderedArray[midIndex]==target){
                System.out.println("有这个数"+randomOrderedArray[midIndex]);
                return;
            }else if (randomOrderedArray[midIndex]<target){
                index1=midIndex+1;
            }else{
                index2=midIndex-1;
            }
        }
        System.out.println("没有这个数");

    }

    @Test
    public void testQues2(){
        int[] array = ArrayUtils.generateRandomOrderArray(20, 10);
        ArrayUtils.showAll(array);
        int index = ArrayUtils.findFirstBigThanValueIndexOfOrderedArray(1, array);
        System.out.println(index==-1?"":"数组中大于等于"+1+"的下标是"+index+",值为"+array[index]);
    }

    @Test
    public void test(){
        System.out.println((3<<4)-1);
    }

    @Test
    public void testSmallSum(){
        int[] array={1,3,4,2,5};
        System.out.println(SmallSum.smallSum(array, 0, array.length - 1));
    }

    @Test
    public void testReversePair(){
        int[] randomArray={1,3,4,2,2,5,1};
        System.out.println(ReversePair.reversePair(randomArray));
        ArrayUtils.showAll(randomArray);
    }

}
