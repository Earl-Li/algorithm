package com.atlisheng.leetcode.Q043;

public class Solution {
    public String multiply(String num1, String num2) {
        if (num1.charAt(0)=='0' || num2.charAt(0)=='0'){
            return "0";
        }
        int len1=num1.length();
        int len2=num2.length();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int[] result=new int[len1+len2];
        for (int i = 0; i <len2; i++) {
            for (int j = 0; j < len1; j++) {
                result[i+j+1]+=(chars1[j]-'0')*(chars2[i]-'0');
            }
        }
        for (int i = result.length-1; i >0; i--) {
            result[i-1]+=result[i]/10;
            result[i]=result[i]%10;
        }
        //StringBuilder构造方法必须传参字符串才能创建初始字符串的builder
        StringBuilder builder=result[0]==0?new StringBuilder():new StringBuilder(String.valueOf(result[0]));
        for (int i = 1; i < result.length; i++) {
            builder.append(result[i]);
        }
        return builder.toString();
    }
}
