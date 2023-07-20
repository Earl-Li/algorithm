package com.atlisheng.algor.utils;

public class MatrixUtils {
    private MatrixUtils(){

    }
    public static void showAll(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]+"\\t");
            }
            System.out.println();
        }
    }
}
