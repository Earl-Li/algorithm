package com.atlisheng.leetcode.Q005;

public class Solution {
    public String longestPalindrome(String s) {
        int len=s.length();
        //如果输入字符串长度小于2，直接返回整个字符串作为回文串
        if (len<2){
            return s;
        }
        int maxLen=1;//最大回文子串的长度
        int begin=0;//最大回文子串的起始下标
        char[] chars = s.toCharArray();//子串通过记录下标避免多次截取子串降低效率

        //子串长度严格大于二，因为长度为1的子串必为回文字符串，如果没有回文字符串再进行处理
        for (int i = 0; i < len-1; i++) {//子串右边界
            for (int j = i+1; j <len ; j++) {//子串左边界
                if (j-i+1>maxLen && validPalindromic(chars,i,j) ){//如果截取的子串长度大于当前最大回文子串的长度且是回文子串
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);//截取对应下标字符串，起始位置包括，结束下标不包括
    }

    public static boolean validPalindromic(char[] chars,int left,int right){
        while(left<right){
            if (chars[left]!=chars[right]){//左边字符不等于右边字符返回false
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
