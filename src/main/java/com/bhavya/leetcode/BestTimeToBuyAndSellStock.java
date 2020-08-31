package com.guavus.jobs2.bhavya.leetcode;

import java.util.*;

/**
 * @author bhavya.jain
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        List<Integer> arr = new ArrayList<>();
        arr.get(0);
        int max = -1;
        int profit = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (prices[i] > max) {
                max = prices[i];
            } else {
                int localProfit = max - prices[i];
                if (localProfit > profit) {
                    profit = localProfit;
                }
            }
        }
        return profit;
    }


}
