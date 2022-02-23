package leetcode;

// 148. 排序链表
// https://leetcode-cn.com/problems/sort-list/
public class SortList {


    // 归并排序
    // 思想：如果局部有序，则整体有序
    public ListNode sortList(ListNode head) {
        // 分割：使用slow、fast指针，找到二分点（奇数：中点，偶数：中心左侧点）
        if (head == null || head.next == null) return head;

        ListNode fast = head.next, slow = head; // fast要比slow快一个节点，
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next; // 当fast走完链表时，slow一定位于中心点
        }
        ListNode tmp = slow.next;
        slow.next = null; // 截断左右，形成局部左右两个链表

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) { //归并到一个新链表下
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right; // 这一步必须要，
        return res.next;
    }
}
