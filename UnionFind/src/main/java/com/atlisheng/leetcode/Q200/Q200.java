package com.atlisheng.leetcode.Q200;

import java.util.LinkedList;
import java.util.Queue;

public class Q200 {
    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法一：深度优先搜索,遇到岛且未被访问过的就进行深度优先搜索，并且岛的数量加1
     * @创建日期 2023/07/21
     * @since 1.0.0
     */
    class Solution1{
        public int numIslands(char[][] grid) {
            int islandCount=0;
            if (grid.length==0 || grid ==null){
                return 0;
            }
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j]=='1'){
                        dfs(i,j,grid);
                        islandCount++;
                    }
                }
            }
            return islandCount;
        }
        public void dfs(int i,int j,char[][] grid){
            if (i<0 || i>= grid.length ||j<0 || j>=grid[0].length){
                return;
            }
            if (grid[i][j]=='1'){
                grid[i][j]='2';//访问过的岛都被标记为'2'，'0'的岛都不用管
                dfs(i-1,j,grid);
                dfs(i+1,j,grid);
                dfs(i,j+1,grid);
                dfs(i,j-1,grid);
            }
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法2：宽度优先搜索：遇到岛且未被访问过就进行宽度优先搜索，并且岛的数量加1
     * @创建日期 2023/07/21
     * @since 1.0.0
     */
    class Solution2{
        public int numIslands(char[][] grid){
            int islandCount=0,width=grid.length,arr=grid[0].length;
            if(width==0 || grid==null){
                return 0;
            }
            Queue<Integer> path=new LinkedList<>();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j <arr ; j++) {
                    if (grid[i][j] == '1') {
                        path.offer(i*arr+j);
                        grid[i][j]='2';
                        while(!path.isEmpty()){
                            Integer curIsland = path.poll();//poll队列空返回null，remove抛异常
                            int curI=curIsland/arr,curJ=curIsland%arr;
                            if (curI-1>=0 && grid[curI-1][curJ]=='1'){
                                path.offer((curI-1)*arr+curJ);
                                grid[curI-1][curJ]='2';
                            }
                            if (curI+1<width && grid[curI+1][curJ]=='1'){
                                path.offer((curI+1)*arr+curJ);
                                grid[curI+1][curJ]='2';
                            }
                            if (curJ+1<arr && grid[curI][curJ+1]=='1'){
                                path.offer((curI)*arr+curJ+1);
                                grid[curI][curJ+1]='2';
                            }
                            if (curJ-1>=0 && grid[curI][curJ-1]=='1'){
                                path.offer((curI)*arr+curJ-1);
                                grid[curI][curJ-1]='2';
                            }
                        }
                        islandCount++;
                    }
                }
            }
            return islandCount;
        }
    }

    /**
     * @author Earl
     * @version 1.0.0
     * @描述 方法三：并查集
     * @创建日期 2023/07/21
     * @since 1.0.0
     */
    class Solution3{
        class UnionFind{
            int count;
            int[] parent;
            int[] rank;
            public UnionFind(char[][] grid){
                this.count=0;
                int m= grid.length;
                int n=grid[0].length;
                parent=new int[m*n];
                rank=new int[m*n];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j]=='1'){
                            parent[i*n+j]=i*n+j;//初始化每个元素指向自己
                            ++count;//一共有多少个集合
                        }
                        rank[i*n+j]=0;
                    }
                }
            }
            public int find(int i){//通过数据i找到其父，没有优化查询过程
                if (parent[i]!=i){
                    parent[i]=find(parent[i]);//递归查找直到parent[i]=i
                }
                return parent[i];
            }
            public void union(int x,int y){//合并两个集合
                int rootx=find(x);
                int rooty=find(y);
                if (rootx!=rooty){
                    if (rank[rootx]>rank[rooty]){//有限定
                        parent[rooty]=rootx;
                    }else if(rank[rootx]<rank[rooty]){
                        parent[rootx]=rooty;
                    }else{
                        parent[rootx]=rooty;
                        rank[rootx]+=1;
                    }
                    --count;//集合数量减一
                }
            }
            public int getCount(){
                return count;//获取集合数量
            }
        }
        public int numIslands(char[][] grid){
            if (grid==null || grid.length==0){
                return 0;
            }
            int width= grid.length;;
            int arr=grid[0].length;
            UnionFind uf=new UnionFind(grid);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < arr; j++) {
                    if (grid[i][j]=='1'){
                        grid[i][j]='2';
                        if (i-1>=0 && grid[i-1][j]=='1'){
                            uf.union((i-1)*arr+j,i*arr+j);
                        }
                        if (i+1<width && grid[i+1][j]=='1'){
                            uf.union((i+1)*arr+j,i*arr+j);
                        }
                        if (j-1>=0 && grid[i][j-1]=='1'){
                            uf.union(i*arr+j-1,i*arr+j);
                        }
                        if (j+1<arr && grid[i][j+1]=='1'){
                            uf.union(i*arr+j+1,i*arr+j);
                        }
                    }
                }
            }
            return uf.getCount();
        }
    }
}
