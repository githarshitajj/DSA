package stacks_queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class InbuiltExamples {
    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(10);
//        stack.push(20);
//        stack.push(30);
//        System.out.println(stack.pop()); //so how to remove specific elements -- removes the last element to be entered i.e. at the top of the stack
//        System.out.println(stack);

        Queue<Integer> q = new LinkedList<>(); //queues are implemented by the linked list
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4); // 1-> 2-> 3->4
        System.out.println(q.peek()); //gets the head of the queue
        System.out.println(q.remove()); //removes the head of the queue and returns the element -- throws exception when queue is empty
        System.out.println(q.remove(3)); //removes the specific object and returns boolean removed
        System.out.println(q);
    }
}
