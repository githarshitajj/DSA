package stacks_queues;

public class CustomStackMain {
    public static void main(String[] args) {
        CustomStack<Integer> stack = new CustomStack<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek());
        System.out.println(stack.size());
        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack.size());
    }
}
