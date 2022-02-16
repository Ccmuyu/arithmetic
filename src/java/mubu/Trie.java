package mubu;

// 前缀树（字典树）
public class Trie {

    private Trie[] children;
    private boolean end;

    public Trie() {
        children = new Trie[26];
        end = false;
    }

    public void insert(String word) {
        //我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
        //
        //子节点存在。沿着指针移动到子节点，继续处理下一个字符。
        //子节点不存在。创建一个新的子节点，记录在 \textit{children}children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
        //重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。
        //
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            int index = c - 'a';
            if (root.children[index] == null) {
                root.children[index] = new Trie();
            }
            root = root.children[index];
        }
        root.end = true;
    }

    private Trie searchPrefix(String prefix) {
        Trie root = this;
        char[] chars = prefix.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (root.children[index] == null) {
                return null;
            }
            root = root.children[index];
        }
        return root;
    }

    public boolean search(String word) {
        Trie trie1 = searchPrefix(word);
        return trie1 != null && trie1.end;
    }

    public boolean startsWith(String prefix) {
        Trie trie1 = searchPrefix(prefix);
        return trie1 != null;
    }
}
