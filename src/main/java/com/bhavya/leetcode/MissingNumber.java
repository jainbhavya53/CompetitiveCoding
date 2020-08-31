package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int tempSum = 0;
        for (int i = 0; i < nums.length; i++) {
            tempSum = tempSum + nums[i];
        }
        return (nums.length * (nums.length + 1)) / 2 - tempSum;
    }
}
