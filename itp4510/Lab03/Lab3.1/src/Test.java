public class Test {
    public static void main(String[] args) {
        LinkedList s = new LinkedList();
        System.out.println(s);
        s.addToHead("Betty");
        s.addToTail("Dave");
        s.addToTail("Felix");
        s.addToTail("Jenny");
        System.out.println(s);
        System.out.println("count = " + s.getCount());
        System.out.println("Pos 0 = " + s.getItemAt(0));
        System.out.println("Pos 2 = " + s.getItemAt(2));
        System.out.println("Last = " + s.getItemAt(s.getCount() - 1));
        s.addItemAt("Gary", 3);
        System.out.println(s);
        s.addItemAt("Apple", 0);
        System.out.println(s);
        System.out.println("Removing " + s.removeItemAt(2));
        System.out.println(s);
        System.out.println("Removing " + s.removeItemAt(0));
        System.out.println(s);
        System.out.println("Removing " + s.removeItemAt(3));
        System.out.println(s);

        // Comparator c = new StringComparator();
        // ComparatorLinkedList s = new ComparatorLinkedList(c);
        // System.out.println(s);
        // s.insertInOrder("Betty");
        // System.out.println(s);
        // s.insertInOrder("Dave");
        // System.out.println(s);
        // s.insertInOrder("Catherine");
        // System.out.println(s);
        // s.insertInOrder("Thomas-1");
        // System.out.println(s);
        // s.insertInOrder("Sandra");
        // System.out.println(s);
        // s.insertInOrder("Thomas-2");
        // System.out.println(s);
        // s.insertInOrder("Alice-1");
        // System.out.println(s);
        // s.insertInOrder("Alice-2");
        // System.out.println(s);
    }
}
