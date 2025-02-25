

public class queue {
	private int rear, front;
    private Object[] Queue;

  public  queue(int capacity) {
        this.Queue = new Object[capacity];
        rear = -1;
        front = 0;
    }

    public void enqueue(Object data) {
        if (isFull()) {
            System.out.println("Queue overflow");
        } else {
            rear = (rear + 1) % Queue.length;
            Queue[rear] = data;
        }
    }
    public Object dequeue() {
        if (isEmpty()) {
            //System.out.println("Queue is empty");
            return null;
        } else {
            Object retData = Queue[front];
            Queue[front] = null;
            front = (front + 1) % Queue.length;
            return retData;
        }
    }
    public Object peek() {
        if (isEmpty()) {
            
            return null;
        } else {
            return Queue[front];
        }
    }
    boolean isEmpty() {
        return Queue[front] == null;
    }
    boolean isFull() {
        return (front == (rear + 1) % Queue.length && Queue[front] != null && Queue[rear] != null);
    }
    int size() {
        if (Queue[front] == null) {
            return 0;
        } else {
            if (rear >= front)
                return rear - front + 1;
            else
                return Queue.length - (front - rear) + 1;
        }
    }
}
