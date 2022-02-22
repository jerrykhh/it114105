public class ListNode {
    Object data;
    ListNode next;
    public ListNode(){
        data=null;
        next=null;
    }
    public ListNode(Object data){
        this.data = data;
        this.next = null;
    }
    public ListNode(Object data, ListNode next){
        this.data = data;
        this.next = next;
    }
}
