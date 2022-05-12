public class ArrayStack implements Stack {
    public static final int CAPACITY =1000;
    private int capacity;
    private Object[] array;
    private int top =-1;
	public ArrayStack() {
		this(CAPACITY);
	}

    public ArrayStack(int cap){
        capacity =cap;
        array = new Object[capacity];
    }
    @Override
    public int size() {
        return top+1;
    }

    @Override
    public boolean isEmpty() {
        return (top<0);
    }

    @Override
    public void push(Object item) throws StackFullException {
        if(size()==capacity)
            throw new StackFullException();
        array[++top]=item;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if(isEmpty())
            throw new StackEmptyException();
        Object item =array[top];
        array[top--]=null;
        return item;
    }

    @Override
    public Object top() throws StackEmptyException {
        if(isEmpty())
            throw new StackEmptyException ();
            return array[top];
    }
    
}