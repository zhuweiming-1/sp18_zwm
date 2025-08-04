package hw3.hash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /*
          Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        Set<SimpleOomage> sos1 = new HashSet<>();
        Set<SimpleOomage> sos2 = new HashSet<>();

        for (int red = 0; red <= 255; red++) {
            for (int blue = 0; blue <= 255; blue++) {
                for (int green = 0; green <= 255; green++) {
                    if (red % 5 == 0 && blue % 5 == 0 && green % 5 == 0) {
                        SimpleOomage so1 = new SimpleOomage(red, green, blue);
                        SimpleOomage so2 = new SimpleOomage(red, green, blue);
                        sos1.add(so1);
                        sos2.add(so2);
                    }
                }
            }
        }

        int count = 0;
        for (SimpleOomage s1 : sos1) {
            for (SimpleOomage s2 : sos2) {
                if (s1.equals(s2)) {
                    assertEquals(s1.hashCode(), s2.hashCode());
                } else {
                    assertNotEquals(s1.hashCode(), s2.hashCode());
                }
                count++;
                if (count > 100000000) {
                    break;
                }
            }
        }


    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
