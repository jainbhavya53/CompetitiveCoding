package com.bhavya.hackerrank;

/**
 * @author bhavya.jain
 */
// https://www.hackerrank.com/challenges/friend-circle-queries/problem

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DisjointSetsFriendCircle {

    // n^2 complexity(Worst Case)
    static int[] maxCircle(int[][] queries) {
        int output[] = new int[queries.length];
        PriorityQueue<Set<Integer>> queue = new PriorityQueue<>(10, new SetComparator());
        Set<Integer> initial = new HashSet<>();
        initial.add(queries[0][0]);
        initial.add(queries[0][1]);
        queue.add(initial);
        output[0] = initial.size();
        //System.out.println(output[0]);
        for (int i = 1; i < queries.length; i++) {
            int first = queries[i][0];
            int second = queries[i][1];
            ArrayList<Set<Integer>> tempQueue = new ArrayList<>(queue.size());
            ArrayList<Set<Integer>> updatedList = new ArrayList<>(2);
            while (!queue.isEmpty() && updatedList.size() < 2) {
                Set<Integer> current = queue.remove();
                if (current.contains(first) || current.contains(second)) {
                    current.add(first);
                    current.add(second);
                    updatedList.add(current);
                } else {
                    tempQueue.add(current);
                }
            }
            if (updatedList.size() == 2) {
                if (updatedList.get(0).size() > updatedList.get(1).size()) {
                    Set<Integer> combineSet = updatedList.get(0);
                    combineSet.addAll(updatedList.get(1));
                    tempQueue.add(combineSet);
                } else {
                    Set<Integer> combineSet = updatedList.get(1);
                    combineSet.addAll(updatedList.get(0));
                    tempQueue.add(combineSet);
                }
            } else if (updatedList.size() == 1) {
                tempQueue.addAll(updatedList);
            } else {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(first);
                newSet.add(second);
                queue.add(newSet);
            }
            queue.addAll(tempQueue);
            output[i] = queue.peek().size();
            //System.out.println(output[i]);
        }
        /*while(!queue.isEmpty()){
            System.out.println(queue.remove());
        }*/
        return output;
    }

    static int[] maxCircle2(int[][] queries) {
        int output[] = new int[queries.length];
        PriorityQueue<Set<Integer>> queue = new PriorityQueue<>(10, new SetComparator());
        Set<Integer> initial = new HashSet<>();
        initial.add(queries[0][0]);
        initial.add(queries[0][1]);
        queue.add(initial);
        output[0] = initial.size();
        //System.out.println(output[0]);
        for (int i = 1; i < queries.length; i++) {
            int first = queries[i][0];
            int second = queries[i][1];
            ArrayList<Set<Integer>> origList = new ArrayList<>(2);
            ArrayList<Set<Integer>> updatedList = new ArrayList<>(2);
            Iterator<Set<Integer>> mainIterator = queue.iterator();
            while (mainIterator.hasNext() && updatedList.size() < 2) {
                Set<Integer> current = mainIterator.next();
                if (current.contains(first) || current.contains(second)) {
                    origList.add(current);
                    current.add(first);
                    current.add(second);
                    updatedList.add(current);
                }
            }
            if (updatedList.size() == 2) {
                queue.remove(origList.get(0));
                queue.remove(origList.get(1));
                if (updatedList.get(0).size() > updatedList.get(1).size()) {
                    Set<Integer> combineSet = updatedList.get(0);
                    combineSet.addAll(updatedList.get(1));
                    queue.add(combineSet);
                } else {
                    Set<Integer> combineSet = updatedList.get(1);
                    combineSet.addAll(updatedList.get(0));
                    queue.add(combineSet);
                }
            } else if (updatedList.size() == 1) {
                queue.remove(origList.get(0));
                queue.addAll(updatedList);
            } else {
                Set<Integer> newSet = new HashSet<>(2);
                newSet.add(first);
                newSet.add(second);
                queue.add(newSet);
            }
            output[i] = queue.peek().size();
            //System.out.println(output[i]);
        }
        /*while(!queue.isEmpty()){
            System.out.println(queue.remove());
        }*/
        return output;
    }

    // Final Solution
    // nlogn complexity(Worst Case)
    static int[] maxCircle3(int[][] queries) {
        int output[] = new int[queries.length];
        int maxSize = 200000;
        Map<Integer, Integer> coord = new HashMap<>(maxSize);
        int[] treeArr = new int[maxSize];
        int[] rankArr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            treeArr[i] = i;
            rankArr[i] = 1;
        }
        int counter = 0;
        int max = 1;
        for (int i = 0; i < queries.length; i++) {
            int first = queries[i][0];
            int second = queries[i][1];
            int firstIndex = -1;
            int secondIndex = -1;

            // Fetch Indexes
            if (coord.containsKey(first)) {
                firstIndex = coord.get(first);
            } else {
                firstIndex = counter;
                coord.put(first, counter);
                counter++;
            }
            if (coord.containsKey(second)) {
                secondIndex = coord.get(second);
            } else {
                secondIndex = counter;
                coord.put(second, counter);
                counter++;
            }

            // Find Root
            int firstRoot = getRoot(treeArr, firstIndex);
            int secondRoot = getRoot(treeArr, secondIndex);
            if (firstRoot != secondRoot) {
                if (rankArr[firstRoot] > rankArr[secondRoot]) {
                    treeArr[secondRoot] = firstRoot;
                    rankArr[firstRoot] = rankArr[firstRoot] + rankArr[secondRoot];
                    if (rankArr[firstRoot] > max) {
                        max = rankArr[firstRoot];
                    }
                } else {
                    treeArr[firstRoot] = secondRoot;
                    rankArr[secondRoot] = rankArr[secondRoot] + rankArr[firstRoot];
                    if (rankArr[secondRoot] > max) {
                        max = rankArr[secondRoot];
                    }
                }
            }
            output[i] = max;
        }
        return output;
    }


    static int getRoot(int[] treeArr, int input) {
        int root = input;
        while (root != treeArr[root]) {
            root = treeArr[root];
        }
        return root;
    }

    public static class SetComparator implements Comparator<Set<Integer>> {
        public int compare(Set<Integer> set1, Set<Integer> set2) {
            int size1 = set1.size();
            int size2 = set2.size();
            if (size1 > size2) {
                return -1;
            } else if (size1 == size2) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
