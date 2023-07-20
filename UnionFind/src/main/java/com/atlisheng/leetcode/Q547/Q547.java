package com.atlisheng.leetcode.Q547;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Q547 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：并查集
     * @创建日期 2023/07/20
     * @since 1.0.0
     */
    class Solution1{
        public int findCircleNum(int[][] isConnected) {
            int cityCount=isConnected.length,ret=0;
            int[] parent=new int[cityCount];
            for (int i = 0; i < cityCount; i++) {
                parent[i]=i;
            }
            for (int i = 1; i < cityCount; i++) {
                for (int j = 0; j < i; j++) {
                    if (isConnected[i][j]==1){
                        if (findHead(parent,i)!=findHead(parent,j)) {
                            union(parent,parent[i],parent[j]);
                        }
                    }
                }
            }
            for (int i = 0; i < cityCount; i++) {
                if (parent[i]==i){
                    ret++;
                }
            }
            return ret;
        }
        public int findHead(int[] arr,int i){
            Deque<Integer> path=new LinkedList();
            while(arr[i]!=i){
                path.push(i);
                i=arr[i];
            }
            while(!path.isEmpty()){
                arr[path.pop()]=i;
            }
            return i;
        }
        public void union(int[] arr,int i,int j){
            if (i<=j){
                arr[j]=i;
            }else {
                arr[i]=j;
            }
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 官方并查集，省略再连接过程和数值小的城市在前
     * @创建日期 2023/07/21
     * @since 1.0.0
     */
    class Solution2{
        public int findCircleNum(int[][] isConnected) {
            int cityCount=isConnected.length,ret=0;
            int[] parent=new int[cityCount];
            for (int i = 0; i < cityCount; i++) {
                parent[i]=i;
            }
            for (int i = 1; i < cityCount; i++) {
                for (int j = 0; j < i; j++) {
                    if (isConnected[i][j]==1){
                        union(parent,i,j);
                    }
                }
            }
            for (int i = 0; i < cityCount; i++) {
                if (parent[i]==i){
                    ret++;
                }
            }
            return ret;
        }
        public int findHead(int[] arr,int i){
            while(arr[i]!=i){
                i=arr[i];
            }
            return i;
        }
        public void union(int[] arr,int i,int j){
            arr[findHead(arr,i)]=findHead(arr,j);
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 官方深度优先搜索，dfs，如果一个城市从来没被访问过，就对该城市进行深度优先搜索，找出所有与其相连的城市并对这些城市标记为访问过，每有一个未被访问过的城市就表示有一个省
     * @创建日期 2023/07/21
     * @since 1.0.0
     */
    class Solution3{
        public int findCircleNum(int[][] isConnected) {
            int cityCount=isConnected.length;
            boolean[] visited=new boolean[cityCount];
            int provinces=0;
            for (int i = 0; i < cityCount; i++) {
                if (!visited[i]){
                    dfs(isConnected,visited,cityCount,i);//对没访问过的城市都进行深度优先遍历，对该城市连接的城市都标记访问过
                    provinces++;//一个城市沒有被访问过就进行深度优先遍历，直接一个省上的所有城市都被标记过visited
                }
            }
            return provinces;
        }
        public void dfs(int[][] isConnected,boolean[] visited,int cityCount,int i){
            for (int j = 0; j < cityCount; j++) {
                if (isConnected[i][j]==1 && !visited[i]){//visited没有被访问过且与城市i相连才对城市i进行深度优先遍历，相连但是被访问过说明在之前访问的时候已经进行了深度优先遍历，这中情况已经被讨论过了
                    visited[j]=true;
                    dfs(isConnected,visited,cityCount,j);
                }
            }
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法4：广度优先搜索，wfs：
     * @创建日期 2023/07/21
     * @since 1.0.0
     */
    class Solution4{
        public int findCircleNum(int[][] isConnected) {
            int cityCount=isConnected.length;
            boolean[] visited=new boolean[cityCount];
            int provinces=0;
            Queue<Integer> queue=new LinkedList<>();
            for (int i = 0; i < cityCount; i++) {
                if (!visited[i]){
                    queue.offer(i);//如果没访问过该城市，queue添加该城市，这是从第一个城市开始的，第一个城市加入后马上就移除，然后将所有与第一个城市相连的城市如队列再对每个入队列且没有被访问过的城市进行直连城市遍历入队列，直到一个省的所有城市被访问过，省数量加1
                    while(!queue.isEmpty()){
                        int j=queue.poll();//获取并移除队列的头,移除后遍历所有与被移除城市直接相连的城市并加入队列
                        visited[j]=true;
                        for (int k = 0; k < cityCount; k++) {
                            if (isConnected[j][k]==1 && !visited[k]){
                                queue.offer(k);
                            }
                        }
                    }
                }
                provinces++;
            }
        return provinces;
        }
    }
}
