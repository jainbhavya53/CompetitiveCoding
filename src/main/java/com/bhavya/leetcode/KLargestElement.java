package com.guavus.jobs2.bhavya.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author bhavya.jain
 */
public class KLargestElement {
    /**
     * Implementation using Quick Select; complexity -> Average case complexity is O(N); worst case is O(N^2)
     * Here, N is the number of elements in the array and k is the rank of the element in descending order
     * <p>
     * Quick Select is same as partition strategy used in QuickSort, wherein we bring the pivot element to its rightful position.
     * So, effectively we repeatedly choose a pivot element until we find that the given pivot element has reached the desired index.
     * Now the way we choose the pivot element can improve the run time complexity of the algorithm. In the given scenario, we have implemented QuickSelect with RandomPivoting
     * </p>
     *
     * <b>References:-></b>
     * <p>
     * For detailed analysis please refer to the following pages ->
     * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
     * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-2-expected-linear-time/
     * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-3-worst-case-linear-time/ -> <b>This strategy will bring even worst case complexity to linear time</b>
     * <p>
     */
    public int findKthLargest(int[] nums, int k) {
        int min = 0;
        int max = nums.length - 1;
        int index = randomPartition(nums, min, max);
        while (index != nums.length - k) {
            if (index < nums.length - k) {
                min = index + 1;
                //index = randomPartition(nums,index+1,nums.length-1);
            } else {
                max = index - 1;
                //index = randomPartition(nums,0,index-1);
            }
            index = randomPartition(nums, min, max);
        }
        return nums[nums.length - k];
    }

    public int randomPartition(int nums[], int low, int high) {
        int randIndex = low + (int) (Math.random() * (high - low));
        swap(nums, randIndex, high);
        return partition(nums, low, high);
    }

    public int partition(int[] nums, int low, int high) {
        int i = low - 1;
        int pivot = nums[high];
        for (int j = low; j <= high - 1; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;
    }

    public void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Implementation using Max heap; complexity -> O(N + klog(N))
    // N is the number of elements in the array and k is the rank of the element in descending order
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new IntComp());
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        for (int i = 0; i < k - 1; i++) {
            queue.remove();
        }
        return queue.remove();
    }

    public class IntComp implements Comparator<Integer> {
        public int compare(Integer v1, Integer v2) {
            return v1.compareTo(v2) * -1;
        }
    }
}