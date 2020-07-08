package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int times;
    private double[] fractions;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        times = T;
        fractions = new double[T];
        int totalSites = N * N;
        for (int i = 0; i < T; i++) {
            int numOpenedSites = 0;
            Percolation test = pf.make(N);
            while (!test.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                if (!test.isOpen(x, y)) {
                    test.open(x, y);
                    numOpenedSites++;
                }
            }
            fractions[i] = numOpenedSites / totalSites;
        }

    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLow() {
        double mean = mean();
        double std = stddev();
        return mean - (1.96 * std / Math.sqrt(times));
    }

    public double confidenceHigh() {
        double mean = mean();
        double std = stddev();
        return mean + (1.96 * std / Math.sqrt(times));
    }

}
