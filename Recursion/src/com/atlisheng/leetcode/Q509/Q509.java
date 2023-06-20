package com.atlisheng.leetcode.Q509;

public class Q509 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        //int value = new Solution().getValue(30);
        int value = new Solution1().fib(30);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " | " +value);

    }
}
//递归
class Solution {//ME:38.1MB T:8ms
    public int getValue(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        return getValue(n-1)+getValue(n-2);//这个为什么这么慢
    }
}
//动态规划
class Solution1 {//ME:38.1MB T:0ms
    public int fib(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int pre=0,head=1,temp=0;
        for(int i=2;i<=n;i++){
            temp=head;
            head+=pre;
            pre=temp;
        }
        return head;
    }
}

