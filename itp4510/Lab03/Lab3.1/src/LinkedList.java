public class LinkedList {
    ListNode head;
    ListNode tail;
    int count = 0;

    LinkedList(){
        head = null;
        tail = null;
    }

    public int getCount(){
        return count;
    }

    public boolean isEmpty(){
        return (head == null && tail == null);
    }

    public void addToHead(Object data){
        ListNode node = new ListNode(data);
        if(isEmpty()){
            head = tail = node;
            count++;
            return;
        }
        node.next = head;
        head = node;
        count++;
    }

    public void addToTail(Object data){
        ListNode node = new ListNode(data);
        if(isEmpty()){
            head = tail = node;
            count++;
            return;
        }
        tail.next = node;
        tail = node;
        count++;
    }

    public Object removeFromHead(){
        if(isEmpty())
            throw new IndexOutOfBoundsException();
        ListNode node = head;

        if(tail == head){
            head = tail = null;
            count--;
            return node.data;
        }

        head = head.next;
        count--;
        return node.data;
    }

    public Object removeFromTail(){
        if(isEmpty())
            throw new IndexOutOfBoundsException();

        ListNode node = tail;

        if(tail == head){
            head = tail = null;
            count--;
            return node.data;
        }
        ListNode currentNode = head;
        while(currentNode.next != tail){
            currentNode = currentNode.next;
            System.out.println(currentNode.data);
        }
        currentNode.next = null;
        tail = currentNode;
        count--;
        return node.data;
    }

    public String toString(){
        ListNode currentNode = head;
        String listData = "";
        while (currentNode != null){
            listData += currentNode.data + " ";
            currentNode = currentNode.next;
        }
        return listData;
    }

    public void clear(){
        head = tail = null;
    }

    public Object getItemAt(int n){
        if (n < 0 || n >= count)
            throw new IndexOutOfBoundsException();

        int currentPos = 0;
        ListNode currentNode = head;
        while(currentPos < n){
            if(count == currentPos)
                break;
            currentNode = currentNode.next;
            currentPos++;
        }

        return currentNode.data;
    }

    public Object removeItemAt(int n){
        if (n < 0 || n >= count)
            throw new IndexOutOfBoundsException();

        int currentPos = 0;
        ListNode currentNode = head;

        if(n==0){
            removeFromHead();
            return currentNode.data;
        }

        while(currentPos < n-1){
            currentNode = currentNode.next;
            currentPos++;
        }

        ListNode item = currentNode.next;
        currentNode.next = currentNode.next.next;
        count--;
        return item.data;
    }


    public void addItemAt(Object item, int n){
        if (n > count)
            throw new IndexOutOfBoundsException();
        
        
        if (isEmpty() || n == 0){
            addToHead(item);
            return;
        }

        ListNode node = new ListNode(item);
        int currentPos = 0;
        ListNode currentNode = head;
        while(currentPos < n-1){
            currentNode = currentNode.next;
            currentPos++;
        }

        node.next = currentNode.next;
        currentNode.next = node;
        count++;
    }

}
