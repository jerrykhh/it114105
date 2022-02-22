public class List {
    private Node head;
    private Node tail;
    private int size;

    public List(){
        head = tail = null;
        size = 0;
    }

    public boolean isEmply(){
        return head == null;
    }

    public void append(Object data){
        if(head == null){
            head = tail = new Node(data);
            size++;
            return;
        }

        tail.next = new Node(data);
        tail = tail.next;
        size++;
    }

    public Object getItemAt(int index){
        Node tmp = head;
        int tmpIndex = 0;
        while(tmpIndex < index){
            tmp = tmp.next;
            tmpIndex++;
        }
        return tmp.data;
    }

    public int getSize(){
        return size;
    }

    
}
