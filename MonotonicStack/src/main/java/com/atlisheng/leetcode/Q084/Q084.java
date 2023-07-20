package com.atlisheng.leetcode.Q084;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Q084 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：枚举宽的暴力解法,枚举不同左边界，宽度不同情况下的所有矩形面积，选出最大值，java和官方的c++都超时
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution1{
        public int largestRectangleArea(int[] heights) {
            int width=heights.length;//总宽度width
            int ans=0;//答案是面积
            //枚举左边界
            for (int left = 0; left < width; ++left) {
                int minHeight=Integer.MAX_VALUE;
                for (int right = left; right < width; ++right) {
                    minHeight=Math.min(minHeight,heights[right]);
                    ans=Math.max(ans,(right-left+1)*minHeight);
                }
            }
            return ans;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法2：根据下标枚举确定当前高度，由当前竹子向两侧延伸，直达左右遇到小于该高度的柱子，从而确定当前高度下含当前柱子的最大矩形面积
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution2{
        public int largestRectangleArea(int[] heights) {
            int ans=0;
            for (int i = 0; i < heights.length; i++) {
                int height=heights[i],left=i,right=i;
                while(left>=0 && heights[left]>=height){
                    --left;
                }
                while (right<heights.length && heights[right]>=height){
                    ++right;
                }
               ans=Math.max(ans,(right-left-1) *height);
            }
            return ans;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 单调栈:left存对于i的高度，左侧比其小最近的边界；同理right含义是右侧比i高度小的最近的右边界
     * ?一次单调栈能不能搞定?
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution3{
        public int largestRectangleArea(int[] heights){
            int n=heights.length;
            int[] left=new int[n];
            int[] right=new int[n];
            Deque<Integer> mono_stack=new ArrayDeque<>();
            for (int i = 0; i < n; ++i) {
                while(!mono_stack.isEmpty() && heights[mono_stack.peek()]>=heights[i]){
                    mono_stack.pop();
                }
                left[i]=(mono_stack.isEmpty()?-1:mono_stack.peek());//如果单调递增栈为空，-1表示对于i对应元素，左侧没有比其小的高度；
                // 如果不为空，左侧比其小的高度的左边界是单调栈的下一个待弹出的下标，该下标和i之间的所有高度都是比i的高度更大的高度;
                // 栈内现存的下标和i之间一定没有比栈内现存元素更小的，因为如果有会使这些元素之前就被弹栈
                mono_stack.push(i);
            }
            mono_stack.clear();
            for (int i = n-1; i >=0 ; --i) {
                while(!mono_stack.isEmpty() && heights[mono_stack.peek()]>=heights[i]){
                    mono_stack.pop();//用到的是单调递增栈的性质使其弹出的都是左侧或右侧最近比其小的，这样栈中剩下的就是左侧或右侧最近比i小的;
                    // 还有个性质是被弹出下面压着的是其左侧或右侧最近比被弹出的小的
                }
                right[i]=(mono_stack.isEmpty()?n:mono_stack.peek());//这个n和之前的-1就是哨兵，和链表的虚拟头类似
                mono_stack.push(i);
            }
            //对于每个i的高度，相应的面积为
            int ans=0;
            for (int i = 0; i < n; ++i) {
                ans=Math.max(ans,heights[i]*(right[i]-left[i]-1));
            }
            return ans;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 单调栈+常数优化
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution4{
        public int largestRectangleArea(int[] heights){
            int width=heights.length;
            int[] left=new int[width],right=new int[width];
            Arrays.fill(right,width);//将right数组全部填充为width
            Deque<Integer> mono_stack=new ArrayDeque<>();
            for (int i = 0; i < width; ++i) {
                while(!mono_stack.isEmpty() && heights[mono_stack.peek()]>=heights[i]){
                    right[mono_stack.peek()]=i;//使单调栈弹出的是右侧最近比其小的
                    mono_stack.pop();
                }
                left[i]=mono_stack.isEmpty()?-1:mono_stack.peek();
                mono_stack.push(i);
            }
            int ans=0;
            for (int i = 0; i < width; ++i) {
                ans=Math.max(ans,(right[i]-left[i]-1)*heights[i]);
            }
            return ans;
        }
    }


}
