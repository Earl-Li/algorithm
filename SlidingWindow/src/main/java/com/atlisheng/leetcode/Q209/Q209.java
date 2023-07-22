package com.atlisheng.leetcode.Q209;

import java.util.Arrays;

public class Q209 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：暴力法,以每个元素作为头，找到大于等于target的最小下标，从而得到每个元素开头的最小长度
     * @创建日期 2023/07/22
     * @since 1.0.0
     */
    class Solution1 {
        public int minSubArrayLen(int target, int[] nums) {
            int ret = Integer.MAX_VALUE;
            int width = nums.length;
            if (width == 0) {
                return 0;
            }
            for (int i = 0; i < width; ++i) {
                int sum = nums[i];
                int k = i;
                while (sum < target && k < width - 1) {
                    k++;
                    sum += nums[k];
                }
                if (sum >= target) {
                    ret = Math.min(ret, k - i + 1);
                }
            }
            return ret > width ? 0 : ret;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法二：前缀和+二分查找：算出所有前缀和，遍历前缀和数组的每个元素，与目标值相加，用二分查找找到对应结果在前缀和数组中的位置并计算对应长度求出最小长度
     * @创建日期 2023/07/22
     * @since 1.0.0
     */
    class Solution2 {
        public int minSubArrayLen(int s, int[] nums) {
            int width = nums.length;
            if (width == 0) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int[] sums = new int[width + 1];//为了方便计算，将size=n+1,sums[0]=0意味着前0个元素的前缀和为0，sums[1]=A[0]意味着前1个元素的前缀和为A[0]
            for (int i = 1; i <= width; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];//前i个元素的前缀和为前i-1个元素的前缀和+第i-1个元素,相当于一次求出了所有的前缀和,且i位保存的是前下标i-1及之前的所有元素和
            }
            for (int i = 1; i <= width; i++) {//遍历数组
                int target = s + sums[i - 1];//target是目标值与前i-1个的和的总和，意思是测试每个前缀和加上目标值s是否有前缀和与其匹配，如果有，二分查找对应前缀和的索引求目标值对应的数组长度
                int bound = Arrays.binarySearch(sums, target);//使用二分查找算法在sums中搜索target，返回搜索值的索引，如果没有就返回-1或-插入点【插入点是第一个大于指定值的元素的索引】，只有完全小于数组范围内才会返回-1，完全大于数组范围返回-（length+1）
                if (bound < 0) {
                    bound = -bound - 1;//前缀和数组中找不到target，bound变成target应该插入点的下标[因为binarySearch返回值是从1开始计的]
                }
                if (bound <= width) {
                    ans = Math.min(ans, bound - (i - 1));//如果target在前缀和数组的范围中，就拿长度bound-(i-1)和ans比较，谁更小取谁
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法三：滑动窗口：当窗口和小于目标值，窗口右边界右移，直到窗口总和大于等于目标值；此时左边界左移直到窗口总和小于目标值，满足窗口总和大于等于目标值，记录比较窗口长度选取最小的返回
     * @创建日期 2023/07/23
     * @since 1.0.0
     */
    class Solution3{
        public int minSubArrayLen(int s,int[] nums){
            int width= nums.length;
            if (width==0){
                return 0;
            }
            int ans=Integer.MAX_VALUE;
            int start=0,end=0;
            int sum=0;
            while(end<width){
                sum+=nums[end];
                while(sum>=s){//当右边界右移满足sum>=目标值时，左边界开始右移直到不满足sum>=目标值，再接着右边界右移
                    ans=Math.min(ans,end-start+1);
                    sum-=nums[start];
                    start++;
                }
                end++;
            }
            return ans==Integer.MAX_VALUE?0:ans;
        }
    }
}
