import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        List<Integer> l = new ArrayList<>();
        l.add(2);
        l.removeFirst();

        for (int i = 0; i < 7; i++) {
            a.removeLast();
        }

        a.addLast(9);
        a.addLast(8);
        a.addLast(8);
        for (int i = 0; i < 6; i++) {
            a.removeFirst();
        }
    }
}
