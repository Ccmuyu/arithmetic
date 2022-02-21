package leetcode;

public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; //缓存next
            curr.next = pre; //转向
            pre = curr; //pre节点向前移动，移动到新链表顶端
            curr = next; //指针节点移动到下一个待翻转节点
        }
        return pre;//此时，pre就是新的头节点
    }

}
