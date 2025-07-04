package synthesizer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);
        Assert.assertTrue(arb.isEmpty());
        arb.enqueue(9.3);
        Assert.assertArrayEquals(new Double[]{9.3, null, null, null}, arb.toArray());
        arb.enqueue(15.1);
        Assert.assertArrayEquals(new Double[]{9.3, 15.1, null, null}, arb.toArray());
        arb.enqueue(31.2);
        Assert.assertArrayEquals(new Double[]{9.3, 15.1, 31.2, null}, arb.toArray());
        Assert.assertFalse(arb.isFull());
        arb.enqueue(-3.1);
        Assert.assertArrayEquals(new Double[]{9.3, 15.1, 31.2, -3.1}, arb.toArray());
        Assert.assertTrue(arb.isFull());
        Assert.assertEquals(Double.valueOf(9.3), arb.dequeue());
        Assert.assertEquals(Double.valueOf(15.1), arb.peek());
        Assert.assertArrayEquals(new Double[]{null, 15.1, 31.2, -3.1}, arb.toArray());

    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        int j = 1;
        for (Integer i : arb) {
            Assert.assertEquals(Integer.valueOf(j++), i);
        }
    }

    @Test
    public void testUpdate() {
        ArrayRingBuffer arb1 = new ArrayRingBuffer(3);
        Assert.assertEquals(1, arb1.update(0));
        Assert.assertEquals(2, arb1.update(1));
        Assert.assertEquals(0, arb1.update(2));

        ArrayRingBuffer arb2 = new ArrayRingBuffer(1);
        Assert.assertEquals(0, arb2.update(0));
        Assert.assertEquals(0, arb2.update(0));
    }

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, arb.toArray());
        try {
            arb.enqueue(5);
            Assert.fail("Expected an RuntimeException to be thrown");
        } catch (RuntimeException e) {
            Assert.assertEquals("Ring buffer overflow", e.getMessage());
        }
    }

    private static ArrayRingBuffer<Integer> arb;

    @Before
    public void initArrayRingBuffer() {
        arb = new ArrayRingBuffer<Integer>(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
    }

    @Test
    public void testDequeue() {
        Assert.assertEquals(Integer.valueOf(1), arb.dequeue());
        arb.enqueue(5);
        Assert.assertArrayEquals(new Integer[]{5, 2, 3, 4}, arb.toArray());
        Assert.assertEquals(Integer.valueOf(2), arb.dequeue());
        Assert.assertEquals(Integer.valueOf(3), arb.dequeue());
        Assert.assertArrayEquals(new Integer[]{5, null, null, 4}, arb.toArray());
        arb.enqueue(6);
        arb.enqueue(7);
        Assert.assertArrayEquals(new Integer[]{5, 6, 7, 4}, arb.toArray());
        Assert.assertEquals(Integer.valueOf(4), arb.dequeue());
        Assert.assertEquals(Integer.valueOf(5), arb.dequeue());
        Assert.assertEquals(Integer.valueOf(6), arb.dequeue());
        Assert.assertEquals(Integer.valueOf(7), arb.dequeue());
        try {
            arb.dequeue();
            Assert.fail("Expected an RuntimeException to be thrown");
        } catch (RuntimeException e) {
            Assert.assertEquals("Ring buffer underflow", e.getMessage());
        }

    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> abr2 = new ArrayRingBuffer<>(10);
        try {
            abr2.peek();
            Assert.fail("Expected an RuntimeException to be thrown");
        } catch (RuntimeException e) {
            Assert.assertEquals("Ring buffer underflow", e.getMessage());
        }
        Assert.assertEquals(Integer.valueOf(1), arb.peek());
        Assert.assertEquals(Integer.valueOf(1), arb.peek());
        Assert.assertEquals(Integer.valueOf(1), arb.peek());
        Assert.assertEquals(Integer.valueOf(1), arb.peek());
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
