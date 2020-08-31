package com.bhavya.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bhavya.jain
 */
public class LongestSubstringWithoutRepeatingChars {
    // A more expressive version for better understanding
    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        Map<Character, Integer> map = new HashMap<>();
        int size = 0;
        int start = 0;
        while (i < s.length()) {
            char current = s.charAt(i);
            if (!map.containsKey(current)) {
                map.put(current, i);
            } else {
                int index = map.get(current);
                if (index < start) {
                    map.put(current, i);
                } else {
                    if (i - start > size) {
                        size = i - start;
                    }
                    start = index + 1;
                    map.put(current, i);
                }
            }
            i++;
        }
        if (i - start > size) {
            size = i - start;
        }
        return size;
    }

    // Same logic as above, just a reduced version of above
    public int lengthOfLongestSubstring2(String s) {
        int i = 0;
        Map<Character,Integer> map = new HashMap<>();
        int size = 0;
        int start = 0;
        while(i < s.length()){
            char current = s.charAt(i);
            if(map.containsKey(current)){
                int index = map.get(current);
                if(index >= start){
                    if(i-start > size){
                        size = i-start;
                    }
                    start = index + 1;
                }
            }
            map.put(current,i);
            i++;
        }
        if(i-start > size){
            size = i-start;
        }
        return size;
    }
}
