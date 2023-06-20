package com.atlisheng.leetcode.Q070;

public class Q070 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        //Thread.sleep(1000);
        int methods = new Solution2().climbStairs(45);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)+" | "+methods);
        System.out.println(2|4);//6全部转成二进制按二进制位进行或运算得对应二进制位再转换成十进制
    }
}
class Solution{//ME:37.8MB T:0ms
    public int climbStairs(int count) {
        int[] stairCount=new int[count+1];
        return climbCurrentStairs(count,stairCount);

    }
    public int climbCurrentStairs(int count,int[] stairCount){
        if (stairCount[count]>0) {
            return stairCount[count];
        }
        if (count==1){
            return 1;
        }
        if (count==2){
            return 2;
        }
        return stairCount[count]=climbCurrentStairs(count-1,stairCount)+climbCurrentStairs(count-2,stairCount);
    }
}
class Solution2{//ME:38.1MB T:0ms
    private int[] stairCount;
    public int climbStairs(int count) {
        this.stairCount=new int[count+1];
        return climbCurrentStairs(count);
    }
    public int climbCurrentStairs(int count){
        if (stairCount[count]>0) {
            return stairCount[count];
        }
        if (count==1){
            return 1;
        }
        if (count==2){
            return 2;
        }
        return stairCount[count]=climbCurrentStairs(count-1)+climbCurrentStairs(count-2);
    }
}
class Solution3{//ME:38MB T:0ms
    public int climbStairs(int count) {
        if (count==1){
            return 1;
        }
        int[] dp=new int[count+1];
        dp[1]=1;
        dp[2]=2;
        for (int i=3;i<=count;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[count];
    }
}
