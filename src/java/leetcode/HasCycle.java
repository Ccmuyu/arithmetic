package leetcode;

// 141. 环形链表
// https://leetcode-cn.com/problems/linked-list-cycle/
public class HasCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        //快慢双指针
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {//如果相遇，则说明有环
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;//慢指针一次走异步
            fast = fast.next.next;//快指针一次走两步
        }
        return true;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
