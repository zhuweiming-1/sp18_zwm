package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorAudioVisualizer;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
        this.state = 0;
    }

    private int sumSample = 0;

    private double normalize(int i) {
        int t = i - sumSample;
        double step = 2.0 / period;
        return -1 + t * step;
    }

    @Override
    public double next() {
        if (state == sumSample + period) {
            sumSample += period;
            period = (int)Math.floor(period * factor);
        }
        int t = state;
        state++;
        return normalize(t);
    }

    public static void main(String[] args) {
        Generator generator = new AcceleratingSawToothGenerator(200, 1.05);
        GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);
        gav.drawAndPlay(4096, 1000000);
    }
}
