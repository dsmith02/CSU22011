package csu22011_a2;

import javax.swing.text.DefaultStyledDocument;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 * This class contains the methods of Doubly Linked List.
 *
 * @author
 * @version 11/10/22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 *
 * @param <T> This is a type parameter. T is used as a class name in the
 *            definition of this class.
 *            <p>
 *            When creating a new DoublyLinkedList, T should be instantiated with an
 *            actual class name that extends the class Comparable.
 *            Such classes include String and Integer.
 *            <p>
 *            For example to create a new DoublyLinkedList class containing String data:
 *            DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *            <p>
 *            The class offers a toString() method which returns a comma-separated sting of
 *            all elements in the data structure.
 *            <p>
 *            This is a bare minimum class you would need to completely implement.
 *            You can add additional methods to support your code. Each method will need
 *            to be tested by your jUnit tests -- for simplicity in jUnit testing
 *            introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
        // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         *
         * @param theData  : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode)
        {
            data = theData;
            prev = prevNode;
            next = nextNode;
        }

        public String toString()
        {
            return data + "";
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     *
     * @return DoublyLinkedList
     */
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     *
     * @return true if list is empty, and false otherwise
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Inserts an element in the doubly linked list
     *
     * @param pos  : The integer location at which the new data should be
     *             inserted in the list. We assume that the first position in the list
     *             is 0 (zero). If pos is less than 0 then add to the head of the list.
     *             If pos is greater or equal to the size of the list then add the
     *             element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public void insertBefore(int pos, T data)
    {
        DLLNode nodeToInsert = new DLLNode(data, null, null);
        if (isEmpty())
        {
            head = nodeToInsert;
            tail = nodeToInsert;
            return;
        }
        else if (pos <= 0)
        {
            nodeToInsert.next = head;
            head.prev = nodeToInsert;
            head = nodeToInsert;
            return;
        }

        DLLNode nodeToBeMoved = nodeAt(pos);
        if (nodeToBeMoved == null)
        {
            nodeToInsert.prev = tail;
            tail.next = nodeToInsert;
            tail = nodeToInsert;
        }
        else
        {
            nodeToInsert.prev = nodeToBeMoved.prev;
            nodeToBeMoved.prev.next = nodeToInsert;
            nodeToBeMoved.prev = nodeToInsert;
            nodeToInsert.next = nodeToBeMoved;
        }
    }

    /**
     * Returns the data stored at a particular position
     *
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public T get(int pos)
    {
        if (isEmpty() || pos < 0)
        {
            return null;
        }

        DLLNode node = head;
        for (int i = 0; i < pos && node != null; i++)
        {
            node = node.next;
        }

        return node == null ? null : node.data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     *
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public boolean deleteAt(int pos)
    {
        DLLNode nodeToBeDeleted = nodeAt(pos);
        if (nodeToBeDeleted == null)
        {
            return false;
        }

        if (nodeToBeDeleted == head && nodeToBeDeleted == tail)
        {
            head = null;
            tail = null;
            return true;
        }
        else if (nodeToBeDeleted == head)
        {
            head.next = head;
            head.prev = null;
            return true;
        }
        else if (nodeToBeDeleted == tail)
        {
            tail = tail.prev;
            tail.next = null;
            return true;
        }
        else
        {
            nodeToBeDeleted.prev.next = nodeToBeDeleted.next;
            nodeToBeDeleted.next.prev = nodeToBeDeleted.prev;
            nodeToBeDeleted.prev = null;
            nodeToBeDeleted.next = null;
            return true;
        }
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public void reverse()
    {
        if (isEmpty() || head == tail)
        {
            return;
        }

        DLLNode prev = null;
        DLLNode current = head;

        while (current != null)
        {
            prev = current.prev;
            current.prev = current.next;
            current.next = prev;
            current = current.prev;
        }

        DLLNode tmp = head;
        head = tail;
        tail = tmp;
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public void makeUnique()
    {
        //TODO
    }

    /**
     * Returns a reference to the node at the pos position.
     * If pos < 0, it returns the head.
     * If pos is greater than the size of the DLL, it returns the tail.
     * @param pos
     * @return
     */
    public DLLNode nodeAt(int pos)
    {
        if (pos == 0)
        {
            return head;
        }
        else if (pos < 0)
        {
            return null;
        }

        int count = 0;
        DLLNode current = head;
        while ((current = current.next) != null && ++count < pos);

        return current;
    }


    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     *
     * @param item : the item to push on the stack
     *             <p>
     *             Worst-case asymptotic running time cost: TODO
     *             <p>
     *             Justification:
     *                          TODO
     */
    public void push(T item)
    {
        //TODO
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     *
     * @return the last item inserted with a push; or null when the list is empty.
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public T pop()
    {
        //TODO
        return null;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     *
     * @param item : the item to be enqueued to the stack
     *             <p>
     *             Worst-case asymptotic running time cost: TODO
     *             <p>
     *             Justification:
     *                          TODO
     */
    public void enqueue(T item)
    {
        //TODO
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     *
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     * <p>
     * Worst-case asymptotic running time cost: TODO
     * <p>
     * Justification:
     *  TODO
     */
    public T dequeue()
    {
        //TODO
        return null;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     * <p>
     * Worst-case asymptotic running time cost:   Theta(n)
     * <p>
     * Justification:
     * The code consists of the lines before the for-loop, the for-loop itself and the lines after the for-loop.
     * <p>
     * The lines before the for loop involve
     * - the creation of a StringBuilder object which does not depend on the length of the list. Therefore it takes Theta(1) time.
     * - the allocation and assignment of two variables which are constant time operations.
     * Thus the code before the for-loop will take Theta(1) time to run.
     * <p>
     * The lines after the for-loop involve a single return instruction and thus take Theta(1) time.
     * <p>
     * The for-loop itself will iterate over all elements of the DLL. Thus it will perform Theta(N) iterations.
     * Each iteration will involve:
     * * The if-conditional will run:
     * - the if-then-else conditions and branching, which are constant time operations. Thus these cost Theta(1) time.
     * - The then-branch of the conditional calls StringBuilder's append() method, which (from the Java documentation) we know it runs in Theta(1) time.
     * - the else-branch of the conditional involves a single assignment, thus runs in Theta(1) time.
     * Therefore the if-then-else conditions will cost Theta(1) in the worst case.
     * * The final call to StringBuilder's append will cost again Theta(1)
     * * the nested call to toString() will cost time proportional to the length of the strings (but not the length of the list).
     * Assuming strings are relatively small compared to the size of the list, this cost will be Theta(1).
     * Therefore each iteration of the loop will cost Theta(1).
     * Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
     * <p>
     * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) = Theta(N) time in the worst case.
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;

        // iterate over the list, starting from the head
        for (DLLNode iter = head; iter != null; iter = iter.next)
        {
            if (!isFirst)
            {
                s.append(",");
            }
            else
            {
                isFirst = false;
            }
            s.append(iter.data.toString());
        }

        return s.toString();
    }

    public int getSize()
    {
        if (isEmpty())
        {
            return 0;
        }

        DLLNode currentNode = head;
        int size = 0;
        while ((currentNode = currentNode.next) != null)
        {
            size++;
        }
        return size;
    }
}



