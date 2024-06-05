package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BreadFirstSearchTests {

    @Test
    public void testBFS() {
        assertThat(bfs(4, 2, List.of(List.of(1, 2), List.of(2, 3)), 1)).isEqualTo(List.of(6, 12, -1));
        assertThat(bfs(5, 3, List.of(List.of(1, 2), List.of(1, 3), List.of(3, 4)), 1)).isEqualTo(List.of(6, 6, 12, -1));
    }

    static class Graph {
        Map<Integer, List<Integer>> graph;

        public Graph() {
            graph = new HashMap<>();
        }

        public void addAll(List<List<Integer>> edges) {
            for (List<Integer> edge : edges) {
                int from = edge.get(0);
                int to = edge.get(1);
                addEdge(from, to);
                addEdge(to, from);
            }
        }

        public void addEdge(int from, int to) {
            List<Integer> edges = graph.getOrDefault(from, new ArrayList<>());
            edges.add(to);
            graph.put(from, edges);
        }

        public List<Integer> nextOf(int node) {
            return graph.getOrDefault(node, Collections.emptyList());
        }
    }

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        Graph graph = new Graph();
        graph.addAll(edges);
        // queue for bread first search (change to stack it'll be depth first search
        Deque<Integer> current = new ArrayDeque<>();
        // to find out those node not reachable from starting node
        BitSet found = new BitSet(n + 1);
        // remember how many steps away from starting node
        TreeMap<Integer, Integer> steps = new TreeMap<>();
        current.offer(s);
        found.set(s);
        steps.put(s, 0);
        while (!current.isEmpty()) {
            int node = current.remove();
            int step = steps.get(node) + 1;
            for (int next : graph.nextOf(node)) {
                if (!found.get(next)) {
                    found.set(next);
                    current.offer(next);
                    steps.put(next, step);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i == s) continue;
            if (found.get(i)) {
                result.add(steps.get(i) * 6);
            } else {
                result.add(-1);
            }
        }

        return result;
    }
}
