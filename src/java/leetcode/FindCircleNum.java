package leetcode;

import java.util.HashMap;
import java.util.Map;

// 547. 省份数量
// https://leetcode-cn.com/problems/number-of-provinces/
public class FindCircleNum {

    /*
    并查集典型例题:
    目的是求连通分量的个数
    1.构造并查集基本数据结构UnionFind{}:
    成员变量:Map<Integer, Integer>father保存每一个节点的父亲;numsOfProvince保存连通域数量
    方法:add()添加节点;merge()合并节点;findAnc()寻找祖宗节点;isConnected()判断是两个节点是否否连通
        getProvinces()获取连通域个数
    2.遍历邻接矩阵,因为1与2的连接关系必定和2与1的一致,因此只遍历左下角的矩阵即可;
    而对角线的必定为1(自己与自己必定是连通的);
    每遍历一行,就把i加入到uf,然后看看[0,i-1]之中哪个和i连通的,有则合并;没有则跳过
    3.最后返回连通域个数就是省份数量
    */
    public int findCircleNum(int[][] isConnected) {
        // 创建并查集
        UnionFind uf = new UnionFind();
        int n = isConnected.length;
        // 遍历左下角矩阵
        for (int i = 0; i < n; i++) {
            // 先将city[i]加入uf
            uf.add(i);
            // 再遍历city[0,i-1]看看哪个与city[i]相连的
            for (int j = 0; j < i; j++) {
                // 若矩阵显示city[i]与city[j]相连
                if (isConnected[i][j] == 1) {
                    // 在uf中将他们合并
                    uf.merge(i, j);
                }
            }
        }
        // 最后返回uf中连通域的数目就是省份数量
        return uf.getProvinces();
    }

    /*
    并查集基本数据
    */
    class UnionFind {
        // 保存每个节点的父亲
        Map<Integer, Integer> father;
        // 连通域数量
        int numsOfProvince;

        // 构造器
        public UnionFind() {
            this.father = new HashMap<>();
            this.numsOfProvince = 0;
        }

        // 添加节点进uf
        public void add(int x) {
            // 若不含某个节点,则加入并设置父亲为空(孤立状态)
            // 若含有就不进行操作了
            if (!father.containsKey(x)) {
                father.put(x, null);
                // 连通域数量+1
                numsOfProvince++;
            }
        }

        // 连接x与y两个节点
        public void merge(int x, int y) {
            // 先找出他们的祖先节点
            int rootX = findAnc(x);
            int rootY = findAnc(y);
            // 判断是否为同一个祖先
            // 若不是同一个祖先,则要将他们祖先合并,从而使得x与y连通
            if (rootX != rootY) {
                father.put(rootX, rootY);
                // 连通域数量-1
                numsOfProvince--;
            }
        }

        // 查找x的祖先节点
        public int findAnc(int x) {
            // 这里最简单的可以一直通过迭代进行查找(但是当树高h很大时效率会极低)
            // 因此采用路径压缩算法
            // 1.先找到x的祖先节点
            int rootX = x;
            // 说明x有父亲节点
            while (father.get(rootX) != null) {
                // 向上移动
                rootX = father.get(rootX);
            }
            // 2.将每一个不是rootX的子节点全部直接挂到rootX
            while (father.get(x) != null) {
                // 保留旧的父亲节点
                int old_father = father.get(x);
                // 将x挂到rootX下
                father.put(x, rootX);
                // x向上移动
                x = old_father;
            }
            // 返回祖先节点
            return rootX;
        }

        // 判断两个节点是否连通:判断祖先节点是否相同即可
        public boolean isConnected(int x, int y) {
            return findAnc(x) == findAnc(y);
        }

        // 获取连通域数量
        public int getProvinces() {
            return numsOfProvince;
        }
    }
}
