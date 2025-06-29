import org.junit.Before;
import org.junit.Test;

public class ArrayDequeTest {
    ArrayDeque<Integer> a = null;

    @Before
    public void initArrayDeque() {
        a = new ArrayDeque<>();
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
    }

    @Test
    public void testAddFirst() {
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        a.removeFirst();
        a.removeLast();

    }

    @Test
    public void testAddLast() {
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        Integer[] exp = {4, 5, 6};
    }

    @Test
    public void testAdjustCapacity() {
        a.addLast(9);

        for (int i = 0; i < 7; i++) {
            a.removeLast();
        }
    }
}
