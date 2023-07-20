package com.atlisheng.leetcode.utils;

public class MatrixUtils {
    private MatrixUtils(){

    }

    /**
     * @param matrix 矩阵
     * @描述 整形矩阵遍历
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/07/20
     * @since 1.0.0
     */
    public static void showAll(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    /**
     * @param matrix 矩阵
     * @描述 字符形矩阵遍历
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/07/20
     * @since 1.0.0
     */
    public static void showAll(char[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+"\\t");
            }
            System.out.println();
        }
    }
}
