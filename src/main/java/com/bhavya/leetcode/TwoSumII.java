package com.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */
// Two Sum II when the input array is sorted
public class TwoSumII {
    // Implemented using two pointers
    // Fastest method -> O(N)
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int x = numbers[i] + numbers[j];
        while (x != target) {
            if (x < target) {
                i++;
            } else {
                j--;
            }
            x = numbers[i] + numbers[j];
        }
        return new int[]{i + 1, j + 1};
    }

    // Implementation using HashMap
    // Slower than binarySearch, I don't know why ?
    public int[] twoSum2(int[] numbers, int target) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            List<Integer> indices;
            if (map.containsKey(numbers[i])) {
                indices = map.get(numbers[i]);
            } else {
                indices = new ArrayList<>();
            }
            indices.add(i);
            map.put(numbers[i], indices);
        }
        Set<Map.Entry<Integer, List<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : entrySet) {
            int second = target - entry.getKey();
            if (second == entry.getKey()) {
                int index1 = entry.getValue().get(0);
                int index2 = entry.getValue().get(1);
                return new int[]{index1 + 1, index2 + 1};
            } else if (map.containsKey(second)) {
                int index1 = entry.getValue().get(0) + 1;
                int index2 = map.get(second).get(0) + 1;
                return new int[]{Math.min(index1, index2), Math.max(index1, index2)};
            }
        }
        return new int[]{-1, -1};
    }

    // Implementation using BinarySearch
    // Second Fastest -> O(NlogN)
    public int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int index = getElementIndex(numbers, target - numbers[i], i + 1);
            if (index != -1) {
                return new int[]{i + 1, index + 1};
            }
        }
        return new int[]{-1, -1};
    }

    public int getElementIndex(int[] arr, int input, int start) {
        int end = arr.length - 1;
        int mid = (start + end) / 2;
        while (start <= end && mid < arr.length) {
            if (arr[mid] == input) {
                return mid;
            } else if (arr[mid] > input) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }
}
