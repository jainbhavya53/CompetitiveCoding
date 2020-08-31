package com.bhavya.hackerrank;

/**
 * @author bhavya.jain
 */
// https://www.hackerrank.com/challenges/maximum-xor/problem

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MaximumXors {

    // Complete the maxXor function below.
    static int[] maxXor(int[] arr, int[] queries) {
        Node root = null;
        Node update = null;
        int output[] = new int[queries.length];
        for (int i = 0; i < arr.length; i++) {
            root = updateTree(root, arr[i]);
        }
        //printTree2(root);
        for (int i = 0; i < queries.length; i++) {
            Node current = root;
            int max_value = 0;
            String binary = getBinaryEqv(queries[i]);
            for (int j = 0; j < 31; j++) {
                if (binary.charAt(j) == '0') {
                    if (current.right != null) {
                        max_value = max_value + (int) Math.pow(2, 30 - j);
                        current = current.right;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.left != null) {
                        max_value = max_value + (int) Math.pow(2, 30 - j);
                        current = current.left;
                    } else {
                        current = current.right;
                    }
                }
            }
            output[i] = max_value;
        }
        return output;
    }

    static Node updateTree(Node root, int value) {
        if (root == null) {
            root = new Node(-1);
            System.out.println("Entered here");
        }
        String binaryEqv = getBinaryEqv(value);
        //System.out.println("input -> " + value + " , " + binaryEqv);
        return addNode(root, value, binaryEqv);
    }

    static Node addNode(Node root, int value, String input) {
        if (input.length() == 1) {
            if (input.charAt(0) == '0') {
                //System.out.println("Left leaf node with value -> " + value);
                root.left = new Node(value);
            } else {
                //System.out.println("Right leaf node with value -> " + value);
                root.right = new Node(value);
            }
        } else {
            if (input.charAt(0) == '0') {
                if (root.left == null) {
                    root.left = new Node(-1);
                }
                //System.out.println("left ; value -> " + value +" ; inputSubstring -> " + input);
                root.left = addNode(root.left, value, input.substring(1));
            } else {
                if (root.right == null) {
                    root.right = new Node(-1);
                }
                //System.out.println("right ; value -> " + value +" ; inputSubstring -> " + input);
                root.right = addNode(root.right, value, input.substring(1));
            }
            root.height = computeHeight(root);
        }
        return root;
    }

    static int computeHeight(Node node) {
        int lHeight = 0;
        int rHeight = 0;
        if (node.left != null) {
            lHeight = node.left.height;
        }
        if (node.right != null) {
            rHeight = node.right.height;
        }
        return Math.max(lHeight, rHeight) + 1;
    }

    static String getBinaryEqv(int input) {
        String binary = Integer.toBinaryString(input);
        StringBuffer buf = new StringBuffer();
        int cLength = binary.length();
        if (binary.length() < 31) {
            for (int i = 0; i < 31 - cLength; i++) {
                buf.append("0");
            }
        }
        return buf.append(binary).toString();
    }

    static class Node {
        public int value;
        public Node left;
        public Node right;
        public int height;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    static void printTree2(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int prevHeight = root.height;
        int currHeight = root.height;
        StringBuffer sb = new StringBuffer();
        while (queue.size() != 0) {
            Node currentNode = queue.remove();
            sb.append(currentNode.height + "(" + currentNode.value + ")");
            if (currentNode.left != null) {
                System.out.println("right is null");
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                System.out.println("left is null");
                queue.add(currentNode.right);
            }
        }
        System.out.println(sb.toString());
    }

    static void printTree(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int prevHeight = root.height;
        int currHeight = root.height;
        StringBuffer sb = null;
        while (queue.size() != 0) {
            Node currentNode = queue.remove();
            currHeight = currentNode.height;
            System.out.println("value -> " + currentNode.value);
            if (prevHeight == currHeight) {
                sb = new StringBuffer(String.valueOf(currentNode.value));
                sb.append(" -- ");
            } else {
                System.out.println(sb.toString());
            }
            prevHeight = currHeight;
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));
            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

