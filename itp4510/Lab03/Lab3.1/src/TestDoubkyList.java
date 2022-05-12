public class TestDoubkyList {
    public static void main(String[] args) throws EmptyListException {
        DoublyList s = new DoublyList();
		System.out.println(s);

		s.addToTail(1);
		System.out.println(s);

		s.addToTail(2);
		System.out.println(s);

		s.addToTail(3);
		System.out.println(s);

		s.addToHead(0);

		System.out.println(s.forward());
		System.out.println(s.backward());

		while (!s.isEmpty()) {
			System.out.println("removed: " + s.removeFromHead());
			System.out.println(s);
		}
    }
}
