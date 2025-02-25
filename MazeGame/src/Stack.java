
public class Stack {

	private int top;
    private Object[] Stack;

    public Stack(int capacity) {
        Stack = new Object[capacity];
        top = -1;
    }

    public boolean isFull() {
        return top + 1 == Stack.length;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(Object data) {
        if (isFull()) {
            //System.out.println("Stack overflow");
        }

        else {
            top++;
            Stack[top] = data;
        }
    }

    public Object pop() {
        if (isEmpty()) {
            //System.out.println("Stack is Empty");
            return null;
        }

        else {
            Object retData = Stack[top];
            Stack[top] = null;
            top--;
            return retData;
        }
    }

    public Object peek() {
        if (isEmpty()) {
            //System.out.println("Stack is Empty");
            return null;
        }

        else {
            return Stack[top];
        }
    }

    public int size() {
        return top + 1;
    }
}
