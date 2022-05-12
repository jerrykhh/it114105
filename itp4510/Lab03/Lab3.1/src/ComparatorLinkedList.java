public class ComparatorLinkedList extends LinkedList {

    Comparator comparator;

    ComparatorLinkedList(Comparator comparator) {
        super();
        this.comparator = comparator;
    }

    public void removeItem(Object item) throws ItemNotFoundException, EmptyListException {
        if (isEmpty()) {
            throw new ItemNotFoundException();
        }
        if (comparator.isEqualTo(head.data, item)) {
            removeFromHead();
        } else if (comparator.isEqualTo(tail.data, item)) {
            removeFromTail();
        } else {
            ListNode current = head;
            while (current.next != null) {
                if (comparator.isEqualTo(current.next.data, item)) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
            throw new EmptyListException();
        }
    }

    public void insertInOrder(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            if (comparator.isGreaterThanOrEqualTo(head.data, item)) {
                addToHead(item);
            } else if (comparator.isLessThanOrEqualTo(tail.data, item)) {
                addToTail(item);
            } else {
                ListNode current = head;
                while (current.next != null) {
                    if (comparator.isGreaterThanOrEqualTo(current.next.data, item)) {
                        ListNode newNode = new ListNode(item);
                        newNode.next = current.next;
                        current.next = newNode;
                        return;
                    }
                    current = current.next;
                }
            }
        }
    }

}
