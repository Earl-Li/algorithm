package com.atlisheng.leetcode.Q023;

import java.util.PriorityQueue;

public class Q023 {
    /**
     * @param a 一个
     * @param b b
     * @return {@link ListNode }
     * @描述 合并两个有序链表的一般步骤，时间复杂度O(N),空间复杂度O(1)，比较两链表剩余部分头部的大小来创建新链表连接
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/07/13
     * @since 1.0.0
     */
    public ListNode mergeTwoLists(ListNode a,ListNode b){
        if(a==null || b==null){
            return a !=null?a:b;
        }
        ListNode head=new ListNode(0);//创建一个值为0的头节点为新链表的头节点，最终返回的是这个节点的下一个节点作为新链表的头节点
        ListNode tail=head,aPtr=a,bPtr=b;//用tail指向新链表的尾部，用aPtr表示以a为头的新链表的引用，用bPtr表示以b为头的新链表的引用
        while(aPtr!=null && bPtr!=null){//当两个链表都还有剩余待添加部分时
            if (aPtr.val<bPtr.val){//如果a链表剩余头部比b链表剩余部分头部小
                tail.next=aPtr;
                aPtr=aPtr.next;
            }else {//a链表剩余部分头部比b链表剩余部分头部大或者相等的情况
                tail.next=bPtr;
                bPtr=bPtr.next;
            }
            tail=tail.next;
        }
        //运行到这里说明至少有一条链表已经遍历结束,直接让tail.next等于没有遍历完链表剩余部分头部即可，后面部分会自动连上,不可能两个都跳到null。因为两个的右移不是同步的
        tail.next=(aPtr!=null?aPtr:bPtr);
        return head.next;
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：顺序合并，每次将两个链表合并在一起将结果存入ans，第i次循环将第i个链表和ans合并并将答案保存在ans中
     * @创建日期 2023/07/13
     * @since 1.0.0
     */
    class Solution1{
        public ListNode mergeTwoLists(ListNode a,ListNode b){
            if (a==null || b==null){
                return a!=null?a:b;
            }
            ListNode head=new ListNode(0);
            ListNode tail=head,aPtr=a,bPtr=b;
            while (aPtr!=null && bPtr !=null){
                if (aPtr.val<bPtr.val){
                    tail.next=aPtr;
                    aPtr=aPtr.next;
                }else{
                    tail.next=bPtr;
                    bPtr=bPtr.next;
                }
                tail=tail.next;
            }
            tail.next=(aPtr!=null?aPtr:bPtr);
            return head.next;
        }
        public ListNode merge(ListNode[] lists){
            ListNode ans=null;
            for (int i = 0; i < lists.length; i++) {
                ans=mergeTwoLists(ans,lists[i]);
            }
            return ans;
        }

    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法二：分治算法，将合并总链表数组拆分成合并左右两个部分分别合并出来的两个链表，并对左右两边的链表作为新的总链表重复上诉
     * 过程，baseCase是刚好只有一个链表合并，或者两个相邻链表合并；由mid+1导致的l大于r的问题直接返回null会在mergeTwoLists方法中只
     * 返回有值的链表
     * @创建日期 2023/07/13
     * @since 1.0.0
     */
    class Solution2{
        public ListNode mergeTwoLists(ListNode a,ListNode b){
            if (a==null || b==null){
                return a!=null?a:b;
            }
            ListNode head=new ListNode(0);
            ListNode tail=head,aPtr=a,bPtr=b;
            while (aPtr!=null && bPtr !=null){
                if (aPtr.val<bPtr.val){
                    tail.next=aPtr;
                    aPtr=aPtr.next;
                }else{
                    tail.next=bPtr;
                    bPtr=bPtr.next;
                }
                tail=tail.next;
            }
            tail.next=(aPtr!=null?aPtr:bPtr);
            return head.next;
        }
        public ListNode merge(ListNode[] lists,int l,int r){
            if (l==r){
                return lists[l];
            }
            if (l>r){
                return null;
            }
            int mid=(l+r)>>1;
            return mergeTwoLists(merge(lists,l,mid),merge(lists,mid+1,r));
        }
        public ListNode mergeKLists(ListNode[] lists){
            return merge(lists,0,lists.length-1);
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 使用优先级队列合并,优先级队列中只保存每个链表剩余部分第一个，每次弹出各链表剩余部分的头部元素并将后一个元素添加进优先级队列，直至优先级队列最后没有元素
     * @创建日期 2023/07/13
     * @since 1.0.0
     */
    class Solution3{
        class Status implements Comparable<Status>{
            int val;
            ListNode ptr;//这个就是单纯存入链表头节点的，只是val利于小根堆的比较
            Status(int val,ListNode ptr){
                this.val=val;
                this.ptr=ptr;
            }
            @Override
            public int compareTo(Status o) {
                return this.val-o.val;
            }
        }
        PriorityQueue<Status> queue=new PriorityQueue<>();
        public ListNode mergeKLists(ListNode[] lists){
            for (ListNode list:lists) {
                if (list!=null){
                    queue.offer(new Status(list.val,list));
                }
            }
            ListNode head=new ListNode(0);
            ListNode tail=head;
            while (!queue.isEmpty()){
                Status cur=queue.poll();
                tail.next= cur.ptr;
                tail=tail.next;
                if (cur.ptr.next!=null){
                    queue.offer(new Status(cur.ptr.next.val, cur.ptr.next));
                }
            }
            return head.next;
        }
    }
}
