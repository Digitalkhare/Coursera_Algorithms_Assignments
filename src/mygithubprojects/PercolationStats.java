package mygithubprojects;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author osomo
 */
public class PercolationStats {

    /**
     * @param args the command line arguments
     */
    private final double[] trialResults;
    private final int numberOftrials;

    public PercolationStats(int n, int trials) {
        numberOftrials = n;
        trialResults = new double[trials];
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int column = StdRandom.uniform(1, n + 1);
                percolation.open(row, column);
            }
            trialResults[trial] = (percolation.numberOfOpenSites() / Math.pow(n, 2));
        }
    }

    public double mean() {
        return StdStats.mean(trialResults);
    }

    public double stddev() {
        return StdStats.stddev(trialResults);
    }

    public double confidenceLo() {
        return mean() - (stddev() / Math.sqrt(numberOftrials));
    }

    public double confidenceHi() {
        return mean() + (stddev() / Math.sqrt(numberOftrials));
    }

    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            int size = StdIn.readInt();
            int trials = StdIn.readInt();
            PercolationStats stats = new PercolationStats(size, trials);
            StdOut.println("mean =   " + stats.mean());
            StdOut.println("stddev =   " + stats.stddev());
            StdOut.println("95% confidence interval =    [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        }

    }

}
