package com.atlisheng.leetcode.Q093;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    List<String> result=new ArrayList<>();
    public List<String> restoreIpAddresses(String s){
        List<String> strs=new ArrayList<>();
        getStr(0,s,strs);
        return result;
    }
    public void getStr(int startIndex,String s,List strs){
        if (strs.size()>4){//如果分出的字符大于了4个证明之前的分割都失效，直接返回
            return;
        }
        if (startIndex==s.length() && strs.size()==4){//如果找到了四个合适的字符串且刚好长度和为字符串的和就将本次结果拼接并加入result中
            result.add(montageStr(strs));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);//精髓啊，i从0开始，startIndex+i会越界
            if (isValid(str)){
                strs.add(str);
                getStr(i+1,s,strs);
                strs.remove(strs.size()-1);//这个地方不能直接按字符串删除，不会删最后的，只会删
            }
        }
    }

    public String montageStr(List<String> strs){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < strs.size(); i++) {
            sb.append(strs.get(i));
            if (i!=strs.size()-1) sb.append(".");
        }
        return sb.toString();
    }

    public boolean isValid(String s){
        if (s.length()==1) return true;
        if (s.charAt(0)=='0') return false;
        if (s.length()>3) return false;//也精髓啊，如果字符串数值大于21亿，下面的转成整形也会抛异常
        if (Integer.parseInt(s)>255) return false;
        return true;
    }
}
