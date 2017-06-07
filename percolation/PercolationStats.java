import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    private int noe;
    private double []experiments;
    // Perform T independent experiments (Monte Carlo simulations) on an 
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        noe = T;
        if (N <= 0 || noe <= 0) {
            throw new IllegalArgumentException();
        }
        else { 
          experiments = new double [noe];
          for (int t = 0; t < noe; t++) {
            Percolation perc = new Percolation(N);
            double count = 0.0;
                while (!perc.percolates()) {
                    int i = StdRandom.uniform(0, N);
                    int j = StdRandom.uniform(0, N);
                    perc.open(i, j);
                } 
                count = perc.numberOfOpenSites();
                experiments[t] = (count/(N*N));
          }
        }
    }
    
    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(experiments);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(experiments);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(noe)); 
    }
    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(noe));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
