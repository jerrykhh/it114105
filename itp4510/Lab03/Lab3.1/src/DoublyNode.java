public class DoublyNode {
    
    Object data;
    DoublyNode previous;
    DoublyNode next;

    DoublyNode(Object data){
        this.data = data;
        previous = next = null;
    }

    DoublyNode(Object data, DoublyNode previous, DoublyNode next){
        this.data = data;
        this.previous = previous;
        this.next = next;
    }


}
