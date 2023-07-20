package com.atlisheng.algor.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 并查集结构【左神版本】
 * @创建日期 2023/07/20
 * @since 1.0.0
 */
public class UnionFind {
    public static class Element<V>{//这是组织数据的单个元素
        public V value;
        public Element(V value){
            this.value=value;
        }
    }
    public static class UnionFindSet<V>{
        public HashMap<V,Element<V>> elementMap;//通过value可以找到对应的Element
        public HashMap<Element<V>,Element<V>> fatherMap;//通过一个节点可以找到对应的根节点
        public HashMap<Element<V>,Integer> rankMap;//通过节点获取对应集合的节点个数,可以用来在合并过程判断两并查集的哪个根节点作为根节点

        public UnionFindSet(List<V> list){
            elementMap=new HashMap<>();
            fatherMap=new HashMap<>();
            rankMap=new HashMap<>();
            for (V value:list) {
                Element<V> element=new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);//初始化父节点就是自己，即没合并前每个节点都是一个并查集
                rankMap.put(element,1);//element所在并查集的节点个数初始化为自身即1
            }
        }

        /**
         * @param element 元素
         * @return {@link Element }<{@link V }>
         * @描述 找到根节点，找到过程缓存路径节点信息，找到根节点后将所有的路径节点的头节点全部重置为根节点，下次在找就无需再次遍历路径
         * @author Earl
         * @version 1.0.0
         * @创建日期 2023/07/20
         * @since 1.0.0
         */
        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> path=new Stack<>();
            while(element !=fatherMap.get(element)){//element从fatherMap中获取的父节点不是自己，说明当前节点不是根节点，将该节点入栈；当前节点变成其父节点继续进行上述判断，直到找到根节点
                path.push(element);
                element=fatherMap.get(element);
            }
            while(!path.isEmpty()){
                fatherMap.put(path.pop(),element);//弹栈，将element节点向上的所有路径节点全部重置为头节点
            }
            return element;//返回根节点
        }

        /**
         * @param value1 value1
         * @param value2 value2
         * @return boolean
         * @描述 isSameSet方法根据数据而非节点判断是否同一集合，判断是否同一集合就判断两根节点是否同一个
         * 首先要有这两个数据对应的节点，没有直接返回false,找父节点的过程路径节点会被优化为直接指向根节点
         * @author Earl
         * @version 1.0.0
         * @创建日期 2023/07/20
         * @since 1.0.0
         */
        public boolean isSameSet(V value1,V value2){
            if (elementMap.containsKey(value1) && elementMap.containsKey(value2)){
                return findHead(elementMap.get(value1))== findHead(elementMap.get(value2));
            }
            return false;
        }

        /**
         * @param value1 value1
         * @param value2 value2
         * @描述 合并两并查集，核心是将
         * @author Earl
         * @version 1.0.0
         * @创建日期 2023/07/20
         * @since 1.0.0
         */
        public void union(V value1,V value2){
            if (elementMap.containsKey(value1) && elementMap.containsKey(value2)){
                Element<V> value1Head = findHead(elementMap.get(elementMap.get(value1)));
                Element<V> value2Head = findHead(elementMap.get(elementMap.get(value2)));
                if (value1Head != value2Head){
                    Element<V> big=rankMap.get(value1Head) >= rankMap.get(value2Head)?value1Head:value2Head;//当两个集合的数量相同，合并的根节点取决于合并时节点的前后顺序，在前即作为新的根节点
                    Element<V> small=value1Head==big?value2Head:value1Head;
                    //合并两集合的操作
                    fatherMap.put(small,big);//数量小的集合的根节点由自己指向big，这一步所有small集合的节点的根节点都指向了big.只是修改fatherMap是在下一次判断对应两个节点是否在同一个集合中
                    rankMap.put(big,rankMap.get(big)+rankMap.get(small));//将两个集合的数量合并到大集合的根节点上，rankMap移除掉小根节点
                    rankMap.remove(small);
                }
            }
        }
    }
}
