package com.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */
public class WordLadder {
    // First version, this solution was accepted, but it was not fast enough
    // Runtime: 694 ms, faster than 17.38% of Java online submissions for Word Ladder.
    // Memory Usage: 41.5 MB, less than 62.77% of Java online submissions for Word Ladder.
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!checkIfEndWordPresent(endWord, wordList)) {
            return 0;
        }
        Map<String, Boolean> visitedNodes = new HashMap<>();

        List<String> compWordList = new ArrayList<>(wordList.size() + 1);
        compWordList.add(beginWord);
        compWordList.addAll(wordList);
        for (String word : compWordList) {
            visitedNodes.put(word, false);
        }

        Map<String, List<String>> graph = createGraph(compWordList);
        int distance = calcDistance(graph, visitedNodes, beginWord, endWord);
        return distance + 1;
    }

    int calcDistance(Map<String, List<String>> graph, Map<String, Boolean> visitedNodes, String beginWord, String endWord) {
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComp());
        Map<String, Integer> distances = new HashMap<>();
        distances.put(beginWord, 0);
        heap.add(new Node(beginWord, 0));
        while (!heap.isEmpty()) {
            Node currentNode = heap.remove();
            String cVal = currentNode.val;
            int cDist = currentNode.distance;
            if (visitedNodes.get(cVal)) {
                continue;
            }
            visitedNodes.put(cVal, true);
            distances.put(cVal, cDist);
            if (cVal.equals(endWord)) {
                break;
            }
            if (!graph.containsKey(cVal)) {
                continue;
            }
            for (String child : graph.get(cVal)) {
                if (visitedNodes.get(child)) {
                    continue;
                }
                heap.add(new Node(child, cDist + 1));
            }
        }
        if (distances.containsKey(endWord)) {
            return distances.get(endWord);
        }
        return -1;
    }

    public class Node {
        String val;
        int distance;

        public Node(String val, int distance) {
            this.distance = distance;
            this.val = val;
        }
    }

    public class NodeComp implements Comparator<Node> {
        public int compare(Node v1, Node v2) {
            return Integer.compare(v1.distance, v2.distance);
        }
    }

    Map<String, List<String>> createGraph(List<String> listOfNodes) {
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < listOfNodes.size() - 1; i++) {
            String node = listOfNodes.get(i);
            for (int j = i + 1; j < listOfNodes.size(); j++) {
                String newNode = listOfNodes.get(j);
                if (getLevel(node, newNode) == 1) {
                    updateMap(graph, node, newNode);
                    updateMap(graph, newNode, node);
                }
            }
        }
        return graph;
    }

    void updateMap(Map<String, List<String>> graph, String node, String newNode) {
        List<String> edges;
        if (graph.containsKey(node)) {
            edges = graph.get(node);
        } else {
            edges = new ArrayList<>();
        }
        edges.add(newNode);
        graph.put(node, edges);
    }

    boolean checkIfEndWordPresent(String endWord, List<String> wordList) {
        for (String word : wordList) {
            if (word.equals(endWord)) {
                return true;
            }
        }
        return false;
    }

    int getLevel(String s1, String s2) {
        int level = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                level++;
            }
        }
        return level;
    }
}
