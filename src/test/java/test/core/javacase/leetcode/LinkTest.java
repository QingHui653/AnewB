package test.core.javacase.leetcode;

import io.swagger.models.auth.In;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:woshizbh
 * @Date: 2018/8/28
 * @Deseription
 */
public class LinkTest {

    @Test
    public void TestLink(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;

        node1= reverseList(node1);
        System.out.println(node1);
    }

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     *     4 -> 5 -> 1 -> 9
     * 示例 1:
     *
     * 输入: head = [4,5,1,9], node = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * 示例 2:
     * 输入: head = [4,5,1,9], node = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     * 说明:
     *
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     * @param node
     */
    public void deleteNode(ListNode node) {
        //判断相等 ,直接将 next 指向 next 的 next
        node.val=node.next.val;
        node.next=node.next.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的。
     * 进阶：
     * 你能尝试使用一趟扫描实现吗？
     * @param head
     * @param n
     * @return
     * 双指针法 ,前指针先走 n ,后指针在走
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode preNode = head;
        ListNode curNode = head;

        // 前指针 先走 n 步
        for (int i = 0; i < n; i++) {
            curNode = curNode.next;
        }

        if (curNode == null) {
            return preNode.next;
        }
        // 当前指针 走完时. 正好 后指针到删除位置
        while (curNode.next != null) {
            preNode = preNode.next;
            curNode = curNode.next;
        }

        // 改变指向
        preNode.next = preNode.next.next;

        return head;

    }

    /**
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode next = head.next;
        head.next = null;
        ListNode re = reverseList(next);
        next.next = head;
        return re;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode now = head;
        while (now != null) {
            ListNode next = now.next;
            now.next = prev;
            prev = now;
            now = next;
        }

        return prev;
    }
}
