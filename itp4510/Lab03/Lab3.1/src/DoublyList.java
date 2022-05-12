public class DoublyList {
    private DoublyNode head;
    private DoublyNode tail;

    public DoublyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void addToHead(Object item) {
        if (isEmpty()) {
            head = tail = new DoublyNode(item);
        } else {
            head.previous = new DoublyNode(item, null, head);
            head = head.previous;
        }
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new DoublyNode(item);
        } else {
            tail.next = new DoublyNode(item, tail, null);
            tail = tail.next;
        }
    }

    public Object removeFromHead() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        Object item = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        return item;
    }

    public Object removeFromTail() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        Object item = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        return item;
    }
    public String toString(){
        String s ="[";
        DoublyNode current = head;
        while(current!=null){
            s+=current.data + " ";
            current = current.next;
        }
        return s + "]";
    }
    public String forward(){
       return toString();
    }
    
    public String backward(){
        String s = "[";
        DoublyNode current =tail;
        while(current != null){
            s+= current.data + " ";
            current = current.previous;
        }
        return s+ "]";
    }
}
