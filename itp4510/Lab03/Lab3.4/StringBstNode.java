public class StringBstNode {

    private String data;
    private StringBstNode left;
    private StringBstNode right;

    public StringBstNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public String getData() {
        return data;
    }

    public StringBstNode getLeft() {
        return left;
    }

    public StringBstNode getRight() {
        return right;
    }

    public void setLeft(StringBstNode p) {
        left = p;
    }
    public void setRight(StringBstNode p){
        right =p;
    }

}