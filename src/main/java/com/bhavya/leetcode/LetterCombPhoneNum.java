package com.bhavya.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bhavya.jain
 */

public class LetterCombPhoneNum {
    String[] lookUp = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<String>(1);
        }
        String work = lookUp[digits.charAt(0) - '2'];
        if (digits.length() == 1) {
            List<String> list = new ArrayList();
            for (int i = 0; i < work.length(); i++) {
                list.add(work.substring(i, i + 1));
            }
            return list;
        } else {
            List<String> childList = letterCombinations(digits.substring(1));
            List<String> outputList = new ArrayList<String>(childList.size() * work.length());
            for (int i = 0; i < work.length(); i++) {
                for (String current : childList) {
                    outputList.add(work.substring(i, i + 1) + current);
                }
            }
            return outputList;
        }
    }
}
