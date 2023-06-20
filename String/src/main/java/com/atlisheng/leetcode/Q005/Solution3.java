package com.atlisheng.leetcode.Q005;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 动态规划解法
 * @创建日期 2023/06/19
 * @since 1.0.0
 */
public class Solution3 {
    public String longestPalindrome(String s){
        int len=s.length();
        if(len<2){
            return s;
        }
        int maxLen=1;
        int begin=0;
        boolean[][] dp=new boolean[len][len];
        char[] chars=s.toCharArray();
        for(int j=0;j<len;j++){
            for(int i=0;i<=j;i++){
                if(chars[i]!=chars[j]){
                    dp[i][j]=false;
                }else{
                    if(j-i<3){
                        dp[i][j]=true;
                    }else{
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if(dp[i][j]==true && j-i+1>maxLen){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[j][i]+" ");
            }
            System.out.println();
        }
        return s.substring(begin,begin+maxLen);
    }
    /*public String longestPalindrome(String s){
        int len =s.length();
        if (len<2){
            return s;
        }
        int maxLen=1;
        int begin=0;

        //boolean数组默认值是false，将对角线上的元素全部改成true，表明单个字符是回文结构
        boolean[][] dp=new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i]=true;
        }

        char[] chars = s.toCharArray();
        for (int j = 1; j < len; j++) {//j=0的左下角即对角线已经是true了
            //dp数组左下角先填
            for (int i = 0; i < j; i++) {
                if (chars[i]!=chars[j]){//如果左边界和右边界字符不等，意味着dp[i][j]即s[i][j]不是回文子串
                    dp[i][j]=false;//dp[i][j]指第i列第j行
                }else{//chars[i]==chars[j]
                    if ((j-i)<3){//当子串的长度小于3说明该子串必为回文子串
                        dp[i][j]=true;
                    }else{
                        dp[i][j]=dp[i+1][j-1];//子串是否回文当边界两边字符相同的情况下只需要判断小一圈的子串是否回文结构，
                        // 关键就是小一圈的子串需要先得出结论，否则这里会得到错误结论，然而每一行依赖于上一行右对角线上的结果，靠近对角线的要么
                        // 左右字符不等为false，否则j-i<3直接为true，所以所有的左下角元素都会有结果
                    }
                }
                //因为j就是右边界，而左边界比有边界小，且能表示所有子串，右上角的dp元素无所谓，实际上都在左下角讨论过了
                //只要dp[i][j]==true成立，就表示子串s[i,j]是回文结构且回文长度大于当前最大回文长度，此时记录回文长度和起始位置
                if (dp[i][j]==true && j-i+1>maxLen){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);

    }*/
}
