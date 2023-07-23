package com.atlisheng.leetcode.Q003;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Q003 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：注意子串是连续的，子序列不要求连续；方法是滑动窗口左边界右移过程中判断右边界右移是否仍然能保持滑动窗口内无重复字符，
     * 用哈希表HashSet记录每个字符是否出现过
     * @创建日期 2023/07/23
     * @since 1.0.0
     */
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> characters = new HashSet<>();
            int width=s.length();
            int rPointer=-1,ans=0;//右指针初始值为-1，相当于在字符串左边界的左侧还没开始移动，左指针的右移用哈希表移除滑动窗口首位元素表示
            for (int i = 0; i < width; i++) {//没有必要把滑动窗口清空
                if (i != 0){//左指针每次都会右移一格位置
                    characters.remove(s.charAt(i-1));//第一次进来是不会移除字符的
                }
                while(rPointer+1<width && !characters.contains(s.charAt(rPointer+1))){
                    characters.add(s.charAt(rPointer+1));//右指针下一位不在哈希表中，就将右指针下一位添加到哈希表中
                    ++rPointer;//不断移动右指针
                }
                //每次左指针右移一位都从滑动窗口中取一个不重复子串的最大长度，注意右指针指向滑动窗口内最后一位，由于总是右指针下一位判断
                // 是否入滑动窗口，所以边界条件是rPointer+1<width
                ans=Math.max(ans,rPointer-i+1);
            }
            return ans;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 改变更新滑动窗口头指针位置的规则保证滑动窗口内字符的不重复性并由遇到重复字符确定直到第一个相同字符的下一个字符之前的所有字符串的长度都小于当前字符串
     * @创建日期 2023/07/23
     * @since 1.0.0
     */
    class Solution2{
        public int lengthOfLongestSubstring(String s) {
            HashMap<Character,Integer> map=new HashMap<>();
            int max=0,start=0;
            for (int end = 0; end < s.length() ; end++) {
                char ch=s.charAt(end);//从字符串中依次循环获取字符
                if (map.containsKey(ch)){//如果HashMap中包含该字符，
                    start=Math.max(map.get(ch)+1,start);//滑动窗口的起始位置直接跳转到相同字符的下一个位置，这样保证了滑动窗口内必然没有重复的字符，又因为
                    //出现相同字符就说明当前最大长度已经被统计过了，直到从前一个相同字符的后一个开始统计前都不会有比当前字符串长度更长的字符
                }
                max=Math.max(max,end-start+1);//重新在添加字符后统计最长长度，实际上只需要管理尾指针即可
                map.put(ch,end);
            }
            return max;
        }
    }
}
