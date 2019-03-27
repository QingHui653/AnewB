package test.core.javacase.middle;

import org.junit.Test;

/**
 * @Auther:b
 * @Date: 2019/3/26
 * @Deseription
 */

/**
 * 链表问题相对容易掌握。 不要忘记"双指针解法"，它不仅适用于数组问题，而且还适用于链表问题。
 * 另一种大大简化链接列表问题的方法是"Dummy node" 节点技巧，所谓Dummy Node其实就是带头节点的指针。
 */
public class LinkTest {

    @Test
    public void test(){
//        ListNode node1=new ListNode(2);
//        ListNode node1_2=new ListNode(4);
//        node1_2.next=new ListNode(3);
//        node1.next=node1_2;
//        ListNode node2=new ListNode(5);
//        ListNode node2_2=new ListNode(6);
//        node2_2.next=new ListNode(4);
//        node2.next=node2_2;
//        addTwoNumbers(node1,node2);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode result = oddEvenList(node1);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * @param l1
     * @param l2
     * @return
     */
    //1. 暴力破解 ,分别迭代相加
    //2. 循环一次,相同index 相加(进位).
    // 链表中
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //dummy 不能变
        ListNode dummy = new ListNode(-1);
        //一个 新的指针 指向 dummy的最新节点
        ListNode cur = dummy;
        int carry=0;//进位
        while (l1!=null || l2!=null){
            int d1 = l1==null?0:l1.val;
            int d2 = l2==null?0:l2.val;
            int sum= d1+d2+carry;
//            carry = sum >= 10 ? 1 : 0;//大于10 进1
            carry = sum / 10;//大于10 进1
            cur.next = new ListNode(sum % 10);//取余
            // 不会改变 前面已经确定的 指针弄得
            cur = cur.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        //最后如果还进一
        if (carry == 1) cur.next = new ListNode(1);
        return dummy.next;
    }

    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * 示例 1:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     * 示例 2:
     * 输入: 2->1->3->5->6->4->7->NULL
     * 输出: 2->3->6->7->1->5->4->NULL
     * 说明:
     * 应当保持奇数节点和偶数节点的相对顺序。
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     * @param head
     * @return
     */
    //空间复杂度O(1)，表示所需空间为常量，并且与n无关。
    // 两个 listNde 一个奇数,一个偶数,在相连.
    /**
     * ListNode odd = head;
     * odd.next=even.next;// 此时 odd 修改 会 影响 head
     * odd=odd.next; //此时不会影响head,
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)  return head;
        // 将head 指向odd,修改odd 会影响 head
        ListNode odd = head; //奇数
        ListNode even = head.next;//偶数
        ListNode evenHead = even;//偶数的 头节点
        while (even != null && even.next != null){
            odd.next=even.next;
            odd=odd.next;

            even.next=odd.next;
            even=even.next;
        }
        odd.next=evenHead;
        return head;
    }
}
