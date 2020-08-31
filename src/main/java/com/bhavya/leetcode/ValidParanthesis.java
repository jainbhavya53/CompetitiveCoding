package com.guavus.jobs2.bhavya.leetcode;

import java.util.Stack;

/**
 * @author bhavya.jain
 */
public class ValidParanthesis {
    Stack<Character> stack;

    public boolean isValid(String s) {
        stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else {
                if (!checkClosingBrace(curr)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean checkClosingBrace(char input) {
        if (stack.isEmpty()) {
            return false;
        } else {
            char temp = stack.pop();
            switch (input) {
                case ')':
                    return temp == '(';
                case '}':
                    return temp == '{';
                case ']':
                    return temp == '[';
            }
        }
        return false;
    }
}
