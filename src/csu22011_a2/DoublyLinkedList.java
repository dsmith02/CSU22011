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
     * Worst-case asymptotic running time cost: theta(1)
     * <p>
     * Justification:
     *  As there is no case where either the head or tail is null, we can safely
     *  check only one of these fields for null.
     *
     *  If one is null, then the list is empty and as this is a simple operation,
     *  it will run in the worst-case at theta(1).
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
     * Worst-case asymptotic running time cost: theta(N)
     * <p>
     * Justification:
     *  We create a node to insert -> theta(1).
     *  We then check if it is empty and make some simple assignments if so -> theta(1).
     *  We then check if the position is less than 0, in which case we make more assignments
     *  and thus worst-case is still theta(1).
     *
     *  If the node is further up the list, we then have to find the node which uses
     *  the nodeAt function, which runs in theta(N) time in the worst-case (see below).
     *
     *  The last if-else branches then execute, which only contain simple assignemnts
     *  and thus run in theta(1) time.
     *
     *  Therefore, the worst-case is theta(N) as it calls the nodeAt() function.
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
     * Worst-case asymptotic running time cost: theta(N)
     * <p>
     * Justification:
     *  We first check if the list isEmpty, which runs in constant time and ensure that the position
     *  is valid -> theta(1).
     *
     *  We then assign the node = head -> theta(1)
     *  We then iterate over the loop, making some simple assignments which run in constant time.
     *  If the position is equal to the tail or worse, it will iterate over all nodes -> theta(N).
     *
     *  Therefore the worst-case running time is theta(N)
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
     * Worst-case asymptotic running time cost: theta(N)
     * <p>
     * Justification:
     *  Firstly we get the node reference, which in worst-case (see below) is theta(N).
     *  All other branches, comparisons and assignments run in constant time so it can
     *  be deduced that the worst-case running time of deleteAt is theta(N)
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
            head = head.next;
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
     * Worst-case asymptotic running time cost: theta(N)
     * <p>
     * Justification:
     *  Firstly, if the list is empty or is a one element list, we do nothing.
     *  These all run in constant time -> theta(1).
     *
     *  We then make some simple assignments and enter a while loop which changes the references
     *  of the nodes so that they are reversed. All nodes need to be visited so the worst case time
     *  is theta(N).
     *
     *  The final assignments have no gravity on the worst-case running time as they run in
     *  theta(1) time.
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
     * Worst-case asymptotic running time cost: theta(N^2)
     * <p>
     * Justification:
     *  Again, if the list is empty or contains only one element, we do nothing -> theta(1).
     *
     *  We then enter a for loop which makes some simple assignments and creates a pos variable -> theta(1)
     *  We then enter another for loop, which does the same as the previous for-loop -> theta(1)
     *
     *  However, this algorithm takes each element and in turn compares it to every other element
     *  in the list i.e. a nested for-loop. We use the deleteAt function, which runs in theta(N)
     *  time.
     *
     *  All in all, this algorithm runs in theta(N) * (theta(N) + theta(N)), which simplifies
     *  to theta(N^2) in the worst-case as all N elements ar visited N times.
     */
    public void makeUnique()
    {
        if (isEmpty() || head == tail)
        {
            return;
        }

        for (DLLNode i = head; i != null; i = i.next)
        {
            int pos = 0;
            for (DLLNode j = head; j != null; j = j.next)
            {
                if (j.data == i.data && j != i)
                {
                    j = j.prev;
                    deleteAt(pos);
                    pos--;
                }
                pos++;
            }
        }
    }

    /**
     * Returns a reference to the node at the pos position.
     * If pos < 0, it returns the head.
     * If pos is greater than the size of the DLL, it returns the tail.
     * @param pos
     * @return
     *
     * Worst-case: theta(N)
     *
     * Justification:
     *  If the list is empty, we do a simple comparison with head and null and return null -> theta(1)
     *
     *  We then check if it is the first element by checking if pos = 0 -> theta(1)
     *
     *  If pos is invalid i.e. < 0 -> theta(1)
     *
     *  We make two simple assignments of count = 0 and current = head, which are constant.
     *  We then enter a while loop, which increments the current node, which is theta(1).
     *  It also makes a comparison to null in constant time.
     *  The counter is then incremented and checked if it is less than pos -> theta(1).
     *
     *  All in all, if the node at the position specified is the tail or out of range,
     *  then nodeAt will iterate over all N nodes.
     *
     *  Therefore the worst-case is theta(N)
     */
    public DLLNode nodeAt(int pos)
    {
        if (head == null)
        {
            return null;
        }

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
     *             Worst-case asymptotic running time cost: theta(1)
     *             <p>
     *             Justification:
     *                          Simply uses the insertBefore method but with the parameter 0 fixed,
     *             therefore it is only changing the head which runs in theta(1) time.
     */
    public void push(T item)
    {
        insertBefore(0, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     *
     * @return the last item inserted with a push; or null when the list is empty.
     * <p>
     * Worst-case asymptotic running time cost: theta(1)
     * <p>
     * Justification:
     *  This method checks if it is empty, which runs in constant time.
     *
     *  It then saves the data to be returned from the head, which is the top of the stack.
     *  Then deletes the old head, which according to my analysis of the deleteAt() function
     *  runs in theta(1) as the parameter is fixed to 0.
     */
    public T pop()
    {
        if (isEmpty())
        {
            return null;
        }

        T data = head.data;
        deleteAt(0);
        return data;
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
     *             Worst-case asymptotic running time cost: theta(1)
     *             <p>
     *             Justification:
     *                          Merely calls the push() function which runs in constant time.
     */
    public void enqueue(T item)
    {
        push(item);
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     *
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     * <p>
     * Worst-case asymptotic running time cost: theta(1)
     * <p>
     * Justification:
     *  It checks if the list is empty, which runs in constant time.
     *
     *  It checks if the list is empty, with the if statement and its assignments
     *  running in constant time -> theta(1).
     *
     *  If not, it meerely changes the tail to the element previous to the current tail.
     *  And returns the old tail's data, with the assignemnts running in constant time.
     *
     *  Therefore, theta(1).
     */
    public T dequeue()
    {
        if (isEmpty())
        {
            return null;
        }

        if (head == tail)
        {
            T data = head.data;
            head = null;
            tail = null;
            return data;
        }

        T data = tail.data;
        DLLNode oldTail = tail;
        tail = tail.prev;
        tail.next = null;
        oldTail.prev = null;
        return data;
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
}



