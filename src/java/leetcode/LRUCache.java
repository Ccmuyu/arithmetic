package leetcode;


import java.util.HashMap;
import java.util.Map;

// LRU 最佳solution
// https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
public class LRUCache {

    int size;
    int capacity;
    Map<Integer, LinkNode> cache = new HashMap<>();
    LinkNode head;
    LinkNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new LinkNode();
        this.tail = new LinkNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        // 如果 key 不存在，则返回 -1；
        // 如果 key 存在，则 key 对应的节点是最近被使用的节点。
        // 通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
        LinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void set(int key, int value) {
        LinkNode node = cache.get(key);

        // 如果 key 不存在，使用 key 和 value 创建一个新的节点，
        // 在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，
        // 如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；

        if (node == null) {
            node = new LinkNode(key, value);
            addToHead(node);
            cache.put(key, node);
            size++;
            if (size > capacity) {
                LinkNode oldest = removeTail();
                cache.remove(oldest.key);
                size--;
            }
        } else {
            // 如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，
            // 并将该节点移到双向链表的头部。
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(LinkNode node) {
        // 将当前位置的节点删除
        deleteNode(node);
        // 添加至虚拟head后面
        addToHead(node);
    }

    private void addToHead(LinkNode node) {
        LinkNode next = head.next;

        head.next = node;
        next.pre = node;

        node.pre = head;
        node.next = next;
    }

    private void deleteNode(LinkNode node) {
        LinkNode pre = node.pre;
        LinkNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    private LinkNode removeTail() {
        LinkNode pre = tail.pre;
        deleteNode(pre);
        return pre;
    }
}

class LinkNode {
    int key;
    int value;
    LinkNode pre;
    LinkNode next;

    public LinkNode() {
    }

    public LinkNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
