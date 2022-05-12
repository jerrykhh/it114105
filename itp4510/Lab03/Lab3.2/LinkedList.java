
public class LinkedList {

    private ListNode head;
    private ListNode tail;
    private int count = 0;

    public boolean isEmpty() {
        return (head == null);
    }

    public void addToHead(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            head = new ListNode(item, head);
        }
        count++;
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            tail.next = new ListNode(item);
            tail = tail.next;
        }
        count++;
    }

    public Object removeFromHead() throws EmptyListException {
        Object item = null;
        if (isEmpty()) {
            throw new EmptyListException();
        }
        item = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        count--;
        return item;
    }

    public Object removeFromtail() throws EmptyListException {

        if (isEmpty()) {
            throw new EmptyListException();
        }
        Object item = tail.data;
        if (head == tail) {
            head = tail = null;
            return item;
        }
        ListNode current = head;
        while (current.next != tail) {
            current = current.next;
        }
        tail = current;
        tail.next = null;
        count--;
        return item;
    }

    public String toString() {
        String s = "[ ";
        ListNode current = head;
        while (current != null) {
            s = s + current.data + " ";
            current = current.next;
        }
        return s + " ]";
    }

    public int getCount() {
        return count;
    }

    public Object getItemAt(int n) {
        if (n < 0 || n >= count) {
            throw new IndexOutOfBoundsException();
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos <= (n - 1)) {
            current = current.next;
            currentPos++;
        }
        return current.data;
    }

    public void addItemAt(Object item, int n) {
        if (isEmpty() || n == 0) {
            addToHead(item);
            return;
        }
        if (n >= count) {
            addToTail(item);
            return;
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos < (n - 1)) {
            current = current.next;
            currentPos++;
        }
        ListNode newNode = new ListNode(item);
        newNode.next = current.next;
        current.next = newNode;
        count++;

    }
    public Object removeItemAt(int n) throws EmptyListException{
        if(n<0 || n>=count)
            throw new IndexOutOfBoundsException();
        if(n==0)
            removeFromHead();
        int currentPos=0;
        ListNode current=head;
        while(currentPos<(n-1)){
            current = current.next;
            currentPos++;
        }
        Object item = current.next.data;
        current.next = current.next.next;
        count--;
        return item;
        
    }
}

