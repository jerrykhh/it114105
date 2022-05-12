public class LinkedList {
	private ListNode head;
	private ListNode tail;
	private int count;

	public LinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

	public boolean isEmpty() {
		return (head==null);
	}

	public int getCount() {
		return count;
	}

	public void addToHead(Object item) {
		count++;
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			head = new ListNode(item, head);
		}
	}

	public void addToTail(Object item) {
		count++;
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			tail.next = new ListNode(item);
			tail =  tail.next;
		}
	}

	public Object removeFromHead() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		} 
		Object item = head.data;
		if (head == tail) // there's only one single node
			head = tail = null;
		else
			head = head.next;
		count--;
		return item;
	}

	public Object removeFromTail() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		} 
		count--;
		Object item = tail.data;
		if (head == tail) {   // there is only one node
			head = tail = null;
			return item;
		}
		// search for the second last node 
		ListNode current = head;
		while (current.next != tail)
			current = current.next;
		// set second last node as new tail
		tail = current;
		tail.next = null;
		return item;
	}

	public String toString() {
		String s = "[ ";

		// traverse the list from head towards tail
		ListNode current = head;
		while (current != null) {
			s += current.data + " ";
			current = current.next;
		}
		return s + "]";
	}
}





