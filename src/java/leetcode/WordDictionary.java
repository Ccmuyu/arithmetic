package leetcode;

// 211. 添加与搜索单词 - 数据结构设计
// https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
public class WordDictionary {

    Tree root;

    public WordDictionary() {
        root = new Tree();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    // index:树的深度
    private boolean dfs(String word, int index, Tree node) {
        if (index == word.length()) {
            // 当长度相同时，直接判断单词是否存在
            return node.end;
        }
        char c = word.charAt(index);
        if (Character.isLetter(c)) {
            //如果当前字符是字母，则判断当前字符对应的子结点是否存在，
            // 如果子结点存在则移动到子结点，继续搜索下一个字符，
            // 如果子结点不存在则说明单词不存在，返回 false；
            int childIndex = c - 'a';
            if (node.child[childIndex] != null && dfs(word, index + 1, node.child[childIndex])) {
                return true;
            }
        } else {
            //如果当前字符是点号，由于点号可以表示任何字母，因此需要对当前结点的所有非空子结点继续搜索下一个字符。
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null && dfs(word, index + 1, node.child[i])) {
                    return true;
                }
            }
        }
        return false;
    }


    class Tree {
        Tree[] child;
        boolean end;

        public Tree() {
            this.child = new Tree[26];
            this.end = false;
        }

        public void insert(String word) {
            Tree root = this;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                int index = c - 'a';
                Tree tree = root.child[index];
                if (tree == null) {
                    tree = new Tree();
                }
                root = tree;
            }
            root.end = true;
        }

        public Tree search(String word) {
            Tree root = this;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                int index = c - 'a';
                if (root.child[index] == null) {
                    return null;
                }
                root = root.child[index];
            }
            return root;
        }
    }
}


