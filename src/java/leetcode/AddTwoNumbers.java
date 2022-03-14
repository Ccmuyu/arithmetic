package leetcode;

// 2. 两数相加
// https://leetcode-cn.com/problems/add-two-numbers/
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
        //请你将两个数相加，并以相同形式返回一个表示和的链表。
        //你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        ListNode pre = new ListNode(0);  // dummy节点，用于缓存结果
        ListNode cur = pre; //移动指针
        int carry = 0;
        while (l1 != null || l2 != null) { // 终止条件：两个链表都结束
            int x = l1 == null ? 0 : l1.val;  // 如果链已结束，用0代表value
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry; // 计算和

            carry = sum / 10; // 计算进位值
            sum = sum % 10; // 计算当前位的值
            cur.next = new ListNode(sum); // 用value作为节点，拼到dummy后

            cur = cur.next; //指针后移
            if (l1 != null)
                l1 = l1.next; // l1 后移
            if (l2 != null)
                l2 = l2.next; // 后移
        }
        if (carry == 1) { // 当两个链表长度相等时，可能还要向前进位
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

}
