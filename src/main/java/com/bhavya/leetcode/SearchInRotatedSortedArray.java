package com.bhavya.leetcode;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int pivot = -1;
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                pivot = 0;
            }
            return pivot;
        }
        pivot = findPivot(nums);
        if (pivot == -1) {
            throw new IllegalArgumentException("pivot cannot be -1, something wrong");
        }
        int start = 0;
        int end = nums.length - 1;
        if (pivot != 0) {
            if (target >= nums[0]) {
                start = 0;
                end = pivot - 1;
            } else {
                start = pivot;
                end = nums.length - 1;
            }
        }
        return getIndex(nums, start, end, target);
    }

    // Assuming that nums.length > 1
    // Not required that nums is rotated
    public int findPivot(int nums[]) {
        int pivot = -1;
        int start = 0;
        int end = nums.length - 1;
        boolean result = false;
        while (!result) {
            int mid = (start + end) / 2;
            int left = mid - 1;
            int right = mid + 1;
            if (mid == 0) {
                left = nums.length - 1;
            }
            if (mid == nums.length - 1) {
                right = 0;
            }
            // Check if pivot element
            if (nums[left] > nums[mid] && nums[mid] < nums[right]) {
                pivot = mid;
                result = true;
            }
            // Check if at the largest element
            else if (nums[left] < nums[mid] && nums[mid] > nums[right]) {
                pivot = mid + 1;
                result = true;
            }
            // In other cases just update start and end to continue search
            else {
                // first < mid > last  :   -------->
                if (nums[0] < nums[mid] && nums[mid] > nums[nums.length - 1]) {
                    start = mid + 1;
                }
                // first < mid < last  ;   <-------
                // first > mid < last  ;   <-------
                // first > mid > last  ;   not possible since the number are sorted in ascending order
                else {
                    end = mid - 1;
                }
            }
        }
        return pivot;
    }

    public int getIndex(int[] nums, int start, int end, int target) {
        int mid = -1;
        boolean result = false;
        while (start <= end && !result) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                result = true;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // Successful search
        if (result) {
            return mid;
        }
        // Unsuccessful
        else {
            return -1;
        }
    }
}
