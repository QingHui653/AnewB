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

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        ListNode result = oddEvenList(node1);

        //[0,9,1,2,4], listB = [3,2,4],
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode nodeb0 = new ListNode(3);
        ListNode nodeb1 = new ListNode(2);
        ListNode nodeb2 = new ListNode(4);
//        ListNode nodeb3 = new ListNode(1);
//        ListNode nodeb4 = new ListNode(4);
//        ListNode nodeb5 = new ListNode(5);

        nodeb0.next = nodeb1;
        nodeb1.next = node2;
        nodeb2.next = node3;
//        nodeb3.next = node4;
//        nodeb4.next = node5;

        ListNode result = getIntersectionNode2(node1,nodeb0);

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

    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     *
     * 注意：
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     * @param headA
     * @param headB
     * @return
     */
    //O(n) 遍历一次.不改变 原链表结构.
    //1. 相交的 话. headB的 尾节点 一定存在 A 中.将 B 反转, 遍历A ,无法 保证O(n)
    //2. 相交的 话, 相交的 长度一定是相等的 ,所以让 长的 先走几步,在 同时前进
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        ListNode reverNode = new ListNode(-1);
//        //反转 B
//        while (headB.next!=null){
//            reverNode=headB.next;
//            reverNode.next=new ListNode(headB.val);
//            reverNode=reverNode.next;
//        }
//        reverNode=reverNode.next;
        if (headA == null || headB == null)  return null;
        int countA = 1, countB = 1;
        ListNode ANode = headA, BNode = headB;
        // 遍历长度
        while(ANode.next != null){
            ++countA;
            ANode = ANode.next;
        }
        // 遍历长度
        while(BNode.next != null){
            ++countB;
            BNode = BNode.next;
        }
        //  长的 先行
        if(countB > countA){
            int temp = countB - countA;
            while(temp>0 && headB != null){
                headB = headB.next;
                temp--;
            }
        }else{
            int temp = countA - countB;
            while(temp>0 && headA != null){
                headA = headA.next;
                temp--;
            }
        }

        // 不同 则前进,相同则返回
        while (headA != null && headB != null) {
            if(headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //**
    // 两个 指针,一直循环 至到 相遇.
    // 当短的 为null ,交换长的 ,并且让长的 先行
    // 长的 为null ,交换为 短的 .此时长的 已经先行
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        //[0,9,1,2,4], listB = [3,2,4]
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while(pA != pB){
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA: pB.next;
        }
        return pA;
    }
}
