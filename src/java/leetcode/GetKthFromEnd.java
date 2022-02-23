package leetcode;

// 剑指 Offer 22. 链表中倒数第k个节点
// https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
public class GetKthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode tmp = head; //获取链表长度
        int count = 0;
        while(tmp != null) {
            tmp = tmp.next;
            count++;
        }

        int i = count - k; // 需要移动的次数
        ListNode ref = head;
        while (i-- > 0) {
            ref = ref.next;
        }
        return ref;

    }
}
