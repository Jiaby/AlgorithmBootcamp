package org.train.secondweek;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> item;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        item = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (item.get(key) == null) return -1;
        int value = item.get(key).value;
        updateIndex(key);
        return value;
    }

    private void updateIndex(int key) {
        Node node = item.get(key);
        // 从当前位置删除
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // 插入头部，head之后
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    public void put(int key, int value) {
        if (item.containsKey(key)) {
            item.get(key).value = value;
            updateIndex(key);
            return;
        }
        // 已满先删除最末尾元素
        if (item.size() == capacity) {
            Node remove = tail.pre;
            Node pre = remove.pre;
            pre.next = tail;
            tail.pre = pre;
            item.remove(remove.key);
        }
        Node cur = new Node();
        cur.value = value;
        cur.key = key;
        cur.next = head.next;
        cur.pre = head;
        head.next.pre = cur;
        head.next = cur;
        item.put(key, cur);
    }

    static class Node {
        int value;
        int key;
        Node next;
        Node pre;
    }

}
