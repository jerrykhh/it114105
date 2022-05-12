public class StringBst  {
    private StringBstNode root;

    public StringBst() {
        root = null;
    }

    public void addNode(String data) {
        StringBstNode p = root, prev = null;
        if (root == null) {
            root = new StringBstNode(data);
            return;
        }
        while (p != null) {
            prev = p;
            if (data.compareTo(p.getData()) < 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        if (data.compareTo(prev.getData()) < 0) {
            prev.setLeft(new StringBstNode(data));
        } else {
            prev.setRight(new StringBstNode(data));
        }
    }

    public String search(String data) {
        StringBstNode p = root;
        if (root == null) {
            return null;
        }
        while (p!=null) {
            if (data.compareTo(p.getData()) < 0) {
                p = p.getLeft();
            } else if(data.compareTo(p.getData()) > 0){
                p = p.getRight();
            }
            else{
               return p.getData();
            }
        }
       return null;

    }

    public void preorder() {
        preorder(root);
    }

    public void preorder(StringBstNode v) {
        System.out.print(v.getData() + " ");
        if (v.getLeft() != null) {
            preorder(v.getLeft());
        }
        if (v.getRight() != null) {
            preorder(v.getRight());
        }
    }

    public void inorder() {
        inorder(root);
    }

    public void inorder(StringBstNode v) {
        if (v.getLeft() != null) {
            inorder(v.getLeft());
        }
        System.out.print(v.getData() + " ");
        if (v.getRight() != null) {
            inorder(v.getRight());
        }
    }

    public void postorder() {
        postorder(root);
    }

    public void postorder(StringBstNode v) {
        if (v.getLeft() != null) {
            postorder(v.getLeft());
        }
        if (v.getRight() != null) {
            postorder(v.getRight());
        }
        System.out.print(v.getData() + " ");
    }
}
