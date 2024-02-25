/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats
 *
 *  Analyzes a percolation scenario, given n, the size of the n-by-n grid, and 
 *  T, the number of trials. Calculates the mean, standard deviation, and the
 *  95% confidence interval.
 * 
 *  % java PercolationStats 20 30
 *  Mean            = 0.609250
 *  Stddev          = 0.002210
 *  95% confidence interval = [0.251405, 0.967095]
 ******************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] siteRatios;
    private static double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        siteRatios  = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int randRow = StdRandom.uniformInt(0, n);
                int randCol = StdRandom.uniformInt(0, n);
                percolation.open(randRow, randCol);
            }

            siteRatios[i] = percolation.numberOfOpenSites() / Math.pow(n, 2.0);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double result = StdStats.mean(siteRatios);
        return result;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double result = StdStats.stddev(siteRatios);
        return result;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {        
        double mean = mean();
        double sqrtT = Math.sqrt(siteRatios.length);

        return mean - (CONFIDENCE_95/sqrtT);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        double sqrtT = Math.sqrt(siteRatios.length);

        return mean + (CONFIDENCE_95/sqrtT);
    }

   // test client (see below)
   public static void main(String[] args) {

        int n = 0;
        int t = 0;
        
        n = Integer.parseInt(args[0]);
        t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        System.out.printf("mean\t\t\t= %f\n", stats.mean());
        System.out.printf("stddev\t\t\t= %f\n", stats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }

}