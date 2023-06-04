public class Test {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next =  node2; node2.next = node3; node3.next = node4; node4.next = node5; node5.next = null;
        System.out.println(hasCyc(node1));

    }


    public static boolean hasCyc(ListNode node) {
        if (node == null) return false;
        ListNode dummy = new ListNode(-1);
        dummy.next = node;
        ListNode slow = dummy, fast = node;
        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

   static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

}
