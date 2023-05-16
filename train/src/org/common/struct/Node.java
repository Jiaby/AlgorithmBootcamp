package org.common.struct;

import java.util.List;

/**
 * @author liuyue
 * @Description: N叉树结点
 * @date 2021/12/5 20:28
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }

    public Node() {
    }
}
