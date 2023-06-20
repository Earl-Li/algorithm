package com.atlisheng.leetcode.Q093;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result=new ArrayList<>();
        for (int i = 1;i<4;i++){
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 4; k++) {
                    for (int l = 1; l < 4; l++) {
                        if (i+j+k+l==s.length()){
                            String str1=s.substring(0,i);
                            String str2=s.substring(i,i+j);
                            String str3=s.substring(i+j,i+j+k);
                            String str4=s.substring(i+j+k);
                            if (isValid(str1) && isValid(str2) && isValid(str3) && isValid(str4)){
                                result.add(str1+"."+str2+"."+str3+"."+str4);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    public static boolean isValid(String s){
        if (s.charAt(0)=='0' && s.length()!=1)return false;
        int i = Integer.parseInt(s);
        if(i>=0 && i<=255 ) return true;
        return false;
    }
}
