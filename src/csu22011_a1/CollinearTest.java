package csu22011_a1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author
 *  @version 03/10/22 22:33:19
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    public static int[] emptyArray = new int[0];
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */

    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear with 3 empty arrays should return zero",     expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast with 3 empty arrays should return zero", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    /**
     * countCollinear Tests
     */
    @Test
    public void testCountCollinear()
    {
        assertEquals(25, Collinear.countCollinear(new int[]{5, 10, 15, 20, 25, 30, 35}, new int[]{5, 10, 15, 20, 25, 30, 35}, new int[]{5, 10, 15, 20, 25, 30, 35}));
    }

    @Test
    public void testForLoopOne()
    {
        assertEquals(0, Collinear.countCollinear(emptyArray, new int[]{5, 10, 15, 20, 25, 30, 35}, new int[]{5, 10, 15, 20, 25, 30, 35}));
    }

    @Test
    public void testForLoopTwo()
    {
        assertEquals(0, Collinear.countCollinear(new int[]{5, 10, 15, 20, 25, 30, 35}, emptyArray, new int[]{5, 10, 15, 20, 25, 30, 35}));
    }

    @Test
    public void testForLoopThree()
    {
        assertEquals(0, Collinear.countCollinear(new int[]{5, 10, 15, 20, 25, 30, 35}, new int[]{5, 10, 15, 20, 25, 30, 35}, emptyArray));
    }

    // [5, 10, 15, 20, 25, 30, 35],[5, 10, 15, 20, 25, 30, 35],[5, 10, 15, 20, 25, 30, 35]
    @Test
    public void testCountCollinearFast()
    {
        assertEquals(25, Collinear.countCollinearFast(new int[]{5, 10, 15, 20, 25, 30, 35}, new int[]{5, 10, 15, 20, 25, 30, 35}, new int[]{25, 35, 30, 20, 5, 15, 10}));
    }
}
