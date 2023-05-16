package org.common.struct;

public class StructTestMain {
    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < 6; i++) {
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);
        for (int i = 0; i < 3; i++) {
            arrayQueue.dequeue();
        }
        System.out.println(arrayQueue);
        for (int i = 0; i < 2; i++) {
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i);
        }
        System.out.println(arrayQueue);
    }

    private static void testLinkedStack() {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            linkedStack.push(i);
        }
        System.out.println(linkedStack);
        for (int i = 0; i < 10; i++) {
            System.out.println(linkedStack.pop());
        }
        System.out.println(linkedStack);
    }

    private static void testArrayStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        Integer i = 1;
        while (i < 12){
            stack.push(i);
            if (i == 3) System.out.println(stack);
            i ++;
        }
        System.out.println(stack);
        while (i != null){
            i = stack.pop();
        }
        System.out.println(stack);

        stack.push(23);
        System.out.println(stack);
    }
}
