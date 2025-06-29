import org.junit.Before;
import org.junit.Test;

public class ArrayDequeTest {
    ArrayDeque<Integer> a = null;

    @Before
    public void initArrayDeque() {
        a = new ArrayDeque<>();
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


//        Assert.assertArrayEquals(exp, a.toArray());
//        Assert.assertEquals(exp, a.toArray());
    }

    @Test
    public void testAddLast() {
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        Integer[] exp = {4, 5, 6};
//        Assert.assertArrayEquals(exp, a.toArray());
    }
}
