package com.bhavya.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author bhavya.jain
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] splitList = paragraph.split("[ !?',;.']");
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String ban : banned) {
            set.add(ban);
        }
        for (String curr : splitList) {
            String temp = curr.trim().toLowerCase();
            if (!set.contains(temp) && temp.length() != 0) {
                if (map.containsKey(temp)) {
                    int value = map.remove(temp);
                    map.put(temp, value + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        int max = 0;
        String output = "";
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                output = entry.getKey();
            }
        }
        return output;
    }
}
