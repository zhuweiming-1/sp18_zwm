package synthesizer;

/* Since this test is part of a package, we have to import the package version of StdAudio. */
/* Don't worry too much about this, we'll get there in due time. */

import edu.princeton.cs.introcs.StdAudio;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the GuitarString class.
 *
 * @author Josh Hug
 */

public class TestGuitarString {
    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testTic() {
        // 创建一个频率为 11025 的 GuitarString，它是一个长度为 4 的 ArrayRingBuffer。
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // 记录前四个值，边走边动。
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic();
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // 如果我们再抽一次，它应该等于 0.996*0.5*（s1 + s2）
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // 使用容差 0.001 检查新样品是否正确。
        // 有关 assertEquals（double， double） 容差如何工作的描述，请参阅 JUnit 文档
        assertEquals(expected, s5, 0.001);

    }

    /**
     * Calls tests for GuitarString.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestGuitarString.class);
    }
} 
