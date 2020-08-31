package com.guavus.jobs2.bhavya.leetcode;

/**
 * @author bhavya.jain
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("[.]");
        String[] split2 = version2.split("[.]");
        int minI = Math.min(split1.length, split2.length);
        for (int i = 0; i < minI; i++) {
            int first = Integer.parseInt(split1[i]);
            int second = Integer.parseInt(split2[i]);
            if (first > second) {
                return 1;
            } else if (first < second) {
                return -1;
            }
        }
        if (split1.length > split2.length) {
            for (int i = minI; i < split1.length; i++) {
                int value = Integer.parseInt(split1[i]);
                if (value != 0) {
                    return 1;
                }
            }
        } else if (split1.length < split2.length) {
            for (int i = minI; i < split2.length; i++) {
                int value = Integer.parseInt(split2[i]);
                if (value != 0) {
                    return -1;
                }
            }
        }
        return 0;
    }
}