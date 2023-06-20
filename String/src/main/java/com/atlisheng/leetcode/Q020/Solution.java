package com.atlisheng.leetcode.Q020;

import java.util.Stack;

/*
public class Solution {
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            switch (chars[i]) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                default:
                    if (stack.isEmpty() || chars[i] != stack.pop()) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }
}
*/
public class Solution {
    public boolean isValid(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }

        return s.length() == 0;
    }
}