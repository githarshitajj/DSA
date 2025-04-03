package stacks_queues;

public class CustomStack<T> {
    //stack implementation -> array of fixed size and then dynamic stack where it's resizable
    protected T[] data;
    //private static final int DEFAULT_CAPACITY = 10; //private - don't want others to change
    //static - every object should have the same size
    //final - i don't want to accidentally change it
    private int maxSize;
    private int top;

    @SuppressWarnings("unchecked")
    public CustomStack(int size) {
        this.maxSize = size;
        this.data = (T[]) new Object[maxSize];
        this.top = -1;
    }

    public void push(T item) {
        if(isFull()){
            throw new StackOverflowError("Stack is full.Cannot push " + item);
        }
        data[++top] = item;
    }
    public T pop() {
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return data[top--];
    }
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        return data[top];
    }

    private boolean isFull() {
        return top == maxSize - 1;
    }
    private boolean isEmpty() {
        return top == -1;
    }
    public int size() {
        return top + 1;
    }

}
