package com.guavus.jobs2.bhavya.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bhavya.jain
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<Point> list = new ArrayList<>();
        int[] left = sortArr(intervals, false);
        int[] right = sortArr(intervals, true);
        Arrays.sort(left);
        Arrays.sort(right);
        int start = 0;
        int end = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (left[i] <= right[end]) {
                end++;
            } else {
                list.add(new Point(left[start], right[end]));
                start = i;
                end = i;
            }
        }
        if (start < intervals.length && end < intervals.length) {
            list.add(new Point(left[start], right[end]));
        }
        int[][] output = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            Point point = list.get(i);
            output[i] = new int[]{point.x, point.y};
        }
        return output;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] sortArr(int[][] arr, boolean flag) {
        int j = 0;
        if (flag) {
            j = 1;
        }
        int[] output = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            output[i] = arr[i][j];
        }
        return output;
    }
}
