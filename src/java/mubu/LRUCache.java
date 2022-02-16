package mubu;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多种方案：1、使用链表（LinkedHashMap），但是失去了题目的意义
 * 2、思路：
 * Ⅰ、节点内容：key、value、pre、after <br>
 * Ⅱ、新增节点：新节点：node添加至header后面，header的下一个为：node  <br>
 * Ⅲ、删除节点：缓存pre、after，并更新pre和after的前后指针  <br>
 * Ⅳ、GET节点：map.get(key)之后，移动节点（新增+删除）至header后 <br>
 * Ⅴ、PUT节点：
 *      <li>
 *          存在：<br> 走GET流程
 *      </li>
 *      <li>
 *          不存在： <br>
 *              更新header的指针，count+1，如果超出容量则删除tail节点
 *      </li>
 */
public class LRUCache {

    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node) {

        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node) {
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }

    private Hashtable<Integer, DLinkedNode>
            cache = new Hashtable<Integer, DLinkedNode>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.post = null;

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {

        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1; // should raise exception here.
        }

        // move the accessed node to the head;
        this.moveToHead(node);

        return node.value;
    }


    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {

            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if (count > capacity) {
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }


    // 使用LinkedHashMap
    class SimpleLRU {
        private Map<Integer, Integer> map;

        public SimpleLRU(int capacity) {
            map = new LinkedCappedHashMap<>(capacity);
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void set(int key, int value) {
            map.put(key, value);
        }

        private class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {

            int maximumCapacity;

            LinkedCappedHashMap(int maximumCapacity) {
                super(16, 0.75f, true);
                this.maximumCapacity = maximumCapacity;
            }

            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maximumCapacity;
            }
        }
    }
}
