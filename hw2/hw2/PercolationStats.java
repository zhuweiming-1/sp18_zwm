package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] x; // 每次渗透时open site的比例
    private int T; // 实验次数
    private static final double CONSTANT = 1.96;


    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        x = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                perc.open(row, col);
            }
            x[i] = perc.numberOfOpenSites() * 1.0 / (N * N);
        }
    }

    public double mean() {
        double sum = 0;
        for (double xt : x) {
            sum += xt;
        }
        return sum / T;
    }

    public double stddev() {
        double avg = mean();
        double varianceSum = 0;
        for (double xt : x) {
            varianceSum += Math.pow(xt - avg, 2);
        }
        return Math.sqrt(varianceSum / (T - 1));
    }

    public double confidenceLow() {
        double avg = mean();
        double dev = stddev();

        return avg - (CONSTANT * dev / Math.sqrt(T));
    }

    public double confidenceHigh() {
        double avg = mean();
        double dev = stddev();

        return avg + (CONSTANT * dev / Math.sqrt(T));
    }

}
