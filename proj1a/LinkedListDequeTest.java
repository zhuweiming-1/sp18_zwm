import org.junit.Assert;
import org.junit.Test;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     * <p>
     * && is the "and" operation.
     */
    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below "
                + "(and delete this print statement).");
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    @Test
    public void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below "
                + "(and delete this print statement).");
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    @Test
    public void testAddFirst() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);
    }

    @Test
    public void testAddLast() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(4);
        lld.addLast(5);
        lld.addLast(6);
    }

    @Test
    public void testGet() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(4);
        lld.addLast(5);
        lld.addLast(6);

        Assert.assertEquals(5, (long) lld.get(1));
        Assert.assertEquals(4, (long) lld.get(0));
        Assert.assertEquals(6, (long) lld.get(2));
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(4);
        lld.addLast(5);
        lld.addLast(6);

        Assert.assertEquals(5, (long) lld.getRecursive(1));
        Assert.assertEquals(4, (long) lld.getRecursive(0));
        Assert.assertEquals(6, (long) lld.getRecursive(2));
    }
}
