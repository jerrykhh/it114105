import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLinkedList {
    
    private LinkedList list;

    @Before
    public void init(){
        list = new LinkedList();
    }

    @After
    public void teardown(){
        list.clear();
    }

    @Test
    public void testAddToHead1(){
        list.addToHead("1");
        assertEquals(list.toString().trim(), "1");
        assertEquals(list.count, 1);
    }

    @Test
    public void testAddToHead2(){
        list.addToHead("3");
        list.addToHead("2");
        list.addToHead("1");
        assertEquals(list.toString().trim(), "1 2 3");
    }

    @Test
    public void testAddToTail1(){
        list.addToTail("1");
        list.addToTail("2");
        list.addToTail("3");
        assertEquals(list.toString().trim(), "1 2 3");
    }

    @Test
    public void testAddToTail2(){
        list.addToTail("1");
        assertEquals(list.toString().trim(), "1");
        assertEquals(list.count, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFromTail1(){
        list.removeFromTail();
    }

    @Test
    public void testRemoveFromTail2(){
        list.addToTail(1);
        list.removeFromTail();
        assertEquals(list.count, 0);
        assertEquals(list.toString().trim(), "");
    }

    @Test
    public void testRemoveFromTail3(){
        list.addToTail("1");
        list.addToTail("2");
        list.addToTail("3");
        list.addToTail("4");
        list.addToHead("0");
        list.removeFromTail();
        Object data = list.removeFromTail();
        assertEquals(data.toString(), "3");
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveFromHead1() {
        list.removeFromHead();
    }

    @Test
    public void testRemoveFromHead2() {
        list.addToHead("3");
        list.addToHead("2");
        list.addToHead("1");
        list.addToHead("0");
        Object data = list.removeFromHead();
        assertEquals(data.toString(), "0");
    }

    @Test
    public void testRemoveFromHead3(){
        list.addToTail("1");
        list.addToHead("0");
        list.addToTail("2");
        list.removeFromHead();
        list.removeFromHead();
        list.removeFromHead();
        assertEquals(list.toString().trim(), "");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetItemAt1(){
        list.getItemAt(-1);
    }

    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetItemAt2(){
        list.getItemAt(1);
    }

    @Test
    public void testGetItemAt3(){
        list.addToTail(1);
        assertEquals(list.getItemAt(0), 1);
    }

    @Test
    public void testGetItemAt4(){
        list.addToTail(1);
        list.addToTail(2);
        list.addToTail(3);
        list.addToTail(4);
        list.removeFromTail();
        assertEquals(list.getItemAt(2), 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveItemAt1(){
        list.removeItemAt(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveItemAt2(){
        list.removeItemAt(1);
    }

    @Test
    public void testRemoveItemAt3(){
        list.addToTail(1);
        assertEquals(list.removeItemAt(0), 1);
        assertEquals(list.count, 0);
    }

    @Test
    public void testRemoveItemAt4(){
        list.addToTail(1);
        list.addToTail(2);
        list.addToTail(3);
        list.addToTail(4);
        assertEquals(list.removeItemAt(2), 3);
        assertEquals(list.count, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddItemAt1(){
        System.out.println(list.count);
        list.addItemAt(1, 1);
    }

    @Test
    public void testAddItemAt2(){
        list.addItemAt(1, 0);
        assertEquals(list.count, 1);
        assertEquals(list.toString().trim(), "1");
    }

    @Test
    public void testAddItemAt3(){
        list.addItemAt(2, 0);
        list.addItemAt(1, 0);
        assertEquals(list.count, 2);
        assertEquals(list.toString().trim(), "1 2");
    }

    @Test
    public void testAddItemAt4(){
        list.addToTail(1);
        list.addItemAt(2, 1);
        list.addItemAt(3, 2);
        list.addItemAt(4, 3);
        assertEquals(list.count, 4);
        assertEquals(list.toString().trim(), "1 2 3 4");
    }


}
