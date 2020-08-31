package com.bhavya.leetcode;

/**
 * @author bhavya.jain
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class MergeTwoSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode output = new ListNode(-1);
        ListNode tempOutput = output;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                tempOutput.next = new ListNode(temp1.val);
                temp1 = temp1.next;
            } else if (temp1.val > temp2.val) {
                tempOutput.next = new ListNode(temp2.val);
                temp2 = temp2.next;
            } else {
                tempOutput.next = new ListNode(temp1.val);
                tempOutput = tempOutput.next;
                tempOutput.next = new ListNode(temp2.val);
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            tempOutput = tempOutput.next;
        }
        if (temp1 == null && temp2 == null) {

        } else if (temp1 == null) {
            tempOutput.next = temp2;
        } else {
            tempOutput.next = temp1;
        }
        return output.next;
    }
}
