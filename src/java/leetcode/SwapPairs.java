package leetcode;

// 24. 两两交换链表中的节点
// https://leetcode-cn.com/problems/swap-nodes-in-pairs/
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next; // 缓存next指针
        head.next = swapPairs(next.next); //递归置换，next替换为置换节点
        next.next = head; // next.next 置换为当前节点
        return next;
    }
}
