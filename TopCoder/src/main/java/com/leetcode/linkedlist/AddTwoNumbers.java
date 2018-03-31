package com.leetcode.linkedlist;

public class AddTwoNumbers {

    static class LinkedListNode {
        private int data;
        private LinkedListNode next;

        public LinkedListNode(int data) {
            this.data = data;
        }
    }

    public void add (LinkedListNode list1, LinkedListNode list2) {
        int result = recursive(list1, list2, 0);
        System.out.println(result);
    }

    private int recursive(LinkedListNode list1, LinkedListNode list2, int overflow) {
        if (list1 == null && list2 == null) {
            return overflow;
        }
        int number1 = 0;
        if (list1 != null) {
            number1 = list1.data;
        }

        int number2 = 0;
        if (list2 != null) {
            number2 = list2.data;
        }

        int sum = number1 + number2 + overflow;
        int remaining = sum / 10;
        int remainder = sum % 10;
        int previousSum = recursive(list1 != null ? list1.next : null,
                                    list2 != null ? list2.next : null,
                                    remaining);
        return previousSum*10 + remainder;
    }


    public static void main(String[] args) {
        LinkedListNode list1 = new LinkedListNode(2);
        list1.next = new LinkedListNode(4);
        list1.next.next = new LinkedListNode(3);

        LinkedListNode list2 = new LinkedListNode(5);
        list2.next = new LinkedListNode(6);
        list2.next.next = new LinkedListNode(4);
        new AddTwoNumbers().add(list1, list2);
    }
}
