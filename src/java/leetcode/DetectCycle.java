package leetcode;

// 142. 环形链表 II
// https://leetcode-cn.com/problems/linked-list-cycle-ii/
public class DetectCycle {

    // 快慢指针，当快慢指针相遇时，第三个指针从head触发，一定能与慢指针在环入口处相遇
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;

        while (fast != null) {
            slow = slow.next; //慢指针速度1
            if (fast.next != null) {
                fast = fast.next.next; //快指针速度2
            }else {
                return null;
            }

            if (slow == fast) {//说明有环
                //此时，由公式：a+n(b+c)+b=a+(n+1)b+nc --> a+(n+1)b+nc=2(a+b) 推导得出：a=c+(n−1)(b+c)
                //即：a = c+ n圈，此时再有节点从头触发，则一定在入环处与slow相遇。
                ListNode p = head; //第三个指针
                while (p != slow) {
                    p = p.next;
                    slow = slow.next;
                }
                return p;//入口环
            }
        }
        return null;
    }


}
