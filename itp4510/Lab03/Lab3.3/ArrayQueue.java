public class ArrayQueue {
    public static final int CAPACITY = 1000;
    private int capacity;
    private Object[] array;
    private int front = 0;
    private int rear = 0;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int cap) {
        capacity = cap;
        array = new Object[capacity];
    }

    public int size() {
        return (capacity - front + rear) % capacity;
    }

    public boolean isEmpty() {
        return (front == rear);
    }

    public void enqueue(Object item) throws QueueFullException {
        if (size() == capacity - 1) {
            throw new QueueFullException();
        }
        array[rear] = item;
        rear = (rear + 1) % capacity;
    }

    public Object dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        Object item = array[front];
        array[front] = null;
        front = (front + 1) % capacity;
        return item;
    }

    public Object front() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        return array[front];
    }

    public String toString() {
        String s = "[ ";
        int next = front;
        for (int i = 0; i < size(); i++) {
            s += array[next] + " ";
            next = front + i;
        }
        return s + " ]";
    }
}
