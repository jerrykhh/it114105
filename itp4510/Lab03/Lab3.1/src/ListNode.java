public class ListNode {
    Object data;
    ListNode next;

    ListNode(Object data, ListNode next){
        this.data = data;
        this.next = next;
    }

    ListNode(Object data){
        this.data = data;
        this.next = null;
    }
}