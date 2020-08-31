package com.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */

// Problem drills down to finding a cycle in a directed graph
public class CourseSchedule {

    // Runtime:-> 5ms and Memory Usage:-> 42MB
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        boolean[] visitedNodes = new boolean[numCourses];
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for (int node = 0; node < numCourses; node++) {
            if (visitedNodes[node]) {
                continue;
            } else {
                Stack<Integer> stack = new Stack<>();
                Set<Integer> recursionStack = new HashSet<>();
                stack.push(node);
                recursionStack.add(node);
                while (!stack.isEmpty()) {
                    int topNode = stack.peek();
                    int nextChild = getNextUnvisitedChild(graph, visitedNodes, topNode);
                    if (nextChild == -1) {
                        visitedNodes[topNode] = true;
                        stack.pop();
                        recursionStack.remove(topNode);
                    } else {
                        stack.push(nextChild);
                        if (recursionStack.contains(nextChild)) {
                            return false;
                        } else {
                            recursionStack.add(nextChild);
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getNextUnvisitedChild(ArrayList<Integer>[] graph, boolean[] visitedNodes, int node) {
        if (graph[node].size() == 0 || visitedNodes[node]) {
            return -1;
        }
        List<Integer> childs = graph[node];
        for (Integer child : childs) {
            if (!visitedNodes[child]) {
                return child;

            }
        }
        return -1;
    }

    // Runtime:-> 6ms and Memory Usage:-> 43.2MB
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        boolean[] visitedNodes = new boolean[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> edges;
            if (graph.containsKey(prerequisites[i][0])) {
                edges = graph.get(prerequisites[i][0]);
            } else {
                edges = new ArrayList<>();
            }
            edges.add(prerequisites[i][1]);
            graph.put(prerequisites[i][0], edges);
        }
        for (int node = 0; node < numCourses; node++) {
            if (visitedNodes[node]) {
                continue;
            } else {
                Stack<Integer> stack = new Stack<>();
                Set<Integer> recursionStack = new HashSet<>();
                stack.push(node);
                recursionStack.add(node);
                while (!stack.isEmpty()) {
                    int topNode = stack.peek();
                    int nextChild = getNextUnvisitedChild(graph, visitedNodes, topNode);
                    if (nextChild == -1) {
                        visitedNodes[topNode] = true;
                        stack.pop();
                        recursionStack.remove(topNode);
                    } else {
                        stack.push(nextChild);
                        if (recursionStack.contains(nextChild)) {
                            return false;
                        } else {
                            recursionStack.add(nextChild);
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getNextUnvisitedChild(Map<Integer, List<Integer>> graph, boolean[] visitedNodes, int node) {
        if (!graph.containsKey(node) || visitedNodes[node]) {
            return -1;
        }
        List<Integer> childs = graph.get(node);
        for (Integer child : childs) {
            if (!visitedNodes[child]) {
                return child;

            }
        }
        return -1;
    }

    // Runtime:-> 9ms and Memory Usage:-> 43.3MB
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        Map<Integer, Boolean> visitedNodes = new HashMap<>(numCourses);
        Map<Integer, List<Integer>> graph = new HashMap<>(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> edges;
            if (graph.containsKey(prerequisites[i][0])) {
                edges = graph.get(prerequisites[i][0]);
            } else {
                edges = new ArrayList<>();
            }
            edges.add(prerequisites[i][1]);
            graph.put(prerequisites[i][0], edges);
            visitedNodes.put(prerequisites[i][0], false);
            visitedNodes.put(prerequisites[i][1], false);
        }
        Set<Integer> listOfNodes = visitedNodes.keySet();
        for (Integer node : listOfNodes) {
            if (visitedNodes.get(node)) {
                continue;
            } else {
                Stack<Integer> stack = new Stack<>();
                Set<Integer> recursionStack = new HashSet<>();
                stack.push(node);
                recursionStack.add(node);
                while (!stack.isEmpty()) {
                    int topNode = stack.peek();
                    int nextChild = getNextUnvisitedChild(graph, visitedNodes, topNode);
                    if (nextChild == -1) {
                        visitedNodes.put(topNode, true);
                        stack.pop();
                        recursionStack.remove(topNode);
                    } else {
                        stack.push(nextChild);
                        if (recursionStack.contains(nextChild)) {
                            return false;
                        } else {
                            recursionStack.add(nextChild);
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getNextUnvisitedChild(Map<Integer, List<Integer>> graph, Map<Integer, Boolean> visitedNodes, int node) {
        if (!graph.containsKey(node) || visitedNodes.get(node)) {
            return -1;
        }
        List<Integer> childs = graph.get(node);
        for (Integer child : childs) {
            if (!visitedNodes.get(child)) {
                return child;
            }
        }
        return -1;
    }
}
