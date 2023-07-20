package com.atlisheng.leetcode.Q240;

public class Q240 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：遍历二维矩阵，查找某个数
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution1{
        public boolean searchMatrix(int[][] matrix,int target){
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j]==target){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法2：二分查找矩阵每一行
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution2{
        public boolean searchMatrix(int[][] matrix,int target){
            for (int i = 0; i < matrix.length; i++) {
                if (searchArray(matrix[i],0,matrix[i].length-1,target)){
                    return true;
                }
            }
            return false;
        }
        public boolean searchArray(int[] array,int left,int right,int target){
            if (left>right){
                return false;
            }
            int mid=((right-left)>>1)+left;
            if (array[mid]==target){
                return true;
            }
            if (left==right){
                return false;
            }
            return array[mid]<target?searchArray(array,mid+1,right,target):searchArray(array,left,mid,target);
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 Z字形查找,取（x,y）为矩阵右上角，如果(x,y)=target,直接返回true,如果(x,y)>target,则讨论(x,y-1);如果(x,y)<target,则讨论（x+1,y);直到x达到m或者y达到0
     * @创建日期 2023/07/15
     * @since 1.0.0
     */
    class Solution3{
        public boolean searchMatrix(int[][] matrix,int target){
            int x=matrix.length-1,y=0;
            while (x>=0 && y<matrix[0].length) {
                int candidate = matrix[x][y];
                if (candidate == target) {
                    return true;
                }else if (candidate > target) {
                    x--;
                }else{
                    y++;
                }
            }
            return false;
        }
    }
}
