package com.guavus.jobs2.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */

// Topological Sorting and detecting cycle in graph
public class CourseSchedule2 {
    // Built the graph in reverse order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] outputList = new int[0];
        boolean[] visitedNodes = new boolean[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> edges;
            if (graph.containsKey(prerequisites[i][1])) {
                edges = graph.get(prerequisites[i][1]);
            } else {
                edges = new ArrayList<>();
            }
            edges.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], edges);
        }
        Stack<Integer> output = new Stack<>();
        for (int node = 0; node < numCourses; node++) {
            if (visitedNodes[node]) {
                continue;
            } else {
                Stack<Integer> stack = new Stack<>();
                stack.push(node);
                Set<Integer> recursionStack = new HashSet<>();
                recursionStack.add(node);
                while (!stack.isEmpty()) {
                    int latestNode = stack.peek();
                    int nextChild = getNextUnvisitedChild(graph, visitedNodes, latestNode);
                    if (nextChild == -1) {
                        visitedNodes[latestNode] = true;
                        stack.pop();
                        output.push(latestNode);
                        recursionStack.remove(latestNode);
                    } else if (recursionStack.contains(nextChild)) {
                        return outputList;
                    } else {
                        recursionStack.add(nextChild);
                        stack.push(nextChild);
                    }
                }
            }
        }
        outputList = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            outputList[i] = output.pop();
        }
        return outputList;
    }


    public int getNextUnvisitedChild(Map<Integer, List<Integer>> graph, boolean[] visitedNodes, int node) {
        if (!graph.containsKey(node) || visitedNodes[node]) {
            return -1;
        }
        for (Integer child : graph.get(node)) {
            if (!visitedNodes[child]) {
                return child;
            }
        }
        return -1;
    }
}
