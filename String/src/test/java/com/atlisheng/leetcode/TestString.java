package com.atlisheng.leetcode;

import com.atlisheng.leetcode.Q005.Solution;
import com.atlisheng.leetcode.Q005.Solution3;
import com.atlisheng.leetcode.Q093.Solution2;
import org.junit.Test;


public class TestString {
    @Test
    public void testQ005(){
        Solution solution=new Solution();
        long startTime = System.currentTimeMillis();
        String longestPalindrome = solution.longestPalindrome("sdjkakjdfhskflhasdlkjfhsdjkfasdlkfasd;jfalskflasdfjasdlkfhsdak");
        long endTime=System.currentTimeMillis();
        System.out.println(longestPalindrome);
        System.out.println("耗时:"+(endTime-startTime));


    }
    @Test
    public void testQ005_1(){
        Solution3 solution3=new Solution3();
        System.out.println(solution3.longestPalindrome("babad"));
    }
    @Test
    public void testQ020(){
        com.atlisheng.leetcode.Q020.Solution  solution=new com.atlisheng.leetcode.Q020.Solution();
        System.out.println(solution.isValid("[({])}"));
    }

    @Test
    public  void testQ043(){
        com.atlisheng.leetcode.Q043.Solution solution=new com.atlisheng.leetcode.Q043.Solution();
        System.out.println(solution.multiply("9133", "0"));
    }

    @Test
    public void testQ093(){
        com.atlisheng.leetcode.Q093.Solution solution=new com.atlisheng.leetcode.Q093.Solution();
        System.out.println(solution.restoreIpAddresses("25525511135"));
    }

    @Test
    public void testQ093_1(){
        Solution2 solution=new Solution2();
        System.out.println(solution.restoreIpAddresses("010010"));
    }
}
