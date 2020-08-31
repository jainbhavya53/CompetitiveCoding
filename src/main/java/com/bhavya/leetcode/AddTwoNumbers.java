package com.guavus.jobs2.bhavya.leetcode;

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

public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode output = new ListNode(-1);
        ListNode tempOutput = output;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        int carry = 0;
        while (temp1 != null && temp2 != null) {
            int value = temp1.val + temp2.val + carry;
            carry = value / 10;
            int rem = value % 10;
            ListNode current = new ListNode(rem);
            tempOutput.next = current;
            tempOutput = tempOutput.next;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        if (temp1 == null && temp2 == null) {
            if (carry != 0) {
                tempOutput.next = new ListNode(carry);
            }
            return output.next;
        } else if (temp1 == null) {
            tempOutput.next = temp2;
        } else {
            tempOutput.next = temp1;
        }
        ListNode parent = tempOutput;
        tempOutput = tempOutput.next;
        while (carry != 0 && tempOutput != null) {
            int value = tempOutput.val + carry;
            int rem = value % 10;
            carry = value / 10;
            tempOutput.val = rem;
            parent = tempOutput;
            tempOutput = tempOutput.next;
        }
        if (tempOutput == null && carry != 0) {
            parent.next = new ListNode(carry);
        }
        return output.next;
    }
}
