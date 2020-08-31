package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */
public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int left[] = new int[nums.length];
        left[0] = 1;
        if (nums.length == 0) {
            return nums;
        }
        if (nums.length == 1) {
            return left;
        }
        for (int i = 1; i < left.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            left[i] = left[i] * temp;
            temp = temp * nums[i];
        }
        return left;
    }


    public int[] productExceptSelf2(int[] nums) {
        int left[] = new int[nums.length];
        int right[] = new int[nums.length];
        left[0] = 1;
        right[right.length - 1] = 1;
        if (nums.length == 0) {
            return nums;
        }
        if (nums.length == 1) {
            return left;
        }
        for (int i = 1; i < left.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = left[i] * right[i];
        }
        return nums;
    }
}
