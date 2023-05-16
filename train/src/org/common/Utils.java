package org.common;

import org.common.struct.ListNode;

import java.util.HashMap;

public class Utils {
    public final static HashMap<Character, Integer> romanNumberMap = new HashMap<>();
    static {
        romanNumberMap.put('I',1);
        romanNumberMap.put('V',5);
        romanNumberMap.put('X',10);
        romanNumberMap.put('L',50);
        romanNumberMap.put('C',100);
        romanNumberMap.put('D',500);
        romanNumberMap.put('M',1000);
    }
    
    public static ListNode  arrayToList(int[] a) {
        if (a.length ==0) return null;
        ListNode node = new ListNode(a[0]);
        ListNode cur = node;
        for (int i = 1; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return node;
    }

    public static void main(String[] args) {

    }
}
