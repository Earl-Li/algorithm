package com.atlisheng.leetcode.Q560;

import java.util.HashMap;

public class Q560 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法1：枚举每个元素结尾情况下找到和为目标值对应前置下标的情况
     * @创建日期 2023/07/24
     * @since 1.0.0
     */
    class Solution1 {
        public int subarraySum(int[] nums, int k) {
            int count=0;
            for (int i = 0; i < nums.length; i++) {
                int sum=0;
                for (int j = i; j >=0 ; j--) {
                    sum+=nums[j];
                    if (sum==k){
                        count++;
                    }
                }
            }
            return count;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法二：前缀和+哈希表优化：对于每个元素都计算前缀和，该前缀和会在最后放入HashMap，放入的方式是如果前缀和已经存在就在原数上加1，否则以数量1放入哈希表
     * ，在放入当前前缀和之前先利用当前前缀和计算出前缀和为当前前缀和减去目标值是否匹配之前的前缀和并查出个数，即两个前缀和之间的连续数组的和都是目标值
     * @创建日期 2023/07/24
     * @since 1.0.0
     */
    class Solution2{
        public int subarraySum(int[] nums, int k) {
            int count =0,pre=0;
            HashMap<Integer,Integer> mp=new HashMap<>();
            mp.put(0,1);//这个必须放作为从0开始的连续数组计数的依赖
            for (int i = 0; i < nums.length; i++) {
                pre+=nums[i];//pre为i的前缀和，
                if (mp.containsKey(pre-k)){//如果mp中含pre-k的前缀和
                    count+=mp.get(pre-k);//获取该前缀和pre-k的数量加到count上
                }
                mp.put(pre,mp.getOrDefault(pre,0)+1);//mp.getOrDefault是获取指定key对应的value，如果找不到key就返回用户设置的默认值0
            }
            return count;
        }
    }
}
