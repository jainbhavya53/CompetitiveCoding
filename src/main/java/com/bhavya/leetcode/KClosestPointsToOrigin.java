package com.bhavya.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author bhavya.jain
 */
public class KClosestPointsToOrigin {
    public class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDist() {
            return Math.pow(x * x + y * y, 0.5);
        }

        public int getDist2() {
            return x * x + y * y;
        }
    }

    // This comparator is slower as computation of square root is expensive
    public class PointComp implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double v1 = p1.getDist();
            double v2 = p2.getDist();
            if (v1 > v2) {
                return 1;
            } else if (v2 > v1) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public class PointComp2 implements Comparator<int[]> {
        public int compare(int[] p1, int[] p2) {
            int v1 = p1[0] * p1[0] + p1[1] * p1[1];
            int v2 = p2[0] * p2[0] + p2[1] * p2[1];
            if (v1 > v2) {
                return 1;
            } else if (v2 > v1) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public class PointCompInt implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            int v1 = p1.getDist2();
            int v2 = p2.getDist2();
            if (v1 > v2) {
                return 1;
            } else if (v2 > v1) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    // Using Heap
    public int[][] kClosest3(int[][] points, int K) {
        int[][] output = new int[K][];
        PriorityQueue<Point> queue = new PriorityQueue<>(new PointCompInt());
        for (int i = 0; i < points.length; i++) {
            queue.add(new Point(points[i][0], points[i][1]));
        }
        for (int i = 0; i < K; i++) {
            Point curr = queue.remove();
            output[i] = new int[]{curr.x, curr.y};
        }
        return output;
    }

    // Using classic sort operation using a comparator
    public int[][] kClosest2(int[][] points, int K) {
        int[][] output = new int[K][];
        Arrays.sort(points, new PointComp2());
        for (int i = 0; i < K; i++) {
            output[i] = points[i];
        }
        return output;
    }

    // Using Quick Select , refer to Kth Largest Element Class for more Details
    // Fastest Approach -> O(N) average case complexity, O(N^2) for worst case
    public int[][] kClosest(int[][] points, int K) {
        int min = 0;
        int max = points.length - 1;
        int index = randomPartition(points, min, max);
        while (index != K - 1) {
            if (index > K - 1) {
                max = index - 1;
            } else {
                min = index + 1;
            }
            index = randomPartition(points, min, max);
        }
        int[][] output = new int[K][];
        for (int i = 0; i < output.length; i++) {
            output[i] = points[i];
        }
        return output;
    }

    public int randomPartition(int[][] points, int low, int high) {
        int randI = low + (int) (Math.random() * (high - low));
        swap(points, randI, high);
        return partition(points, low, high);
    }

    public int partition(int[][] points, int low, int high) {
        int i = low - 1;
        int pivot = getDist(points[high]);
        for (int j = low; j <= high - 1; j++) {
            if (getDist(points[j]) < pivot) {
                i++;
                swap(points, i, j);
            }
        }
        swap(points, i + 1, high);
        return i + 1;
    }

    public void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    public int getDist(int[] coor) {
        return coor[0] * coor[0] + coor[1] * coor[1];
    }
}
