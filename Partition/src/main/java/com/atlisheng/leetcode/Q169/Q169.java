package com.atlisheng.leetcode.Q169;

import java.util.*;

public class Q169 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 用哈希表统计词频，遍历哈希表得词频最大值代价比词频数组小
     * @创建日期 2023/07/14
     * @since 1.0.0
     */
    class Solution {
        public int majorityElement(int[] nums) {
            Map<Integer, Integer> counts = countNums(nums);
            Map.Entry<Integer,Integer> res=null;
            for (Map.Entry<Integer,Integer> entry:counts.entrySet()) {
                if (res==null||entry.getValue()>res.getValue()){
                    res=entry;
                }
            }
            return res.getKey();
        }
        /**
         * @param nums 全国矿工工会
         * @return {@link Map }<{@link Integer },{@link Integer }>
         * @描述 把数组词频统计在HashMap中，代价比遍历词频数组小，单次对HashMap的操作想当于常数操作
         * @author Earl
         * @version 1.0.0
         * @创建日期 2023/07/14
         * @since 1.0.0
         */
        public Map<Integer,Integer> countNums(int[] nums){
            HashMap<Integer, Integer> counts = new HashMap<>();
            for (int num:nums) {
                if (!counts.containsKey(num)){
                    counts.put(num,1);
                }else{
                    counts.put(num,counts.get(num)+1);
                }
            }
            return counts;
        }
    }

    class Solution2{
        public int majorityElement(int[] nums){
            Arrays.sort(nums);
            return nums[nums.length>>1];
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 随机元素法：随机挑出一个元素，判断是不是多数元素，比较用HashMap存储随机元素查询结果和不用情况下的代价
     * @创建日期 2023/07/14
     * @since 1.0.0
     */
    class Solution3{
        HashMap<Integer,Integer> counts=new HashMap<>();
        public int majorityElement(int[] nums){
            Random rand=new Random();
            int majorityCount=nums.length>>1;
            while(true){
                int candidate=nums[rand.nextInt(nums.length)];
                if(count(nums,candidate)>majorityCount){
                    return candidate;
                }
            }
        }
        public int count(int[] nums,int candidate){
            if (counts.containsKey(candidate)){
                return counts.get(candidate);
            }
            int count=0;
            for (int i = 0; i < nums.length ; i++) {
                if (nums[i]==candidate){
                    count++;
                }
            }
            counts.put(candidate,count);
            return count;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 分治算法：大数组的多数元素等于左右两个数组中多数元素在两个部分中个数和更多的那个，baseCase是只有一个元素就返回
     * @创建日期 2023/07/14
     * @since 1.0.0
     */
    class Solution4{
        public int majorityElement(int[] nums){
            return majorityElementRec(nums,0,nums.length-1);
        }
        public int majorityElementRec(int[] nums,int lo,int hi){
            if (lo==hi){
                return nums[lo];
            }
            int mid=((hi-lo)>>1)+lo;
            int left=majorityElementRec(nums, lo, mid);
            int right=majorityElementRec(nums,mid+1,hi);
            if (left==right){
                return left;
            }
            int leftCount=countInRange(nums,left,lo,hi);
            int rightCount=countInRange(nums,right,lo,hi);
            return leftCount>rightCount?left:right;
        }
        public int countInRange(int[] nums,int candidate,int left,int right){
            int count=0;
            for (int i = left; i <= right; i++) {
                if (nums[i]==candidate){
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法5：Boyer-Moore投票算法:让初始人当候选者，有唱票得一分，没唱票扣一分，注意候选者票数为0，当前候选者变成下一次被唱票的人，
     * 而不能是使候选者变成0的人
     * @创建日期 2023/07/14
     * @since 1.0.0
     */
    class Solution5{
        public int majorityElement(int[] nums){
            int count=0;
            int candidate=0;
            for (int i = 0; i < nums.length; i++) {
                if (count==0){
                    candidate=nums[i];
                }
                if (nums[i]==candidate){
                    count++;
                }else{
                    count--;
                }
            }
            return candidate;
        }
    }
}
