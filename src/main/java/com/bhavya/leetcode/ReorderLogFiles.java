package com.guavus.jobs2.bhavya.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author bhavya.jain
 */
public class ReorderLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        PriorityQueue<String> heap = new PriorityQueue<>(new CustomComparator());
        for (int i = 0; i < logs.length; i++) {
            if (!checkDigit(logs[i])) {
                heap.add(logs[i]);
            }
        }
        String[] output = new String[logs.length];
        int i = 0;
        while (!heap.isEmpty()) {
            output[i] = heap.remove();
            i++;
        }
        for (int j = 0; j < logs.length; j++) {
            if (checkDigit(logs[j])) {
                output[i] = logs[j];
                i++;
            }
        }
        return output;
    }

    public String[] reorderLogFiles2(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean result1 = checkDigit(log1);
            boolean result2 = checkDigit(log2);
            if (result1 && result2) {
                return 0;
            } else if (result1) {
                return 1;
            } else if (result2) {
                return -1;
            } else {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp == 0) {
                    return split1[0].compareTo(split2[0]);
                } else {
                    return cmp;
                }
            }
        });
        return logs;
    }

    public boolean checkDigit(String input) {
        String[] split = input.split(" ", 2);
        int value = split[1].charAt(0) - '0';
        if (value >= 0 && value <= 9) {
            return true;
        } else {
            return false;
        }
    }

    public class CustomComparator implements Comparator<String> {
        public int compare(String v1, String v2) {
            String[] split1 = v1.split(" ", 2);
            String[] split2 = v2.split(" ", 2);
            int diff = split1[1].compareTo(split2[1]);
            if (diff == 0) {
                return split1[0].compareTo(split2[0]);
            } else {
                return diff;
            }
        }
    }
}
