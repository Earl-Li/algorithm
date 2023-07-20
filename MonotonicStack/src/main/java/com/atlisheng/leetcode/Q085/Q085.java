package com.atlisheng.leetcode.Q085;

import com.atlisheng.leetcode.utils.MatrixUtils;

import java.util.Deque;
import java.util.LinkedList;

public class Q085 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：统计矩阵中所有元素左侧连续为1的个数，利用该个数来枚举当前元素作为矩形右下角不同高度下的矩形的面积大小
     * @创建日期 2023/07/20
     * @since 1.0.0
     */
    class Solution1{
        public int maximalRectangle(char[][] matrix){
            int width=matrix.length,row=matrix[0].length,res=0;
            int[][] leftCount=new int[width][row];
            //统计每个元素的左侧连续为1的个数
            for (int i = 0; i < width; ++i) {
                int count=0;//计数器
                for (int j = 0; j <row ; ++j) {
                    leftCount[i][j]=(matrix[i][j]=='1'?++count:(count=0));
                }
            }
            //对每个元素作为矩形左下角枚举任意高度的矩形面积大小
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < row; ++j) {
                    if (leftCount[i][j]==0){
                        continue;
                    }
                    int minWidth=leftCount[i][j];
                    for (int k = 0; k <=i; ++k) {
                        minWidth=Math.min(leftCount[i-k][j],minWidth);
                        res=Math.max(res,minWidth*(k+1));
                    }
                }
            }
            return res;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法2：对于每个元素统计处左侧连续为1的个数，即化简为一系列柱状图，取每个柱状图作为矩形的宽求出对应的最大矩形面积
     * @创建日期 2023/07/20
     * @since 1.0.0
     */
    class Solution2{
        public int maximalRectangle(char[][] matrix){
            int width=matrix.length,arr=matrix[0].length,ret=0;
            if (width==0){
                return 0;
            }
            //对每个元素求左侧连续1的个数
            int[][] leftCount=new int[width][arr];
            for (int i = 0; i < width; i++) {
                int count=0;
                for (int j = 0; j < arr; j++) {
                    leftCount[i][j]=(matrix[i][j]=='1'?++count:(count=0));
                }
            }
            for (int j = 0; j < arr; j++) {//对每一列进行下列操作
                int[] up=new int[width];
                int[] down=new int[width];
                Deque<Integer> stack=new LinkedList<>();
                for (int i = 0; i < width; i++) {
                    while(!stack.isEmpty()&& leftCount[stack.peek()][j]>=leftCount[i][j]){//单调增栈，让当前弹栈的新元素是弹栈元素下侧第一个比其小的
                        stack.pop();
                    }
                    up[i]=stack.isEmpty()?-1:stack.peek();//up数组中存的是当前j列i元素上侧第一个比其小的元素,没有就取成-1
                    stack.push(i);
                }
                stack.clear();
                for (int i = width-1; i >= 0 ; i--) {
                    while(!stack.isEmpty() && leftCount[stack.peek()][j]>=leftCount[i][j]){
                        stack.pop();//如果栈内元素，即下方元素大于等于当前元素就弹出
                    }
                    down[i]=stack.isEmpty() ? width :stack.peek();// down数组中存的是当前j列i行元素下侧第一个比其小的元素
                    stack.push(i);//逻辑是对于当前元素右侧连续1的个数作为宽度，包含当前元素并作为矩形左边界其对应的最大的高度是up[i]-down[i]，由此求出以当前元素作为矩形左边界一部分并以其左侧1个数作为矩形的宽的最大矩形面积
                }
                for (int i = 0; i < width; i++) {
                    ret=Math.max(ret,leftCount[i][j]*(down[i]-up[i]-1));
                }
            }
            return ret;
        }
    }
}
