package com.bhavya.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRoomsII {
    public int solve(int[][] A) {
        Point[] points = getSortArr(A);
        int count = 0;
        int max = 0;
        for (Point pt : points) {
            switch (pt.y) {
                case 0:
                    count = count + 1;
                    break;
                case 1:
                    count = count - 1;
                    break;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if (p1.x - p2.x > 0) {
                return 1;
            } else if (p1.x - p2.x < 0) {
                return -1;
            } else {
                if (p1.y > p2.y) {
                    return -1;
                } else if (p1.y < p2.y) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public Point[] getSortArr(int[][] arr) {
        Point[] output = new Point[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            output[i] = new Point(arr[i][0], 0);
            output[arr.length + i] = new Point(arr[i][1], 1);
        }
        Arrays.sort(output, new PointComparator());
        return output;
    }
}

