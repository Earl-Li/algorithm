package com.atlisheng.leetcode.Q005;

public class Solution2 {
    /**
     * @param s 年代
     * @return {@link String }
     * @描述 中心扩散法，核心是每次循环都从左到右依次选取字符并以该字符为中心判断最大偶数回文结构长度或者奇数回文结构
     * 最大长度谁的长度更大并与现存最大回文结构长度对比是否更新，更新就记录最大回文结构长度和回文结构起始下标，
     * 时间复杂度O(N^2)
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/18
     * @since 1.0.0
     *///中心扩散法
    public String longestPalindrome(String s){
        int len=s.length();
        if (len<2){
            return s;
        }
        int maxLen=1;
        int begin=0;
        char[] chars=s.toCharArray();
        for (int i = 0; i < len-1; i++) {
            int oddLen=expandAroundCenter(chars,i,i);//对于字符串每一个字符都判断以该字符为中心向外扩展的最大奇数回文结构长度
            int evenLen=expandAroundCenter(chars,i,i+1);//对于字符串每一个字符都判断以该字符为中心向外扩展的最大偶数回文结构长度
            int curMaxLen=Math.max(oddLen,evenLen);//在奇数和偶数回文结构长度中选择一个最大值
            if (curMaxLen>maxLen){//如果当前字符的回文结构最大值是整个字符串的回文结构最大值，更新该最大值
                maxLen=curMaxLen;
                begin=i-(maxLen-1)/2;//开始下标，(maxLen-1)/2始终等于不包括中点的右半边长度，不论maxLen为奇数还是偶数
            }
        }
        return s.substring(begin,begin+maxLen);
    }

    /**
     * @return 返回回文结构最大长度
     * @描述 传入left和right，由两边界比较是否相等，相等向外扩展一圈判断字符是否相等，
     * 直到两边字符不相等或者超过字符串左边界或者右边界，返回以left和right为内边界的最大回文结构长度
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/06/18
     * @since 1.0.0
     */
    public int expandAroundCenter(char[] chars,int left,int right){
        int len=chars.length;
        int i=left;
        int j=right;
        while(i>=0&&j<len){//当左边界大于等于0且右边界小于字符串长度就一直循环
            if (chars[i]==chars[j]){//如果左边界字符等于有边界字符，向外扩大范围继续比较，直到左边界小于0或者有边界下标等于字符串长度
                i--;
                j++;
            }else {//不等就直接跳出循环
                break;
            }
        }
        return j-i-1;//返回回文长度，因为不论是左右边界字符不等还是字符一边越界，都多计算了一次i--或者j++，所以回文长度应该为j-i+1-2即j-1-1
    }
}
