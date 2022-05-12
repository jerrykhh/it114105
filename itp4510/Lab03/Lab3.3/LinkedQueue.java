public class LinkedQueue {
    LinkedList qll;

    public LinkedQueue() {
        qll = new LinkedList();
    }

    public int size() {
        return qll.getCount();
    }

    public boolean isEmpty() {
        return qll.isEmpty();
    }

    public void enqueue(Object item) throws QueueFullException {
        qll.addToTail(item);
    }

    public Object dequeue() throws QueueEmptyException {
        try {
            Object item = qll.removeFromHead();
            return item;
        } catch (EmptyListException e) {
            throw new QueueEmptyException();
        }
    }

    public String toString() {
        return qll.toString();
    }

    public Object front() throws QueueEmptyException {
        try {
            Object item = qll.removeFromHead();
            qll.addToHead(item);
            return item;
        } catch (EmptyListException e) {
            throw new QueueEmptyException();
        }
    }
}