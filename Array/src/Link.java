import java.math.BigDecimal;

/**
 * 链表结构
 */
public class Link {
    static class ListNode{
        //当前节点的值
        int val;
        //下一个节点
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    /** leetcode hot100  2
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * @param l1
     * @param l2
     * @return
     */
    public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        Link link = new Link();
        BigDecimal  decimal = new BigDecimal("1111.11");
        System.out.println(link.addTwoNumbers(new ListNode(10),new ListNode(20)));

    }

}
