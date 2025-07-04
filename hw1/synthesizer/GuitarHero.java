package synthesizer;

import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

import java.util.HashMap;
import java.util.Map;

public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static Map<Integer, GuitarString> guitarStrings = new HashMap<>();

    public static void main(String[] args) {

        for (int i = 0; i < 37; i++) {
            guitarStrings.put(i, new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0)));
        }
        GuitarString stringA = guitarStrings.get(0);
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (!keyboard.contains(String.valueOf(key))) {
                    continue;
                }
                int i = keyboard.indexOf(key);
                stringA = guitarStrings.get(i);
                stringA.pluck();
            }

            /* compute the superposition of samples */
            double sample = stringA.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringA.tic();
        }
    }
}
