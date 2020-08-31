package com.guavus.jobs2.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */
public class TwoSums {
    // Two Pass Hash Table (1st approach) 56% faster than other users
    public int[] twoSum(int[] nums, int target) {
        int output[] = new int[2];
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                ArrayList<Integer> arr = map.get(nums[i]);
                arr.add(i);
                map.put(nums[i], arr);
            } else {
                ArrayList<Integer> arr = new ArrayList<>(2);
                arr.add(i);
                map.put(nums[i], arr);
            }

        }
        Iterator<Integer> keys = map.keySet().iterator();
        boolean result = false;
        while (keys.hasNext() && !result) {
            int key = keys.next();
            int diff = target - key;
            if (map.containsKey(diff)) {
                if (diff != key) {
                    output[0] = map.get(key).get(0);
                    output[1] = map.get(diff).get(0);
                    result = true;
                } else {
                    output[0] = map.get(key).get(0);
                    output[1] = map.get(key).get(1);
                    result = true;
                }
            }
        }
        return output;
    }

    // One pass Hash Table(2nd approach) 99.91 % faster than other users
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                return new int[]{i,map.get(diff)};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

}
