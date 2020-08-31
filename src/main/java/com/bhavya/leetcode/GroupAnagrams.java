package com.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */
public class GroupAnagrams {
    // Ordering of output does not matter
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> output = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String curr : strs) {
            String sorted = getSortedString(curr);
            if (map.containsKey(sorted)) {
                List<String> temp = map.remove(sorted);
                temp.add(curr);
                map.put(sorted, temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(curr);
                map.put(sorted, temp);
            }
        }
        Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
        for (Map.Entry<String, List<String>> a : entrySet) {
            output.add(a.getValue());
        }
        return output;
    }

    public String getSortedString(String input) {
        char[] output = input.toCharArray();
        Arrays.sort(output);
        return new String(output);
    }

    // Ordering of output matters
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> output = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String curr : strs) {
            String sorted = getSortedString(curr);
            if (map.containsKey(sorted)) {
                List<String> temp = map.remove(sorted);
                temp.add(curr);
                map.put(sorted, temp);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(curr);
                map.put(sorted, temp);
            }
        }
        for (String curr : strs) {
            String sorted = getSortedString(curr);
            if (map.containsKey(sorted)) {
                List<String> temp = map.remove(sorted);
                output.add(temp);
            }
        }
        return output;
    }
}
