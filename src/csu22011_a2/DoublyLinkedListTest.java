package csu22011_a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

//-------------------------------------------------------------------------

/**
 * Test class for Doubly Linked List
 *
 * @author
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------

    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);

        testDLL.insertBefore(0, 4);
        assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString());
        testDLL.insertBefore(1, 5);
        assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString());
        testDLL.insertBefore(2, 6);
        assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString());
        testDLL.insertBefore(-1, 7);
        assertEquals("Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString());
        testDLL.insertBefore(7, 8);
        assertEquals("Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString());
        testDLL.insertBefore(700, 9);
        assertEquals("Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString());

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        assertEquals("Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10, 1);
        assertEquals("Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10, 1);
        assertEquals("Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString());
    }

    @Test
    public void testReverse()
    {
        // Test on DLL of size 1.
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.reverse();
        assertEquals("1", testDLL.toString());

        // Test on DLL of size 3.
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);
        testDLL.reverse();
        assertEquals("3,2,1", testDLL.toString());

        // Test empty DLL
        testDLL = new DoublyLinkedList<>();
        assertEquals("", testDLL.toString());
    }


    @Test
    public void testDeleteAt()
    {
        // Test on empty
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<>();
        assertEquals(false, testDLL.deleteAt(0));
        assertEquals(false, testDLL.deleteAt(1));

        // Test on DLL of size one
        testDLL.insertBefore(0, 3);
        assertEquals("3", testDLL.toString());
        assertEquals(true, testDLL.deleteAt(0));
        assertEquals("", testDLL.toString());


        // Test on DLL of size three
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(1, 2);
        testDLL.insertBefore(2, 3);
        assertEquals(true, testDLL.deleteAt(2));
        assertEquals("1,2", testDLL.toString());
    }

    @Test
    public void testMakeUnique()
    {
        // Test on empty
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.makeUnique();
        assertEquals("", list.toString());

        // Test on size one
        list.insertBefore(0, 1);
        list.makeUnique();
        assertEquals("1", list.toString());

        // Test on size three
        list.insertBefore(1, 4);
        list.insertBefore(2, 4);
        list.insertBefore(3, 5);
        list.insertBefore(4, 8);
        list.makeUnique();
        assertEquals("1,4,5,8", list.toString());

        // Test on size four
        list.insertBefore(0, 1);
        list.insertBefore(1, 4);
        list.insertBefore(2, 4);
        list.insertBefore(3, 5);
        list.insertBefore(4, 8);
        list.insertBefore(5, 1);
        list.makeUnique();
        assertEquals("1,4,5,8", list.toString());
    }

    @Test
    public void testPush()
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // One element
        list.push(0);
        assertEquals("0", list.toString());

        // Two elements
        list.push(1);
        assertEquals("1,0", list.toString());

        // Test with all digits
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.push(6);
        list.push(7);
        list.push(8);
        list.push(9);
        assertEquals("9,8,7,6,5,4,3,2,1,0", list.toString());
    }

    @Test
    public void testPop()
    {
        // Test on empty list
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertEquals(null, list.pop());

        // One element
        list.push(0);
        assertEquals(Integer.valueOf(0), list.pop());

        // Two elements
        list.push(1);
        list.push(2);
        assertEquals(Integer.valueOf(2), list.pop());

    }

    @Test
    public void testEnqueue()
    {
        // Test on one elem
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.enqueue(0);
        assertEquals("0", list.toString());

        // Test on two elems
        list.enqueue(1);
        assertEquals("1,0", list.toString());

        // Test with all digits
        list.enqueue(2);
        list.enqueue(3);
        list.enqueue(4);
        list.enqueue(5);
        list.enqueue(6);
        list.enqueue(7);
        list.enqueue(8);
        list.enqueue(9);
        assertEquals("9,8,7,6,5,4,3,2,1,0", list.toString());
    }

    @Test
    public void testDequeue()
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // Test empty list
        assertEquals(null, list.dequeue());

        // Test one elem
        list.enqueue(0);
        assertEquals(Integer.valueOf(0), list.dequeue());
        assertEquals("", list.toString());

        // Test with all digits
        list.enqueue(2);
        list.enqueue(3);
        list.enqueue(4);
        list.enqueue(5);
        list.enqueue(6);
        list.enqueue(7);
        list.enqueue(8);
        list.enqueue(9);
        assertEquals(Integer.valueOf(2), list.dequeue());
        assertEquals("9,8,7,6,5,4,3", list.toString());
    }

    @Test
    public void testGet()
    {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // Test empty
        assertEquals(null, list.get(0));

        // Test negative
        assertEquals(null, list.get(-1));

        // Test one element
        list.insertBefore(0, 1);
        assertEquals(Integer.valueOf(1), list.get(0));

        // Test 3 elements
        list.insertBefore(1, 2);
        list.insertBefore(2, 3);
        assertEquals(Integer.valueOf(2), list.get(1));

        // Test tail
        assertEquals(Integer.valueOf(3), list.get(2));

        // Test if it goes beyond the list and returns null
        assertEquals(null, list.get(3));
    }
}

