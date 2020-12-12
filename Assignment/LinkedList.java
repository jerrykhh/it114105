
public class LinkedList {

    private ListNode head;
    private ListNode tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void addToHead(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            head = new ListNode(item, head);
        }
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            tail.next = new ListNode(item);
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
        }
        return item;
    }

    public String toString() {
        String s = "[ ";
        ListNode current = head;
        while (current != null) {
            s += current.data + " ";
            current = current.next;
        }
        return s + "]";
    }

    public Object removeFromTail() throws EmptyListException {
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
        return item;
    }

    public int getCount() {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void addItemAt(Object item, int n) {
        if (isEmpty() || n == 0) {
            head = new ListNode(item, head);
            return;
        }
        if (n >= getCount()) {
            tail.next = new ListNode(item);
        } else {
            int currentPos = 0;
            ListNode current = head;
            while (currentPos != (n - 1)) {
                current = current.next;
                currentPos++;
            }
            ListNode newNode = new ListNode(item);
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public Object removeItemAt(int n) throws EmptyListException {
        if (n < 0 || n > getCount()) {
            throw new IndexOutOfBoundsException();
        }
        if (n == 0) {
            return (removeFromHead());
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos < (n - 1)) {
            current = current.next;
            currentPos++;
        }
        Object item = current.next.data;
        current.next = current.next.next;
        return item;
    }

    public Object getItemAt(int n) {
        if (n < 0 || n > getCount()) {
            throw new IndexOutOfBoundsException();
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos < n) {
            current = current.next;
            currentPos++;
        }
        return current.data;
    }

    public int searchItemAt(Object item) { // search the item position
        ListNode current = head;           // create the pointer to head
        int currentPos = 0;                // let the position as 0, the linkedlist with start at 0
        while (current != null) {          // search the item until the linkedlist tail -> null
            if (current.data == item) {    // if found the same data in the linkedlist will break
                break;
            }
            current = current.next;
            currentPos++;                  // if current data is not equal to we may found data so the position will add 1 to calculate current Node position.
        }
        return currentPos;                 // return the search item position
    }
    
    public boolean checkDup(){
        ListNode current=head;
        boolean checkDup = false;
        while (current!=null){
            ListNode currentSec = current.next;
            while(currentSec !=null){
                 if(current.data.equals(currentSec.data)){
                     checkDup = true;
                     break;
                 }
                 currentSec= currentSec.next;
            }
            if(checkDup)
                break;
            current=current.next;
        }
        return checkDup;
    }

    public void findJokerA() throws EmptyListException { // switch the card 27 and 27 next card  
        if (tail.data.equals(27)) {             // In special case, if the tail data is 27
            Object cache = head.data;           // cache the data of head data, because the tail data will replcae the head data when the card switch
            tail.data = cache;                  // tail data will equal to head data when the card switch
            head.data = 27;                     // head data equal the 27 when the case of 27 is tail data
        } else {
            ListNode current = head;            // create the pointer to head of ListNode
            while (!current.data.equals(27)) {  // find the ListNode position and the ListNode data is 27
                current = current.next;         // if ListNode data is not 27 will point to next Node
            }
            Object cache = current.next.data;   // set the cache data of next current position
            current.data = cache;               // current position set to data of next current position
            current.next.data = 27;             // the next current position set to 27
        }
    }

    public void findJokerB() throws EmptyListException {// switch the card 28, 28 next card and 28 next next card 
        if (tail.data.equals(28)) {                     // In special case, if the tail data is 28
            Object cache = head.data;                   // set cache data of head data, because the tail data will replcae the head data when the card switch
            tail.data = cache;                          // tail data will set to head data
            head.data = head.next.data;                 // The head data will set to head next data
            head.next.data = 28;                        // The head next data will set to 28
        } else if (searchItemAt(28) == 26) {            // In special case, if the tail before data is 28
            Object cachetail = removeFromTail();        // set the cache data of Tail data and remove tail data
            tail.data = cachetail;                      // In currenly, the total count card is 27. The tail data set to cache data (remove tail data in line 171)
            addToTail(head.data);                       // Add to tail of head data in the LinkedList and the total count card is 28
            head.data = 28;                             // set the 28 is head data.
        } else {
            ListNode current = head;                    // create the pointer to head of ListNode
            while (!current.data.equals(28)) {          // find the ListNode position and the ListNode data is 28
                current = current.next;                 // if ListNode data is not 28 will point to next Node
            }
            Object cache = current.next.data;           // set cache data is current next data and current next next data
            Object cacheTwo = current.next.next.data;  
            current.data = cache;                       // current data position is 28 so set the data to current next data
            current.next.data = cacheTwo;               // 28 after data set to current next next data
            current.next.next.data = 28;                // the current next next data set to 28
        }
    }

    public void tripleCut() throws EmptyListException {
        LinkedList cacheLeft = new LinkedList();                        //Create the LinkedList to store cut data in Left and Right
        LinkedList cacheRight = new LinkedList();                       
        int listRightSize, listLeftSize;
        if (searchItemAt(27) > searchItemAt(28)) {                      // search the data is 27 and 28, if the 27 position larger that of 28 position will cut right hand site(Tail), and the 28 will left hand site(Head)
            listRightSize = getCount() - searchItemAt(27) - 1;          // calcuate the 27 how many value should cut  
            listLeftSize = searchItemAt(28);                            // the 28 should cut the current position value
        } else {                                                        // otherwise the 28 position larger that of 27 position will cut right hand site(Tail) and the 27 will cut left hand site(Head)
            listRightSize = getCount() - searchItemAt(28) - 1;          // calcuate the 28 how many value should cut 
            listLeftSize = searchItemAt(27);                            // the 27 should cut the current position value
        }
        for (int i = 0; i < listRightSize; i++) {
            cacheRight.addToTail(removeFromTail());                     //cut right hand sit and put the value to cacheRight LinkedList

        }
        for (int i = 0; i < listLeftSize; i++) {
            cacheLeft.addToTail(removeFromHead());                      // cut left hand sit and put the value to cacheLeft LinkedList
        }
        for (int i = 0; i < listRightSize; i++) {
            addToHead(cacheRight.removeFromHead());                     // store all value of cacheRight LinkedList to Left Hand site (Head)
        }
        for (int i = 0; i < listLeftSize; i++) {
            addToTail(cacheLeft.removeFromHead());                      // store all vale of cacheLeft LinkedList to Right Hand site (Tail)
        }
    }

    public void countCut() throws EmptyListException {
                                            
        if (tail.data.equals(28)) {                                          // if tail data is 28
            return;                                                          // Not to do
        } else {
            Object item = removeFromTail();                                  // remove the tail data and store the value to item
            for (int i = 0; i < (int) item; i++) {                           // According the item value to put the head Node to Tail Node of number
                addToTail(removeFromHead());
            }
            addToTail(item);                                                 // put the item value(in line 219) to Tail.
        } 
    }

    public Object topCard() throws EmptyListException {
        if (head.data.equals(28)|| head.data.equals(27)) {                   //when the head data is 28 or 27 will return the tail data
            return tail.data;                                                
        } else {                                                             // the head data is not 28 or 28 will return the search of head data position
            return getItemAt((int) head.data);
        }
    }
}
