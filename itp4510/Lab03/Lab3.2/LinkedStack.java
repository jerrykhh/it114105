public class LinkedStack implements Stack {
    private LinkedList sll = new LinkedList();

    @Override
    public int size() {
        return sll.getCount();
    }

    @Override
    public boolean isEmpty() {
        return sll.isEmpty();
    }

    @Override
    public void push(Object item) throws StackFullException {
        sll.addToHead(item);
    }

    @Override
    public Object pop() throws StackEmptyException {
        try {
            Object item = sll.removeFromHead();
            return item;
        } catch (EmptyListException e) {
            throw new StackEmptyException();
        }
    }

    @Override
    public Object top() throws StackEmptyException {
        try {
            Object item = sll.removeFromHead();
            sll.addToHead(item);
            return item;
        } catch (EmptyListException e) {
            throw new StackEmptyException();
        }
    }
}
