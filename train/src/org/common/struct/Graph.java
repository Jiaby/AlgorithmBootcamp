package org.common.struct;

import java.util.*;

public class Graph<T> {
    // 图中顶点的个数
    private int v;
    // 图中顶点集合
    private Set<T> nodes;
    // 边的集合
    private HashMap<T, LinkedList<T>> edges;

    public Graph(int v) {
        this.v = v;
        nodes = new HashSet<>(v);
        edges = new HashMap<>(v);
    }

    /**
     * 无向图加边
     */
    public void addEdgeUndirected(T s, T t) {
        LinkedList<T> sl = edges.get(s);
        if (sl == null) {
            sl = new LinkedList<>();
            edges.put(s, sl);
        }
        if (!sl.contains(t)) {
            sl.add(t);
        }
        LinkedList<T> tl = edges.get(t);
        if (tl == null) {
            tl = new LinkedList<>();
            edges.put(t, tl);
        }
        if(!tl.contains(s)) {
            tl.add(s);
        }

    }

    public void addNode(T n) {
        if (n == null) {
            throw new IllegalArgumentException("nodes is Empty");
        }
        nodes.add(n);
        v ++;
    }

    public void addNodes(Collection<T> nodes) {
        if (nodes == null || nodes.size() == 0) {
            throw new IllegalArgumentException("nodes is Empty");
        }
        this.nodes.addAll(nodes);
        v += nodes.size();
    }

    /**
     * 有向图存储 s-> t
     */
    public void addEdgeDirected(T s, T t) {
        LinkedList<T> sl = edges.get(s);
        if (sl == null) {
            sl = new LinkedList<>();
            edges.put(s, sl);
        }
        if (!sl.contains(t)) {
            sl.add(t);
        }
    }

    public List<T> findPath(T s, T e) {
        Deque<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        HashMap<T, T> prev = new HashMap<>();
        queue.offer(s);
        visited.add(s);
        w: while (!queue.isEmpty()) {
            T p = queue.poll();
            LinkedList<T> edges = this.edges.getOrDefault(p, new LinkedList<>());
            for (T q : edges) {
                if (!visited.contains(q)) {
                    prev.put(q, p);
                    if (q == e) {
                        break w;
                    }
                    visited.add(q);
                    queue.add(q);
                }
            }
        }
        LinkedList<T> rs = new LinkedList<>();
        T node = e;
        while (node != null) {
            rs.addFirst(node);
            node = prev.get(node);
        }
        return rs;
    }


    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(7);
        graph.addNodes(Arrays.asList(1,2,3,4,5,6,7));
        graph.addEdgeDirected(1,2);
        graph.addEdgeDirected(1,3);
        graph.addEdgeDirected(2,3);
        graph.addEdgeDirected(2,4);
        graph.addEdgeDirected(4,6);
        graph.addEdgeDirected(3,5);
        graph.addEdgeDirected(5,6);
        graph.addEdgeDirected(5,7);
        graph.addEdgeDirected(6,7);
        List<Integer> path = graph.findPath(1, 7);
        System.out.println(path);
    }


}
