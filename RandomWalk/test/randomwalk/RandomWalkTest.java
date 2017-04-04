/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomwalk;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class RandomWalkTest {
    
    public RandomWalkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addWalk method, of class RandomWalk.
     */
    @Test
    public void testAddWalk_RandomWalk() {
        System.out.println("addWalk");
        RandomWalk newWalk = null;
        RandomWalk instance = new RandomWalk();
        instance.addWalk(newWalk);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addWalk method, of class RandomWalk.
     */
    @Test
    public void testAddWalk_int_int() {
        System.out.println("addWalk");
        int x = 0;
        int y = 0;
        RandomWalk instance = new RandomWalk();
        instance.addWalk(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addWalk method, of class RandomWalk.
     */
    @Test
    public void testAddWalk_0args() {
        System.out.println("addWalk");
        RandomWalk instance = new RandomWalk();
        instance.addWalk();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runBug method, of class RandomWalk.
     */
    @Test
    public void testRunBug() {
        System.out.println("runBug");
        RandomWalk instance = new RandomWalk();
        instance.runBug();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allVisited method, of class RandomWalk.
     */
    @Test
    public void testAllVisited() {
        System.out.println("allVisited");
        RandomWalk instance = new RandomWalk();
        boolean expResult = false;
        boolean result = instance.allVisited();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSteps method, of class RandomWalk.
     */
    @Test
    public void testGetTotalSteps() {
        System.out.println("getTotalSteps");
        RandomWalk instance = new RandomWalk();
        int expResult = 0;
        int result = instance.getTotalSteps();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printRoom method, of class RandomWalk.
     */
    @Test
    public void testPrintRoom() {
        System.out.println("printRoom");
        RandomWalk instance = new RandomWalk();
        instance.printRoom();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class RandomWalk.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        RandomWalk.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
