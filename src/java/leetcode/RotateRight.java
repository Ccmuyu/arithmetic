package leetcode;

// 61. 旋转链表
// https://leetcode-cn.com/problems/rotate-list/
public class RotateRight {


    // 获取环的长度，同时将指针节点移动到链表末尾
    // 计算需要移动的位置：add = n-k%n(当k是n的整数倍时2，链表等于原状)
    // 成环: iter.next = head
    // 移动节点 add 次 （iter是尾节点）
    // iter.next 即是新链表的头节点
    // 断开环：尾节点的next指针
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;//链表为空，或者k=0
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next; //移动到尾节点
            n++;// 获取链表长度
        }
        int add = n - k % n; //需要移动的节点数
        if (add == n) {//恰好为n的整数倍时，节点不需要移动
            return head;
        }
        iter.next = head; // 设置成环
        while (add-- > 0) { // 移动指定节点数 add
            iter = iter.next; // 移动add次
        }
        ListNode ret = iter.next; //此时节点的下一个即是目标位置
        iter.next = null;  //重要：将环断开
        return ret;
    }
}
