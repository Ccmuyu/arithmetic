package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 212. 单词搜索 II
// https://leetcode-cn.com/problems/word-search-ii/
public class FindWords {

    // 上下左右的方向
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        //构建字典树
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                // 遍历一维数组
                dfs(board, trie, i, j, ans);
            }
        }

        // 去重返回
        return new ArrayList<>(ans);
    }

    // 核心点：检查当前坐标位置的字符，是否为当前节点的单词词尾（即word是否存在）
    public void dfs(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        char ch = board[i1][j1]; // 缓存节点值

        if (!now.children.containsKey(board[i1][j1])) {
            return; // 不包含当前节点，直接结束当前路径
        }

        now = now.children.get(ch);
        if (!"".equals(now.word)) { // 说明是单词的结束节点，添加进结果集
            ans.add(now.word);
        }

        board[i1][j1] = '#'; // 临时替换已经访问过的点，防止重复访问
        for (int[] dir : dirs) { // 上下左右搜索一遍
            int i2 = i1 + dir[0], j2 = j1 + dir[1]; //按上下左右移动后的新坐标: i2,j2
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, now, i2, j2, ans);
            }
        }
        board[i1][j1] = ch; // 恢复节点值
    }

    class Trie {
        String word; // 完整word，只有词尾的节点才有值。类似于end标记
        Map<Character, Trie> children; //子节点，如果只有小写字母的情况下，也可简化为数组：Trie[] child;

        public Trie() {
            this.word = "";
            this.children = new HashMap<>();
        }

        public void insert(String word) {
            Trie root = this;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!root.children.containsKey(c)) {
                    root.children.put(c, new Trie());
                }
                root = root.children.get(c);
            }
            root.word = word;
        }

    }

}

