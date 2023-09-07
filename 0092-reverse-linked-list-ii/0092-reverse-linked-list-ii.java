/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
      if (head == null || head.next == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev_left = dummy;
        ListNode curr = head;

        // traverse to the left-th node and reverse the links
        for (int i = 1; i < left; i++) {
            prev_left = curr;
            curr = curr.next;
        }

        ListNode prev = null;
        ListNode right_node = curr; // the right-th node after reversal
        for (int i = left; i <= right; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // update the links between prev_left, right_node, and the node after right_node
        prev_left.next = prev;
        right_node.next = curr;

        return dummy.next;
    }
}