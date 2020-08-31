package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */
public class FirstUniqueCharinString {
    public int firstUniqChar(String s) {
        int[] posArr = new int[26];
        int[] countArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (countArr[index] == 0) {
                posArr[index] = i;
            }
            countArr[index]++;
        }
        int outputPos = -1;
        for (int i = 0; i < 26; i++) {
            if (countArr[i] == 1 && (outputPos == -1 || outputPos > posArr[i])) {
                outputPos = posArr[i];
            }
        }
        return outputPos;
    }
}
