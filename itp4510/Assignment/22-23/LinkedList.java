class ListNode {

	private Object data;
	private ListNode next;

	public ListNode(Object o) { data = o; next = null; }
	public ListNode(Object o, ListNode nextNode)
		{ data = o; next = nextNode; }

	public Object getData() { return data; }
	public void setData(Object o) { data = o; }
	
	public ListNode getNext() { return next; }
	public void setNext(ListNode next) { this.next = next; }


} // class ListNode

class EmptyListException extends RuntimeException {
	public EmptyListException ()
		{ super("List is empty"); }
} // class EmptyListException

public class LinkedList {

	private ListNode head;
	private ListNode tail;

	private int length;		// the length of the list

	public LinkedList() {
		head = tail = null;
		length = 0;
	}

	public boolean isEmpty() { return head == null; }

	public void addToHead(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else
			head = new ListNode(item, head);
		length++;
	}

	public void addToTail(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else {
			tail.setNext(new ListNode(item));
			tail = tail.getNext();
		}
		length++;
	}

	public Object removeFromHead() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = head.getData();
		if (head == tail)
			head = tail = null;
		else
			head = head.getNext();
		length--;
		return item;
	}

	public Object removeFromTail() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = tail.getData();
		if (head == tail)
			head = tail = null;
		else {
			ListNode current = head;
			while (current.getNext() != tail)
				current = current.getNext();
			tail = current;
			current.setNext(null);
		}
		length--;
		return item;
	}
	
	public int count() {
		return length;
	}
	
	// students need to revise toString method
	public String toString() {
		String str = "";
		ListNode current = head;
		while (current != null) {
			FoodOrder foodOrder = (FoodOrder) current.getData();
			str += foodOrder + "\n";
			current = current.getNext();
		}
		return str;
	}

	 
	/**

	Removes a ListNode from the LinkedList with a specific Member ID.

	@param targetID the Member ID of the ListNode to be removed

	@throws EmptyListException if the list is empty
	*/
	public void remove(int targetID) throws EmptyListException {

		// Throw an exception if the list is empty
		if (isEmpty()) {
			throw new EmptyListException();
		}
		
		// If the target node is the head node, remove it from the head
		if (((FoodOrder) head.getData()).getMemberID() == targetID) {
			removeFromHead();
			return;
		}
		
		// Traverse the linked list to find the target node
		ListNode current = head.getNext();
		ListNode prev = head;
		
		while (current != null && ((FoodOrder) current.getData()).getMemberID() != targetID) {
			prev = current;
			current = current.getNext();
		}
		
		// If the target node is found, remove it
		if (current != null) {
			prev.setNext(current.getNext());
			length--;
		}
	}

	/**

	Checks if the linked list contains a FoodOrder object with the given targetID as its member ID.
	@param targetID the member ID to search for in the linked list
	@return true if the linked list contains a FoodOrder object with the given targetID as its member ID, false otherwise
	*/
	public boolean contain(int targetID) {
		ListNode current = head;
		while (current != null) {
			if (((FoodOrder) current.getData()).getMemberID() == targetID) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}
	
	/**

	Adds a new FoodOrder to the LinkedList in the correct position based on its priority.
	@param item the FoodOrder to add to the LinkedList
	*/
	public void add(Object item) {
		FoodOrder foodOrderItem = (FoodOrder) item;
	
		// if the list is empty, add the new node as the head
		if (isEmpty()) 
			addToHead(item);
		
		// if the new node has higher priority than the head, add it as the new head
		else if (foodOrderItem.getPriority() < ((FoodOrder) head.getData()).getPriority()) 
			addToHead(item);
		
		// if the new node has lower priority than the tail, add it as the new tail
		else if (foodOrderItem.getPriority() >= ((FoodOrder) tail.getData()).getPriority()) 
			addToTail(item);
		
		// otherwise, find the correct position for the new node and insert it
		else {
			ListNode current = head.getNext();
			ListNode prev = head;
	
			while (current != null && foodOrderItem.getPriority() >= ((FoodOrder) current.getData()).getPriority()) {
				prev = current;
				current = current.getNext();
			}
	
			prev.setNext(new ListNode(item, current));
			length++;
		}
	}
	
} 
