package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorAudioVisualizer;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    private double normalize(int i) {
        int t = i % period;
        double step = 2.0 / period;
        return -1 + t * step;
    }

    @Override
    public double next() {
        int t = state;
        state++;
        int weirdState = t & (t >> 3) & (t >> 8) % period;
        return normalize(weirdState);
    }

    public static void main(String[] args) {
        Generator generator = new StrangeBitwiseGenerator(512);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);
    }
}
