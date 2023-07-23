package com.atlisheng.leetcode.Q724;

import com.atlisheng.leetcode.utils.ArrayUtils;

import java.util.Arrays;

public class Q724 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：求出前缀和，求出后缀和，遍历数组比较左右两侧的前缀后缀和;虽然通过试例，但是感觉逻辑有问题
     * @创建日期 2023/07/23
     * @since 1.0.0
     */
    class Solution1{
        public int pivotIndex(int[] nums) {
            int width=nums.length;
            int[] prefixSum=new int[width+1];//第i位表示前i个数的前缀和
            int[] suffixSum=new int[width+1];//第i位表示后i个数的后缀和
            for (int i = 0; i < width; i++) {
                prefixSum[i+1]=prefixSum[i]+nums[i];
            }
            for (int i = 0; i < width; i++) {
                suffixSum[i+1]=suffixSum[i]+nums[width-1-i];
            }
            for (int i = 0; i < width; i++) {
                if (prefixSum[i]==suffixSum[width-i-1]){
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法2：只需要求一个前缀和，后缀和用一个总和减去当前值表示，尽量不要用两个数组，数组下标的讨论很容易出错
     * @创建日期 2023/07/23
     * @since 1.0.0
     */
    class Solution2{
        public int pivotIndex(int[] nums) {
            int sumRight= Arrays.stream(nums).sum();//Arrays的stream的sum方法求整个数组的和
            int sumLeft=0;
            for (int i = 0; i < nums.length; i++) {
                sumRight-=nums[i];
                if (sumLeft==sumRight){
                    return i;
                }
                sumLeft+=nums[i];
            }
            return -1;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法三：对每个元素求前缀和乘2加当前数的总和是否等于数组的总和
     * @创建日期 2023/07/23
     * @since 1.0.0
     */
    class Solution3{
        public int pivotIndex(int[] nums) {
            int total= Arrays.stream(nums).sum();
            int sum=0;
            for (int i = 0; i < nums.length; i++) {
                if (2*sum+nums[i]==total){
                    return i;
                }
                sum+=nums[i];
            }
            return -1;
        }
    }


}
